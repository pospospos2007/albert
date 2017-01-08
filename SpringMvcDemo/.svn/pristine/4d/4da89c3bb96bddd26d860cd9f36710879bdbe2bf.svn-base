
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<body>

	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>提现
		</h2>
	</div>
<c:if test="${user.roles == 1 }">
      </c:if>
	<div class="login_f rechar_f">
		<form action="<%=request.getContextPath() %>/pay/chinapnr/start-Cash" method="post" id="cashForm">
			<dl style="width: 25%">
				<dd style="background: #e6eaed">账户金额</dd>
				<dd style="background: #e6eaed" class="flat">可提金额</dd>
				<dd>提现金额</dd>
			</dl>
			
			<dl style="width: 75%">
				<dd style="color: #f4406f; background: #e6eaed">
						<span class="col-xs-4" style="margin-top: 6px;"> <span id="availableAmount">0.00</span>元</span>
				</dd>
				<dd style="color: #f4406f; background: #e6eaed" class="flat red">
						<span class="col-xs-4" style="margin-top: 6px;"> <span id="availableAmount3">0.00</span>元</span>
				</dd>
				<dd>
					<input type="text"  id="getcash"  name="payReqFromApp.transAmt" placeholder="请输入提现金额" />
					<input type="hidden" name="payReqFromApp.userId" value="${user.userId}" /> 
					<input type="hidden" name="bankId" value="${userCode }" />
					<input type="hidden" name="cardId" value="${cardNo}" />
					<input type="hidden" name="payReqFromApp.gateBusiId" id="BusiId" value="B2C" />
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
	<div class="reg_bot tc">每笔提现收取1元手续费</div>
	<div class="login_f_btn" id="withDraw">提现</div>

	<div class="reg_bot tl" style="width: 85%">
		温馨提示
		<p>*  提现手续费每笔1元。</p>
		<p>*  当天充值后需到第二天才可提现！</p>
		<p>*  用手机上充值后将会取代先前绑的提现卡！</p>
		<p>*  为了您的账户安全，请在充值前进行身份认证、手机绑定以及提现密码设置。</p>
		<p>*  您的账户资金将由第三方平台托管。</p>
		<p>*  请注意您的银行卡充值限制，以免造成不便。</p>
		<p>*  禁止洗钱、信用卡套现、虚拟交易等行为，一经发现并确认，将终止该账户的使用。</p>
		<p>*  如果充值金额没有及时到账，请联系客服，${servicePhone}。</p>
	</div>
	<div class="theme-popover-mask"></div>
	<script type="text/javascript">
/* jQuery(document).ready(function($) {
$('.login_f_btn').click(function(){
		 $('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
		$('#cashFrom').submit();
		
	});

    $('.index_login').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	}) 

}) */
var reg=/^[0-9]+(\.[0-9]{1,2})?$/;//金额验证正则表达式

$(function(){
	var d_url=document.location.href;
	var s=d_url.indexOf("#");
	var t=d_url.substring(s+1);
	if (t == 'withdraw'){
		$('#withdraw').tab('show');//初始化显示哪个tab
	}
//余额查询
	//var  yeData=data[0];
	var d=new Date();
	var id = $("#userId").val();
	  $.getJSON("${base}/pay/chinapnrj/start-QueryMoney.action",{'payReqFromApp.userId':${user.userId},'version':d.getTime()},function (data){
		
		  if(data == null ){
			$("#availableAmount").html('0');
			$("#availableAmount2").html('0');
			$("#balance").html('0');
			$("#freezeAmount").html('0');
			//yeData=new array(0,0,0);
			return;
		 }
		  //yeData=data;
		  for(var key in data){
			  var val="0";
			  if(data[key])val=data[key];
			  $("#"+key).html(val.replace(/\d(?=(\d{3})+\.)/g, '$&,'));
			}
		  //modified by cjq 提现余额
	  	  $("#availableAmount2").html($("#availableAmount").html());
		  var availableAmount3= data['availableAmount']-1;
		 // alert(availableAmount3);
		  if (availableAmount3>1){
			  $("#availableAmount3").html(availableAmount3);
		  }else{
			  $("#availableAmount3").html('0');
		  }
	  });
	  
		//充值提现条件
		function checkPre(action){
			
			if(action=="withDraw"){
				 if(${empty userCode}){
					  showDialogHTML("你还未进行实名认证!<a href='<%=request.getContextPath() %>/toRealNameMobile'>现在就去认证</a>");
					  return false;
				  }
				  if(${empty cardNo}) {
					  showDialogHTML("你还未绑定银行卡!<a href='<%=request.getContextPath() %>/pay/chinapnr/start-BindCard.action'>现在就去绑定</a>");
					return false;
				  }
				 
				  return true;
			}
			return true;
		}
		
	  $("#withDraw").click(function(){
		  
			if(checkPre("withDraw")){
				var getcash = $.trim($("#getcash").val());
			  	var availableAmount=parseFloat($("#availableAmount").text().replace(/\,/g,""));
				 if(getcash==""){
					 showDialogHTML("提现金额不能为空");
		             return;
				 } if(getcash<1){
					 showDialogHTML("提现金额最少为1元！");
		             return;
				 }
				 else if(!reg.test(getcash)){ 
					 showDialogHTML("提现金额输入不正确,请输入xx.xx的格式");
		             return;
				 }else if((availableAmount*1-getcash*1)<1){
					 showDialogHTML("你的余额不足");
				     return false;
				 }/*else if(getcash-200000>0){
					 openL("提现提醒","提现金额不能大于20万");
				     return false;
				 }*/ 
				 $("#cashForm").submit();
			}
		});
});


</script>

<%@ include file="/mobile/comm/bott.jsp"%>
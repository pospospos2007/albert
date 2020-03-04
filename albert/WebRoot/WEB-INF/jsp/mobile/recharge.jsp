<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>

<body>

	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>快捷充值
		</h2>
	</div>
<c:if test="${user.roles == 1 }">
      </c:if>
	<div class="login_f rechar_f">
		<form action="<%=request.getContextPath() %>/pay/chinapnr/start-Recharge" method="post"  id="chargeForm">
			<dl style="width: 25%">
				<dd style="background: #e6eaed">账户金额</dd>
				<dd>我要充值</dd>
			</dl>
			<dl style="width: 75%">
				<dd style="color: #f4406f; background: #e6eaed">
				<span class="col-xs-4" style="margin-top: 6px;"> ￥<span id="availableAmount">0.00</span>元</span>
				</dd>
				<dd>
					<input type="text" id="charge_amount" name="payReqFromApp.transAmt"  placeholder="请输入充值金额" />
					<input type="hidden" name="payReqFromApp.userId" value="${user.userId}" id="userId" />
					<input type="hidden" name="payReqFromApp.gateBusiId" id="BusiId" value="B2C" />
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
	<div class="reg_bot tc">充值手续费暂时由平台支付</div>
	<div class="login_f_btn" id="recharge">充值</div>

	<div class="reg_bot tl" style="width: 85%">
		温馨提示
		<p>*  为了您的账户安全，请在充值前进行身份认证和交易密码设置。</p>
		<p>*  您的账户资金将通过第三方平台进行充值。</p>
		<p>*  请注意您的银行卡充值限制，以免造成不便。</p>
		<p>*  禁止洗钱、信用卡套现、虚假交易等行为，一经发现并确认，将终止该账户的使用。</p>
		<p>*  如果充值金额没有及时到账，请联系客服，${servicePhone}。</p>
	</div>



	<script>
	/*  jQuery(document).ready(function($) {
	$('.login_f_btn').click(function(){
			$('#form').submit();
	});

		 $('.index_login').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		}) 

	})  */
	var reg=/^[0-9]+(\.[0-9]{1,2})?$/;//金额验证正则表达式
	//余额查询
	//var  yeData=data[0];
	var d=new Date();
	var id = $("#userId").val();
	  $.getJSON("<%=request.getContextPath() %>/pay/chinapnrj/start-QueryMoney.action",{'payReqFromApp.userId':${user.userId},'version':d.getTime()},function (data){
		
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
		 
	  });
	  $(function(){
			var d_url=document.location.href;
			var s=d_url.indexOf("#");
			var t=d_url.substring(s+1);
			if (t == 'withdraw'){
				$('#withdraw').tab('show');//初始化显示哪个tab
			}
	//充值提现条件
		function checkPre(action){
			if(action=="recharge"){
				  if('${user.userCode}' == ''){
					  showDialogHTML("你还没有注册托管支付的账户，请你去注册支付账户!<a href='<%=request.getContextPath() %>/toRealNameMobile'>现在就去开户</a>");
					  return false;
				  }
				  return true;
			}
			return true;
		}
	//充值
	
		$("#recharge").click(function(){
			if(checkPre("recharge")){
				 var charge = $.trim($("#charge_amount").val());
				// var rradioId=$("input:radio:checked").val();
				 if(charge==""){
					 showDialogHTML("充值金额不能为空");
			           //openL("充值提醒","充值金额不能为空");
			          return;
			     }
				 if(charge<0.01){
					 showDialogHTML("充值金额至少为0.01元！");
			           //openL("充值提醒","充值金额不能为空");
			          return;
			     }
				if((charge*1)>50000){
					showDialogHTML("单笔充值金额不能超过5万");
			           return ;
				 }
			    if(!reg.test(charge)){
			    	 showDialogHTML("充值金额输入不正确,请输入xx.xx的格式");
			           return;
			     } 
	 		    /*  if(rradioId==null||rradioId==""){
	 				  openL("充值提醒","请选择充值银行");
	 				  return ;
	 			  } */
			     $("#chargeForm").submit();
			}
		});
	  });
	 
	
</script>

<%@ include file="/mobile/comm/bott.jsp"%>
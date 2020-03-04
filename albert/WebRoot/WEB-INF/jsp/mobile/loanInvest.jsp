<%@ page contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
 <script src="${base}/js/bootstrap/3.0.3/js/bootstrap.js"></script>
<script type="text/javascript" src="${base}/js/jquery-1.10.1.min.js"></script>

<c:set target="${self}" property="products" value='<%= new String[]{"按月付息，到期还本", "一次性还本付息", "等额本息"}%>' />
<c:set target="${self}" property="termUnit" value='<%= new String[]{"","天", "周", "个月"}%>' />
<%--
# 投资模块相关对话框
--%>
<c:set target="${self.content}" property="free">
<style type="text/css">
.l_i_tr{
	line-height: 32px;
}
.modal-body_new{
	width: 722px;
	height: 465px;
}
.affirm_input_new{
	width: 621px;
	height: 140px;
	margin-left: 30px;
}
.invest-modal-footer_new{
	width: 80%;
	height: 40px;
	border: 0px;
	margin-left: 40px;
	padding-top:12px; 
}
.btn-default_new{
	width: 100px;
	height: 30px;
}
.investsucc_new{
	width:720px ;
	height: 293px;
	
}
.investsucc_new_top{
	width:720px ;
	height: 50px;
}
</style>
<c:set target="${self.content}" property="main">
	<script type="text/javascript">
		var ver_amount =  /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
		// 刷新验证码
		function coderefresh() {  
			var id = document.getElementById("imgverCode");
			var d = new Date();
			id.src = '${ctx }/registVerfyCode?a=' + d.getTime();
		}
		 //投资成功后跳转
		  function successJump(){
		 	var url = window.location.href;
		 	url = url + "#jump"
		     window.location.href=url;
		 	window.location.reload(true);
		 }
		  function jump(count) {
	   	     	var count = $("#closetime").html();
	   	         window.setTimeout(function(){
	   	             count--;
	   	             if(count > 0) {
	   	                 $("#closetime").html(count);
	   	                 jump(count);
	   	             } else {
	   	                successJump();
	   	             }
	   	         }, 1000);
	   	    }
<%-- 		  var biddingAmount = <%=request.getParameter("biddingAmount")%>;
		  var amount= <%=request.getParameter("amount")%>;
	   	 	  var progress = biddingAmount/amount*100;
   	 	  alert(progress);
   	 	$("#proGress").append(progress)
	   	 	  
	   	 	  $('#invest-modal').find('#loanProgress').width(progress+"%");
	   	 	  $('#invest-modal').find('#loanProgressbaifenbi').html(loanProgressbaifenbi);
 --%>		
			 function buttonls() {
		 			//确认投资buttonls
			   	    // $(this).addClass('disabled');
			   	 	 var verifycode = $("#verifycode").val();
			   	 	 var agreement = document.getElementById("agreecheckbox").checked;
			   	 	 var usercash = Number(${userVo.cash});
			   	 	 var money2 = Number(${param.money});
			   	 	 if(verifycode==""||verifycode==null){
			   	 		$("#message").html("请输入验证码");
		 	   	 	$(this).removeClass('disabled');
		 	   		return ;
			   	 	 }
			   	 	 if(!agreement){
			   	 		$("#message").html("请确认协议");
		 	   	 	$(this).removeClass('disabled');
		 	   		return ;
			   	 	 }
			   	 	 else{if(money2>usercash){
			   	 		 alert("你的金额不足！");
			   	 		 return;
			   	 	 }
			   	 	 else{
			   	 		$("#loadingImage").show();
			   	 		var url = "/verifycode";
			 	   	 		var money = $('#money').val();
			 	   	 		var loanId = $('#loanId').val();
			 	   	 		var userId=$('#userId').val();
				 	   	 window.location.href="/pay/chinapnr/start-InitiativeTender.action?payReqFromApp.loanId="+loanId+"&payReqFromApp.transAmt="+money+"&payReqFromApp.userId="+userId;
				 	   	 /* $.post(url,{	
			 	   	 			"vlidate" : verifycode
			 	   	 			},function(data, varStatus){
			 	   	 				if(data == ""|| null == data){
			 	   	 					$("#loadingImage").hide();
			 	   	 					$("#verifycode").val("");
			 	   	 					$('#invest-modal').modal('hide');
			 	   	 				    $('#invest_btn').removeClass('disabled');
			 	   	 				    
			 	   	 				var openUrl = "/findLoanList";
			 			 	   		 $('#alert-modal-title').html("投资提醒");
			 			 	   	     $('#alert-modal-body').html("投资处理完毕后,请您<a style='color:red;text-decoration: underline;' href='"+openUrl+"'>关闭</a>");
			 			 	   	     $('#alert-modal').modal('show');
			 			 	   	     
			 	   	 				    window.location.href="/pay/chinapnr/start-InitiativeTender.action?payReqFromApp.loanId="+loanId+"&payReqFromApp.transAmt="+investAmount+"&payReqFromApp.userId="+userId;
			 	   	 				  // var  qulUrl = "/pay/chinapnr/start-InitiativeTender.action";
			 	   	 				  //  window.open(qulUrl+"?payReqFromApp.loanId="+loanId+"&payReqFromApp.transAmt="+investAmount+"&payReqFromApp.userId="+userId); 
			 	   	 				
			 	   	 				//var tempwindow=window.open('_blank');//打开一个窗口，
			 	   	 				//tempwindow.location='http://www.baidu.com';
			 	   	 				
			 	   	 				}else{
			 	   	 					coderefresh();
			 	   	 					$("#loadingImage").hide();
			 	   	 					$("#verifycode").val("");
			 	   	 					$("#message").html(data);
			 	   	 					$('#invest_btn').removeClass('disabled');
			 	   	 				}
			 	   	 			},"json"); */
			 	   		 return ;
		   			 }
			   	 	 }
		}
			 	   	  //10.16 cf 打开条款
				   	   function openServiceItems(url){
				   	    window.open(url, 'regconfirm', 'height=584,width=718,toolbar=no,menubar=no,scrollbars=no,resizable=false,location=no,status=no');
				   	    return true;
				   	   }
				   	function checkRoleInvest(){
				   		var roleinevest = $.trim('${user.roles}');
				   	 	if(roleinevest!= '' && roleinevest!="1"){
				   	 		$('#alert-modal-body_a').html("借款人不能投资！") ;
				   	 		$('#alert-modal_a').modal('show');
		//		   	 	$("#investButton").attr("href","javascript:void(0)");
				   		}
				   	}
	</script>
<div class="modal fade" id="invest-modal" tabindex="-1" role="dialog" aria-labelledby="invest-modal-title" aria-hidden="true" >
	<div class="modal-dialog invest-modal" style="width: 703px;">
		<div class="modal-content invest-modal-content modal-body_new">
  		<div class="modal-header invest-modal-header modal-header-blue">
    	<h4 class="modal-title invest-modal-title" id="invest-modal-title">确认投标金额</h4></div>
   	<div class="modal-body invest-modal-body " style="background-color: white;">
<!-- 
	<div class="affirm_message">
   
  	<div class="mes_pic affirm_pic pull-left">
  	img src="${ctx}realLoanHead?picId=${loan.loanPortraitId}&type=2&imgtype=1" width="124" height="91" /><br />
      <a id=borrowerNickname></a>
       <input type="hidden" name="loanId" value="${data.loanId}" />
        <input type="hidden" name="userId" id="userId" value="${user.userId}" />
     </div>
     
     <div class="mes_m affirm_m pull-right">
       <span class="mes_tit"><img src="${ctx }images/credit_a.gif" width="21" height="21" title="信用等级为A" /><a class=" f20 fw" style="font-size: 20px;" id="title" ></a>　${loan.title}</span>
       <table border="0" cellspacing="0" cellpadding="0" class="mes_tab affirm_mastab">
            <tr>
              <td>借款金额</td>
              <td>年化利率</td>
              <td>期限</td>
            </tr>
            <tr>
              <td><span id="amount" class="black fw"></span>元</td>
              <td><span id="annualInterestRate" class="black fw"></span>%</td>
              <td><span id="termCount" class="black fw"></span>个月</td>
            </tr>
        </table>
      </div>
    
   </div>
  <table border="0" cellspacing="0" cellpadding="0" class="affirm_tab">
    <tr>
      <td class="first_td">还款方式：</td>
      <td><span id="productId2" class="fw black"></span></td>
      <td>可赚利息：</td>
      <td>￥<span id="earning" class="fw black"></span></td>
    </tr>
    <tr>
      <td class="first_td">保障范围：</td>
      <td><span class="fw black">保障本息</span></td>
      <td>投标进度：</td>
      <td>
	<div class="progress progress-striped active" style="width: 200px;margin-top: 15px;">
	  <div class="progress-bar" id="loanProgress" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" >
	  </div>
	</div>	
</td>
    </tr>
    <tr>
      <td class="first_td">剩余投标金额：</td>
      <td>￥<span id="remaining" class="fw black"></span></td>
      <td>&nbsp;</td>
      <td>&nbsp;</td>
    </tr> 
  </table> 
   -->
   <div>

<%-- 	     	<img src="${base}/images/credit_a.gif" style="margin-left: 30px; margin-top: 5px;" width="21" height="21" title="信用等级为A"  class="pull-left"/> --%>
	<div style="margin-left: 40px;"><span><a class=" f20 fw" style="font-size: 20px;font-weight: bold;" id="title" ></a></span>
	</div>
 <div style="margin-left: 50px; width: 90%;margin-top: 10px;">
 	<table style="width: 100%;height: 100px;font-size: 15px;color:#444444;"> 
 	<tr class="l_i_tr">
		<td>借款金额:<span id="amount"  class="black fw">${param.amount} </span>元</td>
		<td>借款期限:<span id="termCount" class="black fw">${param.termCount}</span><span id="termUnit" class="black fw"></span></td>
		<td>还款方式:<span id="productId" class="fw black">${param.prductText}
		<%-- <% String str = new String(request.getParameter("prductText").getBytes("iso8859-1"),"utf-8");out.print(str);%> --%>
		<%-- <%=request.getParameter("prductText")%> --%></span></td>
 	</tr>
 	
 	<tr  class="l_i_tr">
	 	<td>预计年化收益:<span id="annualInterestRate"  class="black fw">${param.annualInterestRate}</span>%</td>
	 	<td>保障范围:<span class="fw black">保障本息</span></td>
	 	<td>剩余金额:￥<span id="remaining" class="fw black">${param.remaining}</span></td> 
 	</tr>
 	<%-- <tr class="l_i_tr">
 	<td colspan="3" >
 		<span>投标进度条：</span>
 		<div class="progress progress-striped " style="float:right;width: 200px; height:20px;margin-top: 5px;margin-right: 300px;margin-bottom: 1px;">
			<div class="progress-bar" id="loanProgress" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" >
			</div>
		</div>
 		<span id="proGress" style="top:31%;left:250px;position: absolute;">${biddingAmount/amount*100}%</span> 
			<span class="fw black" style="top:31%;left:250px;position: absolute;" id="loanProgressbaifenbi" ></span>
			<span style="top:31%;left:290px;position: absolute;"></span>
	</td>
 	</tr> --%>
 	<tr class="l_i_tr">
 		<td colspan="3">可赚利息:<span id="earning" class="fw black">${param.moneyMath}</span>元</td>
 	</tr>
 	</table>
 </div>
 </div>
<img id="loadingImage" style="display:none; position:absolute;left:50%;z-index:10;" src="${base}/themes/${theme4app}/img/loading.gif" />	
<div class=" affirm_input_new" style="background-color:#EFEFEF ;border: 0px;" >
<table border="0" cellspacing="0" cellpadding="0" class="affirm_input_tab" style="font-weight:normal;margin: 15px 0 0 30px;" >
<tr style="height:20px;"></tr>
    <tr> 
		<td width="80" class="black" style="color:black;" >
			<c:choose>
	       <%-- <c:when test="${not empty loan.minInvestUnit && loan.minInvestUnit>0 }">
	                              认购份数:
	 			</c:when> --%>
				<c:otherwise>
	       	 投标金额:
				</c:otherwise>
    		</c:choose>	         
		</td>
      <td><span id="investAmount" style="font-family:'微软雅黑';color:#444034"><%=request.getParameter("money")%></span>${(not empty loan.minInvestUnit && loan.minInvestUnit>0)?"份":"元" }</td>
    </tr>
    <tr>
      	<td width="80" class="black" style=" color:black;">　验证码：</td>
		<td><input type="text" name="verifycode" style="font-family:'微软雅黑';color:#444034" id="verifycode" class="yzm_input" onfocus="$('#message').html('')"/>
      		<img align="absmiddle" alt="验证码" title="验证码" id="imgverCode" src="<%=request.getContextPath() %>/registVerfyCode" onclick="coderefresh() "/>
		</td>
 		<td><span style="float: left; color: red;" id="message"></span></td>
	</tr>
    <tr>
      <td width="80">&nbsp;</td>
		<td>
      	<span style="float: left;"><input type="checkbox" name="agreecheckbox" id="agreecheckbox" value="1" style="width:14px; height:14px; border:none;" checked/>我同意	&nbsp;
          <a href="Javascript:void(0)" onclick="openServiceItems('${base}/service')" title="您是否同意服务条款"><span class="blue"><u>使用条款</u></span></a>&nbsp;和
          <a href="Javascript:void(0)" onclick="openServiceItems('${base}/privacy')" title="您是否同意服务条款" ><span class="blue"><u>隐私条款</u></span></a>
		</span>
		</td>
	</tr>
	</table>
	</div>
   	<div class=" invest-modal-footer invest-modal-footer_new " align="center">
   		<button type="button" id="invest_btn" style="color:white;width: 100px;height: 30px;" onclick="buttonls()";>确认</button>
 		<button type="button" onClick ="javascript:location.href ='javascript:window.history.back(-1);'" style="color:white;width: 100px;height: 30px;" data-dismiss="modal">取消</button>
	</div>
    </div>
  	</div>
	</div>
</div>  
		<%-- <% String username=(String)request.getParameter("amount");int username1= Integer.parseInt(username);%>
		<% String username2=(String)request.getParameter("biddingAmount");int username3= Integer.parseInt(username2);%> --%>
		<%-- <% String username=(String)request.getParameter("amount");out.print(username);%> --%>
		<input type="hidden" name="userId" id="userId" value="${userVo.userId}" />
		<input type="hidden" id="loanId" name="loanId" value="<%=request.getParameter("loanId")%>"/>
		<input type="hidden" id="money" name="money" value="<%=request.getParameter("money")%>"/>
		<input type="hidden" id="amount" name="amount" value="<%=request.getParameter("amount")%>"/>
		<input type="hidden" id="termCount" name="termCount" value="<%=request.getParameter("termCount")%>"/>
		<input type="hidden" id="productId" name="productId" value="<%=request.getParameter("productId")%>"/>
		<input type="hidden" id="remaining" name="remaining" value="<%=request.getParameter("remaining")%>"/>
		<input type="hidden" id="biddingAmount" name="biddingAmount" value="<%=request.getParameter("biddingAmount")%>"/>
		<input type="hidden" id="annualInterestRate" name="annualInterestRate" value="<%=request.getParameter("annualInterestRate")%>"/>
<!-- <div class="modal fade" id="alert-modal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
  <div class="modal-dialog  alert-modal">
    <div class="modal-content alert-modal-content">
      <div class="modal-header alert-modal-header"> 
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">关闭</span></button>
        <h4 class="modal-title alert-modal-title" id="alert-modal-title">提示</h4>
      </div>
      <div id="alert-modal-body" class="modal-body invest-modal-body">
      
      </div>
    </div>
  </div>
</div> -->

<div class="modal fade" id="success-modal" tabindex="-1" role="dialog" aria-labelledby="success-modal" aria-hidden="true">
  <div class="modal-dialog success-modal" style="">
	<div class="modal-content success-modal-content investsucc_new" >
 <!--  <div class="modal-header success-modal-header modal-header-blue investsucc_new_top">
    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
    <span class="success-modal-title" style="color: white;font-size: 16px;font-weight:bold;margin-bottom:15px;" id="myModalLabel">投标成功</span>
</div> -->
<%-- <div class="modal-body" style="height: 243px;" align="center">
 <div class="" style="margin-top: 40px;font-size: 16px;color: black;height: 200px;" >
<img alt="" src="${base}/themes/${theme4app}/img/success_new.png"><br/>
恭喜您，投标成功。<br/>
页面将在<span id="closetime">5</span>秒内后跳转，如果没有跳转，您可以<a><span style="cursor: pointer;color: #008CEE" onclick="successJump();"><u>点击这里</u></span></a>
		</div>
      </div>
      <div class="clear"></div>
      <div class="success-modal-footer">
      </div> --%>
    </div>
  </div>
</div>
</c:set>


<div class="clear"></div>
</c:set>


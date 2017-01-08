<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<script type="text/javascript" src="${base}/js/idCardNoValidate.js"></script>
<script>
/* jQuery(document).ready(function($) {
$('.login_f_btn').click(function(){
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	});

	$('.index_login').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})

}) */
     $(function() {
 		var d=new Date()
 		$.getJSON('${ctx}payAccountReady.action?version='+d.getTime(), function(data) {
 			if (data) {
 				$('#payAccountReady').show();
 			} else {
 				$('#payAccountUnready').show();
 			}
 		});
//  		var isIDCard2=/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/; 
 		var isIDCard2=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
 		
 		var regx=/^[\u4e00-\u9fa5]+$/;
 		var thirdpartCheck = function(pRealName, pIdentNo){
 			if(pRealName == "" || pRealName.length == 0){
 				showDialogHTML("姓名不能为空");
 	   			$("#pRealName").focus();
 	   			return false;
 			}
 		   if(pRealName.length < 2 || pRealName.length > 8){
 			  showDialogHTML("请输入真实姓名");
 	   			$("#pRealName").focus();
 	   			return false;
 			}
 			if(!regx.test(pRealName)){
 				showDialogHTML("只支持中文名称");
 				$("#pRealName").focus();
 				return false;
 			}
 			if(pIdentNo == "" || pIdentNo.length == 0){
 				showDialogHTML("身份证号不能为空");
 				$("#pIdentNo").focus();
 	   			return false;
 			}
 			if(!isIDCard2.test(pIdentNo)){
 				showDialogHTML("身份证号格式不正确");
 				$("#pIdentNo").focus();
 	   			return false;
 			}
 			return true ;
 		};
 		$('#startUserRegisterSub').click(function(){
 			if(thirdpartCheck($.trim($('#pRealName').val()),$.trim($('#pIdentNo').val()))){
 				$("#startUserRegister").submit();
 				//showDialogHTML("开户提醒:请开户完毕后关闭此窗口");
 			}
 		});
 		
 	});
</script>
<body>

	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInstall" title="返回" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>资金托管开户
		</h2>
	</div>

	<div class="login_f">
		<form id="startUserRegister"  action="<%=request.getContextPath() %>/pay/chinapnr/start-UserRegister.action" method="post">
			<dl style="width: 30%">
				<dd>真实姓名：</dd>
				<dd>身份证号：</dd>
			</dl>
			<dl style="width: 70%">
				<dd class="bor">
					<input type="text" id="pRealName"  name="payReqFromApp.realName" placeholder="请输入正确的姓名"  value="${user.realName}"/>
					<input type="hidden" id="roles" name="payReqFromApp.roles" class="no_auth_input" value="${user.roles}" />
				</dd>
				<dd>
					<input type="hidden" value="${user.userId}" class="no_auth_input"
								readonly="readonly"  name="payReqFromApp.userId" />
					<input type="text" size="25" id="pIdentNo"  name="payReqFromApp.idNo"
						placeholder="请填写16到18位身份证号码" value="${user.idCardNo}" />
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
	<div class="login_f_btn" id="startUserRegisterSub">注册</div>

	<div class="theme-popover-mask"></div>
	

	<%@ include file="./comm/bott.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<body>

	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>账户设置
		</h2>
	</div>

	<div class="set_user">
		<img src="<%=request.getContextPath() %>/mobile/images/face.jpg" />
		<div style="float: left; line-height: 24px; margin-top: 3px;color:#FFFFFF">
			用户昵称：${fn:substring(loginUser.nickName,0,3)}****${fn:substring(loginUser.nickName,7,-1)}<br />
			实名认证：${fn:substring(loginUser.realName,0,1)}**<br />
			身份认证：${fn:substring(loginUser.idCardNo,0,4)}******${fn:substring(loginUser.idCardNo,12,-1)}
		</div>
		<div class="clear"></div>
	</div>
	<div
		style="background: #f87a9f; color:; text-align: center; color: white; line-height: 30px; font-size: 14px">
		<c:if test="${empty  loginUser.userCode}">
		<img src="<%=request.getContextPath() %>/mobile/images/icon_kill.png" style="margin-right: 7px" />
				<span class="no_authtit f14 fw black">未通过实名认证</span>
				<%-- <span class="no_authtit_r f14 fw red f_w100"><a
						href="<%=request.getContextPath() %>/toRealNameMobile">点击认证</a></span> --%>
				</c:if>
			<c:if test="${loginUser.userCode!=null&&loginUser.userCode!=''}">
			<img src="<%=request.getContextPath() %>/mobile/images/icon_mark.jpg" style="margin-right: 7px" />
				<span class="no_authtit f14 fw black">已通过实名认证</span>
				</c:if>
	</div>

	<div class="login_f login_m set_m">
		<dl>
			<a href="<%=request.getContextPath() %>/retPassword"><dd>
					<span class="fr"><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.jpg" /></span><img
						src="<%=request.getContextPath() %>/mobile/images/icon_re_pwd.jpg" />修改登录密码
				</dd></a>
				<c:if test="${empty  loginUser.userCode}">
				</c:if>
			<c:if test="${loginUser.userCode!=null&&loginUser.userCode!=''}">
			<a href="${base}/pay/chinapnr/start-ResetPassword.action?payReqFromApp.userId=${user.userId}">
			   <dd><span class="fr"><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.jpg" /></span>
			   <img src="<%=request.getContextPath() %>/mobile/images/icon_re_key.jpg" />修改支付密码
			   </dd>
			</a>
			</c:if>
			<dd id="cash">
				<span class="fr"><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.jpg" /></span><img
					src="<%=request.getContextPath() %>/mobile/images/icon_cash.jpg" />提现绑卡
			</dd>

			<dd id="account">
				<span class="fr"><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.jpg" /></span><img
					src="<%=request.getContextPath() %>/mobile/images/icon_account.jpg" />资金托管开户
			</dd>

		</dl>

		<div class="clear"></div>


	</div>
	<p class="blank10"></p>
	<a href="<%=request.getContextPath() %>/logout" type="button"><div  class="login_f_btn">退出</div></a>

	<div class="theme-popover" id="tip_cash">
		<img class="bond_close" src="<%=request.getContextPath() %>/mobile/images/d_close.png" title="关闭" />
		<div class="theme-poptit">绑卡提醒</div>
		 <c:if test="${cardNo==null}">
 			<div class="pull-right">
      		    <p >未绑定银行卡</p>
      		    <c:if test="${user.realName=='' ||user.realName==null }">
      		    <p>您还没有通过注册托管支付的账户，请您去注册支付账户！</p>
      			<a href="<%=request.getContextPath() %>/toRealNameMobile"><div class="index_login">立即认证</div></a>
      		    </c:if>
      		    <c:if test="${user.realName!=null&&user.realName!=''}">
      		    <a href="<%=request.getContextPath() %>/pay/chinapnr/start-BindCard.action"><div class="index_login">立即绑卡</div></a>
      		    </c:if>
      		 </div>    
          </c:if>
	     <c:if test="${cardNo!=null}">
	          <p>恭喜，您已经绑卡！</p>
	          <a href="<%=request.getContextPath() %>/accountInstall"><div class="index_login">确定</div></a>
	     </c:if>
		

	</div>

	<div class="theme-popover" id="tip_account">
		<img class="bond_close" src="<%=request.getContextPath() %>/mobile/images/d_close.png" title="关闭" />

		<div class="theme-poptit">开户提醒</div>
		
		 	<c:if test="${empty  loginUser.userCode}">
				<p>您还没有注册托管支付的账户，请您去注册支付账户</p>
				<a href="<%=request.getContextPath() %>/toRealNameMobile"><div class="index_login">立即认证</div></a>					
			</c:if>
		    <c:if test="${loginUser.userCode!=null}">
		       <p>恭喜，您已经通过认证！</p>
		       <a href="<%=request.getContextPath() %>/accountInstall"><div class="index_login">确定</div></a>
		   </c:if>
	</div>

	<script>
	 jQuery(document).ready(function($) {
		$('#cash').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('#tip_cash').slideDown(200);
		});
		
		$('#account').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('#tip_account').slideDown(200);
		});
		
		$('.theme-popover .bond_close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('#tip_cash').slideUp(200);
			$('#tip_account').slideUp(200);
		})
	}) 
</script>

<%@ include file="/mobile/comm/bott.jsp"%>
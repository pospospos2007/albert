<%@page import="com.itextpdf.text.log.SysoLogger"%>
<%@page import="com.itextpdf.text.log.SysoCounter"%>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>
	<body>
	<script type="text/javascript">
	
	$(function(){
		$(".mf_qqimg").click(function(){ 
			window.location.href="<%=request.getContextPath() %>/ul/qqlogin";
		});
	   $('#userName').focus();
	   $("input").focus(function(){
		   $('#errorStr').text("").hide();
		});
		$('#loginSubmit').click(formSubmit);
	});
	
	function formSubmit() {
	  var userName = $('#userName').val();
	  var password = $('#password').val();
	 
	  if ($.trim(userName).length==0){
		  showDialogHTML("用户名不能为空");
	  } else if ($.trim(password).length==0){
		  showDialogHTML("密码不能为空");
	  }
	  
	  var arr = new Array();
	  arr = userName.split(" ");
	  if (arr.length != 1) {
		  showDialogHTML("账号中不能有空格!");
	    //$('#errorStr').text("账号中不能有空格!").show();
	    return false;
	  }
	  if (validation(userName, '账号') && validation(password, '密码')) {
	    var autosession = $("input[name='autosession']").is(':checked');
	    $.ajax({
	      type: "POST",
	      url:"<%=request.getContextPath() %>/login.action",
	      data: "userName=" + userName + "&password=" + password + "&autosession=" + autosession,
	      dataType: "text",
	      success: function(data) {
	        var res = data.split("#");
	        var number = 10 - res[1];
	        switch (res[0]) {
	          case "-1":
	        	  showDialogHTML('邮箱不存在!');
	            break;
	          case "-2":
	        	  showDialogHTML('账号不存在!');
	            break;
	          case "-3":
	        	  showDialogHTML("账号不存在!");
	            //$('#errorStr').text('账号不存在!').show();
	            break;
	          case "-4":
	            res[1] == 0 ? showDialogHTML('密码输入错误!')  :  showDialogHTML('密码输入错误!   如果密码输错10次账号将被锁定!您还有' + number + '次机会'); 
	            break;
	          case "-5":
	        	  showDialogHTML('请重新登录!');
	            break;
	          case "-66":
	        	  showDialogHTML('手机端暂时不支持借款人登录!');
	            break;
	          case "-99":
	        	  showDialogHTML('账号已被锁定,请联系客服!');
	            break;
	          default:
	            if (res[2] != '') {
	              window.location.href = res[2];
	            } else {
	              window.location.href = address + 'accountInfo.action';
	            }
	            break;
	        }
	      }
	    });
	  }
	}
	function validation(str,msg){
		if($.trim(str).length==0){
			$('#errorStr').text(msg+"不能为空!").show();
			return false;
		}
		return true;
	}
	</script>
	<input type="hidden" id="loanId" name="loanId" value="${loanId}"/>
	<input type="hidden" id="loanId" name="selectPage" value="${selectPage}"/>
	<div class="login">
	
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/user_n.jsp" title="返回" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a><a href="<%=request.getContextPath() %>/goRegister" class="fr">注册</a>登录
		</h2>
	</div>

	<div class="login_f">
		<form action="" method="post">
			<dl style="width: 25%">
				<dd>账 &nbsp;号</dd>
				<dd>密 &nbsp;码</dd>
			</dl>
			<dl style="width: 75%">
				<dd class="bor">
					<input type="text" id="userName" placeholder="请输入手机号码" />
				</dd>
				<dd>
					<input type="password" id="password" placeholder="请填写6到16位密码" />
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>

	  <div type="button"  id="loginSubmit"  class="login_f_btn">登录</div>

	<div class="reg_bot tc">
		<a style="color: ##2c5fae" href="<%=request.getContextPath() %>/toFindLoginPasswordPage">忘记密码？</a>
	</div>

	<%@ include file="./comm/bott.jsp"%>
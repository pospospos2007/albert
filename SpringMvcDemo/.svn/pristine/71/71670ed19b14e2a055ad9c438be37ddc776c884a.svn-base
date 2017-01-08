<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>


<body>

	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInstall" title="设置" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>修改登录密码
		</h2>
	</div>

	<div class="login_f reset_f">
		<form action="" method="post">
			<dl style="width: 30%">
				<dd>原登录密码</dd>
				<dd>输入新密码</dd>
				<dd>确认新密码</dd>
				<dd>输入验证码</dd>

			</dl>
			<dl style="width: 70%">
				<dd class="bor">
					<input maxlength="30" type="password" id="password" name="pwd_old" onblur="checkPass()" placeholder="请输入原登录密码" />
				</dd>
				<dd class="bor">
					<input maxlength="30" type="password" name="pwd_new" id="password_regiest" onblur="checkNewPass()" placeholder="请输入新密码" />
				</dd>
				<dd class="bor">
					<input  maxlength="30" type="password" name="pwd_again" id="pdwuri" onblur="reCheckNewPass()" placeholder="两次输入密码需一致" />
				</dd>
            <dd><input type="text" name="verifycode_password" style="font-family:'微软雅黑';width: 88px;" id="verifycode_password" class="no_auth_input"/>
            <img align="absmiddle" alt="验证码" title="验证码" id="imgverCode_password" src="<%=request.getContextPath() %>/registVerfyCode" onclick="coderefresh_password() "/>
            </dd>
			</dl>
			<div class="clear"></div>
		</form>
	</div>
	<a ><div onclick="updateLoginPass()" class="login_f_btn">修改</div></a>

	
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
	  //只能数字和字母
      function onlyLetterNumber(s)
      {
      	  var reg =/^[A-Za-z0-9]+$/;
       	  if (!reg.exec(s)) {
      		return false;
      	  }
          return true
      }
      //原密码
  	function checkPass(){
  		var password = $("#password").val();
  		if(password == ""|| password == null){
  			showDialogHTML("请输入原密码");
  			return false;
  		}else{
  			return true;
  		}
  	}
    //新密码
  	function checkNewPass(){
  		var newpassword = $("#password_regiest").val();
  		if(newpassword == ""|| newpassword == null){
  			showDialogHTML("请输入新密码");
  			return false;
  		}else if(newpassword.length<6||newpassword.length>16){
  			showDialogHTML("密码长度应在6-16位之间");
  			return false;
  		}else if(!onlyLetterNumber(newpassword)){
  			showDialogHTML("密码只能是数字和字母");
  			return false;
  		}
  		else{
  			return true;
  		}
  	}
  	//确认新密码
  	function reCheckNewPass(){
  		var renewpassword = $("#pdwuri").val();
  		var newpassword = $("#password_regiest").val();
  		if(renewpassword == ""|| renewpassword == null){
  			showDialogHTML("请再次输入新密码");
  			return false;
  		}else if(renewpassword != newpassword){
  			showDialogHTML("两次密码不同");
  			return false;
  		}else{
  			
  			return true;
  		}
  	}
  	
  	function updateLoginPass(){
  		if(checkPass()&&checkNewPass()&&reCheckNewPass()&&checkVerCode_password()){
  		var url = "${base}/updatePassword";
  		var oldPassword = $("#password").val();
  		var newPassword = $("#password_regiest").val();
  		var verifycode_password = $("#verifycode_password").val();
  		$.post(url,{
  			"verifycode_password":verifycode_password,
  			"oldPassword" : oldPassword,
  			"newPassword" : newPassword
  			},function(data, varStatus){
  				if(data=="-1"){
  					showDialogHTML("原密码错误");
  					coderefresh_password();
  				}else if(data=="0"){
  					showDialogHTML("更新失败，请稍后再试");
  					coderefresh_password();
  				}else if(data=="1"){
  					showDialogHTML("密码修改成功，请重新登陆");
  					//$("#loginPassMessage").html("修改成功，请重新登陆");
  					$("#password").val('');
  					$("#password_regiest").val('');
  					$("#pdwuri").val('');
  					$("#verifycode_password").val('');
  					window.setTimeout(function (){
  						;
  					},5000);
  					logout();
  				}else{
  					showDialogHTML(data);
  					coderefresh_password();
  					$("#verifycode_password").val('');
  				}
  			},"text");
  		}
  	}
  	
  	function logout(){
  		 window.location.href='<%=request.getContextPath() %>/logout';
  		window.location.href='<%=request.getContextPath() %>/toLoginPage';
  		
  	}
  	
  	function coderefresh_password() {  // 刷新验证码
  		var id = document.getElementById("imgverCode_password");
  		var d = new Date();
  		id.src = '<%=request.getContextPath() %>/registVerfyCode?a=' + d.getTime();
  	}
  		
  	function checkVerCode_password(){
  		var verifycode_password = $("#verifycode_password").val();
  		if(verifycode_password==null||verifycode_password==""){
  			showDialogHTML("请输入验证码");
  			return false;
  		}else{
  			return true;
  		}
  	}
  	
</script>
	<%@ include file="./comm/bott.jsp"%>
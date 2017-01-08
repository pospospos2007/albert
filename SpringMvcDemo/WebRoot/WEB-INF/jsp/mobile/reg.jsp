<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<script>
	jQuery(document).ready(function($) {
		$('.login_f_btn').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		
		$('.index_login').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})
		$("span#count_yz").css('display','none');
	
	})

	//手机号码校验	
	function checkMobile(){
		var regst =/^1[3|4|5|7|8][0-9]\d{8}$/;
		var telnum = $("#mobile").val();
		if($("#mobile").val()==""||$("#mobile").val()==null){
			showDialogHTML("请先输入手机号");
			$("#mobile").focus();
			return false;
		}else if(!regst.test(telnum)){
			showDialogHTML("请输入正确的手机号");
			$("#mobile").focus();
			return false;
		} else{
			return true;
		}
   }
	
   	// 手机验证码	
  	function getVerifycodeMobile(){
  	     $("#tip_vali").html("");
 		 if(!checkMobile()){
 		 	return false;
 		 }
 		 $("#getCodeButton").hide();
 		 $('#getCodeButton').attr('disabled',true);
 		 $.post("app/sendVerifyCodeWithNoForward",
				{
					"mobile":$("#mobile").val()
				},
				function(data){
					if(data==1){
						$("#count_yz").html(60);
						$("#count_yz").show();
						$('#getCodeButton').attr('disabled',false);
						showDialogHTML("验证码已发送");
						//$('#verifycode').attr('disabled',true);
						$('#verifycode').removeAttr("disabled");
						jump(60);
					}else{
						$('#getCodeButton').attr('disabled',false);
						showDialogHTML(data == 2?"该手机号已被使用":"发送失败"+data);
						$("#getCodeButton").show();
						$("#mobile").focus();
					}
				});
 			
 	}
 	
 	function jump(count) {
        window.setTimeout(function(){
            count--;
            if(count > 0) {
                $("#count_yz").html(count);
                jump(count);
            } else {
               $("#getCodeButton").show();
               $("#count_yz").hide();
               $("#verifycode").attr("disabled","disabled");
            }
        }, 1000);
   }
   
      	
    //校验手机验证码是否正确
    function mobileVerifycode(){
      	if($("#mobile").val() == ""){
      		showDialogHTML("请输入手机号");	
				return;
			}
			 if($("#verifycode").val() == ""){
				showDialogHTML("请输入验证码");	
				return;
			}
			$.post("app/boundPhoneWithNoForward",{
				"mobilePhone":$("#mobile").val(),
				"verifycode":$("#verifycode").val()
				},function(data){
			if(data==2){
				/* $("#tip_vali").html("✔");
				$("#tip_vali").css({color:'green'}); */
				$("#count_yz").hide();
				$("#mobile").attr('disabled',true);
				$("#verifycode").attr('disabled',true);
			}else if(data==1){
				showDialogHTML("手机验证码错误");	
				$("#verifycode").val("");
			}else{
				showDialogHTML("手机验证失败");	
				$("#verifycode").val("");
			}
		});
    }	
      
     //推荐人号码校验
	function referrerCoder(){
		var regst =/^1[3|4|5|7|8][0-9]\d{8}$/;
		var telnum = $("#referrerCode").val();
			if($("#referrerCode").val()==""||$("#referrerCode").val()==null){
				return true;
			}else if(!regst.test(telnum)){
				showDialogHTML("请输入正确的推荐码");	
				return false;
			} else{
				return true;
			}
	}
  
    //只能数字和字母
    function onlyLetterNumber(s)
    {
   	    var reg =/^[A-Za-z0-9]+$/;
    	if (!reg.exec(s)) {
   			return false;
    	}
        return true
    }
    //密码输入框获取焦点时，判断验证码是否输入
    function clearChekPwdfor(){
    	 if($("#verifycode").val() == ""){
				showDialogHTML("请输入验证码");	
			$("#verifycode").focus(); 
			}
    }  
     //校验密码是否正确
 	function chekPwdfor() {
	    var re = false;
	    if($("#verifycode").val() != ""){
		    var pwd = document.getElementById("password");
		    if (pwd.value == "" || pwd == null ) {
		    	showDialogHTML("请输入密码");	
		        return re;
		    }else if(pwd.value.length<6||pwd.value.length>16){
		    	showDialogHTML("密码长度为6-16位");
		        return re;
		    }else if(!onlyLetterNumber(pwd.value)){
		    	showDialogHTML("只能输入数字和字母");
		        return re;
		    }else {
		      	pwStrength(pwd.value);
		        return true;
		    }
	    }
	 }
    
    //二次验证密码
    function checkPWDui() {  //密码确认
        var re = false;
        if(!chekPwdfor()){
            return re;
        }
        var PWDui = document.getElementById("pdwuri");
        var PWD = document.getElementById("password");
        if (PWDui.value == "" || PWDui == null) {
        	showDialogHTML("请再次输入用户密码");
            return re;
        } else if (PWD.value != PWDui.value) {
        	showDialogHTML("两次密码不一致");
            return re;
        } else if (PWD.value == PWDui.value) {
            re = true;
            return re;
        } 
    }
    
	//注册
    function register() {
       if(checkMobile()){
    	   	var  verifycode=$("#verifycode").val();
	     	 if(verifycode==""){
		    	 showDialogHTML("请输入验证码！");
		         return false; 
	        }  
    		 if(referrerCoder()&&chekPwdfor()&&checkPWDui()){    
				$("#mobile").attr('disabled',false);
				$("#verifycode").attr('disabled',false);
		        $("#registerUser").submit();
	       } else{
	           return false;       
	       }
       }
/*        if(checkMobile()&&referrerCoder()&&chekPwdfor()&&checkPWDui()){    
	       	var  verifycode=$("#verifycode").val();
	     	if(verifycode==""){
		    	 showDialogHTML("请输入验证码！");
		         return false; 
	        }
			$("#mobile").attr('disabled',false);
			$("#verifycode").attr('disabled',false);
	        $("#registerUser").submit();
	       } else{
	           return false;       
	       } */
     }
      
	//判断输入密码的类型  
	function CharMode(iN){  
	if (iN>=48 && iN <=57) //数字  
	return 1;  
	if (iN>=65 && iN <=90) //大写  
	return 2;  
	if (iN>=97 && iN <=122) //小写  
	return 4;  
	else  
	return 8;   
	}  
	//bitTotal函数  
	//计算密码模式  
	function bitTotal(num){  
		modes=0;  
		for (var i=0;i<4;i++){  
			if (num & 1) modes++;  
			num>>>=1;  
		}  
		return modes;  
	}  
	//返回强度级别  
	function checkStrong(sPW){  
		if (sPW.length<=5){
			return 0; //密码太短  
		}  
		var Modes=0;  
		for (var i=0;i<sPW.length;i++){  
			//密码模式  
			Modes|=CharMode(sPW.charCodeAt(i));  
		}  
		return bitTotal(Modes);  
	}  
	
	//显示颜色  
	function pwStrength(pwd){  
		S_level=checkStrong(pwd); 
		switch(S_level) {  
			case 0:  
			$("#messagePwd").html(""); 
			case 1:  
			$("#messagePwd").html("弱"); 
			$("#messagePwd").css({color:'red'});
			$("#messagePwd").show(); 
			break;  
			case 2:  
			$("#messagePwd").html("中"); 
			$("#messagePwd").css({color:'DarkGoldenrod'});
			$("#messagePwd").show(); 
			break;  
			default:  
			$("#messagePwd").html("强"); 
			$("#messagePwd").css({color:'green'});
			$("#messagePwd").show(); 
		}  
	}  
</script>
<div class="login">
	<h2>
		<a href="<%=request.getContextPath() %>/mobile/user_n.jsp" title="返回" class="fl">
		<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>注册
	</h2>
</div>

<div class="login_f reset_f">
	<form action="register" id="registerUser" method="post">
		<dl style="width: 25%">
			<dd>*手机号码</dd>
			<dd>*验证码</dd>
			<dd>推荐人</dd>
			<dd>*密码</dd>
			<dd>*确认密码</dd>
		</dl>
		<dl class="reg_dl_po">
			<dd class="bor">
				<input type="text" name="mobile" id="mobile" onblur="checkMobile()" onfocus="hideMobile()"maxlength='11' placeholder="请输入手机号" />
				<input type="hidden" name="roles" id="userRoles" value="1"/>
				<span id="mobilemessage" class="tip"></span>
			</dd>
			<dd class="bor">
				<input type="text" name="verifycode" id="verifycode" disabled="disabled" maxlength='6' onblur="mobileVerifycode()" placeholder="请输入验证码" />
				<span id="tip_vali" class="tip" style="color: green; right: 30%; font-size: 16px"></span>
				<span class="get_vali" id="getCodeButton"  onclick="getVerifycodeMobile()">获取</span>
				<span class="get_vali" id="count_yz" style="'display','none'"; ></span>
			</dd>
			<dd class="bor">
				<input type="text" onblur="referrerCoder()" name="referrerCode" id="referrerCode" onfocus="hideVide()" placeholder="推荐人手机号"  value='${param.referrerCode}'/><span
					id="mobilemessag" class="tip"></span>
			</dd>
			<dd class="bor">
				<input type="password" maxlength='16' name="passwd" id="password" onblur="chekPwdfor()" onfocus="clearChekPwdfor()"
					placeholder="请输入6到16位新密码" /><span id="messagePwd" class="tip"></span>
			</dd>
			<dd>
				<input type="password" name="pdwuri" id="pdwuri" onblur="checkPWDui()" onfocus="clearCheckPWDui()" maxlength='16'
					placeholder="请再次输入密码" /><span id="messagepwduri" class="tip"></span>
			</dd>
		</dl>
		<div class="clear"></div>
	</form>
</div>
<div class="login_f_btn" onclick="register()">同意协议并注册</div>

<div class="reg_bot tc">
	<span style="color: #9c9d9f; float: left">
		<img src="<%=request.getContextPath() %>/mobile/images/reg_log.jpg" />已有账号，
		<a href="<%=request.getContextPath() %>/toLoginPage" />去登录</a>
	</span> 
	<span class="fr">查看<a href="<%=request.getContextPath() %>/service?url=goRegister" />《智典财富服务协议》</a></span>
</div>
<%@ include file="/mobile/comm/bott.jsp"%>
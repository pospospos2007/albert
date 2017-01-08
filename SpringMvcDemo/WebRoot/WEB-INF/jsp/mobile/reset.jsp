<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>
	<script type="text/javascript">
	 jQuery(document).ready(function($) {
		$('.login_f_btn').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		
		$('.index_login').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})
	
	}) 

	//手机号码校验	
	function checkMobile(){
	 /* var regst =/^1[3|4|5|7|8][0-9]\d{8}$/; */
	 	var vlidate = $("#mobile").val();
		 if (null==vlidate||""== vlidate) {
			  showDialogHTML("手机号码不能为空");
		    //$('#errorStr').text("账号中不能有空格!").show();
		    return false;
		  } 
		 $.post("app/checkPhoneExist",{
						"userName":vlidate
					},
					function(data){
				    	  if(data){
				    		  showDialogHTML('手机号不存在!');
				    		  return false;
				    	  }
		});
	   <%--  $.ajax({
	      type: "POST",
	      url:"<%=request.getContextPath() %>/app/checkPhoneExist",
			      data: "userName=" + vlidate,
			      dataType: "text",
			      success: function(data) {
			    	  alert(data);
			    	  if(data=="false"){
			    		  showDialogHTML('手机号不存在!');
			    		  return false;
			    	  }


			      }
			});	 --%>
			return true;
   }
   
   	// 手机验证码	
  	function getVerifycodeMobile(){
  		  if(!checkMobile()){
  		 	return false;
  		 }
  		 $('#getCodeButton').attr('disabled',true);
  		 $.post("<%=request.getContextPath() %>/sendCodeByPassWord",
 				{
 					"mobile":$("#mobile").val()
 				},
 				function(data){
 					if(data==1){
 						//$("#messageMobile").show();
 						showDialogHTML("验证码已发送");
 						$("#getCodeButton").html(60);
 						$("#getCodeButtonInactive").show();
 						$("#num").html(60);
 						$('#getCodeButton').attr('disabled',false);
 						$("#verifycode").focus();
 						//$("#verifycode").val(data);
 						jump(60);
 					}else if(data==3){
 						showDialogHTML("手机号不存在");
 						
 					}else{
 						$('#getCodeButton').attr('disabled',false);
 						/* alert(data == 2?"该手机号已被使用":"发送失败"+data); */
 						$("#mobile").focus();
 					}
 				});
 	}
 	
 	function jump(count) {
        window.setTimeout(function(){
            count--;
            if(count > 0) {
                $("#getCodeButton").html(count);
                jump(count);
            } else {
               $("#getCodeButton").show();
               $("#getCodeButtonInactive").hide();
               $("#getCodeButton").html("获取");
            }
        }, 1000);
   }
   
      	
    //校验手机验证码是否正确
    function mobileVerifycode(){
      	    if($("#mobile").val() == ""){
      		showDialogHTML("请输入手机号");
			$("#tip_vali").css({color:'DimGray'});
				return;
			}
			if($("#verifycode").val() == ""){
				showDialogHTML("请输入验证码");
				$("#tip_vali").css({color:'DimGray'});
				return;
			}
			$.post("app/boundPhoneWithNoForward",{
				"mobilePhone":$("#mobile").val(),
				"verifycode":$("#verifycode").val()
				},function(data){
			if(data==2){
				$("#tip_vali").html("✔");
				$("#tip_vali").css({color:'green'});
				$("#mobile").attr('disabled',true);
				$("#verifycode").attr('disabled',true);
				$("#getCodeButton").attr('disabled',true);
			}else if(data==1){
				showDialogHTML("手机验证码错误");
				$("#tip_vali").css({color:'DimGray'});
				$("#verifycode").val("");
			}else{
				showDialogHTML("手机验证失败");
				$("#tip_vali").css({color:'DimGray'});
				$("#verifycode").val("");
			}
		}); 
		return true;
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
	    
     //校验密码是否正确
 	function chekPwdfor() {
    	 
 		var result = getVerifycodeMobile();
 		if(result == false ){
 			return;
 		}
 		
	     var re = false;
	    var pwd = $("#password").val();
	    if (pwd ==""|| pwd == null ) {
	    	showDialogHTML("请输入密码");
	        return re;
	    }else if(pwd.length<6||pwd.length>16){
	    	showDialogHTML("密码长度为6-16位");
	        return re;
	    }else if(!onlyLetterNumber(pwd)){
	    	showDialogHTML("只能输入数字和字母");
	        return re;
	    } else {
	      	pwStrength(pwd);
	        return true;
	    } 
	 }
    	
    
    //二次验证密码
    function checkPWDui() {  //密码确认
         var re = false;
        if(!chekPwdfor()){
            return re;
        }
        var PWDui = $("#pdwuri").val();
        var PWD = $("#password").val();
        if (PWDui == "" || PWDui== null) {
        	showDialogHTML("请再次输入用户密码");
            //$("#messagepwduri").show();
            return re;
        } else if (PWD != PWDui) {
        	showDialogHTML("两次密码不一致");
            //$("#messagepwduri").show();
            return re;
        } else if (PWD == PWDui) {
           // $("#messagepwduri").html("");
            re = true;
            return re;
        }  
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
 
    

 // 注册按钮提交操作
    function changePass() {
        if(checkMobile()&&mobileVerifycode()&&chekPwdfor()&&checkPWDui()){ 
        	/*  var  verifycode=$("#verifycode").val();
		      if(verifycode==""){
		    	  showDialogHTML("请输入验证码！");
		         return false; 
		        }  */
				$("#mobile").attr('disabled',false);
				$("#verifycode").attr('disabled',false);
				
	            $("#changePassword").submit();
	            
        } else{
            return false;       
        }
    }
    
    </script>

<body>
	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/user_n.jsp" title="返回" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>重置密码
		</h2>
		<div class="clear"></div>
	</div>

	<div class="login_f reset_f">
		<form id="changePassword" action="<%=request.getContextPath() %>/updatePasswordByPhone" method="post">
			<dl style="width: 25%">
				<dd>手机号码</dd>
				<dd>验证码</dd>
				<dd>新密码</dd>
				<dd>确认密码</dd>
			</dl>
			<dl style="width: 75%">
				<dd class="bor">
					<input type="text" name="mobile" id="mobile" onblur="checkMobile()" maxlength='11' placeholder="请输入注册时的手机号" />
				</dd>
				<dd class="bor">
					<input type="text" id="verifycode" name="verifycode" onChange="mobileVerifycode()" placeholder="请输入验证码" />
					<span id="tip_vali" class="tip" style="color: green; right: 30%; font-size: 16px"></span>
					<span class="get_vali" id="getCodeButton" onclick="getVerifycodeMobile()">获取</span>
				</dd>
				<dd class="bor">
					<input type="password" maxlength='16' name="passwd" id="password" onblur="chekPwdfor()" placeholder="请输入6到16位新密码" />
					 <span id="messagePwd" class="tip"></span>  
				</dd>
				<dd>
					<input type="password" name="pdwori" id="pdwuri"  maxlength='16' onblur="checkPWDui()" placeholder="请再次输入密码" />
					  <span id="messagepwduri" class="tip"></span>  
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
	<a ><div class="login_f_btn" onclick="changePass()">修改</div></a>

	<%@ include file="./comm/bott.jsp"%>
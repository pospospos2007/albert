<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <title>登录</title>
    <link rel="stylesheet" media="screen" href="<%=path%>/css/register/zzsc.css" />
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <script>
    $('#username').keyup(function(e){
		var kd = $('#username').val();
		if(null!=kd&&""!=kd){
			
		}
		
	});
    function login(){
    	$.ajax({
    		url : "<%=path%>/loginvalidate",
    		data : $("#msform").serialize(),
    		type : "POST",
    		dataType : "JSON",
    		success : function(result) {
    			if (result.status) {
    				$("#msg").html("登陆成功");
    				setTimeout(function(){
						window.location.href ="<%=path%>/userInfo";
					 },2000);
    			} else {
    				alert("登陆失败");	
    			}
    		},
    		error : function(){
    			alert("系统错误");	
    		}
    	}); 
    }
    
    </script>
</head>
<form id="msform" action="<%=path%>/loginvalidate" method="post">
    <!-- progressbar -->
    <ul id="progressbar">
<!--         <li class="active">登录</li> -->
<!--         <li>Social Profiles</li> -->
<!--         <li>Personal Details</li> -->
    </ul>
    <!-- fieldsets -->
    <fieldset>
        <h2 class="fs-title">登录</h2>
        <h3 class="fs-subtitle">填写信息</h3>
        <input type="text" name="username" id="username" placeholder="用户名" autocomplete="off" />
        <input type="password" name="password" placeholder="密码" autocomplete="off" />
        <input type="button" onclick="login()" class="action-button" value="提交" />
        <span id="msg" style="color:red"></span>
    </fieldset>
</form>
<script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/register/zzsc.js" type="text/javascript"></script>
<div style="text-align:center;clear:both">
</div>
</body>

</html>
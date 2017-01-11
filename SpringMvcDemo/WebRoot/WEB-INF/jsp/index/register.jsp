<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<html>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script src="http://thecodeplayer.com/uploads/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="http://thecodeplayer.com/uploads/js/jquery.easing.min.js" type="text/javascript"></script>
<script src="<%=path%>/js/register/zzsc.js" type="text/javascript"></script>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
<head>
    <title>注册</title>
    <link rel="stylesheet" media="screen" href="<%=path%>/css/register/zzsc.css" />
    <script>
    $(function(){
    $('#username').keyup(function(e){
		var kd = $('#username').val();
		if(null!=kd&&""!=kd){
			$.ajax({
	    		url : "<%=path%>/checkUsername",
	    		data : {"username":kd},
	    		type : "POST",
	    		dataType : "JSON",
	    		success : function(result) {
	    			if (result.status) {
	    				$("#submitBtn").addClass("action-button");
	    				$("#submitBtn").val("提交");
	    				$("#submitBtn").removeAttr('disabled');
	    			} else {
						$("#submitBtn").removeClass("action-button");
						$("#submitBtn").val("用户名已存在");
	    				$("#submitBtn").attr('disabled','disabled');
	    			}
	    		},
	    		error : function(){
	    			alert("系统错误");	
	    		}
	    	}); 
		}
		
		});
	});
    </script>
</head>
<form id="msform" autocomplete="off" action="<%=path%>/register" method="post">
    <!-- progressbar -->
    <ul id="progressbar">
<!--         <li class="active">帐户设置</li> -->
<!--         <li>Social Profiles</li> -->
<!--         <li>Personal Details</li> -->
    </ul>
    <!-- fieldsets -->
    <fieldset>
        <h2 class="fs-title">创建账号</h2>
        <h3 class="fs-subtitle">填写信息</h3>
        <input type="text" name="username" id="username" placeholder="用户名" autocomplete="off"  />
        <input type="password" name="password" placeholder="密码" autocomplete="off"  />
 		<input type="text" name="email" placeholder="Email" autocomplete="off" />
        <input type="submit"  id="submitBtn" class="next action-button" value="提交" />
    </fieldset>

</form>
<div style="text-align:center;clear:both">
</div>
</body>

</html>
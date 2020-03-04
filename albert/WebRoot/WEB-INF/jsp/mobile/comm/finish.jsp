<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <style type="text/css">
		.theme-p {position: fixed;top: 40%;left: 50%;width: 80%;margin-left: -40%;color: #ff2766;text-align: center}
	</style>
    <title>waiting</title>
    <script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
		$(document).ready(function(){
			showWaitDialogHTML('accountInfo',$("#description").val());
		});
		
		function showWaitDialogHTML(targetUrl,description) {
			//$('.theme-p-mask').fadeIn(100);
			//$('#waitbox').slideDown(200);
			delayURL(window.top.location.href,targetUrl,description);
		}
		
		//延时等待框
		function delayURL(url,targetUrl,description) {
			//var delay1 = document.getElementById("time").innerHTML;
			var delay = document.getElementById("time1").innerHTML;
			document.getElementById("time").innerHTML = description+"成功,";
			if(delay > 1) {
				delay--;
				//document.getElementById("time").innerHTML = delay;
				document.getElementById("time1").innerHTML = delay;
			} else {
				//location.reload();
				$('.theme-p-mask').fadeOut(100);
				$('#waitbox').slideUp(200);
				window.location.href=targetUrl; 
				
			}
			setTimeout("delayURL('" + url + "','" + targetUrl + "','"+description+"')", 1000);
		}
	</script>
  </head>
  <body style="background-image:url(mobile/images/waiting.jpg);background-size:100% 100%;background-attachment:fixed;">
    <div class="theme-p">
	    <p>
	    	<font size="10">	
	    		<span id="time"></span></br>5秒后完成交易<br />
	    		<span id="time1">6</span>秒
	    	</font>
	    </p>
		<input type="hidden" id="description" value="${description}">
    </div>
  </body>
</html>

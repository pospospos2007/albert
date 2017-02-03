<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
 	 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    
    <title>Albert,to be a hero</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="Albert,keyword2,keyword3">
	<meta http-equiv="description" content="to be a hero">
	<link href="<%=path%>/css/style.css" rel="stylesheet" />
  </head>
  
  	<body>
	    <%--<a href="<%=path%>/getUserList">Got it.</a> <br>--%>
	    <div id="container">
    <div id="header">
	<h1>Welcome<p>不忘初心，方得始终</p></h1>
        <a href="<%=path%>/movie/toMovieList">&nbsp;&nbsp;前往|Go for fun&nbsp;&nbsp;</a>
	<%--<a href="javascript:return" onclick="alert('Daddy is here!')" >关于|About me</a>
	--%><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=497603778&amp;site=qq&amp;menu=yes" target="_blank">联系我|Contact me</a>
    </div>
    <video id="bgvideo" autoplay="true" width="100%" name="media" poster="images/123.jpg"><!--将这里的预览图更换为你自己的-->
	<source src="video/Still Remaining.mp4" type="video/mp4">
	<%--<source src="media/123.ogv" type="video/ogg">
	<source src="media/123.webm" type="video/webm">
	
	--%><%--<source src="sound/fun.mp3" type="video/webm">
	--%>
<%--	<source src="video/Superman With a GoPro.mp4" type="video/mp4">--%>
	<%--<source src="images/123.jpg" type="video/mp4">
    您的浏览器不支持 video 标签。请使用更先进的浏览器,如--%><a href="http://www.google.cn/chrome/browser/" target="_blank">Chrome浏览器</a>或<a href="http://www.firefox.com.cn/download/" target="_blank">Firefox浏览器</a>
    </video>
</div>
	    
	    
    </body>
    
    
</html>

<html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
	<meta charset="utf-8" />
	<title>斗地主</title>
	
</head>
<body>
<div  style=" width:1350px; height:768px; margin-right:auto;margin-left:auto; overflow:hidden" >
    <embed src="game/Landlords.swf"  height="768" width="1350"/>
	</div>
</body>
</html>

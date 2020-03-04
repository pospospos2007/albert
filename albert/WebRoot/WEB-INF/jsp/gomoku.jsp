<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html manifest="offline.appcache">
<head>  <title>Gomoku</title> 
 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">

 </head> 
<body>

<div style="width:770px;margin:0 auto">
<iframe src="<%=path%>/gomokuHelper" frameborder="0" scrolling="auto"  marginwidth="0" marginheight="0" allowtransparency="true" width="760px" height="500px"></iframe>
</div>
</body>
</html>
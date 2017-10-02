<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="base">${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}</c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="format-detection" content="telphone=no,email=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-title" content="标题">
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<meta name="viewport"
	content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="MobileOptimized" content="240" />
<link rel="apple-touch-icon" href="<%=request.getContextPath() %>/mobile/images/logo_zd.jpg" />
<title>Albert</title>
<meta name="keywords"
	content="Albert｜albert6.com:9999｜论坛｜人工智能｜智能识别｜ 博客｜ 影音娱乐" />
<meta name="description"
	content="Albert albert6.com:9999 play for fun" />

<link href="<%=request.getContextPath() %>/mobile/style/style.css" rel="stylesheet" type="text/css"
	media="all" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/mobile/style/jquery.fancybox-1.3.1.css"
	type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/mobile/style/tipso.min.css">

<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery.fancybox-1.3.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery.mousewheel-3.0.6.pack.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/pngobject.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/unslider.js"></script>
<script src="<%=request.getContextPath() %>/mobile/js/tipso.min.js"></script>
</head>

<%@ include file="dialog.jsp"%>
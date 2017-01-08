<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<meta charset="utf-8" />
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link href="http://v3.bootcss.com/examples/sticky-footer-navbar/sticky-footer-navbar.css" rel="stylesheet">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>	

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="f"  uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s"  uri="http://www.springframework.org/tags/spring"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<script>

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-79653638-1', 'auto');
  ga('send', 'pageview');

</script>
<link rel="shortcut icon" href="<%=path%>/images/favicon.ico">


</head>

<body>

<!-- Fixed navbar -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
<!--           <a class="navbar-brand" href="index.html">0.0</a> -->
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
<!--             <li onclick="active(this)" ><a href="<%=path%>/getAllAirticle">博客</a></li> -->
			<li onclick="active(this)"><a  href="<%=path%>/movie/toMovieList"><s:message code='movie.list'/></a></li>
			<li onclick="active(this)"><a  href="<%=path%>/message/getAllTheme"><s:message code='forum.list'/></a></li>
            <li onclick="active(this)"><a  href="<%=path%>/file/faceList"><s:message code='portrait.recognition.list'/></a></li>
            <li onclick="active(this)"><a  href="<%=path%>/message/getArticleFromZhihu"><s:message code='daily.list'/></a></li>
            <li onclick="active(this)"><a  href="<%=path%>/toGameList"><s:message code='game.list'/></a></li>
            <li onclick="active(this)"><a target="_blank" href="<%=path%>/lab/toLab"><s:message code='laboratory.index'/></a></li>
            <li onclick="active(this)"><a target="_blank" href="<%=path%>/file/toMusicVisual"><s:message code='music.visualization.index'/></a></li>
            
<%--              <li onclick="active(this)"><a href="<%=path%>/chatroom/toChatroom">聊天室</a></li> --%>
          </ul>
        </div><!--/.nav-collapse -->
      </div>
    </nav>

    <!-- Begin page content -->
    <div class="container">
	
	
	
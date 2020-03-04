<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>${zhihu.title}</title>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
<!-- 	<link rel="stylesheet" href="css/style3.css" /> -->
<!--   	<link rel="stylesheet" href="css/font-awesome.min.css"> -->
  	<link rel="stylesheet" href="${zhihu.css}">
  	<style>
  .content {
  font-size: 40px;
}
  	</style>
  </head>
    
  
  <body>




   
<!--       <h1>${zhihu.title}</h1> -->
     <button type="button" style="width:100%;height:10%;font-size:40px;background:#f74172"  onclick="backtoHome()">返回</button>
<!--     <img src="${zhihu.images}"></img>  -->
        ${zhihu.content}
     


    

<script type="text/javascript">
function backtoHome(){
// 	window.location.href="<%=path%>/message/getArticleFromZhihu";
	history.go(-1);
}
$( document ).ready(function() {
// 	$('.img-place-holder').append("<img src='${zhihu.images}'  ></img>");
	
 	$("img").each(function(){
 		var str = $(this).attr("src");
 		$(this).attr("src","http://<%=request.getLocalAddr()%>:<%=request.getLocalPort()%><%=request.getContextPath()%>/url.jsp?url="+str);
		
 	});
	
});


</script>
  </body>
</html>

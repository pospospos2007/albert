<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>${airticle.title}</title>
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
	<link rel="stylesheet" href="css/style3.css" />
  	<link rel="stylesheet" href="css/font-awesome.min.css">
  </head>
    <style>
     .content{
       background:rgba(217,213,206,1) url("img/a3.png") no-repeat; 
       overflow:scroll; 
     }
     .article{
      text-indent: 2em;
      font-size: 18px;
      letter-spacing: 0.05em;
     }
  </style>
  
  <body>

	<%@ include file="/include/title.jsp"%>


     


   </div>
   
   <div class="content">
      <br/>
      <h1>${airticle.title}</h1>
     
      <div class="article">
        <h2></h2>
        <p class="right">作者:&nbsp;<a href="http://weibo.com/pospospos2007" target="_blank">Albert</a>&nbsp;&nbsp;操作:&nbsp;修改</a>
       删除       </p>
        <br />
        <p>${airticle.content}</p>
       
      </div>


    

   </div>
   <div class="footer">
      <copy>&copy;Albert</copy>
   </div>
	
	<script charset="utf-8" src="kindeditor/plugins/insertVideo/ckplayer.js"></script>
  </body>
</html>

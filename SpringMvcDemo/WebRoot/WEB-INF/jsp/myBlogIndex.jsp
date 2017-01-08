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
    <title>MyBlog</title>
    
    <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <link rel="stylesheet" href="css/style3.css" />
    <link rel="stylesheet" href="css/font-awesome.min.css">
	<style type="text/css">
		.pic{
		  width:20px;
		  height:16px;
		  position:absolute;
		  left:-1px;
		  top:-1px;
		  
		}	
	    .content{
		
		 background:rgba(176,176,176,1) url("img/background2.jpg") no-repeat;
		   overflow:hidden; 
		}	
	    .div2{
		  height:0;
		}				
	</style>

  </head>
  
  <body>


	<%@ include file="/include/title.jsp"%>


      <div class="content" >
       
<!-- 	<img src="images/111.gif"  height="100px" width="100px" alt="11" /> -->
	  </div>
	  <div class="div2">
	  </div>
<!-- 	<div class="footer"><copy>&copy;Albert</copy></div> -->

	<script language="javascript" src="rain/jquery.js"></script>
	<script type="text/javascript"src="rain/flowerrain.js"></script>
	
		
	
  </body>
</html>

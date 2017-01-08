<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>文章列表</title>
     <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style3.css" />
    <link rel="stylesheet" href="css/manage.css" />

  </head>
  <style type="text/css">
	.content{
     background:rgba(202,195,189,1) url("img/a4.jpg") no-repeat; 
    } 
    .article{
      
       font-size: 20px;
       letter-spacing: 0.1em;
       line-height: 2em;
    }
   
</style>
 <style>
    a#release{
      display: block;
      width:100px;
      margin-top: -30px;
      margin-left:550px;
      float: left;
      padding:5px 9px;
     /* background-color:rgba(248,201,207,1);*/
      background-color:rgba(241,132,151,1);
      border-radius: 3px;
      color:#666;
    }
    a#release:hover{
      color:#FFF;
    }
    
    .right{
      width: 100%;
      font-size: small;
      text-align: right;
    }
  /*  .right::before,.right::after{
      content: "";
      display: block;
    }
    .right:after{clear:both;}*/
  </style>
  <body>
	<%@ include file="/include/title.jsp"%>

      <div class="content">
      	  <br />
      	  <h1>&nbsp;&nbsp;文章列表</h1> <a href="<%=path%>/toAddAirticle" id="release"><i class="fa fa-file-text"></i>&nbsp;发布新文章</a>
      	  <div class="article">
      	  	<ol>
      	  	
      	      <c:if test="${!empty airticles }">
					<c:forEach items="${airticles}" var="airticle">
					  
							<li><a href="<%=path%>/getAirticleDetail?airticleId=${airticle.airticleId}">${airticle.title}</a></li>
							
					</c:forEach>
				</c:if>
      	  	
      
      	  	</ol>
      	  </div>


	  </div>
	 
	
<!-- 	<div class="footer"><copy>&copy;Albert</copy></div> -->
	
  </body>
</html>

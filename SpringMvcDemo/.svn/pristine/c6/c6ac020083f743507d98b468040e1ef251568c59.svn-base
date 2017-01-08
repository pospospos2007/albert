<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%@ taglib uri="/WEB-INF/tld/pager.tld" prefix="pager" %> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    
    
    <title>主题列表</title>
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
 
 /*page*/
#page{
	width:100%;
	height:26px;
	margin:50px 0;
	text-align:center;
}
#page ul{
	display:inline-block;
	margin:0 5px;
}
#page span{
	display:inline-block;
	line-height:20px;
	height:24px;
	border:1px solid #999;
	padding:0 0.5em;
	border-radius:2px;
}
#page span.onC a{
	color:#b8b8b8;
}
#page span i{
	display:inline-block;
	width:5px;
	height:17px;
	margin:0 5px 0 0;
	background:url(/images/listCon.png) no-repeat;
	background-position:-16px -1px;
	vertical-align:middle;
}
#page span.onC i{
	background-position:-6px -1px;
}
#page span.last i{
	background-position:-6px -15px;
	margin:0 0 0 5px;
}
#page span.last i.onC{
	background-position:-16px -15px;
}
#page ul li{
	display:inline-block;
	line-height:20px;
	height:24px;
	border:1px solid #999;
	border-radius:2px;
	margin: 0 2px;
	transition:all 0.5s;
}
#page ul li:hover{
	border:1px solid #3598da;
	background:#3598da;
}
#page ul li.onC{
	border:1px solid #3598da;
	background:#3598da;
}
#page ul li.onC a,#page ul li:hover a{
	color:#fff;
}
#page ul li a{
	display:inline-block;
	line-height:20px;
	padding:0 0.5em;
	border-radius:2px;
}


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
      	  <h1>&nbsp;&nbsp;主题列表</h1> <a href="<%=path%>/message/toAddTheme" id="release"><i class="fa fa-file-text"></i>&nbsp;发布新主题</a>
      	  <div class="article">
      	  	<form action="<%=path%>/message/getAllTheme" method="post"
				id="listform" name="listform">
				
      	      <ul>
					<c:forEach items="${pageView.voList}" var="theme">
					  
							<li><a href="<%=path%>/message/getThemeDetail?id=${theme.id}">${theme.theme}</a></li>
							
					</c:forEach>
			</ul>
			
			  
				
       <div id="page">
      <pager:pager pageSize="${pageView.pageSize}"  currentPageId="currentPage"  pageNo="${pageView.currentPage}"   fromId="listform" recordCount="${pageView.recordCount}"/>
     </div>
				</form>
      	  	
      </div>
      	


	  </div>
	 
	
<!-- 	<div class="footer"><copy>&copy;Albert</copy></div> -->
	
  </body>
</html>

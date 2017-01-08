<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/pager.tld" prefix="pager" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>图像识别</title>


  </head>
 
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
 
 
 
  </style>
  <style type="text/css">
        * {
            padding: 0;
            margin: 0;
            list-style: none;
        }
        
        ul {
            padding: 10px;
        }
        
        img {
            display: block;
            width: 100%;
            margin-bottom: 10px;
        }
        
        @-webkit-keyframes fadeIn {
            0% {
                opacity: 0.5
            }
            100% {
                opacity: 1
            }
        }
        
        @keyframes fadeIn {
            0% {
                opacity: 0.5
            }
            100% {
                opacity: 1
            }
        }

    </style>
  <body>

	<button type="button" style="width:100%;height:10%;" onclick="backtoHome()">返回主页</button>
    <button type="button" style="width:100%;height:10%;" onclick="uploadImage()">上传图片</button>
	
	 <div class="content">
	<form id="imageform" action="<%=path%>/file/faceList" >
	
	 <ul id="ul">
<!--         <li><img data-src="images/111.gif" src="images/default.png" alt="默认图片"></li> -->
<!--         <li><img data-src="images/111.gif" src="images/default.png" alt="默认图片"></li> -->
		<c:if test="${empty pageView.voList }">
		<p>暂无图片</p>
		</c:if>
		<c:forEach items="${pageView.voList}" var="image">
        <li><a href="<%=path%>/file/faceDetail?id=${image.id}" ><img data-src="uploadface/${image.url}" src="images/default.gif" alt="${image.title}"></a></li>
      	 </c:forEach>
      
    </ul>
   		<div id="page">
      <pager:pager pageSize="${pageView.pageSize}"  currentPageId="currentPage"  pageNo="${pageView.currentPage}"   fromId="imageform" recordCount="${pageView.recordCount}"/>
   		</div>
    
    </form>
    
    </div>
    <script src="js/lazyloadImg.min.js"></script>
    <script>
    var lazyloadImg = new LazyloadImg({
        el: '#ul [data-src]', //匹配元素
        top: 50, //元素在顶部伸出长度触发加载机制
        right: 50, //元素在右边伸出长度触发加载机制
        bottom: 50, //元素在底部伸出长度触发加载机制
        left: 50, //元素在左边伸出长度触发加载机制
        qriginal: false, // true，自动将图片剪切成默认图片的宽高；false显示图片真实宽高
        load: function(el) { //图片加载成功后执行的回调方法，传入一个参数
            el.style.cssText += '-webkit-animation: fadeIn 01s ease 0.2s 1 both;animation: fadeIn 1s ease 0.2s 1 both;';
        },
        error: function(el) { //图片加载失败后执行的回调方法

        }
    });
    //结束图片懒加载事件监听：lazyloadImg.end();
    //开始图片懒加载事件监听：lazyloadImg.start();
        
        function uploadImage(){
        	window.location.href="<%=path%>/file/toAddFace";
        }
        
        function backtoHome(){
        	window.location.href="<%=path%>/index";
        }

    </script>
	
  </body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
 	 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    
    <title>Albert's Home</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<%--<link rel="stylesheet" href="<%=path%>/css/APlayer.min.css">
--%><style>
.demo{width:360px; margin:60px auto 10px auto}
.demo p{padding:10px 0}
</style>
  </head>
  
  <body>
    <a href="<%=path%>/getUserList">Got it.</a> <br><%--
    <div id="main">
    <div class="demo">
    <p><strong>样式2：</strong></p>
        <div id="player1" class="aplayer"></div>
    </div>
    </div>

  --%></body><%--
  
  <script src="<%=path%>/js/APlayer.min.js"></script>
    <script>
        var ap1 = new APlayer({
            element: document.getElementById('player1'),
            narrow: false,
            autoplay: false,
            showlrc: false,
            music: {
                title: 'Preparation',
                author: 'Hans Zimmer/Richard Harvey',
                url: '<%=path%>/sound/fun.mp3',
                pic: 'http://7xifn9.com1.z0.glb.clouddn.com/Preparation.jpg'
            }
        });
        ap1.init();
       
    </script>
	
--%></html>

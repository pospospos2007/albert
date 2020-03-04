<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%@ include file="/include/head.jsp"%>
    <title><s:message code='game.list'/></title>
    <link rel="stylesheet" href="css/picture.css">

  <style>
	.content{
		  position: relative;
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
	
   <div class="content">
   	   <h1><s:message code='game.wall'/></h1>
<!--    	   <audio src="sound/Dimmi - Dizzy.mp3" controls="controls" autoplay autostart="0" >请使用最新的firefox和chrome浏览器访问</audio> -->
   	   <a href="<%=path%>/QBNB" target="_blank"><img src="img/QBNB.png" alt="Q版泡泡堂" class="pic pic1"></a>
   	   <a href="<%=path%>/kofWing" target="_blank"> <img src="img/kofWing.png" alt="拳皇Wing" class="pic pic2"></a>
   	   <a href="<%=path%>/Landlords" target="_blank"> <img src="img/Landlords.png" alt="斗地主" class="pic pic3"></a>
   	   <a href="<%=path%>/Ensign" target="_blank"><img src="img/Ensign.png" alt="暗翻军旗" class="pic pic4"></a>
   	  <a href="<%=path%>/JellyDrips" target="_blank"> <img src="img/JellyDrips.png" alt="果冻大逃亡" class="pic pic5"></a>
<!--    	  <img src="img/backgrond.jpg" alt="mypicture" class="pic pic6"> -->
<!--    	   <img src="img/background2.jpg" alt="mypicture" class="pic pic7"> -->
<!--    	    <img src="img/petal.jpg" alt="mypicture" class="pic pic8"> -->
<!--    	   <img src="img/manage.jpg" alt="mypicture" class="pic pic9"> -->
<!--    	    <img src="img/may1.jpeg" alt="mypicture" class="pic pic10">  -->

   </div>
	 
<%@ include file="/include/footer.jsp"%>
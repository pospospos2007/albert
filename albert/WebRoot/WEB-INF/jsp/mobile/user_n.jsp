<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../mobile/comm/head.jsp"%>

<body>
	<div class="user_body_bg"><div class="user_body">
		<div class="user_l fl tc">
		<img style="margin: 58px auto 25px; width: 46%"
			src="<%=request.getContextPath() %>/mobile/images/user_face.png" />
		<p style="font-size: 16px; margin-bottom: 20px">登录后，您可以</p>

		<p>● 智能图像识别（暂未开放）</p>
		<p>● 神经网络测试（暂未开放）</p>

		<div class="login_m user_m">
			<a style="background: #41b1d6" href="#">注册</a>
			 <a href="#">登录</a>
			<p class="clear"></p>
			<a style="background: #89838f; width: 80%" href="<%=request.getContextPath() %>/mobile/more">更多</a>
			<%-- <a style="width:80%; box-sizing:border-box;background:transparent;border:1px solid #fff;" href="<%=request.getContextPath() %>/mobile/dialogMoble/app-release.apk">APP下载</a> --%>
		</div>

	</div>

	
    <div class="user_r fl">
		<iframe src="<%=request.getContextPath() %>/index"  marginheight="0" marginwidth="0" 
		height="100%" allowtransparency="true" scrolling="no" noResize 
		hspace="0" vspace="0" pixelHeight="auto" frameborder="0" 
		scrolling="no"></iframe>
		<a href="<%=request.getContextPath() %>/index"><div class="float_b"></div></a>
	</div><div class="clear"></div>
    </div></div>           

<%@ include file="../mobile/comm/bott.jsp"%>
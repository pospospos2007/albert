<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%request.setCharacterEncoding("UTF-8");%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<meta charset="UTF-8">
	<title>画图板</title>
	<script src="<%=path%>/js/draw/js/jquery.js"></script>
	<script src="<%=path%>/js/draw/js/jquery.bigcolorpicker.js"></script>
	<script src="<%=path%>/js/draw/js/draw.js"></script>
	<script src="<%=path%>/js/draw/js/storage.js"></script>
	<script src="<%=path%>/js/draw/js/main.js"></script>
	<link rel="stylesheet" href="<%=path%>/css/draw/css/canvas.css" type="text/css" />
	<link rel="stylesheet" href="<%=path%>/css/draw/css/jquery.bigcolorpicker.css" type="text/css" />
	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
</head>
<body>
<div id="draw">

		<div id="logo">多贝绘画板</div>

		<ul class="draw_controller cf">
			<li class="normal pencil" title="铅笔"></li>
			<li class="normal handwriting" title="涂鸦"></li>
			<li class="normal line showLine" title="线条大小"></li>
			<li class="normal showColor" id="chooseColor" title="选择颜色"></li>
			<li class="normal rubber" title="橡皮擦"></li>

			<span>|</span>

			<li class="normal drawLine" title="画直线"></li>
			<li class="normal square" title="方形"></li>
			<li class="normal circle" title="圆"></li>
			<li class="normal fill" title="填充前景"></li>

			<span>|</span>

			<li class="normal cancel" title="撤销上一个操作"></li>
			<li class="normal next" title="重做上一个操作"></li>	
			<li class="normal clearContext" title="清屏"></li>
			<li class="normal save" title="保存"></li>
			<li class="normal downloadImage" title="下载"><a href="#" download="picture.png"  id="downloadImage_a"></a></li>
		</ul>


		<div class="canvas_container">
			<canvas id="canvas">
				浏览器不支持哦，亲
			</canvas>

			<canvas id="canvas_bak"></canvas>
		</div>
		<p class="tip">小提示：可以拖拽传入图片编辑哦~</p>

		<div id="showHistory" class="cf">
			<h2>绘画保存记录</h2>
		</div>

		<div id="line_size"  class="line_size normal">
			<ul>
				<!-- <li><button data-value="0.5" class="small"><span style="width: 1px; height: 1px;"></span></button></li> -->
				<li><button data-value="1" class="small"><span style="width: 2px; height: 2px;"></span></button></li>
				<!-- <li><button data-value="2" class="small"><span style="width: 3px; height: 3px;"></span></button></li> -->
				<li><button data-value="3" class="small selected"><span style="width: 4px; height: 4px;"></span></button></li>
				<!-- <li><button data-value="4"><span style="width: 6px; height: 6px;"></span></button></li> -->
				<li><button data-value="5"><span style="width: 8px; height: 8px;"></span></button></li>
				<li><button data-value="7"><span style="width: 10px; height: 10px;"></span></button></li>
				<!-- <li><button data-value="9"><span style="width: 12px; height: 12px;"></span></button></li> -->
				<!-- <li><button data-value="16"><span style="width: 16px; height: 16px;"></span></button></li> -->
				<!-- <li><button data-value="32" class="large"><span style="width: 28px; height: 28px;"></span></button></li> -->
			</ul>
		</div>



</div>
</body>

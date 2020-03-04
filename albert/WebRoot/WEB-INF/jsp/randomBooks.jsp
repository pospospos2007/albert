<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
 	 <link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    
    <title>randomBooks</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style>
		* {
			padding: 0;
			margin: 0;
		}
		body {
			background-color: #ccc;
			color: #555;
			font-size: 14px;
			font-family: "Avenir Next", 'Lantinghei SC';
			-webkit-font-smoothing: antialiased;
		}
		.wrap {
			width: 100%;
			height: 600px;
			position: absolute;
			top: 50%;
			margin-top: -300px;
			background-color: #333;
			overflow: hidden;
			-webkit-perspective: 800px;
		}
		.photo {
			width: 260px;
			height: 320px;
			position: absolute;
			z-index: 1;
			box-shadow:  0 0 1px rgba(0,0,0, .5);
			-webkit-transition: all .5s;
			-webkit-transform: rotate(0deg);
		}
		.photo .side {
			width: 100%;
			height: 100%;
			background-color: #eee;
			position: absolute;
			top: 0;
			right: 0;
			padding: 10px;
			-webkit-box-sizing: border-box;
			box-sizing: border-box;
		}
		.photo .side-front{

		}
		.photo .side-front .image {
			width: 100%;
			height: 280px;
			line-height: 280px;
			overflow: hidden;
		}
		.image img {
			width: 100%;
		}
		.photo .side-front .caption {
			height: 40px;
			text-align: center;
			font-size: 16px;
			line-height: 40px;
			color: #000;
			font-family: "微软雅黑";
		}

		.photo .side-back .desc {
			padding: 5px;
			color: #666;
			font-size: 14px;
			line-height: 1.5em;
			font-family: "微软雅黑";
		}
		.photo_center {
			left: 50%;
			top: 50%;
			margin: -160px 0 0 -130px;
			z-index: 999;
		}
		.photo-wrap {
			position: absolute;
			width: 100%;
			height: 100%;
			-webkit-transform-style: preserve-3d;
			-webkit-transition: all .5s ease;
			-moz-transition: all .5s ease;
			-ms-transition: all .5s ease;
			-o-transition: all .5s ease;
			transition: all .5s ease;
		}
		.photo-wrap .side-front {
			-webkit-transform: rotateY(0deg);
		}
		.photo-wrap .side-back {
			-webkit-transform: rotateY(180deg);
		}
		.photo-wrap .side{
			-webkit-backface-visibility: hidden;
		}
		.photo-front .photo-wrap{
			-webkit-transform: rotateY(0deg);
		}
		.photo-back .photo-wrap {
			-webkit-transform: rotateY(180deg);
		}
	</style>
  </head>
  
  	<body onselectstart="return false;">
	
	<div class="wrap" id="wrap">
		<!-- photo负责平移，旋转 -->
		<div class="photo photo-front" id="{{id}}">
			<!-- photo-wrap负责翻转 -->
			<div class="photo-wrap">
				<div class="side side-front">
					<p class="image">
						<img src="1.jpg" alt="">
					</p>
					<p class="caption">
						{{caption}}
					</p>
				</div>
				<div class="side side-back">
					<p class="desc">
						{{desc}}
					</p>
				</div>
			</div>
		</div>
	</div>
	<script src="js/data.js"></script>
	<script>
		// 通用函数 
		function g(selector) {
			if(typeof selector !== "string") {
				return false;
			}
			var str = selector.slice(1);
			if(selector.charAt(0) === ".") {
				return document.getElementsByClassName(str);
			} else if (selector.charAt(0) === "#") {
				return document.getElementById(str);
			}
		}
		// 检测className
		function hasClass(elements, cName) {
			return !!elements.className.match(new RegExp('(\\s|^)' + cName + '(\\s|$)'));
		}
		function removeClass(elements, cName) {
			if (hasClass(elements, cName)) {
				elements.className = elements.className.replace(new RegExp('(\\s|^)' + cName + '(\\s|$)'), ' ');
			}
		}
		function addClass(elements, cName) {
			if (!hasClass(elements, cName)) {
				elements.className += ' ' + cName;
			}
		}
		// 返回给定范围中的一个随机整数（闭区间）
		function random(range) {
			var min = Math.min(range[0], range[1]);
			var max = Math.max(range[0], range[1]);
			var diff = max - min + 1;
			var index = Math.floor( (Math.random() * diff +min) );
			return index;
		}
		// 翻转元素
		function turn(el){
			var cls = el.className;
			if(/photo-front/.test(cls)) {
				cls = cls.replace("photo-front","photo-back");
			}else if(/photo-back/.test(cls)){
				cls = cls.replace("photo-back","photo-front");
			}
			el.className = cls;
		}
		// 排序海报 
		function resort(n) {
			var photos = g(".photo"),
				photosArr = [].slice.call(photos),
				curPhoto = g("#photo" + n),
				photoCenter = photosArr.splice(n-1,1)[0],
				photosLeft = photosArr.splice(0, Math.ceil(photosArr.length / 2)),
				photosRight = photosArr;
			for (var i = 0; i < photos.length; i++) {
				if(hasClass(photos[i], "photo_center")) {
					removeClass(photos[i], "photo_center");
				}
				if( (i+1) !== n){
					photos[i].style['webkitTransform'] = "rotate("+ random([-90,90])+"deg)";
				}
			}
			addClass(curPhoto, "photo_center");
			var ranges = range();

			for (var k = 0; k < photosLeft.length; k++) {
				photosLeft[k].style.left = random(ranges.left.x) + "px";
				photosLeft[k].style.top = random(ranges.left.y) + "px";
			}
			for (var j = 0; j < photosRight.length; j++) {
				photosRight[j].style.left = random(ranges.right.x) + "px";
				photosRight[j].style.top = random(ranges.right.y) + "px";
			}

			curPhoto.style.left = "50%";
			curPhoto.style.top = "50%";
			curPhoto.style['webkitTransform'] = "rotate(0deg)";

			for (var i = 0,len = photos.length; i < len; i++) {
				photos[i].onclick = function(num){
					return function() {
						resort(num);
					};
				}(i+1);
			}
			curPhoto.onclick = function(){
				turn(this);
			};
		}
		// 计算左右分区的范围
		function range() {
			var range = {
				left: {
					x: [],
					y: []
				},
				right: {
					x: [],
					y: []
				}
			},
				wrap = {
					w: g('#wrap').clientWidth,
					h: g('#wrap').clientHeight
				},
				photo = {
					w: g('.photo')[0].clientWidth,
					h: g('.photo')[0].clientHeight
				};

				range.wrap = wrap;
				range.photo = photo;

				range.left.x = [-photo.w/2, wrap.w/2 - photo.w];
				range.left.y = [0, wrap.h-photo.w];

				range.right.x = [wrap.w/2 + photo.w/2 ,wrap.w - photo.w/2];
				range.right.y = [0, wrap.h-photo.w];

				return range;
		}
		// 数据读取
		function getData() {
			var wrap = g('#wrap'),
				photo = g('.photo')[0],
				html = "",
				wrapH = wrap.innerHTML;
			for (var i = 0; i < data.length; i++) {
				var wrapHtml = wrapH.replace("{{id}}", data[i].id)
								    .replace("1.jpg", data[i].src)
								    .replace("{{caption}}", data[i].caption)
								    .replace("{{desc}}", data[i].desc);
				html += wrapHtml;
			}
			wrap.innerHTML = html;
			resort ( random([1,data.length]));
		}
		window.onload = function() {
			getData();
		}
	</script>
	    
    </body>
    
    
</html>

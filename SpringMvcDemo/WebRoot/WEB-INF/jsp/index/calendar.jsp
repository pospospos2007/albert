<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>日历</title>
	<link rel="stylesheet" type="text/css" href="calendar.css">
</head>
<body>
	<div id="calendar">
		<header>
		    <div id="good"></div>
			<input type="button" id="pre">
			<input type="button" id="next">
		</header>
		<section>
			<ul id="week"></ul>
	    	<ul id="date"></ul>
		</section>
	</div>
	<div id="clock"></div>
	<span id="icon"></span>
	<div id="set">
		<div class="title">日期</div>
		<p>
			<select id="YEAR"></select>
			<select id="MONTH"></select>
			<select id="DATE"></select>
		</p>
		<div class="title">时间</div>
		<p>
			<select id="HOUR"></select>
			<select id="MINUTE"></select>
		</p>
		<div id="setBtn">
			<span>取消</span>
			<span>更改</span>
		</div>
	</div>
	<script src="calendar.jsp"></script>
</body>
</html>

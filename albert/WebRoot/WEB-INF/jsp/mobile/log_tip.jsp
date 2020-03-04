<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>

<body>

	<div class="index">
		<h1>
			<a href="user.htm"><img class="fl" src="images/index_login.jpg" /></a><a
				href="search.htm"><img class="fr" src="images/index_search.jpg" /></a>Albert
		</h1>

	</div>
	<div class="index_b">
		<ul>
			<li><a href=""><div
						style="background: url(images/index_ban1.jpg) center center no-repeat; background-size: 100% 100%; height: 160px"></div></a></li>
			<li><a href=""><div
						style="background: url(images/index_ban2.jpg) center center no-repeat; background-size: 100% 100%; height: 160px"></div></a></li>
			<li><a href=""><div
						style="background: url(images/index_ban3.png) center center no-repeat; background-size: 100% 100%; height: 160px"></div></a></li>
		</ul>
	</div>

	<div class="index_c">
		<h3>
			<a href="d_invest_r.htm">珠宝金融第二期001</a>
		</h3>
		<p style="line-height: 36px">
			<a>投资</a><b>12%</b><span>10天</span>&nbsp;&nbsp;&nbsp;一次性还本付息
		</p>
		<div class="index_c_per">
			<div class="index_c_per bg" style="width: 12%"></div>
		</div>
		<p>100.00元起股
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩163,700.00/20.00万</p>
	</div>

	<div class="index_c">
		<h3>
			<a href="d_invest_r.htm">珠宝金融第二期001</a>
		</h3>
		<p style="line-height: 36px">
			<a>投资</a><b>0%</b><span>10天</span>&nbsp;&nbsp;&nbsp;一次性还本付息
		</p>
		<div class="index_c_per">
			<div class="index_c_per bg" style="width: 0%"></div>
		</div>
		<p>100.00元起股
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩163,700.00/20.00万</p>
	</div>

	<div class="index_c">
		<h3>
			<a href="d_invest_r.htm">珠宝金融第二期001</a>
		</h3>
		<p style="line-height: 36px">
			<a style="background: #cbccce">满标</a><b>100%</b><span>10天</span>
		</p>
		<div class="index_c_per">
			<div class="index_c_per bg" style="width: 100%"></div>
		</div>
		<p>100.00元起股
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩163,700.00/20.00万</p>
	</div>

	<div class="index_c">
		<h3>
			<a href="d_invest_r.htm">珠宝金融第二期001</a>
		</h3>
		<p style="line-height: 36px">
			<a>投资</a><b>90%</b><span>10天</span>&nbsp;&nbsp;&nbsp;一次性还本付息
		</p>
		<div class="index_c_per">
			<div class="index_c_per bg" style="width: 90%"></div>
		</div>
		<p>100.00元起股
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩163,700.00/20.00万</p>
	</div>

	<div class="index_c">
		<h3>
			<a href="d_invest_r.htm">珠宝金融第二期001</a>
		</h3>
		<p style="line-height: 36px">
			<a>投资</a><b>30%</b><span>10天</span>
		</p>
		<div class="index_c_per">
			<div class="index_c_per bg" style="width: 30%"></div>
		</div>
		<p>100.00元起股
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩163,700.00/20.00万</p>
	</div>

	</div>

	<div class="theme-popover" style="display: block">
		<div class="theme-poptit">
			<a href="javascript:;" title="关闭" class="close">×</a> 提示
		</div>
		<p>请登录后进行投资</p>
		<a href="log.htm"><div class="index_login">去登录</div></a>
	</div>
	<div class="theme-popover-mask" style="display: block"></div>

	<script type="text/javascript" src="js/unslider.min.js"></script>
	<script>
	if(window.chrome){$('.index_b li a div').css('background-size', '100% 100%');}
	$('.index_b').unslider({
		fluid: true,
		dots: true,
		speed: 500
	});
</script>
	<%@ include file="./comm/bott.jsp"%>
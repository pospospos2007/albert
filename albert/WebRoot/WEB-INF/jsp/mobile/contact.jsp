
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../mobile/comm/head.jsp"%>

<body>

	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/account" title="返回" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>联系我
		</h2>

	</div>
	<div class="tc">
		<img src="<%=request.getContextPath() %>/mobile/images/contact_tel.jpg"
			style="width: 24%; margin: 25px auto" />
		<p>My Tel：(周一至周五 9:00-18:00)</p>
	</div>
	<div class="login_f_btn">110</div>

	<div class="reg_bot tc">
		<a style="cursor: pointer">关注我的微信</a>
	</div>

	<div class="theme-popover">
		<img class="bond_close" src="<%=request.getContextPath() %>/mobile/images/d_close.png" title="关闭" />
		<div style="width: 75%; margin: 25px auto">
			<img width="90%" src="<%=request.getContextPath() %>/mobile/images/weixin.jpg" />
		</div>
		<div
			style="border-top: 1px solid #d2d2d2; font-size: 16px; color: #f74172; line-height: 20px; padding: 10px 0">
			扫一扫或轻按3秒识别二维码<br/>关注我的微信
		</div>
	</div>
	<div class="theme-popover-mask"></div>
	<script>
	jQuery(document).ready(function($) {
		$('.reg_bot a').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		$('.theme-poptit .close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})

	});

	$('.theme-popover .bond_close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})
</script>

<%@ include file="../mobile/comm/bott.jsp"%>
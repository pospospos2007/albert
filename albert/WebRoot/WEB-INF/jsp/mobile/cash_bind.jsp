<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>

<body>

	<div class="login">
		<h2>
			<a href="user.htm" title="账户中心" class="fl"><img
				src="images/poi_lt.jpg" /></a>提现
		</h2>
	</div>

	<div class="bind_card">
		<img src="images/bind_card.jpg" /><br /> 尾号8425储蓄卡
	</div>
	<div class="login_f rechar_f">
		<form action="" method="post">
			<dl style="width: 25%">
				<dd style="background: #e6eaed">账户金额</dd>
				<dd>提现金额</dd>
			</dl>
			<dl style="width: 75%">
				<dd style="color: #f4406f; background: #e6eaed">￥0.00元</dd>
				<dd>
					<input type="text" name="recharge" placeholder="请输入充值金额" />
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
	<div class="reg_bot tc">每笔提现收取2元手续费</div>
	<div class="login_f_btn">提现</div>

	<div class="reg_bot tl" style="width: 85%">
		温馨提示
		<p>&nbsp;</p>
		<p>为了您的资金安全。。。。。。为了您的资金安全。。。</p>
		<p>为了您的资金安全。。。。。。。。。</p>
		<p>为了您的资金安全。。。。。。。。。</p>
	</div>



	<div class="theme-popover">
		<div class="theme-poptit">提示</div>
		<p>提现成功</p>
		<a href="user.htm"><div class="index_login">返回账户中心</div></a>
	</div>
	<div class="theme-popover-mask"></div>
	<script>
	jQuery(document).ready(function($) {
	$('.login_f_btn').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});

	})
</script>

	<%@ include file="./comm/bott.jsp"%>
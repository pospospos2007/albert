<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp"%>
<body>
	<div class="user_body_bg"><div class="user_body">
	<div class="user_l fl">	
		<div class="user_msg tr"><span></span></div>
		<p style="line-height: 18px; text-align: center">
			<span style="color: #00ffe5">${fn:substring(loginUser.nickName,0,3)}****${fn:substring(loginUser.nickName,7,-1)}</span>
			<%-- <span style="color: #00ffe5">${loginUser.mobile}</span> --%>
		</p>
		<p class="blank5"></p>

		<p style="font-size: 14px; text-align: left">账户金额(元)</p>

		<div class="login_m user_m">
			<dl>
				<dt  id="cash">0.00</dt>
				<dd>
					投资总额<span id="amount">0.00</span>
			    </dd>
				<dd>
					冻结资金<span id="frozen">0.00</span>
				</dd>
				<dd>
					待收本金<span id="toCollectPrincipal">0.00</span>
				</dd>
				<dd>
					待收收益<span id="toCollectInterest">0.00</span>
				</dd>
			</dl>
			<a style="background: #41b1d6" href="<%=request.getContextPath() %>/toMobileRecharge"><span><img
					src="<%=request.getContextPath() %>/mobile/images/icon_recharge.jpg" /></span>充值</a> 
			<a href="<%=request.getContextPath() %>/toMobileDrow"><span><img
					src="<%=request.getContextPath() %>/mobile/images/icon_cash_u.png" /></span>提现</a>
			<p class="clear"></p>
		</div>

		<div class="user_set">
			<dl>
				<dd>
					<a href="<%=request.getContextPath() %>/investRecordMobile"><img src="<%=request.getContextPath() %>/mobile/images/user_i1.png" /><br />投资记录</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath() %>/repayingList"><img src="<%=request.getContextPath() %>/mobile/images/user_i2.png" /><br />回款计划</a>
				</dd>
				<dd style="border-right: none">
					<a href="<%=request.getContextPath() %>/mobile/moneyFlow.jsp"><img src="<%=request.getContextPath() %>/mobile/images/user_i3.png" /><br />资金流水</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath() %>/mobile/bondAssign.jsp"><img src="<%=request.getContextPath() %>/mobile/images/user_i4.png" /><br />债权认购</a>
				</dd>
				<dd>
					<div id='inviteBtn' ><img src="<%=request.getContextPath() %>/mobile/images/user_i5.png" /><br/>邀请好友</div>
				</dd>
				<dd style="border-right: none">
					<a href="<%=request.getContextPath() %>/toAboutPage?type=aboutus"><img src="<%=request.getContextPath() %>/mobile/images/user_i6.png" /><br />关于我们</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath() %>/mobile/contact.jsp"><img src="<%=request.getContextPath() %>/mobile/images/user_i7.png" /><br />联系我们</a>
				</dd>
				<dd>
					<a href="<%=request.getContextPath() %>/toMessagePage"><img style="margin: 13px auto"
						src="<%=request.getContextPath() %>/mobile/images/user_i8.png" /><br />消息中心</a>
				</dd>
				<dd style="border-right: none">
					<a href="<%=request.getContextPath() %>/accountInstall"><img src="<%=request.getContextPath() %>/mobile/images/user_i9.png" /><br />账户设置</a>
				</dd>
				<p class="clear"></p>
			</dl>
		</div>
	</div>
	
	
	<div class="user_r fl">
		<iframe src="<%=request.getContextPath() %>/index"  marginheight="0" marginwidth="0" 
		height="100%" allowtransparency="true" scrolling="no" noResize 
		hspace="0" vspace="0" pixelHeight="auto" frameborder="0" 
		scrolling="no"></iframe>
		<a href="<%=request.getContextPath() %>/index"><div class="float_b"></div></a>
	</div><div class="clear"></div>
	</div>
<script src="/themes/bsd/js/Chart.js"></script>
	<script>
	$(function() {
	    var d=new Date();
	    $.getJSON("<%=request.getContextPath() %>/queryForAccBalance.action?v="+d.getTime()+Math.random(), function (data){
	    	var balance = data.balance;
	    	var user = data.user;
//	    	$("#cash").html((user.cash).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
//	   	$("#frozen").html((user.frozenBiddingCash+user.frozenWithDrawCash).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
	    	$("#amount").html((balance.totalInvestAmout+balance.boughtCreditRights).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"元");
	    	$("#toCollectPrincipal").html((balance.toCollectPrincipal).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"元");
	    	$("#toCollectInterest").html((balance.toCollectInterest).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"元");
	    });
	    
	    
	    $.getJSON("<%=request.getContextPath()%>/pay/chinapnrj/start-QueryMoney.action",{'payReqFromApp.userId':${user.userId},'version':d.getTime()},function (data){
	   	 if(data == null ){
	   		$("#cash").html('0.00');
	   		$("#frozen").html('0.00');
	   	 }else{
	   	 $('#cash').html(data['availableAmount']);
	   		if(data['availableAmount']){
	   			$('#cash').html((data['availableAmount']).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
	   		}
	   		$('#frozen').html(data['freezeAmount']);	
	   		if(data['freezeAmount']){
	   			$('#frozen').html((data['freezeAmount']).replace(/\d(?=(\d{3})+\.)/g, '$&,')+"元");		
	   		}		 
	   	 }
	    });
	    //开户提醒
	    $.getJSON('<%=request.getContextPath() %>/payAccountReady.action?version='+d.getTime(), function(data) {
	    	if (!data) {
	    		showDialogAccount("您好，您需要在第三方托管平台上进行注册，才可申请充值提现。点击“开户”，立即开通。");
	    	} 
	    });
	  });
	 jQuery(document).ready(function($) {
		/*$('.user_set dl dd a').bind('touchstart',function(){var index = $('.user_set dl dd').index($(this).parent());var index_p = index + 1;
			$('.user_set dl dd').eq(index).children('a').children('img').attr('src','images/user_i'+index_p+'_r.png');
			$(this).css('color','#ff3578');
		})
		
		$('.user_set dl dd a').mouseup(function(){var index = $('.user_set dl dd').index($(this).parent());var index_p = index + 1;
			$('.user_set dl dd').eq(index).children('a').children('img').attr('src','images/user_i'+index_p+'.png');
			$(this).css('color','#ffffff');
		})*/
		//弹出复制链接对话框，并生成邀请码注册地址
		$("#inviteBtn").click(
				function() {
					$.post("<%=request.getContextPath() %>/copyLink.action", function(data) {
						//alert(data);
						if (data != "") {
							<c:if test="${userVo.referrerCode!=null&&userVo.referrerCode!=''}">
							var referrerCode='${userVo.referrerCode}';
							</c:if>
							<c:if test="${userVo.referrerCode==null||userVo.referrerCode==''}">
							var referrerCode='${userVo.mobile}';
							</c:if>

							location.href="<%=request.getContextPath() %>/inviteFriends?invitedCode="+ data+"&referrerCode="+referrerCode ;
						}
					}, "json");
				});

})
 


 
</script>
</div>
<%@ include file="/mobile/comm/bott.jsp"%>
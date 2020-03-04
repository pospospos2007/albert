<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<script>
	jQuery(document).ready(function($) {
		
		$('.search_c dl > dd').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popove-c').fadeIn(100);
			var index = $('.search_c dl dd').index(this);
			$('.theme-popove > ul').eq(index).slideDown(200).siblings().hide();
		});
		$('.theme-popove > ul li').click(function(){
			$('.theme-popover-mask').fadeOut(100);$('.theme-popove-c').fadeOut(100);
			$('.theme-popove > ul').slideUp(200);
			$(this).parent();
			//修改类别显示文本
			$('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).html($(this).text());
		/* 	if ($('.theme-popove > ul li').index(this) % 5 > 0) { */
			//修改项目金额的类别和名称
			    if ('amount'==$(this).parent().attr("id")) {
			    	 $("#priceRangeIndex").attr("value",$(this).attr("value"));
			    	 $("#priceRangeName").attr("value",$(this).text());
			    	 
			    	 
			    }else if ('state'==$(this).parent().attr("id")) {
			    	 $("#loanStatusIndex").attr("value",$(this).attr("value"));
			    	 $("#loanStatusName").attr("value",$(this).text());
			    	 
			    	 
			    }else if ('time'==$(this).parent().attr("id")) {
			    	 $("#termCountIndex").attr("value",$(this).attr("value"));
			    	 $("#termCountName").attr("value",$(this).text());
			    	 
			    	 
			    }else if ('interest'==$(this).parent().attr("id")) {
			    	 $("#interestRangeIndex").attr("value",$(this).attr("value"));
			    	 $("#interestRangeName").attr("value",$(this).text());
			    	 
			    	 
			    }
			    
// 				if($(this).text()=='全部'){
// 			    	 $("#priceRangeIndex").attr("value",'0');
// 			     }
// 			     if($(this).text()=='10万以下'){
// 			    	 $("#priceRangeIndex").attr("value",'1');
// 			     }
// 			     if($(this).text()=='10-20万'){
// 			    	 $("#priceRangeIndex").attr("value",'2');
// 			     }
// 			     if($(this).text()=='20-50万'){
// 			    	 $("#priceRangeIndex").attr("value",'3');
// 			     }
// 			     if($(this).text()=='50万以上'){
// 			    	 $("#priceRangeIndex").attr("value",'4');
// 			     }
// 			     if($(this).text()=='全部'){
// 			    	 $("#loanStatusIndex").attr("value",'300');
// 			     }
// 			     if($(this).text()=='预热中'){
// 			    	 $("#loanStatusIndex").attr("value",'301');
// 			     }
// 			     if($(this).text()=='投标中'){
// 			    	 $("#loanStatusIndex").attr("value",'300');
// 			     }
// 			     if($(this).text()=='满标'){
// 			    	 $("#loanStatusIndex").attr("value",'400');
// 			     }
// 			     if($(this).text()=='还款中'){
// 			    	 $("#loanStatusIndex").attr("value",'500');
// 			     }
// 			     if($(this).text()=='已还款'){
// 			    	 $("#loanStatusIndex").attr("value",'600');
// 			     }
// 			     if($(this).text()=='全部'){
// 			    	 $("#termCountIndex").attr("value",'0');
// 			     }
// 			     if($(this).text()=='1个月以下'){
// 			    	 $("#termCountIndex").attr("value",'1');
// 			     }
// 			     if($(this).text()=='1-6个月'){
// 			    	 $("#termCountIndex").attr("value",'2');
// 			     }
// 			     if($(this).text()=='6-12个月'){
// 			    	 $("#termCountIndex").attr("value",'3');
// 			     }
// 			     if($(this).text()=='12-18个月'){
// 			    	 $("#termCountIndex").attr("value",'4');
// 			     }
// 			     if($(this).text()=='全部'){
// 			    	 $("#interestRangeIndex").attr("value",'0');
// 			     }
// 			     if($(this).text()=='10%以下'){
// 			    	 $("#interestRangeIndex").attr("value",'1');
// 			     }
// 			     if($(this).text()=='10%-12%'){
// 			    	 $("#interestRangeIndex").attr("value",'2');
// 			     }
// 			     if($(this).text()=='12%-15%'){
// 			    	 $("#interestRangeIndex").attr("value",'3');
// 			     }
// 			     if($(this).text()=='15-18%'){
// 			    	 $("#interestRangeIndex").attr("value",'4');
// 			     }
			     
			  $('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).attr('style','color:red')
			/* }
			else $('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).attr('style','color:#656565'); */
		});
		
		$('.theme-popove-c p').click(function(){
			$('.theme-popove-c').fadeOut(100);$('.theme-popove > ul').slideUp(200);
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		});

		$('.theme-poptit .close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
	});
	$('.login_f_btn').click(function(){
/* 		alert($('#loanStatusIndex').val());
		alert($('#loanStatusName').val()); */
		$('#serachFrom').submit();
	});

})
</script>
<body style="background:#ffffff">
<div class="login search_t">
	<h2>
		<a href="<%=request.getContextPath() %>/" title="返回" class="fl"><img
			src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>搜索
	</h2>
</div>
<div class="search_c tc">
	<form action="<%=request.getContextPath() %>/findLoanList" id="serachFrom" method="post">
            <input type="hidden" name="priceRangeIndex"  id="priceRangeIndex" value="0" />
            <input type="hidden" name="loanStatusIndex"  id="loanStatusIndex" value="300" />
            <input type="hidden" name="termCountIndex" id="termCountIndex" value="0" />
            <input type="hidden" name="interestRangeIndex" id="interestRangeIndex" value="0"/>
            <input type="hidden" name="priceRangeName"  id="priceRangeName" value="全部" />
            <input type="hidden" name="loanStatusName"  id="loanStatusName" value="全部" />
            <input type="hidden" name="termCountName"  id="termCountName" value="全部" />
            <input type="hidden" name="interestRangeName" id="interestRangeName" value="全部"/>
		<img src="<%=request.getContextPath() %>/mobile/images/search_ban.jpg"
			style="margin: 10px auto 0; width: 59%" />
		<dl>
			<dd>
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif"/></b><span>项目金额</span><i>全部</i>
			</dd>
			<dd>
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></b><span>项目状态</span><i>全部</i>
			</dd>
			<dd>
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></b><span>投资期限</span><i>全部</i>
			</dd>
			<dd style="border-bottom: none">
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></b><span>预计收益</span><i>全部</i>
			</dd>
		</dl>
		<div class="login_f_btn">搜索项目</div>
	</form>
</div>

<div class="theme-popove">
	<ul id="amount" value='10'>
		<li class="default" value='0'>全部</li>
		<li value='1'>10万以下</li>
		<li value='2'>10-20万</li>
		<li value='3'>20-50万</li>
		<li value='4'>50万以上</li>
	</ul>

	<ul id="state">
		<li class="default" value="0">全部</li>
		<li value='1'>预热中</li>
		<li value='2'>投标中</li>
		<li value='3'>满标</li>
		<li value='4'>还款中</li>
	</ul>

	<ul id="time">
		<li class="default" value='0'>全部</li>
		<li value='1'>1个月以下</li>
		<li value='2'>1-6个月</li>
		<li value='3'>6-12个月</li>
		<li value='4'>12-18个月</li>
	</ul>

	<ul id="interest">
		<li class="default" value='0'>全部</li>
		<li value='1'>10%以下</li>
		<li value='2'>10%-12%</li>
		<li value='3'>12%-15%</li>
		<li value='4'>15-18%</li>
	</ul>

</div>

<div class="theme-popover">
	<div class="theme-poptit">
		<a href="javascript:;" title="关闭" class="close">×</a>提示
	</div>
	<p>请选择投资金额</p>
</div>

<div class="theme-popove-c">
	<p>取消</p>
</div>

<div class="theme-popover-mask"></div>



<%@ include file="./comm/bott.jsp"%>
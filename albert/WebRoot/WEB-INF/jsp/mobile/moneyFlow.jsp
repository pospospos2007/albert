<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<body style="background:#ffffff">
<div class="login search_t">
	<h2>
		<a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl">
		<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>资金流水
	</h2>
</div>
<div class="search_c tc">
	<form action="<%=request.getContextPath() %>/sessionRecord" method="post" id="listform" name="listform" role="form">
		    <input type="hidden" name="currentPage" value="" />
            <input type="hidden" name="type" id="type" value=""/>
              <input type="hidden" name="typeName" id="typeName" value=""/>
            <input type="hidden" name="time" id="timeScope" value=""/>
             <input type="hidden" name="timeScopeName" id="timeScopeName" value=""/>
		<img src="<%=request.getContextPath() %>/mobile/images/money_f.jpg" style="margin: 10px auto 0; width: 59%" />
		<dl>
			<dd>
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></b><span>流水类型</span><i>全部</i>
			</dd>
			<dd style="border-bottom: none">
				<b><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></b><span>时间范围</span><i>全部</i>
			</dd>
		</dl>
		<div class="login_f_btn">点击查询</div>
	</form>
</div>

<div class="theme-popove">
	<ul id="sessionType">
		<li class="default" value="0">所有流水</li>
		<li value="220">回收本金</li>
		<li value="230">回收利息</li>
		<li value="1350">投标冻结</li>
		<li value="103">充值</li>
		<li value="1100">提现</li>
	</ul>

	<ul id="dateTime">
		<li class="default" value="-1">全部</li>
		<li value="0">最近7天</li>
		<li value="1">1个月</li>
		<li value="2">2个月</li>
		<li value="3">3个月</li>
	</ul>

</div>

<div class="theme-popove-c">
	<p>取消</p>
</div>
<div class="theme-popover-mask" style="top: 46px"></div>
<script>
	jQuery(document).ready(function($) {
		$('.search_c dl > dd').click(function(){
			$('.theme-popover-mask').fadeIn(100);
				var index = $('.search_c dl dd').index(this);
				$('.theme-popove > ul').eq(index).slideDown(200).siblings().hide();$('.theme-popove-c').show();
		});
		
		$('.theme-popove > ul li').click(function(){
			$('.theme-popover-mask').fadeOut(100);$('.theme-popove-c').fadeOut(100);
			$('.theme-popove > ul').slideUp(200);
			$('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).html($(this).text());
			//if ($('.theme-popove > ul li').index(this) % 5 > 0 ){
			if ('sessionType'==$(this).parent().attr("id")) {
			          $("#type").attr("value",$(this).attr("value"));
			    	  $("#typeName").attr("value",$(this).text());
			}
			if ('dateTime'==$(this).parent().attr("id")) {
			          $("#timeScope").attr("value",$(this).attr("value"));
			    	  $("#timeScopeName").attr("value",$(this).text());
			}
				$('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).attr('style','color:red')
			//}
			//else {
				//$('.search_c dl dd i').eq($('.theme-popove ul').index($(this).parent())).attr('style','color:#656565');
			//}
		});
		
		$('.theme-popove-c p').click(function(){
			$('.theme-popove-c').fadeOut(100);
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popove > ul').slideUp(200);
		});
		
		$('.theme-poptit .close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
	})
	$('.login_f_btn').click(function(){
	     if($("#type").val()==""){
	        $("#type").attr("value",'0');
	         $("#typeName").attr("value",'所有流水');
	     }
	     if($("#timeScope").val()==""){
	        $("#timeScope").attr("value",'-1');
	        $("#timeScopeName").attr("value",'全部');
	        
	     }
		$('#listform').submit();
	});
	
})
</script>

<%@ include file="/mobile/comm/bott.jsp"%>
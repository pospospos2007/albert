<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body>
	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/bondAssign.jsp" title="返回" class="fl">
			<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>转让中债权
		</h2>
	</div>
<c:forEach items="${page.voList}" var="data" varStatus="i">
 <div class="index_c m_search"><h3><a >${data.loanTitle}</a></h3>
	<dl>
		<dd><em class="wid6">待&nbsp;收&nbsp;利&nbsp;息:</em>&nbsp;<fmt:formatNumber value="${data.soldInterest}" pattern="#,##0.00"/>元</dd>
		<dd class="ml2"><em class="wid4">年化收益:</em>&nbsp;${data.loanAnnualInterestRate}%</dd>
		<dd><em class="wid6">转&nbsp;出&nbsp;价&nbsp;格:</em>&nbsp;<fmt:formatNumber value="${data.soldPrice}" pattern="#,##0.00"/>元</dd>
		<dd class="ml2"><em class="wid4">剩余期限:</em>&nbsp;${data.leftTermCount}期</dd>
		<dd ><em class="wid6">下一还款日:</em>&nbsp;<fmt:formatDate value="${data.nextRepayDate}" pattern="yyyy-MM-dd "/></dd>
		<dd class="ml2"><em class="wid4">手&nbsp;&nbsp;续&nbsp;&nbsp;费:</em>&nbsp;${data.soldCommission}元</dd>
		<div class="clear"></div>
	</dl>	  
	<a class="btn_recall"  data-value="${data.id}"  id="${data.id}" >撤&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</a>
 </div>
</c:forEach>

<c:if test="${page.recordCount>10}">
	<div id="starfooter">下拉加载更多...</div>
</c:if>

<div class="theme-popover">
     <div class="theme-poptit">
                   撤销转让
     </div><p> 确定要撤销转出该债权</p>
	 <div class="bond_ass_btn">
		<ul><li id="cancel" style="margin-right:26%">取消</li><a id="convert" ><li>确定</li></a></ul>
	 </div>	
</div>
<div class="theme-popover-mask"></div>
<script>
	 jQuery(document).ready(function($) {
		var currentPage=1,winScroll=0;
		//下拉加载
		$(document).on("scrollstart",function(){
			var winHeight = $(window).height();       // 窗口高度
			var allHeight = $(document).height();     // 文档高度
			var allScroll = $(document).scrollTop();  //滚动前scroll
			//判断当前底部是否显示和数据添加
			if(allScroll+winHeight>=allHeight-10&&allScroll >= winScroll){
				setTimeout(addText,0);
			}
		});
		
		$(document).on("scrollstop",function(){
			winScroll = $(document).scrollTop();      //滚动后scroll
		})
		
		function addText(){
			//添加条目
			var str;
			//查看更多明星标
			currentPage++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/listMobile1.action", //请求路径   
			       data:{"index":currentPage}, 
			       success:function(data){
			    	    if(data.length==0){
			    		   $('#starfooter').remove();
			    		   return;
			    	   } 
			    	 
			    	   $(data).each(function(index, element) {
			    	    str = '<div class="index_c m_search"><h3><a >'+element.loanTitle+'</a></h3>'+
								'<dl>'+
									'<dd><em class="wid6">待&nbsp;收&nbsp;利&nbsp;息:</em>&nbsp;'+Number(element.soldInterest).toFixed(2)+'元</dd>'+
									'<dd class="ml2"><em class="wid4">年化收益:</em>&nbsp;'+Number(element.loanAnnualInterestRate).toFixed(2)+'%</dd>'+
									'<dd><em class="wid6">转&nbsp;出&nbsp;价&nbsp;格:</em>&nbsp;'+Number(element.soldPrice).toFixed(2)+'元</dd>'+
									'<dd class="ml2"><em class="wid4">剩余期限:</em>&nbsp;'+element.leftTermCount+'期</dd>'+
									'<dd ><em class="wid6">下一还款日:</em>&nbsp;'+element.formatNextRepayDate+'</dd>'+
									'<dd class="ml2"><em class="wid4">手&nbsp;&nbsp;续&nbsp;&nbsp;费:</em>&nbsp;'+Number(element.soldCommission).toFixed(2)+'元</dd>'+
									'<div class="clear"></div>'+
								'</dl>'+	  
								'<a class="btn_recall"  data-value="'+element.id+'"  id="'+element.id+'" >撤&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回</a>'+
							 '</div>'; 
			    		  
			    		 	$("#starfooter").before(str);
							//缓显
							$(".index_c m_search").fadeIn(1000);
			    	   })
			       }
			      
			})
		}
		
	$('.btn_recall').click(function(){
	     var url="<%=request.getContextPath() %>/unSell?loanInvestorId="+$(this).attr("id");
	     $('#convert').attr("href",url);
		$('.theme-popover-mask').fadeIn(100);
		$('.theme-popover').slideDown(200);
	});
	$('#cancel').click(function(){
		$('.theme-popover-mask').fadeOut(100);
		$('.theme-popover').slideUp(200);
	})
	
	}); 
</script>
<%@ include file="/mobile/comm/bott.jsp"%>
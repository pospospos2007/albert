<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body>
	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/bondAssign.jsp" title="返回" class="fl">
			<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>已认购债权
		</h2>
	</div>
	<c:forEach items="${page.voList}" var="data" varStatus="i">	
	 <div class="index_c m_search"><h3><a >${data.title}</a></h3>
		<div class="debt_ass_l">	  
		  <p><em>买入价格:</em>&nbsp;${data.tradePrice} 元</p>
		  <p><em>交易时间:</em>&nbsp;<fmt:formatDate value="${data.tradeTime}" pattern="yyyy-MM-dd HH:mm"/></p>	  
		</div>
	   <div class="debt_ass_l debt_ass_r tc"><p class="blank5"></p><p><b>${data.loanAnnualInterestRate}%</b></p>
	   </div><div class="clear"></div>
    </div>
	</c:forEach>
	<c:if test="${page.recordCount>10}">
		<div id="starfooter">下拉加载更多...</div>
	</c:if>
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
			       url:"${base}/listMobile4.action", //请求路径   
			       data:{"index":currentPage}, 
			       success:function(data){
			    	    if(data.length==0){
			    		   $('#starfooter').remove();
			    		   return;
			    	   } 
			    	 
			    	   $(data).each(function(index, element) {
			    	   
			    	   	str = '<div class="index_c m_search"><h3><a >'+element.title+'</a></h3>'+
									'<div class="debt_ass_l">'+	  
									  '<p><em>买入价格:</em>&nbsp;'+element.tradePrice+'元</p>'+
									  '<p><em>交易时间:</em>&nbsp;'+element.formatTradeTime+'</p>'+	  
									'</div>'+
								   '<div class="debt_ass_l debt_ass_r tc"><p class="blank5"></p><p><b>'+Number(element.loanAnnualInterestRate).toFixed(2)+'%</b></p>'+
								   '</div><div class="clear"></div>'+
							   '</div> '; 
							   
			    		 	$("#starfooter").before(str);
							//缓显
							$(".index_c m_search").fadeIn(1000);
			    	   })
			       }
			      
			})
		}
	}); 
</script>	
<%@ include file="/mobile/comm/bott.jsp"%>
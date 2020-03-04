<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body>

	<div class="login search_t">
		<h2>
			<a data-ajax="false" href="<%=request.getContextPath() %>/mobile/moneyFlow.jsp" title="资金流水" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>资金流水
		</h2>
	</div>
	
	<p class="search_t_t">流水类型：<b style="color:red">${type}</b>,时间范围：<b style="color:red">${time}</b></p>
      <c:forEach items="${datas}" var="data">
    <div class="index_c m_search">
		<h3><a data-ajax="false">${data.record.loanTitle}</a></h3>
		<ul>
			<c:if test="${data.record.tradeType<1000 }"><li >收入：<span><fmt:formatNumber value="${data.record.amount }"  pattern="${moneyexp}"></fmt:formatNumber>元</span></li></c:if>
			<c:if test="${data.record.tradeType>1000 }"><li >支出：<span><fmt:formatNumber value="${data.record.amount }"  pattern="${moneyexp}"></fmt:formatNumber>元</span></li></c:if>
			<li >类型：${fn:substring(data.mtype, 0, 4)}</li>
			<li  style="width: 100%">时间：<fmt:formatDate value="${data.record.tradeTime }" pattern="yyyy-MM-dd HH:mm"/></li>
			<li style="width: 100%"  >备注：${data.record.tradeComment}</li>
			<div class="clear"></div>
		</ul>
	</div>
    </c:forEach>
    <c:if test="${fn:length(datas)>2}">
    <div id="claimsfooter">下拉加载更多...</div>
    </c:if>
    <script>

$(document).ready(function(){
	var i=0,j=0,k=0,ii=0,jj=0,kk=0,actionIndex=0,winScroll=0;

	//下拉加载
	$(document).on("scrollstart",function(){
		if(actionIndex) return;
		var winHeight = $(window).height();       // 窗口高度
		var allHeight = $(document).height();     // 文档高度
		var allScroll = $(document).scrollTop();  //滚动前scroll
		//判断当前底部是否显示和数据添加
		if(allScroll+winHeight>=allHeight-10&&allScroll >= winScroll){
			actionIndex=1;
			setTimeout(addText,0);
		}
	});
	$(document).on("scrollstop",function(){
		winScroll = $(document).scrollTop();      //滚动后scroll
	})
	
	//$("#claimsfooter").on("click",addText);
	function addText(){
		//添加条目
		var str;
		//查看更多资金记录
		//if($('#renGou').hasClass('on')){
			k=10+kk*5;
			kk++;
		 $.ajax({  
			   type:"POST", //请求方式  
		       url:"${base}/findNextFinancialRecords.action", //请求路径   
		       data:{"index":k,"typeSearch":${typeSearch},"timeSearch":${timeSearch}}, 
		       success:function(data){
		    	   if(data.length==0){
		    		   $('#claimsfooter').remove();
		    		   return;
		    	   }
		    	   $(data).each(function(index, element) {
		    		   
		    		    str = "<div class='index_c m_search displayN'><h3><a data-ajax='false'>";
		    		    
		    		    str = str + element.record.loanTitle +"</a></h3><ul>";
		    		   
		    		    
    		    	    if(element.record.tradeType<1000){
		    		    	str = str + "<li >收入：<span>" + element.record.amount +"元</span></li>";
    		    	    }
		    		    if(element.record.tradeType>1000){
		    		    	str = str + "<li >支出：<span>" + element.record.amount +"元</span></li>";	
		    		    }
		    			
						str = str + "<li >类型：" + element.mtype.substring(0,4) + "</li>";
						
						str = str + "<li  style='width: 100%'>时间：" +element.record.formatTradeTime +"</li>";
						
						str = str +"<li style='width: 100%'>备注：" +element.record.tradeComment +"</li>";
						
						str = str +"<div class='clear'></div></ul></div>";
						
		    			$("#claimsfooter").before(str);
						//缓显
						$(".displayN").fadeIn(1000);
		    	   })
		       }
		})
		//}
		actionIndex = 0;
	}
});
</script>
<%@ include file="/mobile/comm/bott.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/mobile/comm/head.jsp"%>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body style="background:#ffffff">
<div class="login search_t">
	<h2>
		<a data-ajax="false" href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl">
		<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>回款计划
	</h2>
</div>
<div class="pay_plan return_plan" >
	<dl style="width:8%">
		<dt class="leftLine"></dt>
		<c:forEach items="${repaying}" var="data" begin="0" end="9" varStatus="i">
			<dd></dd>
		</c:forEach>
	</dl>
	<dl>
		<dt class="midLine">标题</dt>
		<c:forEach items="${repaying}" var="data" begin="0" end="9" varStatus="i">
			<c:if test="${fn:length(data.title)>10}">
			<a data-ajax="false" href="<%=request.getContextPath() %>/mobile/d_return_plan.jsp?borrowerNickname=${data.borrowerNickname }&nextPhase=${data.nextPhasePrincipal + data.nextPhaseInterest}&productId=${data.productId}&termCount=${data.termCount}&leftTermCount=${data.leftTermCount}&investAmount=${data.investAmount}"><dd border-bottom-color: #ff0000> ${fn:substring(data.title, 0, 9)} ...</dd></a>
			</c:if>
			<c:if test="${fn:length(data.title)<=10}">
			<a data-ajax="false" href="<%=request.getContextPath() %>/mobile/d_return_plan.jsp?borrowerNickname=${data.borrowerNickname }&nextPhase=${data.nextPhasePrincipal + data.nextPhaseInterest}&productId=${data.productId}&termCount=${data.termCount}&leftTermCount=${data.leftTermCount}&investAmount=${data.investAmount}"><dd border-bottom-color: #ff0000>  ${data.title}</dd></a>
			</c:if>
		</c:forEach>
	</dl> 
	
	<%--<dl>
		<dt>标题</dt>
		<c:forEach items="${repaying}" var="data">
				<a href="<%=request.getContextPath() %>/mobile/d_return_plan.jsp?borrowerNickname=${data.borrowerNickname }&nextPhase=${data.nextPhasePrincipal + data.nextPhaseInterest}&productId=${data.productId}&termCount=${data.termCount}&leftTermCount=${data.leftTermCount}&investAmount=${data.investAmount}"><dd border-bottom-color: #ff0000>${fn:substring(data.title, 0, 9)} ...</dd></a>
		</c:forEach>
	</dl> 

	--%>
	<dl>
		<dt class="rightLine">下次还款时间</dt>
		<c:forEach items="${repaying}" var="data" begin="0" end="9" varStatus="i">
			<dd>
				<a data-ajax="false" href="<%=request.getContextPath() %>/mobile/d_return_plan.jsp?borrowerNickname=${data.borrowerNickname }&nextPhase=${data.nextPhasePrincipal + data.nextPhaseInterest}&productId=${data.productId}&termCount=${data.termCount}&leftTermCount=${data.leftTermCount}&investAmount=${data.investAmount}"><img src="<%=request.getContextPath() %>/mobile/images/poi_gt.jpg" /></a><fmt:formatDate value="${data.nextRepayDate }" pattern="yyyy-MM-dd"/>
			</dd>
		</c:forEach>
	</dl>
 <c:if test="${fn:length(repaying)>10}">
<div id="claimsfooter">下拉加载更多...</div>
</c:if>
</div>
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
		var midStr;
		var rightStr;
		//查看更多回款计划
		//if($('#renGou').hasClass('on')){
			k=10+kk*5;
			kk++;
		 $.ajax({  
			   type:"POST", //请求方式  
		       url:"${base}/findNextRepaymentPlan.action", //请求路径   
		       data:{"index":k}, 
		       success:function(data){
		    	   if(data.length==0){
		    		   $('#claimsfooter').remove();
		    		   return;
		    	   }
		    	   $(data).each(function(index, element) {
		    		   var calculateRs = Number(element.nextPhasePrincipal)+Number(element.nextPhaseInterest);
		    		   var title = element.title;
		    		   if(title.length>10){
		    			   title = title.substring(0,9)+"...";
		    		   }
		    		  
		    		   midStr = "<a data-ajax='false' href='${base}/mobile/d_return_plan.jsp?borrowerNickname="+element.borrowerNickname+"&nextPhase="+calculateRs+"&productId="+element.productId+"&termCount="+element.termCount+"&leftTermCount="+element.leftTermCount+"&investAmount="+element.investAmount+"'>"+"<dd class='displayN'> "+title+"</dd>"+"</a>";
		    		   
		    		   rightStr = "<dd class='displayN'>"+"<a data-ajax='false' href='${base}/mobile/d_return_plan.jsp?borrowerNickname="+element.borrowerNickname+"&nextPhase="+calculateRs+"&productId="+element.productId+"&termCount="+element.termCount+"&leftTermCount="+element.leftTermCount+"&investAmount="+element.investAmount+"'>"+"<img src='${base}/mobile/images/poi_gt.jpg'/></a>"+element.formatNextRepayDate+"</dd>";
						$(".leftLine").parent().append("<dd class='displayN'></dd>");
						$(".midLine").parent().append(midStr);
						$(".rightLine").parent().append(rightStr);
						
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
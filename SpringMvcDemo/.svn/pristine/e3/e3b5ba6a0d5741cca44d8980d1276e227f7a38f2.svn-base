<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="../mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<body>

	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/index" title="返回" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>${theme.theme}
		</h2>
	</div>

	<p class="search_t_t">楼主${theme.ip}
	</p>
	<p>${theme.content}</p>
	<c:if test="${empty messages }"> <div class="index_c"><p class="search_t_t">暂无回复</p></div></c:if>
	 <c:forEach items="${messages}" var="message"  varStatus="i">
          	  
          	   <div class="index_c">
			  <h3>	<a href="#">${message.message}</a></h3>
			  <p style="line-height:36px">  
			  
         	 <a href="#" style="background: #cbccce">${i.count}楼</a>
         	 ${message.ip}
	                          发表于  <fmt:formatDate value="${message.addTime }" pattern="yyyy-MM-dd HH:mm"/>
			  </p>
<!-- 			  <div class="index_c_per"><div class="index_c_per bg" style="width:${data.biddingAmount/data.amount*100}%"></div></div> -->
<!-- 			  <p><fmt:formatNumber value="${data.beginAmount}"  pattern="#,#00.00#"></fmt:formatNumber>元起投 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余<fmt:formatNumber value="${data.amount-data.biddingAmount}"  pattern="#,#00.00#"></fmt:formatNumber>元/总额<fmt:formatNumber value="${data.amount}"  pattern="#,#00.00#"></fmt:formatNumber>元</p> -->
			
			 </div>
          	  </c:forEach>
	<%@ include file="./comm/bott.jsp"%>
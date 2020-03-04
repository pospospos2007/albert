<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<body style="background:#ffffff">
<div class="login search_t">
	<h2>
		<a href="<%=request.getContextPath() %>/mobileLoanDetail?loanId=${loanId}&id=${loanInvestorId}&item=back" title="返回" class="fl"><img
			src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>收款计划
	</h2>
</div>

<div class="pay_plan">
	 <dl style="width:21%;"><dt>收款日期</dt>
	 <c:forEach items="${repayMentList}" var="data" >
	    <dd><fmt:formatDate value="${data.repayMentDate}" pattern="yyyy-MM-dd" /></dd>
	 </c:forEach>
  </dl>  
 
  
  <dl><dt>应收利息(元)</dt>
  <c:forEach items="${repayMentList}" var="dataInterest" >
	    <dd style="text-align:center"><fmt:formatNumber value="${dataInterest.plannedTermInterest}" pattern="##0.00"></fmt:formatNumber></dd>
	 </c:forEach>
  </dl>  
  <dl><dt>应收本金(元)</dt>
  <c:forEach items="${repayMentList}" var="dataPrincipal" >
	    <dd><fmt:formatNumber value="${dataPrincipal.plannedTermPrincipal}" pattern="##0.00"></fmt:formatNumber></dd>
  </c:forEach>
  </dl>  
  <dl><dt>应收罚息(元)</dt>
   <c:forEach items="${repayMentList}" var="dataDueInterest" >
	    <dd style="text-align:center">${dataDueInterest.overDueInterest}</dd>
	 </c:forEach>
  </dl>
   <dl style="width:13%;text-align:center"><dt>状态</dt>
     <c:forEach items="${repayMentList}" var="dataStatus" >
	    <dd>${dataStatus.status}</dd>
	 </c:forEach>
  </dl>
</div>
<%@ include file="/mobile/comm/bott.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="net.zkbc.p2p.api.model.Banner"%>
<%@ page import="com.zkbc.core.consts.LoanDef" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<body>

	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/search.jsp" title="返回" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>搜索结果
		</h2>
	</div>

	<p class="search_t_t">金额:<b style="color:red">${priceRangeName}</b>,状态:<b style="color:red">${loanStatusName}</b>,期限:<b style="color:red">${termCountName}</b>,收益:<b style="color:red">${interestRangeName}</b></p>
	
	<c:if test="${empty loanList }"> <div class="index_c"><p class="search_t_t">暂无数据</p></div></c:if>
	 <c:forEach items="${loanList}" var="data" begin="0" end="9" varStatus="i">
          	  
          	   <div class="index_c">
			  <h3>	<a href="<%=request.getContextPath() %>/loanDetail?loanId=${data.loanId}">${data.title}</a></h3>
			  <p style="line-height:36px">  
			  <c:choose>
						       	 <c:when test="${data.status eq 300}">
	                               	<a  href="<%=request.getContextPath() %>/getLoanDetailByLoanId?loanId=${data.loanId}" class="btn btn-primary btn-invest-size invest-btn">投资</a>
	                               </c:when>
	                               <c:when test="${data.status eq 400}">
	                               <a href="<%=request.getContextPath() %>/loanDetail?loanId=${data.loanId}" style="background: #cbccce">已满标</a>
	                                </c:when>
	                               <c:when test="${data.status eq 500}">
	                               <a href="<%=request.getContextPath() %>/loanDetail?loanId=${data.loanId}" style="background: #cbccce">还款中</a>
	                               </c:when>
	                               <c:when test="${data.status eq 301}">
	                                <a href="<%=request.getContextPath() %>/loanDetail?loanId=${data.loanId}" style="background: #cbccce">预热中</a>
	                               </c:when>
	                               <c:otherwise>
	                                <a href="<%=request.getContextPath() %>/loanDetail?loanId=${data.loanId}" style="background: #cbccce">已还款</a>
	                             </c:otherwise>
	          </c:choose>
	                               	<span><em class="wid4"><fmt:formatNumber value="${data.annualInterestRate}" pattern="#0.0"/>%</em><em class="wid4">
	                
			       <c:choose>
					   <c:when test="${data.termUnit ==1}">${data.termCount}天</c:when>
					    <c:otherwise>${data.termCount}个月</c:otherwise>
					</c:choose>
					</em></span>&nbsp;&nbsp;&nbsp;	
			  <c:choose>
					   <c:when test="${data.productId ==1}">按月付息到期还本</c:when>
					   <c:when test="${data.productId ==2}">一次性还本付息</c:when>
					    <c:otherwise>等额本息</c:otherwise>
			 </c:choose></p>
			  <div class="index_c_per"><div class="index_c_per bg" style="width:${data.biddingAmount/data.amount*100}%"></div></div>
			  <p><fmt:formatNumber value="${data.beginAmount}"  pattern="#,#00.00#"></fmt:formatNumber>元起投 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余<fmt:formatNumber value="${data.amount-data.biddingAmount}"  pattern="#,#00.00#"></fmt:formatNumber>元/总额<fmt:formatNumber value="${data.amount}"  pattern="#,#00.00#"></fmt:formatNumber>元</p>
			
			 </div>
          	  </c:forEach>
	<%@ include file="./comm/bott.jsp"%>
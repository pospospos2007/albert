<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body style="background:#ffffff">
 <div class="login search_t">
	 <h2><a href="<%=request.getContextPath() %>/investRecordMobile" title="账户中心" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg"/></a>投资记录</h2>
 </div>   
	
<div id="adv2" class="invest_re"> 
  <ul>
		<li id="li_#recordListRaise"  class="on"><a href="<%=request.getContextPath() %>/investRecordMobile?item=raise">回款中的标</a></li>
	</ul>
  </div>
 
 <div class="invest_rec">
  <ul id="invest_rec0" style="width:25%;float:left;">
  <li>投标日期：</li>
<!--   <li>借&nbsp;&nbsp;款&nbsp;&nbsp;人：</li> --> 
   <li>利&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;率：</li>
   <li>投&nbsp;&nbsp;资&nbsp;&nbsp;额：</li>
   <li>收款计划：</li>
  </ul>

  <ul style="width:75%;float:left">
    <li>&nbsp;<fmt:formatDate value="${loanDetail.investTime }" pattern="yyyy-MM-dd HH:mm"/></li>
 <!--  <li>&nbsp;${loanDetail.borrowerNickname }</li> -->   
	<li>&nbsp;${loanDetail.annualInterestRate }%</li>
	<li>&nbsp;<i>${loanDetail.investAmount }</i>元</li>
    <li>&nbsp;<a href="<%=request.getContextPath() %>/getEarningList.action?loanId=${loanDetail.loanId}&loanInvestorId=${loanDetail.loanInvestorId}">查看</a></li>
  </ul>
 </div>
<%@ include file="/mobile/comm/bott.jsp"%>


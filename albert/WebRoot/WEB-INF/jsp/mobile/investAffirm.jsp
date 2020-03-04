<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery-1.11.3.min.js"></script>
<script src="<%=request.getContextPath() %>/mobile/js/invest.js"></script>

<body>
	<div class="login">
		<h2>
			<a href="<%=request.getContextPath() %>/index" title="主页" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>投资确认
		</h2>
	</div>
	<div class="invest_aff">
		<p>
			<b>${loan.title}</b>
		</p>
		<p>
			借款金额：${loan.amount}元<br />借款期限：
			<c:choose>
					   <c:when test="${loan.termUnit ==1}">${loan.termCount}天 <input type="hidden" name="termUnit" value="天"/></c:when>
					   <c:otherwise>${loan.termCount}月 <input type="hidden" name="termUnit" value="月"/></c:otherwise>
			</c:choose><br />还款方式：
			<c:choose>
					   <c:when test="${loan.productId ==1}">按月付息到期还本</c:when>
					   <c:when test="${loan.productId ==2}">一次性还本付息</c:when>					   
					   <c:otherwise>等额本息</c:otherwise>
		</c:choose>
<br />预计年化收益：${loan.annualInterestRate}%<br />保障范围：本息保障<br />剩余金额：<span><span class="cash" id="cash"><fmt:formatNumber value="${user.cash}"  pattern="${moneyexp}"></fmt:formatNumber></span>元
			&nbsp;<a href="<%=request.getContextPath() %>/rechargeUI">充值</a>
		</p>
	</div>

	<div class="login_f" style="margin-top: 0">
		<form action="" method="post">
			<dl style="width: 30%">
				<dd>投资金额：</dd>
				<dd>验&nbsp; 证&nbsp;码：</dd>
			</dl>
			<dl style="width: 70%">
				<dd class="bor">
			           
					    <input type="text" name="investAmount"  id="investAmount" placeholder="起投${loan.beginAmount }元,递增${loan.increaseAmount}元" />
					    <input type="hidden" id="usercash" value="${userVo.cash}"/> 
						<input type="hidden" id="beginAmount" value="${loan.beginAmount }"  />
						<input type="hidden" id="increaseAmount" value="${loan.increaseAmount }"  />
						<input type="hidden" id="loanId" name="loanId" value="${loan.loanId}"/>
						<input type="hidden" id="borrowerNickname" name="borrowerNickname" value="${loan.borrowerNickname}" />
						<input type="hidden" id="title" name="title" value="${loan.title}" />
					    <input type="hidden" id="amount" name="amount" value="${loan.amount}" />
					    <input type="hidden" id="annualInterestRate" name="annualInterestRate" value="${loan.annualInterestRate}" />
					    <input type="hidden" id="termCount" name="termCount" value="${loan.termCount}" />	
					    <input type="hidden" id="productId" name="product" value="${loan.productId}" />				   
					   	<input type="hidden" id="earning" name="earning" value="${loan.annualInterestRate*loan.termCount}" />
					    <input type="hidden" id="remaining" name="remaining" value="${loan.amount-loan.biddingAmount}"/>
		                <input type="hidden" name="userId" id="userId" value="${userVo.userId}" />
		                 <input type="text" id="message" name="message"  hidden="hidden">
		               
				</dd>
				<dd>
				  <input size="10" type="text"  name="verifycode" id="verifycode" placeholder="验证码" />	            	
					<span ><img align="absmiddle" alt="验证码" title="验证码" id="imgverCode" src="<%=request.getContextPath() %>/registVerfyCode" onclick="coderefresh() "/></span>
				</dd>
			</dl>
			<div class="clear"></div>
		</form>

	</div>
   <img id="loadingImage" style="display:none; position:absolute;left:50%;z-index:10;" src="<%=request.getContextPath() %>/themes/${theme4app}/img/loading.gif" />	
	<div class="login_f_btn" id="invest-btn">同意协议并投资</div>


	<div class="reg_bot" style="width: 83%">
		<span style="color: #9c9d9f"><img src="<%=request.getContextPath() %>/mobile/images/reg_log.jpg" />我同意
			查看<a href="<%=request.getContextPath() %>/service?url=getLoanDetailByLoanId?loanId=${loan.loanId}" />《服务条款》</a> 和 <a href="<%=request.getContextPath() %>/privacy?url=getLoanDetailByLoanId?loanId=${loan.loanId}" />《隐私条款》</a></span>
	</div>

	<div class="theme-popover">
		<div class="theme-poptit">提示</div>
		<p>投标成功</p>
		<a href="<%=request.getContextPath() %>/accountInfo"><div class="index_login">返回我的账户</div></a>
	</div>
	<div class="theme-popover-mask"></div>

<%@ include file="/mobile/comm/bott.jsp"%>
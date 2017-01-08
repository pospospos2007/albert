<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<body>
	 <div class="login">
	  	<h2><a href="<%=request.getContextPath() %>/index?selectPage=selectPage" title="返回" class="fl">
	  	<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg"/></a>债权认购确认</h2>
	 </div>
	 <div class="bond_sub"><h3>${loanTitle}</h3>
		<p>
		    <em>债 权 本 金 :</em>${page.investAmount}元<br/>
			<em>转 让 价 格 :</em>${page.soldPrice}元<br/>
			<em>剩 余 天 数 :</em>${page.restDays}天<br/>
			<em>年 化 收 益 :</em>${page.loanAnnualInterestRate}%<br/>
			<em>净　收　益:</em><span>${page.interest}元</span><br/>
			<!-- <fmt:formatDate value="${data.tradeTime}" pattern="yyyy-MM-dd HH:mm"/> -->
			<em>下一还款日:</em><fmt:formatDate value="${page.nextRepayDate}" pattern="yyyy-MM-dd"/><br/>
			
		</p>
	 </div>
	 <a href="<%=request.getContextPath() %>/pay/chinapnr/start-CreditAssign.action?payReqFromApp.loanInvestorId=${page.id}&payReqFromApp.userId=${userVo.userId}"><div class="login_f_btn">认购</div></a>
	 <div class="reg_bot tc">
		  <span style="color:#9c9d9f;float:left"><img src="<%=request.getContextPath() %>/mobile/images/reg_log.jpg"/>我同意</span>  
		  <span class="fr">查看<a href="<%=request.getContextPath() %>/debtAgreement?loanInvestorId=${page.id}"/>《债权转让与受让协议》</a></span>
	 </div>
<%@ include file="/mobile/comm/bott.jsp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/mobile/comm/head.jsp"%>
<body style="background:#ffffff">
<div class="login search_t">
	<h2>
		<a href="<%=request.getContextPath() %>/repayingList" title="返回" class="fl"><img
			src="images/poi_lt.jpg" /></a>回款计划
	</h2>
</div>
<img src="images/d_return_p.jpg" width="100%" />
<%  
  String  productId = request.getParameter("productId");
  String  termCount = request.getParameter("termCount");
  String  leftTermCount = request.getParameter("leftTermCount");
  int mount = 0;
  int count = 0;
 if("2".equals(productId)){
 	mount = 1;
   	count =  1- Integer.parseInt(leftTermCount);
 }else{
 	mount = Integer.parseInt(termCount);
 	count = Integer.parseInt(termCount) - Integer.parseInt(leftTermCount);
 }
	//String borrowerNickname = new String(request.getParameter("borrowerNickname").getBytes("ISO-8859-1"),"UTF-8"); 
  %>
<div class="search_c d_return_p">
	<dl>
		<dd>
			<!--  <span>借&nbsp;&nbsp;款&nbsp;&nbsp;人：</span><i><%=request.getParameter("borrowerNickname") %></i>-->
		</dd>
		<dd>
			<span>下期待收：</span><i style="color: #f74172"><%=request.getParameter("nextPhase") %></i>元   
		</dd>
		<dd>
			<span>期&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数：</span>
			<i><%=count %>/<%=mount %>期</i> 
		</dd>
		<dd style="border-bottom: none">
			<span>投&nbsp;&nbsp;资&nbsp;&nbsp;额：</span><i><%=request.getParameter("investAmount") %>元</i>  
		</dd>
	</dl>


</div>
<%@ include file="/mobile/comm/bott.jsp"%>
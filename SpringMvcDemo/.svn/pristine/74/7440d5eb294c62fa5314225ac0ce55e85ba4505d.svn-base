<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<title>聊天室</title>
<link rel="stylesheet" href="<%=path%>/css/chatroom/talk.css" type="text/css"></link>
<script type="text/javascript" src="<%=path%>/js/chatroom/talk.js" charset="utf-8"></script>

<c:if test="${empty type||type ne 'long' }">
	<script type="text/javascript" src="<%=path%>/js/chatroom/short.js"></script>
</c:if>
<c:if test="${empty type||type ne 'short' }">
	<script type="text/javascript" src="<%=path%>/js/chatroom/long.js"></script>
</c:if>
<body>
	<div id="container">
		<div id="left">
			<ul id="msg_container">
			</ul>
			<div id="control_container">
				当前用户:<span id="user">${user }</span>
				对:<span id="reader" class="reader">所有人</span>说:<input type="text" id="msg" onkeydown="return sendKey(event);"/><input type="button" value="提交" onclick="sendMessage()" />
<!-- 				<input type="button" value="性能测试" onclick="test()" /> -->
			</div>
		</div>
		<ul id="right"></ul>
	</div>
<%@ include file="/include/footer.jsp"%>

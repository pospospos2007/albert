<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%-- <%@ include file="/include/head.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript" src="<%=path%>/js/chatroom/jquery.js"></script>
<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/commoneditor.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor.js"></script>
<title>网页聊天室</title>
<link href="<%=path%>/css/chatroom/style.css" rel="stylesheet">
	<div class="container">
		<div class="header">
			<div class="login">
				<p>
					<span style="color: #000000;"></span> 
					<input type="hidden" id="url" size="30" value="localhost:8080/SpringMvcDemo" class="txt" />
					<span style="color: #000000;">用户名：</span> 
					<input type="text" id="user" size="30" class="txt"
						onkeyup="this.value = this.value.replace(/\s/g,'')" placeHolder="请输入一个注册用户名"/> 
					<span><input type="button" value="连接" id="loginbtn" /></span>
				</p>
			</div>
		</div>
		<div class="center">
			<div class="contactlist">
				<ul>
					<li>好友列表（<span id="count" style="color: #000000">0</span>）</li>
					<li><a href="javascript:void(0)" onclick="selectUser(this)">所有人</a></li>
					<li><span id="me"></span></li>
				</ul>
				<ul id="userlist"></ul>
			</div>
			<div id="content"></div>
		</div>
		<div class="bottom">
			<div class="menubar">
				<a class="selemoji" href="javascript:void(0)" onclick="$('.emoji').show();">
					<img alt="表情" src="<%=path%>/images/chatroom/emoji.png">
				</a> 
				<span>&nbsp;&nbsp;@&nbsp;</span>
				<input type="text" id="to" disabled="disabled" value="所有人" class="txt" />
				<span>&nbsp;&nbsp;&nbsp;&nbsp;字体颜色：</span> 
				<select id="color" class="select">
					<option style="color: #000000" value="000000" selected>默认颜色</option>
					<option style="color: #FF0000" value="FF0000">红色热情</option>
					<option style="color: #0000FF" value="0000ff">蓝色开朗</option>
					<option style="color: #ff00ff" value="ff00ff">桃色浪漫</option>
					<option style="color: #009900" value="009900">绿色青春</option>
					<option style="color: #009999" value="009999">青色清爽</option>
					<option style="color: #990099" value="990099">紫色拘谨</option>
					<option style="color: #990000" value="990000">暗夜兴奋</option>
					<option style="color: #000099" value="000099">深蓝忧郁</option>
					<option style="color: #999900" value="999900">卡其制服</option>
					<option style="color: #ff9900" value="ff9900">镏金岁月</option>
					<option style="color: #0099ff" value="0099ff">湖波荡漾</option>
					<option style="color: #9900ff" value="9900ff">发亮蓝紫</option>
					<option style="color: #ff0099" value="ff0099">爱的暗示</option>
					<option style="color: #006600" value="006600">墨绿深沉</option>
					<option style="color: #999999" value="999999">烟雨蒙蒙</option>
				</select>
				<div class="emoji">
					<c:forEach begin="1" end="60" varStatus="index">
						<a class="emotion" href="javascript:void(0)"
							onclick="selectEmoji('${index.count}')"> <img width="35px" height="35px"
							src="<%=path%>/images/chatroom/biaoqing/1 (${index.count}).gif" /></a>
						<c:if test="${index.count % 10 eq 0}">
							<br />
						</c:if>
					</c:forEach>
				</div>
			</div>
		</div>
		<div id="msg" id="contentID" editFlag='init' onKeyDown="if(event.keyCode==13 && event.ctrlKey) sendMsg();"
			onfocus="hideEmoji()" contenteditable="true">
		</div>
		<p>
			<span class="block"><input type="button" value="发送"
				onclick="sendMsg()" /> 快捷键：Ctrl+Enter</span>
		</p>
	</div>
	<script type="text/javascript" src="<%=path%>/js/chatroom/jquery.js"></script>
	<script type="text/javascript" src="<%=path%>/js/chatroom/main.js" charset="utf-8"></script>
<%-- <%@ include file="/include/footer.jsp"%> --%>
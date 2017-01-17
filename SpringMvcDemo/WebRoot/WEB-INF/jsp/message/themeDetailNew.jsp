<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/commoneditor.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor.js"></script>
    <title>${theme.theme}</title>
    <meta name="keywords" content="${theme.theme}" />
    


	<div class="content">
      <br/>
      <h1>${theme.theme}</h1>
     
      <div class="article">
        <h2><img src="<%=path%>/uploadimage/${theme.avatar }" width="50px" height="50px" class="img-rounded"></img></h2>
        <p class="right">楼主:<a href="<%=path%>/userInfo?id=${theme.userId}">${theme.username}</a>
	        &nbsp;&nbsp;<fmt:formatDate value="${theme.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </p>
        <br />
        <p>${theme.content}</p>
       
      </div>
      <hr>
      <c:if test="${!empty messages }">
      <ol>
		<c:forEach items="${messages}" var="message">
			<br/>
	     	<div class="article">
	        <h2></h2>
	        <a href="<%=path%>/userInfo?id=${message.userId}" target="_blank"><img src="<%=path%>/uploadimage/${message.avatar }" width="50px" height="50px" class="img-rounded pull-left"></img></a>
	        <p class="right">
		        ${message.username}
		        &nbsp;&nbsp;<fmt:formatDate value="${message.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	        </p>
	        <br />
	        <p>${message.message}</p>
	        </div>
	        <hr>
		</c:forEach>
		</ol>
	  </c:if>
	  
	  
      <div class="article">
      <c:if test="${empty USER_SESSION_KEY}">
      	请<a href="<%=path%>/login" >登录</a>后进行操作！
      </c:if>
      <c:if test="${!empty USER_SESSION_KEY}">
      <form method="post" action="<%=path%>/message/addMessage" >
      	<input type="hidden" name="themeId" value="${theme.id}"></input>
                
                <div class="form-group">
					<label for="" class="col-sm-2 control-label"><span>回复</span></label>
					<div class="col-sm-9">
						<textarea id="editor_id" name="message" editFlag='init'  style="width:700px;height:300px;"></textarea>
					</div>
				</div>

            <div class="form-group">
                	<h3><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div>
					</h3>
                	<input name="messageCode" type="text" id="messageCode"  placeholder="请输入验证码" />
                </div>

            <input type="submit" value="回复" />
        </form>
      </c:if>
      </div>
	</div>
   
<%@ include file="/include/footer.jsp"%>

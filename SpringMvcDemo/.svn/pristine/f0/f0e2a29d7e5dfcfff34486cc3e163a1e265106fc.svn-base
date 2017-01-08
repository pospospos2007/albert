<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>${theme.theme}</title>
  <link rel="stylesheet" href="css/style3.css" />
  <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- 配置文件 -->
	<script type="text/javascript" src="kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script>
        KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id');
        });

        var options = {
                cssPath : '/css/index.css',
                filterMode : true
        };
        var editor = K.create('textarea[name="message"]', options);
</script>
  </head>
  <style>
     .content{
       background:rgba(217,213,206,1) url("img/a3.png") no-repeat; 
       overflow:scroll; 
     }
     .article{
      text-indent: 2em;
      font-size: 18px;
      letter-spacing: 0.05em;
     }
  </style>
  <body>
<%@ include file="/include/title.jsp"%>
	<div class="content">
      <br/>
      <h1>${theme.theme}</h1>
     
      <div class="article">
        <h2></h2>
        <p class="right">楼主${theme.ip}
	        &nbsp;&nbsp;添加时间:<fmt:formatDate value="${theme.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
        </p>
        <br />
        <p>${theme.content}</p>
       
      </div>
      <ol>
      <c:if test="${!empty messages }">
		<c:forEach items="${messages}" var="message">
			<br/>
	     	<div class="article">
	        <h2></h2>
	        <p class="right"><li>楼&nbsp;
		        <a href="#" target="_blank">${message.ip}</a>
		        &nbsp;&nbsp;添加时间:<fmt:formatDate value="${message.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
	        </p>
	        <br />
	        <p>${message.message}</p>
	        </div>
		</c:forEach>
	  </c:if>
	  </ol>
      <div class="article">
      <form method="post" action="<%=path%>/message/addMessage" >
      	<input type="hidden" name="themeId" value="${theme.id}"></input>
                
                <div class="form-group">
					<label for="" class="col-sm-2 control-label"><span>内容</span></label>
					<div class="col-sm-9">
						<textarea id="editor_id" name="message" style="width:700px;height:300px;">
						</textarea>
					</div>
				</div>

            <div class="form-group">
                	<li><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div></li>
                	<input name="messageCode" type="text" id="messageCode"  placeholder="请输入验证码" />
                </div>

            <input type="submit" value="回复" />
        </form>
      
      </div>
	</div>
   
  <div class="footer">
      <copy>&copy;Albert</copy>
   </div>
  </body>
</html>

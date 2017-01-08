<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico">
    <base href="<%=basePath%>">
    <script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <title>添加文章</title>
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
        var editor = K.create('textarea[name="content"]', options);
</script>
  </head>
  <style>
   .content{
      background:rgba(202,195,189,1) url("img/a2.jpg") no-repeat; 
   }
  </style>
  <body>
<%@ include file="/include/title.jsp"%>
<div class="content">
    <h1>添加新文章</h1>
    <div class="article">
        <form method="post" action="<%=path%>/addAirticle" >
            <table cellpadding="8" cellspacing="10"><%--
             
                <tr>
                    <td><label for="title" >文章名: </label></td>
                    <td><input name="title" type="text" id="title" autofocus placeholder="请输入文章名"/></td>
                </tr>
             
                <tr>
                    <td><label for="content">内容: </label></td>
                    <td><textarea name="content" id="content" cols="55" rows="10"></textarea></td>
                </tr>
                --%>
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">标题</label>
                	<input name="title" type="text" id="title" autofocus placeholder="请输入文章名" />
                </div>
                <div class="form-group">
					<label for="" class="col-sm-2 control-label">内容</label>
					<div class="col-sm-9">
						<textarea id="editor_id" name="content" style="width:700px;height:300px;">
						</textarea>
					</div>
				</div>

            </table>

            <input type="submit" value="发布文章" />
        </form>
   
   </div>
   </div>
  <div class="footer">
      <copy>&copy;Albert</copy>
   </div>
  </body>
</html>

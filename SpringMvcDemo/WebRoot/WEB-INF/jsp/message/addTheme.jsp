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
    <title>添加主题</title>
  <link rel="stylesheet" href="css/style3.css" />
  <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- 配置文件 -->
	<script type="text/javascript" src="../../kindeditor/kindeditor-min.js"></script>
<script type="text/javascript" src="../../kindeditor/commoneditor.js"></script>
<script type="text/javascript" src="../../kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript" src="../../kindeditor/kindeditor.js"></script>

  </head>
  <style>
   .content{
      background:rgba(202,195,189,1) url("img/a2.jpg") no-repeat; 
   }
  </style>
  <body>
<%@ include file="/include/title.jsp"%>
<div class="content">
    <h1>添加新主题</h1>
    <div class="article">
        <form method="post" action="<%=path%>/message/addTheme" >
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label"><span>标题</span></label>
                	<input name="theme" type="text" id="title" autofocus placeholder="请输入主题" maxlenth="15" />
                </div>
                <div class="form-group">
					<label for="" class="col-sm-2 control-label">内容</label>
					<div class="col-sm-9">
						<textarea  name="content" id="detailID" editFlag='init' style="width:700px;height:300px;">
						</textarea>
					</div>
				</div>

            </table>
				<div class="form-group">
				<li><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div></li>
                	<input name="code" type="text"  autofocus placeholder="请输入验证码" />
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

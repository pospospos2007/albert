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
    <title>分享文件</title>
  <link rel="stylesheet" href="css/style3.css" />
  <link rel="stylesheet" href="css/font-awesome.min.css">
    <!-- 配置文件 -->
	<script type="text/javascript" src="kindeditor/kindeditor-all-min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
<script>
        KindEditor.ready(function(K) {
                window.editor = K.create('#editor_id');
        });

        var options = {
                cssPath : '/css/index.css',
                filterMode : true
        };
        var editor = K.create('textarea[name="discription"]', options);
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
    <h1>分享图片</h1>
    <div class="article">
        <form method="post" action="<%=path%>/file/addImage" >
            <table cellpadding="8" cellspacing="10">
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">名称</label>
                	<input name="name" type="text" id="name"  placeholder="请输入名称" maxlength="30" />
                </div>
                 <div class="form-group">
                	<label for="" class="col-sm-2 control-label">资源</label>
                	 <span class="filebtn "><input type="file" id="file" name="file" class="fbtn" onchange="uploadFile();"/></span>
<!-- 	           <a id="downFileId" href="" style="color:blue"></a> -->
	           <input type="text" value=""  readonly="readonly" name="url" id="url"></input>
                </div>
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">格式</label>
                	<input name="imageFormat" type="text" id="imageFormat" readonly="readonly"/>
                </div>
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">支持格式:</label>
                	<span>JPG,JPEG,GIF,PNG,JPG,jpg,jpeg,gif,png,bpm,BPM</span>
                </div>
              
				
				<div class="form-group">
				<li><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div></li>
                	<input name="imageCode" type="text"  placeholder="请输入验证码" />
                </div>

            </table>

            <input type="submit" value="提交" />
        </form>
   
   </div>
   </div>
  <div class="footer">
      <copy>&copy;Albert</copy>
   </div>
   <script>
   
 //文件上传
   function uploadFile(){
 	  $.ajaxFileUpload({
 		  url:'<%=path%>/file/uploadImage',
 		  secureuri:false,
 		  fileElementId :'file',
 		  type : "POST",
 		  dataType : "text",
 		  success:function(data, status){
 			  var jsonIndex = data.indexOf("{");
 			  var length = data.length;
 			  var result = $.parseJSON(data.substring(jsonIndex, length));
 			  if(result.status) {
 				  var path = result.filePath;
 				  
 				 $("#url").val("");
 				 
 				  $("#url").val(path);
 				  $("#imageFormat").val(result.fileFormat);
 				  alert("上传成功");
 			  } else {
 				  alert(result.msg);
 			  }
 		  },
 		  error:function(data, status){
 			 alert(status);
 		  }
 	  });
   } 
   </script>
  </body>
</html>

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
    <h1>文件详情</h1>
    <div class="article">
        <form method="post" action="<%=path%>/file/addFile" >
            <table cellpadding="8" cellspacing="10">
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">标题</label>
                	<input name="title" type="text" id="title" value="${file.title}" placeholder="请输入文章名" />
                </div>
                 <div class="form-group">
                	<label for="" class="col-sm-2 control-label">资源</label>
	           <input type="text" readonly="readonly" value="${file.url}" name="url" id="url"></input>
                </div>
                 <div class="form-group">
                	<label for="" class="col-sm-2 control-label">格式</label>
	           <input type="text" value="${file.fileFormat}" name="fileFormat" id="fileFormat" readonly="readonly"></input>
                </div>
                <div class="form-group">
					<label for="" class="col-sm-2 control-label">描述</label>
					<div class="col-sm-9">
						<textarea id="editor_id"  name="discription" style="width:700px;height:300px;">
						${file.discription}
						</textarea>
					</div>
				</div>
				

            </table>

            <input type="button" onclick="download(${file.id})" value="下载" />
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
 		  url:'<%=path%>/file/uploadImg',
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
 	  
 	  
 	  function download(id){
 		  
 		 window.location.href="<%=path%>/file/downloadFile?id="+id;
 	  }
   </script>
  </body>
</html>

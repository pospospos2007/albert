<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>

    <title>上传人像</title>
	
<div class="content">
    <h1>上传人像以识别</h1>
    <div class="article">
        <form method="post" action="<%=path%>/file/addFace" >
            <table cellpadding="8" cellspacing="10">
            <p>注意：如果图片过大或者网速慢，请耐心等待提示“上传成功”后再提交以识别</p>
            <p>此图像识别不支持卡通人物的识别，请尽量上传中等大小图片和真实人像</p>
                 <div class="form-group">
                	<label for="" class="col-sm-2 control-label">人像上传</label>
                	 <input type="file" id="file" name="file"  onchange="uploadFile();"/>
<!-- 	           <a id="downFileId" href="" style="color:blue"></a> -->
	           <input type="hidden" value=""  readonly="readonly" name="url" id="url"></input>
                </div>
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">格式</label>
                	<input type="text" id="imageFormat" readonly="readonly"/>
                </div>
                <div class="form-group">
                	<label for="" class="col-sm-2 control-label">支持格式:</label>
                	<span>JPG,JPEG,PNG,JPG,jpg,jpeg,png,bpm,BPM</span>
                </div>
              
				
				<div class="form-group">
				<li><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div></li>
                	<input name="faceCode" type="text"  placeholder="请输入验证码" />
                </div>

            </table>

            <input type="submit" value="提交" />
        </form>
   
   </div>
   </div>
  <div class="footer">
      <copy>&copy;Albert</copy>
   </div>
   <script type="text/javascript" src="<%=path%>/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
   <script>
   
 //文件上传
   function uploadFile(){
 	  $.ajaxFileUpload({
 		  url:'<%=path%>/file/uploadFace',
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
<%@ include file="/include/footer.jsp"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
    <title>人像识别结果</title>
    <meta name="keywords" content="人像识别结果" />

    <h1>人像识别结果</h1>
    <div class="article">
        <img src="<%=path%>/uploadface/${face.url}" ></img>
        <c:if test="${empty faceAttrs}">
		<p>无法识别此图像</p>
		</c:if>
        <c:forEach items="${faceAttrs}" var="faceAttr" varStatus="status">
        <p> 图像中的第${status.count}个人</p>
        <li>性别：${faceAttr.gender }</li>
        <li>有无眼镜：${faceAttr.glass }</li>
        <li>年龄：${faceAttr.age }</li>
        <li>人种：${faceAttr.race }</li>
        </br>
        </c:forEach>
   
   </div>
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
<%@ include file="/include/footer.jsp"%>
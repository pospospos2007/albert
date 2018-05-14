<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
    <title>添加主题</title>

    <!-- 配置文件 -->
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/commoneditor.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor.js"></script>

<script>
		 var via ="";
		 $("#via").val(via);
</script>
  </head>

  <body>

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
						<textarea  name="content" id="contentID" editFlag='init' style="width:700px;height:300px;"></textarea>
					</div>
				</div>
<!-- 				<div class="form-group"> -->
<!-- 					<label for="" class="col-sm-2 control-label">via:</label> -->
<!-- 					<input name="via" type="text" id="via" value="" /> -->
<!-- 				</div> -->

            </table>
<!-- 				<div class="form-group"> -->
<!-- 				<h3><label for="user" class="input-tips2">验证码：</label> -->
<!-- 					<div class="inputOuter2"> -->
<%-- 						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张" --%>
<!-- 							onClick="this.src=this.src+'?'" /> -->
<!-- 					</div> -->
<!-- 				</h3> -->
<!--                 	<input name="code" type="text"  autofocus placeholder="请输入验证码" /> -->
<!--                 </div> -->
                
            <input type="submit" value="发表" />
            
        </form>
        
  	 </div>
   </div>
   
<%@ include file="/include/footer.jsp"%>

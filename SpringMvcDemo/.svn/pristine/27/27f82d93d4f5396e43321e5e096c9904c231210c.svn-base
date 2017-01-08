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
/*
* 智能机浏览器版本信息:
*
*/
		var browser = {
		versions: function() {
		var u = navigator.userAgent, app = navigator.appVersion;
		return {//移动终端浏览器版本信息 
		trident: u.indexOf('Trident') > -1, //IE内核
		presto: u.indexOf('Presto') > -1, //opera内核
		webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
		gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1, //火狐内核
		mobile: !!u.match(/AppleWebKit.*Mobile.*/) || !!u.match(/AppleWebKit/), //是否为移动终端
		ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
		android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或者uc浏览器
		iPhone: u.indexOf('iPhone') > -1 || u.indexOf('Mac') > -1, //是否为iPhone或者QQHD浏览器
		iPad: u.indexOf('iPad') > -1, //是否iPad
		webApp: u.indexOf('Safari') == -1 //是否web应该程序，没有头部与底部
		};
		}(),
		language: (navigator.browserLanguage || navigator.language).toLowerCase()
		}
		
		
// 		 document.writeln(" ios终端: " + browser.versions.ios);
// 		 document.writeln(" android终端: " + browser.versions.android);
// 		 document.writeln(" 是否为iPhone: " + browser.versions.iPhone);
// 		 document.writeln(" 是否ipad: " + browser.versions.iPad);
		 var via="";
		 if(browser.versions.ios=="true"){
			 via = "ios";
		 }else if(browser.versions.android=="true"){
			 via = "android";
		 }else if(browser.versions.iPhone=="true"){
			 via = "iPhone";
		 }else if(browser.versions.iPad=="true"){
			 via = "iPad";
		 }else{
			 via = "PC";
		 }
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
				<div class="form-group">
					<label for="" class="col-sm-2 control-label">via:</label>
					<input name="via" type="text" id="via" value="" />
				</div>

            </table>
				<div class="form-group">
				<h3><label for="user" class="input-tips2">验证码：</label>
					<div class="inputOuter2">
						<img src="<%=path%>/message/tuXingYanZhengMa" title="看不清，点击换一张"
							onClick="this.src=this.src+'?'" />
					</div>
				</h3>
                	<input name="code" type="text"  autofocus placeholder="请输入验证码" />
                </div>
                
            <input type="submit" value="发表" />
            
        </form>
        
  	 </div>
   </div>
   
<%@ include file="/include/footer.jsp"%>

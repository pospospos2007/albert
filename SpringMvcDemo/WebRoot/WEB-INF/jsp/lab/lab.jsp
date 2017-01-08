<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ include file="/include/head.jsp"%>
    <title><s:message code='laboratory.index'/></title>
 


      <div class="content">
	  <li>多线程测试 ：<button onclick="testMutiThreads()">按这个按钮</button></li>
	  <li><a href="<%=path%>/lab/toDraw" target="_blank">画板</a></li> 	
<!-- 	  <li>谷歌搜索（需翻墙才可显示）：</li>    -->
		<script>
// 	  (function() {
// 	    var cx = '007821717631179546661:42aw6xv7bxe';
// 	    var gcse = document.createElement('script');
// 	    gcse.type = 'text/javascript';
// 	    gcse.async = true;
// 	    gcse.src = 'https://cse.google.com/cse.js?cx=' + cx;
// 	    var s = document.getElementsByTagName('script')[0];
// 	    s.parentNode.insertBefore(gcse, s);
// 	  })();
	</script>
<!-- 	<gcse:search></gcse:search> -->
      	  <li>网易云音乐（我喜欢的音乐，自动更新）：</li> 
<iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=20%  height=450 src="http://music.163.com/outchain/player?type=0&id=66673043&auto=1&height=430"></iframe>
	 
<!-- 	   <li>内置浏览器    网址：<input placeHolder="http://" value="http://www.baidu.com" id="address"></input><button value="访问" onclick="goBrowser()">访问</button></button></li>  -->
<!-- <iframe id="browser" frameborder="no" border="0" marginwidth="0" marginheight="0" width=100%  height=600 src="http://www.baidu.com"></iframe>   -->
	  
	 <li>地图查询</li> 
	  
	  
	  
	  
	  
	  </div>
	 
	
<!-- 	<div class="footer"><copy>&copy;Albert</copy></div> -->
	<script type="text/javascript">
	function goBrowser(){
		var address = $("#address").val();
		if(address.indexOf("http")==-1){
			address = "http://"+address;
		}
		$("#browser").attr("src",address);
	}
	function testMutiThreads(){
		
		$.ajax({
			url : "<%=path%>/lab/mutiThreads",
			type : "GET",
			dataType : "json",
			success : function(result) {
					alert(result.msg);
			}
		});
	}
	</script>
<%@ include file="/include/footer.jsp"%>

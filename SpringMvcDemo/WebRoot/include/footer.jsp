<%@ page contentType="text/html;charset=UTF-8" %>
<script>
//切换环境语言
function changLanguage(language){
	$.ajax({
		url : "<%=path%>/changeLanguage",
		data : {'language': language},
		type : "POST",
		dataType : "json",
		success : function(result) {
			if(result.res=='true'){
				//重新加载页面
				location.reload();
			}else{
				alert(result.msg);
			}
		}
	});
}
</script>
</div>
  <div class="footer">
      <div class="container">
        <p class="text-muted"><copy>&copy;Albert</copy>&nbsp;
        <a target="_blank" href="http://shang.qq.com/wpa/qunwpa?idkey=1e5a28df9900e87e337603787a215851eb0b35587768c8b8698462cf6d3f09ee"><img border="0" src="http://pub.idqqimg.com/wpa/images/group.png" alt="Albert网站用户群" title="Albert网站用户群"></a>
        <a target="_blank" href="http://mail.qq.com/cgi-bin/qm_share?t=qm_mailme&email=1LKmtbq-uLGxi_Dt4_Lk5_Pj7JSlpfq3u7k" style="text-decoration:none;"><img src="http://rescdn.qqmail.com/zh_CN/htmledition/images/function/qm_open/ico_mailme_01.png"/></a>
<!--         <a  href="#" >中文</a>/<a  href="#" >English</a> -->
		 <a href="javascript:void(0)" onclick="changLanguage('zh_CN');"  style="cursor: pointer;">中文</a>／
     	 <a href="javascript:void(0)" onclick="changLanguage('en_US');"  style="cursor: pointer;">English</a>／
     	 <a href="javascript:void(0)" onclick="changLanguage('fr_FR');"  style="cursor: pointer;">Français</a>
        </p>
     	
      </div>
      
     
  	
    </div>
<script src="http://v3.bootcss.com/assets/js/ie10-viewport-bug-workaround.js"></script>
<script>
function active(obj){
	$("li").removeClass("active");
	$(obj).addClass("active");
}
console.log("%cCopyright  2016-%s, huiwupay.com:9999\n\n%cWelcome%c \n\n%cFree and trust.", "font-size:12px;color:#999999;", (new Date).getFullYear(), 'font-family: "Helvetica Neue", "Luxi Sans", "DejaVu Sans", Tahoma, "Hiragino Sans GB", "Microsoft Yahei", sans-serif;font-size:64px;color:#404040;-webkit-text-fill-color:#404040;-webkit-text-stroke: 1px #777;', 'font-family: "Helvetica Neue", "Luxi Sans", "DejaVu Sans", Tahoma, "Hiragino Sans GB", "Microsoft Yahei", sans-serif;font-size:12px;color:#999999; font-style:italic;', 'font-family: "Helvetica Neue", "Luxi Sans", "DejaVu Sans", Tahoma, "Hiragino Sans GB", "Microsoft Yahei", sans-serif;font-size:12px;color:#999999;');
</script>


</body>
</html>

<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>
<script type="text/javascript" src="/js/jquery.qrcode.min.js"></script>
<body style="TEXT-ALIGN: center"><div style="height:1px;overflow:hidden;"><img src="http://www.zhidiancaifu.cn:8080/ueditor/jsp/upload/image/20150205/1423122470142055137.jpg" /></div> 
	<c:if test="${userVo.userId!=null}">
		<div class="login search_t">
			<h2>
				<a href="/accountInfo" title="账户中心" class="fl"><img
					src="/mobile/images/poi_lt.jpg" /></a>邀请好友
			</h2>
	
		</div>
	<div class="tc">
		<img src="/mobile/images/invite.jpg" style="width: 46%; margin: 25px auto" />
		<p>
			请点击右上角弹出菜单发送给好友或分享到朋友圈或分享到QQ好友，每成功邀请一位好友，<br/>
			您可获得奖励积分，上不封顶。
		</p>
	</div>
	<script>
		jQuery(document).ready(function($) {
			document.title = '关注智典财富，注册有礼';
		});
	</script>
	</c:if>
	<br><div id="qrcodeZhiDian" style="MARGIN-RIGHT: auto; MARGIN-LEFT: auto"></div><br>
<script>
function utf16to8(str) {  
    var out, i, len, c;  
    out = "";  
    len = str.length;  
    for(i = 0; i < len; i++) {  
    c = str.charCodeAt(i);  
    if ((c >= 0x0001) && (c <= 0x007F)) {  
        out += str.charAt(i);  
    } else if (c > 0x07FF) {  
        out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));  
        out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    } else {  
        out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));  
        out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));  
    }  
    }  
    return out;  
}  
var hostport=document.location.host;
var options = {  
        //render: 'table',//设置渲染方式canvas/table  
        width : 200,     //设置宽度256  
        height: 200,     //设置高度 256
        text: "http://"+hostport+"/goRegister?invitedCode=${param.invitedCode}&referrerCode=${param.referrerCode}"//设置二维码内容  
    };  
    
jQuery(function(){
	jQuery('#qrcodeZhiDian').qrcode(options);
})
</script>

	<c:if test="${userVo.userId==null}">
	
	<div class="login_f_btn">点击注册为智典新用户</div>
	<script>
	
	jQuery(document).ready(function($) {
			$('.login_f_btn').click(function(){
				location.href="http://"+hostport+"/goRegister?invitedCode=${param.invitedCode}&referrerCode=${param.referrerCode}";
			});
		});
	</script>
	</c:if>

	<%@ include file="./comm/bott.jsp"%>
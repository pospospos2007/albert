<%@ page contentType="text/html;charset=UTF-8"%>
<style type="text/css">
.theme-p-mask {z-index: 2;position: fixed;top: 0;left: 0;width: 100%;height: 100%;background: #000;opacity: 0.5;filter: alpha(opacity = 50);display:}
.theme-p {z-index: 3;position: fixed;top: 20%;left: 50%;width: 80%;margin-left: -40%;border-radius: 5px;background-color: #fff;display:none;box-shadow: 0 0 5px #666;color: #ff2766;text-align: center}
.theme-p .theme-close {position: absolute;top: -8px;right: -8px;cursor: pointer}
.theme-p .theme-btn {margin: 12px;height: 36px;border-radius: 15px;background: #f74172;line-height: 36px;color: white;font-size: 16px;cursor: pointer}
.theme-p p {color: gray;margin: 15px 0;font-size: 16px}
.theme-p a {text-decoration: none}
.theme-ptit {border-bottom: 1px solid #ddd;margin: 0 15px;padding: 6px 0;font-size: 18px}
.theme-ptit .close {float: right;color: #999;padding: 3px;margin: -2px -5px -5px;text-shadow: 0 1px 0 #ddd}
.theme-p p a {width: 39%;height: 28px;line-height: 28px;font-size: 16px;color: white;border-radius: 5px;background: #f74172;text-align: center;margin-top: 5px;cursor: pointer}
.theme-p .affirm-btn {width: 80%;margin: 0 auto}
.theme-p .affirm-btn ul li {float: left;height: 36px;border-radius: 15px;background: #f74172;line-height: 36px;color: white;font-size: 16px;cursor: pointer;width: 37%;margin-bottom: 10px;list-style: none}
.error-c {width: 284px;overflow: hidden;font-family: 'Microsoft YaHei', 'Microsoft JhengHei', 'STHeiti',	'MingLiu';font-size: 16px;line-height: 26px;color: #f6ac1d;margin: 0 auto}
.error-c a {color: #ffffff;text-decoration: none;}
.error-c a:hover {color: #f6ac1d;text-decoration: none;}
.error-c span {font-weight: bold;}
#digAccount{width:86%; margin:2em auto;font-size:14px;text-align:left;}
#lookOk{width:86%; margin:0 auto 1em;text-align:right;}
</style>
<!-- 开户提醒提示框 -->
<div class="theme-p" id="accountOpen"><div class="theme-ptit">提示</div><p id="digAccount">提示框</p><a href='${ctx}toRealNameMobile' ><div class="theme-btn">开户</div></a><a><div id="lookOk">我去逛逛</div></a></div>

<!-- 提示框 -->
<div class="theme-p" id="tooltip"><div class="theme-ptit">提示</div><p id="digMessage">提示框</p><a><div class="theme-btn" id="tooltipOK">确定</div></a></div>

<!-- 选择框 -->
<div class="theme-p" id="selectbox"><div class="theme-ptit">提示</div><p id="affirmMessage">选择框 </p>	<div class="affirm-btn"><ul><li id="affirmOK" style="margin-right: 26%">确定</li><li id="affirmCancel">取消</li></ul></div></div>

 <!-- 延时等待框  -->
<div class="theme-p" id="waitbox"><div class="error-c"><img
				src="<%=request.getContextPath() %>/mobile/images/wainting.gif" /><p><span id="time"></span>秒后完成交易<br /><span id="time1">6</span>秒</p></div></div>

<div class="theme-p-mask" ></div>

<script>
	jQuery(document).ready(function($) {
		$('.theme-p-mask').fadeOut(100);
	})
	
    //用户开户提示框
	function showDialogAccount(msg){
		document.getElementById("digAccount").innerHTML=msg;
		$('.theme-p-mask').fadeIn(100);
		$('#accountOpen').slideDown(200);
		$('#lookOk').click(function(){
			$('.theme-p-mask').fadeOut(100);
			$('#accountOpen').slideUp(200);
		})
	}
	
	
	//提示框
	function showDialogHTML(msg){
		document.getElementById("digMessage").innerHTML=msg;
		$('.theme-p-mask').fadeIn(100);
		$('#tooltip').slideDown(200);
		$('#tooltipOK').click(function(){
			$('.theme-p-mask').fadeOut(100);
			$('#tooltip').slideUp(200);
		})

	}
	//选择框
	function showAffirmDialogHTML(msg,Func){
		document.getElementById("affirmMessage").innerHTML=msg;
		$('.theme-p-mask').fadeIn(100);
		$('#selectbox').slideDown(200);
		$('#affirmOK').click(function(){
			$('.theme-p-mask').fadeOut(100);
			$('#selectbox').slideUp(200);
			Func();
		});
		$('#affirmCancel').click(function(){
			$('.theme-p-mask').fadeOut(100);
			$('#selectbox').slideUp(200);
		})
	}
	
	/* //延时等待框    ---冯文学
	function showWaitDialogHTML(targetUrl) {
			$('.theme-p-mask').fadeIn(100);
			$('#waitbox').slideDown(200);
			delayURL(targetUrl);
	}
	function delayURL(targetUrl) {
		
		var delay = document.getElementById("time").innerHTML;
		var delay = document.getElementById("time1").innerHTML;

		if(delay > 1) {
			delay--;
			$('#time').html(delay);
			$('#time1').html(delay);
		} else {
			//location.reload();
			$('.theme-p-mask').fadeOut(100);
			$('#waitbox').slideUp(200);
			window.location.href=targetUrl; 
			
		}
		setTimeout("delayURL(" + targetUrl + "')", 1000);
	} */
	
	function showWaitDialogHTML(targetUrl,description) {
			$('.theme-p-mask').fadeIn(100);
			$('#waitbox').slideDown(200);
			delayURL(window.top.location.href,targetUrl,description);
	}
	
	//延时等待框
	function delayURL(url,targetUrl,description) {
		//var delay1 = document.getElementById("time").innerHTML;
		var delay = document.getElementById("time1").innerHTML;
		document.getElementById("time").innerHTML = description+"成功,5";
		if(delay > 1) {
			delay--;
			//document.getElementById("time").innerHTML = delay;
			document.getElementById("time1").innerHTML = delay;
		} else {
			//location.reload();
			$('.theme-p-mask').fadeOut(100);
			$('#waitbox').slideUp(200);
			window.location.href=targetUrl; 
			
		}
		setTimeout("delayURL('" + url + "','" + targetUrl + "','"+description+"')", 1000);
	}
	
	
	//显示气泡提示框
	function ShowTipBox(inptObj,msgCont) {
		inptObj.tipso({useTitle: false,content:msgCont,width:400});
		inptObj.tipso('show');
	}

</script>

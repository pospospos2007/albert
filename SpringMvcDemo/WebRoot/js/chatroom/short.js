window.onload = function(){
	window.setInterval(getMessage,1000);	//设置每秒获取一次消息
}

//短轮询用获取消息函数
function getMessage(){
	var request = getHttpRequest();
	if(request){
		var rnd = Math.round(Math.random()*10000);				//生成一个随机数
		request.open('GET','GetMessage?temp='+rnd,true);		//把随机数作为参数发送(避免IE缓存BUG)
		request.onreadystatechange = function(){
			if(request.readyState == 4){
				if(request.status == 200){
					var msgList = request.responseXML.documentElement.getElementsByTagName('msg');	//获取消息集合
					refreshContainer(msgList);
				}
			}
		}
		request.send(null);
	}else{
		alert('您的浏览器不支持此聊天室功能');
	}
}
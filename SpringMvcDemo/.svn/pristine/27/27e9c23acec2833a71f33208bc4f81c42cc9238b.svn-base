window.onload = function(){
	getMessage();
}

//长轮询用获取消息函数
function getMessage(){
	var request = getHttpRequest();
	if(request){
		var rnd = Math.round(Math.random()*10000);				//生成一个随机数
		request.open('GET','GetMessageLong?temp='+rnd,true);		//把随机数作为参数发送(避免IE缓存BUG)
		request.onreadystatechange = function(){
			if(request.readyState == 4){
				if(request.status == 200){
					if(request.responseXML !=null){
						var msgList = request.responseXML.documentElement.getElementsByTagName('msg');	//获取消息集合
						refreshContainer(msgList);
					}
					getMessage();		//消息获取完成后再次请求服务器
				}
			}
		}
		request.send(null);
	}else{
		alert('您的浏览器不支持此聊天室功能');
	}
}
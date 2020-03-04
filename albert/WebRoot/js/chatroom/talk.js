function getHttpRequest(){
	if(window.ActiveXObject){
		return new ActiveXObject('Microsoft.XMLHTTP');
	}else if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
}

function sendMessage(){
	var input = document.getElementById('msg');						//获取消息文本框
	var container = document.getElementById('msg_container');		//获取消息容器
	
	if(input ==null || container==null || input.value==''){
		return;
	}
	var request = getHttpRequest();
	if(request){
		var str = input.value;		//获取要发送的消息字符串
		if(str == ''){
			return;
		}
		
		request.open('POST','SendMessage.do',true);
		request.setRequestHeader("Content-Type","application/x-www-form-urlencoded;");
		request.onreadystatechange = function(){
			if(request.readyState == 4){
				if(request.status == 200){
					var reader = document.getElementById('reader').innerHTML;
					if(request.responseText == 'true'){		//如果发送成功
						//在消息容器中添加自己发送的消息
						container.innerHTML +='<li><span class="sender">你</span>对<span class="reader">'+reader+'</span>说:<span class="msg">'+ str +'</span></li>';
						container.scrollTop = container.scrollHeight;		//将容器滚动条滚动到最后
						input.value="";	//清空消息输入框
					}else{
						alert('发送失败');
					}
				}
			}
		}
		var reader = document.getElementById('reader').innerHTML;
		if(reader=='所有人'){
			reader = '';
		}
		request.send('msg='+str+'&reader='+reader);
	}else{
		alert('您的浏览器不支持此聊天室功能');
	}
}

function refreshContainer(list){
	var msgStr="";
	for(var i=0;i<list.length;i++){
		var m = list[i];						//获取每条消息节点
		msgStr +='<li><span class="sender">'+ m.getAttribute('s') +'</span>对<span class="reader">'+(m.getAttribute('r')==''?'所有人':m.getAttribute('r'))+'</span>说:<span class="msg">'+ m.getAttribute('m') +'</span></li>';
	}
	if(list.length >0){
		var ul =document.getElementById('msg_container');		//获取消息容器
		ul.innerHTML += msgStr;									//向容器添加消息
		ul.scrollTop = ul.scrollHeight;							//将容器滚动条滚动到最后
	}
}

//输入框回车键
function sendKey(e) { 
	var keynum; 

	if (window.event){ // IE  
		keynum = e.keyCode; 
	} 
	else if (e.which){ // Netscape/Firefox/Opera 
		keynum = e.which; 
	} 
	if (keynum == 13) { 
		sendMessage(); 
		return false; 
	}
}

function test(){
	var i =0;
	window.setInterval(function(){document.getElementById('msg').value="TEST"+i;i++;sendMessage();},20);
}

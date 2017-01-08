	var isOpen = false; //标识是否打开连接
	
	var ws = null; //WebSocket对象
	//启动WebSocket
	function startSocket(){
		var user = $("#user").val();
		if(user.replace(/\s/g,'') == ''){
			alert("请输入用户名");
			return;
		}
		if(/@/.test(user)){
			alert("用户名不能包含特殊字符");
			return;
		}
		if(user == '所有人'){
			alert("用户名不能为‘所有人’");
			return;
		}
		
		$("#me").html(user);
		var wsurl = "ws://"+ $("#url").val() +"/webchat/"+ user; //声明一个变量存储服务器地址
		
		//根据浏览器类型创建对应的WebSocket实例
		if('WebSocket' in window){ 
			ws = new WebSocket(wsurl);
		}
		else if('MozWebSocket' in window){
			ws = new MozWebSocket(wsurl);
		} 
		else {
			alert("您的浏览器不支持WebSocket");
		}
	
		//给WebSocket注册监听方法
		
		//建立连接时触发
		ws.onopen = function() {
			isOpen = true;
			$("#content").html("<span style='display:block;float:left;margin:6px;clear:both;'>" +
					"系统消息</span><div class='msg msg-left clear'><p>欢迎进入聊天室，请注意文明用语。</p></div>");
		};
		
		//客户端接收服务端数据时触发
		ws.onmessage = function(e) {
			
			$("#userlist").html("");
			var result = eval("("+e.data+")");
			var from = result.from;
			var time = result.time;
			var content = result.msg;
			$("#count").html(result.count);
			var currentUser = $("#me").html();
			
			//如果有消息记录
			if(result.records){ 
				handleRecords(result.records[0],currentUser);
			}
			else{ //处理消息
				handleMessage(from, time, content,currentUser);
			}
			
			//显示在线用户列表
			users = result.userlist.toString().split("@");
			for(var i in users){
				if(users[i] != currentUser)
					$("#userlist").append("<li><a href='javascript:void(0)' " +
							"onclick='selectUser(this)'>"+users[i]+"</a></li>");
			}
			scrollDivToBottom();
		};
	
		//通信发生错误时触发
		ws.onerror = function() {
			$("#content").append("<div class='clear'><p><font color='red'>【系统消息】服务器异常</font></p></div>");
		};
	
		//连接关闭时触发
		ws.onclose = function() {
			$("#content").html("<div class='clear'><p><font color='red'>【系统消息】与服务器的连接已断开</font></p></div>");
			$("#btn").val("连接");
			$("#me").html("");
			$("#count").html("0");
		};
	}
	
	/** 处理消息 */
	function handleMessage(from,time,content,currentUser){
		if(content == ""){
			$("#content").append("");
		}
		else if(from == currentUser){
			$("#content").append("<span color='blue' style='display:block;float:right;margin:6px;clear:both;'>我（"+
					time+"）</span><div class='msg msg-right clear'><p>"+content+"</p></div>");
		}
		else{
			if(from == 'system'){ //系统消息
				$("#content").append("<span color='blue' style='display:block;float:left;margin:6px;clear:both;'>" +
						"系统消息 （"+time+"）</span><div class='msg msg-left clear'><p>"+content+"</p></div>");
			}else{
				$("#content").append("<span style='float:left;clear:both;'><a href='javascript:void(0)' " +
						"onclick='selectUser(this);'>"+from+"</a> （"+time+"）</span><div class='msg msg-left clear'><p>"
						+content+"</p></div>");
			}
		}
	}
	
	/** 处理聊天记录 */
	function handleRecords(records, currentUser){
		for(var i in records){
			var sender = records[i].sender;
			var receiver = records[i].receiver;
			var message = records[i].message;
			var recordtime = records[i].time;
			if(sender == currentUser)
				sender = '我';
			else
				sender = "<a href='javascript:void(0)' onclick='selectUser(this);'>"+sender+"</a>";
			if(receiver == currentUser){
				$("#content").append("<span color='blue' style='display:block;float:left;margin:6px;clear:both;'>"
						+ sender + " 对我说（"+recordtime+"）</span><div class='msg msg-left clear'><p>"
						+message+"</p></div>");
			}else {
				if(sender == '我'){
					$("#content").append("<span style='float:right;clear:both;'>我对 "+receiver+" 说"
							+"（"+recordtime+"）</span><div class='msg msg-right clear'><p>"+message+"</p></div>");
				}
				else{
					$("#content").append("<span style='float:left;clear:both;'>"+sender+" 对 "+receiver+" 说"
							+"（"+recordtime+"）</span><div class='msg msg-left clear'><p>"+message+"</p></div>");
				}
			}
		}
		if(records[0].sender != '')
			$("#content").append("<div class='clear' style='text-align:center;margin:5px auto;'>-----以上是历史消息-----</div>");
	}
	
	//断开连接
	function closeSocket(){
		if(isOpen){
			ws.close();
			isOpen = !isOpen;
		}
	}

	//发送消息
	function sendMsg() {
		var to = $("#to").val();
		var msg = $("#msg").html();

		if(!isOpen){
			alert("请先连接服务器");
		}
		else if(to == ""){
			alert("请选择聊天对象");
		}
		else if(to != '所有人' && !isOnline(to)){
			alert("该用户不在线，请重新选择聊天对象");
		}
		else if($.trim(msg).length == 0){
			alert("消息内容不能为空");
			$("#msg").html("");
		}
		else{			
			var from = $("#me").html();
			var face = $("#face").val();
			var to = $("#to").val();
			var color = $("#color").val();
			
			//给服务端发送消息
			var data = {"from":from,"face":face,"to":to,"color":color,"content":msg};
			ws.send(JSON.stringify(data));
			//清空消息发送区
			$("#msg").html("");
		}
	}

	//按钮点击事件
	$("#loginbtn").click(function() {
		if (!isOpen) {
			startSocket();
			$("#user").val("");
			$("#to").val("所有人");
			$(this).val('断开');
		} 
		else if (isOpen){
			closeSocket();
			$("#user").val("");
			$("#to").val("所有人");
			$(this).val('连接');
		}
	});
	
	/**使得滚动条始终在最底部，显示最新消息*/
	function scrollDivToBottom(){
		//原生代码实现滚动条自动到最底部
        //lct = document.getElementById('content');
        //lct.scrollTop=Math.max(0,lct.scrollHeight-lct.offsetHeight);
        $("#content").scrollTop($("#content")[0].scrollHeight);
	}

	/**选择聊天对象*/
	function selectUser(obj){
		$("#to").val($(obj).text());
	}
	
	/** 判断用户是否在线 */
	function isOnline(user){
		var flag = false;
		for(var i in users)
			if(users[i] == user){
				flag = true; 
				break;
			}
		return flag;
	}
	
	/** 选择表情 */
	function selectEmoji(id){
		$('#msg').append("<img src=\"../images\/chatroom\/biaoqing\/1 ("+ id +").gif\" />");
		$(".emoji").hide();
		sendMsg();
	}
	
	/** 隐藏表情区 */
	function hideEmoji(){
		$('.emoji').hide();
	}
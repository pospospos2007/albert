<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
 <meta name="keywords" content="<s:message code='chatroom.index'/>" />

    <title><s:message code='chatroom.index'/></title>

<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/commoneditor.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="<%=path%>/kindeditor/kindeditor.js"></script>
	
<style>
.chatBody{
	margin-top:5px;
}

</style>
  <div class="chatBody">
    <div class="container">
    	<div class="row">
    		<div class="col-md-3">
    		<div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title">当前登录用户</h3>
				  </div>
				  <div class="panel-body">
				    <div class="list-group">
					 <a href="#" class="list-group-item">你好，${USER_SESSION_KEY.username}</a>
					 <a href="<%=path%>/logout" class="list-group-item">退出</a>
					</div>
				  </div>
				</div>
    			<div class="panel panel-primary" id="online">
				  <div class="panel-heading">
				    <h3 class="panel-title">当前在线的其他用户</h3>
				  </div>
				  <div class="panel-body">
				    <div class="list-group" id="users">
					</div>
				  </div>
				</div>
<!-- 				<div class="panel panel-primary"> -->
<!-- 				  <div class="panel-heading"> -->
<!-- 				    <h3 class="panel-title">群发系统广播</h3> -->
<!-- 				  </div> -->
<!-- 				  <div class="panel-body"> -->
<!-- 				    <input type="text" class="form-control"  id="msg" /><br> -->
<!--  					<button id="broadcast" type="button" class="btn btn-primary">发送</button> -->
<!-- 						<input type="text" class="form-control"   /><br> -->
<!-- 				    	<button type="button" class="btn btn-primary">发送</button> -->
<!-- 				  </div> -->
<!-- 				</div> -->
    		</div>
  			<div class="col-md-9">
  				<div class="panel panel-primary">
				  <div class="panel-heading">
				    <h3 class="panel-title" id="talktitle"></h3>
				  </div>
				  <div class="panel-body">
				    <div class="well" id="log-container" style="height:400px;overflow-y:scroll">
				    
				    </div>
<!-- 				    	<input type="text" id="myinfo" class="form-control col-md-12" /> <br> -->
<!-- 				    	<button id="send" type="button" class="btn btn-primary">发送</button> -->
<!-- 							<input type="text" id="msg" class="form-control col-md-12" /> <br> -->
							<textarea id="msg"  editFlag='init'  style="width:700px;height:300px;"></textarea><br>
							<button id="broadcast" type="button" class="btn btn-primary">发送</button>
				    </div>
				</div>
  			</div>
    	</div>
    </div> 
<script>

	function getRootPath(){   
		return "<%=path%>/";
// 		var pathName = window.location.pathname.substring(1);   
// 		var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));   
// 		return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';   
	}

    $(document).ready(function() {
        // 指定websocket路径
        var websocket;
        if ('WebSocket' in window) {
// 			websocket = new WebSocket("ws://localhost:8080/albert/ws?uid="+${USER_SESSION_KEY.id});
			websocket = new WebSocket("ws://albert6.com:9999/ws?uid="+${USER_SESSION_KEY.id});
		}else if('MozWebSocket' in window) {
// 			websocket = new MozWebSocket("ws://localhost:8080/albert/ws"+${USER_SESSION_KEY.id});
			websocket = new MozWebSocket("ws://albert6.com:9999/ws"+${USER_SESSION_KEY.id});
		}else{
// 			websocket = new SockJS("http://localhost:8080/albert/ws/sockjs"+${USER_SESSION_KEY.id});
			websocket = new SockJS("http://albert6.com:9999/ws/sockjs"+${USER_SESSION_KEY.id});
		}
        
        //var websocket = new WebSocket('ws://localhost:8080/albert/ws');
        websocket.onmessage = function(event) {
       	 var data=JSON.parse(event.data);
       	 	if(data.from>0||data.from==-1){//用户或者群消息
            // 接收服务端的实时消息并添加到HTML页面中
            $("#log-container").append("<div class='bg-info'><label class='text-danger'>"+data.fromName+"&nbsp;"+data.date+"</label><div class='text-success'>"+data.text+"</div></div><br>");
            // 滚动条滚动到最低部
            scrollToBottom();
            }else if(data.from==0){//上线消息
            	if(data.text!="${USER_SESSION_KEY.username}")
            	{	
//             		$("#users").append('<a href="#" onclick="talk(this)" class="list-group-item">'+data.text+'</a>');
            		$("#users").append('<a href="'+getRootPath()+'userInfo?id='+data.userId+'" target="_blank" class="list-group-item">'+'<img src="'+getRootPath()+'uploadimage/'+data.avatar+'" class="img-circle pull-left" width="20px" height="20px" ></img>'+data.text+'</a>');
//             		alert(data.text+"上线了");
            	}
            }else if(data.from==-2){//下线消息
            	if(data.text!="${USER_SESSION_KEY.username}")
            	{	
            		$("#users > a").remove(":contains('"+data.text+"')");
//             		alert(data.text+"下线了");
            	}
            }
        };
        
        $.post(getRootPath()+"chatroom/onlineusers",function(data){
    		for(var i=0;i<data.length;i++){
//     			$("#users").append('<a href="#" onclick="talk(this)" class="list-group-item">'+data[i]+'</a>');
    			$("#users").append('<a href="'+getRootPath()+'userInfo?id='+data[i].id+'" target="_blank"  class="list-group-item">'+'<img src="'+getRootPath()+'uploadimage/'+data[i].avatar+'" class="img-circle pull-left" width="20px" height="20px" ></img>'+data[i].username+'</a>');
    		}
   		});
        
        $("#broadcast").click(function(){
        	KindEditor.instances[0].html('');
//         	clearEditor('textarea');
        	$.post(getRootPath()+"chatroom/broadcast",{"text":$("#msg").val()});
        });
        
        $("#send").click(function(){
        	$.post(getRootPath()+"chatroom/getuid",{"username":$(".chatBody").data("to")},function(d){
        		var v=$("#myinfo").val();
        		
				if(v==""){
					return;
				}else{
					var data={};
					data["from"]="${USER_SESSION_KEY.id}";
					data["fromName"]="${USER_SESSION_KEY.username}";
					data["to"]=d.id;
					data["text"]=v;
					websocket.send(JSON.stringify(data));
					$("#log-container").append("<div class='bg-success'><label class='text-info'>我&nbsp;"+new Date()+"</label><div class='text-info'>"+v+"</div></div><br>");
					scrollToBottom();
					$("#myinfo").val("");
				}
        	});
        	
        });
    });
   
   function talk(a){
   	$("#talktitle").text("与"+a.innerHTML+"的聊天");
   	$(".chatBody").data("to",a.innerHTML);
   	//TODO  修改div
   }
   function scrollToBottom(){
		var div = document.getElementById('log-container');
		div.scrollTop = div.scrollHeight;
	}
</script>    
  </div>
<%@ include file="/include/footer.jsp"%>

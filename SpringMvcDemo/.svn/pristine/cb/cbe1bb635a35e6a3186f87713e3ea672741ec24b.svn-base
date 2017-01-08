<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="./comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
	<style type="text/css">
		.message_m ul li{background:#f74373}
	</style>
	<script type="text/javascript">
	 	var methods = "";
	jQuery(document).ready(function($) {
		//全选
		$('#spanCheckAll').click(function(){
			var n = document.form1.mid.length;
			for(var i = 0;i < n;i++){
				if(!document.form1.mid[i].checked){
					document.form1.mid[i].checked = true;
				}
			}
		});
		//反选
		$('#oppClick').click(function(){
			var n = document.form1.mid.length;
			for(var i = 0;i < n;i++)
			{
				if(document.form1.mid[i].checked)
				{
					document.form1.mid[i].checked = false;
				}else{
					document.form1.mid[i].checked = true;
				}
			}
		});
		//已读
		$('#markedRead').click(function(){
			methods = "markedRead";
			updateMsg();
		});
		//未读
		$('#markedNoRead').click(function(){
			methods = "markedNoRead";
			updateMsg();
		});
		//删除
		$('#deleteMsg').click(function(){
			var readId=validation();
			if(readId=="") {
				return ;
			}
			methods = "delInnerMail";
			showAffirmDialogHTML("确定要删除这些站内信？",updateMsg);
		});
	})
	
	function updateMsg(){
		var readId=validation();
		if(readId==""){
			return;
		}
		$.ajax({
			type:"POST",
			url:methods,
			data:"readId="+readId,
			dataType:"text",
			success:function(res){
				switch (methods) {
				case "delInnerMail":
					window.location.reload();			
					break;
				case "markedRead":
					window.location.reload();			
					break;
				case "markedNoRead":
					window.location.reload();			
					break;
				default:
					break;
				}
			}
		});
		var n = document.form1.mid.length;
		for(var i = 0;i < n;i++){
			document.form1.mid[i].checked = false;
		}
	}
	
	//查找被选中的消息项
	function validation(){
		var readId="";
		var n = document.form1.mid.length;
		for(var i = 0;i < n;i++){
			if(document.form1.mid[i].checked){
				readId=readId+document.form1.mid[i].value+",";
			}
		}
		if(readId==""){
			showDialogHTML("请至少选择一条站内消息！");
			return readId;
		}
		return readId;
	}
	
	
	$(document).ready(function(){
		var i=0,j=0,k=0,ii=0,jj=0,kk=0,actionIndex=0,winScroll=0;

		//下拉加载
		$(document).on("scrollstart",function(){
			if(actionIndex) return;
			var winHeight = $(window).height();       // 窗口高度
			var allHeight = $(document).height();     // 文档高度
			var allScroll = $(document).scrollTop();  //滚动前scroll
			//判断当前底部是否显示和数据添加
			if(allScroll+winHeight>=allHeight-10&&allScroll >= winScroll){
				actionIndex=1;
				setTimeout(addText,0);
			}
		});
		$(document).on("scrollstop",function(){
			winScroll = $(document).scrollTop();      //滚动后scroll
		})
		
		//$("#claimsfooter").on("click",addText);
		function addText(){
			//添加条目
			var str;
			//查看更多资金记录
			//if($('#renGou').hasClass('on')){
				k=10+kk*5;
				kk++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/newMessagePage.action", //请求路径   
			       data:{"index":k}, 
			       success:function(data){
			    	   if(data.length==0){
			    		   $('#claimsfooter').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
			    		   var isRead ;
			    		   if(element.isread==false){
			    			   isRead = "message_r";
			    		   }else{
			    			   isRead = "message_r new";
			    		   }
			    		    str = '<div class="index_c">'+
							'<p class="fl">' +
							'<input type="checkbox" value="'+element.id+'" name="mid" />'+
						'</p>'+
						'<div  class="'+
						isRead+
						'">'+
							'<ul>'+
								'<li style="width: 25%"><b>['+element.messageType+']</b></li>'+
								'<li style="width: 45%">'+element.sendtime+'</li>'+
								'<li>'+element.senderName+'</li>'+
							'</ul>'+
							'<div class="clear"></div>'+
							'<div style="margin-left: 2%">'+element.title+'</div>'+
						'</div>'+
						'<div class="clear"></div>'+
					'</div>';
							
			    			$("#claimsfooter").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
			})
			//}
			actionIndex = 0;
		}
	});
	
</script>
<body>
	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl"><img
				src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>消息中心
		</h2>
	</div>
	<form method="post" action="" name="form1">
		<div class="message_m">
			<ul>
				<li id="spanCheckAll">全选</li>
				<li id="oppClick" >反选</li>
				<li id="markedRead" >已读</li>
				<li id="markedNoRead" >未读</li>
				<li style="margin-right:0" id="deleteMsg" >删除</li>
			</ul>
			<div class="clear"></div>
		</div>
		<c:forEach items="${msgList}" var="item">
			<div class="index_c">
				<p class="fl">
					<input type="checkbox" value='${item.id}' name="mid" />
				</p>
				<div  class="${item.isread==false?'message_r':'message_r new'}">
					<ul>
						<li style="width: 25%"><b>[${item.messageType}]</b></li>
						<li style="width: 45%">${item.sendtime}</li>
						<li>${item.senderName }</li>
	
					</ul>
					<div class="clear"></div>
					<div style="margin-left: 2%">${item.title }</div>
				</div>
				<div class="clear"></div>
			</div>
		</c:forEach>
		<c:if test="${fn:length(msgList)>=10}">
			<div id="claimsfooter"><center><font size="3">下拉加载更多...</font></center></div>
		</c:if>
	</form>
	

	<%@ include file="./comm/bott.jsp"%>
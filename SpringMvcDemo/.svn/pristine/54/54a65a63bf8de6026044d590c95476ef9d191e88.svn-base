var putStorage = function(key,value){
	window.localStorage.setItem(key,value);
}

var getStorage = function(key){
	return window.localStorage.getItem(key);
}

var removeStorage = function(key){
	window.localStorage.removeItem(key);
}

var callBackStorage = function(e){
	initHistorty();
}

var initHistorty = function(){
	// 限制保存25张
	for(var i=1;i<25;i++){
		var dataUrl = getStorage(i);
		if(dataUrl!=null&&dataUrl!=''){
			item(dataUrl,i);
		}
	}
}

// 在绘画记录中添加图片
function item(imgurl,i){
	var itemHtml='<div class="item"><img src = '+imgurl+' id="history_'+i+'"/><a href="javascript:void(0);" id="history_del_'+i+'">删除</a><a href="'+imgurl+'" id="history_download_'+i+'" download="picture.png">下载</a></div>';
	$("#showHistory h2").after(itemHtml).fadeIn(400);
}

//保存图片，base64格式
var save = function(){
	for(var i = 1;i<=25;i++){
		var dataUrl = getStorage(i);
		if(dataUrl == null || dataUrl == ''){
			putStorage(i,canvas.toDataURL());
			item(canvas.toDataURL(),i);
			// initHistorty();
			return ;
		}
	}			
}


$(function(){
	if (window.addEventListener) {    
		window.addEventListener("storage", callBackStorage, false); 
	} else {     
		window.attachEvent("onstorage", callBackStorage); 
	};

	

	initHistorty();

	// 删除
	$(".item a").live("click",function(){
		var id = $(this).attr("id");
		if(id.indexOf("del")!='-1'){
			var index = id.substring(id.length-1);
			removeStorage(index);
			$(this).parent().fadeOut(200,function(){
				$(this).remove();
			});
			// initHistorty();
		}
	});
	

});




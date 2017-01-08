function uploadFile(context, object){
	var imageId = object.id + "View";
	var hiddenId = object.id + "Path";
	var parentId = object.id + "Parent";
	var anchorId = object.id + "Anchor";
	
	$.ajaxFileUpload
    (
        {
            url: 'file/upload.do', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: object.id, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {	
            	if(data && data.success){
            		var path = context + data.path.replace(/\\/ig, "/");
                    var imageObj = $("#" + imageId);
                    var anchorObj = $("#" + anchorId);
                    
                    if(imageObj.length <= 0){
                    	$("<div class=\"upload_pictures\"><a target=\"_blank\" href=\"\" id=\"" + anchorId + "\"><img id=\"" + imageId + "\" src=\"\"/></a><a class=\"remove\" onclick=\"deleteFile('" + hiddenId + "', '" + imageId + "')\"></a></div>").insertBefore($("#" + parentId));
                    	imageObj = $("#" + imageId);
                    	anchorObj = $("#" + anchorId);
                    }
                    imageObj.attr("src", path);
                    anchorObj.attr("href", path);
                    
                    $("#" + hiddenId).attr("value", data.path.replace(/\\/ig, "/"));
                    
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                      //      alert(data.error);
                        } else {
                      //      alert(data.msg);
                        }
                    }
            	}

            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                //alert(e);
            }
        }
    );
    return false;
}

function uploadFileAndHide(context, object){
	uploadFile(context, object);
}

function uploadFileAndMove(context, object, varName, indexId){
	var index = $("#" + indexId).attr("value");
	$("#" + indexId).attr('value', ++index);
	var parent = $(object).parent();
	
	var imageId = object.id + index + "View";
	var hiddenId = object.id + index + "Path";
	var anchorId = object.id + index + "Anchor";
	
	$.ajaxFileUpload
    (
        {
            url: 'file/upload.do', //用于文件上传的服务器端请求地址
            secureuri: false, //是否需要安全协议，一般设置为false
            fileElementId: object.id, //文件上传域的ID
            dataType: 'json', //返回值类型 一般设置为json
            success: function (data, status)  //服务器成功响应处理函数
            {	
            	if(data && data.success){
            		var path = context + data.path.replace(/\\/ig, "/");
                    
                    $("<div class=\"upload_pictures\">" +
                    		"<a target=\"_blank\" href=\"" + path + "\" id=\"" + anchorId + "\">" +
                    			"<img id=\"" + imageId + "\" src=\"" + path + "\"/>" +
                    			"</a><input type=\"hidden\" name=\"" + varName + "\" id=\""+ hiddenId + "\" value=\"" + data.path.replace(/\\/ig, "/") + "\">" +
                            "<a class=\"remove\" onclick=\"deleteFile('" + hiddenId + "', '" + imageId + "')\"></a></div>")
                    .insertBefore(parent);
                    
                    if (typeof (data.error) != 'undefined') {
                        if (data.error != '') {
                      //      alert(data.error);
                        } else {
                      //      alert(data.msg);
                        }
                    }
            	}

            },
            error: function (data, status, e)//服务器响应失败处理函数
            {
                //alert(e);
            }
        }
    );
    return false;
}

function deleteFile(hiddenImagePathId, imageId){
	$("#" + hiddenImagePathId).attr("value", "");
	var image = $("#" + imageId);
	image.attr("src", "");
	
	image.parent().parent().remove();
}
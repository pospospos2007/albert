var editor;
$(function() {
	window.setTimeout(function() {
		initEditor();
	}, 100);
}); 
var url=getRootPath()+'/kindedtor/file_upload';
function initEditor(){
	$("[editFlag='init']").each(function(){
		var id=$(this).attr("id");
		var width= $(this).css("width");
		var height = $(this).css("height");
		editor = KindEditor.create('#'+id, {
			width : width,
			height : height,
			items : 
//				[   'fullscreen','|','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
//                        'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
//                        'insertunorderedlist', 'image', 'emoticons','baidumap', 'link' ],
                        [ 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
                         'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
                         'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
                         'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
                         'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                         'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
                         'anchor', 'link', 'unlink', '|', 'about'
                 ],
            uploadJson : url,
			allowFileManager : false,
			afterBlur:function(){
				this.sync();
			}
		});
	});
}


function getRootPath()   
{   
 var pathName = window.location.pathname.substring(1);   
 var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));   
 return window.location.protocol + '//' + window.location.host + '/';   
}
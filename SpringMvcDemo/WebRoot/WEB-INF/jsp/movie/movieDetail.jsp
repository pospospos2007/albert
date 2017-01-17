<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ include file="/include/head.jsp"%>
<%@ taglib uri="/WEB-INF/tld/pager.tld" prefix="pager" %> 

    <title>${movie.name }</title>
    <meta name="keywords" content="${movie.name}" />
  
        ${movie.metadata}
<script type="text/javascript">
function backtoHome(){
	history.go(-1);
}
// var curUrl = location.href.toLowerCase();
// baseUrl =curUrl.indexOf("SpringMvcDemo") > -1 ? "/SpringMvcDemo/" : "";
$( document ).ready(function() {
 	$("body").find("img").each(function(){
//  		var tempPointer =$(this);
 		var str = $(this).attr("src");
 		var newUrl="";
 		if(null!=str){
	 		$.ajax({
				url : "<%=path%>/movie/fileExchange",
				type : "GET",
				data:{"url":str}, 
				async:false,
				dataType : "json",
				success : function(data) {
					newUrl = data.url;
				}
			});
 		
 		}
 		$(this).attr("src","<%=path%>/uploadfile/"+newUrl);
 		$(this).attr("srcset","");
 	});
	
});


</script>
<%@ include file="/include/footer.jsp"%>

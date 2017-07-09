<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<%@ taglib uri="/WEB-INF/tld/pager.tld" prefix="pager" %>
<!-- <link href="http://vjs.zencdn.net/5.8.8/video-js.css" rel="stylesheet"> -->
<!-- If you'd like to support IE8 -->
<!-- <script src="http://vjs.zencdn.net/ie8/1.1.2/videojs-ie8.min.js"></script>  -->
<!-- <script src="http://vjs.zencdn.net/5.8.8/video.js"></script> -->

    <title>
    	谷歌-搜索
    </title>


<style>
 
 /*page*/
#page{
	width:100%;
	height:26px;
	margin:50px 0;
	text-align:center;
}
#page ul{
	display:inline-block;
	margin:0 5px;
}
#page span{
	display:inline-block;
	line-height:20px;
	height:24px;
	border:1px solid #999;
	padding:0 0.5em;
	border-radius:2px;
}
#page span.onC a{
	color:#b8b8b8;
}
#page span i{
	display:inline-block;
	width:5px;
	height:17px;
	margin:0 5px 0 0;
	background:url(<%=path%>/images/listCon.png) no-repeat;
	background-position:-16px -1px;
	vertical-align:middle;
}
#page span.onC i{
	background-position:-6px -1px;
}
#page span.last i{
	background-position:-6px -15px;
	margin:0 0 0 5px;
}
#page span.last i.onC{
	background-position:-16px -15px;
}
#page ul li{
	display:inline-block;
	line-height:20px;
	height:24px;
	border:1px solid #999;
	border-radius:2px;
	margin: 0 2px;
	transition:all 0.5s;
}
#page ul li:hover{
	border:1px solid #3598da;
	background:#3598da;
}
#page ul li.onC{
	border:1px solid #3598da;
	background:#3598da;
}
#page ul li.onC a,#page ul li:hover a{
	color:#fff;
}
#page ul li a{
	display:inline-block;
	line-height:20px;
	padding:0 0.5em;
	border-radius:2px;
}
/*     图片展览css样式 */
    #fixImg{
	width:100%;
	height:100%;
 	background:url(<%=path%>/images/personb.png); 
	text-align:center;
	position:fixed;
	top:0px;
	display:none;
	}
	#fixImg img{
		position:relative;
		top:70px;
		max-width:700px;
		max-height:700px;
	}
/* 	End */
  </style>
  	<form action="" method="post" id="searchForm"  >
	 <div class="row head">
	 
	                <div class="col-lg-12">
	                    <div class="input-group">
	<!--                     	<span class="input-group-btn"> -->
	<!-- 	                    	<select id="searchType" name="searchType" class="form-control" style="width: auto;">   -->
	<!-- 				                <option value="0" >按用户</option>   -->
	<%-- 				                <option value="1" <c:if test="${!empty tsh&&tsh.searchType eq 1}">selected="selected"</c:if> >按内容</option>   --%>
	<!-- 				            </select>  -->
	<!--                         </span> -->
	                         <input type="text" class="form-control" id="wd" autocomplete="off" name="wd" value="${wd}" placeholder="">
	                         <input type="hidden"  id="start"  value="${start}" >
	                        <span class="input-group-btn">
	                            <button class="btn btn-info" id="searchButton" type="button" onclick="searchByKey()"  ><span class="glyphicon glyphicon-search">搜索</span></button>
	                        </span>
	                        
	                    </div>
	                    
	                   		<div id="list" class="input-group" align="left">
							</div>
						
	                </div>
	 </div>
 	</form>
  </br>          
<form action="<%=path%>/google/search" method="post" id="listform" name="listform">
<input type="hidden" name="searchId" value="${tsh.id}"></input>
<input type="hidden" name="searchType" value="${tsh.searchType}"></input>
<input type="hidden" name="searchKey" value="${tsh.searchKey}"></input>
	<div class ="container">
   		<div class="u-account-box undis" id="twitterMedia">
       		<div id="tabCont">
           		<section>
               		<section class="ukindeditor of">
                   		<section class="clearfix" >
                   		
                   		<c:forEach items="${list}" var="result" varStatus="status">
							<div class="panel panel-default">
							  <div class="panel-heading">
							    <h3 class="panel-title">
							    <a href="${result.formattedUrl }" data-content="" target="_blank"
							     screenName="${result.htmlTitle}" title="${result.htmlTitle}" class="bind_hover_card" data-toggle="popover" data-placement="bottom" data-trigger="hover">
							    <b>${result.htmlTitle}</b> 
							    </a>
							    </h3>
							  </div>
							  <div class="panel-body">
							   <p>
							   <img src="${result.src}"  width="${result.width}" height="${result.height}" class="img-rounded twitterMedia"></img>
							   ${result.htmlSnippet}
 								</p>
							  </div>
							</div>
						</c:forEach>
                             	<section class="clear"></section>
                         		</section>
					</section>
                 </section>
             </div>
   		</div>
	</div>
	<c:if test="${start ne 1 }">
	<button type="button"  class="btn btn-default pull-right" onclick="jumpPage('${wd}',${start-10})">上一页</button>
	</c:if>
    <button type="button"  class="btn btn-success pull-right" onclick="jumpPage('${wd}',${start+10})">下一页</button>
          
	
		     
 </form>
 		<div id="fixImg"><img src=""></img></div> 
 		
 		<div class="modal fade" id="loadingModal">
		    <div style="width: 200px;height:20px; z-index: 20000; position: absolute; text-align: center; left: 50%; top: 50%;margin-left:-100px;margin-top:-10px">
		        <div class="progress progress-striped active" style="margin-bottom: 0;">
		            <div class="progress-bar" style="width: 100%;"></div>
		        </div>
		        <h5>正在加载...</h5>
		    </div>
		</div>
  
		<script>
		
		function jumpPage(wd,start){
			window.location.href =$("#listform").val()+"?wd=" +wd+ "&start="+start;
		}
		
		function searchByKey(){
			var wd=$("#wd").val();
			window.location.href =$("#listform").val()+"?wd=" +wd;
		}
		
		$(function() {  
		    $("[data-toggle='popover']").popover({  
		        html : true,    
		        delay:{show:200, hide:500}
// 		        content: function() {  
// 		          return content();    
// 		        }   
		    });  
		});  
		
		//模拟动态加载内容(真实情况可能会跟后台进行ajax交互)
		function content() {
		    var data = $("<form><ul><li><span aria-hidden='true' class='icon_globe'></span>&nbsp;<font>粉丝数:</font>7389223</li>" +
		             "<li><span aria-hidden='true' class='icon_piechart'></span>&nbsp;<font>关注:</font>265</li>" +
		             "<li><span aria-hidden='true' class='icon_search_alt'></span>&nbsp;<font>微博:</font>645</li>" +
		             "<li><span aria-hidden='true' class='icon_pens_alt'></span>&nbsp;<font>所在地:</font>台湾</li>" +
		             "<input id='btn' type='button' value='关注' onclick='test()'/></form>");
		    
		    return data;
		}
		//模拟悬浮框里面的按钮点击操作
		function test() {
		    alert('关注成功');
		}
		function showModal(){
			$("#loadingModal").modal('show');
		}
		function search(){
			showModal();
			var wd=$("#wd").val().trim();
// 			var searchType=$("#searchType").val();
			if(null==wd||""==wd){
				alert("请输入内容！");
				return;
			}
			if(containSpecial(wd)){
				alert("不能包含特殊字符！");
				return;
			}
			window.location.href ='<%=path%>/google/search?wd='+wd;
		}
		/** 
		 * 判断是否含有汉字 
		 * @param value 
		 * @return 
		 */  
		 function isChinese(s){
            var patrn=/[\u4E00-\u9FA5]|[\uFE30-\uFFA0]/gi;
            if(!patrn.exec(s)){
            	return false;
            }else{
            	return true;
            }
         }
		
		 $( document ).ready(function() {
			 	$("#twitterMedia").find(".twitterMedia").each(function(){
			 		var str = $(this).attr("src");
			 		var newUrl="";
			 		if(null!=str){
				 		$.ajax({
							url : "<%=path%>/twitter/fileExchange",
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
			 	});
				
			});
//	     图片展览js
		    $(".StarConP").click(function(){
				var src = $(this).attr("src");
				$('#fixImg').css("display","block");
				$('#fixImg').children("img").attr("src",src);
			});
			
			$("#fixImg").click(function(){
				$(this).css("display","none");
			});
//			 	end
			//判断字符中是否包含有特殊字符：  
			function containSpecial( s )      
			{      
			    var containSpecial = RegExp(/[(\ )(\~)(\!)(\@)(\#)(\$)(\%)(\^)(\&)(\*)(\()(\))(\-)(\_)(\+)(\=)(\[)(\])(\{)(\})(\|)(\\)(\;)(\:)(\')(\")(\,)(\.)(\/)  (\<)(\>)(\?)(\)]+/);      
			    return ( containSpecial.test(s) );      
			}
			function searchUser(obj){
				var searchKey = $(obj).attr("screenName");
				var searchType  = 0;
				$.ajax({
					url : "<%=path%>/twitter/check",
					type : "GET",
					async:false,
					data:{"searchKey":searchKey,"searchType":searchType}, 
					dataType : "json",
					success : function(result) {
						if(result.status){
							window.location.href ="<%=path%>/twitter/search?searchId="+result.searchId+"&searchType="+searchType+"&searchKey="+searchKey;
						}else{
							alert(result.msg);
						}
					}
				});
			}
		</script>
<%@ include file="/include/footer.jsp"%>
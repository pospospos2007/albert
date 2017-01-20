<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/include/head.jsp"%>
<%@ taglib uri="/WEB-INF/tld/pager.tld" prefix="pager" %> 
    <title>
    	Twitter-Discovery
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

  </style>
  
 <div class="row head">
                <div class="col-lg-12">
                    <div class="input-group">
                    	<span class="input-group-btn">
	                    	<select id="searchType" name="searchType" class="form-control" style="width: auto;">  
				                <option value="0">按用户</option>  
				                <option value="1">按内容</option>  
				            </select> 
                        </span>
                        <input type="text" class="form-control" id="searchKey" autocomplete="off" name="searchKey" value="${tsh.searchKey}" placeholder="">
                        <span class="input-group-btn">
                            <button class="btn btn-info" type="button" onclick="search()" ><span class="glyphicon glyphicon-search">搜索</span></button>
                        </span>
                        
                    </div>
                    
                   		<div id="list" class="input-group" align="left">
						</div>
					
                </div>
 </div>
  </br>          
<form action="<%=path%>/twitter/search" method="post" id="listform" name="listform">
<input type="hidden" name="searchId" value="${tsh.id}"></input>
<div class ="container">
   		<div class="u-account-box undis">
       		<div id="tabCont">
           		<section>
               		<section class="ukindeditor of">
                   		<section class="clearfix">
                   		
                   		<c:forEach items="${pageView.voList}" var="twitterPost">
							<div class="panel panel-default">
							  <div class="panel-heading">
							    <h3 class="panel-title"><a href="#" class="bind_hover_card" data-toggle="popover" data-placement="bottom" data-trigger="hover">${twitterPost.name}</a></h3>
							  </div>
							  <div class="panel-body">
							   <p>
							   <img src="${twitterPost.avatar}"  width="40px" height="40px" class="img-circle"></img>
							   ${twitterPost.text}
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
	
	<div id="page">
      <pager:pager pageSize="${pageView.pageSize}"  currentPageId="currentPage"  pageNo="${pageView.currentPage}"   fromId="listform" recordCount="${pageView.recordCount}"/>
     </div>
		     
 </form>
 
  
		<script>
		$(function() {  
		    $("[data-toggle='popover']").popover({  
		        html : true,    
		        title: title(),    
		        delay:{show:200, hide:100},  
		        content: function() {  
		          return content();    
		        }   
		    });  
		});  
		
		//模拟动态加载标题(真实情况可能会跟后台进行ajax交互)
		function title() {
		    return '田喜碧Hebe(节制的人生)';
		}

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
		function search(){
			var searchKey=$("#searchKey").val().trim();
			var searchType=$("#searchType").val();
			if(null==searchKey||""==searchKey){
				alert("请输入内容！");
				return;
			}
			if(isChinese(searchKey)&&searchType==0){
				alert("推特用户名不能为中文！");
				return ;
			}
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
		</script>
<%@ include file="/include/footer.jsp"%>

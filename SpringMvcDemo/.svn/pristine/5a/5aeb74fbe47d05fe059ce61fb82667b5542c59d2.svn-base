<%@ page contentType="text/html;charset=UTF-8"%>

<%@ include file="../mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery.mobile-1.3.2.min.js"></script>
   <div class="index">
		<h1>
		        <c:choose >
		        <c:when test="${userVo==null}"><a data-ajax="false" href="<%=request.getContextPath() %>/mobile/account"></c:when>
		        <c:otherwise><a data-ajax="false" href="<%=request.getContextPath() %>/accountInfo"></c:otherwise>
		        </c:choose>
			   <img class="fl" src="<%=request.getContextPath() %>/mobile/images/index_login.jpg" /></a>
			   <a data-ajax="false" href="#"><img class="fr" src="<%=request.getContextPath() %>/mobile/images/index_search.jpg" /></a>Albert
		</h1>
	</div>
	<div class="index_b">
		<ul>
		   <li><a href=""><div style="background:url(<%=request.getContextPath() %>/mobile/images/index_ban1.jpg) center center no-repeat;background-size:100% 100%;height:160px"></div></a></li>
		   <li><a href=""><div style="background:url(<%=request.getContextPath() %>/mobile/images/index_ban2.jpg) center center no-repeat;background-size:100% 100%;height:160px"></div></a></li>
		   <li><a href=""><div style="background:url(<%=request.getContextPath() %>/mobile/images/index_ban3.jpg) center center no-repeat;background-size:100% 100%;height:160px"></div></a></li>
	   </ul>
	</div>
	
  <div class="index_c index_c_n" style="padding-bottom:0">
  <ul style="width:250px">
   <li class="on" class="star" style="margin-right:20px" id="starLi">论坛<div></div></li>
   <li style="margin-right:20px" id="normalLoan">日报<div></div></li>
<!--    <li id="renGou">债权转让<div></div></li> -->
  </ul><div class="blank5"></div>
 </div>
 
 
	<div id="l_invest">
	<div>
          <c:if test="${empty themelist }"> <div class="index_c"><h3>暂无数据</h3></div></c:if>
          	  <c:forEach items="${themelist}" var="theme" begin="0" end="9" varStatus="i">
          	   <div class="index_c">
          	    <h3><a data-ajax="false" href="<%=request.getContextPath() %>/message/getThemeDetail?id=${theme.id}">${fn:substring(theme.theme,0,20)}</a></h3>
			  <p style="line-height:36px">  
                     	<a data-ajax="false"  href="<%=request.getContextPath() %>/message/getThemeDetail?id=${theme.id}" class="btn btn-primary btn-invest-size invest-btn">查看</a>
					发表于 ${theme.addTime }
				</p>
			 </div>
          	  </c:forEach>
          	  <div id="starfooter">下拉加载更多...</div>
          </div>
          
          
         <div style="display:none;">
         <c:if test="${empty zhihuAirticlelist }"> <div class="index_c"><h3>暂无数据</h3></div></c:if>
         	  <c:forEach items="${zhihuAirticlelist}" var="article" begin="0" end="9" varStatus="i">
         	   <div class="index_c">
         	    <h3><a data-ajax="false" href="<%=request.getContextPath() %>/message/getZhihuArticleDetail?id=${article.id}">${article.title }</a></h3>
				
				<p style="line-height:36px">  
<%--                      	<a data-ajax="false"  href="<%=request.getContextPath() %>/message/getZhihuArticleDetail?id=${article.id}" class="btn btn-primary btn-invest-size invest-btn">查看</a> --%>
										<a data-ajax="false" target="_blank" href="http://daily.zhihu.com/story/${article.id}" class="btn btn-primary btn-invest-size invest-btn">查看</a>
					发表于  ${article.addTime }
				</p>
		 </div>
         	  </c:forEach>
         	  	<div id="loanfooter">下拉加载更多...</div>
         </div>
	      
	</div>

<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/unslider.min.js"></script>	
 <script>
	if(window.chrome){$('.index_b li a div').css('background-size', '100% 100%');}
	$('.index_b').unslider({
		fluid: true,
		dots: true,
		speed: 500
	});
	
	$(document).ready(function(){
		var i=0,j=0,k=0,ii=0,jj=0,kk=0,actionIndex=0,winScroll=0;
		$('.index_c ul li').click(function(){
			$('#l_invest > div').eq($('.index_c ul li').index(this)).show().siblings().hide();
			$('.index_c ul li').eq($('.index_c ul li').index(this)).addClass('on').siblings().removeClass('on');
		});	
		
		if(${selectPage!=null && selectPage!=''}){
        	$("#renGou").click();
        }
		
		if(${message!=null && message!=''}){
			showDialogHTML($("#message").val());
			$("#renGou").click();
		}
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
		function addText(){
			//添加条目
			var str;
			//查看更多明星标
			if($('#starLi').hasClass('on')){
				i=10+ii*5;
				ii++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"<%=request.getContextPath() %>/message/findNextTheme", //请求路径   
			       data:{"index":i}, 
			       success:function(data){
			    	   if(data.length==0){
			    		   $('#starfooter').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
		    			   str = '<div class="displayN index_c"><h3><a data-ajax="false" href="<%=request.getContextPath() %>/message/getThemeDetail?id='+element.id+'">'+element.theme.substring(0,20)+'</a></h3><p style="line-height:36px"><a data-ajax="false" href="'+
			    				 '<%=request.getContextPath() %>/message/getThemeDetail?id='+element.id+'" class="btn btn-primary btn-invest-size invest-btn">查看</a>'+
			    				 '发表于  '+element.addTime+
	    						 '</p></div>';
								
			    		 	$("#starfooter").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
			})
			}
			
			
			
			//查看更多zhihu
			if($('#normalLoan').hasClass('on')){
				j=10+jj*5;
				jj++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"<%=request.getContextPath() %>/message/findNextZhihuAirticle", //请求路径   
			       data:{"index":j}, 
			       success:function(data){
			    	   if(data.length==0){
			    		   $('#loanfooter').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
			    		   str = '<div class="displayN index_c"><h3><a data-ajax="false" target="_blank" href="http://daily.zhihu.com/story/'+element.id+'">'+element.title+'</a></h3><p style="line-height:36px"><a data-ajax="false" href="'+
		    				 '<%=request.getContextPath() %>/message/getZhihuArticleDetail?id='+element.id+'" class="btn btn-primary btn-invest-size invest-btn">查看</a>'+
		    				 '发表于  '+element.addTime+
  						 	'</p></div>';
			    		   $("#loanfooter").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
			})
			}
			
			
			
			//查看更多债权认购
			if($('#renGou').hasClass('on')){
				k=10+kk*5;
				kk++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/findNextClaimsLoan.action", //请求路径   
			       data:{"index":k}, 
			       success:function(data){
			    	   //alert(data.voList);
			    	   if(data.voList.length==0){
			    		   $('#claimsfooter').remove();
			    		   return;
			    	   }
			    	   $(data.voList).each(function(index, element) {
			    		   //alert(data.voList);
			    		   //alert(element.nickName)
			    		   if(${empty userVo.nickName}){
			    			   str = '<div class="index_c index_c_n"><h3><a data-ajax="false" href="${base}/toLoginPage?selectPage=selectPage" class="btn">认购</a><em><a data-ajax="false" href="${base}/loanDetail?loanId='+element.loanId+'&selectZhaiQuan=selectZhaiQuan">'+element.loanTitle+'</a></em><em class="wid5"><b>'+Number(element.loanAnnualInterestRate).toFixed(1)+'%</b></em></h3><div class="blank10"></div><dl><dd>债权本金</dd><dd>转让价格</dd><dd>剩余天数</dd><dd>净收益</dd><dd><span>'+element.investAmount+'</span>元</dd><dd><span>'+element.soldPrice+'</span>元</dd><dd><span>'+element.restDays+'</span>天</dd><dd><span>'+Number(element.interest).toFixed(2)+'</span>元</dd></dl><div class="clear"></div></div>';
								
			    		   }else{
			    			   str = '<div class="index_c index_c_n"><h3><a data-ajax="false" href="${base}/buyDeb?loanId='+element.id+'" class="btn">认购</a><em><a data-ajax="false" href="${base}/loanDetail?loanId='+element.loanId+'&selectZhaiQuan=selectZhaiQuan">'+element.loanTitle+'</a></em><em class="wid5"><b>'+Number(element.loanAnnualInterestRate).toFixed(1)+'%</b></em></h3><div class="blank10"></div><dl><dd>债权本金</dd><dd>转让价格</dd><dd>剩余天数</dd><dd>净收益</dd><dd><span>'+element.investAmount+'</span>元</dd><dd><span>'+element.soldPrice+'</span>元</dd><dd><span>'+element.restDays+'</span>天</dd><dd><span>'+Number(element.interest).toFixed(2)+'</span>元</dd></dl><div class="clear"></div></div>';
								
			    		   }
			    		  	$("#claimsfooter").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
			})
			}
			actionIndex = 0;
		}
	});
</script>

<%@ include file="../mobile/comm/bott.jsp"%>
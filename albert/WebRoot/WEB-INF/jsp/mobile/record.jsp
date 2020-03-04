<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<script type="text/javascript" src="<%=request.getContextPath() %>/mobile/js/jquery.idTabs.min.js"></script>
<script type="text/javascript" src="${base}/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body style="background:#ffffff">

 <div class="login search_t">
	 <h2><a href="<%=request.getContextPath() %>/accountInfo" title="账户中心" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg"/></a>投资记录</h2>
 </div>  
<div id="adv2" class="invest_re"> 
  <ul>
		<li id="recordListBack" class="on">回款中的标<div></div></li>
		<li id="recordListRaise" >筹款中的标<div></div></li>
		<li id="recordListFinish" style="margin: 0">已完成的标<div></div></li>
	</ul>
  </div>
  
  <div id="invest_rec" class="invest_rec">
  <div>
	<ul id="recordListBack1" style="display: block; ">
		<c:if test="${empty back}"> <div class="invest_re" ><h3><center><font size="3" >暂无数据</font></center></h3></div></c:if>
		<c:forEach items="${back}" var="dataBack" varStatus="st" begin="0" end="9">
			<div class="invest_re">
				<a href="<%=request.getContextPath() %>/mobileLoanDetail?loanId=${dataBack.loanId}&id=${dataBack.id}&item=back"><li>
				<c:if test="${fn:length(dataBack.title)>9}">${fn:substring(dataBack.title, 0, 9)} ...</c:if>
				<c:if test="${fn:length(dataBack.title)<=9}">${dataBack.title}</c:if>
				<span><img 	src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></span></li></a>
			</div>
		</c:forEach>
		<c:if test="${fn:length(back)>=10}">
			<div id="mBackList"><center><font size="3">下拉加载更多...</font></center></div>
		</c:if>	
	</ul>
	</div>
	<div style="display:none;">
	<ul id="recordListRaise1" >
		<c:if test="${empty raise}"> <div class="invest_re" ><h3><center><font size="3" >暂无数据</font></center></h3></div></c:if>
		<c:forEach items="${raise}" var="dataRaise" varStatus="st" begin="0" end="9">
			<div class="invest_re">
				<a href="<%=request.getContextPath() %>/mobileLoanDetail?loanId=${dataRaise.loanId}&id=${dataRaise.id}&item=raise"><li>
				<c:if test="${fn:length(dataRaise.title)>9}">${fn:substring(dataRaise.title, 0, 9)} ...</c:if>
				<c:if test="${fn:length(dataRaise.title)<=9}">${dataRaise.title}</c:if>
				<span><img 	src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></span></li></a>
			</div>
		</c:forEach>
		<c:if test="${fn:length(raise)>=10}">
			<div id="mRaiseList"><center><font size="3">下拉加载更多...</font></center></div>
		</c:if>
	</ul>
	</div>
	<div style="display:none;">
	<ul id="recordListFinish1" >
		<c:if test="${empty finish }"> <div class="invest_re" ><h3 ><center><font size="3" >暂无数据</font></center></h3></div></c:if>
		<c:forEach items="${finish}" var="dataFinish" varStatus="st" begin="0" end="9">
			<div class="invest_re">
				<a href="<%=request.getContextPath() %>/mobileLoanDetail?loanId=${dataFinish.loanId}&id=${dataFinish.id}&item=finish"><li>
				<c:if test="${fn:length(dataFinish.title)>9}">${fn:substring(dataFinish.title, 0, 9)} ...</c:if>
				<c:if test="${fn:length(dataFinish.title)<=9}">${dataFinish.title}</c:if>
				<span><img 	src="<%=request.getContextPath() %>/mobile/images/poi_gt.gif" /></span></li></a>
			</div>
		</c:forEach>
		<input id="message" type="hidden" value="${message}">
		<c:if test="${fn:length(finish)>=10}">
			<div id="mfinishList"><center><font size="3">下拉加载更多...</font></center></div>
		</c:if>
	</ul>
	</div>
</div>
<script type="text/javascript" src="js/unslider.min.js"></script>	
<script> 
  $(document).ready(function(){
		var i=0,j=0,k=0,ii=0,jj=0,kk=0,actionIndex=0,winScroll=0;
		$('.invest_re ul li').click(function(){
			$('#invest_rec > div').eq($('.invest_re ul li').index(this)).show().siblings().hide();
			$('.invest_re ul li').eq($('.invest_re ul li').index(this)).addClass('on').siblings().removeClass('on');
		});	
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
			//查看更多回款中的标
			if($('#recordListBack').hasClass('on')){
				i=10+ii*5;
				ii++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/myInvestRecordMobile.action", //请求路径   
			       data:{"index":i,"type":0}, 	//将当前多少条放入index中，查询时从第index开始向后查
			       success:function(data){
			    	   if(data.length==0){
			    		   $('#mBackList').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
			    		   var title = element.title;
			    		   if(title.length>10){
			    			   title = title.substring(0,9)+"...";
			    		   }
			    		   str = "<div class='invest_re invest_re'><a href='<%=request.getContextPath() %>/mobileLoanDetail?loanId=" +element.loanId+ "&id="+element.id+ "&item=back'"+"> <li>"+title+"<span><img src='<%=request.getContextPath() %>/mobile/images/poi_gt.gif' /></span></li></a></div>";
							//	 str="<div>11111</div>";
			    		 	$("#mBackList").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
				})
			}
			//查看更多筹款中的标
			if($('#recordListRaise').hasClass('on')){
				j=10+jj*5;
				jj++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/myInvestRecordMobile.action", //请求路径   
			       data:{"index":j,"type":1}, 
			       success:function(data){
			    	   if(data.length==0){
			    		   $('#mRaiseList').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
			    		   var title = element.title;
			    		   if(title.length>10){
			    			   title = title.substring(0,9)+"...";
			    		   }
			    		   str = "<div class='invest_re invest_re'><a href='<%=request.getContextPath() %>/mobileLoanDetail?loanId=" +element.loanId+ "&id="+element.id+ "&item=raise'"+"> <li>"+title+"<span><img src='<%=request.getContextPath() %>/mobile/images/poi_gt.gif' /></span></li></a></div>";
			    		  
			    		   $("#mRaiseList").before(str);
							//缓显
							$(".displayN").fadeIn(1000);
			    	   })
			       }
			})
			}
			
			
			//查看更多已完成的标
			if($('#recordListFinish').hasClass('on')){
				k=10+kk*5;
				kk++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/myInvestRecordMobile.action", //请求路径   
			       data:{"index":k,"type":2}, 
			       success:function(data){
			    	   //alert(data.voList);
			    	   if(data.length==0){
			    		   $('#mfinishList').remove();
			    		   return;
			    	   }
			    	   $(data).each(function(index, element) {
			    		   var title = element.title;
			    		   if(title.length>10){
			    			   title = title.substring(0,9)+"...";
			    		   }
			    		   str = "<div class='invest_re invest_re'><a href='<%=request.getContextPath() %>/mobileLoanDetail?loanId=" +element.loanId+ "&id="+element.id+ "&item=finish'"+"> <li>"+title+"<span><img src='<%=request.getContextPath() %>/mobile/images/poi_gt.gif' /></span></li></a></div>";
			    		   
			    		  	$("#mfinishList").before(str);
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
<%@ include file="/mobile/comm/bott.jsp"%>
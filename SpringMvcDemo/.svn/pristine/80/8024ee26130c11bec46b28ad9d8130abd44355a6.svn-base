<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="/mobile/js/jquery.mobile-1.3.2.min.js"></script>
<body>

	<div class="login search_t">
		<h2>
			<a href="<%=request.getContextPath() %>/mobile/bondAssign.jsp" title="返回" class="fl">
			<img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg" /></a>可转让债权
		</h2>
	</div>
 <c:forEach items="${page.voList}" var="data" varStatus="i" begin="0" end="9">
  <div class="index_c m_search"><h3><a >${data.loanTitle}</a></h3>
	<div class="debt_ass_l debt_ass_l_w">	  
	  <p><em>待&nbsp;收&nbsp;本&nbsp;息:</em>&nbsp;${data.toBeCollectedInterest+data.investAmount}元</p>
	  <p><em>剩&nbsp;余&nbsp;期&nbsp;限:</em>&nbsp;${data.leftTermCount}期</p>
	  <p><em>下一还款日:</em>&nbsp;<fmt:formatDate value="${data.nextRepayDate}" pattern="yyyy-MM-dd"/></p>	  
	</div>
	<div class="debt_ass_l debt_ass_r tc">
	  <p><a data-value="${data.id}"  id="${data.id}" >转出</a></p><p class="blank10"></p>
		<p><b>${data.loanAnnualInterestRate}%</b></p>
	 </div><div class="clear"></div>
 </div>

</c:forEach>

<c:if test="${page.recordCount>10}">
	<div id="starfooter">下拉加载更多...</div>
</c:if>

	<div class="theme-popover" style="top:10%">
		<img class="bond_close" src="<%=request.getContextPath() %>/mobile/images/d_close.png" title="关闭" />
		 <form id="sellform" class="form-horizontal" role="form" action="<%=request.getContextPath() %>/debtSellMobile" method="post">  
			<div class="bond_ass_f">
				<dl style="font-size: 14px; float: left; width: 39%">
					<dd>
						<img src="<%=request.getContextPath() %>/mobile/images/icon_rate.png" />折让率(%)
					</dd>
					<dd>
						<img src="<%=request.getContextPath() %>/mobile/images/icon_price.png" />转出价格
					</dd>
					<dd>
						<img src="<%=request.getContextPath() %>/mobile/images/icon_price.png" />当期利息
					</dd>
					<dd>
						<img src="<%=request.getContextPath() %>/mobile/images/icon_fee.png" />手续费
					</dd>
				</dl>

				<dl style="text-align: left; float: left; width: 61%">
					<dd >
					    <input type="text"  id="soldDiscountRate2" name="soldDiscountRate" placeholder="请输入折让率"/>
					    <span id="tip_rate" style="margin-right:3px;display:none;position:absolute;top:34px;right:5px">
								<div style="margin:0 auto;width:6px">
									<div style="background:#81cfff;float:left">
									    <div style="border-top:5px solid #fff;border-right:3px solid transparent;height:0"></div>
									</div>
							        <div style="background:#81cfff;float:left">
							            <div style="border-top:5px solid #fff;border-left:3px solid transparent;height:0"></div>
							        </div>
						        </div>
						        <div style="background:#81cfff;color:white;padding:0 3px;clear:both;line-height:18px">*无效的数值</div>
						</span>
						<span id="soldDiscountRateText"></span>
				</dd>
				<dd>
				 <input type="text"  style="background: #e2e2e2; color: #8a8a8a; text-align: center; cursor: pointer" placeholder="点击计算显示" readonly="readonly" id="outPrice" name="outPrice" />
					<br /><span></span>
				</dd>
				<dd>
				    <input type="text"  style="background: #e2e2e2; color: #8a8a8a; text-align: center; cursor: pointer" placeholder="点击计算显示" readonly="readonly" id="lixiValue" name="lixiValue" />
					<br /><span></span>
				</dd>
					<dd>
					<input type="text"   style="background:#e2e2e2;color:#8a8a8a;text-align:center;cursor:pointer" placeholder="点击计算显示" readonly="readonly" id="feePrice" name="feePrice" />
						<br />
						<span id="feePriceText"></span>
					</dd>
				</dl>
				<p class="clear"></p>
			</div>
			<hr style="color:#d4d4d4"/>
			 <div class="login_f_btn" id="jsfy" style="font-size:16px;font-weight:normal;margin:7px auto 0">点 击 计 算 转 出 费 用</div>
			<div class="login_f_btn" style="font-size:16px;font-weight:normal;margin:7px auto 0" id="conOut">确认转出</div>
			<div class="blank10"></div>
		    <input type="hidden" id="loanInvestorId" name="loanInvestorId" value="">
			<input type="hidden" id="soldDiscountRateMin" name="soldDiscountRateMin" />
            <input type="hidden" id="soldDiscountRateMax" name="soldDiscountRateMax" />
		</form>


	</div>
	<div class="theme-popover-mask"></div>
	<script>
	jQuery(document).ready(function($) {
		var currentPage=1,winScroll=0;
		//下拉加载
		$(document).on("scrollstart",function(){
			var winHeight = $(window).height();       // 窗口高度
			var allHeight = $(document).height();     // 文档高度
			var allScroll = $(document).scrollTop();  //滚动前scroll
			//判断当前底部是否显示和数据添加
			if(allScroll+winHeight>=allHeight-10&&allScroll >= winScroll){
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
			currentPage++;
			 $.ajax({  
				   type:"POST", //请求方式  
			       url:"${base}/listMobile2.action", //请求路径   
			       data:{"index":currentPage}, 
			       success:function(data){
			    	    if(data.length==0){
			    		   $('#starfooter').remove();
			    		   return;
			    	   } 
			    	 
			    	   $(data).each(function(index, element) {
			    	    str = '<div class="index_c m_search"><h3><a >'+element.loanTitle+'</a></h3>'+
								'<div class="debt_ass_l debt_ass_l_w">'+	  
								  '<p><em>待&nbsp;收&nbsp;本&nbsp;息:</em>&nbsp;'+(element.toBeCollectedInterest+element.investAmount)+'元</p>'+
								  '<p><em>剩&nbsp;余&nbsp;期&nbsp;限:</em>&nbsp;'+element.leftTermCount+'期</p>'+
								  '<p><em>下一还款日:</em>&nbsp;'+element.formatNextRepayDate+'</p>'+	  
								'</div>'+
								'<div class="debt_ass_l debt_ass_r tc">'+
								  '<p><a data-value="'+element.id+'"  id="'+element.id+'" >转出</a></p><p class="blank10"></p>'+
									'<p><b>'+Number(element.loanAnnualInterestRate).toFixed(2)+'%</b></p>'+
								 '</div><div class="clear"></div>'+
							 '</div>'; 
			    		  
			    		 	$("#starfooter").before(str);
							//缓显
							$(".index_c m_search").fadeIn(1000);
			    	   })
			       }
			      
			})
		} 
	
		$(document).delegate(".debt_ass_l p a","click",function(){
		    $('#soldDiscountRate2').val("");
		    $('#lixiValue').val("");
		    $('#outPrice').val("");
		    $('#feePrice').val("");
		    $('#loanInvestorId').val($(this).attr("id"));
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		$('.theme-popover .bond_close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		});
		
		
	      //动态读取债权认购参数
    	   $.post('<%=request.getContextPath() %>/findParmer', function( data ) {    		   
    	        	$("#soldDiscountRateText").html("(范围:"+data.diccountRateMin+"%-"+data.diccountRateMax+"%)");
    	        	$("#feePriceText").html("转出价格*"+data.fee+"%;<br/>下限:"+data.feeMin+"元;上限:"+data.feeMax+"元;");
    	        	$("#soldDiscountRateMin").val(data.diccountRateMin);
    	        	$("#soldDiscountRateMax").val(data.diccountRateMax);
  	        }, 'json');
       

	      // 计算转出价格和手续费
	      $('#jsfy').click(function() {	    	 
	    	  var flag=checksoldDiscountRate();	    
	    	  if(flag){
	    	      $.post('<%=request.getContextPath() %>/computingFee',$('#sellform').serialize(), function( data ) {
	    	        	$("#outPrice").val(data.CalcCreditRightsValue);
	    	        	$("#feePrice").val(data.tradeCommissionFee);
	    	        	$("#lixiValue").val(data.lixiValue);
	  	        }, 'json');
	    	  }else{
				setTimeout(function(){
					$("#soldDiscountRate2").validationEngine("hideAll");
					},3000);
				return false;
			}

	      });
	      
	      
	      // 确认转出
	      $('#conOut').click(function() {
	    	  var flag=checksoldDiscountRate();
	    	  if(flag){
	    		  $('#sellform').submit();
	    	  }else{
				setTimeout(function(){
					$("#soldDiscountRate2").validationEngine("hideAll");
					},3000);
				return false;
			}

	      });
	      
	      /**
	       * 验证折让率范围
	       */
	       function checksoldDiscountRate(){
	      	  var soldDiscountRate=$("#soldDiscountRate2").val();
	      	  var soldDiscountRateMin=Number($("#soldDiscountRateMin").val());
	      	  var soldDiscountRateMax=Number($("#soldDiscountRateMax").val());	      	  
	          var reg = new RegExp("^[0-9]*$");  
	           if(soldDiscountRate==''||!reg.test(soldDiscountRate)||soldDiscountRate==0){
	        	   $('#tip_rate').css('display','block');$("#soldDiscountRate2").focus();
	        	   return false;
	           }
	           else if(soldDiscountRate>soldDiscountRateMax||soldDiscountRate<soldDiscountRateMin){
	        		$('#tip_rate').css('display','block');$("#soldDiscountRate2").focus();
	        		return false;
	      		}else{
	      			 $('#tip_rate').css('display','none');
	      			 return true;
	      		} 
	          return true;
	      	  
	      }
	})
</script>
<%@ include file="/mobile/comm/bott.jsp"%>
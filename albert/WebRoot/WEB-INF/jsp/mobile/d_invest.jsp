<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./comm/head.jsp"%>
<%@ include file="/WEB-INF/views/include/global.jsp" %>

<%@ page import="net.zkbc.p2p.api.model.Banner"%>
<%@ page import="com.zkbc.core.consts.LoanDef" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
	HashMap<String, String> creditTypes = new HashMap<String, String>();
creditTypes.put("M1", "其他");
	creditTypes.put("B1", "身份证");
	creditTypes.put("C1", "信用报告");
	creditTypes.put("D8", "收入证明");
	creditTypes.put("F7", "居住地址证明");
	creditTypes.put("J3", "贷前调查资料");//学历证明
	creditTypes.put("H1", "房产证明");
	creditTypes.put("I1", "购车证明");
	creditTypes.put("E8", "保付机构资料");//工作证明
	creditTypes.put("B3", "结婚证明");

	creditTypes.put("F4", "各种缴费单据");
	creditTypes.put("F5", "公务机关(税务局、公安局、法院等)邮寄信函");
	creditTypes.put("G1", "经营公司证明");
	creditTypes.put("G2", "验资报告或公司章程或股权证明");
	creditTypes.put("G3", "经营场所或办公地的租房合同");
	creditTypes.put("G4", "经营场所最近一个月该场所的缴费(水电煤物业等)单据");
	creditTypes.put("G5", "对公账户流水");
	creditTypes.put("G6", "各类协议、合同");
	creditTypes.put("H1", "产权证");
	creditTypes.put("H7", "房契证");
	creditTypes.put("I3", "有价金融资产");

	HashMap<String, String> isFong = new HashMap<String, String>();
	//1：无房；2，有房无贷款；3，有房有贷款
	isFong.put("1", "无房");
	isFong.put("2", "有房无贷款");
	isFong.put("3", "有房有贷款");
%>
<c:set target="${self}" property="isFong" value="<%= isFong%>" />
<c:set target="${self}" property="creditTypes" value="<%= creditTypes%>" />
<script>
	jQuery(document).ready(function($) {
		$('.d_invest_c dl dd').click(function(){//alert($('.d_invest_c dl dd').index(this));
			$('.d_invest_c > ul').eq($('.d_invest_c dl dd').index(this)).show().siblings('ul').hide();
			$('.d_invest_c dl dd').eq($('.d_invest_c dl dd').index(this)).children('p').addClass('on').parent().siblings().children().removeClass('on');
			//$('.theme-popover').slideDown(200);
		});
			
		$('.d_invest_calc').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover').slideDown(200);
		});
		
		$('#invest').click(function(){
			$('.theme-popover-mask').fadeIn(100);
			$('.theme-popover1').slideDown(200);
		});
		
		$('#advde').click(function(){
			$('.calc_res').show(100);
		});

		$('.theme-popover .bond_close').click(function(){
			$('.theme-popover-mask').fadeOut(100);
			$('.theme-popover').slideUp(200);
		})
		
		$("a[rel=example_group]").fancybox({
			'transitionIn'		: 'none',
			'transitionOut'		: 'none',
			'titlePosition' 	: 'over',
			'titleFormat'		: function(title, currentArray, currentIndex, currentOpts) {
				return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
			}
		});
        if(${selectPage!=null && selectPage!=''}){
        	$("#2").click();
        }
	})
</script>

<script language="javascript">



	function invest_calc(){
		var amount = $("#amount").val();
		var rate = $("#rate").val();
		var loandeadline = $("#loandeadline").val();
		if(check_num(amount)){
			if(check_num(rate)){
				if(check_num(loandeadline)){
				/* 	var table = document.getElementById("repayplan");
					var arr = table.getElementsByTagName("tr"); */
				/* 	for(var i = arr.length - 1; i > 0; i--) {
			        	table.deleteRow(i);
			    	} */
		var Deadline = parseInt(loandeadline);
		var datalist = new Array(Deadline);
			//计算利息
	   	 datalist = Calculate(amount, rate,loandeadline);			
		document.getElementById("lab-amount").innerHTML = amount;
		document.getElementById("lab-loandeadline").innerHTML = loandeadline;
		document.getElementById("lab-rate").innerHTML = rate;

		for(var i = 0;i<Deadline;i++){
			var newTr = repayplan.insertRow(-1);
			var newTd0 = newTr.insertCell(-1);
			var newTd1 = newTr.insertCell(-1);
			var newTd2 = newTr.insertCell(-1);
			var newTd3 = newTr.insertCell(-1);
			var newTd4 = newTr.insertCell(-1);
			newTd0.align="center"; 
			newTd1.align="center"; 
			newTd2.align="center"; 
			newTd3.align="center"; 
			newTd4.align="center"; 
			//这里修改与页面对应值
			newTd0.innerHTML= datalist[i][0];
			newTd1.innerHTML= datalist[i][1] + "元";
			newTd2.innerHTML= datalist[i][3] + "元";
			newTd3.innerHTML= datalist[i][2] + "元";
			newTd4.innerHTML= datalist[i][4] + "元";
		}
		}else{
			alert("请输入正确的投资期限!");
			$("#loandeadline").val("");
			$("#loandeadline").focus();
		}
		}else{
			alert("请输入正确的预期年利率!");
			$("#rate").val("");
			$("#rate").focus();
			
		}
		}else{
			alert("请输入正确的投入金额!");
			$("#amount").val("");
			$("#amount").focus();
		}
	}

	 //按照月计算利息
		function Calculate(amount, rate, loandeadline) {
		var Deadline = parseInt(loandeadline);
		var Amount = parseFloat(amount);
		var Rate = parseFloat(rate)
		var datalist = new Array(Deadline);
/* 		var i;
		var a; // 偿还本息
		var b; // 偿还利息
		var c; // 偿还本金
		var d=Amount; // 剩余本金
		var TotalRate = (Amount * Deadline * Rate * Math.pow((1 + Rate), Deadline))
				/ (Math.pow((1 + Rate), Deadline) - 1) - Amount;
		TotalRate = Math.round(TotalRate * 100) / 100;// 支付总利息
		var TotalRepay = TotalRate + Amount;
		TotalRepay = Math.round(TotalRepay * 100) / 100;
		a = TotalRepay / Deadline;
		a = Math.round(a * 100) / 100;// 支付总还款额
		for (i = 1; i <= Deadline; i++) {
			b = (Amount * Rate * (Math.pow((1 + Rate), Deadline) - Math.pow(
					(1 + Rate), (i - 1))))
					/ (Math.pow((1 + Rate), Deadline) - 1);
			b = Math.round(b * 100) / 100;
			c = a - b;
			c = Math.round(c * 100) / 100;
			d = d-c;
			d = Math.round(d * 100) / 100;
			if (i==Deadline) {
				c=c+d;
				b=b-d;
				c = Math.round(c * 100) / 100;
				b = Math.round(b * 100) / 100;
				d=0;
			};
			var tempList = new Array([5]);
			tempList[0] = i;// 期数
			tempList[1] = a;// 偿还本息
			tempList[2] = b;// 偿还利息
			tempList[3] = c;// 偿还本金
			tempList[4] = d;// 剩余本金
			datalist[i - 1] = tempList;
		} */
	/* 	lab_totalmomey = Math.round((Amount+TotalRate)*100)/100; */
	    lab_totalmomey = Rate*Amount*Deadline+Amount;
	    var interest=Math.round(parseFloat(Amount*(Rate/1200)*Deadline)*100)/100;
	    var sum=Math.round((interest+Amount)*100)/100;
	    $("#lab-income").html(interest+"元");
	    $("#lab-totalmomey").html(sum+"元");
		
		return datalist;
	}

    function show(){
    	var theTypeTz= $('#theTypeTz').val();
    	var obj = $('#theType').val();
    	
    	if(theTypeTz=='1'){   
    		$('#theType').empty();
    		jQuery("#theType").prepend("<option value='2'>一次性还本付息</option>"); 
//     		$('#theType').attr("Option","2");
//     		$("#theType").get(0).value = "一次性还本付息";
//     		obj.options.length = 0;
    		//obj.options.remove(obj.selectedIndex[0]);
    		//obj.options.remove(obj.selectedIndex[1]);   		
//        		 obj.options.add(new Option("一次性还本付息","2")); 
    	}else{
         //obj.options.remove(obj.selectedIndex[0]);
         $('#theType').empty();
         jQuery("#theType").prepend("<option value='0'>等额本息</option>"); 
         jQuery("#theType").prepend("<option value='1'>按月付息,到期还本</option>"); 
//          obj.options.length = 0;
//          obj.options.add(new Option("等额本息","0"));
//          obj.options.add(new Option("按月付息,到期还本","1"));
    	 //obj.options.add(new Option("一次性还本付息","2"));
    	}
    	
    }
	
	function check_num(num){
		var re = /^[0-9]+(\.\d+)?$/;  
	 	if (!re.test(num)){
	 		return false;
		}
	 	if(num == 0){
	 		return false;
	 	}
	 	if(100000000-num <= 0){
	 		return false;
	 	}
	 	
		return true;
	}

	

	$(function(){
		$(".calcu-button").click(function(){
			var type=$("#theType").val();
			var amount = $("#amount").val();
			var rate = $("#rate").val();
			var loandeadline = $("#loandeadline").val();
			var r = /^[0-9]*[1-9][0-9]*$/
			if(check_num(amount)){
				if(check_num(rate)){
					if(check_num(loandeadline)){
						if(type==0||type==1){
							if(rate<0||rate>100){
								alert("年利率应是0~100内的数字(可小数)");	
								$('#rate').empty();
								$('#lab-income').empty();
								$('#lab-totalmomey').empty();
								$("#rate").focus();
								
							}else{
								
							if(loandeadline>12||loandeadline<1||!r.test(loandeadline)){
								alert("请输入1~12月(整数)");	
								$('#loandeadline').empty();
								$('#lab-income').empty();
								$('#lab-totalmomey').empty();
								$("#loandeadline").focus();
							}
							else{
							$("#repayplan").show();
							$("#the_title").show();
							invest_calc();
							}
							}
						}
						/* if(type==1){
							$("#repayplan").show();
							$("#the_title").show();
							var amount=$("#amount").val();
							var loandeadline=$("#loandeadline").val();
							var rate=$("#rate").val();
							var sum=amount*rate/100*(loandeadline/12);
							var sumfinal=sum.toFixed(2);
							$("#lab-totalmomey").text(amount*1+sumfinal*1);
							alert(33);
							alert(lab-totalmomey);
							$("#lab-income").text(sumfinal);
							//每个月需要还的利息钱
							var sumfinalEve=(sumfinal/loandeadline).toFixed(2);
							$("#repayplan").empty();
							var objtitle=$("<tr style='height: 30px;'><td align='center' bgcolor='#f6fbfe'>期数</td><td align='center' bgcolor='#f6fbfe'>每月还款本息</td> <td align='center' bgcolor='#f6fbfe'>应还本金</td> <td align='center' bgcolor='#f6fbfe'>应还利息</td><td align='center' bgcolor='#f6fbfe'>剩余回款本息</td></tr>");
							$("#repayplan").append(objtitle);
							for(var i=1;i<loandeadline;i++){
								var objbody=$(" <tr style='height: 30px;'>"+
							            " <td align='center' >"+i+"</td>"+
							            " <td align='center' >"+sumfinalEve+"元</td>"+
							            " <td align='center' >0元</td>"+
							            " <td align='center' >"+sumfinalEve+"元</td>"+
							            " <td align='center' >"+(amount*1+sumfinal*1-sumfinalEve*i).toFixed(2)+"元</td></tr>");
								$("#repayplan").append(objbody);
							}
							var objbody=$(" <tr style='height: 30px;'>"+
						            " <td align='center' >"+loandeadline+"</td>"+
						            " <td align='center' >"+(amount*1+sumfinalEve*1).toFixed(2)+"元</td>"+
						            " <td align='center' >"+amount+"元</td>"+
						            " <td align='center' >"+sumfinalEve+"元</td>"+
						            " <td align='center' >"+0+"元</td></tr>");
							$("#repayplan").append(objbody);
						} */
						if(type==2){
						   var theTypeTz= $("#theTypeTz").val();
							 	if(theTypeTz=="0"){
									/* var amount=$("#amount").val();
									var loandeadline=$("#loandeadline").val();
									var rate=$("#rate").val();
									var sum=amount*rate/100*(loandeadline/12);
									var sumfinal=sum.toFixed(2);
									$("#lab-totalmomey").text(amount*1+sumfinal*1);
									$("#lab-income").text(sumfinal);
									$("#repayplan").hide();
									$("#the_title").hide(); */
							 		showDialogHTML("一次性还本付息的投资期限只能在1个月以下");
								}else{
									if(rate<0||rate>100){
										alert("年利率应是0~100内的数字(可小数)");	
										$('#rate').empty();
										$('#lab-income').empty();
										$('#lab-totalmomey').empty();
										$("#rate").focus();
										
									}else{
									if(loandeadline>30||loandeadline<1||!r.test(loandeadline)){
										alert("请输入1~30天数(整数)");
										$('#loandeadline').empty();
										$('#lab-income').empty();
										$('#lab-totalmomey').empty();
										$("#loandeadline").focus();
									}else{
									   var amount=parseInt($("#amount").val());
									   var loandeadline=parseInt($("#loandeadline").val());
									   var rate=parseFloat($("#rate").val());
									   var interest=Math.round(parseFloat(amount*(rate/1200/30)*loandeadline)*100)/100;
									   var sum=Math.round((interest+amount)*100)/100; 
									   $("#lab-income").html(interest+"元");
									   $("#lab-totalmomey").html(sum+"元");
									   $("#repayplan").hide();
									   $("#the_title").hide();
									}
									}
									
									}
						}
						
					}else{
						
						alert("请输入正确的投资期限!");
						$("#loandeadline").val("");
						$('#lab-income').empty();
						$('#lab-totalmomey').empty();
						$("#loandeadline").focus();
					}
				}else{
						alert("请输入正确的预期年利率!");
						$("#rate").val("");
						$('#lab-income').empty();
						$('#lab-totalmomey').empty();
						$("#rate").focus();
				}
			}else{
				alert("请输入正确的投入金额!");
				$("#amount").val("");
				$('#lab-income').empty();
				$('#lab-totalmomey').empty();
				$("#amount").focus();
			}
		
		});
	});
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/yrycodeapi.js"></script>
<%--   <style type="text/css">
  .blur {    
    
       -moz-filter: blur(10px);
        -ms-filter: blur(10px);    
            filter: blur(10px); 
         
}
  </style> --%>

<body>
    <input type="hidden" id="selectPage" value="${selectPage}"/>
	<div class="login search_t">
		<h2>
		    <c:choose>
                  <c:when test="${empty selectZhaiQuan}">
                  	<a href="<%=request.getContextPath() %>/index" title="主页" class="fl"><img
						src="${base}/mobile/images/poi_lt.jpg" /></a>
                  </c:when>
                  <c:otherwise>
                  	<a href="<%=request.getContextPath() %>/index?selectPage=${selectZhaiQuan}" title="主页" class="fl"><img
							src="${base}/mobile/images/poi_lt.jpg" /></a>
                 </c:otherwise>
	          </c:choose>
			投资详情
		</h2>
	</div>


	<div class="index_c d_invest">
		<h3>
			<a href="#">${loan.title }</a>
		</h3>
		<p style="line-height: 36px">
			<img class="d_invest_calc"
				style="float: right; margin-top: -9px; cursor: pointer"
				src="${base}/mobile/images/d_invest_calc.jpg" /><span><em class="wid4"><fmt:formatNumber value="${loan.annualInterestRate}" pattern="#0.0"/>%</em><em class="wid4"><c:choose>
					   <c:when test="${loan.termUnit ==1}">${loan.termCount}天</c:when>
					    <c:otherwise>${loan.termCount}个月</c:otherwise>
					</c:choose></em></span>&nbsp;&nbsp;&nbsp;<c:choose>
					   <c:when test="${loan.productId ==1}">按月付息到期还本</c:when>
					   <c:when test="${loan.productId ==2}">一次性还本付息</c:when>
					    <c:otherwise>等额本息</c:otherwise>
			 </c:choose>
		</p>
		<div class="index_c_per"
			style="width: 86%; float: left; margin: 5px 7px 0 0">
			<div class="index_c_per bg" style="width:<fmt:formatNumber value="${loan.biddingAmount/loan.amount*100}" pattern="#,##0"/>%"></div>
		</div>
		<b style="color:#ff3e77"><fmt:formatNumber value="${loan.biddingAmount/loan.amount*100}"  pattern="#"></fmt:formatNumber>%</b>
		<p style="clear: both; margin: 0"><fmt:formatNumber value="${loan.beginAmount}"  pattern="${moneyexp}"></fmt:formatNumber>元起投
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余<fmt:formatNumber value="${loan.amount-loan.biddingAmount}"  pattern="##0"></fmt:formatNumber>元/总额<fmt:formatNumber value="${loan.amount}"  pattern="##0"></fmt:formatNumber>元</p>
	</div>

	<div class="d_invest_c">
		<dl>
			<dd>
				基本资料
				<p class="on" id="0"></p>
			</dd>
			<dd>
				借款详情
				<p></p>
			</dd>
			<dd>
				借款材料
				<p id="2"></p>
			</dd>
			<dd>
				投资记录
				<p></p>
			</dd>
			<p class="clear"></p>
		</dl>
		<ul style="display: block">
			<li><img src="${base}/mobile/images/d_invest_f.jpg"/> ${fn:substring(borrower.realName,0,1) }<c:if test="${bpinfo.gender }">先生</c:if><c:if test="${!bpinfo.gender }">女士</c:if></li>
			<li>毕业院校：<c:if test="${!empty bpinfo.topEduSchool }">${fn:substring((bpinfo.topEduSchool), 0, 8)}</c:if>
          	<c:if test="${empty bpinfo.topEduSchool }">无</c:if>
          	</li>
			<li>年　　龄：${age}岁</li>
			<li>籍　　贯：<script>yrycode.getstr(${bpinfo.homeTownProvince},yrycode.province)</script></li>
			<li>结婚状态：<c:if test="${bpinfo.hasMarried}">已婚 </c:if><c:if test="${!bpinfo.hasMarried}">未婚</c:if></li>
			<li>每月收入：${bfinfo.monthlyIncome}元</li>
			<li>最高学历：<c:if test="${bpinfo.topEducation=='90'}">其他
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='80'}">博士
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='70'}">硕士
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='60'}">本科
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='50'}">大专
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='40'}">高中
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='30'}">中专
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='20'}">初中
							          </c:if>
							          <c:if test="${bpinfo.topEducation=='10'}">小学
							          </c:if>
									</li>
			<li>住房情况：<c:if test="${bfinfo.housingType==1}">无房</c:if>
          							<c:if test="${bfinfo.housingType==2}">有房无贷款</c:if>
          							<c:if test="${bfinfo.housingType==3}">有房有贷款</c:if>
          							</li>
			<li>是否有车：<c:if test="${bfinfo.hasCar==true}">有</c:if>
          							<c:if test="${bfinfo.hasCar==false}">无</c:if>
          							<c:if test="${empty bfinfo.hasCar}">无</c:if></li>
		</ul>

		<ul>
			<li>1、投资期限：<c:choose>
					   <c:when test="${loan.termUnit ==1}">${loan.termCount}天</c:when>
					    <c:otherwise>${loan.termCount}个月</c:otherwise>
					</c:choose></li>
			<li>2、年化收益：${loan.annualInterestRate}%</li>
			<li>3、计息方式：满标计息</li>
			<li>4、还　　款：<c:choose>
					   <c:when test="${loan.productId ==1}">按月付息到期还本</c:when>
					   <c:when test="${loan.productId ==2}">一次性还本付息</c:when>
					    <c:otherwise>等额本息</c:otherwise>
			 </c:choose></li>
			<li>5、门槛较低：<fmt:formatNumber value="${loan.beginAmount}"  pattern="${moneyexp}"></fmt:formatNumber>元起投</li>
			<%--<li class="br6">6、本息保障：${loan.description}</li>--%>
			<li class="br6">
				<c:if test="${loan.isStar==1}">6、个人简介：</c:if>
				<c:if test="${loan.isStar==2}">6、风控措施：</c:if>
				${loan.mark }</li>
		</ul>
 <ul>
 
<%--  <c:set var="salary" scope="session" value="${userVo}"/> --%>

 <c:if test="${empty userVo}" >请<a href="<%=request.getContextPath() %>/toLoginPage?loanId=${loan.loanId}">登录</a>后查看借款材料!</c:if>
 <c:if test="${userVo!=null}" >
   <li><img src="/mobile/images/d_invest_h.png" style="margin:10px 3px 0 0"/>身份证</li>
   
   <div id="content">
      						<c:forEach var="creditImage" items="${imagesList}">
       						<c:if test="${creditImage.materialType=='B1'}">
 	   <a rel="example_group" href="${base}/creditMaterial.action?urlpath=${creditImage.filePath}${creditImage.loanId}/prototype/${creditImage.fileName}" title="${self.creditTypes[creditImage.materialType]}"><img alt="点击查看" src="/mobile/images/d_invest_id_s.jpg"/></a>
      						</c:if>
      						</c:forEach>
      						<p class="clear"></p>
	   
	<li><img src="/mobile/images/d_invest_h.png" style="margin:10px 3px 0 0"/>其他资料</li>
      						<c:forEach var="creditImage" items="${imagesList}">
       						<c:if test="${creditImage.materialType!='B1'}">
 	   <a rel="example_group" style="width:86%" href="${base}/creditMaterial.action?urlpath=${creditImage.filePath}${creditImage.loanId}/prototype/${creditImage.fileName}" title="${self.creditTypes[creditImage.materialType]}"><img alt="点击查看" src="/mobile/images/d_invest_m_s.jpg" /></a>
      						</c:if>
      						</c:forEach>
      						<p class="clear"></p>
   </div> 
   </c:if>
  </ul>
	

		<ul class="nobor">
		    <c:if test="${investorList!=null&&fn:length(investorList)!=0}">
      	<c:forEach items="${investorList}" var="investor" varStatus="i">
      		<tr  <c:if test="${user.nickName==investor.investorNickname}">style="color:#ED6D00; font-weight:700;"</c:if>>
	          <li><td nowrap><i>${investor.zdInvestorNickname }</i></td>
	          <c:choose>
             <c:when test="${not empty loan.minInvestUnit && loan.minInvestUnit>0 }">
	            <td nowrap><fmt:formatNumber value="${investor.investAmount/loan.minInvestUnit }"  pattern="${moneyexpInt}"></fmt:formatNumber></td>
             </c:when>
             <c:otherwise>
             	<td nowrap><i><fmt:formatNumber value="${investor.investAmount}"  pattern="#.00"></fmt:formatNumber>元</i></td>
             </c:otherwise>
          		</c:choose>	
	          <td nowrap><i class="wid4"><fmt:formatDate value="${investor.investTime}" pattern="yy-MM-dd HH:mm"/></i></td></li>
        	</tr>
      	</c:forEach>
        </c:if>
        <c:if test="${investorList==null||fn:length(investorList)==0}">
        <tr>
          <td>&nbsp;</td>
           <td>&nbsp;</td>
            <td>&nbsp;</td>
              <td>&nbsp;</td>
        </tr>
         <tr>
            <td colspan="5" style="font-size: 16px;">暂无投标记录</td>
        </tr>
        <tr>
          <td>&nbsp;</td>
           <td>&nbsp;</td>
             <td>&nbsp;</td>
              <td>&nbsp;</td>
        </tr>
        </c:if>
			
			<p class="clear"></p>
		</ul>
	</div>

<%-- 	<a href="<%=request.getContextPath() %>/getLoanDetailByLoanId?loanId=${loan.loanId}"><div class="login_f_btn" id="invest">立即投资</div></a> --%>
			  <c:choose>
						       	 <c:when test="${loan.status eq 300}">
	                               	<a  href="<%=request.getContextPath() %>/getLoanDetailByLoanId?loanId=${loan.loanId}" ><div class="login_f_btn" id="invest">立即投资</div></a>
	                               </c:when>
	                               <c:when test="${loan.status eq 400}">
	                               <div class="login_f_btn" style="background: #cbccce">已满标</div></a>
	                                </c:when>
	                               <c:when test="${loan.status eq 500}">
	                               <div class="login_f_btn" style="background: #cbccce">还款中</div></a>
	                               </c:when>
	                               <c:when test="${loan.status eq 301}">
	                                <div class="login_f_btn" style="background: #cbccce">预热中</div></a>
	                               </c:when>
	                               <c:otherwise>
	                                <div class="login_f_btn" style="background: #cbccce">已还款</div></a>>
	                             </c:otherwise>
	          </c:choose>

	<div class="theme-popover">
		<img class="bond_close" src="${base}/mobile/images/d_close.png" title="关闭" />
		<div id="calculate">
		<div class="bond_ass_f d_invest_f">
			<form id="form1" name="form1" action="" method="post">
				<dl style="font-size: 14px; float: left; width: 43%">
					<dd>
						<img src="${base}/mobile/images/icon_price.png" />投入金额
					</dd>
					<dd>
						<img src="${base}/mobile/images/icon_rate.png" />预期年利率
					</dd>
					<dd>
						<img src="${base}/mobile/images/icon_fee.png" />投资期限
					</dd>
					<dd>
						<img src="${base}/mobile/images/icon_repay.png" />还款方式
					</dd>
				</dl>

				<dl style="text-align: left; float: left; width: 57%">
					<dd>
						<input style="width: 50%;ime-mode:disabled" type="text" class="form-control" name="amount" id="amount" maxlength="9"/><b>元</b>
					</dd>

					<dd>
						<input  type="text" class="form-control" name="rate" id="rate" style="width: 50%;ime-mode:disabled" maxlength="5" /><b>%</b>
					</dd>
					<dd>
						<input type="text" class="form-control" name="loandeadline" id="loandeadline" maxlength="2" style="border-right: none;ime-mode:disabled;width: 50%; border-radius: 0"/><select style="width: 49.2%" onchange="show()" id="theTypeTz"><option
								value="0">月</option>
							<option value="1">天</option></select>
					</dd>
					<dd>
						<select style="width: 100%" id="theType"><option value="0">等额本息</option>
							<option value="1">按月付息，到期还本</option>
							<!-- <option value="2">一次性还本付息</option> --></select>
					</dd>
				</dl>
			<!-- 	<p style="align:center">
				<input type="button" style="background:#f74172" class="login_f_btn calcu-button" value="计算" /></p> -->
				<p class="clear"></p>
		<div class="login_f_btn calcu-button" id="advde">计算</div>
			</form>
			

 			<p class="calc_res" style="display:none">
				本息合计：<span id="lab-totalmomey"></span><br />利息总收益：<span id="lab-income"></span>
			</p> 
		</div>
		</div>
	</div><div class="theme-popover-mask"></div>

<!--  <div class="theme-popover1">
     <div class="theme-poptit">
          提示
     </div>
		<p>投标成功</p>
		<a href="user.htm"><div class="index_login">返回我的账户</div></a>
</div> -->


	

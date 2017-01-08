<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/mobile/comm/head.jsp"%>

<body style="background:#ffffff">

 <div class="login search_t">
  <h2><a href="<%=request.getContextPath() %>/buyDeb?loanId=${loanInvestorId}" title="返回" class="fl"><img src="<%=request.getContextPath() %>/mobile/images/poi_lt.jpg"/></a>服务协议</h2>
 </div>
 <div class="agree_con">
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>  
  <form>
    <input type="hidden" name="statusCode" value="${agreement.statusCode}" />
    <input type="hidden" name="statusMessage" value="${agreement.statusMessage}" />
    <input type="hidden" name="agreementUuid" value="${agreement.uuid}" />
    <input type="hidden" name="loanInvestorId" value="${loanInvestorId}" />
  </form>
  <h2 style="line-height:36px;text-align:center">债权转让及受让协议</h2>
  <p>本债权转让及受让协议（下称“本协议”）由以下双方于北京签署：</p>
  <br/>
  <p><strong>甲方（转让人）：</strong>${agreement.aName}</p>
  <p><strong>身份证号：</strong>${agreement.aIdNo}</p>
  <p><strong>智典财富网用户名：</strong>${agreement.aUsername}</p>
  <br/>
  <p><strong>乙方（受让人）：</strong>${agreement.bName}</p>
  <p><strong>身份证号：</strong>${agreement.bIdNo}</p>
  <p><strong>智典财富网用户名：</strong>${agreement.bUsername}</p>
  <br/>
  <p>就甲方通过${websiteCorpName}向乙方转让债权事宜，双方经协商一致，达成如下协议：</p>
  <h3>1.  债权转让</h3>
  <h4>1.1  标的债权信息及转让</h4>
  <p>乙方同意受让甲方债权。标的债权具体信息如下：</p>
  
  
    <b>标的债权信息</b><br/>
   
      <em>债权人</em>
        ${agreement.aName}<br/>
     
     <em>债务人</em>
        ${agreement.borrowerName}<br/>
        
      <em>借款金额</em>
        ${agreement.amount}元<br/>
     
     <em>剩余期限(月)</em>
        ${agreement.leftTerm}<br/>
        
      <em>年化利率</em>
        ${agreement.interestRate}%<br/>
     
  
 
  <h4>1.2  债权转让流程</h4>
  <h5>1.2.1  双方同意并确认，双方通过自行或授权有关方根据智典财富网有关规则和说明，在智典财富网进行债权转让和受让操作等方式确认签署本协议。</h5>
  <h5>1.2.2  双方在智典财富网债权转让页面点击确认时，本协议立即成立,并待转让价款支付完成时生效。或者标的债权借款人发生逾期还款，标的债权所依据的借款协议中的其他方借款人还款的情况下，在通知借款人后，甲方自动将债权转让给借款人还款的第三方。协议成立的同时乙方不可撤销地自行或授权委托第三方支付机构或合作的金融机构，将转让价款在扣除甲方应支付给智典财富网的转让管理费之后支付给甲方，上述转让价款支付完成即视为本协议生效且标的债权转让完成；同时甲方不可撤销地授权智典财富网将其代为保管的甲方与标的债权借款人签署的电子文本形式的《借款协议》（下称“借款协议”）及借款人相关信息在智典财富网有关系统板块向乙方进行展示。 </h5>
  <h5>1.2.3  本协议生效且标的债权转让完成后，双方特此委托将标的债权的转让事项及有关信息通过站内信等形式通知与标的债权对应的借款人。 </h5>
  <h4>1.3  自标的债权转让成功之日起，乙方成为标的债权的债权人，承继借款协议项下出借人的权利并承担出借人的义务。 </h4>
  <h3>2.  保证与承诺</h3>
  <h4>2.1  甲方保证其转让的债权系其合法、有效的债权，不存在转让的限制。并已按照借款协议约定的方式通知了借款人。甲方同意并承诺按有关协议及智典财富网的相关规则和说明向支付债权转让管理费。 </h4>
  <h4>2.2  乙方保证其所用于受让标的债权的资金来源合法，乙方是该资金的合法所有人。如果第三方对资金归属、合法性问题发生争议，乙方应自行负责解决并承担相关责任。 </h4>
  <h3>3.  违约</h3>
  <h4>3.1  双方同意，如果一方违反其在本协议中所作的保证、承诺或任何其他义务，致使其他方遭受或发生损害、损失等责任，违约方须向守约方赔偿守约方因此遭受的一切经济损失。</h4>
  <h4>3.2  双方均有过错的，应根据双方实际过错程度，分别承担各自的违约责任。 </h4>
  <h3>4.  适用法律和争议解决</h3>
  <h4>4.1  本协议的订立、效力、解释、履行、修改和终止以及争议的解决适用中国的法律。 </h4>
  <h4>4.2  本协议在履行过程中，如发生任何争执或纠纷，双方应友好协商解决；若协商不成，任何一方均有权向乙方住所地有管辖权的人民法院提起诉讼。 </h4>
  <h3>5.  其他</h3>
  <h4>5.1  双方可以书面协议方式对本协议作出修改和补充。经过双方签署的有关本协议的修改协议和补充协议是本协议组成部分，具有与本协议同等的法律效力。 </h4>
  <h4>5.2  本协议及其修改或补充均通过智典财富网以电子文本形式制成；同时双方委托代为保管并永久保存在为此设立的专用服务器上备查。双方均认可该形式的协议效力。 </h4>
  <h4>5.3  甲乙双方均确认，本协议的签订、生效和履行以不违反中国的法律法规为前提。如果本协议中的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并不影响本协议其他条款的效力。 </h4>
  <h4>5.4  除本协议上下文另有定义外，本协议项下的用语和定义应具有智典财富网服务协议及其有关规则中定义的含义。若有冲突，则以本协议为准。</h4>
  </div>
 
<%@ include file="/mobile/comm/bott.jsp"%>
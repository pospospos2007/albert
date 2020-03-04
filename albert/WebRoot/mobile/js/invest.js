  var ver_amount =  /^([1-9][\d]{0,7}|0)(\.[\d]{1,2})?$/;
	 // 刷新验证码
	 function coderefresh() {  
	 	var d = new Date();
	 	var url='/registVerfyCode?a=' + d.getTime();
	 	$('#imgverCode').attr("src",url);
	 }
	 
	   	$(function() {

	   		//	  托管账户余额查询
	   	 if("" !=$("#userId").val()  ){
	   		var d=new Date();
	   		var url='/pay/chinapnrj/start-QueryMoney.action';
		   	 $.getJSON(url,{'payReqFromApp.userId':$("#userId").val(),'version':d.getTime() },function (data){
		   		if(data == null ){
			   		$("#cash").each(function(){
		   				$(this).html('0.00');
		   		  	});
			   		$("#usercash").val('0.00');
			   	 }else{
			   		if(data['AvlBal']){
			   			$(".cash").each(function(){
			   				$(this).html((data['AvlBal']).replace(/\d(?=(\d{3})+\.)/g, '$&,'));
			   		  	});
			   			$('#usercash').val(data['AvlBal']);
			   		}
			   	 }
		   	    });
	   	 }

	   	      
	 	   	 //立即投资
	        	$('.login_f_btn').click(function(){	        	
	        		  var cash= $('#usercash').val();//账户余额
		 	   	 	  var amount= $("#amount").val();//借款金额
		 	   	      var investAmount=$("#investAmount").val();//投标金额
		 	   	      var remaining=$("#remaining").val();//剩余金额
		 	   	      var beginAmount =$("#beginAmount").val();//起投金额
		 	   	      var increaseAmount=$("#increaseAmount").val();//递增金额
		 	   	      var verifycode=$("#verifycode").val();//验证码	
		 	   	     if(investAmount==null||investAmount==''){//投标金额非空
		 	   	 	    showDialogHTML('投标金额为空！');
		 	   	 		 return false;
		 	   	 	  }
			 	   	 if(verifycode==null||verifycode==''){
			 	   	    showDialogHTML('请输入验证码！');
		 	   	 		 return false;
		 	     	 }
		 	   	 	if(remaining-increaseAmount<0&&(investAmount*100)/100!=remaining){
	 	   	 		    showDialogHTML('剩余金额小于递增金额时，最后一笔投资金额必须等于剩余金额');
	 	   	 		     return false;
	 	   	 		 }else{
	 	   	 		    //正则金额校验
		 	   	 	    if(ver_amount.test(investAmount)){
		 	   	 		 if(investAmount - remaining > 0){//投标金额不能大于剩余金额
		 	   	 		        showDialogHTML('投标金额不能大于剩余金额');
		 	   	 				 return false;
		 	   	 		  }
		 	   	 		 if(investAmount - beginAmount < 0 &&  remaining-beginAmount>=0){//投标金额不能小于起投金额
		 	   	 		        showDialogHTML('投标金额不能小于起投金额');
		 	   	 			    return false;
		 	   	 		 }
		 	   	 		 
		 	   	 		 if(investAmount*100 - cash*100 > 0 && remaining-beginAmount>=0){//投标金额不能小于账户金额
		 	   	 		     showDialogHTML('账户余额不足！');
		 	   	 			 return false;
		 	   	 		 }
			 	   	 	 if((investAmount*1-beginAmount*1) % increaseAmount*1!=0 &&(remaining*1-(increaseAmount*1+beginAmount*1)>=0)  ){//投标递增金额
			 	   	 	     showDialogHTML('投标金额必须为递增金额整数倍！');
			 	 			 return false;
			   	 		 }
			 	   	 	 }else{
			 	   	 	    showDialogHTML('您的输入有误，请重新输入！');
		 	   	 		  return false;
			 	   	 	 }
	 	   	 	 }
	 	   	 	
	 	   	 		var url = "/verifycode";
	 	   	 		var investAmount =$('#investAmount').val();
	 	   	 		var loanId = $('#loanId').val();
	 	   	 		var userId=$('#userId').val();	
	 	   	 	   $("#loadingImage").show();
	 	           $.post(url,{	
	 	   	 			"vlidate" : verifycode
	 	   	 			},function(root){	
	 	   	 			  $("#loadingImage").hide();
	 	   	 				if(root == ""|| null == root){	 	   	 				  
	 	   	 					$("#verifycode").val("");
	 	   	 				    $('#invest_btn').removeClass('disabled');
	 	   	 				    window.location.href="/pay/chinapnr/start-InitiativeTender.action?payReqFromApp.loanId="+loanId+"&payReqFromApp.transAmt="+investAmount+"&payReqFromApp.userId="+userId;
	 	   	 			}else{
	 	   	 					coderefresh();
	 	   	 					$("#verifycode").val("");
	 	   	 				    showDialogHTML(root);
	 	   	 					$('#invest_btn').removeClass('disabled');
	 	   	 				}
	 	   	 			},"json");
	        		
	 	   	   }); 
	   	 });


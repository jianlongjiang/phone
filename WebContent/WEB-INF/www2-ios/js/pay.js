///app/ordRecord/orderGenerate

var  orderRecod ;
function  hide3(to){
	hide(3);
	// 
	appAjax({
		type : "POST",
		url: "app/ordRecord/orderGenerate",
		//data:{"proids":ArraytoString(proids),"counts":ArraytoString(counts),"shopid":shop.id,"orderFrom":orderFrom,"deliveryDate":deliveryDate,"giftid":giftid,"recId":recId},
		success : function(data) {
			if (data.isSuccess) {
				orderRecod = data.result;
				$("#pay_createTime").html(orderRecod.createTime);
				$("#order_show_orderNo").html(orderRecod.outtradeno);
				showPage(to);
			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	});
	
}

function cancelOrder(to){
	appAjax({
		type : "POST",
		url: "app/ordRecord/cancelOrder",
		data:{"recordId":  orderRecod.recordId},
		success : function(data) {
			if (data.isSuccess) {
				orderRecod = null;
				showPage(to);
			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	});
}




var  isTestPay = false;

function	payMoney(to){
	if(isTestPay){
		appAjax({
			type : "POST",
			url: "test_alipay",
			data:{"out_trade_no":orderRecod.outtradeno},
			success : function(data) {
				if (data.isSuccess) {
					alert("测试支付成功");
					d = {"resultStatus":9000};
					resultString(d);
				} else {
					alert(data.message);
				}
			},
			dataType : "json"
		});
	}else {
		var  out_trade_no =  orderRecod.outtradeno;
		var 	subject = orderRecod.outtradeno;;
		var bodtxt = orderRecod.outtradeno;;
		var total_fee = orderRecod.tradeCount;
		alert(total_fee);
		total_fee  = 0.01;  //  正式测试的
//		total_fee = 100;
		 var url = BASE_URL+"/ordernotifyurl";
		 
	     window.plugins.Pgalipay.alipay(out_trade_no,subject,bodtxt,total_fee,url,
	                                    function(success) {
	    	 						resultString(success);
	                                    }, function(fail) {
	                                    			alert("encoding failed: " + fail);
	       }); 
	}

	
	
	

	
}   

function resultString(data){
	alert(data.resultStatus);
	if(!isBlack(data) &&  data.resultStatus==9000){
		$("#show_orderNo").html(orderRecod.outtradeno);
		$("#show_createTime").html(orderRecod.createTime);
		$("#show_payTime").html(new Date().format("yyyy-MM-dd hh:mm:ss"));
		var user = get("userInfo");
		user.isVip = true;
		set("userInfo",user);
		showPage("order-details-next");
	}else {
		showPage("home");
	}
}







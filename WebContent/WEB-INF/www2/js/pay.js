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


function	payMoney(to){
//	appAjax({
//		type : "POST",
//		url: "app/ordRecord/orderGenerate",
//		//data:{"proids":ArraytoString(proids),"counts":ArraytoString(counts),"shopid":shop.id,"orderFrom":orderFrom,"deliveryDate":deliveryDate,"giftid":giftid,"recId":recId},
//		success : function(data) {
//			if (data.isSuccess) {
//				
//				if(res.orderFrom == "alipay"){
//					alipay(res.orderCode,res.payment,res.payment,res.payment);//调用支付宝
//				}else if(res.orderFrom == "weixing"){
//					
//				}else{
//					loadDetailOrder('orderDetail',res.id);
//				}
//			} else {
//				alert(data.message);
//			}
//		},
//		dataType : "json"
//	});
	var  out_trade_no =  orderRecod.outtradeno;
	var 	subject = orderRecod.outtradeno;;
	var bodtxt = orderRecod.outtradeno;;
	var total_fee = orderRecod.tradeCount;
	//total_fee  = 0.01;
	 var url = BASE_URL+"/ordernotifyurl";
	 
     window.plugins.Pgalipay.alipay(out_trade_no,subject,bodtxt,total_fee,url,
                                    function(success) {
//                                    var element = document.getElementById('alipaytxt');
//                                    element.innerHTML = "支付结果2:"+success;
    	 
    	 
    	 resultString(success);
                                    }, function(fail) {
                                    			alert("encoding failed: " + fail);
       }); 
	
}   

function resultString(data){
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




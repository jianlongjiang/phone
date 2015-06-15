var orderScroller=null;
$.ui.ready(function(){
	orderScroller=$("#order").scroller({
	    verticalScroll:true,
	    horizontalScroll:false,
	    autoEnable:true
	});

//infinite
	orderScroller.addInfinite();
	$.bind(orderScroller, "infinite-scroll", function () {
		console.log(pageTo);
	    var self = this;
	    console.log("infinite triggered");
	    //Append text at the bottom
	    $(this.el).find('.itemContainer').append("<div id=\"infinite\" style=\"margin-top:10px;width:100%;height:20px;text-align:center;\">加载中...</div>");
	    //Register for the infinite-scroll-end - this is so we do not get it multiple times, or a false report while infinite-scroll is being triggered;
	    $.bind(orderScroller, "infinite-scroll-end", function () {
	        //unbind the event since we are handling it
	        $.unbind(orderScroller, "infinite-scroll-end");
	        self.scrollToBottom();
	        appAjax({
	        	type:"POST",
	        	url:"busapp/shop/orderlist/p"+pageTo+"?"+orderLoadType,
	        	success:function(temp){
	        		$(self.el).find("#infinite").remove();
		            self.clearInfinite();
		            self.scrollToBottom();

	        		if(temp.isSuccess){
						console.log('success');
						if(temp.result.length>0){
							//console.log(show_newOrders(temp.result));
							$(self.el).find('.itemContainer').append(show_newOrders(temp.result));
							pageTo += 1;
						}else{

						}
					}else{

					}
	        	}
	        });
	    });
	});
	 


	$('#newBtn').click(function(){
		orderScroller.scrollToTop(1500);
		$('#orderTopBtn .button').removeClass('active');
		$(this).addClass('active');
		pageTo=1;
		orderLoadType = "orderStatus=un_audit";
		ajaxOrder();
	});
	$('#tuiBtn').click(function(){
		orderScroller.scrollToTop(1500);
		$('#orderTopBtn .button').removeClass('active');
		$(this).addClass('active');
		pageTo=1;
		orderLoadType = "tuiStatus=asktui";
		ajaxOrder();
	});
	$('#handleBtn').click(function(){
		orderScroller.scrollToTop(1500);
		$('#orderTopBtn .button').removeClass('active');
		$(this).addClass('active');
		pageTo=1;
		orderLoadType = "handleStatus=un_audit";
		ajaxOrder();
	});
	ajaxOrder();
});

function ajaxOrder(){
	appAjax({
	        	type:"POST",
	        	url:"busapp/shop/orderlist/p1?"+orderLoadType,
	        	success:function(temp){
	        		if(temp.isSuccess){
						console.log('success');
						$('#order_list .itemContainer').html(show_newOrders(temp.result));
	        			pageTo = 2;
					}else{

					}
	        	}
	        });
}
var pageTo=1;
var hasOrderScroller=false;
var orderLoadType = "orderStatus=un_audit";
//订单管理页面
function onOrderLoad(){
	$('#orderTopBtn').show();
	loadBanner();
	$('#common_header h1').html('我的订单');
	// $('#order .afScrollPanel')[0].style.cssText="";
}
function loadBanner(){
	appAjax({
		type:"POST",
		url:"app/bulkproduct/findTop",
		success:function(temp){
			if(temp.isSuccess){
				var result = temp.result;
				var html = "";
				for(var j = 0 ;j<result.length;j++){
					var item = result[j];
					html += "<div onclick=\"loadDDPage("+item.id+")\" class=\"item\" data-id=\""+ item.id +"\">"
                        + "<img src=\""+item.imageNum+"\">"
                        + "</div>";
				}
				$('#carousel_sl').html(html);
				// init_carousel();
			}else{
				console.log(temp.message);
			}
		}
	});
}

function show_newOrders(result) {
	//	console.log(result);
	var html = "";
	var showst = {
		"ship":"已发货",
		"canceled":"交易取消",
		"success":"交易成功",
		"refund":"退款中"
	};
	var tuist = {
		"tuisuccess":"退款成功",
		"tuiing":"等待退款",
		"asktui":"申请退款",
		"refuse":"拒绝退款",
		"normal":null
	};
	for ( var j=0;j<result.length;j++) {
		var id = result[j].order.id;
		var order_from = result[j].order.order_from ? result[j].order.order_from:"未查到";
		var orderCode = result[j].order.orderCode ? result[j].order.orderCode:"订单号";
		var createTime = result[j].order.createTime ? result[j].order.createTime:"下单时间";
		var receiver_name = result[j].order.receiver_name?result[j].order.receiver_name:"姓名未显示";
		var receiver_mobile = result[j].order.receiver_mobile?result[j].order.receiver_mobile:"号码未显示";
		var receiver_state = result[j].order.receiver_state? result[j].order.receiver_state : "省";
		var receiver_city = result[j].order.receiver_city ? result[j].order.receiver_city:"市";
		var receiver_district = result[j].order.receiver_district ? result[j].order.receiver_district : "区";
		var receiver_address = result[j].order.receiver_address ? result[j].order.receiver_address : "地址";
		var showStatusText=tuiStatusText=buttons=proList="";
        var products = result[j].product;
        for(var k=0;k<products.length;k++){
        	proList+="<tr>"
             +                                   "<td>"+products[k].productName+"</td>"
             +                                   "<td>x"+products[k].productNumber+"</td>"
             +                                   "<td>"+products[k].totalFee+"元</td>"
             +                               "</tr>";
        }
		switch(orderLoadType){
			case "orderStatus=un_audit":
			showStatusText = "待接单";
			buttons = "<div class=\"btn_in2\">"
             +                           "<a class=\"red_btn\" onclick=\"rejectOrder("+id+")\">拒单</a>"
             +                       "</div>"
             +                       "<div class=\"btn_in2\">"
             +                           "<a class=\"green_btn\" onclick=\"receiveOrder("+id+")\">接单</a>"
             +                      " </div>";
			break;
			case "tuiStatus=asktui":
			showStatusText = "申请退款";
			buttons = "<div class=\"btn_in2\">"
             +                           "<a class=\"red_btn\" onclick=\"rejectTui("+id+")\">拒绝退款</a>"
             +                       "</div>"
             +                       "<div class=\"btn_in2\">"
             +                           "<a class=\"green_btn\" onclick=\"receiveTui("+id+")\">同意退款</a>"
             +                      " </div>";
			break;
			case "handleStatus=un_audit":
			showStatusText = showst[result[j].order.order_status]?showst[result[j].order.order_status]:"";
			tuiStatusText = tuist[result[j].order.tui_status]?";退款状态:"+tuist[result[j].order.tui_status]:"";
			break;
		}

		html += "<div class=\"item\">"
             +                   "<div class=\"order_detail\">"
             +                      "<div class=\"clearfix\">"
             +                           "<div class=\"label\">"
             +                               "订单编号"
             +                           "</div>"
             +                           "<div class=\"after_label\">"
             +                               orderCode
             +                           "</div>"
             +                       "</div>"
             +                       "<div class=\"gray6 clearfix\">"
             +                           "<div class=\"label\">"
             +                               "下单时间"
             +                           "</div>"
             +                           "<div class=\"after_label\">"
             +                               createTime
             +                           "</div>"
             +                       "</div>"
             +                   "</div>"
             +                   "<div class=\"user_detail gray6\">"
             +                       "<div class=\"gray6 clearfix\">"
             +                           "<div class=\"label\">"
             +                               receiver_name
             +                           "</div>"
             +                           "<div class=\"after_label\">"
             +                               receiver_mobile
             +                           "</div>"
             +                       "</div>"
             +                       "<div class=\"gray6 clearfix\">"
             +                           "<div class=\"label\">"
             +                               "地址"
             +                           "</div>"
             +                           "<div class=\"after_label\">"
             +                               receiver_state+receiver_city+receiver_district+receiver_address
             +                           "</div>"
             +                       "</div>"
             +                   "</div>"
             +                   "<div class=\"pro_detail gray6\">"
             +                       "<table>"
             +                           "<tbody>"
             +                               proList
             +                           "</tbody>"
             +                       "</table>"
             +                   "</div>"
             +                   "<div class=\"other grayb\">"
             +                       "<table>"
             +                           "<tbody>"
             +                               "<tr>"
             +                                   "<td>支付方式:"+order_from+"</td>"
             +                                   "<td>希望送达时间:12:00-13:00</td>"
             +                               "</tr>"
             +                           "</tbody>"
             +                       "</table>"
             +                       "<div class=\"redtext\">状态:"+showStatusText+tuiStatusText+"</div>"
             +                   "</div>"
             +                   "<div class=\"btn_group clearfix\">"
             +                       buttons
             +                   "</div>"
             +               "</div>";
	}
	return html;
}	
function rejectOrder(id){
	appAjax({
		type:"POST",
		url:"busapp/shop/noaccept",
		data:{
			id:id
		},
		success:function(temp){
			if(temp.isSuccess){
				alert(temp.message);
			}else{
				$("#afui").popup({
							message: temp.message,
							suppressTitle: true,
							cancelText:"知道了",
							cancelOnly:true
						});
			}
		}
	});
}
function rejectTui(id){
	appAjax({
		type:"POST",
		url:"busapp/shop/noagree",
		data:{
			id:id
		},
		success:function(temp){
			if(temp.isSuccess){
				alert(temp.message);
			}else{
				$("#afui").popup({
							message: temp.message,
							suppressTitle: true,
							cancelText:"知道了",
							cancelOnly:true
						});
			}
		}
	});
}
function receiveOrder(id){
	appAjax({
		type:"POST",
		url:"busapp/shop/accept",
		data:{
			id:id
		},
		success:function(temp){
			if(temp.isSuccess){
				alert(temp.message);
			}else{
				$("#afui").popup({
							message: temp.message,
							suppressTitle: true,
							cancelText:"知道了",
							cancelOnly:true
						});
			}
		}
	});
}	
function receiveTui(id){
	appAjax({
		type:"POST",
		url:"busapp/shop/agree",
		data:{
			id:id
		},
		success:function(temp){
			if(temp.isSuccess){
				alert(temp.message);
			}else{
				$("#afui").popup({
							message: temp.message,
							suppressTitle: true,
							cancelText:"知道了",
							cancelOnly:true
						});
			}
		}
	});
}

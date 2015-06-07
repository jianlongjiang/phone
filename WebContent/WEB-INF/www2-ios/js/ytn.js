$(document).ready(function(){
	// if(checkLogin)
	// 	alert('1');
	// else
	// 	alert('0');
	// $("#login-btn").bind("click", function() {
	// 		if($("#frm_test1").val()==""){
	// 			alert("填写手机号码");
	// 			return ;
	// 		}else if( $("#frm_test2").val()==""){
	// 			alert("填写验证码");
	// 			return ;
	// 		}
	// 		var mobile = $("#login_mobile").val();
	// 		var pass = $("#vali_code").val();
	// 		$.ui.showMask('正在加载');
	// 		$.ui.blockUI(.3);
			
	// 		appAjax({
	// 			fromLogin : true,
	// 			type : "POST",
	// 			data:{
	// 				mobileNo:mobile,
	// 				password:pass,
	// 			},
	// 			url : "app/memberInfo/shoplogin",
	// 			success : function(data) {
	// 				$.ui.unblockUI();
	// 				$.ui.hideMask();
	// 				if (data.isSuccess) {
	// 					saveAppToken(data.result.appToken);
	// 					set(key_user,data.result.user.id);
	// 					showPage('home');
	// 				} else {
	// 					alert(data.message);
	// 				}
	// 			},
	// 			error: function(){
	// 				$.ui.popup("网络错误，请重试！");
	// 			},
	// 			dataType : "json"
	// 		});
	// 	//}
	// });
});

var carousel,carousel_sl;
function init_carousel() {
	carousel=$("#carousel").carousel({
		pagingDiv: "carousel_dots",
		pagingCssName: "carousel_paging2",
		pagingCssNameSelected: "carousel_paging2_selected",
		preventDefaults:false,
		wrap:true //Set to false to disable the wrap around
	});
	carousel_sl=$("#carousel_sl").carousel({
		pagingDiv: "carousel_dots_sl",
		pagingCssName: "carousel_paging2",
		pagingCssNameSelected: "carousel_paging2_selected",
		preventDefaults:false,
		wrap:true //Set to false to disable the wrap around
	});
}
window.addEventListener("load", init_carousel, false);

function loadDDPage(id){
	$.ui.loadContent('dingdan',false,false,'fade');
	$('#orderTopBtn').hide();
	appAjax({
		type:"POST",
		url:"app/bulkproduct/productDetail",
		data:{
			id:id,
		},
		success:function(temp){
			if(temp.isSuccess){
				var result = temp.result;
				$('#dingdan .article').html(result.productDesc);
				$('#dingdan_dgsp').val(result.bulkProduct);
				$('#dingdanId').val(id);
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
function subDingdan(){
	
	var id = $('#dingdanId').val();
	var bulkUser = $('#dingdan_name').val();
	if(!bulkUser){
		alert('姓名不能为空');
		return false;
	}
	var bulkMobile = $('#dingdan_mobile').val();
	if(!checkMobile(bulkMobile)){
		alert('请填写正确手机号码');
		return false;
	}
	var bulkNum = $('#dingdan_no').val();
	if(!bulkNum){
		alert('数量不能为空');
		return false;
	}
	var bulkAddress = $('#dingdan_addr').val();
	var message = $('#dingdan_mess').html();
	appAjax({
		type:"POST",
		url:"app/bulkproduct/save",
		data:{
			id:id,
			bulkUser:bulkUser,
			bulkMobile:bulkMobile,
			bulkNum:bulkNum,
			bulkAddress:bulkAddress,
			message:message
		},
		success:function(temp){
			if(temp.isSuccess){
				$("#afui").popup({
					message: "提交成功",
					suppressTitle: true,
					cancelText:"好的",
					cancelOnly:true
				});
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
function onProManagerLoad(){
	$('#orderTopBtn').hide();
	$('#common_header h1').text('菜品管理');
	console.log('====onProManagerLoad!====');
	appAjax({
		type: "POST",
		url: "busapp/shop/greenslist",
		success: function(temp){
			//console.log(temp);
			if(temp.isSuccess){
				console.log('success');
				var fruits=temp.result;
				var html="";
				for ( var atype in fruits ){
					//console.log(atype);
					var htmlThisType="";
					htmlThisType+="<div class=\"pro_atype\"><h2>"+atype+"</h2><div class=\"content\">";
					
					var fruitsInType = fruits[atype];

					for(var j=0;j<fruitsInType.length;j++){
						var item = fruitsInType[j];

						var price=item.productMarketPrice;
						if(!price) price="";

						var image=item.image?item.image:"img/nopic.gif";
						var status=item.productStatus;
						var statusText=status ? "下架":"上架";
						var prid=item.productId;

						var htmlItem = "<div class=\"item clearfix\">";

                        htmlItem +="<img src=\""+image+"\" />";
                        htmlItem +="<div class=\"controls\">";
                        htmlItem +="<div class=\"up clearfix\">";
                        htmlItem +="<div class=\"name left_b\">";
                        htmlItem +=item.productName;
                        htmlItem +="</div>";
                        htmlItem +="<div class=\"sjxj right_b\">";
                        htmlItem +="<a class=\"red_btn\" pro_status=\""+status+"\" id=\"changeProStatus_btn_id"+prid+"\" onclick=\"changeProStatus("+prid+")\">"+statusText+"</a>";
                        htmlItem +="</div>";
                        htmlItem +="</div>";
                        htmlItem +="<div class=\"down clearfix\">";
                        htmlItem +="<div class=\"price left_b\">";
                        htmlItem +="<input id=\"priceM_id"+prid+"\" type=\"text\" value=\""+price+"\"/>元";
                        htmlItem +="</div>";
                        htmlItem +="<div class=\"surePrice right_b\">";
                        htmlItem +="<a class=\"green_btn\" onclick=\"modifyPrice("+prid+")\">确定修改</a>";
                        htmlItem +="</div>";
                        htmlItem +="</div>";
                        htmlItem +="</div>";
                        htmlItem +="</div>";
                        htmlThisType += htmlItem;
					}

					htmlThisType+="</div></div>";
					html+=htmlThisType;
				}
				$('.proManager_container').html(html);
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
function modifyPrice(id){
	var price=$('#priceM_id'+id).val();
	if(!isNumber(price)&&!isfloat(price)){
		alert("请输入数字");
		return false;
	}
		
	//alert(price);
	appAjax({
		type:"POST",
		url:"busapp/shop/editprice",
		data:{
			id:id,
			marketPrice:price
		},
		success:function(temp){
			if(temp.isSuccess){
				console.log('success');
				alert('操作成功');
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
function changeProStatus(id){
	var btnObj=$('#changeProStatus_btn_id'+id);
	
	var status=btnObj.attr('pro_status');

	if(status=="false") status=false;
	else status=true;

	var text="下架";
	if(status){
		text="上架";
	}

	var ant_status_string = (!status).toString();
	
	appAjax({
		type:"POST",
		url:"busapp/shop/editstatus",
		data:{
			id:id,
			productStatus:status
		},
		success:function(temp){
			if(temp.isSuccess){
				alert('操作成功');
				btnObj.html(text);
				btnObj.attr('pro_status', ant_status_string);
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

function onStaticsLoad(){
	$('#orderTopBtn').hide();
	$('#common_header h1').text('数据统计');
	appAjax({
		type:"POST",
		url:"busapp/shop/sysdata",
		success:function(temp){
			if(temp.isSuccess){
				var result = temp.result;
				
				for(var key in result)
				{
					var html="";
					html="<tr><th>项目</th><th>订单数量</th><th>订单金额（元）</th></tr>";
	                
	                var day=result[key];
	                var has=false;
	                for(var j=0;j<day.length;j++){
	                	if(day[j].type=="all"){
	                		var sum=count=0;
	                		if(day[j].sum)  sum=day[j].sum;
	                		if(day[j].count) count=day[j].count;
	                		html+="<tr><td>总成交订单</td><td>"+count+"</td><td>"+sum+"</td></tr>";
	                		has=true;
	                	}
	                }
	                if(!has) html+="<tr><td>总成交订单</td><td>0</td><td>0</td></tr>";
	                has=false;

	                for(var j=0;j<day.length;j++){
	                	if(day[j].type=="alipay"){
	                		var sum=count=0;
	                		if(day[j].sum)  sum=day[j].sum;
	                		if(day[j].count) count=day[j].count;
	                		html+="<tr><td>支付宝</td><td>"+count+"</td><td>"+sum+"</td></tr>";
	                		has=true;
	                	}
	                }
	                if(!has) html+="<tr><td>支付宝</td><td>0</td><td>0</td></tr>";
	                has=false;

	                for(var j=0;j<day.length;j++){
	                	if(day[j].type=="weixing"){
	                		var sum=count=0;
	                		if(day[j].sum)  sum=day[j].sum;
	                		if(day[j].count) count=day[j].count;
	                		html+="<tr><td>微信</td><td>"+count+"</td><td>"+sum+"</td></tr>";
	                		has=true;
	                	}
	                }
	                if(!has) html+="<tr><td>微信</td><td>0</td><td>0</td></tr>";
	                has=false;

	                for(var j=0;j<day.length;j++){
	                	if(day[j].type=="delivery"){
	                		var sum=count=0;
	                		if(day[j].sum)  sum=day[j].sum;
	                		if(day[j].count) count=day[j].count;
	                		html+="<tr><td>货到付款</td><td>"+count+"</td><td>"+sum+"</td></tr>";
	                		has=true;
	                	}
	                }
	                if(!has) html+="<tr><td>货到付款</td><td>0</td><td>0</td></tr>";
	                has=false;

	                for(var j=0;j<day.length;j++){
	                	if(day[j].type=="refund"){
	                		var sum=count=0;
	                		if(day[j].sum)  sum=day[j].sum;
	                		if(day[j].count) count=day[j].count;
	                		html+="<tr><td>退款</td><td>"+count+"</td><td>"+sum+"</td></tr>";
	                		has=true;
	                	}
	                }
	                if(!has) html+="<tr><td>退款</td><td>0</td><td>0</td></tr>";
					
					$('.'+key+'_status tbody').html(html);
				}

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
function onHuodongLoad(){
	appAjax({
		type:"POST",
		url:"busapp/shop/coupon",
		success:function(temp){
			if(temp.isSuccess){
				var result = temp.result;
				var html="";
				for(var j=0;j<result.length;j++){
					var name=result[j].name;
					var id=result[j].id;
					html+="<li onclick=\"chooseActive("+id+")\">"+name+"</li>";
				}
				html+="<li onclick=\"chooseActive(0)\">不参加活动</li>";
				$('.huodong_container ul').html(html);
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
function chooseActive(id){
	appAjax({
		type:"POST",
		url:"busapp/shop/coupon",
		success:function(temp){
			if(temp.isSuccess){
				alert('操作成功');
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


//验证数字(整数、浮点数都可以通过)

function isfloat(oNum){

 if(!oNum) return false;

 var strP=/^\d+(\.\d+)?$/;

 if(!strP.test(oNum)) return false;

 try{

  if(parseFloat(oNum)!=oNum) return false;

 }catch(ex){

   return false;

 }

 return true;

}

//验证正整数

function isNumber(oNum){

  if(!oNum) return false;

  var strP=/^\d+$/; //正整数

  if(!strP.test(oNum)) return false;

  return true;

}

function parseBool(num){
	if(num=="true")
		return true;
	else if(num=="false")
		return false;
}
function checkOut(){
	// alert('ck');
	appAjax({
		type:"POST",
		url:"app/memberInfo/logout",
		success:function(temp){
			alert("登出成功");
			clearAll();
			showPage('login');
		}
	});
}












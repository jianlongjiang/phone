$(function(){
 var HomeLogo = window.localStorage.getItem("HomeLogo");
 
 if (HomeLogo == null) {
  window.location.href = "start.html";
 }
});
/////   下载最新版本 
function downNewsVersion() {
    //todo
    //window.location.href = BASE_IMAGE + "lhVel.apk";
    //window.open( "https://itunes.apple.com/cn/app/id983208990?l=zh&ls=1&mt=8", '_system');
    openAppStore();
    //window.open( BASE_IMAGE+"woniubaba.apk", '_system');
    //alert("替换下载链接")
    console.log("下载最新的Phone.app");
}
backUrl ="";
function  checkLoginTo(to){
	
	var userInfo  = get("userInfo");
	if(isBlack(userInfo)) {
		showPage("login");
		$.ui.unblockUI();
		$.ui.hideMask();
		backUrl = to;
		return ;
	}
	showPage(to);
	
}

function  load_userCenter(){
	
	var userInfo  = get("userInfo");
	if(isBlack(userInfo)) {
		showPage("login");
		return ;
	}
	showPage("user-center");
	autoLogin(false);
}



function openAppStore(){
//    window.open("https://itunes.apple.com/cn/app/wechat/id983208990?mt=8&uo=4", "_system");
	window.open( BASE_IMAGE+"/woniubaba.apk" , "_system");
}


function  show2(){
	$.ui.popup({
	    title: "验证原密码",
	    message: '<input  id="alert2Password" type="password"   /> ',
	    cancelText: "取消",
	    cancelCallback: function () {},
	    doneText: "确定",
	    doneCallback: function () {
	  //  	alert( $("#alert2Password").val());
	    	hide2('change-password');
	    },
	    cancelOnly: false
	});
}


function show9() {
	var mobile = value("reg", "mobile");
	$.ui.popup({
	    title: "确认手机号码",
	    message: '我们将发送验证码短信到这个号码 <br/> +86'+mobile ,
	    cancelText: "取消",
	    cancelCallback: function () {},
	    doneText: "确定",
	    doneCallback: function () {
	  //  	alert( $("#alert2Password").val());
	    	//hide12('bound-alipay');
//	    	show(9);
	    	registSms(this, 'ver')
	    },
	    cancelOnly: false
	});
	
//	$("#mobileShow_9").html("+86" + mobile);
	
}


function  show12(){
	$.ui.popup({
	    title: "验证原密码",
	    message: '<input  id="modifyAliPayPwd" type="password"   /> ',
	    cancelText: "取消",
	    cancelCallback: function () {},
	    doneText: "确定",
	    doneCallback: function () {
	  //  	alert( $("#alert2Password").val());
	    	hide12('bound-alipay');
	    },
	    cancelOnly: false
	});
}



var  version = 1.0;
//更新
function checkVersionFun(firstCheck) {
  appAjax({
      type: "POST",
      url: "app/version/checkVersion",
      data: {
          "version": version
      },
      success: function(data) {
          if (data.isSuccess) {
              var dto = data.result;
              if (dto.updateStatus == 0) {
                  if (isBlack(firstCheck)) alert("当前是最新版本");
              } else if (dto.updateStatus == 1) {
                  $("#app_newVersion").html(dto.version);
                  $.ui.popup({
                      title: "更新",
                      message: "可选:" + dto.updateMsg,
                      cancelText: "不更新",
                      cancelCallback: function() {
                          console.log("cancelled");
                      },
                      doneText: "更新",
                      doneCallback: function() {
                          console.log("Done for!");
                          downNewsVersion();
                      },
                      cancelOnly: false
                  });
              } else {
                  $("#app_newVersion").html(dto.version);
                  $.ui.popup({
                      title: "更新",
                      message: "必更:" + dto.updateMsg,
                      cancelText: "退出",
                      cancelCallback: function() {
                          console.log("cancelled");
                          existApp();
                          showPage("login");
                      },
                      doneText: "更新",
                      doneCallback: function() {
                          console.log("Done for!");
                          downNewsVersion();
                      },
                      cancelOnly: false
                  });
              }

          } else {
              alert(data.message);
          }
      },
      dataType: "json"
  });
}

function existApp() {
	isExist = true;
}

$.ui.ready(function(){
//$(function() {
    $("#app_checkVersion").bind("click", function() {
        checkVersionFun(false);
    });
    //$("#app_checkVersion").click();
    checkVersionFun(true);

});



//微信分享
function weixinshare(event, title, url) {
  //alert(imgbasecode);
  var id = $(event).attr("data-value");
  var image = $(event).attr("data-image");
  //  `
  //    .share('文本', WeChat.Scene.session, function () {
  //           console.log('分享成功~');
  //           }, function (reason) {
  //           console.log(reason);
  //           });
  //
  // 或者 (更多选项见后)."data:image/png;base64,
  try{
  WeChat
      .share({
          //type: WeChat.ShareType.image,
          title: title,
          description: title,
          thumbData: "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", //imgbasecode,
          url: url + id
      }, WeChat.Scene.timeline, function() {
    	  	
    	  	if(title=="邀请好友"){
    	  		appAjax({
    	  			type : "POST",
    	  			url : "app/shareLog/shareFirend",
//    	  			data : {
//    	  				"mobile" : mobile,
//    	  			},
    	  			success : function(data) {
    	  				alert("分享图片");
    	  			}
    	  		});
    	  	}
    	  
          console.log("分享成功！");
      }, function(reason) {
          // 分享失败
         // alert("分享失败");
      });
  }catch(e){
	  console.error("分享插件	未添加");
  }
}





var phoneReg = /^1[3|4|5|8][0-9]\d{8}$/;

// data-defer="html/withdraw-cash-details.html" data-load="funName"




function  show3(){
	var userInfo = get("userInfo");
	
	if(isBlack(userInfo)) {
		showPage("login");
		return ;
	}
	
	if(userInfo.isVip){
		alert("您已经是金蜗牛会员");
	}else {
		//TODO
		//show(3);
		
		$.ui.popup({
		    title: "",
		    message: '前往支付包支付100元红包成为金牌会员 ',
		    cancelText: "取消",
		    cancelCallback: function () {},
		    doneText: "确定",
		    doneCallback: function () {
		  //  	alert( $("#alert2Password").val());
		    		hide3('order-details');
		    },
		    cancelOnly: false
		});
	}
}
// 


/**
 * 发送手机验证码
 */
var waitTime = 0;
// 注册
function registSms(event, to) {
	var mobile = value("reg", "mobile");
	if (!checkMobile(mobile)) {
		alert("请填写正确手机号码");
		return;
	}
	hide(9);

	appAjax({
		type : "POST",
		url : "app/userinfo/sendSmsCode",
		data : {
			"mobile" : mobile,
		},
		success : function(data) {
			if (data.isSuccess) {

				saveAppToken(data.result);
				$("#ver .mobile-num").html("+86" + mobile);
				value("res_valitor_from", "mobile", mobile);
				showPage(to);
				//alert("短信已发送，请在3分钟内完成注册！");
				waitTime = 60;

			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	});
}


var  findPwdTime  = 180;
var  findPwdThread  ="";

function  sendSms2(){
	
	var mobile = value("findPwdForm", "mobile");
	if (!checkMobile(mobile)) {
		alert("请填写手机号码");
		return;
	}
	$("#sendSmsBut2").hide();
	findPwdTime = 180;
	findPwdThread =	setInterval(function(){
		if(findPwdTime  > 0 ){
			$("#findPwdTime").html(findPwdTime);
			findPwdTime --;
		}else {
			$("#sendSmsBut2").show();
			clearInterval(findPwdThread);
			$("#findPwdTime").html("");
		}
	},1000);
	
	appAjax({
		type : "POST",
		url : "app/userinfo/sendSmsCode",
		data : {
			"mobile" : mobile,
			"type": "findPwd"
		},
		success : function(data) {
			if (data.isSuccess) {
				saveAppToken(data.result);
			
			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	});
}


function  findPwdForm(){
	var yzm = value("findPwdForm","yzm");
	var password = value("findPwdForm","password");
	var passwordConfrim = value("findPwdForm","passwordConfrim");
	var  checkNames =["yzm","password","passwordConfrim"] ;
	var showcMsgs = ["填写验证码","填写密码","填写验证密码"];
	if(checkForm("findPwdForm",checkNames, showcMsgs)){
		if(password != passwordConfrim){
			alert("密码不一致");
			return ;
		}
		
		appAjax({
			type : "POST",
			url : "app/userinfo/findPwd",
			data : {},
			success : function(data) {
				if (data.isSuccess) {
					alert("密码找回成功");
					showPage("login");
				} else {
					alert(data.message);
				}
			},
			dataType : "json"
		}, "findPwdForm");
	}
}



function valitorSms(to) {
	appAjax({
		type : "POST",
		url : "app/userinfo/isYZM",
		data : {},
		success : function(data) {
			if (data.isSuccess) {
				saveAppToken(data.result);
				// $("#mobileError").html("");
				showPage(to);
			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	}, "res_valitor_from");

}

function	hide11(){
	hide(11);
	showPage("home");
}


function show11(){
	var	content = value("comment","content");
	if(content == ""){
		alert("填写内容");
		return ;
	}
	appAjax({
		type : "POST",
		url : "app/message/add",
		data : {
			"content":content,
		},
		success : function(data) {
			if (data.isSuccess) {
				pageNo++;
				setTimeout(function(){
					 value("comment","content","");
				},3000);
//				show(11);
				alert(" 恩&nbsp;好的好的&nbsp;我收到了<br>我会尽快给你回复哦~ ");
			}
		}
	});
	
	
}



//#chat
function  into_chat(isMore){
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	if (isMore == 1) {
		isMore = true;
	} else {
		isMore = false;
		pageNo = 1;
//		$.ui.scrollToTop("chat");
		$(".more").html("加载更多");
	}
	
	
	appAjax({
		type : "POST",
		url : "app/message/allinfo",
		data : {
			"pageSize":3,
			pageNo:pageNo},
		success : function(data) {
			$.ui.unblockUI();
			$.ui.hideMask();
			if (data.isSuccess) {
				pageNo++;
				var list = data.result;
				if (list.length == 0) {
					$(".more").html("无");
					return;
				} else {
					// / var list = [1,2,3,4,5];
					var html = "";
					list.forEach(function(message) {
								var str = "";
								if(!isBlack(message.content)){
									str = str+'	<div class="chatItem you">                                                 '+                                                
					                '        <div class="chatItemContent">                                         '+                                                
					                '            <img src="image/user-ava.jpg" class="avatar">                     '+                                                
					                '            <div class="cloud cloudText">                                     '+                                                
					                '                <div class="cloudPannel">                                     '+                                                
					                '                    <div class="sendStatus"></div>                            '+                                                
					                '                    <div class="cloudBody">                                   '+                                                
					                '                        <div class="cloudContent">                            '+                                                
					                '                            <pre style="white-space:pre-wrap">'+message.content+'</pre>        '+                                                
					                '                        </div>                                                '+                                                
					                '                    </div>                                                    '+                                                
					                '                    <div class="cloudArrow"></div>                            '+                                                
					                '                </div>                                                        '+                                                
					                '            </div>                                                            '+                                                
					                '        </div>                                                                '+                                                
					                '        <div class="clear"></div>                                             '+                                                
					                '        <div class="time">                                                    '+                                                
					                message.createTime+                                                
					                '        </div>                                                                '+                                                
					                '    </div>                                                                    ';          
								}
								if(!isBlack(message.replyContent)){
									str = str+'    <div class="chatItem me">                                                '+                                                
					                '        <div class="chatItemContent">                                         '+                                                
					                '            <img src="image/user-ava.jpg" class="avatar">                     '+                                                
					                '            <div class="cloud cloudText">                                     '+                                                
					                '                <div class="cloudPannel">                                     '+                                                
					                '                    <div class="sendStatus"></div>                            '+                                                
					                '                    <div class="cloudBody">                                   '+                                                
					                '                        <div class="cloudContent">                            '+                                                
					                '                            <pre style="white-space:pre-wrap">'+message.replyContent+'</pre>        '+                                                
					                '                        </div>                                                '+                                                
					                '                    </div>                                                    '+                                                
					                '                    <div class="cloudArrow"></div>                            '+                                                
					                '                </div>                                                        '+                                                
					                '            </div>                                                            '+                                                
					                '        </div>                                                                '+                                                
					                '        <div class="clear"></div>                                             '+                                                
					                '        <div class="time">                                                    '+                                                
					                message.updateTime+                                                
					                '        </div>                                                                '+                                                
					                '    </div>                                                                    ';        
								}
				                html = str + html;                                       
						});
					if (isMore)
						$("#chatList").prepend(html);
					else
						$("#chatList").html(html);
				}
			} else {
				alert(data.message);
			}
			$.ui.unblockUI();
			$.ui.hideMask();
		},
		dataType : "json"
	});
	
	
}

mySwiper1 = null;
 function intoNewsDetail(){
 	$('body').css("background","#13b09b");
 }
 function outNewsDetail(){
 	$('body').css("background","#323232");
 }
 function outhome(){
		$('body').css("background","#323232");
	//	if(!isBlack(StatusBar)) StatusBar.styleLightContent ();
	}
function into_home() {
	$('body').css("background","#f9f9f9");

//	if(!isBlack(StatusBar)) StatusBar.styleDefault();
	
	var user = get("userInfo");
	if (!isBlack(user)) {
		$("#to-user-center-btn").attr('href', '#user-center');
		if(!isBlack(user.userName))
			$(".userName").html(user.userName.substr(0,6));
	}
}

$.ui.ready(function() {
	// 注册
	$("#reg-password .submit-btn").click(function(event) {
		var mobile = value("res_valitor_from", "mobile");
		var yzm = value("res_valitor_from", "yzm");

		var password = value("reg-password", "password");
		var passwrodConfirm = value("reg-password", "passwordConfirm");

		if (!password) {
			alert("请输入密码！");
			return;
		} else if (!passwrodConfirm) {
			alert("请再次输入密码！")
			return;
		} else if (password !== passwrodConfirm) {
			alert("两次输入密码不一致！");
			return;
		}
		
		$.ui.showMask('加载中');
		$.ui.blockUI(.3);
		
		appAjax({
			type : "POST",
			url : "app/userinfo/register",
			data : {
				"mobile" : mobile,
				"yzm" : yzm
			},
			success : function(temp) {
				console.log(temp);
				if (temp.isSuccess) {
					alert("注册成功！");
					showPage("login");
					$.ui.unblockUI();
					$.ui.hideMask();
				} else if (temp.code === 1000003) {
					alert("该帐号已被注册过！");
				}
				
				$.ui.unblockUI();
				$.ui.hideMask();
			}
		}, "reg-password_from");
	});

	/**
	 * 登入
	 */
	$("#login .submit-btn").click(function(event) {
		if (!$("#login").find("input[name='account']").val()) {
			alert("请输入用户名！");
			return false;

		} else if (!$("#login").find("input[name='password']")) {
			alert("请输入密码！");
			return false;
		} else {
			appAjax({
				type : "POST",
				url : "app/userinfo/login",
				success : function(temp) {
					console.log(temp);
					if (temp.isSuccess) {
						saveAppToken(temp.result.appToken);
						set("userInfo", temp.result.user);
						set("userMore", temp.result.userMore);
						set(key_user, temp.result.user.id);

						var user = temp.result.user;
						$("#to-user-center-btn").attr('href', '#user-center');
						$(".userName").html(user.userName);
						if(isBlack(backUrl))
							showPage("home");
						showPage(backUrl);
					} else {
						alert(temp.message);

					}
				}
			}, 'login-form');
		}
	});
	load_indexMsg();
});



function  hide12(to){
	
	password = $("#modifyAliPayPwd").val();
	if(password == ""){
		alert("请填写密码");
		return ;
	}
	
	appAjax({
		url : "app/userinfo/validatorPwd",
		data : {
			// "sex":sex
			"password" : password
		},
		success : function(r) {
			if (r.isSuccess) {
				hide(12);
				var  userInfo = get("userInfo");
				value("bound-alipay","mobile", userInfo.mobile);
				showPage(to);
			} else {
				alert(r.message);
				return false;
			}
		}
	});
}



function into_userInformation() {
	console.log("into_userxxx");
	var userInfo = get("userInfo");
	var userMore = get("userMore");

	if(isBlack(userInfo)){
		showPage("login");
		return ;
	}
	
	var panel = $("#user-information");
	panel.find(".mobile").html(userInfo.mobile);
	panel.find(".weixin").html(userInfo.weixin);
	
	if(userInfo.isVip) {
		$("#toBeVipA p").html("金蜗牛会员");
		$("#toBeVipA").attr("href","#");
	}else {
		$("#toBeVipA p").html("点亮蜗牛，给梦想一个机会");
		$("#toBeVipA").attr("href","#gold-snail");
	}
	
	if(!isBlack(userInfo.userName)) {
		panel.find(".user-name").html(userInfo.userName.substr(0,6));
	}
	// panel.find(".address").html(userMore.address);
	var sex = userInfo.sex == 1 ? "男" : "女";
	panel.find(".sex").html(sex);
	var  xuanYan = userMore.xuanYan;
	if(!!userMore.xuanYan ) {
		if(xuanYan.length > 8	){
			panel.find(".xuanYan").html(userMore.xuanYan.substr(0,8)+"...");
		}else {
			panel.find(".xuanYan").html(userMore.xuanYan);
		}
	}
	 

	// 其他页面数据设置
	value("modifyWeiXinForm", "weixin", userInfo.weixin);

	value("name", "userName", userInfo.userName);

	// 3
	setFormValue("addressForm", userMore);

	// 宣言
	setFormValue("declaration", userMore);
	var lastNum = 30;
	if (!!userMore.xuanYan)
		lastNum = 30 - userMore.xuanYan.length;
	$("#lastNum").html(lastNum);
}

function modifyXunYanFun() {
	
	// var xuanYan = value("declaration","xuanYan");
	var xuanYan = $("#declaration .xuanYan").val();
	if (xuanYan == "") {
		alert("请填写宣言");
		return;
	}
	if (xuanYan.length > 30) {
		alert("字数不得超过30");
		return;
	}
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		url : "app/userinfo/updateUserInfo",
		data : {
			"xuanYan" : xuanYan
		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {
				var userMore = get("userMore");
				userMore.xuanYan = xuanYan;
				set("userMore", userMore);
				showPage("user-information");
			}
		}
	});
}

/**
 * 红包提取
 */
function reflectMoneyFun(to) {

	var alipayAccount = value("reflectMoneyForm", "alipayAccount");
	if (alipayAccount == "") {
		alert("先绑定支付宝");
		return;
	}
	var reg = /^[1-9][0-9]*(\.[0-9]+)?&/;
	reg = /^\d+(\.\d+)?$/;
	var money = value("reflectMoneyForm", "money");
	if (!reg.test(money) || money ==0) {
		alert("金额不对!");
		return;
	}
	var money = value("reflectMoneyForm", "money");
	var userMoney = get("userInfo").balance;
	if (money > userMoney) {
		alert("余额不足");
		return;
	}

	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		url : "app/redPack/reflectMoney",
		data : {
		// "sex":sex
		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {
				// 钱
				var userInfo = get("userInfo");
				userInfo.balance = userMoney - money;
				set("userInfo", userInfo);
				var panel = $("#withdraw-cash-details");
				panel.find(".createTime").html(r.result);
				panel.find(".alipayAccount").html(alipayAccount);
				panel.find(".money").html("￥" + money);
				showPage(to);
			} else {
				alert(r.message);
			}
			$.ui.unblockUI();
			$.ui.hideMask();
		}
	}, "reflectMoneyForm");

	var money = value("reflectMoneyForm", "money");
}

function hide2(to) {
	password = $("#alert2Password").val();
	if (password == "") {
		alert("密码为空");
		return;
	}

	appAjax({
		url : "app/userinfo/validatorPwd",
		data : {
			// "sex":sex
			"password" : password
		},
		success : function(r) {
			if (r.isSuccess) {
				hide(2);
				showPage(to);
			} else {
				alert(r.message);
				return false;
			}
		}
	});
}

function modifyPwdFun(to) {

	var newPwd = value("change-password", "newPwd");
	var newPwdConfrim = value("change-password", "newPwdConfrim");
	if (newPwd == "" || newPwdConfrim == "") {
		alert("信息不完整");
		return false;
	} else if (newPwd != newPwdConfrim) {
		alert("密码不一致");
		return false;
	}

	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		url : "app/userinfo/modifyPwd",
		data : {
			"password" : password,
			"newPwd" : newPwd
		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();


			if (r.isSuccess) {
				hide(2);
				showPage(to);
			} else {
				alert(r.message);
				return false;
			}
		}
	});
}

// change-password 修改密码
function into_changePassword() {
	//
	var userInfo = get("userInfo");
	value("change-password", "mobile", userInfo.mobile);
}

var pageNo = 1;

// 支付明细 pay-details
function into_payDetail(isMore) {
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	if (isMore == 1) {
		isMore = true;
	} else {
		isMore = false;
		pageNo = 1;
//		$.ui.scrollToTop("pay-details");
		$(".more").html("加载更多");
	}
	var userInfo = get("userInfo");
	$(".balance").html("￥" + (userInfo.balance - userInfo.reflectRed)) ;

	appAjax(
			{
				url : "app/redPack/list",
				data : {
					// "sex":sex
					pageNo : pageNo,
					"pageSize" : 20
				},
				success : function(r) {
					$.ui.unblockUI();
					$.ui.hideMask();


					if (r.isSuccess) {
						// 钱
						pageNo++;

						var list = r.result;
						if (list.length == 0) {
							$(".more").html("无");
						} else {
							// / var list = [1,2,3,4,5];
							var html = "";
							list
									.forEach(function(log) {
										html = html
												+ '	<li>                                           '
												+ '        <p>'
												+ log.more1
												+ '<span>'
												+ log.cash
												+ '</span></p>         '
												+ '        <b>'
												+ log.createTime
												+ '</b>                    '
												+ '    </li>                                         ';
									});
							if (isMore)
								$("#pay-details-list").append(html);
							else
								$("#pay-details-list").html(html);
						}
					} else {
						alert(r.message);
					}
					
					$.ui.unblockUI();
					$.ui.hideMask();
				}
			}, "reflectMoneyForm");
}

// #withdraw-cash 红包体现
function into_withdrawCash() {
	

	console.log("withdraw-cash  红包体现 ");
	var userInfo = get("userInfo");
	var userMore = get("userMore");

	appAjax(
			{
				url : "app/ordRecord/receiveTime",
				success : function(r) {
					$.ui.unblockUI();
					$.ui.hideMask();
					$(".revceiveTime").html(r.result);
				}
			});
			
	
	
	var panel = $("#withdraw-cash");
	value("withdraw-cash", "alipayAccount", userMore.alipayAccount);
	$("#withdraw-cash_money").attr("placeholder",
			"可提现余额为" + userInfo.balance + "，请输入10元的整数")
	// panel.find(".price").html("￥"+userInfo.balance);
	// panel.find(".alipayAccount").html(userMore.alipayAccount);
	// panel.find(".alipayRealName").html(userMore.alipayRealName);
}

// my-red-one 红包总金额
function into_myRedOne() {
	var userInfo = get("userInfo");
	var panel = $("#my-red-one");
	panel.find(".price").html("￥" + userInfo.balance);
}

// 进入钱包
function into_wallet() {
	var userInfo = get("userInfo");
	var userMore = get("userMore");

	var panel = $("#wallet");
	panel.find(".price").html("￥" + userInfo.balance);
	panel.find(".alipayAccount").html(userMore.alipayAccount);
	panel.find(".alipayRealName").html(userMore.alipayRealName);
	autoLogin(false);

}

// 绑定支付宝
function bindApliPayFun(to) {
	if (!checkForm("bound-alipay_from", [  "alipayRealName",
			"alipayAccount" ], [ "账号名称不能为空", "支付宝账号不能为空" ]))
		return;

	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		url : "app/userinfo/bindAlipay",
		data : {
		// "sex":sex
			"password":password
		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {
				// var userInfo = get("userInfo");
				// userInfo.sex = sex;
				// set("userInfo", userInfo);

				var userMore = get("userMore");
				userMore.alipayRealName = value("bound-alipay_from",
						"alipayRealName");
				userMore.alipayAccount = value("bound-alipay_from",
						"alipayAccount");
				set("userMore", userMore);
				showPage(to);
			} else {
				alert(r.message);
			}
		}
	}, "bound-alipay_from");

}

function radioSexMan() {
	$('#man').show();
	$('#woman').hide();
	modifySexFun(1);
}
function radioSexWoman() {
	$('#man').hide();
	$('#woman').show();
	modifySexFun(0);
}

function modifySexFun(sex) {
	appAjax({
		url : "app/userinfo/updateUserInfo",
		data : {
			"sex" : sex
		},
		success : function(r) {
			if (r.isSuccess) {
				var userInfo = get("userInfo");
				userInfo.sex = sex;
				set("userInfo", userInfo);
				showPage("user-information");
			}
		}
	});
}


function  into_address(){
	var  userMore  = get("userMore");
	console.log("xxxx__into_address");
	var prov= "浙江";
	var city= "杭州";
	var dist="西湖区";
	if(!isBlack(userMore)){
		
		if(!isBlack(userMore.prov)) 
			prov = userMore.prov;
		if(!isBlack(userMore.city)) 
			city = userMore.city;
		if(!isBlack(userMore.dist)) 
			dist = userMore.dist;
	}
	
	$("#citySelect").citySelect({
		//nodata:"none",
		//required:false,
		"prov": prov,
		"city": city,
		"dist":dist,
		nodata:"show",
		required:false
	}); 
	

	
	$("#frm_text2_2").get(0).removeAttribute("disabled");
	$("#frm_text2_3").get(0).removeAttribute("disabled");
	setTimeout(function(){
		$("#frm_text2_2").get(0).removeAttribute("disabled");
		$("#frm_text2_3").get(0).removeAttribute("disabled");
	},500);
	
}


function modifyAddress() {
	
	var mobile = value("addressForm", "mobile");
	var mailCode = value("addressForm", "mailCode");
	
	if(mobile!= "" && !checkMobile(mobile)){
		alert("请填写正确手机号码!");
		return ;
	}
	 var pattern = /^[0-9]{6}$/;
	if(mailCode != "" && !pattern.test(mailCode)) {
		alert("请填写正确邮政编码!");
		return ;
	}
	
	
	appAjax({
		url : "app/userinfo/updateUserInfo",
		data : {
		// "userName":userName
		},
		success : function(r) {
			if (r.isSuccess) {
				var userMore = get("userMore");
				userMore.receiverName = value("addressForm", "receiverName");
				userMore.mobile = value("addressForm", "mobile");
				userMore.zone = value("addressForm", "zone");
				userMore.address = value("addressForm", "address");
				userMore.mailCode = value("addressForm", "mailCode");
				
				userMore.prov = value("addressForm", "prov");
				userMore.city = value("addressForm", "city");
				userMore.dist = value("addressForm", "dist");
				
				set("userMore", userMore);
				showPage("user-information");
			}
		}
	}, "addressForm");
}

// 修改昵称
function modifyUserName() {
	var userName = value("name", "userName");
	if (userName == "") {
		alert("填写昵称");
		return;
	}
	
	if(userName.length > 6){
		alert("超过了6字");
		return ;
	}
	
	appAjax({
		url : "app/userinfo/updateUserInfo",
		data : {
			"userName" : userName
		},
		success : function(r) {
			if (r.isSuccess) {
				var userInfo = get("userInfo");
				userInfo.userName = userName;
				set("userInfo", userInfo);
				showPage("user-information");
			} else {
				alert(r.message);
			}
		}
	});

}

// 修改微信号码
function modifyWeiXinFun() {
	var weixin = value("modifyWeiXinForm", "weixin");
	if (weixin == "") {
		alert("填写微信号");
		return;
	}
	appAjax({
		url : "app/userinfo/updatebyWeixin",
		data : {
			"weixin" : weixin
		},
		success : function(r) {
			if (r.isSuccess) {
				var userInfo = get("userInfo");
				userInfo.weixin = weixin;
				set("userInfo", userInfo);
				showPage("user-information");
			} else {
				alert(r.message);
			}
		}
	});

}

// my-messages
function into_myMessages() {

	appAjax({
		url : "app/userinfo/unreadCount",
		data : {},
		success : function(r) {
			if (r.isSuccess) {
				var messageUnreadCount = r.result.messageUnreadCount;
				var userScoreUnreadCount = r.result.userScoreUnreadCount;
				var userRedUnreadCount = r.result.userRedUnreadCount;

				if (messageUnreadCount > 0) {
					$(".messageUnreadCount").html(messageUnreadCount);
				}else {
					$(".messageUnreadCount").html("").hide();
				}

				if (userScoreUnreadCount > 0) {
					$(".userScoreUnreadCount").html(userScoreUnreadCount);
				}else {
					$(".userScoreUnreadCount").html("").hide();
				}

				if (userRedUnreadCount > 0) {
					$(".userRedUnreadCount").html(userRedUnreadCount);
				}else {
					$(".userRedUnreadCount").html("").hide();
				}

			}
		}
	});

	var userInfo = get("userInfo");

	// console.log("------体现金额 --1:reflectRed"+userInfo.reflectRed);
	// var allMoney = userInfo.balance + userInfo.reflectRed;
	// $("#sys-message-div .all .message-price").html("￥"+allMoney);

	// $("#user-message-div").hide();
	$("#user-message-div .all .message-price").html("￥" + userInfo.integration);
	$("#msg_allRed").html("￥" + userInfo.balance);
	
	load_userScoreMsg(0);
}



function  	changeBlack(str){
	if(isBlack(str)){
		return "";
	}
	return  str;
}


function into_teamManager() {
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	
	var userInfo = get("userInfo");
	$("#teamMessageShare").attr("data-value",userInfo.id);
	// var userInfo =
	console.log("xxx===into_teamManager");

	appAjax({
		url : "app/userinfo/msgManager",
		data : {
			"pageNo" : pageNo,
			"pageSize" : 20

		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {

				var info = r.result;
				$("#inviteesNo").html(info.inviteesNo);
				$("#allInviteesCount").html(info.allInviteesCount);
				$("#downCount").html(info.downCount);
				$("#allScore").html(info.allScore);
				userInfo = get("userInfo");
				userInfo.integration = info.allScore;
				set("userInfo",userInfo);
				
				$("#dayGroupCount").html(info.dayGroupCount);
				$("#dayRedCount").html(info.dayRedCount);
				$("#dayInviteesCount").html(info.dayInviteesCount);
				$("#dayVipCount").html(info.dayVipCount);

				$("#allInviteesCount2").html(info.allInviteesCount);
				$("#grounpCount").html(info.grounpCount);
				$("#vipCount").html(info.vipCount);

				var friends = info.friends;
				var html = "";
				friends
						.forEach(function(friend) {
							
							var imgStr = "user-snail.png";
							if(friend.isVip){
								imgStr = "user-gold-snail.png";
							}
							
							html = html
									+ '	<tr	onclick="choseFriend('
									+ friend.id
									+ ')"	>                                                                        '
									+ '        <td>                                                                   '
									+ '            <input type="checkbox"  style="display: inline-table;"    id="friend_'
									+ friend.id
									+ '" name="mobiles"  value="'
									+ friend.mobile
									+ '" >   <img src="image/'+imgStr+'" width="30px">    '
									+ '        </td>                                                                  '
									+ '        <td ><a href="tel:'+friend.mobile+'">'
									+ friend.mobile
									+ '</a></td>                                                   '
									+ '        <td>'
									+ changeBlack(friend.userName)
									+ '</td>                                                          '
									+ '        <td>'
									+ friend.integration
									+ '</td>                                                         '
									+ '        <td>'
									+ friend.inviteFriendVipAmount
									+ '</td>                                                      '
									+ '        <td>'
									+ friend.inviteFriendVipAmount
									+ '</td>                                                      '
									+ '    </tr>                                                                      ';

						});
				$("#friendsList").html(html);

			}
		}
	});
}

function out_integralRed(){
//	if(!isBlack(StatusBar)){
//		StatusBar.styleLightContent();
//		StatusBar.overlaysWebView(true);
//	//	StatusBar.backgroundColorByName("red");
//	}
}


function  out_queryID(){
	hide(5);
}

function into_integralRed() {
	
//	if(!isBlack(StatusBar)){
//		StatusBar.styleLightContent();
//		StatusBar.overlaysWebView(false);
//		StatusBar.backgroundColorByName("red");
//	}
	
	var html = "";
	$("[name='mobiles']").each(function() {
		if ($(this).is(":checked")) {
			var mobile = $(this).val();
			html = html + '<div class="input-group"> '+
                               ' <label>好友账号</label><input type="text" name="mobiles"  value="'+mobile+'" readOnly="true" ><a href="#" onclick="removeMobile(this)" >-</a> '+
                           ' </div> ';
		}
	});
	$(".mobileList").html(html);
	var userInfo = get("userInfo");
	$("#integral-red .score").html(userInfo.integration+"积分");
}


function  exitUser(){
	
	$.ui.popup({
	    title: "",
	    message: '确定退出!',
	    cancelText: "取消",
	    cancelCallback: function () {},
	    doneText: "确定",
	    doneCallback: function () {
	  //  	alert( $("#alert2Password").val());
	    	clearAll();
	    	showPage("login");
	    		
	    },
	    cancelOnly: false
	});
}




//  #发积分红包
function show10(){
	
	var  jifen = value("integral-red","jifen");
	if(  jifen =="" ||  !/^[0-9]*$/.test(jifen)) {
		alert("积分不对");
		return ;
	}
	
	if($("#integral-red  input[name='mobiles']").length == 0){
		alert("请选择要赠送的好友!");
		return ;
	}
	
	appAjax({
		url : "app/userinfo/jifenhongbao",
		data : {
//			"pageNo" : pageNo,
//			"pageSize" : 20
		},
		success : function(r) {
			if(r.isSuccess){
				show(10);
				
				var Integration =  r.result;
				userInfo = get("userInfo");
				userInfo.Integration = Integration;
				set("userInfo",userInfo);
				$("#integral-red .integral-red").html(Integration);
				
				setTimeout(function(){
					$(".mobileList").html("");
				},3000);
			}else {
				alert(r.message);
			}
		}
	},"integral-red_form");	
}


function  addMobileFun(){
	var mobile = $("#addMobile").val();
	
	if (!checkMobile(mobile)) {
		alert("请填写正确手机号码");
		return;
	}
	
	if(userInfo.mobile == mobile){
		alert("不可填写本人");
		return;
	}
	
	
	var html =  '<div class="input-group"> '+
    ' <label>好友账号</label><input type="text" name="mobiles"  value="'+mobile+'" readOnly="true" ><a href="#" onclick="removeMobile(this)" >-</a> '+
    ' </div> ';
	$(".mobileList").append(html);
	
}

function  removeMobile(t){
	$(t).parent().remove();
	
}


// 好友选中
function choseFriend(friendId) {

	if ($("#friend_" + friendId).prop("checked"))
		$("#friend_" + friendId).prop("checked", false);
	else
		$("#friend_" + friendId).prop("checked", true);
	return false;

}



function load_userScoreMsg(isMore) {
	$("#user-message-div").show();
	$("#sys-message-div").hide();
	if (isMore==1 ){
		isMore = true;
	}else {
//		$.ui.scrollToTop("my-messages");
		isMore = false;
		pageNo = 1;
		$(".more").html("加载更多");
	}

	appAjax({
		url : "app/userScoreLog/list",
		data : {
			"pageNo" : pageNo,
			"pageSize" : 20

		},
		success : function(r) {
			if (r.isSuccess) {
				// 积分
				pageNo++;

				var list = r.result;
				if (list.length == 0) {
					$(".more").html("无");
				} else {
					// / var list = [1,2,3,4,5];
					var html = "";
					list
							.forEach(function(log) {
								html = html
										+ '	<div class="message w-bg">																												'
										+ '        <p class="message-title"><span class="message-from"> '
										+ log.msg
										+ '</span><span class="message-date">'
										+ log.value
										+ '</span></p>          '
										+ '        <p class="message-content">'
										+ log.createTime
										+ '</p>                                                                                    '
										+ '    </div>          ';
							});
					if (isMore)
						$("#user-message-div .list").append(html);
					else
						$("#user-message-div .list").html(html);
				}
			}
		}
	});

}


function load_userRedMsg(isMore) {
	$("#user-message-div").hide();
	$("#sys-message-div").show();
	if (isMore==1 ){
		isMore = true;
	}else {
		isMore = false;
		pageNo = 1;
//		$.ui.scrollToTop("my-messages");
		$(".more").html("加载更多");
	}
	appAjax({
		url : "app/redPack/list",
		data : {
			"pageNo" : pageNo,
			"pageSize" : 20

		},
		success : function(r) {
			if (r.isSuccess) {
				// 积分
				pageNo++;
				var list = r.result;
				if (list.length == 0) {
					$(".more").html("无");
				} else {
					// / var list = [1,2,3,4,5];
					var html = "";
					list
							.forEach(function(log) {
								html = html
										+ '	<div class="message w-bg">																												'
										+ '        <p class="message-title"><span class="message-from"> '
										+ log.more1
										+ '</span><span class="message-date">'
										+ log.cash
										+ '</span></p>          '
										+ '        <p class="message-content">'
										+ log.createTime
										+ '</p>                                                                                    '
										+ '    </div>          ';
							});
					if (isMore)
						$("#msg_red_list").append(html);
					else
						$("#msg_red_list").html(html);
				}
			}
		}
	});

}
// user-center 个人中心
function into_userCenter() {
	console.log("into_userxxx");
	var userInfo = get("userInfo");
	var userMore = get("userMore");
	if(isBlack(userInfo)){
		showPage("login");
		return ;
	}
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	

	var panel = $("#user-center");
	//panel.find(".user-ava").attr("src", BASE_IMAGE + userInfo.more2);
	panel.find(".user-name").html(userInfo.userName);
	panel.find(".mobile").html(userInfo.mobile);
	panel.find(".fakeId").html(userInfo.fakeId);
	console.log("==========> fakeId" + userInfo.fakeId);
	if (userInfo.isVip) {
		panel.find(".user-snail").attr("src", "image/user-gold-snail.png");
	} else {
		panel.find(".user-snail").attr("src", "image/user-snail.png");
	}

	appAjax({
		url : "app/userinfo/unreadCount",
		data : {},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {
				var messageUnreadCount = r.result.messageUnreadCount;
				var userScoreUnreadCount = r.result.userScoreUnreadCount;
				var userRedUnreadCount = r.result.userRedUnreadCount;
				var all = messageUnreadCount + userScoreUnreadCount
						+ userRedUnreadCount;
				if (all > 0) {
//					class="af-badge"
					$("#userCenterUnreadCount").html(all);
					$("#userCenterUnreadCount").addClass("af-badge");
					
				} else {
					$("#userCenterUnreadCount").html("");
					$("#userCenterUnreadCount").removeClass("af-badge");
					                            
				}
			}
		}
	});
}

/**
 * 检查手机号码
 * @param mobile
 * @returns {Boolean}
 */
function checkMobile(mobile) {
	if (mobile.length == 11
			&& /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
					.test(mobile)) {
		return true;
	} else {
		return false;
	}
}

function  swiperClckFun(o){
	getNewsDetail($(o).attr("data-id"));
}


$.ui.ready(function(){
	
	//首页顶端图片点击事件
	$(".jjl_swiper-wrapper .swiper-slide").on("click",function(){
		getNewsDetail($(this).attr("data-id"));
	});
	//首页新闻点击事件
	$("#home .news-item").on("click",function(){
		getNewsDetail($(this).attr("data-id"));
	});
	//新闻详情点击事件
	$("#news-detail .jjl_news-detail").on("click",function(){
		getNewsDetail($(this).attr("data-id"));
	});
	
	
	// 顶
	$(".up-ico").on("click",function(){
		appAjax({
			type : "POST",
			url : "app/newsinfo/pointgood?newsId=" +$(this).attr("data-id"),
			success : function(temp) {
				if (temp.isSuccess) {
					var up_num = $("#icon-up-num").text();
					$("#icon-up-num").text(++up_num);
					$(".up-ico").addClass("active");
				} else {
					alert("知道了");
				}
			}
		});
	});
	
	// 踩
	$(".icon.down-ico").on("click",function(){
		appAjax({
			type : "POST",
			url : "app/newsinfo/pointbad?newsId=" + $(this).attr("data-id"),
			success : function(temp) {
				if (temp.isSuccess) {
					var up_down = $("#icon-up-down").text();
					$("#icon-up-down").text(++up_down);
					$(".down-ico").addClass("active");
				} else {
					alert(temp.message);
				}
			}
		});
	});
	
	
	//TODO
	
});
/**
 * 首页新闻信息
 */
 var fhsfew=false;
function load_indexMsg() {
//	alert("$.ur.reaDy().....");
	
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		url : "app/newsinfo/hp",
		data : {
			"version" : version
		},
		success : function(r) {
			$.ui.unblockUI();
			$.ui.hideMask();

			if (r.isSuccess) {
				var b = r.result.firstNews; // 推荐
				
				var   html = "";
				b.forEach(function(e, i, a) {
					console.log(e.topImage);
					$(".jjl_swiper-wrapper .swiper-slide").eq(i).find("img")
							.attr("src", BASE_IMAGE + e.topImage);
					$(".jjl_swiper-wrapper .swiper-slide").eq(i).attr("data-id",e.id);
				});		
				//$("#swiper-container1 .jjl_swiper-wrapper").html(html);
				
				 
				var d = r.result.lastNews;
				d.forEach(function(e, i, a) {
					var item = $("#home .news-item").eq(i);
					item.find("img").attr("src", BASE_IMAGE + e.image);
					item.find(".news-title").text(e.title);
					item.find(".news-date").text(e.createTime);
					item.find(".news-content").text(e.newsDesc.substr(0,30));
					item.attr("data-id",e.id);
				});
				
				
				if(!fhsfew){
					mySwiper1 = new Swiper('#swiper-container1',{
			            pagination: '#pagination1',
			            grabCursor: true,
			            // paginationClickable: true,
			            autoplay: 5000,
			            speed: 500,
			            loop:true,
			            watchActiveIndex:true,
			            calculateHeight: false,
			            // loopAdditionalSlides:2,
			            // loopedSlides:7,
			            // slidesPerView: "auto",
			            // calculateHeight:true,
			           // cssWidthAndHeight :true,
			            onSwiperCreated: function(swiper){
			                $(".swiper-pagination-switch:eq(0)").addClass('swiper-visible-switch').addClass('swiper-active-switch');
			                swiper.reInit();
			            }
			        });
			        fhsfew = true;
				}
				
			} else {
				alert(r.message);
			}
		},
		dataType : "json"
	});
}

var lastNewsDetail = -1;
// 获取新闻详情
function getNewsDetail(newsId) {
	// console.log(newsId);
	showPage("news-detail");
//	$.ui.showMask('正在加载');
//	$.ui.blockUI(.3);
//	if(lastNewsDetail != newsId){
//		$.ui.scrollToTop("news-detail");
//		lastNewsDetail = newsId;
//	}
	///
	// refreshNewsDetailView();
	console.log("====getNewsDetail" );
	appAjax({
		type : "POST",
		url : "app/newsinfo/detail/" + newsId,
		context : $("#news-detail"),
		success : function(temp) {
			// console.log("newsinfo/detail/"+newsId);
			$.ui.unblockUI();
			$.ui.hideMask();

			var d = temp.detail;
			var r = temp.rec_info;
			var action = temp.action;
			$.ui.unblockUI();
			$.ui.hideMask();
			
			$(".newsId").attr("data-value",d.id);
			$("#news-detail #news-detail_1 .news-title").text(d.title);
			$("#news-detail #news-detail_1 .news-date").text(d.author);
			$("#news-detail #news-date").text(d.createTime);

			$("#news-content").text(d.newsDesc);
			$("#news-detail #news-detail_1 .news-img").attr('src',
					BASE_IMAGE + d.image);
			$("#icon-up-num").text(d.pointGoodCount);
			$("#icon-down-num").text(d.pointBadCount);
			$(".J_weixin_share_member").attr("data-value", d.id);
			$(".J_weixin_share_member")
					.attr("data-image", BASE_IMAGE + d.image);
			
			// 点赞标记
			$(".up-ico").removeClass("active");
			$(".down-ico").removeClass("active");
			if (!!action) {
				if (action == "up") {
					$(".up-ico").addClass("active");
				} else if (action == "up") {
					$(".down-ico").addClass("active");
				}
			}

			
			$(".up-ico").attr("data-id",newsId);
			
			//TODO
			$(".icon.down-ico").attr("data-id",newsId);

			r.forEach(function(e, i, a) {
				var item = $("#news-detail .jjl_news-detail").eq(
						i);
				item.find("img").attr("src", BASE_IMAGE + e.image);
				item.find(".news-title").text(e.title);
				item.find(".news-date").text(e.createTime);
				item.find(".news-content").text(e.newsDesc);
				item.attr("data-id",e.id);
			});
		}
	});
}

$.ui.ready(function() {

	load_indexMsg();

	// 签到
	$(".home-sign-in-btn").click(function(event) {
		appAjax({
			type : "POST",
			url : "app/userinfo/registration/",
			success : function(temp) {
				console.log(temp);
				if (temp.isSuccess) {
					alert("签到成功！");
				} else {
					alert("temp.message");
				}
			}
		});
	});
	
	
	$("#xuanYan").bind("keyup",function(){
		
		var last = 30 -$(this).val().length;
		if(last < 0){
			$("#lastNum").html(0);
			$(this).val($(this).val().substr(0,28)+"..");
		}else {
			
			$("#lastNum").html(last);
		}
		
	})

});

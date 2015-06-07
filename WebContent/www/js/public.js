//	sessionStorage.setItem("key", "value"); 	localStorage.setItem("site", "js8.in");
//var value = sessionStorage.getItem("key"); 	var site = localStorage.getItem("site");
//sessionStorage.removeItem("key"); 	localStorage.removeItem("site");
var carousel;
function init_carousel() {
	carousel=$("#my_div_1").carousel({
		pagingDiv: "carousel_dots",
		pagingCssName: "carousel_paging",
		pagingCssNameSelected: "carousel_paging_selected",
		preventDefaults:false,
		wrap:true //Set to false to disable the wrap around
	});
}
window.addEventListener("load", init_carousel, false);
var isExist = false;
function existApp() {
	isExist = true;
}

Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}

	if (/(y+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
}

var alert_ = alert;
function alert(msg) {
	$.afui.popup({
		message : msg,
		suppressTitle : true,
		cancelText : "知道了",
		cancelOnly : true
	});
}
//function  showMsg(msg){}

/**
 * var remindTime = "2008-04-02 10:08:44";
 */
function toDate(str) {
	// var remindTime = "2008-04-02 10:08:44";
	str = str.replace(/-/g, "/");
	return new Date(str);
}
// 例子
//toDate("2008-04-02 10:08:44").format("yy-MM-dd hh:mm:ss");

(function() {
	var noop = function noop() {
	};
	var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml',
			'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
			'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'table',
			'time', 'timeEnd', 'timeStamp', 'trace', 'warn' ];
	var length = methods.length;
	var console = window.console || {};

	while (length--) {
		// Only stub undefined methods.
		console[methods[length]] = console[methods[length]] || noop;
	}
}());

function log(msg) {
	console.log(msg);
}

function  isBlack(str){
	return typeof(str) =="undefined"  || !str;
}

function clearAll() {
	var storage = window.localStorage;

	for (var i = 0; i < storage.length; i++) {
		// key(i)获得相应的键，再用getItem()方法获得对应的值
		// document.write(storage.key(i)+ " : " +
		// storage.getItem(storage.key(i)) + "<br>");
		var key = storage.key(i);
		if (key == "HomeLogo")
			continue;
		console.log(key);
		localStorage.removeItem(storage.key(i));

	}
}
// clearAll();

function saveAppToken(appToken) {
	localStorage.setItem("appToken", appToken);
}

function set(key, value) {
	var jsonStr = JSON.stringify(value);
	var enyStr = strEnc(jsonStr, "lianhaikeji", "appVel", "des");
	localStorage.setItem(key, jsonStr);
}

function getPageNo(name) {
	// var pageNo = get(name + "_pageNo");
	var pageNo = map[name + "_pageNo"];
	console.log(map);
	if (!!pageNo == false) {
		return 1;
	}
	return pageNo;
}

var map = {};
function setPageNo(name, pageNo) {
	// set(name + "_pageNo", pageNo);
	map[name + "_pageNo"] = pageNo;
}

function get(key) {
	var enyStr = localStorage.getItem(key);

	if (!!enyStr) {
		// decStr = strDec(enyStr, "lianhaikeji", "appVel", "des");
		enyStr = JSON.parse(enyStr);
		return enyStr;
	}
	// return enyStr;
	// return {};
}

function getAppToken() {
	return localStorage.getItem("appToken");
}

function merger(targent, params) {
	var a = {};
	if (!!targent) {
		for ( var key in targent) {
			a[key] = targent[key];
		}
	}
	// return $.extend({}, a,params);
	if (!!params) {
		for ( var key in params) {
			a[key] = params[key];
		}
	}
	return a;
}

<<<<<<< .mine
var base_url_base = "http://wnbb.hzlianhai.com";

base_url_base = "http://www.woniubaba.com";
//base_url_base = "http://localhost:8080";
=======
var base_url_base = "http://www.woniubaba.com";
//base_url_base = "http://localhost:8080";
>>>>>>> .r188
//base_url_base = "http://localhost:8082";
var USER_INFO_IMAGE= "http://wnbb.hzlianhai.com/resources/new/img/";
var BASE_URL = base_url_base + "/app/";

//BASE_URL = "http://localhost:8081";
var BASE_IMAGE = base_url_base + "/resources/admin/store/";

(function() {
	var noop = function noop() {
	};
	var methods = [ 'assert', 'clear', 'count', 'debug', 'dir', 'dirxml',
			'error', 'exception', 'group', 'groupCollapsed', 'groupEnd',
			'info', 'log', 'markTimeline', 'profile', 'profileEnd', 'table',
			'time', 'timeEnd', 'timeStamp', 'trace', 'warn' ];
	var length = methods.length;
	var console = window.console || {};

	while (length--) {
		// Only stub undefined methods.
		console[methods[length]] = console[methods[length]] || noop;
	}
}());

function log(msg) {
	console.log(msg);
}

function checkLogin() {
	var appToken = getAppToken();
	if (!!appToken && appToken.length > 10 && !!get("user")) {
		return true;
	} else {
		return false;
	}
	// var userCenter = get(key_user);
	// if(!!userCenter == true) {
	// return true;
	// }
	// return false;
}

function saveAppToken(appToken) {
	localStorage.setItem("appToken", appToken);
}

function set(key, value) {
	var jsonStr = JSON.stringify(value);
	//var enyStr = strEnc(jsonStr, "lianhaikeji", "appVel", "des");
	localStorage.setItem(key, jsonStr);
}

function getPageNo(name) {
	// var pageNo = get(name + "_pageNo");
	var pageNo = map[name + "_pageNo"];
	console.log(map);
	if (!!pageNo == false) {
		return 1;
	}
	return pageNo;
}

var map = {};
function setPageNo(name, pageNo) {
	// set(name + "_pageNo", pageNo);
	map[name + "_pageNo"] = pageNo;
}

function get(key) {
	var enyStr = localStorage.getItem(key);

	if (!!enyStr) {
		// decStr = strDec(enyStr, "lianhaikeji", "appVel", "des");
		enyStr = JSON.parse(enyStr);
		return enyStr;
	}
	// return enyStr;
	// return {};
}

function getAppToken() {
	return localStorage.getItem("appToken");
}

function merger(targent, params) {
	var a = {};
	if (!!targent) {
		for ( var key in targent) {
			a[key] = targent[key];
		}
	}
	if (!!params) {
		for ( var key in params) {
			a[key] = params[key];
		}
	}
	return a;
}

function appAjax(options, formId) {
	// if (!!(options.fromLogin) == false
	// && (isExist == true )) {
	// //console.log("强制更新 or 未登入")
	// showPage("login");
	// //$("#home").attr
	// return;
	// }
	// if (!!(options.fromLogin) == false
	// && (isExist == true )) {
	// //console.log("强制更新 or 未登入")
	// showPage("login");
	// //$("#home").attr
	// return;
	// }
	if( isBlack(options) ) return;
	var  backOptions = $.extend({},options);
	options.data = merger({
		"appToken" : getAppToken()
	}, options.data);
	if (!!formId) {
		var str = "";
		for ( var key in options.data) {
			str += key + "=" + options.data[key] + "&";
		}

		$("#" + formId + " input").each(function(index) {
			if ($(this).prop("disabled")) {
				str += $(this).attr("name") + "=" + $(this).val() + "&";
			}
		})

		options.data = str + $("#" + formId).serialize();
	}
	options.url = BASE_URL + "/" + options.url;
	var flag = true;
	var success = options.success;
	
	options.success = (function(suc, options) {
		var success = function(data) {
			// 页面过度页面 隐藏
			$("#xiong").hide();

			console.log("---- checkLogin");
			//alert("data"+data);
			if (!data.isSuccess && data.code == 1000000) {
				// showPage('login');
				if (!get("user") || !(get("user").id)) {
					showPage("login");
					return;
				}
				if (isAutoLoginAble()) {
					autoLogin(false, options);
					return;
				}
			}
			if (!data.isSuccess ) {
				if (typeof (data.message) == "undefined") {
					data.message = "网络错误请重试!";
				}
			}

			suc(data);
		}
		return success;
	})(options.success, $.extend({}, backOptions));

	if (!options.type)
		options.type = "POST";
	if (!options.dataType)
		options.dataType = "json";
	if (!options.error)
		options.error = function() {
			alert("网络错误，请重试 ");
		};
	$("#xiong").show();
	console.log(options)
	//	console.log("显示小熊");
	$.ajax(options);
}

function isAutoLoginAble() {
	var userId = "";
	try {
		userId = get("user").id;
		return !!userId;
	} catch (e) {
		return false;
	}
}

function autoLogin(isCheck, options) {
	if (!isCheck)
		isCheck = false;
	console.log("============autoLogin ");
	var userId;
	try {
		userId = get("user").id;
	} catch (e) {
		clearAll();
		return;
	}

	$.ajax({
		async : false,
		//		fromLogin : true,
		type : "POST",
		data : {
			"memberId" : userId
		},
		url : BASE_URL + "userinfo/autoLogin",
		dataType : "json"
	}).then(function(data){
		console.log("========auto:" + data);
		if (data.isSuccess) {
			saveAppToken(data.result);
			$("#to-user-center-btn").attr('href', '#user-center');
			$("#loginButoonMsg").html("个人中心");
			appAjax(options);
		} else {
			//alert(data.message);
			$("#to-user-center-btn").attr('href', '#login');
			$("#loginButoonMsg").html("登入");
			clearAll();
			if (!!isCheck) {
				showPage("login");
			}
		}
	});
}
autoLogin(true);


/**
 * $.ui.loadContent('home',false,false,'fade'); 进入首页
 * 
 * @param id
 */
function toPage(id) {
	$.ui.loadContent(id, false, false, 'fade');
	pageInit(id);
}

// 弹出框 $.ui.hideModal();
// 
function showPage(id) {
	// $.ui.hideModal();
	id = '#' + id;
	//console.log(id);
	$.afui.loadContent(id, false, false, 'fade');
}

function pageInit(id) {
}

function value(formId, name, value) {
	if (!formId) {
		return;
	}
	var firt = formId.substr(0, 1);
	var id = "#" + formId + " input[name='" + name + "']";
	if (firt == "#") {
		id = formId + " input[name='" + name + "']";
	}

	if (!!value == false)
		return $(id).val();
	else
		return $(id).val(value);
}

function setProductFormValue(pageId, res, json) {
	$(pageId + "  input").each(
			function(index) {
				var name = $(this).attr("name");

				var resValue = res[name];
				var jsonValue = json[name];
				console.log("name:" + name + "---- resValue:====" + resValue
						+ "---- jsonValue===" + jsonValue);
				if (!!resValue == true) {
					$(this).val(resValue);
				}
				if (!!jsonValue == true) {
					$(this).val(jsonValue);
				}
			})
	$(pageId + "  textarea").each(
			function(index) {
				var name = $(this).attr("name");

				var resValue = res[name];
				var jsonValue = json[name];
				console.log("name:" + name + "---- resValue:====" + resValue
						+ "---- jsonValue===" + jsonValue);
				if (!!resValue == true) {
					$(this).val(resValue);
				}
				if (!!jsonValue == true) {
					$(this).val(jsonValue);
				}

				//value()
			})

	value(pageId, "pro_compDesc2", json.pro_compDesc);
	value(pageId, "description2", res.description);
	value(pageId, "pro_supplement2", json.pro_supplement);

	//	setLat =res.longitude;
	currentLat = res.longitude;
	//setLng  = res.dimensional;
	currentLng = res.dimensional;

	value(pageId, "latlng", currentLat + "," + currentLng);
	var pro_list1 = json.pro_list1;
	var pro_list2 = json.pro_list2;
	if (!!pro_list1) {
		value(pageId, "pro_list11", pro_list1.split(",").length + "个选择");
	}
	if (!!pro_list2) {
		value(pageId, "pro_list21", pro_list2.split(",").length + "个选择");
	}

}

var pageSize = 2;
var pageNo = 1;
var version = '1.0';

function createScroller(scrollerID) {
	var messageScroller;
	// $.ui.ready(function () {
	messageScroller = $("#" + scrollerID).scroller(); // Fetch the
	// scroller
	// console.log("++++++++======="+scrollerID);
	try {
		messageScroller.addInfinite();
	} catch (e) {
		console.log("==== no  id 滑动加载" + scrollerID);
		// alert("==== no id 滑动加载"+scrollerID);
		return messageScroller;
	}
	messageScroller.addPullToRefresh();
	messageScroller.runCB = true;

	messageScroller.noUp = false;
	messageScroller.noDown = false;
	messageScroller.upFun = function() {
		console.log("upFun");
	};
	messageScroller.downFun = function() {
		console.log("upFun");
	};
	messageScroller.noPage = false;

	$.bind(messageScroller, 'scrollend', function() {
		console.log("scroll end");
	});

	$.bind(messageScroller, 'scrollstart', function() {
		console.log("scroll start");
	});
	$.bind(messageScroller, "scroll", function(position) {
	})
	$.bind(messageScroller, "refresh-trigger", function() {
		console.log("Refresh trigger --向上滑动 加载");
	});
	var hideClose;
	$.bind(messageScroller, "refresh-release", function() {
		// 向上滑动加载数据
		var that = this;
		messageScroller.upFun(function() {
			that.hideRefresh(); // 隐藏 提示信息
		});
		console.log("Refresh release-------");
		clearTimeout(hideClose);
		hideClose = setTimeout(function() {
			console.log("hiding manually refresh-----------");
			that.hideRefresh(); // 隐藏 提示信息
		}, 5000);
		return false; // tells it to not auto-cancel the refresh
	});

	$.bind(messageScroller, "refresh-cancel", function() {
		clearTimeout(hideClose);
		// console.log("cancelled");
	});
	messageScroller.enable();

	$
			.bind(
					messageScroller,
					"infinite-scroll",
					function() {
						var self = this;
						console.log("infinite triggered");
						if (messageScroller.noDown == true) {
							return;
						}
						if (messageScroller.noPage == true) {
							$(this.el)
									.append(
											"<div  class='infinite_messageScroller' style='border:2px solid black;margin-top:10px;width:100%;height:20px'>无数据了</div>");
							setTimeout(function() {
								$(self.el).find(".infinite_messageScroller")
										.remove();
								self.clearInfinite();
								self.scrollToBottom();
								messageScroller.noPage = true;
							}, 2000);
							return;
						} else {
							$(this.el)
									.append(
											"<div  class='infinite_messageScroller' style='border:2px solid black;margin-top:10px;width:100%;height:20px'>正在读取数据"
													+ new Date() + "</div>");
							setTimeout(function() {
								$(self.el).find(".infinite_messageScroller")
										.remove();
								self.clearInfinite();
								self.scrollToBottom();
								messageScroller.noPage = true;
							}, 8000);

						}

						$.bind(messageScroller, "infinite-scroll-end",
								function() {
									$.unbind(messageScroller,
											"infinite-scroll-end");
									messageScroller.downFun(function() {
										$(self.el).find(
												".infinite_messageScroller")
												.remove();
										self.clearInfinite();
										self.scrollToBottom();
									});
								});
					});
	$("#" + scrollerID).css("overflow", "auto");
	// });
	return messageScroller;
}

function getarealist() {
};

var showImgId = 1;
function intoProduct(id) {

	appAjax({
		type : "get",
		url : "app/product/appinput/" + id,
		success : function(data) {
			if (data.isSuccess) {
				var res = data.result;
				var json = $.parseJSON(res.properties);

				var imgs = {};
				if (!!res.images) {
					imgs = res.images.split(',');
				}
				var sources;
				if (!res.publishedSources) {
					sources = "";
				} else if (res.publishedSources == 0) {
					sources = "个人";
				} else {
					sources = "公司";
				}

				var imgsHtml = "";
				for ( var i in imgs) {
					var img = imgs[i];
					showImgId++;
					var imgId = "__imageId_" + showImgId;
					imgsHtml += "	<a class=\"temp-photo\" href=\"#piclarge\"><img id=\""
							+ imgId
							+ "\"                                                  "
							+ "	class=\"upImg\" onclick=\"showmepic(this)\"        data-img='"
							+ img
							+ "'                                                                   "
							+ "	src=\"" + BASE_IMAGE + img + "\"></a>        ";
				}
				$("#" + json.pro_productType + " .addphoto div").html(imgsHtml);
				setProductFormValue("#" + json.pro_productType, res, json);
				showPage(json.pro_productType);

			} else {
				alert(data.message);
			}
		},
		dataType : "json"
	});
}

/**
 * 保存通讯录
 * @param name
 * @param nickname
 * @param tel
 * @param address
 * @param notes
 */
   saveTongXunFlag = true;
function saveTongXun(name, nickname, tel, address, notes, sucFun) {
	if(!saveTongXunFlag) return saveTongXunFlag;
	//	console.log(name);
	//创建一个对象
	var contact = navigator.contacts.create();
	//姓名
	contact.displayName = name;
	//昵称
	contact.nickname = nickname;
	//备注
	contact.note = notes;
	//地址
	contact.addresses = [ {
		"type" : "home",
		"pref" : false,
		"formatted" : address
	//地址变量
	} ]
	//电话
	var phoneNumbers = [];
	phoneNumbers[0] = new ContactField('work', tel, false);
	contact.phoneNumbers = phoneNumbers;
	//保存
	contact.save(add_contact_succ,add_contact_err);
	// function(contactError) {
		// saveTongXunFlag = false;
  //                alert(false);
	// }

	return saveTongXunFlag;
}

//添加成功的函数  
function add_contact_succ(contact) {
//    sucFun;
};
//添加失败的函数
function add_contact_err(contactError) {
    saveTongXunFlag = false;
//    console.log(contactError);
//                 alert("contactError--"+contactError);
};


//手机号正则验证
function checkMobile(mobile) {
	if (mobile.length == 11
			&& /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
					.test(mobile)) {
		return true;
	} else {
		return false;
	}
}

$(function() {
	//var loginFormId = "appLoginForm";
	$("#login-btn").bind("click", function() {//登陆方法
//		if (isExist == true) {
//			if (confirm("必须更新版本才能正常试用")) {
//				downNewsVersion();
//			}
//			return;
//		}
		//if (appLoginForm.valid() == true) {
			appAjax({
				fromLogin : true,
				type : "POST",
				data :{
					"mobileNo":$('#login_mobile').val(),
					"password":$('#vali_code').val(),
				},
				url : "app/memberInfo/shoplogin",
				success : function(data) {
					if (data.isSuccess) {
						saveAppToken(data.result.appToken);
						
						set(key_user,data.result.user.id);
						alert("登录成功!");
						showPage('order');
					} else {
						alert(data.message);
					}
				},
				dataType : "json"
			});
		//}
	});
	
	$("#sendSmsCodeButton2").bind("click", function() {//发送验证码
		//var id = "appLoginForm";
		//console.log("appRegisterFormButton");
		var mobile = $('#login_mobile').val();

		if (!checkMobile(mobile)) {
//			$.ui.popup();
			alert("请填写手机号码");
//			$("#mobileError").html("请填写手机号码");
			return;
		}
		appAjax({
			type : "POST",
			url : "app/memberInfo/sendSmsCode",
			data : {
				"mobile" : mobile
			},
			success : function(data) {
				if (data.isSuccess) {
					saveAppToken(data.result);
					//	$("#mobileError").html("");
					$("#sendSmsCodeButton2").hide();
					$("#timeShow2").show();
					$("#timeShow2").html(180);
					timeThread();
				} else {
					alert(data.message);
				}
			},
			dataType : "json"
		});
	})
})
var time = 180;
var timer;
function timeThread(){
	time = 180;
	timer = setInterval("timeThread2()",1000);  
}
function timeThread2() {
			//time = parseInt($("#timeShow2").html());
	if (time > 0) {
		time--;
		$("#timeShow2").html(time);
		// timeThread();
	} else {
		$("#timeShow2").html("");
		$("#timeShow2").hide();
		$("#sendSmsCodeButton2").show();
		time = 180;
		clearInterval(timer); 
	}
}
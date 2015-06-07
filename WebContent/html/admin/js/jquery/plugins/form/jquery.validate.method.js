// 手机号码验证   
$.validator
		.addMethod(
				"mobile",
				function(value, element) {
					var length = value.length;
					return this.optional(element)
							|| (length == 11 && /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/
									.test(value));
				}, "手机号码格式错误");

// 电话号码验证
jQuery.validator.addMethod("phone", function(value, element) {
	var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

// 邮政编码验证
jQuery.validator.addMethod("zipCode", function(value, element) {
	var tel = /^[0-9]{6}$/;
	return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");

// QQ号码验证
jQuery.validator.addMethod("qq", function(value, element) {
	var tel = /^[1-9]\d{4,9}$/;
	return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");


//金额验证
jQuery.validator.addMethod("money", function(value, element) {
	var money = /^(([1-9]\d{0,9})|0)(\.\d{1,2})?$/;
	return this.optional(element) || (money.test(value));
}, "金额格式错误");


// IP地址验证
jQuery.validator
		.addMethod(
				"ip",
				function(value, element) {
					var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
					return this.optional(element)
							|| (ip.test(value) && (RegExp.$1 < 256
									&& RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
				}, "Ip地址格式错误");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
	var chrnum = /^([a-zA-Z0-9]+)$/;
	return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
	var chinese = /^[\u4e00-\u9fa5]+$/;
	return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

// 字节长度验证
jQuery.validator.addMethod("byteRangeLength",
		function(value, element, param) {
			var length = value.length;
			for ( var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
		}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));

// 字符验证
jQuery.validator.addMethod("stringCheck", function(value, element) {
	return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
}, "只能包括中文字、英文字母、数字和下划线");

// 中文字两个字节
jQuery.validator.addMethod("byteRangeLength",
		function(value, element, param) {
			var length = value.length;
			for ( var i = 0; i < value.length; i++) {
				if (value.charCodeAt(i) > 127) {
					length++;
				}
			}
			return this.optional(element)
					|| (length >= param[0] && length <= param[1]);
		}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");
		
		
// Accept a value from a file input based on a required mimetype
jQuery.validator.addMethod("accept", function(value, element, param) {
	// Split mime on commas in case we have multiple types we can accept
	var typeParam = typeof param === "string" ? param.replace(/\s/g, '').replace(/,/g, '|') : "image/*",
	optionalValue = this.optional(element),
	i, file;

	// Element is optional
	if (optionalValue) {
		return optionalValue;
	}

	if ($(element).attr("type") === "file") {
		// If we are using a wildcard, make it regex friendly
		typeParam = typeParam.replace(/\*/g, ".*");

		// Check if the element has a FileList before checking each file
		if (element.files && element.files.length) {
			for (i = 0; i < element.files.length; i++) {
				file = element.files[i];
				// Grab the mimetype from the loaded file, verify it matches
				if (!file.type.match(new RegExp( ".?(" + typeParam + ")$", "i"))) {
					return false;
				}
			}
		}
	}

	// Either return true because we've validated each file, or because the
	// browser does not support element.files and the FileList feature
	return true;
}, jQuery.format("Please enter a value with a valid mimetype."));

// Older "accept" file extension method. Old docs: http://docs.jquery.com/Plugins/Validation/Methods/accept
jQuery.validator.addMethod("extension", function(value, element, param) {
	param = typeof param === "string" ? param.replace(/,/g, '|') : "png|jpe?g|gif";
	return this.optional(element) || value.match(new RegExp(".(" + param + ")$", "i"));
}, jQuery.format("Please enter a value with a valid extension."));

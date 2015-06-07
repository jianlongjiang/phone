 //search.js
 function checkForm(){
	    var lower = 0;
	    var upper =  0;
	    var count = 0;
	    if ($("#PriceLower").length > 0) {
	    	lower = Number($("#PriceLower").val());
			upper = Number($("#PriceUpper").val());
	    }
	    if ($("#maxCount").length > 0) {
	    	count = Number($("#maxCount").val());
	    }
	    if (isNaN(count)) {
			var msg = new QFCPopup.message({
				content:"请输入正确的起订量",
				title:"错误提示",
				height:110
			});
			msg.show();
			return;
		}
		if (isNaN(lower) || isNaN(upper) || lower < 0 || upper < 0 || (lower > upper && upper > 0)) {
			var msg = new QFCPopup.message({
				content:"请输入正确的价格",
				title:"错误提示",
				height:110
			});
			msg.show();
			return;
		}
		
		var localUrl = location.href;
		var urls = localUrl.split("?");
		var oldQuery = urls[1]? urls[1].split("&") :[];
		var baseUrl = urls[0].replace(/p\d+\.html/, 'p1.html');
		var queries = [];
		if(lower != 0 ){
			queries.push('priceLower='+lower);
		} else {
		   queries.push('priceLower=');
		}
		if (upper != 0) {
		   queries.push('priceUpper='+upper);
		} else {
		   queries.push('priceUpper=');
		}
		
		if(count != 0 ){
			queries.push('maxCount='+count);
		}else{
			queries.push('maxCount=');
		}

		var q = uniqueQuery(oldQuery,queries);
		baseUrl += q.length > 0 ?'?' + q.join("&"):'';
		
		location.href = baseUrl;
		return false;
	}

	function uniqueQuery(base, update){

		var hash = {}, arr = [];
		for (var i = 0; i < base.length; i++) {
			var key = base[i].split("=")[0];
			var value = base[i].split("=")[1];
			hash[key] = value;	
		}
		for (var j = 0; j < update.length; j++) {
			var newKey =  update[j].split("=")[0];
			var newValue =  update[j].split("=")[1];
			hash[newKey] = newValue;
		}
		for(key in hash){
		    if (hash[key] != '') {
		    	arr.push(key + '=' + hash[key]);
		    }
		}

		return arr;
	};
	
	
	$(document).ready(function(){
		//form
		$("#J_FPEnter").click(checkForm);
	});
	


	function show(sid) {
		var id = document.getElementById(sid);
		var kid = document.getElementById("area_city" + sid);
		if (id) {
			$(id).show();
			if (kid)
				kid.className = "position";
		}
	}
	function hide(sid) {
		var id = document.getElementById(sid)
		var kid = document.getElementById("area_city" + sid)
		if (id) {
			id.style.display = "none";
			kid.className = "";
		}
	}
	$("#J_all_area").hover(function() {
		$("#all_area").show();
	}, function() {
		$("#all_area").hide();
	});
	
	$(".sregion").on("click", function() {
	
		var name = $(this).closest('table').attr("name");
		var regionName = $(this).text();
		var province = $(this).attr("p");
	
		if (province) {
			regionName = province + "|" + regionName;
		}
		addcondition($(this), name, regionName);
		return false;
	});
	
	var customUrl;
	function addcondition(f, key, dval) {
		var val = GBKencodeURIComponent(f.val());
		if (dval && "所有地区" != $.trim(dval))
			val = GBKencodeURIComponent(dval);
		if (val) {
			var url;
			if (customUrl) {
				url = customUrl;
			}
			if (!url) {
				url = location.href;
			}
			if (url.indexOf("&" + key) != -1) {
				var num = url.indexOf("&", url.indexOf("&" + key) + 1);
				if (num == -1)
					num = url.length;
				var str = url.substring(url.indexOf("&" + key), num);
				url = url.replace(str, "&" + key + "=" + val);
			} else if (url.indexOf("?" + key) != -1) {
				var num = url.indexOf("&", url.indexOf("?" + key));
				if (num == -1)
					num = url.length;
				var str = url.substring(url.indexOf("?" + key), num);
				url = url.replace(str, "?" + key + "=" + val);
			} else {
				if (url.indexOf("?") == -1)
					url = url.concat("?");
				url = url.concat("&"+key + "=" + val);
			}
			$("#all_region").html(dval);
			addpage(url);
		} else {
			delcondition(f, key);
		}
	}
	function addpage(url) {
		if (!url) {
			if (customUrl) {
				url = customUrl;
			} else {
				url = location.href;
			}
		}
		if (url.charAt(url.length - 1) == "?" || url.charAt(url.length - 1) == "&") {
			url = url.substring(0, url.length - 1);
		}
		if (url.indexOf('p') != -1) {
			url = url.replace(/p[0-9]+/, "p1");
		}
		location.href = url;
	}
	

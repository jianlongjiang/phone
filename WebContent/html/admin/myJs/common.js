//TODO  正式注释
(function() {
    var noop = function noop() {};
    var methods = [
        'assert', 'clear', 'count', 'debug', 'dir', 'dirxml', 'error',
        'exception', 'group', 'groupCollapsed', 'groupEnd', 'info', 'log',
        'markTimeline', 'profile', 'profileEnd', 'table', 'time', 'timeEnd',
        'timeStamp', 'trace', 'warn'
    ];
    var length = methods.length;
    var console = window.console || {};

    while (length--) {
        // Only stub undefined methods.
        console[methods[length]] = console[methods[length]] || noop;
    }
}());


function log(msg){
//	console.log(msg);
}

function save(url, toUrl){
	saveForm("saveForm",url, toUrl);
}


function checkNum(sort){
	  var reg = new RegExp("^[0-9]*$");
	 return reg.test(sort);
}

function saveCheck(key, url, toUrl){
	if(uf.validate()){
		saveForm(key,url,toUrl);
	}
}

//表单保存
$("#save").click(function(){
//	if($('#saveForm').valid()){
		$.ajax({
 		   type: "POST",
 		   url: $('#saveForm').attr('action'),
 		   data: $('#saveForm').serialize(),
 		   success: function(data){
 		     if(data){	    	 
 		    	 bootbox.alert("保存成功");
 		    	setTimeout(function(){
 		    	 if (!!$("#save").attr("urlkey")) {
 		    		//doBack($("#save").attr("urlkey"));
 		    		location.href  = $("#save").attr("urlkey");
 		    	 } else {
 		    		location.href = $('#saveForm').attr('action').replace("save/","list/p1/");
 		    	 }
 		    	 },3);
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
//	}
	
});

function saveForm(key,url, toUrl){
	var queryString = $("#"+key).serialize();
	
	$.ajax({
		type: "POST",
		'url': url,
		data: queryString, 
		dataType: "json",
		success: function( data ){
//			if(data.message !== null && data.message !=""){
//			alert(data.message);
//		}   
//			
			if(data.isSuccess == true){
//				alert("data.result"+data.result);
//				alert(!data.result);
//				!!data.result
//				if(!!data.result){
//					location.href=data.result;
//				}else 
					if(toUrl){
					if("do_refesh"==toUrl || 'refresh'==toUrl){
						location.href=location.href;
					}else if("ajax" ==toUrl){
						
					}else {
						location.href=toUrl;
					}
				}	    
				alert("操作成功！");
			}else {
				alert(data.message);
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}

$(function(){
    $(".toUrl").bind("click",function(){
      var toUrl = $(this).attr("data-toUrl");
      window.location.href = toUrl;
    });
 });

function selectAll(){
	if($("#all").attr("checked")){
		$("table input[type=checkbox]").attr("checked",true);
	}else {
		$("table input[type=checkbox]").attr("checked",false);
	}
}

$(function(){
	$("#doCheck input").bind("click",function(){
		if($(this).attr("checked")){
			$("#doCheck input").attr("checked",false);
			$(this).attr("checked",true);
		}
	});
});

function changeImg(t){
	$(t).attr("src",$(t).attr("src")+new Date());
}

/**
 * form id ="saveForm"
 * @param checkUrl
 * @param url
 * @param toUrl
 */
function yzmSave(checkUrl, url, toUrl){
	var yzm = $("#yzm").val();
	$.ajax({
		type: "POST",
		url: checkUrl,
		data: {"yzm":yzm}, 
		dataType: "json",
		success: function( data ){
//			alert(data.message);
			if(data== true){
				save(url, toUrl);
			}else{
				alert("Verification code error");
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}

function deleteOne(id, url){
	var language = $("#language").val();
	if(confirm("确定删除？")){
		if(language){
			$.ajax({
				type: "GET",
				url: url,
				data: {id : id, language : language}, 
				dataType: "json",
				success: function( data ){
					if(data){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				},
		        error: function() {
		            alert("网络错误，请重试！");
		        }
			});
		}else{
			$.ajax({
				type: "GET",
				url: url,
				data: {id : id}, 
				dataType: "json",
				success: function( data ){
					if(data){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				},
		        error: function() {
		            alert("网络错误，请重试！");
		        }
			});
		}
	}
}

var REGION_AJAX_URL ;
var WEB_ROOT;
$(function(){
	if(!!YS){
		REGION_AJAX_URL = YS.config.path.rootPath+"/common/findregionByParentId";
		WEB_ROOT = YS.config.path.rootPath;
	}
});


$(document).ready(function(){
	$("#state").change(function(){
		var countryId = $("#state").val();
		try{
			$("#countryMobileNo").html(countryMobileMap.get(countryId));
			$("#countryMobileNoInput").val(countryMobileMap.get(countryId));
		}catch(e){}
		$.ajax({
			type: "GET",
			url: REGION_AJAX_URL,
			data: {parentId : countryId}, 
			dataType: "json",
			success: function( data ){
				var html = "<option></option>";
				for(var i=0; i<data.length;i++){
					html = html + "<option value="+data[i].regionName+">"+data[i].regionName+"</option>"
				}
				$("#city").html(html);
				$("#district").html("");
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	$("#city").change(function(){
		var state = $("#city").val();
		$.ajax({
			type: "GET",
			url: REGION_AJAX_URL,
			data: {parentId : state}, 
			dataType: "json",
			success: function( data ){
				var html = "";
				for(var i=0; i<data.length;i++){
					html = html + "<option value="+data[i].regionName+">"+data[i].regionName+"</option>"
				}
				$("#district").html(html);
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
});



$(function(){
	
	$(".upDown").bind("click",function(){
		var  topObject = $(this);
		var  url = $(this).attr("data-url");
		var  status = $(this).attr("data-status");
		var  id = $(this).attr("data-id");
		if(!status){
			status = "0";
		}
		var  doStatus = status==1? 0:1;  
		
		$.ajax({
			type: "post",
			"url": url,
			data: {"sellStatus" : doStatus}, 
			dataType: "json",
			success: function( data ){
//				alert(data.message);
				if(data.isSuccess){
					if(doStatus =="1"){
						$("#do_upDown_"+id).attr("data-status","1");
						$("#upDown_"+id).html("上架");
						$("#do_upDown_"+id).html("下架");
					}else {
						$("#upDown_"+id).html("下架");
						$("#do_upDown_"+id).html("上架");
						$("#do_upDown_"+id).attr("data-status","0");
					}
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	
});



$(function(){
	
	$(".top").bind("click",function(){
		var  topObject = $(this);
		var  url = $(this).attr("data-url");
		var  status = $(this).attr("data-status");
		var  id = $(this).attr("data-id");
		if(!status){
			status = "0";
		}
		var  doStatus = status==1? 0:1;  
		
		$.ajax({
			type: "post",
			"url": url,
			data: {"status" : doStatus}, 
			dataType: "json",
			success: function( data ){
//				alert(data.message);
				if(data.isSuccess){
					if(doStatus =="1"){
						$("#top_"+id).html("置顶");
						$("#do_top_"+id).html("取消");
						$("#do_top_"+id).attr("data-status","1");
					}else {
						$("#top_"+id).html("普通");
						$("#do_top_"+id).html("置顶");
						$("#do_top_"+id).attr("data-status","0");
					}
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	
});





function Map(){
this.container = new Object();
}


Map.prototype.put = function(key, value){
this.container[key] = value;
}


Map.prototype.get = function(key){
return this.container[key];
}


Map.prototype.keySet = function() {
var keyset = new Array();
var count = 0;
for (var key in this.container) {
//跳过object的extend函数
if (key == 'extend') {
continue;
}
keyset[count] = key;
count++;
}
return keyset;
}


Map.prototype.size = function() {
var count = 0;
for (var key in this.container) {
//跳过object的extend函数
if (key == 'extend'){
continue;
}
count++;
}
return count;
}


Map.prototype.remove = function(key) {
delete this.container[key];
}


Map.prototype.toString = function(){
var str = "";
for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
str = str + keys[i] + "=" + this.container[keys[i]] + ";\n";
}
return str;
} 

var countryMobileMap = new Map();
countryMobileMap.put("1","0044");//英国
countryMobileMap.put("62","00351");//葡萄牙
countryMobileMap.put("104","0034");//西班牙
countryMobileMap.put("178","0030");//希腊
countryMobileMap.put("134","0039"); //意大利


(function($,undefined){
    //操作：全选
    $(".J_SelectAll").on("change", function(){
      if ($(this).prop("checked")) {
        $(".J_SelectSub").prop("checked", true);
      } else {
        $(".J_SelectSub").prop("checked", false);
      }
    });
    
    //操作：删除
    $(".J_Table").on("click", ".J_DeleteTr", function() {
      var _this = $(this);
      var url = _this.attr("data-value");
      bootbox.confirm("确认删除", function(result) {
       if(result) {
       	$.ajax({
			type: "GET",
			url: url,
			dataType: "json",
			success: function( data ){
				if(data){
					alert("删除成功！");
					_this.parent().parent().parent().remove();
				}else{
					alert("删除失败！");
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
       }
      });
    });
    
    $(".J_Table").on("click", ".J_UpdateTr", function() {
        var _this = $(this);
        var url = _this.attr("data-value");
        bootbox.confirm("确认为回复成功？", function(result) {
         if(result) {
         	$.ajax({
  			type: "GET",
  			url: url,
  			dataType: "json",
  			success: function( data ){
  				if(data){
  					alert("回复成功！");
  					window.location.reload();
  				}else{
  					alert("回复失败！");
  				}
  			},
  	        error: function() {
  	            alert("网络错误，请重试！");
  	        }
  		});
         }
        });
      }); 
  })(jQuery);

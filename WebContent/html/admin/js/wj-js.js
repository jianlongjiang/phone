
$(function(){
	var data="";
	var url="";
    $.ajax({
    	type: "POST",
    	url: url,
    	data: data,
    	dataType: "json",
    	success: function(data){
    		
    	},
    	erorr: function(){
    		alert("网络错误，请重试！");
    	}
    });
});

function ajaxTem(url,data,toUrl){
	$.ajax({
		type: "POST",
		url: url,
		data: data,
		dataType: "json",
		success: function(data){
			
		},
		erorr: function(){
			alert("网络错误，请调试！");
		}
	});
}
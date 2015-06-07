//
$(function() {
	$(".audit")
			.bind(
					"click",
					function() {
						
						var url = $(this).attr("data-url");
						var status = $(this).attr("data-status");
						var id = $(this).attr("data-id");
						var auditDesc = $("#auditDesc").val();
						// alert("auditDesc="+auditDesc);
						if (!status) {
							status = "0";
						}
						var doStatus = status == 1 ? 0 : 1;

						$
								.ajax({
									type : "post",
									"url" : url,
									data : {
										"isAudit" : doStatus,
										"auditDesc" : auditDesc
									},
									dataType : "json",
									success : function(data) {
										if (data.isSuccess) {
											if (doStatus == "1") {
												$("#audit_" + id).html("已审核");
												$("#do_audit_" + id)
														.html(
																"<i class='icon-stop bigger-120'> 取消审核 </i>");
												$("#do_audit_" + id).attr(
														"data-status", "1");
											} else {
												$("#audit_" + id).html("未审核");
												$("#do_audit_" + id)
														.html(
																"<i class='icon-stop bigger-120'> 通过审核 </i>");
												$("#do_audit_" + id).attr(
														"data-status", "0");
											}
										}
									},
									error : function() {
										alert("网络错误，请重试！");
									}
								});
					});

	// 二级表单保存
	$("#save2").click(function() {
		loadEditorMsg();
		console.log("save222222222222");
		console.log($('#saveForm2').attr('action'));
		console.log($('#saveForm2').valid());
		if ($('#saveForm2').valid()) {
			$.ajax({
				type : "POST",
				url : $('#saveForm2').attr('action'),
				data : $('#saveForm2').serialize(),
				success : function(data) {
					if (data.isSuccess) {
						alert("保存成功");
						location.href = $('#save2').attr('urlkey');
					} else {
						bootbox.alert("保存失败");
					}
				},
				dataType : "json"
			});
		}
	});
	// 加精
	$("#isrec").on("change", function() {
		if ($(this).prop("checked")) {

			$("#isrec").val("1");
			$("#isrec").prop("checked", true);
			console.log($("#isrec").val());
		} else {

			$("#isrec").val("0");
			$("#isrec").prop("checked", false);
			console.log($("#isrec").val());
		}
	});
	// 推荐
	$("#topStatus").on("change", function() {
		if ($(this).prop("checked")) {

			$("#topStatus").val("1");
			$("#topStatus").prop("checked", true);
			console.log($("#topStatus").val());
		} else {

			$("#topStatus").val("0");
			$("#topStatus").prop("checked", false);
			console.log($("#topStatus").val());
		}
	});
	// 推荐图片
	$("#topImage").on("change", function() {
		if ($(this).prop("checked")) {
			$("#topImage").val($("#topImage2").val());
			$("#topImage").prop("checked", true);
			console.log($("#topImage").val());
		} else {

			$("#topImage").val(" ");
			$("#topImage").prop("checked", false);
			console.log($("#topImage").val());
		}
	});
	

});

//冻结
$(function(){
//	console.log("冻结");
	$(".isFreeze").bind("click",function(){
		var  url = $(this).attr("data-url");
		var  status = $(this).attr("data-status");
		var  id = $(this).attr("data-id");
		console.log("url="+url);
		console.log("status="+status);
		console.log("id="+id);
		
		console.log("--------------------------------------------");
		if(!status){
			status = "0";
		}
		var  doStatus = status==1? 0:1;  
		console.log("doStatus="+doStatus);
		$.ajax({
			type: "post",
			"url": url,
			data: {"isFreeze" : doStatus}, 
			dataType: "json",
			success: function( data ){
				if(data.isSuccess){
					if(doStatus =="1"){
						$("#isFreeze_"+id).html("冻结");
						$("#do_isFreeze_"+id).html("<i class='icon-copy bigger-120'>取消</i>");
						$("#do_isFreeze_"+id).attr("data-status","1");
					}else {	
						$("#isFreeze_"+id).html("正常");
						$("#do_isFreeze_"+id).html("<i class='icon-copy bigger-120'>冻结</i>");
						$("#do_isFreeze_"+id).attr("data-status","0");
					}
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	
	$(".isVip").bind("click",function(){
		var  url = $(this).attr("data-url");
		var  status = $(this).attr("data-status");
		var  id = $(this).attr("data-id");
		console.log("url="+url);
		console.log("status="+status);
		console.log("id="+id);
		
		console.log("--------------------------------------------");
		if(!status){
			status = "0";
		}
		var  doStatus = status==1? 0:1;  
		console.log("doStatus="+doStatus);
		$.ajax({
			type: "post",
			"url": url,
			data: {"isVip" : doStatus}, 
			dataType: "json",
			success: function( data ){
				if(data.isSuccess){
					if(doStatus =="1"){
						$("#isVip_"+id).html("金蜗牛");
						$("#do_isVip_"+id).html("<i class='icon-copy bigger-120'>普通</i>");
						$("#do_isVip_"+id).attr("data-status","1");
					}else {	
						$("#isVip_"+id).html("普通");
						$("#do_isVip_"+id).html("<i class='icon-copy bigger-120'>金蜗牛</i>");
						$("#do_isVip_"+id).attr("data-status","0");
					}
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	
});


//删除未选中类别
$(function(){
	
	$(".category").bind("click",function(){
		var  url = $(this).attr("category-url");
		var  toUrl = $(this).attr("category-toUrl");
		var  id =  $(this).attr("category-id");
		$.ajax({
			type: "post",
			"url": url,
			data: $('#cateForm_'+id).serialize() , 
			dataType: "json",
			success: function( data ){
				if(data.isSuccess){
					alert("删除成功");	
					window.location.href=toUrl;
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
	
});

function saveForm_wj(key,url, toUrl){
	var queryString = $("#"+key).serialize();
	
	$.ajax({
		type: "POST",
		'url': url,
		data: queryString, 
		dataType: "json",
		success: function( data ){
			if(data.isSuccess == true){
				alert("操作成功！");
			     if(toUrl){ 
					if("do_refesh"==toUrl || 'refresh'==toUrl){
						location.href=location.href;
					}else if("ajax"==toUrl){
						
					}else {
						location.href=toUrl;
					}
				}	    
			}else {
				alert(data.message);
			}
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
}

//打款管理
$(function(){
	//提款
	$(".tiKuan").click(function() {
		 var _this = $(this);
	     var url = _this.attr("data-value");
	     bootbox.confirm("确定要提款吗？", function(result) {
	    	 if(result) {
	    		 $.ajax({
	    			 type: "GET",
			  			url: url,
			  			dataType: "json",
			  			success: function( data ){
			  				if(data){
			  					alert("操作成功。");  					
			  					window.location.reload();
			  				}else{
			  					alert("失败！");
			  				}
			  			},
			  	        error: function() {
			  	            alert("网络错误，请重试！");
			  	        }
	    		 });
	    	 }
	     });
	 });
	
	

});

$(function(){
	//成功
	$(".cashSuc").click(function() {
		 var _this = $(this);
	     var url = _this.attr("data-value");
	     bootbox.confirm("确认成功操作吗？", function(result) {
	    	 if(result) {
	    		 $.ajax({
	    			 type: "GET",
			  			url: url,
			  			dataType: "json",
			  			success: function( data ){
			  				if(data){
			  					alert("操作成功。");  					
			  					window.location.reload();
			  				}else{
			  					alert("失败！");
			  				}
			  			},
			  	        error: function() {
			  	            alert("网络错误，请重试！");
			  	        }
	    		 });
	    	 }
	     });
	 });
});

$(function(){
	//失败
	$(".cashFail").click(function() {
		 var _this = $(this);
	     var url = _this.attr("data-value");
	     bootbox.confirm("确认失败操作吗？", function(result) {
	    	 if(result) {
	    		 $.ajax({
	    			 type: "GET",
			  			url: url,
			  			dataType: "json",
			  			success: function( data ){
			  				if(data){
			  					alert("操作成功。");  					
			  					window.location.reload();
			  				}else{
			  					alert("失败！");
			  				}
			  			},
			  	        error: function() {
			  	            alert("网络错误，请重试！");
			  	        }
	    		 });
	    	 }
	     });
	 });
});

$(function(){
    $(".disuse").bind("click",function(){
		
		var  url = $(this).attr("data-url");
		var  status = $(this).attr("data-status");
		var  id = $(this).attr("data-id");
		console.log(url+"-----"+status+"-----"+id);
		if(!status){
			status = "n";
		}
		var  doStatus = status=="y"? "n":"y";  
		
		$.ajax({
			type: "post",
			url: url,
			data: {"more1" : doStatus}, 
			dataType: "json",
			success: function( data ){
				if(data.isSuccess){
					if(doStatus =="y"){
						$("#disuse_"+id).html("正常");
						$("#do_disuse_"+id).html("<i class='icon-copy bigger-120'>停用</i>");
						$("#do_disuse_"+id).attr("data-status","y");
					}else {
						$("#disuse_"+id).html("停用");
						$("#do_disuse_"+id).html("<i class='icon-copy bigger-120'>恢复</i>");
						$("#do_disuse_"+id).attr("data-status","n");
					}
				}
			},
	        error: function() {
	            alert("网络错误，请重试！");
	        }
		});
	});
	
});
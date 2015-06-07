//弹框
function addCustom(t) {
      var _id = t.parent().find("a").attr('href');
      var _src = t.parent().find("a").attr('data-src');
      $(_id+' .modal-body').find("iframe").remove();
      $('<iframe src="'+_src+'" border="0" width=100% height="100%">').appendTo($(_id+' .modal-body'));
      t.parent().find("a").click();
}
function closePop(use_name){
  	$("#form-field-5").val(use_name);
  	$("#modal-table .table-header").find('button').trigger("click");
}

function deleteRecord(recordId){
	$("#record-"+recordId).remove();
	$.ajax({
		url:YFT.config.path.rootPath+'/fin/collect/delete/record/'+recordId,
		success: function(data) {
		}
	});
}

$(function(){
	//弹框
	$(".J_InputSelect").focus(function(){
		  addCustom($(this));
		});
	$(".J_InputSelect").next().click(function(){
	  addCustom($(this).prev());
	});
	
	//新建
	$("#newFin").click(function(){
		location.href = $(this).attr("addUrl");
	});
	
	//时间区间
	$(".applyBtn").click(function(){
		$("input[name='beforeFnTime']").val($("input[name='daterangepicker_start']").val());
		$("input[name='afterFnTime']").val($("input[name='daterangepicker_end']").val());
	});
	$(".emptyBtn").click(function(){
		$("input[name='beforeFnTime']").val("");
		$("input[name='afterFnTime']").val("");
	});
	//修改
	$("#editFin").click(function(){
		location.href = $(this).attr("editUrl");
	});
	//返回
	$("#returnList").click(function(){
		location.href = $(this).attr("returnUrl");
	});
});

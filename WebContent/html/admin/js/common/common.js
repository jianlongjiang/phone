var AJAX_REGION_URL = LH.config.path.rootPath + "/admin/mobilecate/jsonCate";

$("#state").on('change' ,function(){
	var id = $(this).val();
	var city = $("#city");
	var selectDefault = "<option value=\"\">&nbsp;</option>";
	var opts = selectDefault;
	if (id == '') return;
	$.ajax({
		type: "GET",
		url: AJAX_REGION_URL,
		data: "parentCateId=" + id, 
		dataType: "json",
		success: function( data ){
			$.each(data, function(i,item) {
				opts += '<option value='+item.id+'>'+item.cateName+'</option>';
			});
			city.html(opts);
			city.get(0).selectedIndex=0;
		},
        error: function() {
            alert("网络错误，请重试！");
        }
	});
	
});

//input 弹框
function addCustom(t) {
	  var title = t.data("title");
      var _id = t.parent().find("a").attr('href');
      var _src = t.parent().find("a").attr('data-src');
      $(_id+' .modal-body').find("iframe").remove();
      $('<iframe src="'+_src+'" border="0" width=100% height="100%">').appendTo($(_id+' .modal-body'));
      t.parent().find("a").click();
      if(title){
    	  $(_id+' .table-header i').html(title);
      }
}

$(".J_InputSelect").focus(function(){
  addCustom($(this));
});
$(".J_InputSelect").next().click(function(){
  addCustom($(this).prev());
});


//修改、删除
function alldo(task1, task2, id, isAjax, showText){
	var url = window.location.pathname;
	var gotourl;
	if(task1){
		gotourl = url.replace(task1, task2);
	}else{
		gotourl = url + "/" + task2;
	}
	if(id){
		gotourl = gotourl + "/" + id;
	}
	gotourl = gotourl.replace("//", "/");
	if(isAjax){
		bootbox.confirm(showText, function(result) {
           if(result) {
        	   $.ajax({
        		   type: "GET",
        		   url: gotourl,
        		   success: function(data){
        		     if(data){
        		     	 location.href=window.location.href;
        		    	 return false;
        		     }
        		   },
        		   dataType: "json"
        		});
           }
          });
	}else{
		location.href=gotourl;
		return false;
	}
}


var defaultrule = {
		errorElement: 'div',
		errorClass: 'qfc-error',

		focusInvalid: false,

		success: function (e) {
			$(e).closest('.row').removeClass('has-error').addClass('has-info');
			$(e).remove();
		},
		submitHandler: function (form) {
		},
		invalidHandler: function (form) {
		},		
	   /* 错误信息的显示位置 */   
	   errorPlacement: function(error, element) {
		   error.insertAfter(element.parent());
	   },
		
};
$.validator.setDefaults(defaultrule);

function  loadEditorMsg(){
	
	console.error("获取在线编辑器的 类容, 没有 也重写改方法");
}
////表单保存
//$("#save").click(function(){
//	loadEditorMsg();
//	if($('#saveForm').valid()){
//		$.ajax({
// 		   type: "POST",
// 		   url: $('#saveForm').attr('action'),
// 		   data: $('#saveForm').serialize(),
// 		   success: function(data){
// 		     if(data.isSuccess){
// 		    	 alert("保存成功");
// 		    	 location.href = $('#save').attr('urlkey');
// 		     }else{
// 		    	bootbox.alert("保存失败");
// 		     }
// 		   },
// 		   dataType: "json"
// 		});
//	}
//	
//});

//表格删除行
//$(".J_Table").on("click", ".J_DeleteTr", function() {
//  var _this = $(this);
//  bootbox.confirm("确定要删除这条数据吗？", function(result) {
//    if(result) {
//      var deletesrc = _this.attr("data-src");
//      var tr_num = _this.attr("tr_num");
//      if(deletesrc){
//    	  $.ajax({
//	  		   type: "GET",
//	  		   url: deletesrc,
//	  		   success: function(data){
//	  		     if(data.isSuccess){
//	  		    	 bootbox.alert("已经删除");
//	  		    	if(tr_num==2){
//	  		    		_this.parent().parent().parent().next().remove();
//	  		    	}
//	  		    	_this.parent().parent().parent().remove();
//	  		     }else{
//	  		    	bootbox.alert("删除失败");
//	  		     }
//	  		   },
//	  		   dataType: "json"
//	  		});
//      }else{
//    	  _this.parent().parent().parent().remove();
//      }
//    }
//  });
//});

//表格添加行
$(".qfc-row-form-ft a").on('click', function() {
  var _tr = $(this).parent().prev("table").find("tr").last();
  _tr.after('<tr>'+_tr.html()+'</tr>');
});

function audit(idName,id){
	if($('#Form').valid()){
		$.ajax({
 		   type: "POST",
 		   url: $('#Form').attr('action'),
 		   data: $('#Form').serialize(),
 		   success: function(data){
 		     if(data){
 		    	bootbox.dialog({
 		    		  message: "审核?",
 		    		  title: "审核",
 		    		  buttons: {
 		    		    success: {
 		    		      label: "审核通过",
 		    		      className: "btn-success",
 		    		      callback: function() {
 		    		    	 $.ajax({
 		    		  		   type: "GET",
 		    		  		   url: $('#Form').attr('action').replace("save/","auditpass/"),
 		    		  		   data: idName+"="+id,
 		    		  		   success: function(data){
 		    		  		     if(data){
 		    		  		    	 bootbox.alert("审核已通过");
 		    		  		    	 location.href = $('#Form').attr('action').replace("save/","");
 		    		  		     }else{
 		    		  		    	bootbox.alert("审核失败");
 		    		  		     }
 		    		  		   },
 		    		  		   dataType: "json"
 		    		  		});
 		    		      }
 		    		    },
 		    		    danger: {
 		    		      label: "审核驳回",
 		    		      className: "btn-danger",
 		    		      callback: function() {
 		    		    	 $.ajax({
 	 		    		  		   type: "GET",
 	 		    		  		   url: $('#Form').attr('action').replace("save/","auditrejected/"),
 	 		    		  		   data: idName+"="+id,
 	 		    		  		   success: function(data){
 	 		    		  		     if(data){
 	 		    		  		    	 bootbox.alert("审核已驳回");
 	 		    		  		    	 location.href = $('#Form').attr('action').replace("save/","");
 	 		    		  		     }else{
 	 		    		  		    	bootbox.alert("审核失败");
 	 		    		  		     }
 	 		    		  		   },
 	 		    		  		   dataType: "json"
 	 		    		  		});
 		    		      }
 		    		    }
 		    		  }
 		    		});
 		    	 return false;
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
		return false;
	}
}



/**
 * 
 * detail页返回到list页。 list页有需要查询条件，因此，从详细页调转到列表页就有很多种情况。
 * 为此，追加cookie记录，记录当前的list页，同时要求当前list页必须是get， 详细页跳转的时候，读取cookie值，跳转到特定的list页
 * 
 * 如下对象定义，仅仅是一个默认值
 */
var urlMap = {
	// 指定需要put的list
	key : {
		buy : "/order/buy"
	},
    value:{
    	buy : "/order/buy"
    	
    }
};


/**
 * get list页url
 * 
 * @param {}
 *            code
 */
function doBack(code) {
	var url = urlMap['value'][code];
	if (url) {
		window.location.href = url;
	} else {
		window.location.href = YFT.config.path.rootPath + "/index.htm";
	}
}

(function($,undefined){
  //操作：全选
  $(".J_SelectAll").on("change", function(){
    if ($(this).prop("checked")) {
      $(".J_SelectSub").prop("checked", true);
    } else {
      $(".J_SelectSub").prop("checked", false);
    }
  });
  
  
   //金额限制
   $(document).on('keyup', '.J_Price', function() {
      if (!(/^([1-9][0-9]*|0)(\.[0-9]*)?$/.test(this.value))) {
          this.value = this.value ? this.value.substr(0,(this.value.length-1)) : "";
      } 
  });
	 
  
})(jQuery);


	



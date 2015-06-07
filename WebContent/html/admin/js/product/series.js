$(function(){
    var pStr = '<li class="dd-item" data-id="1">\
    				<button data-action="collapse" type="button" class="J_collapse" style="display:none">Collapse</button>\
    				<button data-action="expand" type="button" class="J_Expand" style="display:none">Expand</button>\
    				<div class="dd-handle">\
    					<input type="hidden" name="productSeriesId">\
		        		<input type="hidden" name="productSeriesOrder" value="0">\
    					<input type="hidden" name="parentSid">\
    					<input type="hidden" name="isParent" value="true">\
    					<input class="input-middle" type="text" name="productSeriesName">\
    					<a class="delete J_delete pull-right" href="javascript:;">删除</a>\
    					<a class="J_iup iup pull-right icon-arrow-up red" href="javascript:;"></a>\
    					<a class="J_idown idown pull-right icon-arrow-down orange" href="javascript:;"></a>\
    					<a class="pull-right default J_saveFather" href="javascript:;">保存</a>\
    				</div>\
    				<ol class="dd-list"></ol>\
    			</li>';

    var sStr = '<li class="dd-item" data-id="2">\
              <div class="dd-handle">\
              	<input type="hidden" name="productSeriesId">\
        		<input type="hidden" name="productSeriesOrder" value="0">\
        		<input type="hidden" name="parentSid">\
        		<input type="hidden" name="isParent" value="false">\
                <input class="input-middle" type="text" name="productSeriesName">\
                <a class="delete J_delete pull-right" href="javascript:;">删除</a><a class="iup J_iupSub pull-right icon-arrow-up" href="javascript:;"></a><a class="idown J_idownSub pull-right icon-arrow-down" href="javascript:;"></a>\
              </div>\
            </li>';

    $("#J_cateBox").on('click','.J_addPStr',function(){
      $(this).parent().next('.dd-list').show();
      $(this).parent().next('.dd-list').append(sStr);
      var fatherId = $(this).siblings("input[name='productSeriesId']").val();
      var obj = $(this).parent().next().children().last();
      if(obj.prev().length){
    	  obj.find("input[name='productSeriesOrder']").val(parseInt(obj.prev().find("input[name='productSeriesOrder']").val())+1);
      }
      obj.find("input[name='parentSid']").val(fatherId);
      $(this).parent().parent().find('.J_collapse').show();
    });
    $("#J_cateBox").on('click', '.J_collapse', function(){
      $(this).hide().next(".J_Expand").show().parent().find(".dd-list").hide();
    });
    $("#J_cateBox").on('click', '.J_Expand', function(){
      $(this).hide().prev(".J_collapse").show().parent().find(".dd-list").show();
    });
    $(".J_addSeries").click(function(){
      $("#J_cateBox").append(pStr);
      var obj = $("#J_cateBox").children().last();
      if(obj.prev().length){
    	  obj.find("input[name='productSeriesOrder']").val(parseInt(obj.prev().find("input[name='productSeriesOrder']").val())+1);
      }
    });
    $("#J_cateBox").on("click", '.J_delete',function(){
    	var obj = $(this);
		$.ajax({
			url:YFT.config.path.rootPath+'/product/series/delete',
			data:{"seriesIds":$(this).siblings("input[name='productSeriesId']").val()}
	    });
		$(obj).parent().parent().remove();
    });
    $("#J_cateBox").on("click", '.J_iupSub', function(){
      var a = $(this).parent().parent();
      var b = a.prev();
      var aSort = $(a).find("input[name='productSeriesOrder']").val();
      $(a).find("input[name='productSeriesOrder']").val($(b).find("input[name='productSeriesOrder']").val());
      $(b).find("input[name='productSeriesOrder']").val(aSort);
      a.insertBefore(b);
    });
    $("#J_cateBox").on("click", '.J_idownSub', function(){
      var a = $(this).parent().parent();
      var b = a.next();
      var aSort = $(a).find("input[name='productSeriesOrder']").val();
      $(a).find("input[name='productSeriesOrder']").val($(b).find("input[name='productSeriesOrder']").val());
      $(b).find("input[name='productSeriesOrder']").val(aSort);
      a.insertAfter(b);
    });
    $("#J_cateBox").on("click", '.J_iup', function(){
      var a = $(this).parent().parent();
      var b = a.prev();
      var aSort = $(a).find("input[name='productSeriesOrder']").val();
      $(a).find("input[name='productSeriesOrder']").val($(b).find("input[name='productSeriesOrder']").val());
      $(b).find("input[name='productSeriesOrder']").val(aSort);
      a.insertBefore(b);
    });
    $("#J_cateBox").on("click", '.J_idown', function(){
      var a = $(this).parent().parent();
      var b = a.next();
      var aSort = $(a).find("input[name='productSeriesOrder']").val();
      $(a).find("input[name='productSeriesOrder']").val($(b).find("input[name='productSeriesOrder']").val());
      $(b).find("input[name='productSeriesOrder']").val(aSort);
      a.insertAfter(b);
    });
    $("#J_cateBox").on('click','.J_saveFather',function(){
    	var obj = $(this);
    	$.ajax({
    		url:YFT.config.path.rootPath+'/product/series/save',
    		type:"post",
			data:{"isParent":true,"productSeriesOrder":$(this).siblings("input[name='productSeriesOrder']").val(),"productSeriesName":$(this).siblings("input[name='productSeriesName']").val()},
            success: function(data) {
            	$(obj).siblings("input[name='productSeriesId']").val(data);
            	$(obj).removeClass("J_saveFather");
            	$(obj).addClass("J_addPStr");
            	$(obj).html("添加子类");
            }
        });
    });
  });
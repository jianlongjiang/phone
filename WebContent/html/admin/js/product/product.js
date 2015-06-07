$(function(){
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
      bootbox.confirm("确认删除", function(result) {
       if(result) {
         _this.parent().parent().parent().remove();
       }
      });
    });
      
    //是否可销售
    $("input[name='sellStatusSelect']").click(function(){
    	$("input[name='sellStatus']").val($(this).val());
    	if($(this).val() && "true" == $(this).val()){
    		$("#isOnSale").show();
    	}else{
    		$("#isOnSale").hide();
    	}
    });
    //是否可交易
    $("input[name='tradeStatusSelect']").click(function(){
    	$("input[name='tradeStatus']").val($(this).val());
    });
    
    //产品新增
    $("#newProduct").click(function(){
    	location.href = YFT.config.path.rootPath+"/product/add";
    });
    
    //编辑返回
    $("#editReturn").click(function(){
    	location.href = YFT.config.path.rootPath+"/product";
    });
    
    //产品系列
    $("#J_SeriesLevel1 li").click(function(){
		if ($("#J_SeriesLevel2 ul").length) {
			$("#J_SeriesLevel2").addClass("none").html('');
		}
		getChildrenSeries($(this).attr("series-id"));
    });
    
    $("#J_SeriesLevel2").on('click', 'a', function(){
	    	$(this).parent().siblings().removeClass("selected");
	    	$(this).parent().addClass("selected");
    });
    
    $("#seriesConfirm").click(function(){
    	var obj = $(".cate-box li").filter(".selected");
    	$("#form-field-6").val($(obj).children().html());
    	$("input[name='productSeries']").val($(obj).attr("series-id"));
    });
    
    //报价方式
    $("input[name='priceType']").click(function(){
    	$("div[id^='priceType']").hide();
    	$("#priceType"+$(this).val()).show();
    });
});

//产品修改
function editProduct(productId){
	location.href = YFT.config.path.rootPath+"/product/input/"+productId;
}

//子系列
function getChildrenSeries(seriesId){
	$.ajax({
		url:YFT.config.path.rootPath+'/product/postChildrenSeries',
		data:{"parentId":seriesId},
		success: function(data) {
			if (0 < data.length) {
				html = '<ul>';
	            for (var i = 0; i< data.length; i++) {
	              html += '<li series-id="'+data[i].productSeriesId+'"><a href="javascript:;">' + data[i].productSeriesName + '</a></li>';
	            }
	            html += '</ul>';
	            $("#J_SeriesLevel2").append(html).removeClass('none');
			}
		}
	});
}
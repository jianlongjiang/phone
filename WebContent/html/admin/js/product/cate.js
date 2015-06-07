$(function(){
	//所属分类弹框
    $(".qfcInputSelect").focus(function(){
    	$(this).parent().find("a").click();
    });
    $(".qfcInputSelect").next().click(function(){
        $(this).parent().find("a").click();
    });
    
    $("#J_CateLevel1 li").click(function(){
		if ($("#J_CateLevel2 ul").length) {
			$("#J_CateLevel2").addClass("none").html('');
			$("#J_CateLevel3").addClass("none").html('');
		}
		getChildrenCate($(this).attr("cate-id"), "J_CateLevel2");
    });
    
    $("#J_CateLevel2").on('click', 'a', function(){
	    	if ($("#J_CateLevel3 ul").length) {
	    	    $("#J_CateLevel3").addClass("none").html('');
	    	}
	    	$(this).parent().siblings().removeClass("selected");
	    	$(this).parent().addClass("selected");
	    	getChildrenCate($(this).parent().attr("cate-id"), "J_CateLevel3");
    });
    
    $("#J_CateLevel3").on('click', 'a', function(){
    	$("#J_CateLevel2 li").removeClass("selected");
    	$(this).parent().siblings().removeClass("selected");
    	$(this).parent().addClass("selected");
    });
    
    $("#cateConfirm").click(function(){
    	var obj = $(".cate-box li").filter(".selected");
    	$("#form-field-4").val($(obj).children().html());
    	$("input[name='refCateIds']").val($(obj).attr("cate-id"));
    	$("input[name='refCateCodes']").val($(obj).attr("cate-code"));
    });
});

function getChildrenCate(cateId, fatherId) {
	$.ajax({
		url:YFT.config.path.rootPath+'/product/postCate',
		data:{"cateId":cateId},
		success: function(data) {
			if (0 < data.length) {
				html = '<ul>';
	            for (var i = 0; i< data.length; i++) {
	              html += '<li cate-id="'+data[i].cateId+'" cate-code="'+data[i].cateCode+'"><a href="javascript:;">' + data[i].cateName + '</a></li>';
	            }
	            html += '</ul>';
	            $("#"+fatherId).append(html).removeClass('none');
			}
		}
	});
}
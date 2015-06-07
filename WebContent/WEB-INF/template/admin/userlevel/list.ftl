<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <i class="icon-home home-icon"></i>
            <a href="#">经验规则设置</a>
            <small>
                <i class="icon-double-angle-right"></i>
                等级设置
            </small>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="page-header">
        </div><!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="tabbable ">
                        <div class="tab-content">
                            <div class="table-responsive">
                                <div class="dataTables_wrapper">
                                    <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
                                        <thead>
                                        <tr>
                                            <th>称号</th>
                                            <th>达到经验值</th>
                                            <th>优先级</th>
                                            <th>图标</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if infos?has_content>
	                                    <#list infos as info>
	                                    <form id="saveForm${info_index}" action="${request.contextPath}/admin/userlevel/save/">
	                                    <input type="hidden" value="${info.id}" name="id"/>
                                        <tr>
                                            <td>
                                                <input class="input-large" name="levelName" value="${info.levelName}" type="text" value="称号">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="experience" value="${info.experience}" type="text" value="经验值">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="downloadLevel" value="${info.downloadLevel}" type="text" value="优先级">
                                            </td>
                                            <td>
                                            	<input type="hidden"  name="image" id="imageLogo_${info_index}" value='${(info.image)!""}' />
                                                <input type="file" name="file_upload" id="file_upload_${info_index}" style="display: block;float: left;margin-top: 20px;"/>
                                    			<img id="first_img_${info_index}" style="width:100px;height:100px;" src="<#if info?has_content && info.image?has_content>${request.contextPath}/resources/admin/store/${info.image}</#if>"/>
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <a class="btn btn-xs btn-success"  onclick="saveOne('${info_index}', this);">
                                                        <i class="icon-stop bigger-120"> 保存 </i>
                                                    </a>
                                                    <a class="btn btn-xs btn-danger J_DeleteTr" data-src="${request.contextPath}/admin/userlevel/delete/${info.id}">
                                                        <i class="icon-stop bigger-120"> 删除 </i>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                        </form>
	                                    </#list>
	                                    </#if>
	                                    <form id="saveForm" action="${request.contextPath}/admin/userlevel/save/">
                                        <tr>
                                            <td>
                                                <input class="input-large" name="levelName" type="text">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="experience" type="text">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="downloadLevel" type="text">
                                            </td>
                                            <td>
                                            	<input type="hidden"  name="image" id="imageLogo_s" value='${(info.image)!""}' />
                                            	<input type="file" name="file_upload" id="file_upload_s" style="display: block;float: left;margin-top: 20px;"/>
                                    			<img id="first_img_s" style="width:100px;height:100px;" src="<#if info?has_content && info.image?has_content>${request.contextPath}/resources/admin/store/${info.image}</#if>"/>
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <a id="save" urlkey="${request.contextPath}/admin/userlevel/list/p1/" class="btn btn-xs btn-success">
                                                    <i class="icon-stop bigger-120"> 添加等级 </i>
                                                </a>
                                            </div>
                                            </td>
                                        </tr>
                                        </form>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xs-6">
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="dataTables_paginate paging_bootstrap">
                                                <ul class="pagination">
                                                    <#if page?has_content>
													<#assign pageUrl>
														<@lh.searchPageUrl request.requestUri 'pageNo=#' request.queryString/>
													</#assign>
													<#include "../common/pages.ftl">
													</#if>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.table-responsive -->
                        </div>
                    </div>
                </div><!-- /.row -->
                <div class="hr hr32 hr-dotted"></div>
                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
        <div class="page-footer">
        </div><!-- /.page-footer -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/message.js"></script>
<script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js" type="text/javascript"></script>
<script>
$(function() {
   setTimeout(function() {
   <#if infos?has_content>
   <#list infos as info>
     
     $('#file_upload_${info_index}').uploadify({
       'auto': true,
       buttonText: "上传图片",
       formData: {
         "a": "a",
         "b": "b"
       },
       'fileTypeExts': '*.gif;*.jpg;*.png;*.bmp;*.jpeg',
       'fileSizeLimit':  '2048KB',
       'swf': '${request.contextPath}/resources/admin/flash/uploadify.swf', // 选择文件flash文件
       'uploader': '${request.contextPath}/uploadify/upload', // 处理上传的Servlet
       'onUploadSuccess': function(event, data, fileObj) { // 当文件上传完成时触发
         if (fileObj) {
           // 获取具体的图片位置 fileId
           var param = $(this)[0].button[0].parentNode.id;
           var index = param.substring(param.length - 1);
         }
         $("#first_img_${info_index}").attr("src", "${request.contextPath}/resources/admin/store/" + data);
         $("#imageLogo_${info_index}").val(data);
       }
     });
     </#list>
     </#if>
     
     $('#file_upload_s').uploadify({
       'auto': true,
       buttonText: "上传图片",
       formData: {
         "a": "a",
         "b": "b"
       },
       'fileTypeExts': '*.gif;*.jpg;*.png;*.bmp;*.jpeg',
       'fileSizeLimit':  '2048KB',
       'swf': '${request.contextPath}/resources/admin/flash/uploadify.swf', // 选择文件flash文件
       'uploader': '${request.contextPath}/uploadify/upload', // 处理上传的Servlet
       'onUploadSuccess': function(event, data, fileObj) { // 当文件上传完成时触发
         if (fileObj) {
           // 获取具体的图片位置 fileId
           var param = $(this)[0].button[0].parentNode.id;
           var index = param.substring(param.length - 1);
         }
         $("#first_img_s").attr("src", "${request.contextPath}/resources/admin/store/" + data);
         $("#imageLogo_s").val(data);
       }
     });
   }, 10);
 });

function saveOne(index, event){
	var _this = $(event);
	if($('#saveForm'+index).valid()){
		$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/userlevel/save/",
 		   data: $('#saveForm'+index).serialize(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 location.href = "${request.contextPath}/admin/userlevel/list/p1/";
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
	}
}

$(function(){
	$("#saveForm").validate({
		/* 设置验证规则 */	 
		rules: {
			levelName: {
				required: true
			},
			experience: {
				required: true,
				number:true,
			},
			downloadLevel: {
				required: true,
				number:true,
			}	
		},
		/* 设置错误信息 */	 
		messages: {
			levelName: {
				required: '称号不能为空'
			},
			experience: {
				required: '经验不能为空',
				number: '请填写正确的经验值'
			},
			downloadLevel: {
				required: '优先级不能为空',
				number: '请填写正确的优先级'
			}
		},
		/* 错误信息的显示位置 */	 
		errorPlacement: function(error, element) {
			element.parent().parent().addClass("has-error");
			var errorPlace = element.parent().nextAll("div").first();
			errorPlace.html(error);
		},	 
		/* 验证通过时的处理 */	 
		success: function(lable) {
			lable.parent().parent().removeClass("has-error");
			lable.remove();
		},
		onkeyup: false
	});
});
</script>
</#escape>
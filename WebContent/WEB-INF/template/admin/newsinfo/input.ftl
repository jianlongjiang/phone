<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">新闻列表</a>
                <small>
                    <i class="icon-double-angle-right"></i>
                      编辑、添加
                </small>
            </li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="page-header">

        </div><!-- /.page-header -->

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="saveForm" action="">
                        <input name="id" value="${(info.id)}" type="hidden">
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 标题：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="title" value="${(info.title)!''}" class="col-xs-12 col-sm-12">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 作者：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="author" value="${(info.author)!''}" class="col-xs-12 col-sm-12">
                                </div>
                            </div>

                            <div class="space-4"></div>
                            
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 时间：</label>
                                <div class="col-sm-4">
                                    <input class="col-xs-12 col-sm-12 Wdate" type="text" name="updateTime" value="<#if info?? && info.updateTime??>${info.updateTime?string('yyyy-MM-dd HH:mm:ss')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-01 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})" >
                                </div>
                            </div>

                            <div class="space-4"></div>
                            
                            <div class="form-group">
                                <input type="hidden"  name="image" id="imageLogo" value='${(info.image)!""}' />
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 图片：</label>
                                <div class="col-sm-4">
                                    <input type="file" name="file_upload" id="file_upload_1" style="display: block;float: left;margin-top: 20px;"/>
                                    <img id="first_img" style="width:100px;height:100px;" src="<#if info?has_content && info.image?has_content>${request.contextPath}/resources/admin/store/${info.image}</#if>"/>
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 摘要：</label>
                                <div class="col-sm-8">
                                    <textarea style="width: 100%;height: 100px;" id="summary" name="summary">${(info.summary)!''}</textarea>
                                </div>
                            </div>

                            <div class="space-4"></div>
                            
                            
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 内容：</label>
                                <input type="hidden" name="newsDesc" id="newsDesc" value='${(info.newsDesc)!""}' />
                                <div class="col-sm-8">
                                	<script id="editor" type="text/plain" style="width:100%;height:300px;"></script>
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 分类：</label>
                                <div class="col-sm-4">
                                    <select  name="cateId" class="col-xs-12 col-sm-12">
                                        <#if allcates?has_content>
                                    	<#list allcates as c >
                                        <option value="${c.id}" <#if c.id?? && info?? && c.id = info.cateId >selected</#if> >${(c.cateName)!''}</option>
                                        </#list>
                                        </#if>
                                    </select>
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 点赞数：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="pointGoodCount" value="${(info.pointGoodCount)!''}" class="col-xs-12 col-sm-12 J_Price">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 点差数：</label>
                                <div class="col-sm-4">
                                    <input type="text" name="pointBadCount" value="${(info.pointBadCount)!''}" class="col-xs-12 col-sm-12 J_Price">
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 选择：</label>
                                <div class="col-sm-4">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="ace" <#if info?has_content && info.topStatus?has_content && info.topStatus==true>checked</#if> name="topStatus" id="topStatus" value="<#if info?has_content && info.topStatus?has_content && info.topStatus==true>1<#else>0</#if>" >
                                            <span class="lbl"> 首页新闻栏推荐</span>
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="ace" <#if info?has_content && info.isrec?has_content && info.isrec==true>checked</#if> name="isrec" id="isrec" value="<#if info?has_content && info.isrec?has_content && info.isrec==true>1<#else>0</#if>" >
                                            <span class="lbl"> 加精</span>
                                        </label>
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" class="ace" <#if info?? && info.topImage?? && info.topImage!="">checked</#if> name="topImage" id="topImage" value="${(info.topImage)!''}">
                                            <span class="lbl"> 首页顶端推荐  推荐图片尺寸：100×100</span> 
                                        </label>     
                                        <label>
                                            <input type="checkbox" class="ace" <#if info?? && info.more1?? && info.more1!="">checked</#if> name="more1" id="more1" value="${(info.more1)!''}">
                                            <span class="lbl"> 新闻顶端推荐  推荐图片尺寸：100×100</span> 
                                        </label> 
                                        <input type="file" name="file_upload" id="file_upload_2" style="display: block;float: left;margin-top: 20px;"/>
                                        <img id="second_img" style="width:100px;height:100px;" src="<#if info?has_content && info.topImage?has_content>${request.contextPath}/resources/admin/store/${info.topImage}</#if>"/>
                                    </div>    
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group">
                                <label class="col-sm-2 control-label"> 排序：</label>
                                <div class="col-sm-2">
                                    <input type="text" name="orderBy" value="${(info.orderBy)!''}" class="col-xs-12 col-sm-12">
                                </div>
                                <div class="help-block col-xs-12 col-sm-reset inline">
                                    值越小，排序越靠前（1-99）
                                </div>
                            </div>

                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button" onclick="updateMsg('${request.contextPath}/admin/newsinfo/save/','${request.contextPath}/admin/newsinfo/list/p1')">
                                        <i class="icon-ok bigger-110"></i>
                                        提交
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div><!-- /.row -->

                <div class="hr hr32 hr-dotted"></div>

                <!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<script type="text/javascript" charset="utf-8" src="${request.contextPath}/resources/admin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${request.contextPath}/resources/admin/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${request.contextPath}/resources/admin/js/uediter.js"> </script>
<script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/message.js"></script>
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
<script>
$(function() {
   setTimeout(function() {
     $('#file_upload_1').uploadify({
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
         $("#first_img").attr("src", "${request.contextPath}/resources/admin/store/" + data);
         $("#imageLogo").val(data);
       }
     });
   }, 10);
   
   setTimeout(function() {
     $('#file_upload_2').uploadify({
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
         $("#second_img").attr("src", "${request.contextPath}/resources/admin/store/" + data);
         $("#topImage").val(data);
         $("#more1").val(data);
       }
     });
   }, 10);
   
   
 });
 
 $(function(){
	$("#saveForm").validate({
		/* 设置验证规则 */	 
		rules: {
			title: {
				required: true
			},
			author: {
				required: true
			},
			pointGoodCount: {
				number:true,
			},
			pointBadCount: {
				number:true,
			},
			orderBy: {
				number:true,
			}		
		},
		/* 设置错误信息 */	 
		messages: {
			title: {
				required: '标题不能为空'
			},
			author: {
				required: '作者不能为空',
			},
			pointGoodCount: {
				number: '请填写正确的点赞数'
			},
			pointBadCount: {
				number: '请填写正确的点差数'
			},
			orderBy: {
				number: '请填写正确的排序值'
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

function  loadEditorMsg(){
 	getContent();
}
 
 function setContent(isAppendTo) {
 	<#if info?has_content && info.newsDesc?has_content>
     	UE.getEditor('editor').setContent("${info.newsDesc}", isAppendTo);
 	</#if>
 	}
 function getContent() {
   $("#newsDesc").attr("value", UE.getEditor('editor').getContent());
   //alert(UE.getEditor('editor').getContent());
   $("#xinwenForm").submit();
   return false;
 }
 
 UE.getEditor('editor').addListener("ready", function() {
   setContent();
 });
 
function updateMsg(url, toUrl) {
	$("#newsDesc").attr("value", UE.getEditor('editor').getContent());
    save(url, toUrl);
}

 </script>
 <#include "../common/footer.ftl">
</#escape>
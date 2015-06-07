<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">邀请好友</a>
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
                        <form class="form-horizontal" role="form" id="saveForm" action="${request.contextPath}/admin/config/allsave/">
                           
                            <div class="form-group ">
                                <input type="hidden" value="${info.id}" name="id"/>
                                <input type="hidden"  name="configValue" id="configValue" value='${(info.configValue)!""}' />
                                <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span>${info.configName}：</label>
                                <div class="col-sm-4">
                                    <input type="file" name="file_upload" id="file_upload_1" style="display: block;float: left;margin-top: 20px;"/>
                                    <img id="first_img" style="width:100px;height:100px;" src="<#if info?has_content && info.configValue?has_content>${request.contextPath}/resources/admin/store/${info.configValue}</#if>"/>
                                </div>
                            </div>

                            <div class="space-4"></div>
                           
                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button" id="save" urlkey="${request.contextPath}/admin/config/goldensnail">
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
<#include "../common/footer.ftl">
 <script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js?v=${jslevel}" type="text/javascript"></script>
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
         $("#configValue").val(data);
       }
     });
   }, 10);
 });
 
 </script>
</#escape>
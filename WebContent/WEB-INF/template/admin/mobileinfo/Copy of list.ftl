<#escape x as x!"">
<#include "../common/header.ftl">
<script src="${request.contextPath}/resources/admin/js/jquery/plugins/form/ajaxfileupload.js" type="text/javascript"></script>
<div class="main-content">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">手机</a>
            <small>
                <i class="icon-double-angle-right"></i>
                号码列表
            </small>
            
               <small>
                <i class="icon-double-angle-right"></i>
                <a  href="${request.contextPath}/resources/admin/store/手机号码导入模板.xlsx">手机号码导入模板</a>
               
            </small>
        </li>
        <li>
        
        </li>
    </ul><!-- .breadcrumb -->
     
        <form id="upForm"  method="post" enctype="multipart/form-data"  action="${request.contextPath}/admin/mobileinfo/uploadMember"> 
                    		<input type="file" id="J_member" name="exl"   />
                    		<!-- <input type="button"  value="导入" /> -->
                  <!-- 		<button style="display:none" id="J_member_member" type="submit">上传</button> -->
                    		 <button  type="button"   style="float:left" onclick="uploadMember();">上传</button>
         </form>
        
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
    <th class="center">
        <label>
            <input type="checkbox" class="ace J_SelectAll">
            <span class="lbl"></span>
        </label>
    </th>
    <th>号码</th>
    <th>ID</th>
    <th>导入时间</th>
</tr>
</thead>
<tbody>
<form id="deleteForm" action="${request.contextPath}/admin/mobileinfo/deleteall/">
<#if infos?has_content>
<#list infos as info>
<tr>
    <td class="center">
        <label>
            <input type="checkbox" value="${info.id}" name="ids" class="ace J_SelectSub">
            <span class="lbl"></span>
        </label>
    </td>
    <td>${info.mobile}</td>
    <td>${info.id}</td>
    <td><#if info.createTime?has_content>${info.createTime?string("yyyy-MM-dd")}</#if></td>
</tr>
</#list>
</#if>
</form>
</tbody>
</table>
<div class="row">
    <div class="col-xs-6">
        <button type="button" onclick="deleteAll();" class="btn btn-info">
            批量删除
        </button>
       
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
<script src="${request.contextPath}/resources/admin/js/jquery.uploadify.min.js?v=${jslevel}" type="text/javascript"></script>
<script>
function deleteAll(){
	$.ajax({
	   type: "POST",
	   url: "${request.contextPath}/admin/mobileinfo/deleteall/",
	   data: $('#deleteForm').serialize(),
	   success: function(data){
	     if(data.isSuccess){
	    	 alert("删除成功");
	    	 location.href = "${request.contextPath}/admin/mobileinfo/list/p1/";
	     }else{
	    	bootbox.alert("删除失败");
	     }
	   },
	   dataType: "json"
	});
}

 function uploadMember(){
 	var member = $("#J_member").val();
 	if(!member || member == ''){
 		alert("请选择文件!");
 	}else{
 		ajaxFileUpload();
 	}
 }
 
 function ajaxFileUpload() {
	$.ajaxFileUpload({
		url : $("#upForm").attr("action"), //用于文件上传的服务器端请求地址
		secureuri : false, //是否需要安全协议，一般设置为false
		fileElementId : 'J_member', //文件上传域的ID
		dataType : 'json', //返回值类型 一般设置为json
		success : function(data, status) //服务器成功响应处理函数
		{
			if (data.isSuccess) {
				alert("上传成功");
				$("#J_member").val("");
			} else {
				alert("上传失败");
			}
		},
		error : function(data, status, e)//服务器响应失败处理函数
		{
			alert(e);
		}
	})
	return false;
}

</script>
</#escape>
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
                手机分类
            </small>
        </li>
    </ul><!-- .breadcrumb -->

</div>

<div class="page-content">
    <div class="page-header">
        <form class="form-inline" id="saveForm" action="${request.contextPath}/admin/mobilecate/oneSave?cateLevel=1">
            <label> 添加一级分类：</label>
            <div class="form-group">
                <input class="input-large" type="text" value="" id="cateName" name="cateName">
            </div>
                <input class="input-large" type="hidden" value="0" id="parentCateId" name="parentCateId">
            <button class="btn btn-sm btn-primary" type="button" id="save" urlkey="${request.contextPath}/admin/mobilecate/oneList/p1">
                <i class="icon-search align-top"></i>添加
            </button>
        </form>   
        <form class="form-inline" id="saveForm2" action="${request.contextPath}/admin/mobilecate/twoSave?cateLevel=2">    
            <label> 添加二级分类：</label>
            <div class="form-group">
                <input class="input-large" type="text" value="" name="cateName" id="cateName">
                <select class="input-large" name="parentCateId">
                    <#if infos?has_content>
                    <#list infos as info>
                      <option value="${info.id}">${info.cateName}</option>
                    </#list>
                    </#if>   
                </select>
            </div>
            <button class="btn btn-sm btn-primary" type="button" id="save2" urlkey="${request.contextPath}/admin/mobilecate/oneList/p1">
                <i class="icon-search align-top"></i>添加
            </button>
        </form>
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
                                        <th>ID</th>
                                        <th>一级分类</th>
                                        <th>二级分类数量</th>
                                        <th>导入用户数</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr>
                                    <#if infos?has_content>
                                    <#list infos as info>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace J_SelectSub">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td>${info.id}</td>
                                        <td>${(info.cateName)!''}</td>
                                        <td>${info.twoCateSize}</td> 
                                        <td>${info.userSize}</td>                                
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-success" onclick="javascript:window.location.href='${request.contextPath}/admin/mobilecate/twoList/p1?parentCateId=${info.id}'">
                                                    <i class="icon-stop bigger-120"> 查看 </i>
                                                </button>

                                                <button class="btn btn-xs btn-danger J_DeleteTr" data-value="${request.contextPath}/admin/mobilecate/delete/${info.id}">
                                                    <i class="icon-stop bigger-120">删除 </i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    </#list>
                                    </#if>
                                    </tbody>
                                </table>
                                <div class="row">
                                		<a  href="${request.contextPath}/resources/admin/store/手机号码导入模板.xlsx">导入模板</a>
                                		  <form id="upForm"  method="post" enctype="multipart/form-data"  action="${request.contextPath}/admin/mobileinfo/uploadMember"> 
                                		<input type="file" id="J_member" name="exl"   />
                                    <div class="col-xs-6">
                                        <button type="button" class="btn btn-info" onclick="ajaxFileUpload();">
                                            <i class="icon-ok bigger-110"></i>
                                            导入号码
                                        </button>
                                    </div>
                                    </form>
                                    <div class="col-xs-6">
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

    </div><!-- /.page-header -->

</div><!-- /.page-content -->
</div><!-- /.main-content -->

<#include "../common/footer.ftl">

<script>

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
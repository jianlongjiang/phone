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
                分类
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
                                        <th>名称</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <#if infos?has_content>
                                    <#list infos as info>
                                    <form id="saveForm${info_index}" action="${request.contextPath}/admin/newscate/save/">
                                    <tr>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace J_SelectSub">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td>
                                            <input type="hidden" name="id" id="id" value="${info.id}">
                                            <input class="input-small" type="text" name="cateName" value="${info.cateName}">
                                        </td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <a class="btn btn-xs btn-success" onclick="saveOne('${info_index}', this);">
                                                    <i class="icon-stop bigger-120"> 保存 </i>
                                                </a>
                                                <a class="btn btn-xs btn-danger J_DeleteTr" data-value	="${request.contextPath}/admin/newscate/delete/${info.id}">
                                                    <i class="icon-stop bigger-120"> 删除 </i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                    </form>
                                    </#list>
                                    </#if>
                                    <form id="saveForm" action="${request.contextPath}/admin/newscate/save/">
                                    <tr>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace J_SelectSub">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td>
                                            <input class="input-small" type="text" name="cateName" value="">
                                        </td>
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <a id="save" urlkey="${request.contextPath}/admin/newscate/list/p1/" class="btn btn-xs btn-success">
                                                    <i class="icon-stop bigger-120"> 保存 </i>
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
function saveOne(index, event){
	var _this = $(event);
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/newscate/save/",
 		   data: $('#saveForm'+index).serialize(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 location.href = "${request.contextPath}/admin/newscate/list/p1/";
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}
</script>
</#escape>
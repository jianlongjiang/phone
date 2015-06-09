<#escape x as x!"">
<#include "../common/header.ftl">
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
            </li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="page-header">
            <form class="form-inline">
                <div class="form-group">
                    <select class="input-large" name="cateIds">
                        <option value="">&nbsp;全部(手机分类查询)</option>
                        <#if cateInfos?has_content>
                        <#list cateInfos as info>
                        <option <#if bean.cateIds?? && bean.cateIds="${info.id}">selected</#if> value="${info.id}" >${info.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                    <label>手机号码：</label>
                    <input class="input-large" type="text" value="${bean.mobile}" name="mobile">
                </div>
                <button class="btn btn-sm btn-primary">
                    <i class="icon-search align-top"></i>搜索
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
                                                    <input type="checkbox" class="ace J_SelectSub" value="${info.id}" name="ids">
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
                                            <button type="button" class="btn btn-info" onclick="deleteAll()">
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
<script>
function deleteAll(){
console.log($('#deleteForm').serialize());
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

</script>
</#escape>
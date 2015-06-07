<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
<div class="breadcrumbs" id="breadcrumbs">
    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#"> 新闻列表 </a>
        </li>
    </ul><!-- .breadcrumb -->
</div>
<div class="page-content">
<div class="page-header">
    <form class="form-inline" action="${request.contextPath}/admin/newsinfo/list/p1/">
        <div class="form-group">
            <label>标题</label>
            <input class="input-large" name="title" value="${bean.title}" type="text">
            <select class="input-large" name="cateId">
            	<#if allcates?has_content>
            	<#list allcates as cate>
                <option value="${cate.id}">${cate.cateName}</option>
                </#list>
                </#if>
            </select>
            <select class="input-large">
                <option>加精</option>
            </select>
        </div>
        <button class="btn btn-sm btn-primary">
            <i class="icon-search align-top"></i>搜索
        </button>
        <a href="${request.contextPath}/admin/newsinfo/add/">
		<button class="btn btn-warning" type="button">
		<i class="icon-plus align-top bigger-125"></i>
		添加
		</button>
		</a>
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
    <th>标题</th>
    <th>更新时间</th>
    <th>作者</th>
    <th>是否加精</th>
    <th>排序</th>
    <th>类别</th>
    <th>操作</th>
</tr>
</thead>
<tbody>
<#if infos?has_content>
<#list infos as info>
<tr>
    <td class="center">
        <label>
            <input type="checkbox" class="ace J_SelectSub">
            <span class="lbl"></span>
        </label>
    </td>
    <td>${info.title}</td>
    <td><#if info.updateTime?has_content>${info.updateTime?string("yyyy-MM-dd")}</#if></td>
    <td>${info.author}</td>
    <td>是</td>
    <td>${info.orderBy}</td>
    <td>A</td>
    <td>
        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
            <a class="btn btn-xs btn-success" href="${request.contextPath}/admin/newsinfo/input/${info.id}">
                <i class="icon-stop bigger-120"> 编辑 </i>
            </a>
            <a class="btn btn-xs btn-danger J_DeleteTr" data-src="${request.contextPath}/admin/newsinfo/delete/${info.id}">
                <i class="icon-stop bigger-120">删除 </i>
            </a>
        </div>
    </td>
</tr>
</#list>
</#if>
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
</div><!-- /.page-header -->
</div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
</#escape>
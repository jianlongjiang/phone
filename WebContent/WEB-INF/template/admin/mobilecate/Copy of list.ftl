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
                    手机号码
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
                                            <th>ID</th>
                                            <th>名称</th>
                                            <th>属于</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if infos?has_content>
                                        <#list infos as info>
                                        <tr>
                                            <td class="center">
                                                <label>
                                                    <input type="checkbox" value="${(info.id)}" name="ids" class="ace J_SelectSub">
                                                    <span class="lbl"></span>
                                                </label>
                                            </td>
                                            <td>${(info.id)}</td>
                                            <td>${(info.cateName)}</td>
                                            <td>${(info.more1)}</td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <a href="${request.contextPath}/admin/mobileinfo/list/p1/?cateIds=${(info.id)}" class="btn btn-xs btn-success">
                                                        <i class="icon-stop bigger-120"> 查看 </i>
                                                    </a>
                                                    <a class="btn btn-xs btn-danger J_DeleteTr" data-src="${request.contextPath}/admin/mobilecate/delete/${info.id}">
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
                                            <button type="button" class="btn btn-info">
                                                <i class="icon-ok bigger-110"></i>
                                                导入号码
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
            <form class="form-inline" id="saveForm" action="${request.contextPath}/admin/mobilecate/save/">
                <label> 添加分类   </label>
                <div class="form-group">
                    <label > 名称 </label>
                    <input class="input-large" name="cateName" type="text" value="">
                    <label > 属于 </label>
                    <select name="more1">
                    	<#--<#list enums['com.phone.cn.conf.enums.LevelEnum']? values as levelEnum>
                        <option value="${levelEnum.levelName}">${levelEnum.levelName}</option>
                        </#list>-->
                        <#if firstCates?has_content>
                        <#list firstCates as cate>
                        <option value="${cate.cateName}">${cate.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                </div>
                <a id="save" urlkey="${request.contextPath}/admin/mobilecate/list/p1/" class="btn btn-sm btn-primary">
                    <i class="icon-search align-top"></i>添加
                </a>
            </form>
        </div><!-- /.page-header -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
</#escape>
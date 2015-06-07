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
                <div class="form-group" style="margin-bottom: 10px;">
                    <label>标题</label>
                    <input class="input-large" type="text" value="${bean.title}" name="title">

                    <label>更新时间</label>
                    <input class="input-large Wdate" type="text" name="startDate" value="<#if (bean.startDate)??>${bean.startDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                    -
                     <input class="input-large Wdate" type="text" name="endDate" value="<#if (bean.endDate)??>${bean.endDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                </div>
                <br>
                <div class="form-group" style="margin-bottom: 10px;">
                    <select name="cateId" class="input-large">
                        <option value="">类别</option>
                        <#if allcates?has_content>
                        <#list allcates as c >
                        <option value="${c.id}" <#if c.id?? && bean.cateId?? && bean.cateId=c.id>selected</#if> >${c.cateName}</option>
                        </#list>
                        </#if>
                    </select>

                    <select class="input-large">
                        <option>全部</option>
                    </select>

                    <label>ID</label>
                    <input class="input-large" type="text" value="" name="">
                </div>
                <br>
                <button class="btn btn-sm btn-primary">
                    <i class="icon-search align-top"></i>搜索
                </button>

                <button type="button" class="btn btn-sm btn-primary toUrl" data-toUrl="${request.contextPath}/admin/newsinfo/add">          
                    <i class="icon-search align-top"></i>添加新闻 
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
                                            <th>标题</th>
                                            <th>更新时间</th>
                                            <th>作者</th>
                                            <th>发布者</th>
                                            <th>发布时间</th>
                                            <th>是否加精</th>
                                            <th>排序</th>
                                            <th>类别</th>
                                            <th>审核状态</th>
                                            <th>审核留言</th>
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
                                            <td>${info.id}</td>
                                            <td>${info.title}</td>
                                            <td><#if info.updateTime?has_content>${info.updateTime?string("yyyy-MM-dd")}</#if></td>
                                            <td>${info.author}</td>
                                            <td>${info.createUser}</td>
                                            <td><#if info.updateTime?has_content>${info.updateTime?string("yyyy-MM-dd")}</#if></td>
                                            <td><#if info?has_content && info.isrec?? && info.isrec==true>是<#else>否</#if></td>
                                            <td>${info.orderBy}</td>
                                            <td>${info.cateName}</td>
                                            <td><span id="audit_${info.id}"><#if info?? && info.isAudit?? && info.isAudit==true>已审核<#else>未审核</#if></span></td>
                                            <td>
                                                <input class="input-sm" type="text" value="${info.auditDesc}" id="auditDesc" name="auditDesc">
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <button id="do_audit_${info.id}" class="btn btn-xs btn-warning audit" data-url="${request.contextPath}/admin/newsinfo/save?id=${info.id}" data-status="<#if info?? && info.isAudit?? && info.isAudit==true>1<#else>0</#if>" data-id="${info.id}">
                                                        <i class="icon-stop bigger-120"> <#if info?? && info.isAudit?? && info.isAudit==true>取消审核<#else>通过审核</#if> </i>
                                                    </button>
                                                    <button class="btn btn-xs btn-success" onclick="javascript:window.location.href='${request.contextPath}/admin/newsinfo/input/${info.id}'">
                                                        <i class="icon-stop bigger-120"> 编辑 </i>
                                                    </button>

                                                    <button class="btn btn-xs btn-danger J_DeleteTr" data-value="${request.contextPath}/admin/newsinfo/delete/${info.id}">
                                                        <i class="icon-stop bigger-120"> 删除 </i>
                                                    </button>
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
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
</#escape>
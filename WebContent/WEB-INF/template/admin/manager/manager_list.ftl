<#escape x as x!"">
<#include "../common/header.ftl">

<div class="main-content">
<div class="breadcrumbs" id="breadcrumbs">


    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">权限管理</a>
        </li>
    </ul><!-- .breadcrumb -->

</div>

<div class="page-content">
    <div class="page-header">



    </div><!-- /.page-header -->


    <div class="row">
        <div class="col-xs-12">
            <!-- PAGE CONTENT BEGINS -->
*:密码初始为:123456
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
                                        <th>账号</th>
                                        <th>姓名</th>
                                        <th>角色</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <#if infos??>
                                    <#list infos as info>
                                    <tr>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace J_SelectSub">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>
                                        <td> ${info.account} </td>
                                        <td> ${info.realName} </td>
                                        <td> <@lh.loadRoleName info.id />  </td>
                                     
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-success toUrl" data-toUrl="${request.contextPath}/admin/manager/input/${info.id}" >
                                                    <i class="icon-stop bigger-120"> 编辑权限 </i>
                                                </button>

                                                <button class="btn btn-xs btn-danger J_DeleteTr"   data-value="${request.contextPath}/admin/manager/delete/${info.id}"  >
                                                    <i class="icon-stop bigger-120"> 删除 </i>
                                                </button>

                                                <button class="btn btn-xs btn-warning J_UpdateTr"   data-value="${request.contextPath}/admin/manager/defaultPwd?id=${info.id}">
                                                    <i class="icon-copy bigger-120"> 修改密码 </i>
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
</#escape>
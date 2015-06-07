<#escape x as x!"">
<#include "../common/header.ftl">

<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#"> 职务列表 </a>
            </li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="page-header">

        </div><!-- /.page-header -->

        <div class="row">
            <div class="col-xs-8">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="tabbable ">
                        <div class="tab-content">
                            <div class="table-responsive">
                                <div class="dataTables_wrapper">
                                    <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
                                        <thead>
                                        <tr>
                                            <th>职务</th>
                                            <th>描述</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <#if  infos?has_content>
                                        <#list infos as info>
                                        <tr>
                                            <td> ${info.nameCh} </td>
                                            <td>  ${info.description} </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <button class="btn btn-xs btn-success toUrl" data-toUrl="${request.contextPath}/admin/role/input/${info.id}">
                                                        <i class="icon-stop bigger-120"> 查看 </i>
                                                    </button>

                                                    <button class="btn btn-xs btn-danger J_DeleteTr"   data-value="${request.contextPath}/admin/role/delete/${info.id}"  >
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
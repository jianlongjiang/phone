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
               <a href="${request.contextPath}/admin/mobilecate/oneList/p1/"> 手机分类</a>
            </small>
            <small>
                <i class="icon-double-angle-right"></i>
                二级分类
            </small>
        </li>
    </ul><!-- .breadcrumb -->

</div>

<div class="page-content">
    <div class="page-header">
        <form class="form-inline" id="saveForm">
            
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
                                        <th>二级分类</th>
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
                                        <td>${info.userSize}</td>                                
                                        <td>
                                            <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
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
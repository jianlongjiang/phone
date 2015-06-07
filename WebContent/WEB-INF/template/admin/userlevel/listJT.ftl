<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">


        <ul class="breadcrumb">
            <i class="icon-home home-icon"></i>
            <a href="#">经验规则设置</a>
            <small>
                <i class="icon-double-angle-right"></i>
                等级设置
            </small>
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
                                            <th>称号</th>
                                            <th>达到经验值</th>
                                            <th>优先级</th>
                                            <th>图标</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <tr>
                                            <td>
                                                <input class="input-large" type="text" value="称号">
                                            </td>
                                            <td>
                                                <input class="input-small" type="text" value="经验值">
                                            </td>
                                            <td>
                                                <input class="input-small" type="text" value="优先级">
                                            </td>
                                            <td>
                                                <a class="btn btn-app btn-yellow btn-xs" href="#">
                                                    <i class="icon-shopping-cart bigger-160"></i>
                                                    Shop
                                                </a>
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">

                                                    <button class="btn btn-xs btn-success J_DeleteTr">
                                                        <i class="icon-stop bigger-120"> 保存 </i>
                                                    </button>

                                                    <button class="btn btn-xs btn-danger">
                                                        <i class="icon-stop bigger-120"> 删除 </i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <input class="input-large" type="text" value="">
                                            </td>
                                            <td>
                                                <input class="input-small" type="text" value="">
                                            </td>
                                            <td>
                                                <input class="input-small" type="text" value="">
                                            </td>
                                            <td>
                                                <button>上传图标</button>
                                            </td>
                                            <td>
                                                <button>添加等级</button>
                                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xs-6">

                                        </div>
                                        <div class="col-xs-6">
                                            <div class="dataTables_paginate paging_bootstrap">
                                                <ul class="pagination">
                                                    <li class="prev disabled"><a href="#"><i class="icon-double-angle-left"></i></a></li>
                                                    <li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li>
                                                    <li class="next"><a href="#"><i class="icon-double-angle-right"></i></a></li>
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
</#escape>
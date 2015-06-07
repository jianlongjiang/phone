<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#"> 回复留言 </a>
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
                                            <th>用户</th>
                                            <th>手机</th>
                                            <th>标题</th>
                                            <th>内容</th>
                                            <th>回复内容</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <tr>
                                            <td class="center">
                                                <label>
                                                    <input type="checkbox" class="ace J_SelectSub">
                                                    <span class="lbl"></span>
                                                </label>
                                            </td>
                                            <td>Label</td>
                                            <td>18710803487</td>
                                            <td>123</td>
                                            <td>456</td>
                                            <td>
                                                <textarea></textarea>
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <button class="btn btn-xs btn-success">
                                                        <i class="icon-stop bigger-120"> 回复 </i>
                                                    </button>

                                                    <button class="btn btn-xs btn-danger J_DeleteTr">
                                                        <i class="icon-stop bigger-120">删除 </i>
                                                    </button>
                                                </div>
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
            <form class="form-inline">
                <textarea style="height: 100px;width: 200px;">
                </textarea>
                <br>
                <button>
                    批量回复
                </button>
            </form>
        </div><!-- /.page-header -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<#include "../common/footer.ftl">
</#escape>
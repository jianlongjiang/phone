<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#"> 统计页面 </a>
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
                                            <th colspan="2" style="text-align: center;">今日</th>
                                            <th colspan="2" style="text-align: center;">本月</th>
                                            <th colspan="2" style="text-align: center;">总数</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>下载量</td>
                                            <td>${(todayTongji.mobileAmount)!''}</td>
                                            <td>下载量</td>
                                            <td>${(monthTongji.mobileAmount)!''}</td>
                                            <td>下载量</td>
                                            <td>${(yearTongji.mobileAmount)!""}</td>
                                        </tr>
                                        <tr>
                                            <td>下载人数</td>
                                            <td>${(todayTongji.userAmount)!""}</td>
                                            <td>下载人数</td>
                                            <td>${(monthTongji.userAmount)!""}</td>
                                            <td>下载人数</td>
                                            <td>${(yearTongji.userAmount)!''}</td>
                                        </tr>
                                        </tbody>
                                    </table>
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
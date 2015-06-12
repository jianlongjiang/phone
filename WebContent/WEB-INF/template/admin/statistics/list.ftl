<#escape x as x!"">
<#include "../common/header.ftl">
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">


        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">统计页面</a>
            </li>
        </ul><!-- .breadcrumb -->

    </div>

    <div class="page-content">
        <div class="page-header">
            <form class="form-inline">
                <h3>蜗牛巴巴统计</h3>
                <div class="form-group" style="margin-bottom: 10px;">
                    <label>统计时间：</label>
                    <input class="input-large Wdate" type="text" name="startDate" value="<#if (bean.startDate)??>${bean.startDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                    -
                     <input class="input-large Wdate" type="text" name="endDate" value="<#if (bean.endDate)??>${bean.endDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
                </div>
                <br>
				<button class="btn btn-sm btn-primary">
                 <i class="icon-search align-top"></i>查询
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
                                                <th>金蜗牛人数</th>
                                               
                                                <th>注册人数</th>
                                                <th>登录人数</th>
                                                <th>签到人数</th>
                                                <!--
                                                <th>卸载人数</th>
                                                -->
                                                <th>时间</th>
                                            </tr>
                                        </thead>

                                        <tbody>
                                        		<#if  infos?has_content>
                                        		<#list infos as info>
                                            <tr>
                                                <td> ${info.vipAmount}</td>
                                                <td>${info.int1}</td>
                                                <td>${info.loginAmount}</td>
                                                <td>${info.registAmount}</td>
                                                <!--
                                                <td>6---</td>
                                                -->
                                                <th> <#if (info.createTime)??>${info.createTime?string('yyyy-MM-dd')}<#else></#if> </th>
                                            </tr>
                                            </#list>
                                            </#if>
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xs-6">

                                        </div>
                                        <div class="col-xs-6">
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
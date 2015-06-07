<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">积分规则设置</a>
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
                    <div class="col-xs-12">
                        <form class="form-horizontal" role="form" id="saveForm" action="${request.contextPath}/admin/config/allsave/">
                            <#if infos?has_content>
                			<#list infos as info>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span> ${info.configName}：</label>
                                <div class="col-sm-4">
                                	<input type="hidden" value="${info.id}" name="id"/>
                                    <input type="text" id="form-field-1"  value="${info.configValue}" name="configValue" class="col-xs-12 col-sm-12 J_Price">
                                </div>
                            </div>
                            <div class="space-4"></div>
                            </#list>
                    		</#if>
                            <h3>金牌会员推荐金牌会员规则：当被推荐者交100元加入金蜗牛且推荐人也已经是金蜗牛会员</h3>
                            <#if infos2?has_content>
                			<#list infos2 as info>
                            <div class="form-group">
                                <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span> ${info.configName}：</label>
                                <div class="col-sm-4">
                                	<input type="hidden" value="${info.id}" name="id"/>
                                    <input type="text" id="form-field-1"  value="${info.configValue}" name="configValue" class="col-xs-12 col-sm-12 J_Price">
                                </div>
                            </div>
                            <div class="space-4"></div>
                            </#list>
                    		</#if>
                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button" id="save" urlkey="${request.contextPath}/admin/config/integral/">
                                        <i class="icon-ok bigger-110"></i>
                                        保存
                                    </button>
                                </div>
                            </div>
                            

                        </form>
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
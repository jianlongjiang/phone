<#escape x as x!"">
<#include "../common/header.ftl">

<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">添加资源</a>
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
                        <form class="form-horizontal" role="form" id="saveForm">
                        			<input type="hidden"  name="id"  value="${(info.id)!''}"  />
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> URL：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="uri"  value="${(info.uri)!''}" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            
                             <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 名字：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12"  name="more1"  value="${(info.more1)!''}" />
                                </div>
                            </div>
                            <div class="space-4"></div>
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 功能编码：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" <#if info?? && info.more2??>readonly="readonly"</#if> name="more2"  value="${(info.more2)!''}" />
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 描述：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="description"  value="${(info.description)!''}" />
                                </div>
                            </div>

                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button"  onclick="saveFun('${request.contextPath}/admin/resources/save','${request.contextPath}/admin/resources/list/p1')" >
                                        <i class="icon-ok bigger-110"></i>
                                        提交
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
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<#include "../common/footer.ftl">
<#include "../common/footer.ftl">
<script type="text/javascript">
function  saveFun(from, to){
	console.log("aa");
	saveForm("saveForm", from, to);
}
</script>
</#escape>
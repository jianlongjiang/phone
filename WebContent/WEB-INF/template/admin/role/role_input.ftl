<#escape x as x!"">
<#include "../common/header.ftl">

<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">添加职务</a>
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
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 职务名称(中文)：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="nameCh" value="${(info.nameCh)!''}"  />
                                </div>
                            </div>
                            
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 职务名称(英文)：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="name" value="${(info.name)!''}"  />
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 职务描述：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="description" value="${(info.description)!''}"  />
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 职务权限：</label>
                                <div class="col-sm-10">
                                
                                		<#if resources??>
                                		<#list  resources as  resource>
                                    <label class="col-sm-2">
                                        <input type="checkbox" name="resourceIds"  <#if (resource.hasRole)?? && resource.hasRole>checked</#if>  class="ace" value="${resource.id}" >
                                        <span class="lbl">  ${resource.more1}</span>
                                    </label>
                                    </#list>
                                    </#if>
                                </div>
                            </div>

                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button"   onclick="saveFun('${request.contextPath}/admin/role/saveAndResource','${request.contextPath}/admin/role/list/p1')" >
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
<script type="text/javascript">
function  saveFun(from, to){
	console.log("aa");
	saveForm("saveForm", from, to);
}
</script>
</#escape>
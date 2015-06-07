<#escape x as x!"">
<#include "../common/header.ftl">

<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">添加后台管理员</a>
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
                        <form class="form-horizontal"  id="savaForm"  role="form"  >

							<input type="hidden"  name="id"  value="${(info.id)!''}"  />
                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 账号：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12" name="account"  value="${(info.account)!''}"  >
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="form-group ">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 名字：</label>
                                <div class="col-sm-4">
                                    <input type="text"  class="col-xs-12 col-sm-12"  name="realName"  value="${(info.realName)!''}">
                                </div>
                            </div>

                            <div class="space-4"></div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label"> <span class="red">*</span> 职务：</label>
                                <div class="col-sm-4">
                                    <select class="col-xs-6 col-sm-6" name="roleId"  >
										<#if  roles?has_content>
										<#list  roles as role>
                                        <option   value="${role.id}"    <#if roleId?? && roleId==role.id>  selected</#if>   >  ${role.nameCh}  </option>
										</#list>
										</#if>		
                                    </select>
                                </div>
                            </div>

                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button" onclick="saveFun('${request.contextPath}/admin/manager/save','${request.contextPath}/admin/manager/list/p1')" >
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
	saveForm("savaForm", from, to);
}
</script>
</#escape>
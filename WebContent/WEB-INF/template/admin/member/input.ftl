<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">用户信息</a>
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
                        <form class="form-horizontal" id="saveForm"  role="form">
                        		<input type="hidden" 	name="id"  value="${(info.id)!''}">
                        		
                        		
                        		      <div class="form-group ">
                                <label class="col-sm-1">账号</label>
                                <div class="col-sm-8">
                                    <input type="text" name="mobile" readonly=true value="${(info.mobile)!''}"  />
                                </div>
                            </div>
                        		
                            <div class="form-group ">
                                <label class="col-sm-1">昵称</label>
                                <div class="col-sm-8">
                                    <input type="text" name="userName"  value="${(info.userName)!''}"  />
                                </div>
                            </div>
                            
                            
                             <div class="form-group ">
                                <label class="col-sm-1">微信</label>
                                <div class="col-sm-8">
                                    <input type="text" name="weixin"  value="${(info.weixin)!''}"  />
                                </div>
                            </div>
                            
                          
                            <!--
                                <div class="form-group ">
                                <label class="col-sm-1">微信</label>
                                <div class="col-sm-8">
                                    <input type="text" name="weixin"  value="${(info.weixin)!''}"  />
                                </div>
                            </div>
                            -->


							  <div class="form-group ">
                                <label class="col-sm-1">支付宝账号</label>
                                <div class="col-sm-8">
                                    <input type="text" name="alipayAccount" readonly=true  value="${(infoMore.alipayAccount)!''}"  />
                                </div>
                            </div>
                            
                              <div class="form-group ">
                                <label class="col-sm-1">支付宝用户</label>
                                <div class="col-sm-8">
                                    <input type="text" name="alipayRealName"  readonly=true  value="${(infoMore.alipayRealName)!''}"  />
                                </div>
                            </div>

                            <div class="space-4"></div>

                            <div class="clearfix">
                                <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button"   onclick="save('${request.contextPath}/admin/member/userDetailSave','${request.contextPath}/admin/member/list/p1')" >
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
</#escape>
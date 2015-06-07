<#escape x as x!"">
<#include "../common/header.ftl">
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>

<div class="main-content">
<div class="breadcrumbs" id="breadcrumbs">


    <ul class="breadcrumb">
        <li>
            <i class="icon-home home-icon"></i>
            <a href="#">打款管理</a>
        </li>
    </ul><!-- .breadcrumb -->

</div>

<div class="page-content">
    <div class="page-header">
        <form class="form-inline" action="${request.contextPath}/admin/cashUserLog/list/p1">
            <div class="form-group" style="margin-bottom: 10px;">
                <label>打款支付宝：</label>
                <input class="input-large" type="text" name="aplipay"   value="${(bean.aplipay)!''}">

                <label>ID：</label>
                <input class="input-large" type="text" name="id"  value="${(bean.id)!''}">
                <label>状态：</label>
                <select class="input-large" name="doStatus">
                <option value="">&nbsp;全部</option>
                <option <#if bean.doStatus?? && bean.doStatus="3">selected</#if> value="3">提款</option>
                <option <#if bean.doStatus?? && bean.doStatus=="2">selected</#if> value="2">进行中</option>
                <option <#if bean.doStatus?? && bean.doStatus=="1">selected</#if> value="1">成功</option>
                <option <#if bean.doStatus?? && bean.doStatus=="0">selected</#if> value="0">失败</option>
            </select>
            </div>
            <br>
            <div class="form-group" style="margin-bottom: 10px;">
                <label>手机号：</label>
                <input class="input-large" type="text" name="mobile" value="${(bean.mobile)!''}" />

                <label>金额：</label>
                <input class="input-sm" type="text" name="startCash"value="${(bean.startCash)!''}" />
                -
                <input class="input-sm" type="text" name="endCash"value="${(bean.endCash)!''}" />
            </div>
            <br>
            <div class="form-group" style="margin-bottom: 10px;">
                <label>提现申请时间：</label>
                <input class="input-large" type="text" name="startDate" value="<#if (bean.startDate)??>${bean.startDate?string('yyyy-MM-dd')}</#if>"  class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"  />
                -
                <input class="input-large" type="text" name="endDate" value="<#if (bean.endDate)??>${bean.endDate?string('yyyy-MM-dd')}</#if>"  class="Wdate"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})"  />
            </div>
            <br>
            <button class="btn btn-sm btn-primary">
                <i class="icon-search align-top"></i>搜索
            </button>
            <button class="btn btn-sm btn-primary toUrl" type="button" data-toUrl="${request.contextPath}/admin/cashUserLog/exportExcel">
                <i class="icon-search align-top"></i>导出
            </button>
            <br>
                                          填写你要批量删除的编号，每个编号都要换行。
            <br>
            <div class="form-group" style="margin-bottom: 10px;">
                <textarea style="width: 600px;height: 100px;" id="ids" name="ids"></textarea>     
            </div>
            <br>
            <button class="btn btn-sm btn-primary" type="button" id="batchFail" onclick="fail()">
                <i class="icon-search align-top"></i>批量失败
            </button>
            <button class="btn btn-sm btn-primary" type="button" id="batchSuc" onclick="suc()">
                <i class="icon-search align-top"></i>批量成功
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
                                            <th>编号</th>
                                            <th>会员ID</th>
                                            <th>昵称</th>
                                            <th>姓名</th>
                                            <th>手机号</th>
                                            <th>提现金额</th>
                                            <th>提现支付宝</th>
                                            <th>提现账号名称</th>
                                            <th>提现申请时间</th>
                                            
                                             <th>状态</th>
                                            
                                            <th>do</th>
                                        </tr>
                                    </thead>

                                    <tbody>
                                    		<#if infos??>
                                    		<#list infos as info>
                                        <tr>
                                            <td>${info.id} </td>
                                            <td>${info.userId}</td>
                                            <td>${info.nickname} </td>
                                            <td>${info.more2} </td>
                                            <td> ${info.mobile} </td>
                                            <td> ${info.cash} </td>
                                            <td> ${info.aplipay} </td>
                                            <td> ${info.action} </td>
                                            <td> <#if info.createTime??>  ${info.createTime?string("yyyy-MM-dd")}</#if>   </td>
                                             <td><#if info.doStatus?? &&  info.doStatus=='3'>提款
                                             <#elseif info.doStatus?? &&  info.doStatus=='2'>进行中 
                                             <#elseif info.doStatus?? &&  info.doStatus=='1'>成功
                                             <#elseif info.doStatus?? &&  info.doStatus=='0'>失败
                                             <#else>
                                             </#if> 
                                             </td>
                                            <td>  
                                            	<#if info.doStatus?? &&  info.doStatus=='3'>  
                                             	<button class="btn btn-xs btn-success "  data-value="${request.contextPath}/admin/cashUserLog/save?id=${info.id}&doStatus=2" >
                                                    <i class="icon-stop bigger-120">&nbsp;提款</i>   
												</button>			
												<#elseif info.doStatus?? &&  info.doStatus=='2'>
												<button class="btn btn-xs btn-success cashSuc"  data-value="${request.contextPath}/admin/cashUserLog/save?id=${info.id}&doStatus=1" >
                                                    <i class="icon-stop bigger-120">&nbsp;成功</i>
												</button>
					
												<button class="btn btn-xs btn-success cashFail" data-value="${request.contextPath}/admin/cashUserLog/save?id=${info.id}&doStatus=0" >
                                                    <i class="icon-stop bigger-120">&nbsp;失败</i>
												</button>
												<#else>完成。
                                                </#if> 
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
                                            <div class="dataTables_paginate paging_bootstrap">
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

</div><!-- /.main-container-inner -->

<a href="javascript:;" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    <i class="icon-double-angle-up icon-only bigger-110"></i>
</a>
</div><!-- /.main-container -->

<#include "../common/footer.ftl">
<script type="text/javascript">
$(function(){
  var str = $("#ids").val();
  
});

function fail(){
  var ids = $("#ids").val();
  //var url = $("#batchFail").attr("batchFailUrl");
  console.log(ids);
  bootbox.confirm("确认要批量删除吗？此操作后，你添加的提现申请将会变成失败。",function(result){
    if(result){
      $.ajax({
        type: "POST",
        url: "${request.contextPath}/admin/cashUserLog/batchFail/",
        data: {"ids":ids},
        dataType: "json",
        success: function(data){
          alert("操做成功。");
          if(data.isSuccess == true){  
            window.location.reload();
          //window.location.href="${request.contextPath}/admin/cashUserLog/list/p1";
          }
        },
        erro: function(){
          alert("网络错误，请重试！");
        }
      });
    }
  });
}

function suc(){
  bootbox.confirm("确认要批量成功吗？此操作后，所有的提现申请都会变成成功。",function(result){
    if(result){
      $.ajax({
        type: "POST",
        url: "${request.contextPath}/admin/cashUserLog/batchSuc/?doStatus=2",
        data: {},
        dataType: "json",
        success: function(data){   
          if(data.isSuccess == true){ 
            alert("操做成功。"); 
            window.location.reload();
          }
        },
        erro: function(){
          alert("网络错误，请重试！");
        }
      });
    }
  });
}
</script>
</#escape>
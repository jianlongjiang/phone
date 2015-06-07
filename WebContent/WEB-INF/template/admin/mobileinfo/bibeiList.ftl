<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">必备下载号码</a>
            </li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="page-header">
             <form class="form-inline" action="${request.contextPath}/admin/mobileinfo/bibeiList/p1/">
                <div class="form-group">
                    <label>手机号码：</label>
                    <input class="input-sm" type="text" value="${(bean.mobile)!''}" name="mobile">
                </div>
                <div class="form-group">
                    <label>状态：</label>
                    <select class="input-large" name="more1">
                    <option value="">&nbsp;全部状态</option>
                    <option <#if bean.more1?? && bean.more1="y">selected</#if> value="y">未停用</option>
                    <option <#if bean.more1?? && bean.more1="n">selected</#if> value="n">以停用</option>     
                    </select>
                </div>
                
                <button class="btn btn-sm btn-primary">
                    <i class="icon-search align-top"></i>搜索
                </button>
            </form>
        </div><!-- /.page-header -->
        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->
                <div class="row">
                    <div class="col-xs-12">
                        
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
                                                <th>名称</th>
                                                <th>号码</th>
                                                <th>添加时间</th>
                                                <th>下载次数</th>
                                                <th>状态</th>
                                                <th>操作</th>
                                            </tr>
                                            </thead>

                                            <tbody>
                                            <#if infos?has_content>
                                            <#list infos as info>
                                            
                                            <tr>
                                                <td class="center">
                                                    <label>
                                                        <input type="checkbox" class="ace J_SelectSub">
                                                        <span class="lbl"></span>
                                                    </label>
                                                </td>
                                                <td><@lh.searchMobileName info.mobile/></td>
                                                <td>${(info.mobile)!""}</td>
                                                <td><#if info.createTime??>${info.createTime?string("yyyy-MM-dd")}</#if></td>
                                                <td><@lh.searchDownloadAmount info.mobile/></td>
                                                <td><span id="disuse_${info.id}"><#if info.more1?? && info.more1="y">正常
                                                <#elseif info.more1?? && info.more1="n">停用
                                                <#else></#if></span></td>
                                                <td>
                                                    <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                        <button class="btn btn-xs btn-danger J_DeleteTr" data-value="${request.contextPath}/admin/mobileinfo/delete/${info.id}">
                                                            <i class="icon-stop bigger-120">删除 </i>
                                                        </button>

                                                        <button id="do_disuse_${info.id}" class="btn btn-xs btn-warning disuse" data-url="${request.contextPath}/admin/mobileinfo/disuse/?id=${info.id}" data-id="${info.id}" data-status="${info.more1}">
                                                            <i class="icon-copy bigger-120"><#if info.more1?? && info.more1="y">停用
                                                            <#elseif info.more1?? && info.more1="n">恢复<#else></#if></i>
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            </#list>
                                            </#if>
                                            
                                            </tbody>
                                        </table>   
                                        <div class="row">
                                            <div class="col-xs-6">
                                              <form id="saveForm" class="form-inline" action="${request.contextPath}/admin/mobileinfo/">
                                                <input  class="input-large" value="" type="text" name="mobile">
                                                <button type="button" class="btn btn-info" onclick="saveFun()">
                                                    <i class="icon-ok bigger-110">添加号码</i>  
                                                </button>
                                              </form>    
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
                                </div>
                            </div><!-- /.table-responsive -->
                        </div>
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
<script type="text/javascript">
function saveFun(){
      console.log($('#saveForm').serialize());
      $.ajax({
        type: "POST",
        url: "${request.contextPath}/admin/mobileinfo/bibeiAddMobile/",
        data: $('#saveForm').serialize(),
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
</script>
</#escape>
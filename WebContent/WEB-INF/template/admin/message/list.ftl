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
     <form class="form-inline" action="${request.contextPath}/admin/message/list/p1/">
        <div class="form-group">
            <select class="input-large" name="doStatus">
                <option value="">&nbsp;全部</option>
                <option <#if bean.doStatus?? && bean.doStatus="1">selected</#if> value="1">已回复</option>
                <option <#if bean.doStatus?? && bean.doStatus="0">selected</#if> value="0">未回复</option>
            </select>
        </div>    
        <button class="btn btn-sm btn-primary  "  >
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
                                            <th>回复状态</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if infos?has_content>
                                        <#list infos as info>
                                        <form id="saveForm${info_index}" action="${request.contextPath}/admin/message/save/">
                                        <input name="id" value="${info.id}" type="hidden"/>
                                        <tr>
                                            <td class="center">
                                                <label>
                                                    <input type="checkbox" name="ids" value="${info.id}" class="ace J_SelectSub">
                                                    <span class="lbl"></span>
                                                </label>
                                            </td>
                                            <td>${info.userName}</td>
                                            <td>${info.mobile}</td>
                                            <td>${info.title}</td>
                                            <td>${info.content}</td>
                                            <td>
                                                <textarea   id="replyContent_${info_index}" name="replyContent"><#if info.replyContent?has_content>${info.replyContent}</#if></textarea>
                                            </td>
                                            <td><#if bean.doStatus?? && bean.doStatus="1">已回复<#else>未回复</#if></td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                    <a class="btn btn-xs btn-success" onclick="saveOne('${info_index}', this);">
                                                        <i class="icon-stop bigger-120"> 回复 </i>
                                                    </a>
                                                    <a class="btn btn-xs btn-danger J_DeleteTr" data-src="${request.contextPath}/admin/message/delete/${info.id}">
                                                        <i class="icon-stop bigger-120">删除 </i>
                                                    </a>
                                                </div>
                                            </td>
                                        </tr>
                                        </form>
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
            <form class="form-inline">
                <textarea id="msg" style="height: 100px;width: 200px;"> </textarea>
                <br>
                <button  onclick="doAll()"  type="button">
                    批量回复
                </button>
            </form>
        </div><!-- /.page-header -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->

<script>



function  doAll(){
	
	var  ids_str = $("input[name='ids']").serialize();
	
	 $.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/message/replayAll/",
 		   data:  ids_str +"&msg="+$("#msg").val(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 	 	bootbox.alert("保存成功");
 		    	 	 	window.location.reload();
 		    	 	 	// document.URL=location.href;
 		     }else{
 		   	 	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}


function saveOne(index, event){
	var _this = $(event);
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/message/save/",
 		   data: $('#saveForm'+index).serialize()+'&doStatus=1',
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 $('#replyContent_'+index).val("");
 		    	 //location.href = "${request.contextPath}/admin/message/list/p1/";
 		    	 location.reload();
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}
</script>
<#include "../common/footer.ftl">
</#escape>
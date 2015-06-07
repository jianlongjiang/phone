<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">用户列表</a>
            </li>
        </ul><!-- .breadcrumb -->
    </div>
    <div class="page-content">
        <div class="page-header">
            <form  id="coditonForm"  class="form-inline" action="${request.contextPath}/admin/member/exportExcel2">
                <div class="form-group">
                    <select class="input-large" name="firstCateId"  id="firstCateId" >
                        <option value="">类别</option>
                        <#if firstCates??>
                        <#list firstCates as info>
                        <option value="${info.id}"    <#if (bean.firstCateId)??&& bean.firstCateId==info.id> selected</#if>   > ${info.cateName}</option>
                        </#list>
                        </#if>
                      
                    </select>
                    <select class="input-large"  name="secondCateId" id="secondCateId">
                        <option value="">级别</option>
                         <#if secondCates??>
                        <#list secondCates as info>
                        <option value="${info.id}"    <#if (bean.secondCateId)??&& bean.secondCateId==info.id> selected</#if>   > ${info.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                </div>
                <button class="btn btn-sm btn-primary"  >
                    <i class="icon-search align-top"></i>导出
                </button>
                
                 <button class="btn btn-sm btn-primary"  type="button" onclick="search('${request.contextPath}/admin/member/list/p1')" >
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
                                            <th>ID</th>
                                            <th>姓名</th>
                                            <th>微信</th>
                                            <th>手机号</th>
                                            <th>级别</th>
                                            <th>注册时间</th>
                                            <th>邀请好友数</th>
                                            <th>邀请到好友</th>
                                            <th>下载数量</th>
                                            <th>优先级</th>
                                            <th>经验值</th>
                                            <th>积分</th>
                                            <th>操作</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <#if infos?has_content>
                                        <#list infos as info>
                                        <form id="saveForm${info_index}" action="${request.contextPath}/admin/member/save/">
                                        <input type="hidden" value="${info.id}" name="id">
                                        <tr>
                                            <td class="center">
                                                <label>
                                                    <input type="checkbox" name="ids" value="${info.id}" class="ace J_SelectSub">
                                                    <span class="lbl"></span>
                                                </label>
                                            </td>
                                            <td>${info.id}</td>
                                            <td>${info.realName}</td>
                                            <td>${info.weixin}</td>
                                            <td>${info.mobile}</td>
                                            <td>${info.more1}</td>
                                            <td><#if info.createTime?has_content>${info.createTime?string("yyyy-MM-dd")}</#if></td>
                                            <td>${info.inviteFriendAmount}</td>
                                            <td>
                                                <a href="${request.contextPath}/admin/member/list/p1/?inviteesId=${info.id}">查看</a>
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="downloadMobileAmount" value="${info.downloadMobileAmount}" type="text" style="width:50px;">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="downloadLevel" type="text" value="${info.downloadLevel}" style="width:50px;">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="experience" type="text" value="${info.experience}" style="width:50px;">
                                            </td>
                                            <td>
                                                <input class="input-small J_Price" name="integration" type="text" value="${info.integration}" style="width:50px;">
                                            </td>
                                            <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                <a class="btn btn-xs btn-success" onclick="saveOne('${info_index}', this);">
                                                    <i class="icon-edit bigger-120"> 保存 </i>
                                                </a>
                                                <a class="btn btn-xs btn-danger J_DeleteTr" tr_num="2" data-src="${request.contextPath}/admin/member/delete/${info.id}">
                                                    <i class="icon-trash bigger-120"> 删除 </i>
                                                </a>
                                            	</div>
                                            </td>
                                        </tr>
                                        <#if cates?has_content>
                                        <tr><td colspan="13">
                                                所属类别：
                                                	<#if cates['${info.id}']?has_content>
                                                	<#list cates['${info.id}'] as c >
                                                	<#if c?has_content>
                                                    <input type="checkbox" name="cateids" value="${c.id}" checked="checked" class="ace">
                                                    <span class="lbl">${c.cateName}</span>
                                                    </#if>
                                                    </#list>
                                                    </#if>
                                            </td>
                                            <td>
                                                <a class="btn btn-xs btn-danger" onclick="removeCheckBox(this);">
                                                    <i class="icon-trash bigger-120"> 删除未选中类别 </i>
                                                </a>
                                            </td>
                                        </tr>
                                        </#if>
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
                <div class="form-group">
                    <select id="state" class="input-large">
                        <#if firstCates?has_content>
                        <#list firstCates as f >
                        <option value="${f.id}">${f.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                    <select id="city" class="input-large">
                        <option>类别</option>
                    </select>
                </div>
                <button class="btn btn-sm btn-primary">
                    <i class="icon-edit align-top" onclick=""></i>确认给选中的用户添加类别
                </button>
            </form>
        </div><!-- /.page-footer -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
<script>
function updateAll(){
	$("#J_Table").find("");
	$("input[type='checkbox'][checked]").each(function(){ 
	   alert($(this).val());
	});
}



function saveOne(index, event){
	var _this = $(event);
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/member/save/",
 		   data: $('#saveForm'+index).serialize(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 location.href = "${request.contextPath}/admin/member/list/p1/";
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}


$("#firstCateId").bind("change",function(){
	//console.log();
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/mobilecate/loadByParent/",
 		   data:  {"parentId":$(this).val()},
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 	var html = '<option value="">级别</option>';
 		    	 	if(!! data.result) {
 		    	 		for(var index=0 in data.result){
 		    	 			var cateInfo = data.result[index];
 		    	 			html += '<option value="'+cateInfo.id+'"  > '+ cateInfo.cateName+'</option> ';
 		    	 		}
 		    	 	}
 		    	 	$("#secondCateId").html(html);
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
})


function removeCheckBox(event){
	var _this = $(event);
	_this.parent().parent().find("input:checked").each(function(){
		$(this).next().remove();
		this.remove();
	});
}

function search(url) {
	$("#coditonForm").attr("action",url).submit();
}

</script>
</#escape>
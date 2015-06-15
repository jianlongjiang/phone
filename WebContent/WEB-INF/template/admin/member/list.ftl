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
    <form class="form-inline" action="${request.contextPath}/admin/member/list/p1/">
        <div class="form-group" style="margin-bottom: 10px;">
            <select class="input-large" name="isVip">
                <option value="">&nbsp;全部会员</option>
                <option <#if bean.isVip?? && bean.isVip==false>selected</#if> value="0">普通会员</option>
                <option <#if bean.isVip?? && bean.isVip==true>selected</#if> value="1">金蜗牛会员</option>
            </select>
            <select class="input-large" name="cateIds">
                <option value="">&nbsp;全部分类</option>
                <#if allCates?has_content>
                <#list allCates as cate>
                <option <#if bean.cateIds?? && bean.cateIds="${cate.id}">selected</#if> value="${cate.id}">${cate.cateName}</option>
                </#list>
                </#if>
                
            </select>         
            <label>手机号：</label>
            <input class="input-large" type="text" value="" name="mobile" value="">
            <select class="input-large" name="sort">
                <option value="">&nbsp;排序</option>
                <option <#if bean.sort?? && bean.sort="user_level_id.desc">selected</#if> value="user_level_id.desc">级别</option>
                <option <#if bean.sort?? && bean.sort="Invite_friend_amount.desc">selected</#if> value="Invite_friend_amount.desc">邀请的好友数</option>
                <option <#if bean.sort?? && bean.sort="invite_friend_vip_amount.desc">selected</#if> value="invite_friend_vip_amount.desc">邀请的金蜗牛会员数</option>
            </select>
        </div>
        <br>
        <div class="form-group" style="margin-bottom: 10px;">
            <label>邀请好友数：</label>
            <input class="input-sm" type="text" value="${(bean.minVal)!''}" name="minVal">
            -
            <input class="input-sm" type="text" value="${(bean.maxVal)!''}" name="maxVal">
            <label>邀请到金蜗牛会员数：</label>
            <input class="input-sm" type="text" value="${(bean.gsMinNum)!''}" name="gsMinNum">
            -
            <input class="input-sm" type="text" value="${(bean.gsMaxNum)!''}" name="gsMaxNum">
        </div>
        <div class="form-group" style="margin-bottom: 10px;">
            
            <label>注册时间：</label>
            <input class="input-large Wdate" type="text" name="startDate" value="<#if (bean.startDate)??>${bean.startDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
             -
            <input class="input-large Wdate" type="text" name="endDate" value="<#if (bean.endDate)??>${bean.endDate?string('yyyy-MM-dd')}</#if>"  onFocus="WdatePicker({startDate:'%y-%M-%d',dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})" >
            <select class="input-large" name="isFreeze">
                <option value="">&nbsp;用户是否冻结</option>
                <option <#if bean.isFreeze?? && bean.isFreeze=true>selected</#if> value="1">已冻结</option>
                <option <#if bean.isFreeze?? && bean.isFreeze=false>selected</#if> value="0">未冻结</option>
            </select>
            
        </div>
        <br>
        
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
                                    <th>ID</th>
                                    <th>姓名</th>
                                    <th>微信</th>
                                    <th>手机号</th>
                                    <th>级别</th>
                                    <th>注册时间</th>
                                    <th>邀请好友数</th>
                                    <th>邀请到好友</th>
                                    <th>邀请到金蜗牛会员数</th>
                                    <th>下载数量</th>
                                    <th>团队人数</th>
                                    <th>优先级</th>
                                    <th>经验值</th>
                                    <th>收到的红包金额</th>
                                    <th>提现金额</th>
                                    <th>账户余额</th>
                                    <th>积分</th>
                                    <th> 会员 </th>
                                    <th>冻结状态</th>
                                    <th>操作</th>
                                </tr>
                                </thead>

                                <tbody>
                               
                                <#if infos?has_content>
                                <#list infos as info>
                                <tr>
                                    <td class="center">
                                        <label>
                                            <input type="checkbox" class="ace J_SelectSub"  name="userIds"    value="${info.id}" >
                                            <span class="lbl"></span>
                                        </label>
                                    </td>
                                    <td>
                                    <a  href="${request.contextPath}/admin/member/userDetail?id=${info.id}" >
                                    ${info.fakeId}  </a> </td>
                                    <td>${info.realName}</td>
                                    <td>${info.weixin}</td>
                                    <td>${info.mobile}</td>
                                    <td>
                                    <#if levels?has_content>
                                    <#list levels as l>
                                      <#if l.id?? && info.userLevelId?? && l.id=info.userLevelId>
                                      ${l.levelName}
                                      </#if>
                                    </#list>
                                    </#if>
                                    </td>
                                    
                                    <td><#if info.createTime?has_content>${info.createTime?string("yyyy-MM-dd")}</#if></td>
                                    <td>${info.inviteFriendAmount}</td>
                                    <td>
                                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success toUrl" type="button" data-toUrl="${request.contextPath}/admin/member/list/p1/?inviteesId=${info.id}">
                                                <i class="icon-stop bigger-120"> 查看 </i>
                                            </button>
                                        </div>
                                    </td>
                                    <td>${info.inviteFriendVipAmount}</td>
                                    <td>${info.downloadMobileAmount}</td>
                                    <td> <@lh.loadUserGroupMount info.id/></td> 
                                    <td>${info.downloadLevel}</td>
                                    <td>${info.experience}</td> 
                                    <td><@lh.getRedPackCount info.id/></td>
                                    <td>${info.reflectRed}</td>
                                    <td>${info.balance}</td>
                                    <td>${info.integration}</td>
                                    
                                     <td><span id="isVip_${info.id}"><#if info.isVip?? && info.isVip==true>金蜗牛<#else>普通</#if></span></td>
                                   
                                    <td><span id="isFreeze_${info.id}"><#if info.isFreeze?? && info.isFreeze==true>冻结<#else>正常</#if></span></td>
                                   
                                    <td>
                                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                            <button class="btn btn-xs btn-success"  style="display:none;">
                                                <i class="icon-stop bigger-120"> 保存 </i>
                                            </button>

                                            <button id="do_isFreeze_${info.id}" type="button" class="btn btn-xs btn-warning isFreeze" data-url="${request.contextPath}/admin/member/isFreezeSave?id=${info.id}" data-status="<#if info.isFreeze?? && info.isFreeze==true>1<#else>0</#if>" data-id="${info.id}">
                                                <i class="icon-copy bigger-120"><#if info.isFreeze?? && info.isFreeze==true>取消<#else>冻结</#if></i>
                                            </button>
                                            
                                            <button id="do_isVip_${info.id}" type="button" class="btn btn-xs btn-warning isVip" data-url="${request.contextPath}/admin/member/save?id=${info.id}"  data-id="${info.id}"	data-status="<#if info.isVip?? && info.isVip==true>1<#else>0</#if>" >
                                                <i class="icon-copy bigger-120">  <#if info.isFreeze?? && info.isVip==true>普通<#else>金蜗牛</#if> </i>
                                            </button>
                                            
                                        </div>
                                    </td>
                                </tr>
                                <#if cates?has_content>
                                <form class="form-inline" id="cateForm_${info.id}">
                                <tr>
                                    <td colspan="19">所属类别：
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
                                        <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                            <button type="button" class="btn btn-xs btn-danger category" category-url="${request.contextPath}/admin/member/save?id=${info.id}" category-toUrl="${request.contextPath}/admin/member/list/p1/" category-id="${info.id}" >
                                                <i class="icon-stop bigger-120"> 删除未选中类别 </i>
                                            </button>
                                        </div>
                                    </td>
                                </tr>
                                </form>
                                </#if>
                                </#list>
                                </#if>
                               
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-xs-6">
                                <input type="text"  id="msg"  name="msg"   />
                                <button class="btn btn-info  toUrl" type="button"  data-toUrl="${request.contextPath}/admin/member/exportExcel2">
            导出
        </button>
                                    <button type="button" class="btn btn-info"  onclick="sendMsg();" >
                                                                                                                                                                   发消息
                                    </button>
                                    <button type="button" class="btn btn-info"  onclick="sendIntegration()">
                                        发积分红包
                                    </button>
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
        <div class="page-footer">
            
                <div class="form-group">
                    <select class="input-large" name="firstCateId"  id="firstCateId">
                        <option value="">类别</option>
                        <#if firstCates??>
                        <#list firstCates as info>
                        <option value="${info.id}"    <#if (bean.firstCateId)??&& bean.firstCateId==info.id> selected</#if>   > ${info.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                    <select class="input-large" name="secondCateId" id="secondCateId">
                        <option value="">级别</option>
                         <#if secondCates??>
                        <#list secondCates as info> 
                        <option value="${info.id}"    <#if (bean.secondCateId)??&& bean.secondCateId==info.id> selected</#if>   > ${info.cateName}</option>
                        </#list>
                        </#if>
                    </select>
                </div>
                <button class="btn btn-sm btn-primary"  type="button" onclick="listAddCateIdFun()" >
                    <i class="icon-edit align-top" ></i>确认给选中的用户添加类别
                </button>
            
            
        </div><!-- /.page-footer -->
        <div class="hr hr32 hr-dotted"></div>
        
        <!-- PAGE CONTENT ENDS -->
    </div><!-- /.col -->

</div><!-- /.row -->
<div class="page-footer">

</div><!-- /.page-footer -->

</div><!-- /.page-content -->
</div><!-- /.main-content -->

<#include "../common/footer.ftl">
<script language="javascript" type="text/javascript" src="${request.contextPath}/resources/admin/js/datepicker/WdatePicker.js"></script>
<script>


function  sendMsg(){
	
	var  userIds = $("input[name='userIds']").serialize();
	
	 $.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/message/replayAllByAdmin/",
 		   data:  userIds +"&msg="+$("#msg").val(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 	 	bootbox.alert("保存成功");
 		    	 	 //	window.location.reload();
 		     }else{
 		   	 	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
	 
}


function  sendIntegration(){
	var  userIds = $("input[name='userIds']").serialize();
	 $.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/member/addIntegration",
 		   data:  userIds +"&integration="+$("#msg").val(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 	 	bootbox.alert("保存成功");
 		    	 	 	window.location.reload();
 		     }else{
 		   	 	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
	 
}



function  listAddCateIdFun(){
	
	 //$(".J_SelectSub:checked").serializeArray()
	 var  secondCateId = $("#secondCateId").val();
	 if(secondCateId == "") {
	 	alert("请选择类别");
	 	return;
	 }	
	 
	var  userIds = $("input[name='userIds']").serialize();
	if(userIds==""){
	    alert("请选择要添加的对象.");
	    return;
	}
	
	 $.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/member/adLlistCate/",
 		   data:  userIds +"&cateId="+secondCateId,
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 	 	bootbox.alert("保存成功");
 		    	 	 	window.location.reload();
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

function addAll(){
console.log($("#cateForm").serialize());
    var  ids = $("input[name='ids']").serialize();
   	$.ajax({
	   type: "POST",
	   url: "${request.contextPath}/admin/member/addAll",
	   data: ids,
	   success: function(data){
	     if(data.isSuccess){
	    	 alert("添加成功");
	    	 location.href = "${request.contextPath}/admin/member/list/p1";
	     }else{
	    	bootbox.alert("添加失败");
	     }
	   },
	   dataType: "json"
	});
}
</script>

</#escape>
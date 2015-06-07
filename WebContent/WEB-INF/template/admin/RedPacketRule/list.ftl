<#escape x as x!"">
<#include "../common/header.ftl">
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">金蜗牛会员红包规则</a>
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
                       <div class="tabbable ">
                        <div class="tab-content">
                            <div class="table-responsive">
                                <div class="dataTables_wrapper">
                                    <table id="J_Table" class="table table-striped table-bordered table-hover J_Table">
                                        <thead>
                                            <tr>
                                                <th colspan="6"><h3>红包发放规则设定：金额1-99元之间，精确到分。</h3></th>  
                                            </tr>
                                        </thead>

                                        <tbody>	
                                            <#if infos?has_content>
                                            <#list infos as info>
                                            
                                            <tr>
                                                <form id="saveForm${info_index}" action="">
                                                    <input class="input-small" type="hidden" id="id" name="id" value="${info.id}"/>
                                                <td>第<input class="input-small" type="text" id="ruleId" name="ruleId" value="${info.ruleId}"/>次红包金额：</td>
                                                <td><input class="input-small" type="text" id="start" name="start" value="${info.start}"/>元-</td>
                                                <td><input class="input-small" type="text" id="end" name="end" value="${info.end}"/>元</td>
                                                <td>
                                                <div class="visible-md visible-lg hidden-sm hidden-xs btn-group">
                                                  <button class="btn btn-xs btn-success" type="button" onclick="RedPacketRuleSave(${info_index})">
                                                    <i class="icon-stop bigger-120"> 保存 </i>
                                                  </button>

                                                  <button  class="btn btn-xs btn-danger J_DeleteTr" type="button" data-value="${request.contextPath}/admin/RedPacketRule/delete/${info.id}"  >
                                                     <i class="icon-copy bigger-120">删除</i>
                                                  </button>
                                                </div>
                                                </td>
                                                </form>     
                                            </tr>  
                                            
                                            </#list>
                                            </#if>
                                            
                                            <tr id="addTr" style="display:none" ><form id='addSaveForm'   ><td>第<input class='input-small' type='text' id='ruleId' name='ruleId' value=''/>次红包金额：</td>
                 							<td><input class='input-small' type='text' id='start' name='start' value=''/>元-</td>
										     <td><input class='input-small' type='text' id='end' name='end' value=''/>元</td>
										     <td>
										     <div class='visible-md visible-lg hidden-sm hidden-xs btn-group'>
										       <button class='btn btn-xs btn-success' type='button' onclick='addSave()'>
										         <i class='icon-stop bigger-120'> 保存 </i>
										       </button>
										       <button class='btn btn-xs btn-danger' type='button' onclick='hidTr()'>
										         <i class='icon-stop bigger-120'> 删除 </i>
										       </button>
										     </div>
										     </td>
										     </form>
										    </tr>
                                            
                                        </tbody>
                                    </table>
                                    <div class="row">
                                        <div class="col-xs-6">

                                        </div>
                                        <div class="col-xs-6">
                                        </div>
                                    </div>
                                    <div class="clearfix">
                                    <div class="col-sm-offset-1 col-sm-10">
                                    <button class="btn btn-info" type="button" onclick="addTr()">
                                        <i class="icon-ok bigger-110"></i>
                                        添加一条发放次数
                                    </button>
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

        </div><!-- /.page-footer -->
    </div><!-- /.page-content -->
</div><!-- /.main-content -->
<#include "../common/footer.ftl">
<script>
//红包规则表单提交
function RedPacketRuleSave(index){
	//alert($('#saveForm'+index).serialize());
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/RedPacketRule/save",
 		   data: $('#saveForm'+index).serialize(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 location.href = "${request.contextPath}/admin/RedPacketRule/list/p1";
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}
//add提交
function addSave(){
    console.log($("#ruleId1").val());
    console.log($("#start1").val());
    console.log($("#end1").val());
     console.log($('#addSaveForm').serialize());
	$.ajax({
 		   type: "POST",
 		   url: "${request.contextPath}/admin/RedPacketRule/save",
 		   data: $('#addSaveForm').serialize(),
 		   success: function(data){
 		     if(data.isSuccess){
 		    	 alert("保存成功");
 		    	 location.href = "${request.contextPath}/admin/RedPacketRule/list/p1";
 		     }else{
 		    	bootbox.alert("保存失败");
 		     }
 		   },
 		   dataType: "json"
 		});
}
  //给表格添加一行
  function addTr(tab, row){
     //获取table最后一行 $("#tab tr:last")
     //获取table第一行 $("#tab tr").eq(0)
     //获取table倒数第二行 $("#tab tr").eq(-2)
    $("#addTr").show();
  }
  function hidTr(){
    console.log("hidden");
    $("#addTr").hide();
  }
</script>
</#escape>
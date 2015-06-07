<#escape x as x!"">
<#include "../common/header.ftl">

      <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
          <span class="menu-text"></span>
        </a>

        <div class="sidebar" id="sidebar">

          

        </div>

        <div class="main-content">
          <div class="breadcrumbs" id="breadcrumbs">


            <ul class="breadcrumb">
              <li>
                <small>
                  <i class="icon-double-angle-right"></i>
                   修改密码
                </small>
              </li>
            </ul><!-- .breadcrumb -->

          </div>

          <div class="page-content">
            <div class="page-header">




            </div><!-- /.page-header -->
      

            <div class="row">
              <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->

              <form class="form-horizontal" role="form" id="saveForm" >

                <div class="form-group">
                  <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span> 原密码：</label>
                  <div class="col-sm-4">
                    <input type="password"  id="oldPassword" name="oldPassword" class="col-xs-12 col-sm-12">
                  </div>
                  <div  id="oldPasswordError" class="help-block col-xs-12 col-sm-reset inline">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span> 新密码：</label>
                  <div class="col-sm-4">
                    <input type="password" id="password" name="password"  class="col-xs-12 col-sm-12">
                  </div>
                  <div id="passwordError" class="help-block col-xs-12 col-sm-reset inline">
                  </div>
                </div>
                <div class="form-group">
                  <label class="col-sm-2 control-label" for="form-field-1"> <span class="red">*</span> 再输一次：</label>
                  <div class="col-sm-4">
                    <input type="password" id="passwordAgain" name="passwordAgain" class="col-xs-12 col-sm-12">
                  </div>
                  <div id="passworAgaindError" class="help-block col-xs-12 col-sm-reset inline">
                  </div>
                </div>

                <div class="clearfix form-actions">
                  <div class="col-sm-offset-1 col-sm-10">
                    <button class="btn btn-info" type="button" onclick="saveFormFun('saveForm','${request.contextPath}/admin/manager/modifyPwd','refresh')">
                    <i class="icon-ok bigger-110"></i>
                    保存
                    </button>

                    
                  </div>
                </div>
              </form>
            
                <div class="hr hr32 hr-dotted"></div>


                <!-- PAGE CONTENT ENDS -->
              </div><!-- /.col -->
            </div><!-- /.row -->


          </div><!-- /.page-content -->
        </div><!-- /.main-content -->


<#include "../common/footer.ftl">

<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.method.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/message.js"></script>
	<style>
			.errorClass{
				color:red;
			}
			
		</style>
		<script type="text/javascript">
		$(document).ready(function(){
	    
			$("#saveForm").validate({
		/* 设置验证规则 */	 
		rules: {
			oldPassword: {
				required: true
			},
			password: {
				required: true
			},
			passwordAgain:{
				required: true,
				equalTo:"#password"
			}
		},
		/* 设置错误信息 */	 
		messages: {
			
		},
		/* 错误信息的显示位置 */	 
		errorPlacement: function(error, element) {
		//	console.log("id:"+element.attr("id") +"--value:"+element.val());
			var errorPlace = element.parent().nextAll("div").first();
			errorPlace.html(error.html()).addClass("errorClass");
		},	 
		/* 验证通过时的处理 */	 
		success: function(lable) {
			lable.remove();
		},
		onkeyup: false
	});
		
		});
		
		
		function saveFormFun(key,url, tourl){
			if(!$("#saveForm").valid())
				return;				
			saveForm_wj(key,url, tourl);
		}
		
		
		
		
		</script>	
		
</#escape>


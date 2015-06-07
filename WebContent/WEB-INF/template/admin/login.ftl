<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Login</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <!-- basic styles -->
    <link href="${request.contextPath}/resources/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/font-awesome.min.css" />

    <!-- ace styles -->

    <!-- page specific plugin styles -->

    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/jquery-ui-1.10.3.custom.min.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/chosen.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/datepicker.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/daterangepicker.css" />

    <!-- fonts -->
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/ace.min.css" />
    <!--[if lte IE 8]>
      <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/ace-ie.min.css" />
    <![endif]-->

    <script src="${request.contextPath}/resources/admin/assets/js/ace-extra.min.js"></script>
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/resources/admin/assets/js/html5shiv.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/respond.min.js"></script>
    <![endif]-->
     <script type="text/javascript">
	var YS = YS || {data:{},util:{}};
	YS.config = {path:{rootPath:"${request.contextPath}"}};
	
	</script>
  </head>

  <body class="login-layout">
    <div class="main-container">
      <div class="main-content">
        <div class="row">
          <div class="col-sm-10 col-sm-offset-1">
            <div class="login-container">
              <div class="center">
                <h1>
                  <i class="icon-leaf green"></i>
                  <span class="red">蜗牛巴巴</span>
                  <span class="white">Login</span>
                </h1>
              </div>

              <div class="space-6"></div>

              <div class="position-relative">
                <div id="login-box" class="login-box visible widget-box no-border">
                  <div class="widget-body">
                    <div class="widget-main">
                      <h4 class="header green lighter bigger">
                        <i class="icon-group blue"></i>
                        管理员登录
                      </h4>

                      <div class="space-6"></div>
                      <p> 信息填写: </p>
                      
                      <!--  ${request.contextPath}/admin/manager/j_spring_security_check
                            ${request.contextPath}/admin/manager/ajax/j_spring_security_check
                       -->
                      <form id="saveForm" method="post" action="" >
                        <fieldset>
                          <label class="block clearfix">
                            <label class="col-sm-3 control-label no-padding-right">用户名</label>
                            <span class="block input-icon input-icon-right">
                              <input id="account" name="account" type="text" class="col-sm-9"  value="${account!''}" />
                              <i class="icon-user"></i>
                            </span>
                            <span id="accountError" class="errorClass" style="color:#cc0000;"></span>
                          </label>
                          <label class="block clearfix">
                            <label class="col-sm-3 control-label no-padding-right" >密码</label>
                            <span class="block input-icon input-icon-right">
                              <input id="password" name="password" type="password"  value="${password!''}" class="col-sm-9" placeholder="" />
                              <i class="icon-lock"></i>
                            </span>
                             <span id="passwordError" class="errorClass"  style="color:#cc0000;"></span>
                          </label>
                          
                          <label class="block clearfix">
                            <label style="margin-bottom: 30px;" class="col-sm-3 control-label no-padding-right">验证码</label>
                            <span class="block">
                              <input id="yzm" name="yzm" type="text" class="" placeholder="" value="${yzm!''}" />
                              <img onclick="changeImg(this)" src="${request.contextPath}/code?1" />
                            </span>
                             <span id="yzmError"  class="errorClass" style="color:#cc0000;"></span>
                          </label>
                            
                          </label>

                          <div class="space-24"></div>

                          <div class="clearfix">
                            <button id="loginButton"  onclick="saveFun('${request.contextPath}/admin/manager/ajax/j_spring_security_check','${request.contextPath}/admin/manager/index')"  type="button" class="width-65 pull-right btn btn-sm btn-success" >
                 
                             	 登录
                              <i class="icon-arrow-right icon-on-right"></i>
                            </button>
                          </div>
                        </fieldset>
                      </form>
                    </div>

                  </div><!-- /widget-body -->
                </div><!-- /signup-box -->
				

              </div><!-- /position-relative -->
            </div>
          </div><!-- /.col -->
        </div><!-- /.row -->
      </div>
    </div><!-- /.main-container -->




    <!-- basic scripts -->

    <!--[if !IE]> -->
    <script type="text/javascript">
      window.jQuery || document.write("<script src='${request.contextPath}/resources/admin/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
    </script>
    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
      window.jQuery || document.write("<script src='js/jquery-1.10.2.min.js'>"+"<"+"/script>");
    </script>
    <![endif]-->

    <script type="text/javascript">
      if("ontouchend" in document) document.write("<script src='${request.contextPath}/resources/admin/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
    </script>

    <script src="${request.contextPath}/resources/admin/assets/js/bootstrap.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/typeahead-bs2.min.js"></script>

    <!-- page specific plugin scripts -->

    <!--[if lte IE 8]>
      <script src="js/excanvas.min.js"></script>
    <![endif]-->


    <script src="${request.contextPath}/resources/admin/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/chosen.jquery.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/jquery.knob.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/jquery.autosize.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/jquery.maskedinput.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/bootstrap-tag.min.js"></script>

    <!-- ace scripts -->

    <script src="${request.contextPath}/resources/admin/assets/js/ace-elements.min.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/ace.min.js"></script>


    <script type="text/javascript">
      jQuery(function($) {
        
        $('#id-input-file-1 , #id-input-file-2').ace_file_input({
          no_file:'No File ...',
          btn_choose:'Choose',
          btn_change:'Change',
          droppable:false,
          onchange:null,
          thumbnail:false //| true | large
          //whitelist:'gif|png|jpg|jpeg'
          //blacklist:'exe|php'
          //onchange:''
          //
        });
        
        $('#id-input-file-3').ace_file_input({
          style:'well',
          btn_choose:'Drop files here or click to choose',
          btn_change:null,
          no_icon:'icon-cloud-upload',
          droppable:true,
          thumbnail:'small'//large | fit
          //,icon_remove:null//set null, to hide remove/reset button
          /**,before_change:function(files, dropped) {
            //Check an example below
            //or examples/file-upload.html
            return true;
          }*/
          /**,before_remove : function() {
            return true;
          }*/
          ,
          preview_error : function(filename, error_code) {
            //name of the file that failed
            //error_code values
            //1 = 'FILE_LOAD_FAILED',
            //2 = 'IMAGE_LOAD_FAILED',
            //3 = 'THUMBNAIL_FAILED'
            //alert(error_code);
          }
      
        }).on('change', function(){
        });
        
        //dynamically change allowed formats by changing before_change callback function
        $('#id-file-format').removeAttr('checked').on('change', function() {
          var before_change
          var btn_choose
          var no_icon
          if(this.checked) {
            btn_choose = "Drop images here or click to choose";
            no_icon = "icon-picture";
            before_change = function(files, dropped) {
              var allowed_files = [];
              for(var i = 0 ; i < files.length; i++) {
                var file = files[i];
                if(typeof file === "string") {
                  //IE8 and browsers that don't support File Object
                  if(! (/\.(jpe?g|png|gif|bmp)$/i).test(file) ) return false;
                }
                else {
                  var type = $.trim(file.type);
                  if( ( type.length > 0 && ! (/^image\/(jpe?g|png|gif|bmp)$/i).test(type) )
                      || ( type.length == 0 && ! (/\.(jpe?g|png|gif|bmp)$/i).test(file.name) )//for android's default browser which gives an empty string for file.type
                    ) continue;//not an image so don't keep this file
                }
                
                allowed_files.push(file);
              }
              if(allowed_files.length == 0) return false;
      
              return allowed_files;
            }
          }
          else {
            btn_choose = "Drop files here or click to choose";
            no_icon = "icon-cloud-upload";
            before_change = function(files, dropped) {
              return files;
            }
          }
          var file_input = $('#id-input-file-3');
          file_input.ace_file_input('update_settings', { 'before_change':before_change, 'btn_choose': btn_choose, 'no_icon':no_icon})
          file_input.ace_file_input('reset_input');
        });
      
      });
            function show_box(id) {
       jQuery('.widget-box.visible').removeClass('visible');
       jQuery('#'+id).addClass('visible');
      }
    </script>
 <script src="${request.contextPath}/resources/admin/myJs/common.js"></script>
 


<style>
	    		.errorClass { color:red}
	    </style>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.form.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/jquery.validate.method.js"></script>
<script type="text/javascript" src="${request.contextPath}/resources/admin/js/jquery/plugins/form/message.js"></script>

<script>
function saveFun(url, toUrl) {
	
	if(!$("#saveForm").valid())
		return;
/*	$("#saveForm").submit(); */	
	save(url, toUrl); 
}

	$(function(){
	
	$("#saveForm").validate({
		/* 设置验证规则 */	 
		rules: {
			account: {
				required: true
			},
			password: {
				required: true
			}
			,yzm :{
				required: true
			}
			
		},
		/* 错误信息的显示位置 */	 
		errorPlacement: function(error, element) {
			log("id:"+element.attr("name")+"---val:"+element.val());
			// 错误信息提示  有2层 是因为加class 会自动包了一层
			var errorPlace = element.parent().nextAll(".errorClass").first();
			errorPlace.html(error.html()).addClass("errorClass");
			errorPlace = element.nextAll(".errorClass").first();
			errorPlace.html(error.html()).addClass("errorClass");
		},	 
		/* 验证通过时的处理 */	 
		success: function(lable) {
			lable.remove();
		},
		onkeyup: false
	});
	})

</script> 

  </body>
</html>

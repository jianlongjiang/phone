<#escape x as x!"">
<#import "../../common/common.ftl" as lh>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>控制台</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- basic styles -->
    <link href="${request.contextPath}/resources/admin/assets/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/font-awesome.min.css" />
    <!-- page specific plugin styles -->
    <!-- ace styles -->
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/ace.min.css" />
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/qfc-min.css" />
    <!--[if lte IE 8]>
    <link rel="stylesheet" href="${request.contextPath}/resources/admin/assets/css/ace-ie.min.css" />
    <![endif]-->
    <!-- ace settings handler -->
    <!--[if !IE]> -->
	<script src="${request.contextPath}/resources/admin/assets/js/jquery-2.0.3.min.js"></script>
	<!-- <![endif]-->
	<!--[if IE]>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<![endif]-->
	<!--[if !IE]> -->
	<!-- <![endif]-->
	<!--[if IE]>
	<script type="text/javascript">
	    window.jQuery || document.write("<script src='${request.contextPath}/resources/admin/assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
	</script>
	<![endif]-->
    <script src="${request.contextPath}/resources/admin/assets/js/ace-extra.min.js"></script>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${request.contextPath}/resources/admin/assets/js/html5shiv.js"></script>
    <script src="${request.contextPath}/resources/admin/assets/js/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
	    var LH = LH || {data:{},util:{}};
		LH.config = {path:{rootPath:"${request.contextPath}"}}
		
		var YS = YS || {data:{},util:{}};
	    YS.config = {path:{rootPath:"${request.contextPath}"}}
	</script>
</head>
<body>
<div class="navbar navbar-default" id="navbar">
    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf"></i>
                    后台管理系统
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->
        <!--
        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav">
                <li class="light-blue">
                    <a  href="${request.contextPath}/admin/manager/logout" >
                <span class="user-info" >
                 退出
                </span>
                    </a>
                </li>
            </ul>--><!-- /.ace-nav -->
    <!--</div>--><!-- /.navbar-header -->
         <div class="navbar-header pull-right" role="navigation">
          <ul class="nav ace-nav">
            <li class="light-blue">
              <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                <span class="user-info">
                  <small>欢迎光临,</small>
                  管理员${account}
                </span>

                <i class="icon-caret-down"></i>
              </a>

              <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                

                <li>
                  <a href="${request.contextPath}/admin/manager/toModifyPwd/">
                    <i class="icon-user"></i>
                    修改密码
                  </a>
                </li>

                <li class="divider"></li>

                <li>
                  <a onclick="return confirm('Are you sure！')"  href="${request.contextPath}/admin/manager/logout">
                    <i class="icon-off"></i>
                    退出
                  </a>
                </li>
              </ul>
            </li>
          </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
      </div><!-- /.container -->
    </div>
       
    </div><!-- /.container -->
</div>
<div class="main-container" id="main-container">
<div class="main-container-inner">
<a class="menu-toggler" id="menu-toggler" href="#">
    <span class="menu-text"></span>
</a>
<div class="sidebar" id="sidebar">
    <ul class="nav nav-list">
    			
    		<#if ((adminUser.toUrlMap['手机分类'])!0) ==1 ||  ((adminUser.toUrlMap['手机号码'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/mobileinfo') gt -1) 
            || (request.requestUri?index_of('admin/mobilecate') gt -1)
            > class="active" </#if> >
            <a href="#" class="dropdown-toggle">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 手机 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
            		<#if ((adminUser.toUrlMap['手机分类'])!0) ==1 >
                <li <#if (request.requestUri?index_of('admin/mobilecate/oneList') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/mobilecate/oneList/p1/">
                        <i class="icon-double-angle-right"></i>
                        手机分类
                    </a>
                </li>
                </#if>
                	<#if  ((adminUser.toUrlMap['手机号码'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/mobileinfo/list') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/mobileinfo/list/p1/">
                        <i class="icon-double-angle-right"></i>
                        号码列表
                    </a>
                </li>
                </#if>
            </ul>
        </li>
        </#if>
        
       	<#if  ((adminUser.toUrlMap['用户列表'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/member/') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/member/list/p1/">
                <i class="icon-text-width"></i>
                <span class="menu-text"> 用户列表 </span>
            </a>
        </li>
        </#if>
        
        
        	<#if  ((adminUser.toUrlMap['经验规则设置'])!0) ==1 || ((adminUser.toUrlMap['等级设置'])!0) ==1 >
        <li <#if (request.requestUri?index_of('admin/config/experience') gt -1) 
            || (request.requestUri?index_of('admin/userlevel') gt -1)
            > class="active" </#if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 经验规则设置 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
            	<#if  ((adminUser.toUrlMap['经验规则设置'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/config/experience') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/config/experience/">
                        <i class="icon-double-angle-right"></i>
                        经验规则设置
                    </a>
                </li>
                </#if>
                
                	<#if  ((adminUser.toUrlMap['等级设置'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/userlevel') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/userlevel/list/p1/">
                        <i class="icon-double-angle-right"></i>
                        等级设置
                    </a>
                </li>
                </#if>
            </ul>
        </li>
        </#if>
        
        	<#if  ((adminUser.toUrlMap['积分规则设置'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/config/integral') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/config/integral/">
                <i class="icon-list"></i>
                <span class="menu-text"> 积分规则设置 </span>
            </a>
        </li>
        </#if>
        
        	<#if  ((adminUser.toUrlMap['红包规则'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/RedPacketRule') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/RedPacketRule/list/p1">
                <i class="icon-edit"></i>
                <span class="menu-text"> 金蜗牛会员红包规则 </span>
            </a>
        </li>
        </#if>
        
        
        	<#if  ((adminUser.toUrlMap['新闻列表'])!0) ==1 ||  ((adminUser.toUrlMap['新闻类别'])!0) ==1 
        	|| ((adminUser.toUrlMap['新闻添加'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/newsinfo/') gt -1) 
            || (request.requestUri?index_of('admin/newscate') gt -1)
            > class="active" </#if>>
            <a href="#" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 新闻列表 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
            			<#if  ((adminUser.toUrlMap['新闻列表'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/newsinfo/list/') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/newsinfo/list/p1/">
                        <i class="icon-double-angle-right"></i>
                        新闻列表
                    </a>
                </li>
                </#if>
                
                	<#if  ((adminUser.toUrlMap['新闻类别'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/newscate') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/newscate/list/p1/">
                        <i class="icon-double-angle-right"></i>
                        分类
                    </a>
                </li>
                </#if>
                
                	<#if  ((adminUser.toUrlMap['新闻添加'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/newsinfo/add') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/newsinfo/add">
                        <i class="icon-double-angle-right"></i>
                         添加新闻
                    </a>
                </li>
                </#if>
            </ul>
        </li>
        </#if>
        
        	<#if  ((adminUser.toUrlMap['留言管理'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/message') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/message/list/p1/">
                <i class="icon-shopping-cart"></i>
                <span class="menu-text"> 回复留言 </span>
            </a>
        </li>
        </#if>
        
        	<#if  ((adminUser.toUrlMap['邀请好友'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/config/invite') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/config/invite">
                <i class="icon-edit"></i>
                <span class="menu-text"> 邀请好友 </span>
            </a>
        </li>
        </#if>
        
        
        	<#if  ((adminUser.toUrlMap['金蜗牛会员'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/config/goldensnail') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/config/goldensnail">
                <i class="icon-shopping-cart"></i>
                <span class="menu-text"> 金蜗牛会员 </span>
            </a>
        </li>
        </#if>
        
        	<#if  ((adminUser.toUrlMap['金蜗牛会员管理'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/config/manage') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/config/manage">
                <i class="icon-file-alt"></i>
                <span class="menu-text"> 金蜗牛会员管理专区 </span>
            </a>
        </li>
        </#if>
        
        <#if  ((adminUser.toUrlMap['权限管理'])!0) ==1>
        <li 	<#if (request.requestUri?index_of('admin/manager') gt -1) 
            || (request.requestUri?index_of('admin/role') gt -1) 
            ||  (request.requestUri?index_of('admin/resources') gt -1)  > 
            class="open" </#if> >
             <a  class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 权限管理 </span>

                <b class="arrow icon-angle-down"></b>
              </a>
              
             <ul class="submenu"  <#if (request.requestUri?index_of('admin/manager') gt -1) 
            || (request.requestUri?index_of('admin/role') gt -1) 
            ||  (request.requestUri?index_of('admin/resources') gt -1)  > 
            style="display: block;" </#if>  >
                
                <li class="<#if request.requestUri?index_of('/admin/manager/list') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/manager/list/p1">
                    <i class="icon-double-angle-right"></i>
                    用户列表
                  </a>
                </li>
                
                <li class="<#if request.requestUri?index_of('/admin/manager/new') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/manager/new">
                    <i class="icon-double-angle-right"></i>
                    用户添加
                  </a>
                </li>
                
                <li class="<#if request.requestUri?index_of('/admin/role/list') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/role/list/p1">
                    <i class="icon-double-angle-right"></i>
                    职务列表
                  </a>
                </li>

                <li class="<#if request.requestUri?index_of('/admin/role/add') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/role/new">
                    <i class="icon-double-angle-right"></i>
                    职务添加
                  </a>
                </li>
                
                <li class="<#if request.requestUri?index_of('/admin/resources/list') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/resources/list/p1">
                    <i class="icon-double-angle-right"></i>
                     功能列表
                  </a>
                </li>
                
                  <li class="<#if request.requestUri?index_of('/admin/resources/new') != -1 > active</#if>">
                  <a href="${request.contextPath}/admin/resources/new">
                    <i class="icon-double-angle-right"></i>
                     功能新增
                  </a>
                </li>
              </ul>
              
        </li>
        </#if>
        
        
        <#if  ((adminUser.toUrlMap['打款管理'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/cashUserLog/list/p') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/cashUserLog/list/p1">
                <i class="icon-picture"></i>
                <span class="menu-text"> 打款管理 </span>
            </a>
        </li>
        </#if>  
        
        <#if ((adminUser.toUrlMap['蜗牛巴巴统计页面'])!0) ==1 ||  ((adminUser.toUrlMap['蜗牛巴巴会员统计页面'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/statistics/list') gt -1) 
            || (request.requestUri?index_of('admin/statistics/vipList') gt -1)
            > class="active" </#if> >
            <a href="#" class="dropdown-toggle">
                <i class="icon-dashboard"></i>
                <span class="menu-text"> 统计管理 </span>
                <b class="arrow icon-angle-down"></b>
            </a>
            <ul class="submenu">
            		<#if ((adminUser.toUrlMap['蜗牛巴巴统计页面'])!0) ==1 >
                <li <#if (request.requestUri?index_of('admin/statistics/list') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/statistics/list/p1/">
                        <i class="icon-double-angle-right"></i>
                        蜗牛巴巴统计页面
                    </a>
                </li>
                </#if>
                	<#if  ((adminUser.toUrlMap['蜗牛巴巴会员统计页面'])!0) ==1>
                <li <#if (request.requestUri?index_of('admin/statistics/vipList') gt -1)> class="active"</#if>>
                    <a href="${request.contextPath}/admin/statistics/vipList/p1/">
                        <i class="icon-double-angle-right"></i>
                        蜗牛巴巴会员统计页面
                    </a>
                </li>
                </#if>
            </ul>
        </li>
        </#if>
       
       
        <#if  ((adminUser.toUrlMap['必备下载号码'])!0) ==1>
        <li <#if (request.requestUri?index_of('admin/mobileinfo/bibeiList/p') gt -1)> class="active"</#if>>
            <a href="${request.contextPath}/admin/mobileinfo/bibeiList/p1/" >
                <i class="icon-list-alt"></i>
                <span class="menu-text"> 必备下载号码 </span>
            </a>
        </li>
        </#if>
        
        
    </ul><!-- /.nav-list -->
    <div class="sidebar-collapse" id="sidebar-collapse">
        <i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
    </div>
</div>
 </#escape>     
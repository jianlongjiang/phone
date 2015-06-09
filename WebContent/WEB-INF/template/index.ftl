<#escape x as x!"">
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>新闻详情</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/new/css/index.css"> 
     </head>
    <body>
       <div class="container">
         <header>
            <h1>新闻详情</h1>
        </header>
        <div class="content-main">
            <div class="panel" data-title="新闻详情" >
            <div class="content-body">
                <p class="con-title">${info.title}</p>
                <p class="author">
                作者：
                <span>${info.author}</span>  
                <span><#if info.createTime?has_content>${info.createTime?string("yyyy-MM0-dd HH:mm:ss")}</#if></span>
                </p>
                <img src="${request.contextPath}/resources/admin/store/${info.image}">
                <div class="panel-content">
                    <p class="panel-text" style="text-indent:2em;">${info.newsDesc}</p>
                </div>
            </div>
            </div>
        </div>
     <div class="con-cor">
     <#if ios>
     <a class="download">
            <img src="${request.contextPath}/resources/new/img/logo.png" />
            <div class="btn">去下载</div>
        </a>
     <#else>
     	<a class="download" href="${request.contextPath}/resources/admin/store/MainActivity-release.apk">
            <img src="${request.contextPath}/resources/new/img/logo.png" />
            <div class="btn">去下载</div>
        </a>
     </#if>   
     </div>
<!--        end container-->
       </div>        
    </body>
</html>
</#escape>
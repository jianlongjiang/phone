<#escape x as x!"">
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>我的蜗牛巴巴</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
        <link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/new/css/index.css"> 
     </head>
    <body>
        <div class="user_container">
            <div class="thum">
                <img src="${request.contextPath}/resources/new/img/${info.more2}" />
            </div>
            <ul class="user">
                <li class="clearfix">
                    <div class="label">昵称：</div>
                    <div class="cont">${info.userName}</div>
                </li>
                <li class="clearfix">
                    <div class="label">手机：</div>
                    <div class="cont">${info.mobile}</div>
                </li>
                <li class="clearfix">
                    <div class="label">级别：</div>
                    <div class="cont">${levelName}</div>
                </li>
                <li class="clearfix">
                    <div class="label">经验：</div>
                    <div class="cont">${info.experience}</div>
                </li>
                <li class="clearfix">
                    <div class="label">下载总量：</div>
                    <div class="cont">${info.downloadMobileAmount}</div>
                </li>
                <li class="clearfix">
                    <div class="label">积分：</div>
                    <div class="cont">${info.integration}</div>
                </li>
            </ul>
        </div>
        <a class="download">
            <img src="${request.contextPath}/resources/new/img/logo.png" />
            <div class="btn">去下载</div>
        </a>
    </body>
</html>
</#escape>
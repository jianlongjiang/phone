<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/style.css">
</head>
<body style="color: #333333;">
<div id="#afui" class="ios7">
    <div id="content">
        <div class="panel black-bg" title="金蜗牛" id="query-gold-member" data-footer="common_footer">
            <img src="${request.contextPath}/resources/image/gold-member01.png" width="100%">
            <div style="position: relative;">
                <img src="${request.contextPath}/resources/image/gold-member02.png" width="100%">
                <div class="gold-member02">
                    <p><span>证号：</span><span> ${(info.fakeId)!''} </span></p>
                    <p><span>手机号：</span><span> ${(info.mobile)!''}</span></p>
                    <p><span>微信号：</span><span> ${(info.weixin)!''}</span></p>
                    <p><span>团队人数：</span><span> ${(groupCount)!''}</span></p>
                </div>
            </div>
            <div style="position: relative;">
                <img src="${request.contextPath}/resources/image/gold-member03.png" width="100%">
                <div class="gold-member03">
                    <p><span>创业宣言：</span><span> ${(userMore.xuanYan)!''}</span></p>
                </div>
            </div>
            <div style="position: relative">
                <img src="${request.contextPath}/resources/image/gold-member04.png" width="100%">
                <div class="gold-member04">
                    <p><span>发证日期：</span><span> <#if (info.vipTime)??>  ${(info.vipTime)?string("yyyy-MM-dd")}  </#if></span></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
    <link rel="stylesheet" type="text/css" href="${request.contextPath}/resources/css/style.css">
</head>
<body>
<div id="#afui" class="ios7">
    <div id="content">
        <div class="panel w-bg" id="team-manage">
            <div class="flexbox team-btn">							
                <a href="#" class="flexbox-item right-line"><img src="${request.contextPath}/resources/image/team-hand.png" height="50px"><p>推荐人</p><p>${inviteesNo!''} </p></a>
                <a href="#" class="flexbox-item right-line"><img src="${request.contextPath}/resources/image/team-downarrow.png" height="50px"><p>下载数量</p><p>${downCount!''}</p></a>
                <a href="#" class="flexbox-item"><img src="${request.contextPath}/resources/image/team-red.png" height="50px"><p>积分数</p><p>${allScore!''}</p></a>
            </div>
            <img src="${request.contextPath}/resources/image/cut-off.png" class="cut-off">
            <ul>
                <li style="background-color: #c8c8ca;margin-right: 1%;">
                    <img src="${request.contextPath}/resources/image/team-number.png" width="50px">
                    <p class="team-title" style="color:#7c6651;">今日增加团队人数</p>
                    <p class="team-num" style="color: #7c6651;">${dayGroupCount!''}人</p>
                </li>
                <li style="background-color: #494751;">
                    <img src="${request.contextPath}/resources/image/team-w-red.png" width="50px">
                    <p class="team-title" style="color: #FFFFFF;">今日收到红包</p>
                    <p class="team-num" style="color: #FFFFFF;">${dayInvitees!''}人</p>
                </li>
                <li style="background-color: #f9f9fb;margin-right: 1%;">
                    <img src="${request.contextPath}/resources/image/team-people.png" width="50px">
                    <p class="team-title" style="color: #000000;">今天共邀请了</p>
                    <p class="team-num" style="color: #afafaf;">${dayInviteesCount!''}人</p>
                </li>
                <li style="background-color: #f9f9fb;">
                    <img src="${request.contextPath}/resources/image/team-snail.png" width="50px">
                    <p class="team-title" style="color: #000000;">今日邀请金蜗牛</p>
                    <p class="team-num" style="color: #afafaf;">${dayVipCount!''}人</p>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="flexbox team-nav">
                <a href="#" class="flexbox-item"><p>我的好友&nbsp;<span>${(allInviteesCount)!''}</span></p></a>
                <a href="#" class="flexbox-item second"><p>我的团队&nbsp;<span>${grounpCount!''}</span></p></a>
                <a href="#" class="flexbox-item"><p>金蜗牛&nbsp;<span>${vipCount!''}</span></p></a>
            </div>
            <table>
                <thead>
                <tr>
                    <th width="16%">金蜗牛</th>
                    <th width="17%">手机号</th>
                    <th width="16%">昵称</th>
                    <th width="16%">积分</th>
                    <th width="17%">邀请人数</th>
                    <th width="17%">邀请金蜗牛数</th>
                </tr>
                </thead>
                <tbody>
                
                <#if friends??>
                <#list friends as friend>
                <tr>
                    <td>
                    		<#if friend.isVip>
                        <input type="radio"><img src="image/user-gold-snail.png" width="30px">
                        <#else>
                        	<input type="radio"><img src="image/user-snail.png" width="30px">
                        </#if>
                    </td>
                    <td>${friend.mobile} </td>
                    <td>${(friend.userName)!''} </td>
                    <td>${friend.integration} </td>
                    <td>${friend.inviteFriendVipAmount} </td>
                    <td>${friend.inviteFriendVipAmount} </td>
                </tr>
              	</#list>
              	</#if>
                </tbody>
            </table>
            <div class="container">
            		<!--
                <a href="#integral-red" class="submit-btn" data-transition="fade">点击查询</a>
                -->
            </div>
        </div>
    </div>
</div>
</body>
</html>
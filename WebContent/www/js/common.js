var isdrag = false;
var tx, x;
var key_user = '';
// 通讯录保存
var phoneIndex;
var phoneTemp;

function downNewsVersion() {
    //todo
    //window.location.href = BASE_IMAGE + "lhVel.apk";
    //window.open( "https://itunes.apple.com/cn/app/id983208990?l=zh&ls=1&mt=8", '_system');
    openAppStore();
    //window.open( BASE_IMAGE+"woniubaba.apk", '_system');
    //alert("替换下载链接")
    console.log("下载最新的Phone.app");
}

function openAppStore(){
    window.open("https://itunes.apple.com/cn/app/wechat/id983208990?mt=8&uo=4", "_system");
}


//更新
function checkVersionFun(firstCheck) {
    appAjax({
        type: "POST",
        url: "app/version/checkVersion",
        data: {
            "version": version
        },
        success: function(data) {
            if (data.isSuccess) {
                var dto = data.result;
                if (dto.updateStatus == 0) {
                    if (isBlack(firstCheck)) alert("当前是最新版本");
                } else if (dto.updateStatus == 1) {
                    $("#app_newVersion").html(dto.version);
                    $.afui.popup({
                        title: "更新",
                        message: "可选:" + dto.updateMsg,
                        cancelText: "不更新",
                        cancelCallback: function() {
                            console.log("cancelled");
                        },
                        doneText: "更新",
                        doneCallback: function() {
                            console.log("Done for!");
                            downNewsVersion();
                        },
                        cancelOnly: false
                    });
                } else {
                    $("#app_newVersion").html(dto.version);
                    $.afui.popup({
                        title: "更新",
                        message: "必更:" + dto.updateMsg,
                        cancelText: "退出",
                        cancelCallback: function() {
                            console.log("cancelled");
                            existApp();
                            showPage("login");
                        },
                        doneText: "更新",
                        doneCallback: function() {
                            console.log("Done for!");
                            downNewsVersion();
                        },
                        cancelOnly: false
                    });
                }

            } else {
                alert(data.message);
            }
        },
        dataType: "json"
    });
}
$(function() {
    $("#app_checkVersion").bind("click", function() {
        checkVersionFun(false);
    });
    //$("#app_checkVersion").click();
    checkVersionFun(true);

});


//微信分享
function weixinshare(event, title, url) {
    //alert(imgbasecode);
    var id = $(event).attr("data-value");
    var image = $(event).attr("data-image");
    //  WeChat
    //    .share('文本', WeChat.Scene.session, function () {
    //           console.log('分享成功~');
    //           }, function (reason) {
    //           console.log(reason);
    //           });
    //
    // 或者 (更多选项见后)."data:image/png;base64,
    WeChat
        .share({
            //type: WeChat.ShareType.image,
            title: title,
            description: title,
            thumbData: "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", //imgbasecode,
            url: url + id
        }, WeChat.Scene.timeline, function() {
            console.log("分享成功！");
        }, function(reason) {
            // 分享失败
            alert("分享失败");
        });
}

//红包发送
function redpack(event, toUserId) {
    var jifen = $("#integration-span").html();
    $.afui.popup({
        title: "积分红包",
        message: "您共有积分" + jifen + "，请填写红包积分数量: <input type='text' class='af-ui-forms J_jifen_minu'>",
        cancelText: "取消",
        cancelCallback: function() {},
        doneText: "确认发送",
        doneCallback: function() {
            //          navigator.notification.alert('You are the winner!',function(){},'Game Over','Done');
            var pay_jifen = $(".J_jifen_minu").val();
            if (parseInt(pay_jifen) > parseInt(jifen)) {
                alert("积分不足！");
            } else {
                appAjax({
                    url: "app/userinfo/jifenhongbao",
                    data: {
                        "toUserId": toUserId,
                        "jifen": parseInt(pay_jifen)
                    },
                    success: function(data) {
                        if (data.isSuccess) {
                            $("#integration-span").html(parseInt(jifen) - parseInt(pay_jifen));
                            var th = $(event).parent().prev();
                            th.html(parseInt(th.html()) + parseInt(pay_jifen));
                            alert("积分赠送成功!");
                        } else {
                            alert(data.msg);
                        }
                    }
                });
            }
        },
        cancelOnly: false
    });
}




    //找回密码
$(function() {
    $("#sendSmsCodeButton2").bind("click", function() {
        var id = "findPwdForm";
        var mobile = value(id, 'mobileNo');

        if (!checkMobile(mobile)) {
            alert("请填写手机号码");
            //          $("#mobileError").html("请填写手机号码");
            return;
        }
        appAjax({
            type: "POST",
            url: "userinfo/sendSmsCode",
            data: {
                "mobile": mobile,
                "type": "findPwd"
            },
            success: function(data) {
                if (data.isSuccess) {
                    saveAppToken(data.result);
                    //  $("#mobileError").html("");
                    $("#sendSmsCodeButton2").hide();
                    $("#timeShow2").html(180);
                    timeThread2();
                } else {
                    alert(data.message);
                }
            },
            dataType: "json"
        });
    });
});

function timeThread2() {
    setTimeout(function() {
        var time = parseInt($("#timeShow").html());
        if (time > 0) {
            $("#timeShow2").html(time - 1);
            timeThread();
        } else {
            $("#timeShow2").html("");
            $("#sendSmsCodeButton2").show();
        }
    }, 1000);
}



/**
 * 找回mm
 */
function findPwdFormFun() {
    if (findPwdForm.valid() == true) {
        appAjax({
            type: "POST",
            url: "aap/userinfo/findPwd",
            success: function(data) {
                if (data.isSuccess) {
                    saveAppToken(data.result);
                    showPage("login");
                } else {
                    alert(data.message);
                }
            },
            dataType: "json"
        }, "findPwdForm");
    }
};

$(function() {
    findPwdForm = Validator("findPwdForm", {
        errPar: 'li',
        together: true, //显示一条错误，还是同时显示多条错误，默认false(只显示一条)
        errShow: function(msgs) {
            $.afui.popup({
                message: msgs[0].msg,
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: truef
            });
        }, //错误显示样式，默认为alert，支持字符串(alert,single,multiple),自定义function(string || array())
        timely: true
            //开启实时验证,默认关闭
    });
    findPwdForm.addRule([
        ["mobileNo", "required", '不能为空'],
        ["mobileNo", "mobile", '手机格式不对'],
        ["password", "required", '密码不能为空'],
        ["passwordConfirm", "required", '密码验证不能为空'],
        ["passwordConfirm", "equal=password", '密码不一致'],
        ["yzm", "required", '不能为空'],
    ]);

});



var _modifyPwdForm = null;
$(function() {
    _modifyPwdForm = Validator("modifyForm", {
        errPar: 'p',
        together: true, //显示一条错误，还是同时显示多条错误，默认false(只显示一条)
        errShow: function(msgs) {
            //          alert(msgs);
            //          for(var i in msgs){
            //              $.afui.popup({
            //                  message: msgs[i].msg,
            //                  suppressTitle: true,
            //                  cancelText:"知道了",
            //                  cancelOnly:true
            //              });
            //              //alert()
            //          }
            $.afui.popup({
                message: msgs[0].msg,
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });

        }, //错误显示样式，默认为alert，支持字符串(alert,single,multiple),自定义function(string || array())
        timely: false
            //开启实时验证,默认关闭
    });
    _modifyPwdForm.addRule([
        ["password", "required", '原密码不能为空'],
        ["newPwd", "required", '新密码不能为空'],
        ["newPwdConfrim", "required", '新密码验证不能为空'],
        ["newPwdConfrim", "equal=newPwd", '密码不一致']
    ]);
});



function modifyPwdFrom() {
    console.log(_modifyPwdForm);
    if (_modifyPwdForm.valid() == true) {
        appAjax({
            url: "userinfo/modifyPwd",
            //data:{"thirdId":thirdId, "loginType":loginType},
            success: function(data) {
                if (data.isSuccess) {
                    alert("data.message");
                    showPage("user-center");
                } else {
                    alert(data.message);
                }
            }
        }, "modifyForm");
    }
}


function tuichu() {
    $("#to-user-center-btn").attr('href', '#login');
    $("#loginButoonMsg").html("登入");
    showPage("login");
    appAjax({
        url: "userinfo/exit",
        //data:{"thirdId":thirdId, "loginType":loginType},
        success: function(data) {
            if (data.isSuccess) {
                clearAll();
                //showPage("user-center");
            } else {
                alert(data.message);
            }
        }
    });

}
var FTS;
var ajaxLoaded;
$(function() {
    ajaxLoaded = {
        sysMessage: false,
        userMessage: false,
        newsNav: false
    };
    //  http://192.168.1.104:8082/app//userinfo/search/?mobile=15257149665
    var imageUrl = BASE_IMAGE;
    FTS = {};
    /*
     $("#about").on("panelload",function(){
     window.localStorage.appToken = "";
     });
     */

    //检查登录
    if (!checkLogin()) {
        $("#to-user-center-btn").attr('href', '#login');
        $("#loginButoonMsg").html("登入");
    } else {
        $("#to-user-center-btn").attr('href', '#user-center');
        $("#loginButoonMsg").html("个人中心");
    }

    //  function  checkLogin(){
    //      return !!window.localStorage.appToken;
    //  }

    //消息页btn,默认active为系统消息
    $("#sys-message-btn").click(function() {
        $("#user-message-btn").removeClass("active");
        $(this).addClass("active");
        $("#sys-message-div").show();
        $("#user-message-div").hide();
    });
    $("#user-message-btn").click(function() {
        $("#sys-message-btn").removeClass("active");
        $(this).addClass("active");
        $("#user-message-div").show();
        $("#sys-message-div").hide();
    });
    $("#sys-message-btn").trigger("click");

    //个人中心积分btn,默认为隐藏
    $("#jifen-btn").click(function() {
        $(".jifen-btn-show").toggle();
        $("#user-center table").toggle();
    });
    $(".jifen-btn-show").hide();
    $("#user-center table").hide();

    //个人中心页
    $("#user-center").on("panelload", function() {
        appAjax({
            type: "POST",
            url: "userinfo/detail/1",
            context: $("#user-center"),
            success: function(temp) {
                if (temp.isSuccess) {
                    var userObj = temp.result.userinfo;
                    set("user", userObj);
                    $("#mobile-number-input").val(userObj.mobile);
                    $("#nickname-input").val(userObj.userName);
                    $("#rank-span").text(temp.result.levelName);
                    $("#exp-span").text(userObj.experience);
                    $("#weixin-span").text(userObj.weixin);
                    $("#dl-amount-span").text(userObj.downloadMobileAmount);
                    $("#integration-span").text(userObj.integration);
                    $("#recommend-span").text(temp.result.inviteesName);
                    $("#reg-time-span").text(userObj.createTime);
                    $(".J_weixin_share").attr("data-value", userObj.id);
                    $(".J_weixin_share").attr("data-image", imageUrl + userObj.more2);
                    $(".J_userinfo_jpg").attr("src", USER_INFO_IMAGE + userObj.more2);
                    if (!!temp.result.invitees)
                        $(".jifen-btn-show").text("共推荐" + temp.result.invitees.length + "人，选择以下好友发积分红包");
                    var html = "<tr><th>手机号</th><th>昵称</th><th>积分</th><th>积分红包</th></tr>";
                    var rec = temp.result.invitees;
                    rec.forEach(function(e, i, a) {
                        html = html + "<tr><th>" + e.mobile + "</th><th>" + e.userName + "</th><th>" + e.integration + "</th><th><a onclick=\"redpack(this,'" + e.id + "');\">积分红包</a></th></tr>";
                    });
                    $("#J_recommend_users").html(html);
                }
            }
        });
    });

    //新闻中心页
    $("#news").on("panelload", function() {
        appAjax({
            type: "POST",
            url: "app/newsinfo/catelist/?pageNo=1",
            context: $("#news"),
            success: function(temp) {
                if (temp) {
                    $("#jtlg-pub-pic").empty();
                    $(".news-sort").remove();

                    //填充分类新闻容器与导航条,准备分类新闻列表ajaxLoader
                    var cateNum = temp.cates.length;
                    for (var i = 0; i < cateNum; i++) {
                        $("#news").append("<div class='news-sort' id='newsCate-" + i + "'>");
                        if (i === 0) {
                            $("#jtlg-pub-pic").append("<a class='active'>" + temp.cates[i].cateName + "</a>");
                        } else {
                            $("#jtlg-pub-pic").append("<a>" + temp.cates[i].cateName + "</a>");
                        }
                        ajaxLoaded["newsCate" + i] = false;
                    }
                    ajaxLoaded.newsNav = true;

                    //检查nav宽度
                    var nav_width = 0;
                    $("#jtlg-pub-pic a").each(function(index, el) {
                        nav_width += $(el).outerWidth(true);
                    });
                    //console.log(nav_width);
                    $("#jtlg-pub-pic").css('width', nav_width + 'px');
                    var winW = $(window).width();
                    var maxDefelction = winW - nav_width;
                    //console.log(maxDefelction);
                    if (winW < nav_width) {
                        //设置滑动效果
                        document.getElementById("jtlg-pub-pic").addEventListener('touchend', function() {
                            isdrag = false;
                            if (parseInt($(this).css("left")) >= 0) {
                                $(this).animate({
                                    left: '0'
                                }, 500);
                            }
                            if (parseInt($(this).css("left")) <= maxDefelction) {
                                $(this).animate({
                                    left: maxDefelction + 'px'
                                }, 500);
                            }
                        });
                        document.getElementById("jtlg-pub-pic").addEventListener('touchstart', function(e) {
                            isdrag = true;
                            tx = parseInt(document.getElementById("jtlg-pub-pic").style.left + 0);
                            x = e.touches[0].pageX;
                            return false;
                        });
                        document.getElementById("jtlg-pub-pic").addEventListener('touchmove', movemouse);
                    }

                    //设置FTScroller高度
                    var allHeight = $(window).height();
                    var imgHeight = $(".news-banner").height();
                    var finalHeight = allHeight - imgHeight - 84;
                    $(".news-sort").css('height', finalHeight + 'px');
                    //新闻中心页btns
                    $(".news-sort").hide();
                    $("#jtlg-pub-pic a").each(function(index, el) {
                        $(this).click(function(event) {
                            $("#jtlg-pub-pic a").removeClass("active");
                            $(this).addClass("active");
                            $(".news-sort").hide();
                            $($(".news-sort").get(index)).show();
                            if (!ajaxLoaded["newsCate" + index]) {
                                getNewsList(1, index);
                            }
                        });
                    });
                    //默认显示第一栏分类新闻
                    $($("#jtlg-pub-pic a").get(0)).trigger('click');
                }
            }
        });
    });

    //  //获取首页新闻

    $.ajax({
        type: "POST",
        url: BASE_URL + "/" + "newsinfo/hp",
        dataType: "json",
        success: function(r) {
            //console.log(r);
            if (r.isSuccess) {
                var d = r.result.lastNews;
                $("#home-news-1").find(".news-title").text(d[0].title);
                $("#home-news-1").find(".news-date").text(d[0].createTime);
                $("#home-news-1").find(".news-content").text(d[0].newsDesc);
                $("#home-news-1").find(".news-item-png").css('background-image', 'url(' + imageUrl + d[0].image + ')');
                $("#home-news-1").click(function(event) {
                    getNewsDetail(d[0].id);
                });
                $("#home-news-2").find(".news-title").text(d[1].title);
                $("#home-news-2").find(".news-date").text(d[1].createTime);
                $("#home-news-2").find(".news-content").text(d[1].newsDesc);
                $("#home-news-2").find(".news-item-png").css('background-image', 'url(' + imageUrl + d[1].image + ')');
                $("#home-news-2").click(function(event) {
                    getNewsDetail(d[1].id);
                });
                $("#home-news-3").find(".news-title").text(d[2].title);
                $("#home-news-3").find(".news-date").text(d[2].createTime);
                $("#home-news-3").find(".news-content").text(d[2].newsDesc);
                $("#home-news-3").find(".news-item-png").css('background-image', 'url(' + imageUrl + d[2].image + ')');
                $("#home-news-3").click(function(event) {
                    getNewsDetail(d[2].id);
                });
                var b = r.result.firstNews;
                b.forEach(function(e, i, a) {
                    //console.log(e,i);
                    $(".J_hp_first_news").eq(i + 1).find("img").first().attr("src", imageUrl + e.image);
                    $(".J_hp_first_news").eq(i + 1).click(function(event) {
                        getNewsDetail(e.id);
                    });
                });
            }
        },
        error: function() {
            alert("网络错误，请重试！  22");
        }
    });



    //设置消息FTS高度
    $("#sys-message-div").css('height', $(window).height() - 115 + 'px');
    $("#user-message-div").css('height', $(window).height() - 115 + 'px');
    FTS.sysMessage = new FTScroller(document.getElementById('sys-message-div'), {
        scrollingX: false
    });
    FTS.userMessage = new FTScroller(document.getElementById('user-message-div'), {
        scrollingX: false
    });


    //签到
    $(".home-sign-in-btn").click(function(event) {
        //console.log("123123");
        appAjax({
            type: "POST",
            url: "userinfo/registration/",
            success: function(temp) {
                console.log(temp);
                if (temp.isSuccess) {
                    $.afui.popup({
                        message: "签到成功！",
                        suppressTitle: true,
                        cancelText: "知道了",
                        cancelOnly: true
                    });
                } else {
                    $.afui.popup({
                        message: temp.message,
                        suppressTitle: true,
                        cancelText: "知道了",
                        cancelOnly: true
                    });
                }
            }
        });

    });



    //留言
    $("#comment .submit-btn").click(function(event) {
        if (!$("#comment").find("input[name='title']").val()) {
            $.afui.popup({
                message: "请填写标题！",
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });
        } else if (!$("#comment").find("textarea[name='content']").val()) {
            $.afui.popup({
                message: "请填写内容！",
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });
        } else {
            appAjax({
                type: "POST",
                url: "app/message/appSave",
                success: function(temp) {
                    if (temp.isSuccess) {
                        $.afui.popup({
                            message: "留言成功！",
                            suppressTitle: true,
                            cancelText: "知道了",
                            cancelOnly: true
                        });
                    }
                }
            }, 'comment-form');
        }
    });

    //查询号码
    $("#query-ID .submit-btn").click(function(event) {
        var content = $("#query-ID .query-ID-input").val();
        var phoneReg = /^1[3|4|5|8][0-9]\d{8}$/;
        if (phoneReg.test(content)) {
            appAjax({
                type: "POST",
                url: "userinfo/search/?mobile=" + content,
                success: function(temp) {

                    if (temp.result) {
                        if (!!temp.result) {
                            $("#res-nickname").text(temp.result.userName);
                            $("#res-weixin").text(temp.result.weixin);
                            $("#res-rank").text(temp.result.userLevelId);
                            if (!!temp.result.createTime) {
                                $("#res-reg-date").text(toDate(temp.result.createTime).format("yyyy-MM-dd"));
                            }
                            //toDate("2008-04-02 10:08:44").format("yy-MM-dd hh:mm:ss");

                        } else {
                            $.afui.popup({
                                message: "查无此号码！",
                                suppressTitle: true,
                                cancelText: "返回",
                                cancelOnly: true
                            });
                        }
                    } else {
                        $.afui.popup({
                            message: temp.message,
                            suppressTitle: true,
                            cancelText: "返回",
                            cancelOnly: true
                        });
                    }
                }
            });
        } else {
            $.afui.popup({
                message: "请输入有效的11位手机号码！",
                suppressTitle: true,
                cancelText: "返回",
                cancelOnly: true
            });
        }
    });

    //获取新闻列表第一页
    function getNewsList(pageNo, cateId) {
        //console.log(pageNo,cateId);
        appAjax({
            type: "POST",
            url: "newsinfo/catelist/?pageNo=" + pageNo + "&cateId=" + (cateId + 1),
            success: function(temp) {
                //console.log("newsinfo/catelist/?pageNo="+pageNo+"&cateId="+(cateId+1));
                //console.log(temp);
                if (temp.infos.length !== 0) {
                    temp.infos.forEach(function(e, i, arr) {
                        $($(".news-sort").get(cateId)).append("<div class='news-item'><div class='news-item-png' style='background-image: url(" + imageUrl + e.image + ");'></div><p class='news-title'>" + e.title + "</p><p class='news-date'>" + e.createTime + "</p><p class='news-content'>" + e.newsDesc + "</p><a href='#news-detail' data-transition='fade' id='news-detail-ID-" + e.id + "'></a></div>");
                        $("#news-detail-ID-" + e.id).click(function(event) {
                            getNewsDetail(e.id);
                        });
                    });
                    ajaxLoaded["newsCate" + cateId] = false;
                    FTS["newsCate" + cateId] = new FTScroller(document.getElementById('newsCate-' + cateId), {
                        scrollingX: false
                    });
                    //下滑加载
                    FTS["newsCate" + cateId].addEventListener('reachedend', function(response) {
                        getNewsMore(++pageNo, cateId);
                    });
                }
            }
        });
    }

    //下滑加载更多新闻
    function getNewsMore(pageNo, cateId) {
        appAjax({
            type: "POST",
            url: "newsinfo/catelist/?pageNo=" + pageNo + "&cateId=" + (cateId + 1),
            success: function(temp) {
                if (temp.infos.length !== 0) {
                    temp.infos.forEach(function(e, i, arr) {
                        $($(".news-sort").get(cateId)).find(".ftscroller_y").append("<div class='news-item'><div class='news-item-png' style='background-image: url(" + imageUrl + e.image + ");'></div><p class='news-title'>" + e.title + "</p><p class='news-date'>" + e.createTime + "</p><p class='news-content'>" + e.newsDesc + "</p><a href='#news-detail' data-transition='fade' id='news-detail-ID-" + e.id + "'></a></div>");
                        $("#news-detail-ID-" + e.id).click(function(event) {
                            getNewsDetail(e.id);
                        });
                    });
                }
            }
        });
    }

    //获取新闻详情
    function getNewsDetail(newsId) {
        //console.log(newsId);
        refreshNewsDetailView();
        appAjax({
            type: "POST",
            url: "newsinfo/detail/" + newsId,
            context: $("#news-detail"),
            success: function(temp) {
                //console.log("newsinfo/detail/"+newsId);
                var d = temp.detail;
                var r = temp.rec_info;
                var action = temp.action;
                $("#news-title-detail").text(d.title);
                $("#news-author-detail").text(d.author);
                $("#news-date-detail").text(d.createTime);
                $("#news-content-detail").text(d.newsDesc);
                $("#news-detail .news-img").attr('src', imageUrl + d.image);
                $("#icon-up-num").text(d.pointGoodCount);
                $("#icon-down-num").text(d.pointBadCount);
                $(".J_weixin_share_member").attr("data-value", d.id);
                $(".J_weixin_share_member").attr("data-image", imageUrl + d.image);
                // 点赞标记
                $(".up-ico").removeClass("active");
                $(".down-ico").removeClass("active");
                if (!!action) {
                    if (action == "up") {
                        $(".up-ico").addClass("active");
                    } else if (action == "up") {
                        $(".down-ico").addClass("active");
                    }
                }

                //顶
                $(".icon.up-ico").click(function() {
                    appAjax({
                        type: "POST",
                        url: "newsinfo/pointgood?newsId=" + newsId,
                        success: function(temp) {
                            if (temp.isSuccess) {
                                var up_num = $("#icon-up-num").text();
                                $("#icon-up-num").text(++up_num);
                                $(".up-ico").addClass("active");
                            } else {
                                $.afui.popup({
                                    message: temp.message,
                                    suppressTitle: true,
                                    cancelText: "知道了",
                                    cancelOnly: true
                                });
                            }
                        }
                    });
                });

                //踩
                $(".icon.down-ico").click(function() {
                    appAjax({
                        type: "POST",
                        url: "newsinfo/pointbad?newsId=" + newsId,
                        success: function(temp) {
                            if (temp.isSuccess) {
                                var up_down = $("#icon-up-down").text();
                                $("#icon-up-down").text(++up_down);
                                $(".down-ico").addClass("active");
                            } else {
                                $.afui.popup({
                                    message: temp.message,
                                    suppressTitle: true,
                                    cancelText: "知道了",
                                    cancelOnly: true
                                });
                            }
                        }
                    });
                });

                $("#news-detail-rec-1").find(".news-title").text(r[0].title);
                $("#news-detail-rec-1").find(".news-date").text(r[0].createTime);
                $("#news-detail-rec-1").find(".news-content").text(r[0].newsDesc);
                $("#news-detail-rec-1").find(".news-item-png").css('background-image', 'url(' + imageUrl + r[0].image + ')');
                $("#news-detail-rec-1").click(function(event) {
                    getNewsDetail(r[0].id);
                });
                $("#news-detail-rec-2").find(".news-title").text(r[1].title);
                $("#news-detail-rec-2").find(".news-date").text(r[1].createTime);
                $("#news-detail-rec-2").find(".news-content").text(r[1].newsDesc);
                $("#news-detail-rec-2").find(".news-item-png").css('background-image', 'url(' + imageUrl + r[1].image + ')');
                $("#news-detail-rec-2").click(function(event) {
                    getNewsDetail(r[1].id);
                });
                $("#news-detail-rec-3").find(".news-title").text(r[2].title);
                $("#news-detail-rec-3").find(".news-date").text(r[2].createTime);
                $("#news-detail-rec-3").find(".news-content").text(r[2].newsDesc);
                $("#news-detail-rec-3").find(".news-item-png").css('background-image', 'url(' + imageUrl + r[2].image + ')');
                $("#news-detail-rec-3").click(function(event) {
                    getNewsDetail(r[2].id);
                });
            }
        });
    }

    //重载新闻详情页面
    function refreshNewsDetailView() {
        $("#news-detail").empty();
        $("#news-detail").append('<div class="container w-bg"><p class="news-title" id="news-title-detail"></p><p class="news-date">作者：<span id="news-author-detail"></span> <span id="news-date-detail"></span></p><img src="" class="news-img"><p class="news-content" id="news-content-detail"></p><p class="news-btns"><span class="icon up-ico"></span><span id="icon-up-num">98</span><span class="icon down-ico"></span><span id="icon-down-num">23</span><span onclick="weixinshare(this,\'新闻详情\',\'http://wnbb.hzlianhai.com/index/\')" data-value="" class="icon share-gray-ico J_weixin_share_member"></span></p></div><p class="title">新闻</p><div class="news-item" id="news-detail-rec-1"><div class="news-item-png"></div><p class="news-title"></p><p class="news-date"></p><p class="news-content"></p><a href="#news-detail" data-transition="fade"></a></div><div class="news-item" id="news-detail-rec-2"><div class="news-item-png"></div><p class="news-title"></p><p class="news-date"></p><p class="news-content"></p><a href="#news-detail" data-transition="fade"></a></div><div class="news-item" id="news-detail-rec-3"><div class="news-item-png"></div><p class="news-title"></p><p class="news-date"></p><p class="news-content"></p><a href="#news-detail" data-transition="fade"></a></div>');
    }

    //设置进度条样式
    var progress_div_w = ($(window).width()) * 0.8;
    //console.log(progress_div_w);
    $(".progress-div").css('height', progress_div_w / 200 * 15 + 'px');
    $(".progress-img img").css({
        height: progress_div_w / 200 * 15 + 'px',
        width: progress_div_w + 'px'
    });

    var backPage = "home";
    //登录
    $("#login .submit-btn").click(function(event) {
        if (!$("#login").find("input[name='account']").val()) {
            $.afui.popup({
                message: "请输入用户名！",
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });
        } else if (!$("#login").find("input[name='password']")) {
            $.afui.popup({
                message: "请输入密码！",
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });
        } else {
            appAjax({
                type: "POST",
                url: "userinfo/login",
                success: function(temp) {
                    console.log(temp);
                    if (temp.isSuccess) {
                        $.afui.popup({
                            message: "登录成功！",
                            suppressTitle: true,
                            cancelText: "知道了",
                            cancelOnly: true,
                            cancelCallback: function() {
                                console.log("xxxx");
                                saveAppToken(temp.result.appToken);
                                set("user", temp.result.user);
                                //$.afui.loadContent("#home",false,false,"fade");
                                $("#to-user-center-btn").attr('href', '#user-center');
                                $("#loginButoonMsg").html("个人中心");
                                showPage(backPage);
                                console.log("localStorage:", window.localStorage);
                                //return ;
                            }
                        });
                    } else {
                        $.afui.popup({
                            message: temp.message,
                            suppressTitle: true,
                            cancelText: "知道了",
                            cancelOnly: true
                        });
                    }
                }
            }, 'login-form');
        }
    });


  


    //  $("#comment").on("panelload",function(){
    //      if(! checkLogin) {
    //          backPage = "comment";
    //          showPage("login");
    //          return ;
    //      }
    //  });
    //  
    //  $("#query-ID").on("panelload",function(){
    //      if(! checkLogin) {
    //          backPage = "query-ID";
    //          showPage("login");
    //          return ;
    //      }
    //  });



    //注册页面预加载
    $("#reg").on("panelload", function() {
        console.log(123);
        appAjax({
            type: "POST",
            url: "mobilecate/loadByParent?parentId=0",
            success: function(temp) {
                if (temp.isSuccess) {
                    temp.result.forEach(function(e, i, a) {
                        $("#reg").find("select.level1").append('<option value="' + e.id + '">' + e.cateName + '</option>');
                    });
                    $("#reg").find("select.level1").change(function(event) {
                        appAjax({
                            type: "POST",
                            url: "mobilecate/loadByParent?parentId=" + $(this).children('option:selected').val(),
                            success: function(temp2) {
                                //console.log(temp2);
                                $("#reg").find("select[name='cateIds']").empty();
                                $("#reg").find("select[name='cateIds']").append('<option value="0">请选择</option>');
                                temp2.result.forEach(function(e, i, a) {
                                    $("#reg").find("select[name='cateIds']").append('<option value="' + e.id + '">' + e.cateName + '</option>');
                                });
                            }
                        });
                    });
                }
            }
        });
    });

    //下载号码
    $("#dl-ID").on("panelload", function() {
        console.log("---");
        if (!checkLogin) {
            backPage = "dl-ID";
            showPage("login");
            return;
        }

        appAjax({
            type: "POST",
            url: "mobilecate/loadByParent?parentId=0",
            success: function(temp) {
                if (temp.isSuccess) {
                    $("#dl-ID").find("select.firstCateId").empty();
                    $("#dl-ID").find("select.firstCateId").append('<option value="">请选择</option>');
                    temp.result.forEach(function(e, i, a) {
                        $("#dl-ID").find("select.firstCateId").append('<option value="' + e.id + '">' + e.cateName + '</option>');
                    });
                    $("#dl-ID").find("select[name='secondCateId']").html('<option value="">请选择</option>');
                    $("#dl-ID").find("select.firstCateId").change(function(event) {
                        appAjax({
                            type: "POST",
                            url: "mobilecate/loadByParent?parentId=" + $(this).children('option:selected').val(),
                            success: function(temp2) {
                                //console.log(temp2);
                                $("#dl-ID").find("select[name='secondCateId']").empty();
                                $("#dl-ID").find("select[name='secondCateId']").append('<option value="">请选择</option>');
                                temp2.result.forEach(function(e, i, a) {
                                    $("#dl-ID").find("select[name='secondCateId']").append('<option value="' + e.id + '">' + e.cateName + '</option>');
                                });
                            }
                        });
                    });
                }
            }
        });
    });

    var num = 0;
    var downThreadId;
    var isDown = true;
    $("#dl-ID .import-btn").click(function(event) {
            //1级可以不选择
            if (1 == 1 || $("#dl-ID").find("select.firstCateId").children('option:selected').val() != 0) {
                $.afui.popup({
                        title: "蜗牛巴巴",
                        message: "请保证允许手机通讯录权限，点击确定后即导入号码并扣除相应积分，现在导入？",
                        cancelText: "取消",
                        cancelCallback: function() {
                            console.log("cancelled");
                        },
                        doneText: "确定",
                        doneCallback: function() {
                            console.log("Done for!");
                            appAjax({
                                type: "POST",
                                url: "mobileinfo/download",
                                data: {
                                    firstCateId: $("#dl-ID").find("select.firstCateId").children('option:selected').val(),
                                    secondCateId: $("#dl-ID").find("select[name='secondCateId']").children('option:selected').val(),
                                    num: $("#dl-ID").find("select[name='num']").children('option:selected').val()
                                },
                                success: function(temp) {
                                    if (temp.isSuccess) {
                                        if (!!temp.result) {
                                            if (temp.result.length == 0) {
                                                $.afui.popup({
                                                    message: "所有号码都已下载",
                                                    suppressTitle: true,
                                                    cancelText: "知道了",
                                                    cancelOnly: true
                                                });
                                                return;
                                            }
                                            var flag = true;
                                            saveTongXunFlag = true;
                                            phoneIndex = 0;
                                            phoneTemp = temp;
                                            saveall();
                                        } else {
                                            $.afui.popup({
                                                message: "导入失败",
                                                suppressTitle: true,
                                                cancelText: "知道了",
                                                cancelOnly: true
                                            });
                                        }
                                    }
                                }
                            });
                        },
                    cancelOnly: false
                });
        } else {
            showProgress(false);
            updateProgess(0, 0);
            $.afui.popup({
                message: "请先选择第一分类！",
                suppressTitle: true,
                cancelText: "知道了",
                cancelOnly: true
            });
        }
    });
});


function saveall() {
    for (; phoneIndex < phoneTemp.result.length; phoneIndex++) {
        var mobileInfo = phoneTemp.result[phoneIndex];
        var mobile = mobileInfo.mobile;
        var sucFun = (function(index, length) {
                      var success = function(contacts) {
                      if (index == length - 1) {
                      $.afui.popup({
                                   message: "导入成功!！",
                                   suppressTitle: true,
                                   cancelText: "确定",
                                   cancelOnly: true
                                   });
                      }
                      };
                      return success;
                      })(phoneIndex, phoneTemp.result.length);
        console.log("=======   for  index:" + phoneIndex);
        saveTongXun(mobile, null, mobile, null, null, sucFun);
        if (phoneIndex % 50 == 0) {
            if(phoneIndex == phoneTemp.result.length){
                return;
            }
            phoneIndex++;
            console.log("xxx   sleep  2m");
            setTimeout(saveall, 2000);
            break;
        }
    }
}

//显示进度条 true/false
function showProgress(bool) {
        if (bool) {
            $("#importProgress-div").css('visibility', 'visible');
        } else {
            $("#importProgress-div").css('visibility', 'hidden');
        }
    }
    //控制进度条进度 0 - 10
function updateProgess(num, time) {
    if (!time) time = 500;
    //console.log('hello',num);
    if (num <= 10 && num >= 0) {
        $("#importProgress-div .progress-img").animate({
            width: num + '0%'
        }, time);
    }
}

function movemouse(e) {
    if (isdrag) {
        var n = tx + e.touches[0].pageX - x;
        $(this).css("left", n);
        return false;
    }
}

$(function() {
    //  showProgress(true);
    //  updateProgess(5);
});

function clickPage(to) {
    console.log("clickPage+" + to);
    if (!checkLogin()) {
        backPage = to;
        showPage("login");
        return;
    }
    showPage(to);
}

//获取消息
function loadMessage(id, from) {
    //  $(event).addClass("pressed");
    appAjax({
        type: "POST",
        url: "message/allinfo?pageNo=1&auditStatus=" + from,
        success: function(temp) {
            //console.log(temp);
            var a = temp.result;
            if (a.length !== 0) {
                var pageNo = 1;
                $("#sys-message-div").find(".ftscroller_y").empty();
                $("#user-message-div").find(".ftscroller_y").empty();
                a.forEach(function(e, i, arr) {
                    if (from == 'system') {
                        if (e.replyContent) {
                            $("#sys-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p><p class='message-content'>" + e.replyContent + "</p></div>");
                        } else {
                            $("#sys-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p></div>");
                        }
                    } else {
                        $("#user-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p></div>");
                    }
                });
                FTS.sysMessage.addEventListener('reachedend', function(response) {
                    getMsgsMore(++pageNo, 0, from);
                });
                ajaxLoaded.sysMessage = true;
                /*
                 FTS.userMessage.addEventListener('reachedend', function (response) {
                 getMsgsMore(++pageNo,1);
                 });
                 */
            }
        }
    });
}

//下滑加载更多消息
function getMsgsMore(pageNo, cateId, from) {
    appAjax({
        type: "POST",
        url: "message/allinfo?pageNo=" + pageNo,
        success: function(temp) {
            console.log("message/allinfo?pageNo=" + pageNo);
            console.log(temp);
            var a = temp.result;
            if (a.length !== 0) {
                a.forEach(function(e, i, arr) {
                    if (from == 'system') {
                        if (e.replyContent) {
                            $("#sys-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p><p class='message-content'>" + e.replyContent + "</p></div>");
                        } else {
                            $("#sys-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p></div>");
                        }
                    } else {
                        $("#user-message-div").find(".ftscroller_y").append("<div class='message w-bg'><p class='message-title'><span class='message-from'>" + e.userName + "：</span><span class='message-date'>" + e.createTime + "</span></p><p class='message-content'>" + e.content + "</p></div>");
                    }
                });
            }
        }
    });
}
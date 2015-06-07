/**
 * 首页新闻信息
 */
function load_indexMsg() {
	appAjax({
		url : "app/newsinfo/hp",
		data : {
			"version" : version
		},
		success : function(r) {
			if (r.isSuccess) {
				var b = r.result.firstNews; // 推荐  
				b.forEach(function(e, i, a) {
					$(".jjl_swiper-wrapper .swiper-slide").eq(i + 1).find("img").first().attr(
							"src", BASE_IMAGE + e.topImage);
					$(".jjl_swiper-wrapper .swiper-slide").eq(i + 1).click(function(event) {
						getNewsDetail(e.id);
					});
				});

				var d = r.result.lastNews;
				d.forEach(function(e, i, a) {
					//	        			  		$(".news-item").eq(i + 1).find("img").first().attr("src", BASE_IMAGE + e.image);
					$(".home_news-item2").eq(i + 1).click(function(event) {
						getNewsDetail(e.id);
					});
					var item = $("#home .news-item").eq(i + 1);
					item.find("img").first().attr("src", BASE_IMAGE + e.image);
					item.find(".news-title").text(e.title);
					item.find(".news-date").text(e.createTime);
					item.find(".news-content").text(e.newsDesc);
					item.click(function(event) {
						getNewsDetail(e.id);
					});
				});

			} else {
				alert(r.message);
			}
		},
		dataType : "json"
	});
}

//获取新闻详情
function getNewsDetail(newsId) {
	//console.log(newsId);
	showPage("news-detail");
	$.ui.showMask('正在加载');
	$.ui.blockUI(.3);

	//    refreshNewsDetailView();
	appAjax({
		type : "POST",
		url : "app/newsinfo/detail/" + newsId,
		context : $("#news-detail"),
		success : function(temp) {
			//console.log("newsinfo/detail/"+newsId);
			var d = temp.detail;
			var r = temp.rec_info;
			var action = temp.action;

//			$("#news-title-detail").text(d.title);
//            $("#news-author-detail").text(d.author);
//            $("#news-date-detail").text(d.createTime);
//            $("#news-content-detail").text(d.newsDesc);
//            $("#news-detail .news-img").attr('src', imageUrl + d.image);
//            $("#icon-up-num").text(d.pointGoodCount);
//            $("#icon-down-num").text(d.pointBadCount);
//            $(".J_weixin_share_member").attr("data-value", d.id);
//            $(".J_weixin_share_member").attr("data-image", imageUrl + d.image);
//			
//			
//			var  html = "";
			
			
			$("#news-detail #news-detail_1 .news-title").text(d.title);
			$("#news-detail #news-detail_1 .news-date").text(d.author);
			$("#news-detail #news-date").text(d.createTime);

			$("#news-content").text(d.newsDesc);
			$("#news-detail #news-detail_1 .news-img").attr('src', BASE_IMAGE + d.image);
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
					type : "POST",
					url : "app/newsinfo/pointgood?newsId=" + newsId,
					success : function(temp) {
						if (temp.isSuccess) {
							var up_num = $("#icon-up-num").text();
							$("#icon-up-num").text(++up_num);
							$(".up-ico").addClass("active");
						} else {
							$.afui.popup({
								message : temp.message,
								suppressTitle : true,
								cancelText : "知道了",
								cancelOnly : true
							});
						}
					}
				});
			});

			//踩
			$(".icon.down-ico").click(function() {
				appAjax({
					type : "POST",
					url : "app/newsinfo/pointbad?newsId=" + newsId,
					success : function(temp) {
						if (temp.isSuccess) {
							var up_down = $("#icon-up-down").text();
							$("#icon-up-down").text(++up_down);
							$(".down-ico").addClass("active");
						} else {
							$.afui.popup({
								message : temp.message,
								suppressTitle : true,
								cancelText : "知道了",
								cancelOnly : true
							});
						}
					}
				});
			});

			r.forEach(function(e, i, a) {
				var item = $("#news-detail .jjl_news-detail_news-item").eq(i + 1);
				item.find("img").first().attr("src", BASE_IMAGE + e.image);
				item.find(".news-title").text(e.title);
				item.find(".news-date").text(e.createTime);
				item.find(".news-content").text(e.newsDesc);
				item.click(function(event) {
					getNewsDetail(e.id);
				});
			});

		}
	});
}



$.ui.ready(function(){
	
    //签到
    $(".home-sign-in-btn").click(function(event) {
        //console.log("123123");
        appAjax({
            type: "POST",
            url: "app/userinfo/registration/",
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
	
	
	
})




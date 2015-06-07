

//邀请金蜗牛会员
function into_goldSnail(){
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	
	console.log("邀请金蜗牛会员");
	appAjax({
		type : "POST",
		url : "app/sysConfig/vipImage",
		dataType : "json",
		success : function(d) {	
			$.ui.unblockUI();
			$.ui.hideMask();

			var img = d.result;
			$("#gold-snail .snail-banner").attr("src",BASE_IMAGE+img.configValue);
		}
	});
	autoLogin(false);

	
}

//邀请好友
function into_inviteFriends(){
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	var  userInfo = get("userInfo");
	if(isBlack(userInfo)){
		showPage("login");
		return ;
	}
	$(".myUserId").attr("data-value",userInfo.id);
	
	console.log("邀请好友");
	appAjax({
		type : "POST",
		url : "app/sysConfig/friendImage",
		dataType : "json",
		success : function(d) {	
			$.ui.unblockUI();
			$.ui.hideMask();

			var img = d.result;
			console.log(img.configValue);
			$("#invite-friends #inviteF_img").attr("src",BASE_IMAGE+img.configValue);
		}
	});

	
}

//电话号码查询
function query_mobile(){
	

	console.log("电话号码查询");
	console.log("query-ID_mobile="+$("#query-ID_mobile").val());
	var isMobile=/^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/; //手机号码验证规则
	var mobile = $("#query-ID_mobile").val();
	if(!isMobile.test(mobile)){ //如果用户输入的值不同时满足手机号和座机号的正则
	    alert("请正确填写电话号码，例如:13415764179或0321-4816048");
	    $("#query-ID_mobile").focus();       //输入框获得光标
	    return false;         //返回一个错误，不向下执行
	}
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	appAjax({
		type : "POST",
		url : "app/userinfo/findMobile",
		data : {"mobile": mobile},
		dataType : "json",
		success : function(d) {	
			$.ui.unblockUI();
			$.ui.hideMask();


			var u = d.result.userInfo;
			var uMore = d.result.userMore;
			
			if (u==null) {
				$('#alert4').show();
			}else {
				if (u.isVip==1) {
					$("#query-gold-member #gold_fakeId").text(u.fakeId);
					$("#query-gold-member #gold_mobile").text(u.mobile);
					$("#query-gold-member #gold_weixin").text(u.weixin);
					$(".friendId").attr("data-value", u.id);
					
					if(!isBlack(uMore))
					$("#query-gold-member #gold_xy").text(uMore.xuanYan);
					$("#query-gold-member #gold_ct").text(u.vipTime);
					$("#query-gold-member #gold_group").html(d.result.groupNum);
					showPage("query-gold-member");
				}else  {
					console.log("蜗牛巴巴成员");
					$('#query-ID #alert5').show();
					$("#query-ID #res_id").text(u.fakeId);
					$("#query-ID #res_mobile").html(u.mobile);
					$("#query-ID #res_weixin").html(u.weixin);
					if(!isBlack(uMore))
						$("#query-ID #res_xy").html(uMore.xuanYan);
					$("#query-ID #res_createTime").html(u.createTime.substr(0,10));	
				}
			}
		}
	});
}

//签到
function signIn(){
	appAjax({
		type : "POST",
		url : "app/userinfo/registration",
		dataType : "json",
		success : function(d) {				
			var s = d.isSuccess;
			if (s==false) {
				$("#home #signIn").text(d.message);
			}
			$('#home #alert1').show();
		}
	});
}

//电话号码分类联动
function panelload() {
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	console.log("下载号码");
	appAjax({
        type: "POST",
        url: "app/mobilecate/loadByParent?parentId=0",
        success: function(temp) {
        	$.ui.unblockUI();
    		$.ui.hideMask();

            if (temp.isSuccess) {
            	console.log("success");
                $("#dl-ID").find("select.firstCateId").empty();
                $("#dl-ID").find("select.firstCateId").append('<option value="">请选择</option>');
                temp.result.forEach(function(e, i, a) {        	
                    $("#dl-ID").find("select.firstCateId").append('<option value="' + e.id + '">' + e.cateName + '</option>');
                });   
                $("#dl-ID").find("select[name='secondCateId']").html('<option value="">请选择</option>');
                $("#dl-ID").find("select.firstCateId").change(function(event) {
                	console.log("parendId----->"+$("select.firstCateId").val());
                	console.log("$(this).children('option:selected').val()----->"+$(this).children('option:selected').val());
                    appAjax({
                        type: "POST",
                        url: "app/mobilecate/loadByParent?parentId=" + $("select.firstCateId").val(),
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
}

function downLoad_fs(){
//	$.ui.showMask('加载中');
//	$.ui.blockUI(.3);

	console.log("----->downLoad_fs");
	console.log("click----->下载粉丝");
	//1级可以不选择
	var num  = $("#downNum").val(); 
	if(num == ""){
		alert("请选择人数");
		return ;
	}
    if (1 == 1 || $("#dl-ID select.firstCateId").val() != 0) {
    
    	$.ui.popup( {
    		   title:"蜗牛巴巴",
    		   message:"请保证允许手机通讯录权限，点击确定后即导入号码并扣除相应积分，现在导入？",
    		   cancelText:"取消",
    		   cancelCallback: function(){console.log("cancelled");},
    		   doneText:"确定",
    		   doneCallback: function(){
    			   
    			 	appAjax({
    		            type: "POST",
    		            url: "app/mobileinfo/download",
    		            data: {
    		                firstCateId: $("#dl-ID select.firstCateId").val(),
    		                secondCateId: $("#dl-ID select[name='secondCateId']").val(),
    		                num: $("#dl-ID select[name='num']").val()
    		            },
    		            success: function(temp) {
    		                if (temp.isSuccess) {
    		                    if (!!temp.result) {
    		                        if (temp.result.length == 0) {
    		                            alert("所有号码都已下载");
    		                            return;
    		                        }
    		                        //var flag = true;
    		                        saveTongXunFlag = true;
    		                        phoneIndex = 0;
    		                        phoneTemp = temp;
    		                        saveall();
    		                    } else {
    		                    	alert("导入失败");
    		                    }
    		                }else {
    		                		alert( temp.message );
    		                }
    		            }
    		        });
    			   
    		   },
    		   cancelOnly:false
    		 });
    	
   
    	
    } else {  
    	alert("请先选择第一分类！");
    }

}

function saveall() {
    for (; phoneIndex < phoneTemp.result.length; phoneIndex++) {
        var mobileInfo = phoneTemp.result[phoneIndex];
        var mobile = mobileInfo.mobile;
        var  userName = mobileInfo.fakeId;
        var 	nickname = mobileInfo.fakeId;
        var sucFun = (function(index, length) {
                      var success = function(contacts) {
                    	  console.log("index====="+index+"++++++++   lenght:"+ length);
                    	  
                      if (index == length - 1) {
                      $.ui.popup({
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
        saveTongXun(userName, nickname, mobile, null, null, sucFun);
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
    
//    if(phoneIndex == phoneTemp.result.length -1){
//    		alert("导入成功!！");
//    }
    
    
    
}


$.ui.ready(function(){
	$("#news .wj_newsTopImage .swiper-slide").on("click",function(){
		getNewsDetail($(this).attr("data-id"));
	});
	
	$("#news #jtlg-pub-pic .cateName").on("click",function(){
		$("#news #jtlg-pub-pic .cateName").removeClass("active");
		$(this).addClass("active");
		newscenter_cate($(this).attr("data-id"));
	});
});



var  initnewsbanner = false;
function  into_newsCenter1(){
	
	$("#news .news_moreBtn").find("input").show();	
	$("#news .news_moreBtn").find("input").attr("data-pageNo",1);
	console.log("into新闻中心");
	appAjax({
        type: "POST",
        url: "app/newsinfo/newsCenter", 
        success: function(data) {
        	$.ui.unblockUI();
    		$.ui.hideMask();

        	if (data.isSuccess) {
				var tops = data.result.tops;
				var news = data.result.news;
				var newscates = data.result.newscates;
				tops.forEach(function(e,i,a){
					//console.log("第"+i+"张图片---------->"+e.topImage);	
					$("#news .wj_newsTopImage .swiper-slide").eq(i).find("img").attr("src", BASE_IMAGE + e.topImage);
					$("#news .wj_newsTopImage .swiper-slide").eq(i).attr("data-id",e.id);

				});
				console.log($("#news #jtlg-pub-pic .cateName").eq(1).val());
				newscates.forEach(function(e,i,a){
					$("#news #jtlg-pub-pic .cateName").eq(i).text(e.cateName);
					$("#news #jtlg-pub-pic .cateName").eq(i).attr("data-id",e.id);
				});
				
				var newsDiv ="";			
				news.forEach(function(e,i,a){
					newsDiv = newsDiv+"" +
					'<div class="news-item" onclick="getNewsDetail('+e.id+')">'+
                    '<img src="'+BASE_IMAGE+e.image+'"class="news-item-png" data-id="'+e.id+'">'+
                    '<p class="news-title">'+e.title+'</p>'+
                    '<p class="news-content">'+e.newsDesc.substr(0,30)+'</p>'+
                    '</div>';
					//<a href="#news-detail" data-transition="fade"></a>
				});
				$("#news .ncWj_items").html(newsDiv);

				if(!initnewsbanner){
					mySwiper2 = new Swiper('#swiper-container2',{
				        pagination: '#pagination2',
				        grabCursor: true,
			            autoplay: 5000,
			            speed: 500,
			            loop:true,
			            watchActiveIndex:true,
			            calculateHeight: false,
				        onSwiperCreated: function(swiper){
				            $(".swiper-pagination-switch:eq(0)").addClass('swiper-visible-switch').addClass('swiper-active-switch');
				            swiper.reInit();
				        }
				    });	
				    initnewsbanner = true;
				}
				
			}
        }
    });
}

var mySwiper2 = null;

var newsFlag = false;
var  inNewsCenter = false;

function leave_newsCenter(){
	inNewsCenter = false;
}

function into_newsCenter(){
	$('#djjzgd').show();
	//$.ui.scrollToTop('news');
	
//	if(!inNewsCenter ){
//		$.ui.scrollToTop("news");
//		inNewsCenter = true;
//	}
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);
	
	
	 into_newsCenter1();
}

function newscenter_cate(id){
	$('#djjzgd').text('点击加载更多');
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	$("#news .news_moreBtn").find("input").show();	
	$("#news .news_moreBtn").find("input").attr("data-pageNo",1);
	console.log("分类点击---------->新闻");
	console.log("id---------->"+id);
	appAjax({
        type: "POST",
        data: {"id":id, "pageNo":1},
        url: "app/newsinfo/newsCategory", 
        success: function(data) {
        	$.ui.unblockUI();
    		$.ui.hideMask();

        	if (data.isSuccess) {
			    var newsList = data.result.newsCenterCate;
			    console.log("newsList.length="+newsList.length);
			    if (newsList.length<5) {
			    	$("#news .news_moreBtn").find("input").hide();	
				}
				var newsDiv ="";	
				newsList.forEach(function(e,i,a){
					console.log(e.title);
					newsDiv = newsDiv+"" +
					'<div class="news-item" onclick="getNewsDetail('+e.id+')">'+
                    '<img src="'+BASE_IMAGE+e.image+'"class="news-item-png" data-id="'+e.id+'">'+
                    '<p class="news-title">'+e.title+'</p>'+
                    '<p class="news-content">'+e.newsDesc.substr(0,30)+'</p>'+
                    '</div>';
				});
				$("#news .ncWj_items").html(newsDiv);
				$("#news .news_moreBtn").find("input").attr("data-id",id);
				console.log("input_dataid----------->"+$("#news .news_moreBtn").find("input").attr("data-id"));
				//console.log("onclick----------->"+$("#news .news_moreBtn").find("input").attr("onclick"));
				//console.log($("#news .ncWj_items").html());
			}
        }
    });
}

function news_more(){
	$("#djjzgd").text('加载中');	
	$.ui.showMask('加载中');
	$.ui.blockUI(.3);

	var id = $("#news .news_moreBtn").find("input").attr("data-id");
	var pageNo = $("#news .news_moreBtn").find("input").attr("data-pageNo");
	console.log("新闻---------->跟多按钮");
	console.log("input_dataid----------->"+$("#news .news_moreBtn").find("input").attr("data-id"));
	console.log("pageNo="+$("#news .news_moreBtn").find("input").attr("data-pageNo"));
	if(!pageNo){
		pageNo = 1;
	}
	pageNo = parseInt(pageNo)+1;
	console.log("pageNo---------->"+pageNo);
	appAjax({
        type: "POST",
        data: {"id":id, "pageNo":pageNo},
        url: "app/newsinfo/newsCategory", 
        success: function(data) {
        	$.ui.unblockUI();
    		$.ui.hideMask();


        	if (data.isSuccess) {
			    var newsList = data.result.newsCenterCate;
			    console.log("newsList.length="+newsList.length);
				console.log(newsList);
				var newsDiv ="";
				if (newsList.length==0) {
					$("#djjzgd").text('没有了');	
				}else{
					$("#djjzgd").text('点击加载更多');	
				}
				newsList.forEach(function(e,i,a){
					console.log(e.title);
					newsDiv = newsDiv+"" +
					'<div class="news-item" onclick="getNewsDetail('+e.id+')">'+
                    '<img src="'+BASE_IMAGE+e.image+'"class="news-item-png" data-id="'+e.id+'">'+
                    '<p class="news-title">'+e.title+'</p>'+
                    '<p class="news-content">'+e.newsDesc.substr(0,30)+'</p>'+
                    '</div>';
				});
				newsDiv = $("#news .ncWj_items").html()+newsDiv;
				$("#news .ncWj_items").html(newsDiv);
				$("#news .news_moreBtn").find("input").attr("data-id",id);	
				$("#news .news_moreBtn").find("input").attr("data-pageNo",pageNo);
			}
        }
    });
}
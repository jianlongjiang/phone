package com.phone.cn.conf.enums;

public enum SysConfigEnum {
	// ok
	day_sign_jy(1,"每日签到_经验"),
	day_sign_score(5,"签到___积分"),
	
	
	dow_mobile_score(4,"下载1个号码扣除积分"),
	
	search_msg_mobile(9,"查询一次扣除积分"),
	user_tuijian(10,"推荐获得奖励积分, 注册, 是 被推荐ren vip 额外加成"),
	register_user_score(11,"被推荐积分 (注册时推荐_推荐人)"),
	
	add_friend_image(12,"邀请好友图片"),
	vip_show_image(13,"金蜗牛会图片"),
	
	day_common_downCount ( 14,"普通用 每日下载："),
	day_vip_downCount ( 15,"vip用 每日下载："),
	
	be_vip_other ( 16,"成为会员 推荐者拿积分"),  //  在上级拿10%
	be_vip_own ( 17,"成为会员 自己拿积分"),
	
	
	to_be_vip ( 20,"成为金蜗牛"),
	to_share_firend ( 6,"分享朋友圈"),//  分享好友, 其他人 查询号码分享 ...
	
	register_score(8,"注册积分"),
	register_jy(3,"注册经验"),
	
	ask_friend_suc_jy(2,"邀请好友经验_注册时被推荐----"),
	
	register_and_tuijian_own(7,"被推荐积分___注册时推荐_注册人"),
	register_and_tuijian_other(11,"被推荐积分___注册时推荐_推荐人"),
	;
	
	private  Integer  value;
	
	private  String  msg;

	private SysConfigEnum(Integer value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}

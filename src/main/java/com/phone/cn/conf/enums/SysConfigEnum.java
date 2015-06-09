package com.phone.cn.conf.enums;

public enum SysConfigEnum {

	search_msg_mobile(9,"查询一次扣除积分"),
	user_tuijian(10,"推荐获得奖励积分"),
	register_user_score(11,"注册用户获得奖励积分"),
	
	add_friend_image(12,"邀请好友图片"),
	vip_show_image(13,"金蜗牛会图片"),
	
	day_common_downCount ( 14,"普通用 每日下载："),
	day_vip_downCount ( 15,"vip用 每日下载："),
	
	be_vip_other ( 16,"成为会员 推荐者拿积分"),  //  在上级拿10%
	be_vip_own ( 17,"成为会员 自己拿积分"),
	
	to_share_firend ( 6,"分享朋友圈");
	
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

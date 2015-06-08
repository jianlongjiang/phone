package com.phone.cn.conf.enums;

public enum MobileInfoFromEnum {
	
	UPLOAD_IMPORT("others","号码导入"),
	USER("user","用户注册"),
	ADMIN("admin","后台添加");
	
	private String  value;
	
	private String  msg;

	private MobileInfoFromEnum(String value, String msg) {
		this.value = value;
		this.msg = msg;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
	
}

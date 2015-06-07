package com.phone.cn.conf.enums;

public enum CashUserLogActionEnum {

	SYS("sys", "系统发送"),
	USER("user", "用户自己体现"),
	ADMIN("admin", "后台管理员赠送");

	private String value;
	private String msg;

	private CashUserLogActionEnum(String value, String msg) {
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

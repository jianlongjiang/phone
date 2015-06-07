package com.phone.cn.conf.enums;

public enum AuthenticateStatusEnum {

	UNAUDITED("unaudited", "未审核"), THROUGH("through", "通过"), REFUSED("refused",
			"驳回");

	private String value;

	private String msg;

	private AuthenticateStatusEnum(String value, String msg) {
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

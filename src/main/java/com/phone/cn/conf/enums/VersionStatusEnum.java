package com.phone.cn.conf.enums;

public enum VersionStatusEnum {

	NO_UPDATE("0", "不更新"), CHOOSE_UPDATE("1", "选择更新-自主更新"), MUST_UPDATE("2",
			"强制更新");

	private String value;
	private String msg;

	private VersionStatusEnum(String value, String msg) {
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

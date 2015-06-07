package com.phone.cn.conf.enums;

public enum CashUserLogStatusEnum {

	doing("0", "现金提取中"), doed("1", "提取完结"), fail("2", "失败");

	private String value;

	private String msg;

	private CashUserLogStatusEnum(String value, String msg) {
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

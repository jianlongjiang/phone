package com.phone.cn.conf.enums;

public enum LoginTypeEnum {
	WEI_XIN("weiXin"),
	WEI_BO("weiBo"),
	FACE_BOOK("faceBook"),
	TWITTER("twitter")
	;
	
	private  String value;
	
	private LoginTypeEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}

package com.phone.cn.exception;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SimpleException extends Exception {
	
	private static final long serialVersionUID = -6080790193939322528L;

	private String msg;
	
	private Integer code =500;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public SimpleException(String msg, Integer code) {
		super();
		this.msg = msg;
		this.code = code;
	}
	
	public SimpleException( Integer code,String msg) {
		super();
		this.msg = msg;
		this.code = code;
	}
	
	public SimpleException(String msg) {
		this.msg = msg;
	}
	
	
	
	 @Override
	public String toString() {
		return  ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}

package com.phone.cn.bean.product;

import com.phone.cn.entity.product.CateInfo;

@SuppressWarnings("serial")
public class CateInfoDto extends CateInfo {
    private Integer twoCateSize; //二级封类数量
    
    private Integer userSize;//导入用户数

	public Integer getTwoCateSize() {
		return twoCateSize;
	}

	public void setTwoCateSize(Integer twoCateSize) {
		this.twoCateSize = twoCateSize;
	}

	public Integer getUserSize() {
		return userSize;
	}

	public void setUserSize(Integer userSize) {
		this.userSize = userSize;
	}
    
    
}
package com.phone.cn.bean.product;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class DownloadLogBean extends SearchBean {
    private Integer id;

    private Integer userId;

    private Integer mobilenum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

	public Integer getMobilenum() {
		return mobilenum;
	}

	public void setMobilenum(Integer mobilenum) {
		this.mobilenum = mobilenum;
	}
}
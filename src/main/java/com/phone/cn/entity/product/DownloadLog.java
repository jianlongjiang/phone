package com.phone.cn.entity.product;

import com.phone.cn.entity.BaseEntity;
/**
 * �?载类
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class DownloadLog extends BaseEntity<Integer> {
    private Integer id;

	private Integer userId;

	private Integer mobilenum;

	private String downIdsStr;

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

	public String getDownIdsStr() {
		return downIdsStr;
	}

	public void setDownIdsStr(String downIdsStr) {
		this.downIdsStr = downIdsStr == null ? null : downIdsStr.trim();
	}

	

}
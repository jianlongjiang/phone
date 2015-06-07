package com.phone.cn.bean.product;

import com.phone.cn.bean.SearchBean;

public class UpDownLogBean extends SearchBean {

	private static final long serialVersionUID = -4662987658257489605L;

	private Integer id;

	private Integer userId;

	private Integer newsId;

	private String action;

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

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}

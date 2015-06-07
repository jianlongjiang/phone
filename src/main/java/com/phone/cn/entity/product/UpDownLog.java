package com.phone.cn.entity.product;

import com.phone.cn.entity.BaseEntity;
/**
 * 顶踩log类
 * @author zgd
 *
 */
public class UpDownLog extends BaseEntity<Integer> {
   
	private static final long serialVersionUID = -8395991116160371837L;

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
        this.action = action == null ? null : action.trim();
    }
}
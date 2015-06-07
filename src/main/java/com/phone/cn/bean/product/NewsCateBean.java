package com.phone.cn.bean.product;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class NewsCateBean extends SearchBean {
    private Integer id;

    private String cateName;

    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName == null ? null : cateName.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}
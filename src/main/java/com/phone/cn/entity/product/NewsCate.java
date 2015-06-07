package com.phone.cn.entity.product;

import com.phone.cn.entity.BaseEntity;
/**
 * 新闻分类实体类
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class NewsCate extends BaseEntity<Integer> {
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
package com.phone.cn.bean.product;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class CateInfoBean extends SearchBean {
    private Integer id;

    private String cateName;

    private Integer parentCateId;
    
    private Integer cateLevel;

    private String more1;

    private String more2;

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

    public Integer getParentCateId() {
        return parentCateId;
    }

    public void setParentCateId(Integer parentCateId) {
        this.parentCateId = parentCateId;
    }

    public Integer getCateLevel() {
		return cateLevel;
	}

	public void setCateLevel(Integer cateLevel) {
		this.cateLevel = cateLevel;
	}

	public String getMore1() {
        return more1;
    }

    public void setMore1(String more1) {
        this.more1 = more1 == null ? null : more1.trim();
    }

    public String getMore2() {
        return more2;
    }

    public void setMore2(String more2) {
        this.more2 = more2 == null ? null : more2.trim();
    }
}
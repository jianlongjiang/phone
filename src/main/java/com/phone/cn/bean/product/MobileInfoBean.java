package com.phone.cn.bean.product;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class MobileInfoBean extends SearchBean {
    private Integer id;

    private String mobile;

    private String cateIds;

    private String mobileFrom;

    //是否是必备下载号码 y是 n不是
    private String more1;

    private String more2;
    
    private String fakeId;

    
//    ====
    private  String   secondCateIds;
    
    
    
    public String getFakeId() {
		return fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}

	public String getSecondCateIds() {
		return secondCateIds;
	}

	public void setSecondCateIds(String secondCateIds) {
		this.secondCateIds = secondCateIds;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getCateIds() {
        return cateIds;
    }

    public void setCateIds(String cateIds) {
        this.cateIds = cateIds == null ? null : cateIds.trim();
    }

    public String getMobileFrom() {
        return mobileFrom;
    }

    public void setMobileFrom(String mobileFrom) {
        this.mobileFrom = mobileFrom == null ? null : mobileFrom.trim();
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
    
    @Override
    public String toString() {
    	return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
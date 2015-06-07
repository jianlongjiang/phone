package com.phone.cn.entity.product;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.phone.cn.entity.BaseEntity;

@SuppressWarnings("serial")
public class NewsInfo extends BaseEntity<Integer>{
    private Integer id;

    private String title;

    private String author;

    /**  小图**/
    private String image;

    private Integer cateId;

    private String cateName;

    private Integer pointGoodCount;

    private Integer pointBadCount;

    private String newsPlaces;

    private String orderBy;

    private Date createTime;

    private String createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateUser;

    	
    private String more1;

    private String more2;

    private Boolean isrec;

    private Boolean isAudit;

    private Integer managerId;

    private Boolean topStatus;

    /**  大图**/
    private String topImage;

    private String newsDesc;

    private String summary;

    private String auditDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName == null ? null : cateName.trim();
    }

    public Integer getPointGoodCount() {
        return pointGoodCount;
    }

    public void setPointGoodCount(Integer pointGoodCount) {
        this.pointGoodCount = pointGoodCount;
    }

    public Integer getPointBadCount() {
        return pointBadCount;
    }

    public void setPointBadCount(Integer pointBadCount) {
        this.pointBadCount = pointBadCount;
    }

    public String getNewsPlaces() {
        return newsPlaces;
    }

    public void setNewsPlaces(String newsPlaces) {
        this.newsPlaces = newsPlaces == null ? null : newsPlaces.trim();
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy == null ? null : orderBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
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

    
    public Boolean getIsrec() {
		return isrec;
	}

	public void setIsrec(Boolean isrec) {
		this.isrec = isrec;
	}

	public Boolean getIsAudit() {
		return isAudit;
	}

	public void setIsAudit(Boolean isAudit) {
		this.isAudit = isAudit;
	}

	public Boolean getTopStatus() {
		return topStatus;
	}

	public void setTopStatus(Boolean topStatus) {
		this.topStatus = topStatus;
	}

	public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getTopImage() {
        return topImage;
    }

    public void setTopImage(String topImage) {
        this.topImage = topImage == null ? null : topImage.trim();
    }

    public String getNewsDesc() {
        return newsDesc;
    }

    public void setNewsDesc(String newsDesc) {
        this.newsDesc = newsDesc == null ? null : newsDesc.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getAuditDesc() {
        return auditDesc;
    }

    public void setAuditDesc(String auditDesc) {
        this.auditDesc = auditDesc == null ? null : auditDesc.trim();
    }
}
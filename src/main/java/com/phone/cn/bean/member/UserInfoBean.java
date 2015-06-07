package com.phone.cn.bean.member;

import java.util.Date;

import com.phone.cn.entity.member.UserInfo;

@SuppressWarnings("serial")
public class UserInfoBean extends UserInfo {
	private Integer id;

	private String userName;

	private String realName;

	private String weixin;

	private String mobile;

	private String cateIds;

	// private String[] cateIdArrays;
	private Integer firstCateId;
	private Integer secondCateId;

	private Integer userLevelId;

	private Date registTime;

	private Integer inviteFriendAmount;

	private Integer downloadMobileAmount;

	private Integer experience;

	private Integer downloadLevel;

	private Integer integration;

	private String auditStatus;

	private String status;

	private String more1;

	private String more2;

	private String downloadMobile;

	private Integer inviteesId;

	private String password;

	//================不在数据库的字段======================
	// 邀请好友数最大值
	private Integer maxVal;

	// 邀请好友数最小值
	private Integer minVal;
	
	//金蜗牛邀请人数最多前N名
	private Integer goldenNum;
	
	//邀请好友人数最多前N名
	private Integer inviteNum;
	
	//赚钱最多的
	private Integer maxMoney;
	
	// 邀请好友数最大值
    private Integer gsMaxNum;

    // 邀请好友数最小值
	private Integer gsMinNum;
	

	public Integer getFirstCateId() {
		return firstCateId;
	}

	public void setFirstCateId(Integer firstCateId) {
		this.firstCateId = firstCateId;
	}

	public Integer getSecondCateId() {
		return secondCateId;
	}

	public void setSecondCateId(Integer secondCateId) {
		this.secondCateId = secondCateId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin == null ? null : weixin.trim();
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

	public Integer getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(Integer userLevelId) {
		this.userLevelId = userLevelId;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Integer getInviteFriendAmount() {
		return inviteFriendAmount;
	}

	public void setInviteFriendAmount(Integer inviteFriendAmount) {
		this.inviteFriendAmount = inviteFriendAmount;
	}

	public Integer getDownloadMobileAmount() {
		return downloadMobileAmount;
	}

	public void setDownloadMobileAmount(Integer downloadMobileAmount) {
		this.downloadMobileAmount = downloadMobileAmount;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Integer getDownloadLevel() {
		return downloadLevel;
	}

	public void setDownloadLevel(Integer downloadLevel) {
		this.downloadLevel = downloadLevel;
	}

	public Integer getIntegration() {
		return integration;
	}

	public void setIntegration(Integer integration) {
		this.integration = integration;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus == null ? null : auditStatus.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
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

	public String getDownloadMobile() {
		return downloadMobile;
	}

	public void setDownloadMobile(String downloadMobile) {
		this.downloadMobile = downloadMobile == null ? null : downloadMobile
				.trim();
	}

	public Integer getInviteesId() {
		return inviteesId;
	}

	public void setInviteesId(Integer inviteesId) {
		this.inviteesId = inviteesId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMaxVal() {
		return maxVal;
	}

	public void setMaxVal(Integer maxVal) {
		this.maxVal = maxVal;
	}

	public Integer getMinVal() {
		return minVal;
	}

	public void setMinVal(Integer minVal) {
		this.minVal = minVal;
	}

	public Integer getGoldenNum() {
		return goldenNum;
	}

	public void setGoldenNum(Integer goldenNum) {
		this.goldenNum = goldenNum;
	}

	public Integer getInviteNum() {
		return inviteNum;
	}

	public void setInviteNum(Integer inviteNum) {
		this.inviteNum = inviteNum;
	}

	public Integer getMaxMoney() {
		return maxMoney;
	}

	public void setMaxMoney(Integer maxMoney) {
		this.maxMoney = maxMoney;
	}

	public Integer getGsMaxNum() {
		return gsMaxNum;
	}

	public void setGsMaxNum(Integer gsMaxNum) {
		this.gsMaxNum = gsMaxNum;
	}

	public Integer getGsMinNum() {
		return gsMinNum;
	}

	public void setGsMinNum(Integer gsMinNum) {
		this.gsMinNum = gsMinNum;
	}

	public void createCateIds() {
		if (secondCateId != null && firstCateId != null) {
			this.cateIds = firstCateId + "," + secondCateId;
		} else if (secondCateId == null && firstCateId != null) {
			this.cateIds = String.valueOf(firstCateId);
		} else {
			this.cateIds = null;
		}
	}

	
	
}
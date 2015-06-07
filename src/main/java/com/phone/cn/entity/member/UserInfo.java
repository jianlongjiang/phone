package com.phone.cn.entity.member;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.phone.cn.entity.BaseEntity;

/**
 * ??��?��??�?
 * 
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class UserInfo extends BaseEntity<Integer> {

	private Integer id;

	// / ??�称
	private String userName;

	private String realName;

	private String weixin;

	// 手机
	private String mobile;

	/** 类�??id, ??��?�注??? 2级�?��?? (id), **/
	private String cateIds;

	private Integer userLevelId;
	/** 注�????��?? **/
	private Date registTime;
	/** ???请�?�好?????��?? **/
	private Integer inviteFriendAmount;
	/** �?载�????? ??��?? **/
	private Integer downloadMobileAmount;

	private Integer inviteFriendVipAmount;
	/**  **/
	private Integer experience;

	private Integer downloadLevel;

	/** � 积分 **/
	private Integer integration;

	private String auditStatus;

	private String status;

	private String more1;// 级别

	private String more2;

	private String downloadMobile;

	// ??��??�?id
	private Integer inviteesId;

	private String password;

	private Double reflectRed;

	private Double balance;

	private Integer redPackCount;

	private Boolean isVip;
	
	private Date  vipTime;

	private Double freezeRedPack;

	private Boolean isFreeze;

	private Integer sex;
	
	private Integer weixinCount;
	
	private String fakeId;
	
	public String getFakeId() {
		return fakeId;
	}

	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}

	public Integer getWeixinCount() {
		return weixinCount;
	}

	public void setWeixinCount(Integer weixinCount) {
		this.weixinCount = weixinCount;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Boolean getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Boolean isFreeze) {
		this.isFreeze = isFreeze;
	}

	public Double getFreezeRedPack() {
		return freezeRedPack;
	}

	public void setFreezeRedPack(Double freezeRedPack) {
		this.freezeRedPack = freezeRedPack;
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
		this.password = password == null ? null : password.trim();
	}

	public Integer getInviteFriendVipAmount() {
		return inviteFriendVipAmount;
	}

	public void setInviteFriendVipAmount(Integer inviteFriendVipAmount) {
		this.inviteFriendVipAmount = inviteFriendVipAmount;
	}

	public Double getReflectRed() {
		return reflectRed;
	}

	public void setReflectRed(Double reflectRed) {
		this.reflectRed = reflectRed;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getRedPackCount() {
		return redPackCount;
	}

	public void setRedPackCount(Integer redPackCount) {
		this.redPackCount = redPackCount;
	}

	public Boolean getIsVip() {
		return isVip;
	}

	public void setIsVip(Boolean isVip) {
		this.isVip = isVip;
	}

	public String getDownloadMobile() {
		return downloadMobile;
	}

	public void setDownloadMobile(String downloadMobile) {
		this.downloadMobile = downloadMobile == null ? null : downloadMobile.trim();
	}

	public Date getVipTime() {
		return vipTime;
	}

	public void setVipTime(Date vipTime) {
		this.vipTime = vipTime;
	}
	
	public  String  showName(){
		if(!StringUtils.isBlank(userName)) return userName; // 昵称
		return  mobile;  // 手机号码
	}
	

}
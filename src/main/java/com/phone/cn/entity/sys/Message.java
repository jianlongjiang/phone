package com.phone.cn.entity.sys;

import com.phone.cn.entity.BaseEntity;
/**
 * ???�?�?
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class Message extends BaseEntity<Integer> {
    private Integer id;

	private Integer userId;

	private String userName;

	private String mobile;

	private String title;

	private String auditStatus;

	/** 0:未回复  1: 已经回复的  **/
	private String doStatus;

	private Integer managerId;

	private String content;

	private String replyContent;

	private Boolean  isSee;
	
	public Boolean getIsSee() {
		return isSee;
	}

	public void setIsSee(Boolean isSee) {
		this.isSee = isSee;
	}

	
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus == null ? null : auditStatus.trim();
	}

	public String getDoStatus() {
		return doStatus;
	}

	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus == null ? null : doStatus.trim();
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent == null ? null : replyContent.trim();
	}

	
	static public enum DoStatusEnum{
		
		add(0,"留言"),
		suc(1,"回复");
		
		private Integer value;
		
		private String msg;

		private DoStatusEnum(Integer value, String msg) {
			this.value = value;
			this.msg = msg;
		}

		public String getValue() {
			return value +"";
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		
		
	}
	
	
}
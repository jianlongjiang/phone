package com.phone.cn.entity.member;

import com.phone.cn.entity.BaseEntity;

@SuppressWarnings("serial")
public class CashUserLog extends BaseEntity<Integer> {
	private Integer id;

	/** 动作  提权红包  **/
	private String more1;

	/** 支付宝 用户名字 **/
	private String more2;

	private String more3;

	private Integer int1;

	private Integer int2;

	private Integer userId;

	private Double cash;

	/** 状态
0失败 
 1成功, 
2 进行中,
 3, 提款中  **/
	private String doStatus;

	private Integer managerId;

	
	private String nickname;

	private String mobile;

	private String aplipay;

	private String action;
	
	private Boolean  isSee;
	

	public Boolean getIsSee() {
		return isSee;
	}

	public void setIsSee(Boolean isSee) {
		this.isSee = isSee;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAplipay() {
		return aplipay;
	}

	public void setAplipay(String aplipay) {
		this.aplipay = aplipay;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getMore3() {
		return more3;
	}

	public void setMore3(String more3) {
		this.more3 = more3 == null ? null : more3.trim();
	}

	public Integer getInt1() {
		return int1;
	}

	public void setInt1(Integer int1) {
		this.int1 = int1;
	}

	public Integer getInt2() {
		return int2;
	}

	public void setInt2(Integer int2) {
		this.int2 = int2;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
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
	
	
	
	static public enum doStatusEnum{
//		0
//		 1成功, 
//		2 进行中,
//		 3, 提款中
		 fail(0,"失败 "),
		 suc(1,"成功"),
		 doing(2,"进行中"),
		 reflect(3, "现金体现申请");
		 
		 private int value;
		private String msg;
		private doStatusEnum(int value, String msg) {
			this.value = value;
			this.msg = msg;
		}
		public String getValue() {
			return value+"";
		}
		public void setValue(int value) {
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
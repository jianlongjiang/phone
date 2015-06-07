package com.phone.cn.bean.member;

import com.phone.cn.entity.member.CashUserLog;

@SuppressWarnings("serial")
public class CashUserLogBean extends CashUserLog {
	
	
	Double  startCash;
	Double  endCash;

	
	@Override
	public String getAplipay() {
		return (String) doNull(super.getAplipay());
	}
	
	@Override
	public Integer getId() {
		return super.getId();
	}
	
	@Override
	public String getMobile() {
		return (String) doNull(super.getMobile());
	}
	
	public Double getStartCash() {
		return startCash;
	}
	
	public Double getEndCash() {
		return endCash;
	}

	public void setStartCash(Double startCash) {
		this.startCash = startCash;
	}

	public void setEndCash(Double endCash) {
		this.endCash = endCash;
	}
	
	
	
	
	
}
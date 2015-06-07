package com.phone.cn.entity.sys;

import com.phone.cn.entity.BaseEntity;

/**
 * 统计类
 * 
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class StatisticsInfo extends BaseEntity<Integer> {
	private Integer id;

	private Integer downloadAmount;

	private Integer registAmount;

	private Integer downloadPeople;

	private Integer vipAmount;

	private Integer loginAmount;
	
	/**  签到数量 **/
	private Integer int1;
	private Integer int2;
	private Integer int3;
	private Integer int4;
	private Integer int5;
	
	

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

	public Integer getInt3() {
		return int3;
	}

	public void setInt3(Integer int3) {
		this.int3 = int3;
	}

	public Integer getInt4() {
		return int4;
	}

	public void setInt4(Integer int4) {
		this.int4 = int4;
	}

	public Integer getInt5() {
		return int5;
	}

	public void setInt5(Integer int5) {
		this.int5 = int5;
	}

	public Integer getVipAmount() {
		return vipAmount;
	}

	public void setVipAmount(Integer vipAmount) {
		this.vipAmount = vipAmount;
	}

	public Integer getLoginAmount() {
		return loginAmount;
	}

	public void setLoginAmount(Integer loginAmount) {
		this.loginAmount = loginAmount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDownloadAmount() {
		return downloadAmount;
	}

	public void setDownloadAmount(Integer downloadAmount) {
		this.downloadAmount = downloadAmount;
	}

	public Integer getRegistAmount() {
		return registAmount;
	}

	public void setRegistAmount(Integer registAmount) {
		this.registAmount = registAmount;
	}

	public Integer getDownloadPeople() {
		return downloadPeople;
	}

	public void setDownloadPeople(Integer downloadPeople) {
		this.downloadPeople = downloadPeople;
	}
}
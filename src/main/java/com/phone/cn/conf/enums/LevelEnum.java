package com.phone.cn.conf.enums;
/**
 * @author zgdcool
 * @version 2015年3月13日 下午9:29:07
 *   
 */
public enum LevelEnum {
	ONE_LEVEL("顶级",60),
	TWO_LEVEL("中级",30),
	THREE_LEVEL("低级",10),
	;
	private String levelName;
	
	private Integer random;
	
	private LevelEnum(String levelName, Integer random) {
		this.levelName = levelName;
		this.random = random;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Integer getRandom() {
		return random;
	}

	public void setRandom(Integer random) {
		this.random = random;
	}
	
}

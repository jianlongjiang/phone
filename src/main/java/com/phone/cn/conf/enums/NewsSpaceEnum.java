package com.phone.cn.conf.enums;
/**
 * @author zgdcool
 * @version 2015年3月13日 下午9:29:07
 *   
 */
public enum NewsSpaceEnum {
	NEWS_HP_REC("新闻首页推荐","news_hp_rec"),
	HP_REC("首页推荐","hp_rec"),
	;
	private String spaceName;
	
	private String spaceCode;
	
	private NewsSpaceEnum(String spaceName, String spaceCode) {
		this.spaceName = spaceName;
		this.spaceCode = spaceCode;
	}

	public String getSpaceName() {
		return spaceName;
	}

	public void setSpaceName(String spaceName) {
		this.spaceName = spaceName;
	}

	public String getSpaceCode() {
		return spaceCode;
	}

	public void setSpaceCode(String spaceCode) {
		this.spaceCode = spaceCode;
	}
	
}

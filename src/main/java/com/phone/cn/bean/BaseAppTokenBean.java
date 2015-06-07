package com.phone.cn.bean;

import javax.servlet.http.HttpSession;

import org.springside.modules.utils.SpringContextHolder;

import com.phone.cn.conf.DataConfig;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.web.MySessionContext;

/**
 * app 基本参数
 * 
 * @author jiangjianlong
 * 
 */
public class BaseAppTokenBean {

	private String appToken;// 手机令牌


	private boolean isTest = false;
	

	private int userId = 1;

	private String simi;

	public HttpSession getSession() {
		// if(appToken == null) appToken = "11";
		return MySessionContext.getInstance().getSession(appToken);
	}

	public String getAppToken() {
		return appToken;
	}

	public void setAppToken(String appToken) {
		this.appToken = appToken;
	}

	public String getSimi() {
		return simi;
	}

	public void setSimi(String simi) {
		this.simi = simi;
	}

	public UserInfo getAppUser() {

		if (isTest) {
			UserInfo userInfo = new UserInfo();
			userInfo.setId(userId);
			userInfo.setUserName("test nickname");
			userInfo.setIntegration(1000000);
			userInfo.setBalance(10000.0);
			userInfo.setReflectRed(-111.99);
			return userInfo;
		}
		// appToKen 为空
//		if (StringUtils.isBlank(appToken))
//			return null;
		HttpSession session = getSession();
		if (session == null)
			return null;
		UserInfoService userInfoService = SpringContextHolder.getBean(UserInfoService.class);
		UserInfo userInfo =(UserInfo) getSession().getAttribute(DataConfig.APP_USER);
		if(userInfo != null){
			userInfo = userInfoService.findOne(userInfo.getId());
		}
		return  userInfo;
	}

}

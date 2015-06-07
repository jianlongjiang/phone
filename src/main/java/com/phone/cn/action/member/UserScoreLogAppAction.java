package com.phone.cn.action.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.member.UserScoreLogBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.UserScoreLog;
import com.phone.cn.service.sys.UserScoreLogService;
import com.phone.cn.web.action.BaseAppController;
import com.phone.cn.web.interceptor.AppUserLogin;


@Controller
@RequestMapping("app/userScoreLog")
@AppUserLogin
public class UserScoreLogAppAction extends BaseAppController<UserScoreLogBean, UserScoreLog, java.lang.Integer> {
	@Autowired
	private UserScoreLogService userScoreLogService;
	
	
	/**
	 * 用户积分 list	
	 */
	@Override
	public Object list(BaseAppTokenBean baseApp, UserScoreLogBean bean ) {
		UserInfo userInfo = baseApp.getAppUser();
		bean.setUserId(userInfo.getId());
		_defaultSort(bean);
		//  信息已读 设置
		userScoreLogService.userReadAll(userInfo.getId());
		return suc(baseService.queryPage(bean));
	}
	
}

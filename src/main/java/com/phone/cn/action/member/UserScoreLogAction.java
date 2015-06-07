package com.phone.cn.action.member;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.member.UserScoreLogBean;
import com.phone.cn.entity.sys.UserScoreLog;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;


@Controller
@RequestMapping("admin/userScoreLog")
@AdminUserLogin
public class UserScoreLogAction extends BaseCRUDController<UserScoreLogBean, UserScoreLog, java.lang.Integer> {
	
	@Override
	public String list(UserScoreLogBean bean, Model model, @PathVariable Integer pageNo) {
		bean.setSort("update_time.desc");
		return super.list(bean, model, pageNo);
	}
	
}

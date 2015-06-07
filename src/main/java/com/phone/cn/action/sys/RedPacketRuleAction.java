package com.phone.cn.action.sys;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.sys.RedPacketRuleBean;
import com.phone.cn.entity.sys.RedPacketRule;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;


@RequestMapping("admin/RedPacketRule")
@AdminUserLogin
@Controller
public class RedPacketRuleAction extends BaseCRUDController<RedPacketRuleBean, RedPacketRule, java.lang.Integer>{
	
	@Override
	public String list(RedPacketRuleBean bean, Model model, Integer pageNo) {
		return super.list(bean, model, pageNo);
	}

}

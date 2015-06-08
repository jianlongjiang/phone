package com.phone.cn.action.sys;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

	
	@Override
	public Map<String, Object> save(RedPacketRule m, HttpServletRequest request) {
		if(m.getStart() == null || m.getEnd() == null ){
			return fail("规则不对");
		}
		if(m.getStart()  <=0 || m.getEnd() <=0 ){
			return fail("红包值必须大于0");
		}
		if( m.getStart().intValue() > m.getEnd().intValue()  ){
			return fail("红包值有问题");
		}
		
		return super.save(m, request);
	}
}

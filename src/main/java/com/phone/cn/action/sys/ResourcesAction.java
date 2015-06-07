package com.phone.cn.action.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.sys.ResourcesBean;
import com.phone.cn.entity.sys.Resources;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

@RequestMapping("admin/resources")
@AdminUserLogin
@Controller
public class ResourcesAction extends BaseCRUDController<ResourcesBean, Resources, java.lang.Integer> {
	
	@Override
	protected String _loadPrefix() {
		return "resources_";
	}

}

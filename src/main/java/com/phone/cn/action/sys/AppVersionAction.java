package com.phone.cn.action.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.sys.AppVersionBean;
import com.phone.cn.entity.sys.AppVersion;
import com.phone.cn.service.sys.AppVersionService;
import com.phone.cn.web.action.BaseCRUDController;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:56:06
 *   
 */
@Controller
@RequestMapping(value="admin/version")
public class AppVersionAction extends BaseCRUDController<AppVersionBean, AppVersion, Integer>{

	@Autowired
	AppVersionService versionService;
	
}

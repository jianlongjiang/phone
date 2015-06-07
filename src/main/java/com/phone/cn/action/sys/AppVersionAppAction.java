package com.phone.cn.action.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.sys.AppVersionBean;
import com.phone.cn.bean.sys.VelAppVersionDto;
import com.phone.cn.entity.sys.AppVersion;
import com.phone.cn.service.sys.AppVersionService;
import com.phone.cn.web.action.BaseCRUDController;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:56:06
 *   
 */
@Controller
@RequestMapping(value="app/version")
public class AppVersionAppAction extends BaseCRUDController<AppVersionBean, AppVersion, Integer>{

	@Autowired
	AppVersionService versionService;
	
	/**
	 * 版本更新检查
	 * @param version
	 * @return
	 */
	@RequestMapping("checkVersion")
	@ResponseBody
	public  Object  checkVersion(String version){
		VelAppVersionDto dto = versionService.checkVersion(version);
		return suc(SUCCESS,dto);
	}
}

package com.phone.cn.action.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.sys.SysConfigBean;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.web.action.BaseCRUDController;


@Controller
@RequestMapping("app/sysConfig")
public class SysConfigAppAction extends BaseCRUDController<SysConfigBean, SysConfig, Integer>{
	
	@Autowired
	private SysConfigService sysConfigService;
	
	//获取好友页面图片
	@RequestMapping("friendImage")
	@ResponseBody
	public Object friendImage(){
		if (sysConfigService.findOne(12)==null) {
			return fail(FAIL);
		}
		return suc(SUCCESS,sysConfigService.findOne(12));
		
	}
	//获取会员页面图片
	@RequestMapping("vipImage")
	@ResponseBody
	public Object vipImage(){
		if (sysConfigService.findOne(13)==null) {
			return fail(FAIL);
		}
		return suc(SUCCESS,sysConfigService.findOne(13));	
	}

}

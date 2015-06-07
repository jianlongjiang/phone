package com.phone.cn.action.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.sys.SysConfigBean;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseCRUDController;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:57:34
 *   
 */
@Controller
@RequestMapping(value="admin/config")
public class SysConfigAction extends BaseCRUDController<SysConfigBean, SysConfig, Integer>{

	@Autowired
	private SysConfigService sysConfigService;
	
	@RequestMapping(value="experience")
	public String experience(Model model){
		model.addAttribute("infos", sysConfigService.findExperience());
		return viewName("experience");
	}
	
	@ResponseBody
	@RequestMapping(value="allsave")
	public Map<String, Object> experienceSave(HttpServletRequest request){
		String[] ids = request.getParameterValues("id");
		String[] configValues = request.getParameterValues("configValue");
		List<SysConfig> configs = new ArrayList<SysConfig>();
		for (int i = 0; i < configValues.length; i++) {
			SysConfig c = new SysConfig();
			c.setConfigValue(configValues[i]);
			c.setId(Integer.parseInt(ids[i]));
			configs.add(c);
		}
		ResultBean b = new ResultBean();
		Boolean flag = Boolean.TRUE;
		for (SysConfig sysConfig : configs) {
			sysConfigService.save(sysConfig);
			
		}
		b.setIsSuccess(flag);
		return JsonMapper.beanToMap(b);
	}
	
	@RequestMapping(value="integral")
	public String integral(Model model){
		model.addAttribute("infos", sysConfigService.findIntegral());
		model.addAttribute("infos2", sysConfigService.findGolden());
		return viewName("integral");
	}
	
	@RequestMapping(value="invite")
	public String invite(Model model){
		model.addAttribute("info", sysConfigService.findInvite());
		return viewName("invite");
	}
	@RequestMapping(value="goldensnail")
	public String goldensnail(Model model){
		model.addAttribute("info", sysConfigService.findGoldenSnail());
		return viewName("goldensnail");
	}
	
	@RequestMapping(value="manage")
	public String manage(Model model){
		model.addAttribute("infos", sysConfigService.findManage());
		return viewName("manage");
	}
}

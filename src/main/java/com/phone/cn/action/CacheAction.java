package com.phone.cn.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.service.CommonService;

/**
 * @author zgdcool
 * @version 2015年3月1日 下午5:43:33
 *   
 */
@Controller
public class CacheAction {
	@Autowired
	private CommonService commonService;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET, value = "/clear/cache")
	public String clearCache(@RequestParam(value = "ids") String[] cacheNames) {
		StringBuffer cacheBuffer = new StringBuffer();
		for (String cacheName : cacheNames) {
			Boolean clearFlag = commonService.clearCache(cacheName);
			if (clearFlag) {
				cacheBuffer.append(String.format("%s clear ok", cacheBuffer.toString()));
			} else {
				cacheBuffer.append(String.format("%s clear error", cacheBuffer.toString()));
			}
			if (cacheBuffer.length() > 0) {
				cacheBuffer.append(";");
			}
		}
		return cacheBuffer.toString();
	}
}

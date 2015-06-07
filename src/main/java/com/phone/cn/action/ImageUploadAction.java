package com.phone.cn.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ueditor.ConfigManager;

import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.baidu.ueditor.hunter.FileManager;
import com.baidu.ueditor.hunter.ImageHunter;
import com.baidu.ueditor.upload.Uploader;
import com.phone.cn.conf.AppConfig;
import com.phone.cn.utils.JsonMapper;

/**
 * 在线编辑前得  文件上传
 * @author admin
 *
 */
@Controller
public class ImageUploadAction{

	@ResponseBody
	@RequestMapping(value = "ueditor/jsp/controller")
	public String exec( HttpServletRequest request ) {
		String actionType = request.getParameter("action");
		String callbackName = request.getParameter("callback");
		String contextPath = request.getContextPath();
		ConfigManager configManager = ConfigManager.getInstance(AppConfig.appMap.get("imagepath"), contextPath,
				request.getRequestURI());
		if (callbackName != null) {
			if (!validCallbackName(callbackName)) {
				return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
			}
			return callbackName + "(" + invoke(request, actionType, configManager) + ");";
		} else {
			return invoke(request, actionType, configManager);
		}
	}

	public String invoke( HttpServletRequest request, String actionType, ConfigManager configManager ) {
		// 上传文件 类型检验
		if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
			return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
		}
		// 配置文件
		if (configManager == null || !configManager.valid()) {
			return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
		}

		State state = null;

		int actionCode = ActionMap.getType(actionType);

		Map<String, Object> conf = null;

		switch (actionCode) {

			case ActionMap.CONFIG:
				return configManager.getAllConfig().toString();

			case ActionMap.UPLOAD_IMAGE:
			case ActionMap.UPLOAD_SCRAWL:
			case ActionMap.UPLOAD_VIDEO:
			case ActionMap.UPLOAD_FILE:
				conf = configManager.getConfig(actionCode);
				state = new Uploader(request, conf).doExec();
				break;

			case ActionMap.CATCH_IMAGE:
				conf = configManager.getConfig(actionCode);
				String[] list = request.getParameterValues((String) conf.get("fieldName"));
				state = new ImageHunter(conf).capture(list);
				break;

			case ActionMap.LIST_IMAGE:
			case ActionMap.LIST_FILE:
				conf = configManager.getConfig(actionCode);
				int start = getStartIndex(request);
				state = new FileManager(conf).listFile(start);
				break;

		}
		if(state == null){
			return null;
		}
		Map<Object, Object> infoMap = JsonMapper.readStringValueToMap(state.toJSONString());
		if (infoMap != null && StringUtils.isNotEmpty(infoMap.get("url").toString())) {
			infoMap.put("url", request.getContextPath() + "/resources/admin" + infoMap.get("url"));
		}
		return JsonMapper.toJsonStr(infoMap);

	}

	public int getStartIndex( HttpServletRequest request ) {

		String start = request.getParameter("start");

		try {
			return Integer.parseInt(start);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * callback参数验证
	 */
	public boolean validCallbackName( String name ) {

		if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
			return true;
		}

		return false;

	}

}

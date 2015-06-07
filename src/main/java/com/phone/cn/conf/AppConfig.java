package com.phone.cn.conf;

import java.util.HashMap;
import java.util.Map;

import org.springside.modules.utils.SpringContextHolder;

public class AppConfig {

	public static Map<String, String> appMap;
	
	private static Map<Integer, String> imageMap ;

	static {
		appMap = SpringContextHolder.getBean("appConfig");
		if (appMap != null) {
			initConfig();
		}
		imageMap = new HashMap<Integer, String>();
		imageMap.put(0,"cloud.jpg");
		imageMap.put(1,"jokul.jpg");
		imageMap.put(2,"nightfall.jpg");
		imageMap.put(3,"path.jpg");
		imageMap.put(4,"waves.jpg");
		imageMap.put(5,"winter.jpg");
	}
	
	public static String getCountryMobileNo(Integer country){
		if(country == null){
			return null;
		}
		return imageMap.get(country);
	}

	private static void initConfig() {
		//�????
		
	}

	public static Map<String, String> getAppMap() {
		return appMap;
	}

	public static void setAppMap(Map<String, String> appMap) {
		AppConfig.appMap = appMap;
	}

	public static String getConfig(String code) {
		return appMap.get(code);
	}

	public static void putConfig(String code, String value) {
		appMap.put(code, value);
	}

}

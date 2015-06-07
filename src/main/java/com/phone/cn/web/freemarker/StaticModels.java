package com.phone.cn.web.freemarker;

import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;

public class StaticModels extends HashMap<String, Object> {

	/**
	 * serialVersionUID:(用一句话描述这个变量表示�?��).
	 * 
	 * @since JDK 1.6
	 */
	private static final long serialVersionUID = 1L;

	private Properties staticModels;

	public Properties getStaticModels() {
		return staticModels;
	}

	public void setStaticModels(Properties staticModels) {
		//静�?�?
		if (this.staticModels == null && staticModels != null) {
			this.staticModels = staticModels;
			Set<String> keys = this.staticModels.stringPropertyNames();
			for (String key : keys) {
				this.put(key, useStaticPackage(this.staticModels.getProperty(key)));
			}
		}
		//枚举
		this.put("enums", BeansWrapper.getDefaultInstance().getEnumModels());
//		//配置常量
//	    this.putAll(AppConfig.getAppMap());
	}
	

	public static TemplateHashModel useStaticPackage(String packageName) {
		try {
			BeansWrapper wrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModels = wrapper.getStaticModels();
			TemplateHashModel fileStatics = (TemplateHashModel) staticModels.get(packageName);
			return fileStatics;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

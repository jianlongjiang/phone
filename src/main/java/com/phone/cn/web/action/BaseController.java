/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.phone.cn.web.action;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.ResultBean;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.utils.ReflectUtils;

/**
 * 基础控制器
 * <p>User: Zhang Kaitao
 * <p>Date: 13-2-23 下午3:56
 * <p>Version: 1.0
 */
public abstract class BaseController<M extends BaseEntity<ID>, ID extends Serializable> {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 实体类型
     */
    protected final Class<M> entityClass;

    private String viewPrefix;


    protected BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
//        System.out.println(en);
        logger.info("===============>:"+entityClass);
        setViewPrefix(defaultViewPrefix());
    }


    /**
     * 设置通用数据
     *
     * @param model
     */
    protected void setCommonData(Model model) {
    }

    /**
     * 页面 前缀
     * @return
     */
    protected  String  _loadPrefix(){
    		return  null;
    }
    
    /**
     * 页面 前缀
     * @return
     */
    protected  Boolean  _updateTimeSort(){
    		return  null;
    }

    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
    		String prefix =  _loadPrefix();
         if(org.apache.commons.lang3.StringUtils.isNotBlank(prefix)){
         	suffixName = prefix+""+suffixName;
         }
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
       
        return getViewPrefix() + suffixName;
    }

    /**
     * 共享的验证规则
     * 验证失败返回true
     *
     * @param m
     * @param result
     * @return
     */
    protected boolean hasError(M m, BindingResult result) {
        Assert.notNull(m);
        return result.hasErrors();
    }

    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }
    
    protected static String SUCCESS = "成功";
	protected static String FAIL = "失败";
	
	@Autowired  
	protected  HttpServletRequest request; 
	
	
	
	protected Map<String,Object> suc(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.suc(null);
		}else {
			resultBean = ResultBean.suc(msg[0]);
		}
		return JsonMapper.beanToMap(resultBean);
	}
	
	
	protected Map<String,Object> fail(String... msg){
		ResultBean resultBean =null;
		if(msg == null || msg.length == 0){
			resultBean = ResultBean.fail(null);
		}else {
			resultBean = ResultBean.fail(msg[0]);
		}
		
		return JsonMapper.beanToMap(resultBean);
	}
	
	protected Map<String,Object> suc(String msg,Object result){
		return JsonMapper.beanToMap(ResultBean.suc(msg, result));
	}
	protected Map<String,Object> suc(Object result){
		return JsonMapper.beanToMap(ResultBean.suc(null, result));
	}
	
	protected Map<String,Object> fail(int code, String msg){
		return JsonMapper.beanToMap(ResultBean.fail(code, msg));
	}
	
	protected Map<String,Object> fail( String msg, int code){
		return JsonMapper.beanToMap(ResultBean.fail(code, msg));
	}
	
	protected Object fail(SimpleException e) {
		return fail(e.getCode(),e.getMsg());
	}
}

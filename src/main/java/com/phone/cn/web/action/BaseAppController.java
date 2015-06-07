/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.phone.cn.web.action;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.service.BaseService;

/**
 * 基础CRUD 控制器
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-2-23 下午1:20
 * <p>
 * Version: 1.0
 */
public abstract class BaseAppController<S extends SearchBean, M extends BaseEntity<ID>, ID extends Serializable> extends BaseController<M, ID> {

    protected BaseService<M, ID> baseService;


    protected PermissionList permissionList = null;
    
    protected void _defaultSort(SearchBean searchBean) {
		if(org.apache.commons.lang3.StringUtils.isNotBlank(searchBean.getSort())){
			searchBean.setSort("update_time.desc");
		}
	}
    
    
    /**
     * 
     * @param baseApp
     * @param s( pageSize, pageNo,  其他  看需求)
     * @return
     */
    @ResponseBody
    @RequestMapping("list")
    public  Object  list(BaseAppTokenBean baseApp, S s){
    		return  null;
    }
    
    
    /**
     * 设置基础service
     * 
     * @param baseService
     */
    @Autowired
    public void setBaseService(BaseService<M, ID> baseService) {
        this.baseService = baseService;
    }

    public String getRemoteHost(javax.servlet.http.HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

}

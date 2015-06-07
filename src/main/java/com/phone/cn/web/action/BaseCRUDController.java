/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.phone.cn.web.action;

import java.io.Serializable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.conf.DataConfig;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.service.BaseService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * 基础CRUD 控制器
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-2-23 下午1:20
 * <p>
 * Version: 1.0
 */
public abstract class BaseCRUDController<S extends SearchBean, M extends BaseEntity<ID>, ID extends Serializable> extends BaseController<M, ID> {

    protected BaseService<M, ID> baseService;

    private boolean listAlsoSetCommonData = false;

    protected PermissionList permissionList = null;
    
    protected void _defaultSort(SearchBean searchBean) {
		if(org.apache.commons.lang3.StringUtils.isNotBlank(searchBean.getSort())){
			searchBean.setSort("update_time.desc");
		}
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

    /**
     * 列表也设置common data
     */
    public void setListAlsoSetCommonData(boolean listAlsoSetCommonData) {
        this.listAlsoSetCommonData = listAlsoSetCommonData;
    }

    /**
     * 权限前缀：如sys:user 则生成的新增权限为 sys:user:create
     */
    public void setResourceIdentity(String resourceIdentity) {
        if (!StringUtils.isEmpty(resourceIdentity)) {
            permissionList = PermissionList.newPermissionList(resourceIdentity);
        }
    }
    
    @RequestMapping(value="list/p{pageNo}")
    public String list(@Valid
    S bean, Model model, @PathVariable Integer pageNo) {
    	System.out.println("xxxxxxxxxxxxxxx");
    	if(pageNo == null){
    		pageNo =1;
    		logger.error("================= list  pageNo + @PathVariable,  最好 bean 也加 @Valid ");
    		System.out.println("================= list  pageNo + @PathVariable,  最好 bean 也加 @Valid ");
    		//throw new Exception("================= list  pageNo + @PathVariable,  最好 bean 也加 @Valid");
    	}
    	if(_updateTimeSort() != null && _updateTimeSort()){
    		_defaultSort(bean);
    	}
    	
    	bean.setPageNo(pageNo);
    
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }
       
        model.addAttribute("bean", bean);
        PageList<M> infos = baseService.queryAllWithPage(bean, bean.toPageBounds());
        model.addAttribute("page", infos.getPaginator());
        model.addAttribute("infos", infos);
        if (bean.getIsIframe() != null && bean.getIsIframe()) {
            return viewName("iframe-list");
        }
        if (listAlsoSetCommonData) {
            setCommonData(model);
        }
        return viewName("list");
    }

    /**
     * 仅返回表格数据
     * 
     * @param searchable
     * @param model
     * @return
     */
    @AdminUserLogin
    @RequestMapping(method = RequestMethod.GET, headers = "table=true")
    public String listTable(S searchable, Model model) {
        list(searchable, model, 1);
        return viewName("listTable");
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(Model model, @PathVariable("id")
    ID id) {
        if (permissionList != null) {
            this.permissionList.assertHasViewPermission();
        }

        M m = baseService.findOneView(id);
        setCommonData(model);
        model.addAttribute("info", m);
        model.addAttribute(DataConfig.OP_NAME, "查看");
        return viewName("view");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        setCommonData(model);
        model.addAttribute(DataConfig.OP_NAME, "新增");

        return viewName("input");
    }
    
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String add(M m, Model model) {

        if (permissionList != null) {
            this.permissionList.assertHasCreatePermission();
        }

        setCommonData(model);
        model.addAttribute("info", m);
        model.addAttribute(DataConfig.OP_NAME, "新增");

        return viewName("input");
    }
    
    protected M getAddEntity() {
        return null;
    }

    @RequestMapping(value = "/input/{id}", method = RequestMethod.GET)
    public String input(@PathVariable("id")
    ID id, Model model) {
        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }
        setCommonData(model);
        model.addAttribute(DataConfig.OP_NAME, "修改");
        model.addAttribute("info", baseService.findOne(id));
        return viewName("input");
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public Map<String, Object> save(@Valid
    M m,HttpServletRequest request) {
    	ResultBean b = new ResultBean();
        if (permissionList != null) {
            this.permissionList.assertHasUpdatePermission();
        }
        b.setIsSuccess(baseService.isSave(m));
        return JsonMapper.beanToMap(b);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public Map<String, Object> showDeleteForm(@PathVariable("id")
    ID id, Model model) {
    	ResultBean b = new ResultBean();
        if (permissionList != null) {
            this.permissionList.assertHasDeletePermission();
        }
        b.setIsSuccess(baseService.isDelete(id));
        setCommonData(model);
        model.addAttribute(DataConfig.OP_NAME, "删除");
        return JsonMapper.beanToMap(b);
    }

    // @RequestMapping(value = "batch/delete", method = { RequestMethod.GET,
    // RequestMethod.POST })
    // public String deleteInBatch(@RequestParam(value = "ids", required =
    // false)
    // ID[] ids, @RequestParam(value = DataConfig.BACK_URL, required = false)
    // String backURL, RedirectAttributes redirectAttributes) {
    //
    // if (permissionList != null) {
    // this.permissionList.assertHasDeletePermission();
    // }
    //
    // baseService.delete(ids);
    //
    // redirectAttributes.addFlashAttribute(DataConfig.MESSAGE, "删除成功");
    // return redirectToUrl(backURL);
    // }
    
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

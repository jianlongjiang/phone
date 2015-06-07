/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package base;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.service.BaseService;
import com.phone.cn.utils.CreateBeanFactory;
import com.phone.cn.utils.ReflectUtils;
import com.phone.cn.web.action.PermissionList;

/**
 * 基础CRUD 控制器
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-2-23 下午1:20
 * <p>
 * Version: 1.0
 */
public abstract class BaseCRUDTest<S extends SearchBean, M extends BaseEntity<ID>, ID extends Serializable>  extends BaseTest{
	
	protected BaseService<M, ID> baseService;

	protected PermissionList permissionList = null;

	private Class<S> searchClass;
	private Class<M> baseEntityClass;
	
	
	
	public BaseCRUDTest() {
		this.searchClass = ReflectUtils.findParameterizedType(getClass(), 0);          
		this.baseEntityClass = ReflectUtils.findParameterizedType(getClass(), 1);
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
	
	
	@Test
	public void testSaveAndQuery(){
		save();
		queryPage();
	}
	
	
//	@Test
	public  void   save() {
		for (int i = 0; i < array.length; i++) {
			System.out.println(baseEntityClass);
			M m = CreateBeanFactory.createBean(baseEntityClass);
			System.out.println("======>"+m);
			m.setId(null);
			baseService.save(m);
			Assert.assertNotNull(m.getId());
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public  void   queryPage() {
		S s = CreateBeanFactory.createBean(searchClass);
		PageList<M> pageLIst = baseService.queryPage(s);
		Assert.assertNotNull(pageLIst);
		
		try {
			s =((S) s.getClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		pageLIst = baseService.queryPage(s);
		Assert.assertNotEquals(pageLIst.size(), 0);
	}

}

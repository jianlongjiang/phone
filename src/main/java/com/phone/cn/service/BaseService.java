/**
 * Copyright (c) 2005-2012 https://github.com/zhangkaitao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.phone.cn.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.constant.ErrorCodeConstant;
import com.phone.cn.entity.BaseEntity;
import com.phone.cn.entity.search.Searchable;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.mapper.BaseMapper;

/**
 * <p>
 * 抽象service层基类 提供一些简便方法
 * <p/>
 * <p>
 * 泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 * <p>
 * User: Zhang Kaitao
 * <p>
 * Date: 13-1-12 下午4:43
 * <p>
 * Version: 1.0
 */
public abstract class BaseService<M extends BaseEntity<ID>, ID extends Serializable> {

	protected BaseMapper<M, ID> baseRepository;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	public void setBaseRepository(BaseMapper<M, ID> baseRepository) {
		this.baseRepository = baseRepository;
	}
	
	/**
	 * 保存单个实体
	 *
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	public M save(M m) {
		if (m.getId() != null ) {
		
			baseRepository.updateByPrimaryKeySelective(updateBaseEnity(m));
		} else {
			initSave(m);
			baseRepository.insertSelective(createBaseEnity(m));
		}
		return m;
	}

	/**
	 * 类保存数据初始化
	 * @param m
	 */
	protected  void initSave(M m) {
		
	}

	/**
	 * 保存单个实体是否成功
	 *
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	public Boolean isSave(M m) {
		int count = 0;
		if (m.getId() != null) {
			count = baseRepository
					.updateByPrimaryKeySelective(updateBaseEnity(m));
		} else {
			count = baseRepository.insertSelective(createBaseEnity(m));
		}
		if (count == 1) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public ID saveGetId(M m) {
		if (m.getId() != null) {
			baseRepository.updateByPrimaryKeySelective(updateBaseEnity(m));
		} else {
			baseRepository.insertSelective(createBaseEnity(m));
		}
		return m.getId();
	}

	/**
	 * 非保存的方法只是设置 update time
	 * @param m
	 * @return
	 */
	public M updateBaseEnity(M m) {
		return setBaseEnity(m, Boolean.FALSE);
	}

	
	public M createBaseEnity(M m) {
		return setBaseEnity(m, Boolean.TRUE);
	}

	public <T extends BaseEntity<?>> T setBaseEnity(T m, Boolean createFlag) {

		if (createFlag) {
			// 判断是否登入
			// if (MemberUtils.getContext() == null) {
			// m.setCreateUser("test");
			// } else {
			// m.setCreateUser(MemberUtils.getContext().getUserName());
			// }
			if(m.getCreateTime() == null)
				m.setCreateTime(Calendar.getInstance().getTime());
		}
		// 判断是否登入
		// if (MemberUtils.getContext() == null) {
		// m.setUpdateUser("test");
		// } else {
		// m.setUpdateUser(MemberUtils.getContext().getUserName());
		// }
		m.setUpdateTime(Calendar.getInstance().getTime());
		return m;
	}

	/**
	 * 更新单个实体
	 *
	 * @param m
	 *            实体
	 * @return 返回更新的实体
	 */
	public M update(M m) {
		baseRepository.updateByPrimaryKeySelective(updateBaseEnity(m));
		return m;
	}

	/**
	 * 
	 * isUpdate:(这里用一句话描述这个方法的作用).
	 *
	 * @author zhangguodong
	 * @param m
	 * @return 返回是否更新成功
	 * @since JDK 1.6
	 */
	public Boolean isUpdate(M m) {
		Integer count = baseRepository
				.updateByPrimaryKeySelective(updateBaseEnity(m));
		if (count != null && count == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 根据主键删除相应实体
	 *
	 * @param id
	 *            主键
	 */
	public void delete(ID id) {
		baseRepository.deleteByPrimaryKey(id);
	}

	/**
	 * 
	 * isDelete:(这里用一句话描述这个方法的作用).
	 *
	 * @author zhangguodong
	 * @param id
	 * @return 返回是否删除成功
	 * @since JDK 1.6
	 */
	public Boolean isDelete(ID id) {
		// 改id 不存在 
		if(baseRepository.selectByPrimaryKey(id) == null) return Boolean.FALSE;
		Integer count = baseRepository.deleteByPrimaryKey(id);
		if (count != null && count == 1) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * 根据主键删除相应实体
	 *
	 * @param ids
	 *            实体
	 */
	public void delete(ID[] ids) {
		baseRepository.delete(ids);
	}

	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	public M findOne(ID id) {
		return baseRepository.selectByPrimaryKey(id);
	}

	
	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	public M findOneView(ID id) {
		return baseRepository.selectByPrimaryKey(id);
	}
	
	/**
	 * 实体是否存在
	 *
	 * @param id
	 *            主键
	 * @return 存在 返回true，否则false
	 */
	public boolean exists(ID id) {
		return baseRepository.exists(id);
	}

	/**
	 * 统计实体总数
	 *
	 * @return 实体总数
	 */
	public long count() {
		return baseRepository.count();
	}

	/**
	 * 返回集合
	 * 
	 * @param cc
	 * @return
	 */
	public List<M> findByIds(List<ID> ids) {
		List<M> list = new ArrayList<M>();
		for (ID id : ids) {
			M m = findOne(id);
			list.add(m);
		}

		return list;
		// return baseRepository.findByIds(ids);
	}

	public Map<String, M> findMapStringByIds(List<ID> ids) {
		if (ids == null || ids.isEmpty()) {
			return Maps.newHashMap();
		}
		Map<String, M> map = new HashMap<String, M>();
		List<M> entitys = findByIds(ids);
		for (M e : entitys) {
			map.put(String.valueOf(e.getId()), e);
		}
		return map;
	}

	public Map<ID, M> findMapByIds(List<ID> ids) {
		if (ids == null || ids.isEmpty()) {
			return Maps.newHashMap();
		}
		Map<ID, M> map = new HashMap<ID, M>();
		List<M> entitys = findByIds(ids);
		for (M e : entitys) {
			map.put(e.getId(), e);
		}
		return map;
	}

	/**
	 * 返回集合
	 * 
	 * @param cc
	 * @return
	 */
	public List<M> findBySelective(M cc) {
		return baseRepository.findBySelective(cc);
	}

	/**
	 * 查询所有实体
	 *
	 * @return
	 */
	//@Cacheable(cacheName="day_cache_key")
	public List<M> findAll() {
		return baseRepository.query(null);
	}

	/**
	 * 按照顺序查询所有实体
	 *
	 * @param sort
	 * @return
	 */
	public List<M> findAll(Sort sort) {
		return baseRepository.findAll(sort);
	}

	/**
	 * 分页及排序查询实体
	 *
	 * @param pageable
	 *            分页及排序数据
	 * @return
	 */
	public Page<M> findAll(Pageable pageable) {
		return baseRepository.findAll(pageable);
	}

	/**
	 * 按条件分页并排序查询实体
	 *
	 * @param searchable
	 *            条件
	 * @return
	 */
	public Page<M> findAll(Searchable searchable) {
		return baseRepository.findAll(searchable);
	}

	/**
	 * 按条件不分页不排序查询实体
	 *
	 * @param searchable
	 *            条件
	 * @return
	 */
	public List<M> findAllWithNoPageNoSort(Searchable searchable) {
		return Lists.newArrayList(baseRepository.findAll(searchable)
				.getContent());
	}

	/**
	 * 按条件排序查询实体(不分页)
	 *
	 * @param searchable
	 *            条件
	 * @return
	 */
	public List<M> findAllWithSort(Searchable searchable) {
		return Lists.newArrayList(baseRepository.findAll(searchable)
				.getContent());
	}

	/**
	 * 按条件分页并排序统计实体数量
	 *
	 * @param searchable
	 *            条件
	 * @return
	 */
	public Long count(Searchable searchable) {
		return baseRepository.count(searchable);
	}

	/**
	 * 根据条件获取所有对应实体 queryAll:(这里用一句话描述这个方法的作用).
	 *
	 * @author zhangguodong
	 * @param bean
	 * @return
	 * @since JDK 1.6
	 */
	public List<M> queryAll(SearchBean bean) {
		return baseRepository.query(bean);
	}

	/**
	 * 根据条件获取所有对应实体，并分页 queryAllWithPage:(这里用一句话描述这个方法的作用).
	 *
	 * @author zhangguodong
	 * @param bean
	 * @param pageBounds
	 * @return
	 * @since JDK 1.6
	 */
	public PageList<M> queryAllWithPage(SearchBean bean, PageBounds pageBounds) {
		return baseRepository.query(bean, pageBounds);
	}
	
	public PageList<M> queryPage(SearchBean bean) {
		return baseRepository.query(bean,bean.toPageBounds());
	}
	

	public M selectOneByExample(SearchBean t) {
		List<M> list = queryAll(t);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 *后台使用
	 * @param str
	 * @return
	 */
	protected String doEditor(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return StringEscapeUtils.unescapeHtml3(str).replace("\n", "")
					.replace("\"", "\\\"")
					.replace("\t", "").replace("\r", "");
		}
		return str;
	}
	
	/**
	 *前台使用
	 * @param str
	 * @return
	 */
	protected String doEditorForIndex(String str) {
		if (StringUtils.isNotEmpty(str)) {
			return StringEscapeUtils.unescapeHtml3(str).replace("\n", "")
//					.replace("\"", "")
					.replace("\t", "").replace("\r", "");
		}
		return str;
	}
	
//	public String showEditor(String str) {
//		if (StringUtils.isNotEmpty(str)) {
//			return StringEscapeUtils.unescapeHtml3(str).replace("\n", "").replace("\t", "").replace("\r", "");
//		}
//		return str;
//	}
	
	protected void notNull(Object check, String string) throws SimpleException {
		
		if(check == null){
			SimpleException exception = new SimpleException(ErrorCodeConstant.NULL_MSG, ErrorCodeConstant.NULL_CODE);
			logger.error(string, exception);
			throw exception;
		}
		
	}
}

package com.phone.cn.mapper.sys;

import java.util.List;

import com.phone.cn.entity.sys.Resources;
import com.phone.cn.mapper.BaseMapper;

public interface ResourcesMapper extends BaseMapper<Resources, Integer>{
    int deleteByPrimaryKey(Integer id);

	int insert(Resources record);

	int insertSelective(Resources record);

	Resources selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Resources record);

	int updateByPrimaryKey(Resources record);


	List<Resources> findByRoleId(Integer roleId);

}
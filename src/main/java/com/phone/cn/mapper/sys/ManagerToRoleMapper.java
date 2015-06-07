package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.ManagerToRole;
import com.phone.cn.mapper.BaseMapper;

public interface ManagerToRoleMapper extends BaseMapper<ManagerToRole, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(ManagerToRole record);

    int insertSelective(ManagerToRole record);

    ManagerToRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManagerToRole record);

    int updateByPrimaryKey(ManagerToRole record);
}
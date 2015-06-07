package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.Manager;
import com.phone.cn.mapper.BaseMapper;

public interface ManagerMapper extends BaseMapper<Manager, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Manager record);

    int insertSelective(Manager record);

    Manager selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Manager record);

    int updateByPrimaryKey(Manager record);
}
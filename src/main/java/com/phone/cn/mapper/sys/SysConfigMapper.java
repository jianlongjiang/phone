package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.mapper.BaseMapper;

public interface SysConfigMapper extends BaseMapper<SysConfig, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(SysConfig record);

    int insertSelective(SysConfig record);

    SysConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysConfig record);

    int updateByPrimaryKey(SysConfig record);
}
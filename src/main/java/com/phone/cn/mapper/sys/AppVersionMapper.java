package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.AppVersion;
import com.phone.cn.mapper.BaseMapper;

public interface AppVersionMapper extends BaseMapper<AppVersion, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(AppVersion record);

    int insertSelective(AppVersion record);

    AppVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AppVersion record);

    int updateByPrimaryKey(AppVersion record);
}
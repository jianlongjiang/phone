package com.phone.cn.mapper.sys;

import java.util.Date;

import com.phone.cn.entity.sys.LoginLog;
import com.phone.cn.mapper.BaseMapper;

public interface LoginLogMapper extends BaseMapper<LoginLog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(LoginLog record);

    int insertSelective(LoginLog record);

    LoginLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoginLog record);

    int updateByPrimaryKey(LoginLog record);

	Integer loadDay_loginAmount(Date date);
}
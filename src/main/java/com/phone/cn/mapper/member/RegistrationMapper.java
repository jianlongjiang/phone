package com.phone.cn.mapper.member;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.phone.cn.entity.member.Registration;
import com.phone.cn.mapper.BaseMapper;

public interface RegistrationMapper extends BaseMapper<Registration, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(Registration record);

    int insertSelective(Registration record);

    Registration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Registration record);

    int updateByPrimaryKey(Registration record);
    
    Registration findOneDay(@Param("user") Integer user, @Param("startTime") Date startTime, @Param("endTime") Date endTime);
    
    Registration loadCountByTime( @Param("startDate") Date startTime, @Param("startDate") Date endTime);

	Integer loadDay_registrationAmount(Date time);
}
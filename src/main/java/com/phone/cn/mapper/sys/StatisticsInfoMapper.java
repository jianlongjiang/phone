package com.phone.cn.mapper.sys;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.cn.entity.sys.StatisticsInfo;
import com.phone.cn.mapper.BaseMapper;

public interface StatisticsInfoMapper extends BaseMapper<StatisticsInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(StatisticsInfo record);

    int insertSelective(StatisticsInfo record);

    StatisticsInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StatisticsInfo record);

    int updateByPrimaryKey(StatisticsInfo record);
    
    List<StatisticsInfo> findByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
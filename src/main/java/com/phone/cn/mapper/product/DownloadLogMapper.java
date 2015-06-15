package com.phone.cn.mapper.product;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.cn.entity.product.DownloadLog;
import com.phone.cn.entity.sys.StatisticsInfo;
import com.phone.cn.mapper.BaseMapper;

public interface DownloadLogMapper extends BaseMapper<DownloadLog, Integer>{
    int deleteByPrimaryKey(Integer id);

	int insert(DownloadLog record);

	int insertSelective(DownloadLog record);

	DownloadLog selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(DownloadLog record);

	int updateByPrimaryKeyWithBLOBs(DownloadLog record);

	int updateByPrimaryKey(DownloadLog record);


    
    List<DownloadLog> findByTime(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

	Integer loadDayDownAmount(Date  date);

	Integer loadDay_downloadPeople(Date date);

	Integer loadDayDownAmountForUser(@Param("userId") Integer userId, @Param("date") Date date);
	
	Integer loadDownAmountForUser(@Param("userId") Integer userId);

	List<StatisticsInfo> checkDateTime(Date time);
}
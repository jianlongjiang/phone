package com.phone.cn.mapper.member;

import java.util.List;

import com.phone.cn.entity.member.CashUserLog;
import com.phone.cn.mapper.BaseMapper;

public interface CashUserLogMapper extends BaseMapper<CashUserLog, Integer>{
    int deleteByPrimaryKey(Integer id);

	int insert(CashUserLog record);

	int insertSelective(CashUserLog record);

	CashUserLog selectByPrimaryKey(Integer id);
	
	CashUserLog selectByMobile(String mobile);

	int updateByPrimaryKeySelective(CashUserLog record);

	int updateByPrimaryKey(CashUserLog record);
	
	void  test();

	int userReadAll(Integer userId);

	Integer userGetRedCount(Integer userId);

	Integer loadUserDayRedCount(Integer userId);

	List<CashUserLog> loadCurDayLogs(Integer userId);

}
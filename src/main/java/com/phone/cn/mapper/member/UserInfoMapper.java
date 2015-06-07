package com.phone.cn.mapper.member;

import java.util.Date;

import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.mapper.BaseMapper;

public interface UserInfoMapper extends BaseMapper<UserInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

	int insert(UserInfo record);

	int insertSelective(UserInfo record);

	UserInfo selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserInfo record);

	int updateByPrimaryKeyWithBLOBs(UserInfo record);

	int updateByPrimaryKey(UserInfo record);

	UserInfo findByMobile(String mobile);

	int loadVipCount(UserInfoBean userInfoBean);

	Integer loadRegisterCount(UserInfoBean userInfoBean);

	Integer loadDayRegisterAmount(Date date);

//	public java.util.List<UserInfo> query(com.phone.cn.bean.SearchBean cc) ;
	Integer loadDay_vipAmount(Date date);

	Integer loadUserSecondCount(Integer userId);

}
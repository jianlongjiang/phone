package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.UserScoreLog;
import com.phone.cn.mapper.BaseMapper;

public interface UserScoreLogMapper  extends BaseMapper<UserScoreLog, Integer> {

    int insert(UserScoreLog record);

    int insertSelective(UserScoreLog record);

    UserScoreLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserScoreLog record);

    int updateByPrimaryKey(UserScoreLog record);

	void userReadAll(Integer userId);
}
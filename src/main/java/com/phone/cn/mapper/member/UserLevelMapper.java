package com.phone.cn.mapper.member;

import com.phone.cn.entity.member.UserLevel;
import com.phone.cn.mapper.BaseMapper;

public interface UserLevelMapper extends BaseMapper<UserLevel, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserLevel record);

    int insertSelective(UserLevel record);

    UserLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserLevel record);

    int updateByPrimaryKey(UserLevel record);
}
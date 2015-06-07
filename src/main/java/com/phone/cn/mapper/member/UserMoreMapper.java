package com.phone.cn.mapper.member;

import com.phone.cn.entity.member.UserMore;
import com.phone.cn.mapper.BaseMapper;

public interface UserMoreMapper  extends BaseMapper<UserMore, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(UserMore record);

    int insertSelective(UserMore record);

    UserMore selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserMore record);

    int updateByPrimaryKey(UserMore record);
}
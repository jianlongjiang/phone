package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.Message;
import com.phone.cn.mapper.BaseMapper;

public interface MessageMapper extends BaseMapper<Message, Integer>{
    int deleteByPrimaryKey(Integer id);

	int insert(Message record);

	int insertSelective(Message record);

	Message selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Message record);

	int updateByPrimaryKeyWithBLOBs(Message record);

	int updateByPrimaryKey(Message record);

	void userReadAll(Integer userId);

}
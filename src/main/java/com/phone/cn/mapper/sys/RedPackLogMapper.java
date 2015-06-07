package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.RedPackLog;
import com.phone.cn.mapper.BaseMapper;

public interface RedPackLogMapper extends BaseMapper<RedPackLog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(RedPackLog record);

    int insertSelective(RedPackLog record);

    RedPackLog selectByPrimaryKey(Integer id);
    
    RedPackLog findUserId(Integer id);

    int updateByPrimaryKeySelective(RedPackLog record);

    int updateByPrimaryKey(RedPackLog record);
}
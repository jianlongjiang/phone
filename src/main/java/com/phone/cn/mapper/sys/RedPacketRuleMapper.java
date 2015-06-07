package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.RedPacketRule;
import com.phone.cn.mapper.BaseMapper;

public interface RedPacketRuleMapper extends BaseMapper<RedPacketRule, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(RedPacketRule record);

    int insertSelective(RedPacketRule record);

    RedPacketRule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RedPacketRule record);

    int updateByPrimaryKey(RedPacketRule record);
}
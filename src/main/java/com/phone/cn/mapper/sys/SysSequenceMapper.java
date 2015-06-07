package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.SysSequence;
import com.phone.cn.mapper.BaseMapper;

public interface SysSequenceMapper extends BaseMapper<SysSequence, String>{
    int deleteByPrimaryKey(String seqCode);

    int insert(SysSequence record);

    int insertSelective(SysSequence record);

    SysSequence selectByPrimaryKey(String seqCode);

    int updateByPrimaryKeySelective(SysSequence record);

    int updateByPrimaryKey(SysSequence record);
}
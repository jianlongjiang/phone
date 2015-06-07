package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.OrdRecord;
import com.phone.cn.mapper.BaseMapper;

public interface OrdRecordMapper  extends BaseMapper<OrdRecord, Integer>{
    int deleteByPrimaryKey(Integer recordId);

    int insert(OrdRecord record);

    int insertSelective(OrdRecord record);

    OrdRecord selectByPrimaryKey(Integer recordId);

    int updateByPrimaryKeySelective(OrdRecord record);

    int updateByPrimaryKey(OrdRecord record);
}
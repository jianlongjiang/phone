package com.phone.cn.mapper.product;

import com.phone.cn.entity.product.UpDownLog;
import com.phone.cn.mapper.BaseMapper;

public interface UpDownLogMapper extends BaseMapper<UpDownLog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(UpDownLog record);

    int insertSelective(UpDownLog record);

    UpDownLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UpDownLog record);

    int updateByPrimaryKey(UpDownLog record);
}
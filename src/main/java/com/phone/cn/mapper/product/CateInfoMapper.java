package com.phone.cn.mapper.product;

import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.mapper.BaseMapper;

public interface CateInfoMapper extends BaseMapper<CateInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(CateInfo record);

    int insertSelective(CateInfo record);

    CateInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CateInfo record);

    int updateByPrimaryKey(CateInfo record);
}
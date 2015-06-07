package com.phone.cn.mapper.product;

import com.phone.cn.entity.product.NewsCate;
import com.phone.cn.mapper.BaseMapper;

public interface NewsCateMapper extends BaseMapper<NewsCate, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(NewsCate record);

    int insertSelective(NewsCate record);

    NewsCate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NewsCate record);

    int updateByPrimaryKey(NewsCate record);
}
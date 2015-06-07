package com.phone.cn.mapper.product;

import java.util.List;

import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.mapper.BaseMapper;

public interface NewsInfoMapper extends BaseMapper<NewsInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(NewsInfo record);

    int insertSelective(NewsInfo record);

    NewsInfo selectByPrimaryKey(Integer id);
    
    List<NewsInfo> findByCateId(Integer cateId);

    int updateByPrimaryKeySelective(NewsInfo record);

    int updateByPrimaryKeyWithBLOBs(NewsInfo record);

    int updateByPrimaryKey(NewsInfo record);
}
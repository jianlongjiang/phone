package com.phone.cn.mapper.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.mapper.BaseMapper;

public interface MobileInfoMapper extends BaseMapper<MobileInfo, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MobileInfo record);

    int insertSelective(MobileInfo record);

    MobileInfo selectByPrimaryKey(Integer id);
    
    MobileInfo findByMobile (String mobile);

    int updateByPrimaryKeySelective(MobileInfo record);

    int updateByPrimaryKey(MobileInfo record);
    
    List<Integer> getAllIds();
    
    List<Integer> getUserMobileIds();
    
    List<MobileInfo> searchByIds(@Param("ids") List<Integer> ids);

	List<MobileInfo> getAlls();
	 List<MobileInfo> getUserMobiles();

	 // select count(1)   from  xx  where  cate_ids  like '%aa%;'
	Integer loadCountByCateId(String cateIds);

	List<MobileInfo> userMobileInfos(boolean isVip);
}
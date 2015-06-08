package com.phone.cn.mapper.sys;

import com.phone.cn.entity.sys.MobileDownLog;
import com.phone.cn.mapper.BaseMapper;

public interface MobileDownLogMapper  extends BaseMapper<MobileDownLog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(MobileDownLog record);

    int insertSelective(MobileDownLog record);

    MobileDownLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileDownLog record);

    int updateByPrimaryKeyWithBLOBs(MobileDownLog record);

    int updateByPrimaryKey(MobileDownLog record);

	Integer loadMobileDownCount(String mobile);
}
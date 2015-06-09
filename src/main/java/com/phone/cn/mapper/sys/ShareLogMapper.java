package com.phone.cn.mapper.sys;

import com.phone.cn.bean.sys.ShareLogBean;
import com.phone.cn.entity.sys.ShareLog;
import com.phone.cn.mapper.BaseMapper;

public interface ShareLogMapper extends BaseMapper<ShareLog, Integer>{
    int deleteByPrimaryKey(Integer id);

    int insert(ShareLog record);

    int insertSelective(ShareLog record);

    ShareLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShareLog record);

    int updateByPrimaryKey(ShareLog record);

	Integer loadActionDayCount(ShareLogBean record);
}
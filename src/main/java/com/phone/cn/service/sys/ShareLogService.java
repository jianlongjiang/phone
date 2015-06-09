package com.phone.cn.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.sys.ShareLogBean;
import com.phone.cn.entity.sys.ShareLog;
import com.phone.cn.mapper.sys.ShareLogMapper;
import com.phone.cn.service.BaseService;

@Service
public class ShareLogService extends BaseService<ShareLog, Integer> {

	@Autowired
	private ShareLogMapper shareLogMapper;
	

	public Integer loadActionDayCount(ShareLogBean shareLogBean) {
		return shareLogMapper.loadActionDayCount(shareLogBean);
	}

	
}

package com.phone.cn.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.sys.RedPackLog;
import com.phone.cn.mapper.sys.RedPackLogMapper;
import com.phone.cn.service.BaseService;

@Service
public class RedPackLogService extends BaseService<RedPackLog, java.lang.Integer> {
	
	@Autowired
	private RedPackLogMapper mapper;
	
	/**
	 * 根据 userID查询redPackLog
	 * @param id
	 * @return
	 */
	public RedPackLog findUserId(Integer id){
		return mapper.findUserId(id);	
	}
}

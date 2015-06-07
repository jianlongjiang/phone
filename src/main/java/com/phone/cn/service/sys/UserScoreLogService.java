package com.phone.cn.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.sys.UserScoreLog;
import com.phone.cn.mapper.sys.UserScoreLogMapper;
import com.phone.cn.service.BaseService;

@Service
public class UserScoreLogService extends BaseService<UserScoreLog, Integer> {
	@Autowired
	UserScoreLogMapper  mapper;
	
	
	public void userReadAll(Integer userId) {
		mapper.userReadAll(userId);
	}

}

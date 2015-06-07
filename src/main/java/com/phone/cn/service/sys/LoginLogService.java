package com.phone.cn.service.sys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.sys.LoginLog;
import com.phone.cn.mapper.sys.LoginLogMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:00:39
 *   
 */
@Service
public class LoginLogService extends BaseService<LoginLog, Integer>{

	@Autowired
	LoginLogMapper  mapper;
	
	public Integer loadDay_loginAmount(Date time) {
		return mapper.loadDay_loginAmount(time);
	}

	

}

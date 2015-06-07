package com.phone.cn.service.product;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.product.DownloadLog;
import com.phone.cn.mapper.product.DownloadLogMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:58:37
 *   
 */
@Service
public class DownloadLogService extends BaseService<DownloadLog, Integer>{

		@Autowired
		DownloadLogMapper  mapper;
	
	public Integer loadDayDownAmount(Date time) {
		return mapper.loadDayDownAmount(time);
	}

	public Integer loadDay_downloadPeople(Date time) {
		return mapper.loadDay_downloadPeople(time);
	}

	public Integer loadDayDownAmount(UserInfo user, Date date) {
		return  mapper.loadDayDownAmountForUser(user.getId(), date);
	}
	
	public Integer loadDownAmount(UserInfo user) {
		return  mapper.loadDownAmountForUser(user.getId());
	}
	
	
	

}

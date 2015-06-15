package com.phone.cn.service.sys;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.product.DownloadLog;
import com.phone.cn.entity.sys.StatisticsInfo;
import com.phone.cn.mapper.product.DownloadLogMapper;
import com.phone.cn.mapper.sys.StatisticsInfoMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:01:34
 *   
 */
@Service
public class StatisticsInfoService extends BaseService<StatisticsInfo, Integer>{

	@Autowired
	private StatisticsInfoMapper statisticsInfoMapper;
	@Autowired
	private DownloadLogMapper downloadLogMapper;
	
	/**
	 *  日信息
	 * @return
	 */
	public Map<String, Object> todayInfo(){
		Calendar today=Calendar.getInstance();
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE,0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Date startTime = today.getTime();
		today.add(Calendar.DATE, 1);
		Date endTime = today.getTime();
		List<DownloadLog> list = findByTime(startTime, endTime);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer mobileAmount = 0;
		for (DownloadLog downloadLog : list) {
			mobileAmount += downloadLog.getMobilenum();
		}
		map.put("userAmount", list.size());
		map.put("mobileAmount", mobileAmount);
		return map;
	}
	
	public Map<String, Object> monthInfo(){
		Calendar today=Calendar.getInstance();
		today.set(Calendar.DAY_OF_MONTH, 1);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE,0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Date startTime = today.getTime();
		today.add(Calendar.MONTH, 1);
		Date endTime = today.getTime();
		List<DownloadLog> list = findByTime(startTime, endTime);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer mobileAmount = 0;
		for (DownloadLog downloadLog : list) {
			mobileAmount += downloadLog.getMobilenum();
		}
		map.put("userAmount", list.size());
		map.put("mobileAmount", mobileAmount);
		return map;
	}
	
	public Map<String, Object> yearInfo(){
		Calendar today=Calendar.getInstance();
		today.set(Calendar.DAY_OF_YEAR, 1);
		today.set(Calendar.HOUR_OF_DAY, 0);
		today.set(Calendar.MINUTE,0);
		today.set(Calendar.SECOND, 0);
		today.set(Calendar.MILLISECOND, 0);
		Date startTime = today.getTime();
		today.add(Calendar.YEAR, 1);
		Date endTime = today.getTime();
		List<DownloadLog> list = findByTime(startTime, endTime);
		Map<String, Object> map = new HashMap<String, Object>();
		Integer mobileAmount = 0;
		for (DownloadLog downloadLog : list) {
			mobileAmount += downloadLog.getMobilenum();
		}
		map.put("userAmount", list.size());
		map.put("mobileAmount", mobileAmount);
		return map;
	}
	
	public List<DownloadLog> findByTime(Date startTime, Date endTime){
		return downloadLogMapper.findByTime(startTime, endTime);
	}

	
}

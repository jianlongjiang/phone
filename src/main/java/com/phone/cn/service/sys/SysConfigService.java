package com.phone.cn.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.mapper.sys.SysConfigMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:01:47
 *   
 */
@Service
public class SysConfigService extends BaseService<SysConfig, Integer>{
	@Autowired
	private SysConfigMapper sysconfigMapper;

	@Cacheable(cacheName="day_cache_key")
	public List<SysConfig> findExperience(){
		List<SysConfig> list = new ArrayList<SysConfig>();
		list.add(sysconfigMapper.selectByPrimaryKey(1));
		list.add(sysconfigMapper.selectByPrimaryKey(2));
		list.add(sysconfigMapper.selectByPrimaryKey(3));
		return list;
	}
	
	@Cacheable(cacheName="day_cache_key")
	public List<SysConfig> findIntegral(){
		List<SysConfig> list = new ArrayList<SysConfig>();
		list.add(sysconfigMapper.selectByPrimaryKey(4));
		list.add(sysconfigMapper.selectByPrimaryKey(5));
		list.add(sysconfigMapper.selectByPrimaryKey(6));
		list.add(sysconfigMapper.selectByPrimaryKey(7));
		list.add(sysconfigMapper.selectByPrimaryKey(8));
		list.add(sysconfigMapper.selectByPrimaryKey(9));
		list.add(sysconfigMapper.selectByPrimaryKey(10));
		list.add(sysconfigMapper.selectByPrimaryKey(11));
		return list;
	}
	@Cacheable(cacheName="day_cache_key")
	public List<SysConfig> findGolden(){
		List<SysConfig> list = new ArrayList<SysConfig>();
		list.add(sysconfigMapper.selectByPrimaryKey(16));
		list.add(sysconfigMapper.selectByPrimaryKey(17));
		return list;
	}
	
	@Cacheable(cacheName="day_cache_key")
	public SysConfig findInvite(){
		return sysconfigMapper.selectByPrimaryKey(12);
	}
	@Cacheable(cacheName="day_cache_key")
	public SysConfig findGoldenSnail(){
		return sysconfigMapper.selectByPrimaryKey(13);
	}
	@Cacheable(cacheName="day_cache_key")
	public List<SysConfig> findManage(){
		List<SysConfig> list = new ArrayList<SysConfig>();
		list.add(sysconfigMapper.selectByPrimaryKey(14));
		list.add(sysconfigMapper.selectByPrimaryKey(15));
		return list;
	}
	
	
	
//	@CacheEvict(key="day_cache_key",value={}) 
	@TriggersRemove(cacheName="day_cache_key", removeAll=true)
	@Override
	public SysConfig save(SysConfig m) {
		return super.save(m);
	}

	public Integer loadDownLimit(UserInfo user) {
		String  value = null;
		if(user.getIsVip() != null && user.getIsVip()) {
			value =	sysconfigMapper.selectByPrimaryKey(SysConfigEnum.day_vip_downCount.getValue()).getConfigValue();
		}else {
			value =  sysconfigMapper.selectByPrimaryKey(SysConfigEnum.day_common_downCount.getValue()).getConfigValue();
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public SysConfig findOne(Integer id) {
		// TODO Auto-generated method stub
		return super.findOne(id);
	}
	
	
}

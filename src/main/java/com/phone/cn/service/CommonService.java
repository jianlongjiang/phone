package com.phone.cn.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public static final List<Map<String, Object>> BLANK_LIST = new ArrayList<Map<String, Object>>(0);

    @Autowired
    private EhCacheManagerFactoryBean cacheManager;
	
	/**
	 * 清除缓存
	 * @param cacheName
	 * @return
	 */
	public boolean clearCache(String cacheName) {
		CacheManager cacheManagerObject =  cacheManager.getObject();
		Cache cache = cacheManagerObject.getCache(cacheName);
		if (cache != null) {
			cache.removeAll();
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}
	
}

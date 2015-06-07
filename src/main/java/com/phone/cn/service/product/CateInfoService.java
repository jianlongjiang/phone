package com.phone.cn.service.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;
import com.phone.cn.bean.product.CateInfoBean;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.mapper.product.CateInfoMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:57:33
 *   
 */
@Service
public class CateInfoService extends BaseService<CateInfo, Integer>{

	@Autowired
	private CateInfoMapper cateInfoMapper;
	
	public List<CateInfo> searchFirstCates(){
		CateInfoBean c = new CateInfoBean();
		c.setCateLevel(1);
		return cateInfoMapper.query(c);
	}
	
	
	@Cacheable(cacheName="day_cache_key")
	public Map<Integer, CateInfo> findAllCateMap(){
		List<CateInfo> list = findAll();
		Map<Integer, CateInfo> map = new HashMap<Integer, CateInfo>();
		if(list!=null && !list.isEmpty()){
			for (CateInfo cateInfo : list) {
				map.put(cateInfo.getId(), cateInfo);
			}
		}
		return map;
	}
	
	public List<CateInfo> queryByParentId(Integer parentId) {
		CateInfoBean cataCateInfoBean = new CateInfoBean();
		cataCateInfoBean.setParentCateId(parentId);
		return 	super.queryAll(cataCateInfoBean) ;
	}

	public List<Integer> chooseIds(Integer firstCateId, Integer secondCateId) {
		if(firstCateId == null && secondCateId == null) return null;
		List<Integer> list = new ArrayList<Integer>();
		if(secondCateId != null) {
			list.add(secondCateId);
		}else {
			CateInfoBean bean = new CateInfoBean();
			bean.setParentCateId(firstCateId);
			List<CateInfo> seconds =  super.queryAll(bean);
			if(seconds != null) {
				for (CateInfo cateInfo : seconds) {
					list.add(cateInfo.getId());
				}
			}
		}
		return list;
	}
}

package com.phone.cn.service.product;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import base.BaseCRUDTest;

import com.phone.cn.bean.product.NewsInfoBean;
import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.mapper.product.NewsInfoMapper;

public class NewsInfoServiceTest extends BaseCRUDTest<NewsInfoBean, NewsInfo, Integer> {

	@Autowired
	NewsInfoService newsInfoService;
	@Autowired
	NewsInfoMapper newsInfoMapper;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void saveAll(){
		
		List<NewsInfo>  list = newsInfoService.queryAll(null);
		for (NewsInfo newsInfo : list) {
			newsInfo.setId(newsInfo.getId() + 20);
			newsInfoMapper.insert(newsInfo);
		}
		
	}
	

}

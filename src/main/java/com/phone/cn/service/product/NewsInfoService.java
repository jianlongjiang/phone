package com.phone.cn.service.product;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.SearchBean;
import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.mapper.product.NewsInfoMapper;
import com.phone.cn.service.BaseService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:59:39
 *   
 */
@Service
public class NewsInfoService extends BaseService<NewsInfo, Integer>{

	@Autowired
	private NewsInfoMapper newsInfoMapper;
	
	public List<NewsInfo> findByCateId(Integer cateId){
		return newsInfoMapper.findByCateId(cateId);
	}
	
	@Override
	public NewsInfo findOne(Integer id) {
		NewsInfo newsInfo = super.findOne(id);
		if(newsInfo != null){
			newsInfo.setNewsDesc(doEditor(newsInfo.getNewsDesc()));;
		}
		return newsInfo;
	}
	
	@Override
	public NewsInfo findOneView(Integer id) {
		NewsInfo newsInfo = super.findOne(id);
		if(newsInfo != null){
			newsInfo.setNewsDesc(doEditorForIndex(newsInfo.getNewsDesc()));;
		}
		return newsInfo;
	}
	
	public PageList<NewsInfo> queryAllWithPageApp(SearchBean bean,
			PageBounds pageBounds) {
		PageList<NewsInfo> list = super.queryAllWithPage(bean, pageBounds);
		if(list!=null){
			for (NewsInfo newsInfo : list) {
				String title = newsInfo.getTitle();
				String desc = newsInfo.getNewsDesc();
				if(StringUtils.isNotEmpty(title) && title.length() >= 13){
					newsInfo.setTitle(title.substring(0,13)+"...");
				}
				if(StringUtils.isNotEmpty(desc) && desc.length() >= 20){
					newsInfo.setNewsDesc(desc.substring(0,20)+"...");
				}
			}
		}
		return list;
	}
}

package com.phone.cn.action.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.product.NewsInfoBean;
import com.phone.cn.bean.product.UpDownLogBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.product.NewsCate;
import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.entity.product.UpDownLog;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.product.NewsCateService;
import com.phone.cn.service.product.NewsInfoService;
import com.phone.cn.service.product.UpDownLogService;
import com.phone.cn.utils.HtmlUtil;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseAppController;
import com.phone.cn.web.interceptor.AppUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月18日 下午12:06:36
 * 
 */

@Controller
@RequestMapping(value = "app/newsinfo")
public class NewsInfoAppAction extends BaseAppController<NewsInfoBean, NewsInfo, Integer> {
	@Autowired
	private NewsInfoService newsInfoService;
	@Autowired
	private NewsCateService newsCateService;
	@Autowired
	private UpDownLogService upDownLogService;

	/**
	 * 不登入拦截
	 * 
	 * @param baseApp
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "detail/{id}")
	public Map<String, Object> detail(BaseAppTokenBean baseApp, @PathVariable Integer id) {
		NewsInfo newsinfo = newsInfoService.findOneView(id);
		NewsInfoBean bean = new NewsInfoBean();
		bean.setCateId(newsinfo.getCateId());
		bean.setSort("update_time.desc");
		bean.setPageNo(1);
		bean.setPageSize(100);
		List<NewsInfo> list = newsInfoService.queryAllWithPageApp(bean, bean.toPageBounds());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("detail", newsinfo);
		Random random = new Random();
		List<NewsInfo>	rec_info = new ArrayList<NewsInfo>(3);
		while(rec_info.size()<=3 && list.size() > 0){
			int  index = random.nextInt(list.size());
			NewsInfo getInfo = list.remove(index);
			if(getInfo.getId().intValue() != id  ){
				getInfo.setTitle(HtmlUtil.htmlUnescape(getInfo.getTitle() , 10));
				getInfo.setNewsDesc(HtmlUtil.htmlUnescape(getInfo.getNewsDesc(), 50));
				rec_info.add(getInfo);
			}
		}
		map.put("rec_info", rec_info);
		UserInfo userInfo = baseApp.getAppUser();
		if (userInfo != null) {
			UpDownLog log = upDownLogService.loadLog(userInfo, id);
			if (log == null) {
				map.put("action", null);
			} else {
				map.put("action", log.getAction());
			}
		}
		return JsonMapper.beanToMap(map);
	}

	@ResponseBody
	@RequestMapping(value = "catelist")
	public Map<String, Object> catelist(@RequestParam(required = false) Integer cateId, @RequestParam Integer pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cates", newsCateService.findAll());
		NewsInfoBean bean = new NewsInfoBean();
		bean.setCateId(cateId);
		bean.setPageNo(pageNo);
		bean.setPageSize(10);
		map.put("infos", newsInfoService.queryAllWithPageApp(bean, bean.toPageBounds()));
		return JsonMapper.beanToMap(map);
	}

	/**
	 * 首页 home page (首页)
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "hp")
	public Map<String, Object> hp() {
		NewsInfoBean news = new NewsInfoBean();
		news.setPageNo(1);
		news.setPageSize(3);
		news.setSort("update_time.desc");
		Map<String, Object> map = new HashMap<String, Object>();
		// 首页
		List<NewsInfo> lastNews = newsInfoService.queryAllWithPage(news, news.toPageBounds());
		for (NewsInfo getInfo : lastNews) {
			getInfo.setNewsDesc(HtmlUtil.htmlUnescape(getInfo.getNewsDesc(), 50));
		}
		map.put("lastNews", lastNews);
		news.setPageSize(5);
		news.setSort("order_by.asc");
		// 新闻推荐
		map.put("firstNews", newsInfoService.queryAllWithPage(news, news.toPageBounds()));
		return suc(map);
	}

	/**
	 * 顶
	 * 
	 * @param baseApp
	 * @param upDownLogBean
	 * @return
	 */
	@AppUserLogin
	@ResponseBody
	@RequestMapping(value = "pointgood")
	public Map<String, Object> pointgood(BaseAppTokenBean baseApp, @Validated UpDownLogBean upDownLogBean) {
		try {
			upDownLogService.upOrDown(baseApp.getAppUser(), upDownLogBean, "up");
			return suc(SUCCESS);
		} catch (SimpleException e) {
			return fail(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 踩
	 * 
	 * @param baseApp
	 * @param upDownLogBean
	 * @return
	 */
	@AppUserLogin
	@ResponseBody
	@RequestMapping(value = "pointbad")
	public Map<String, Object> pointbad(BaseAppTokenBean baseApp, @Validated UpDownLogBean upDownLogBean) {
		try {
			upDownLogService.upOrDown(baseApp.getAppUser(), upDownLogBean, "down");
			return suc(SUCCESS);
		} catch (SimpleException e) {
			return fail(e.getCode(), e.getMsg());
		}
	}

	/**
	 * 新闻中心
	 * 
	 * @return
	 */
	@RequestMapping("newsCenter")
	@ResponseBody
	public Map<String, Object> newsCenter() {
		Map<String, Object> map = new HashMap<String, Object>();
		NewsInfoBean newsInfoBean = new NewsInfoBean();
		newsInfoBean.setPageNo(1);
		newsInfoBean.setPageSize(5);
		newsInfoBean.setSort("order_by.asc");

		List<NewsCate> cates = newsCateService.findAll();
		map.put("newscates", cates);
		Integer firstCateId = null;
		for (NewsCate c : cates) {
			map.put("news_" + c.getId(), newsInfoService.queryAllWithPage(newsInfoBean, newsInfoBean.toPageBounds()));
			if (firstCateId == null)
				firstCateId = c.getId();
			logger.debug("新闻中心---------------news_" + c.getId());
		}

		// 新闻推荐
		map.put("tops", newsInfoService.queryAllWithPage(newsInfoBean, newsInfoBean.toPageBounds()));
		newsInfoBean.setSort("update_time.desc");
		newsInfoBean.setCateId(firstCateId);
		// newsInfoBean.setPageSize(10);
		map.put("news", newsInfoService.queryAllWithPage(newsInfoBean, newsInfoBean.toPageBounds()));

		return suc(map);
	}

	@RequestMapping("newsCategory")
	@ResponseBody
	public Object newsCategory(BaseAppTokenBean baseApp, @RequestParam Integer id, @RequestParam Integer pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		NewsInfoBean bean = new NewsInfoBean();
		bean.setPageNo(pageNo);
		bean.setCateId(id);
		bean.setPageSize(5);
		List<NewsInfo> infos = newsInfoService.queryAllWithPageApp(bean, bean.toPageBounds());
//		for (NewsInfo newsInfo : infos) {
//			newsInfo.setNewsDesc(HtmlUtil.htmlUnescape(newsInfo.getNewsDesc() , 50));
//			newsInfo.setTitle(HtmlUtil.htmlUnescape(newsInfo.getTitle() , 10));
//		}
		map.put("newsCenterCate", infos);
		return suc(map);
	}

}

package com.phone.cn.service.product;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.product.UpDownLogBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.entity.product.UpDownLog;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.BaseService;

@Service
public class UpDownLogService extends BaseService<UpDownLog, Integer> {

	@Autowired
	private NewsInfoService newsInfoService;

	public boolean upOrDown(UserInfo appUser, UpDownLogBean upDownLogBean,
			String action) throws SimpleException {
		upDownLogBean.setUserId(appUser.getId());
		UpDownLog log = super.selectOneByExample(upDownLogBean);
		if (log == null) {
			NewsInfo news = newsInfoService.findOne(upDownLogBean.getNewsId());
			if (news == null) {
				throw  new SimpleException("新闻不存在",500);
			}
			if(StringUtils.equals("up", action))
				news.setPointGoodCount(news.getPointGoodCount() + 1);
			else if(StringUtils.equals("down", action)){
				news.setPointBadCount(news.getPointBadCount() + 1);
			}
			newsInfoService.isSave(news);

			log = new UpDownLog();
			log.setUserId(upDownLogBean.getUserId());
			log.setNewsId(upDownLogBean.getNewsId());
			log.setAction("up");
			super.isSave(log);
			return true;
		} else {
			throw  new SimpleException("知道了", 500);
		}
	}

	public UpDownLog loadLog(UserInfo userInfo, Integer newsId) {
		UpDownLogBean upDownLogBean = new UpDownLogBean();
		upDownLogBean.setUserId(userInfo.getId());
		upDownLogBean.setNewsId(newsId);
		return super.selectOneByExample(upDownLogBean);
	}

}

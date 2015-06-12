package com.phone.cn.action.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.product.MobileInfoBean;
import com.phone.cn.conf.enums.MobileInfoFromEnum;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.product.DownloadLogService;
import com.phone.cn.service.product.MobileService;
import com.phone.cn.service.sys.MobileDownLogService;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AppUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月18日 下午12:16:00
 *   
 */
@Controller
@RequestMapping(value="app/mobileinfo")
public class MobileInfoAppAction extends BaseCRUDController<MobileInfoBean, MobileInfo, Integer>{

	@Autowired
	private MobileService mobileService;
//	@Autowired
//	private UserInfoService userInfoService;
	@Autowired
	private SysConfigService sysConfigService;
//	@Autowired
//	private CateInfoService cateInfoService;
	@Autowired
	DownloadLogService  downloadLogService;
	
	@Autowired
	MobileDownLogService 	mobileDownLogService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ResponseBody
	@RequestMapping(value="detail/{id}")
	public Map<String, Object> findOne(@PathVariable Integer id){
		return suc(mobileService.findOne(id));
	}
	
	@ResponseBody
	@RequestMapping(value="downloadpage")
	public Map<String, Object> downloadpage(){
//		cateInfoService.
		return null;
	}
	
	@ResponseBody
	@RequestMapping(value="download")
	@AppUserLogin
	public Map<String, Object> downloadmobile(BaseAppTokenBean baseApp,Integer firstCateId, Integer secondCateId,@RequestParam Integer num){
		ResultBean b = new ResultBean();
		SysConfig s = sysConfigService.findOne(SysConfigEnum.dow_mobile_score.getValue());
		UserInfo user = baseApp.getAppUser();
		if(user.getIntegration() < Integer.parseInt(s.getConfigValue())*num   ){
			b.setIsSuccess(Boolean.FALSE);
			b.setMessage("对不起，积分不足！");
			return JsonMapper.beanToMap(b);
		}
		
//		String downloadmobile = user.getDownloadMobile();
		List<String> downMobiles = mobileDownLogService.loadDownMobiles(user);;
//		if(StringUtils.isNotEmpty(downloadmobile)){
//			mobileids = downloadmobile.split(",");
//		}
		
		// 必备手机号码下载
		MobileInfoBean mobileInfoBean = new MobileInfoBean();
		mobileInfoBean.setMore1("y");
		mobileInfoBean.setMobileFrom(MobileInfoFromEnum.ADMIN.getValue());
		List<MobileInfo> mustDowns = mobileService.queryAll(mobileInfoBean);
		
		
		
//		List<Integer>  chooseCateIds = cateInfoService.chooseIds(firstCateId, secondCateId);
//		List<Integer> allOtherIds = mobileService.allOtherIds();
		// 导入的手机号码
		List<MobileInfo> allOthers = mobileService.allOthers();
		List<MobileInfo> otherMobiles  = mobileService.doFilter(allOthers , firstCateId, secondCateId);
//		List<Integer> allUserMobileIds = mobileService.allUserMobileIds();
//		用户注册的手机号码
//		List<MobileInfo> allUserMobiles = mobileService.allUserMobiles();
//		List<Integer> allUserMobileIds = mobileService.doFilter(allUserMobiles , firstCateId, secondCateId);
		List<MobileInfo> vipMobileInfos = mobileService.userMobileInfos(true);
		List<MobileInfo> normalpMobileInfos = mobileService.userMobileInfos(false);
		
		Integer  dayDown_last = downloadLogService.loadDayDownAmount(  user, new Date());
		Integer  dayDownLimit = sysConfigService.loadDownLimit(user);
		dayDown_last = dayDown_last==null?0:dayDown_last;
		dayDownLimit = dayDownLimit==null?0:dayDownLimit;
		
//		if(dayDown_last >= dayDownLimit){
//			num = 0;
//		}else if(dayDown_last + num > dayDownLimit ){
//			num = dayDownLimit - dayDown_last;
//		}
//		if(num <= 0){
//			return  fail("今日下载已达到上限");
//		}
		
		return suc(mobileService.getMobiles(user, num, downMobiles,mustDowns,  vipMobileInfos, normalpMobileInfos,otherMobiles, s));
	}
}

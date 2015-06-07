package com.phone.cn.action.member;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.member.CashUserLogBean;
import com.phone.cn.entity.member.CashUserLog;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.member.CashUserLogService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.member.UserMoreService;
import com.phone.cn.service.sys.RedPackLogService;
import com.phone.cn.web.action.BaseAppController;
import com.phone.cn.web.interceptor.AppUserLogin;

@Controller
@RequestMapping("app/redPack")
@AppUserLogin
public class CashUserLogAppAction extends BaseAppController<CashUserLogBean, CashUserLog, Integer> {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	CashUserLogService  cashUserLogService;
	@Autowired
	private RedPackLogService redPackLogService;
	@Autowired
	private UserMoreService userMoreService;

	/**
	 * 红包提权 现金
	 * 
	 * @param baseAppTokenBean
	 * @param money
	 * @return
	 */
	@RequestMapping("reflectMoney")
	@ResponseBody
	@AppUserLogin
	public Object reflectMoney(BaseAppTokenBean baseAppTokenBean, Double money) {
		try {
			UserInfo userInfo = baseAppTokenBean.getAppUser();
			userInfoService.reflectMoney(userInfo, Math.abs(money));
			
			//  backTime = "";
			//TODO 预计到站时间
			return suc( SUCCESS,  new Date() );
		} catch (SimpleException e) {
			e.printStackTrace();
			return fail(e.getCode(), e.getMsg());
		}
	}

	@AppUserLogin
	@Override
	public Map<String, Object> list( BaseAppTokenBean baseApp, CashUserLogBean bean) {
		
		
//		Map<String, Object> map = new HashMap<String, Object>();
		UserInfo userInfo = baseApp.getAppUser();
		bean.setUserId(userInfo.getId());
		// bean.setPageSize(pageSize);
		bean.setSort("update_time.desc");
		//  信息 已读
		cashUserLogService.userReadAll(userInfo.getId());
//		suc(baseService.queryPage(bean));
		//map.put("cashUserLogs", baseService.queryPage(bean));
		return  suc(baseService.queryPage(bean));
	}
	
	
	@RequestMapping("revceiveTime")
	@ResponseBody
	public  Object  revceiveTime(BaseAppTokenBean baseApp){
		//TODO  到账时间
		return  suc(new Date());
	}
	
}

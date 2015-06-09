package com.phone.cn.action.sys;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.sys.ShareLogBean;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.ShareLog;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.sys.ShareLogService;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.web.action.BaseCRUDController;


@Controller
@RequestMapping("app/shareLog")
public class ShareLogAppAction extends BaseCRUDController<ShareLogBean, ShareLog, Integer> {
		
	@Autowired
	private ShareLogService shareLogService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private UserInfoService userInfoService;
	
		@RequestMapping("shareFirend")
		@ResponseBody
		public Object  shareFirend(BaseAppTokenBean baseApp){
			UserInfo userInfo = baseApp.getAppUser();
			
			ShareLogBean shareLogBean = new ShareLogBean();
			shareLogBean.setUserId(userInfo.getId());
			shareLogBean.setAction("friend");
			shareLogBean.setCreateTime(new Date());
			Integer  dayCount = shareLogService.loadActionDayCount(shareLogBean);
			
			if(dayCount == null || dayCount.intValue()==0){
				SysConfig sysConfig =  sysConfigService.findOne(SysConfigEnum.to_share_firend.getValue());
				if(sysConfig != null){
					userInfoService.addIntegration(userInfo, null, "分享好友", Integer.parseInt(sysConfig.getConfigValue()), false);
				}
				
			}
			return  suc(SUCCESS);
		}
}

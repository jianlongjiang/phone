package com.phone.cn.action.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.bean.sys.StatisticsInfoBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.sys.StatisticsInfo;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.sys.StatisticsInfoService;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:58:32
 *   
 */
@Controller
@RequestMapping(value="admin/statistics")
@AdminUserLogin
public class StatisticsInfoAction extends BaseCRUDController<StatisticsInfoBean, StatisticsInfo, Integer>{
	@Autowired
	private StatisticsInfoService statisticsInfoService;
	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping(value="tongji")
	public String tongji(Model model){
		model.addAttribute("todayTongji", statisticsInfoService.todayInfo());
		model.addAttribute("monthTongji", statisticsInfoService.monthInfo());
		model.addAttribute("yearTongji", statisticsInfoService.yearInfo());
		return viewName("list");
	}
	
	@Override
	public String list(StatisticsInfoBean bean, Model model, @PathVariable Integer pageNo) {
		bean.setSort("update_time.desc");
		return super.list(bean, model, pageNo);
	}
	@RequestMapping("vipList/p{pageNo}")
	public String listVip(UserInfoBean bean, Model model, @PathVariable Integer pageNo,Integer goldenNum,
			Integer inviteNum,Integer maxMoney) {
//		bean.setSort("update_time.desc");
//		super.list(bean, model, pageNo);
		if (pageNo==null) {
			pageNo = 1;
		}
		logger.debug("统计参数----------------------int"+goldenNum+"--------"+inviteNum+"------"+maxMoney);
		if (goldenNum!=null && inviteNum==null && maxMoney==null) {
			bean.setSort("invite_friend_vip_amount.desc");
			bean.setPageSize(goldenNum);
		}
		if (goldenNum==null && inviteNum!=null && maxMoney==null) {
			bean.setSort("Invite_friend_amount.desc");
			bean.setPageSize(inviteNum);
		}
		if (goldenNum==null && inviteNum==null && maxMoney!=null) {
//			bean.setSort(" (balance -reflect_red).desc");
			bean.setCondition("  order by (balance -reflect_red) desc ");
//			bean.setSort("reflect_red.desc");
			bean.setPageSize(maxMoney);
		}
		bean.setPageNo(pageNo);
		model.addAttribute("bean",bean);
		PageList<UserInfo> userInfos = userInfoService.queryAllWithPage(bean, bean.toPageBounds());
		model.addAttribute("page", userInfos.getPaginator());
		model.addAttribute("infos", userInfos);
		
		return viewName("vipList");
	}
}

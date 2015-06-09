package com.phone.cn.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.member.UserMore;
import com.phone.cn.service.member.CashUserLogService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.member.UserLevelService;
import com.phone.cn.service.member.UserMoreService;
import com.phone.cn.service.product.DownloadLogService;
import com.phone.cn.service.product.NewsInfoService;
import com.phone.cn.utils.URLUtils;
/**
 * @author zgdcool
 * @version 2015年1月14日 上午10:42:52
 */
@Controller
public class IndexAction {
	@Autowired
	private NewsInfoService newsInfoService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private UserLevelService userLevelService;
	@Autowired
	private  DownloadLogService downloadLogService;
	
	@Autowired
	private UserMoreService userMoreService;
	
	@Autowired
	CashUserLogService cashUserLogService;
	@RequestMapping(value="index/{id}")
	public String index(@PathVariable Integer id, Model model){
		model.addAttribute("info", newsInfoService.findOne(id));
		model.addAttribute("ios", false);
		return "index";
	}
	
	@RequestMapping(value="index2/{id}")
	public String index2(@PathVariable Integer id, Model model){
		model.addAttribute("ios", true);
		model.addAttribute("info", newsInfoService.findOne(id));
		return "index";
	}
	//memberindex/117
	@RequestMapping(value="memberindex/{id}")
	public String memberIndex(@PathVariable Integer id, Model model){
		UserInfo user = userInfoService.findOne(id);
		model.addAttribute("levelName", userLevelService.findLevelName(user.getExperience()));
		model.addAttribute("info", user);
		model.addAttribute("ios", false);
		
		UserMore userMore = userMoreService.findOne(user.getId());
		model.addAttribute("userMore", userMore);
		
		if(user == null || !user.getIsVip())
			return "memberindex";
		model.addAttribute("groupCount", URLUtils.loadUserGroupMount(user.getId()));
		return "vipMember";
	}
	
	@RequestMapping(value="memberindex2/{id}")
	public String memberIndex2(@PathVariable Integer id, Model model){
		model.addAttribute("ios", true);
		UserInfo user = userInfoService.findOne(id);
		model.addAttribute("levelName", userLevelService.findLevelName(user.getExperience()));
		model.addAttribute("info", user);
		return "memberindex";
	}
	
	@RequestMapping(value="teamMessage2/{id}")
	public  String teamMessage2( @PathVariable Integer id, Model model){
		String  str = teamMessage(id, model);
		model.addAttribute("ios", true);
		return str;
	}
	
	
	@RequestMapping(value="teamMessage/{id}")
	public  String teamMessage( @PathVariable Integer id, Model model){
		UserInfo userInfo = userInfoService.findOne(id); 
		if(userInfo != null ){
			
//		UserInfoBean  userInfoBean1 = new UserInfoBean();
//		userInfoBean1.setInviteesId(userInfo.getId());
//		userInfoBean1.setSort("create_time.asc");
//		List<UserInfo>  list1 = userInfoService.queryAll(userInfoBean1);
//		int  dayInvitees = 0;
//		int dayVipCount  = 0 ;
//		Date now = new Date();
//		for (UserInfo u : list1) {
//			if(u.getCreateTime() != null && DateUtils.isSameDay(u.getCreateTime(), now)){
//				dayInvitees ++;
//				if(u.getVipTime() !=null && DateUtils.isSameDay(u.getVipTime(), now)){
//					dayVipCount ++;
//					
//				}
//			}
//		}
//		
//		//我的好友  推荐人
//		model.addAttribute("allInviteesCount", list1.size());
//		// 自己下载数量
//		Integer downCount = downloadLogService.loadDownAmount(userInfo) ;
//		model.addAttribute("downCount", downCount == null? 0 :	downCount );
//		// 积分shu
//		model.addAttribute("allScore", userInfo.getIntegration() );
//		
////		今天共邀请了
//		model.addAttribute("dayInviteesCount", dayInvitees );
//		//今日邀请金蜗牛
//		model.addAttribute("dayVipCount", dayVipCount);
//		//  二级好友 今日新增
//		Integer  secondFriends = userInfoService.loadUserSecondCount(userInfo.getId());
//		secondFriends = secondFriends==null? 0:secondFriends;
//		model.addAttribute("dayGroupCount", dayInvitees + secondFriends);
//		
//		// 今日收到红包
//		model.addAttribute("dayRedCount", cashUserLogService.loadUserDayRedCount(userInfo.getId()));
//		
//
//		model.addAttribute("vipCount", userInfo.getInviteFriendVipAmount() );
//		// 团队人数
//		model.addAttribute("grounpCount", URLUtils.loadUserGroupMount(userInfo.getId()) );
//		model.addAttribute("getRedCount",  cashUserLogService.userGetRedCount(userInfo)  );
//		model.addAttribute("friends", list1);
			
			UserInfoBean  userInfoBean1 = new UserInfoBean();
			userInfoBean1.setInviteesId(userInfo.getId());
			userInfoBean1.setSort("create_time.asc");
			List<UserInfo>  list1 = userInfoService.queryAll(userInfoBean1);
			int  dayInvitees = 0;
			int dayVipCount  = 0 ;
			Date now = new Date();
			//Integer  groupCount = list1.size();
			List<UserInfo> groupFriends = new ArrayList<UserInfo>(list1);
			@SuppressWarnings("unused")
			int  groupVipCount  = 0;
			int  friendVipCount = 0;
			for (UserInfo u : list1) {
				if(u.getCreateTime() != null && DateUtils.isSameDay(u.getCreateTime(), now)){
					dayInvitees ++;
					if(u.getVipTime() !=null && DateUtils.isSameDay(u.getVipTime(), now)){
						dayVipCount ++;
						
					}
				}
				if(u.getIsVip()){
					groupVipCount ++;
					friendVipCount++;
				}
				UserInfoBean userInfoBean2 = new UserInfoBean();
				userInfoBean2.setInviteesId(u.getId());
				List<UserInfo> secondList = userInfoService.queryAll(userInfoBean2);
				if(secondList != null) {
					groupFriends.addAll(secondList);
					for (UserInfo userInfo2 : secondList) {
//						groupCount++;
						if(userInfo2.getCreateTime() != null && DateUtils.isSameDay(userInfo2.getCreateTime(), now)){
							dayInvitees ++;
							if(userInfo2.getVipTime() !=null && DateUtils.isSameDay(userInfo2.getVipTime(), now)){
								dayVipCount ++;
							}
						}
						
						if(userInfo2.getIsVip()){
							groupVipCount ++;
						}
						
					}
				}
			}
			
			Integer inviteesId = userInfo.getInviteesId();
			
			
			if(inviteesId != null){
				model.addAttribute("inviteesNo", userInfoService.findOne(inviteesId).getMobile());
			}else  {
				model.addAttribute("inviteesNo", "");
			}
			//我的好友  推荐人
			model.addAttribute("allInviteesCount", list1.size());
			// 自己下载数量
			Integer downCount = downloadLogService.loadDownAmount(userInfo) ;
			model.addAttribute("downCount", downCount == null? 0 :	downCount );
			// 积分shu
			model.addAttribute("allScore", userInfo.getIntegration() );
			
//			今天共邀请了
			model.addAttribute("dayInviteesCount", dayInvitees );
			//今日邀请金蜗牛
			model.addAttribute("dayVipCount", dayVipCount);
			//  二级好友 今日新增
			Integer  secondFriends = userInfoService.loadUserSecondCount(userInfo.getId());
			secondFriends = secondFriends==null? 0:secondFriends;
			model.addAttribute("dayGroupCount", dayInvitees + secondFriends);
			
			// 今日收到红包
			model.addAttribute("dayRedCount", cashUserLogService.loadUserDayRedCount(userInfo.getId()));
			
		//	map.put("vipCount", userInfo.getInviteFriendVipAmount() );
			model.addAttribute("vipCount", friendVipCount );
			// 团队人数
			model.addAttribute("grounpCount", groupFriends.size() );
			model.addAttribute("getRedCount",  cashUserLogService.userGetRedCount(userInfo)  );
			model.addAttribute("friends", list1);
		}
		model.addAttribute("ios", false);
		return  "teamShare";
	}
	
}

package com.phone.cn.service.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.conf.enums.CashUserLogActionEnum;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.entity.member.CashUserLog;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.member.UserMore;
import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.entity.sys.UserScoreLog;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.mapper.member.UserInfoMapper;
import com.phone.cn.mapper.sys.MessageMapper;
import com.phone.cn.service.BaseService;
import com.phone.cn.service.product.MobileService;
import com.phone.cn.service.sys.RedPacketRuleService;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.service.sys.UserScoreLogService;
import com.phone.cn.utils.JsonMapper;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:51:59
 * 
 */
@Service
public class UserInfoService extends BaseService<UserInfo, Integer> {

	@Autowired
	private UserInfoMapper mapper;
	@Autowired
	private MessageMapper messageMapper;

	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private UserScoreLogService userScoreLogService;
	@Autowired
	private CashUserLogService cashUserLogService;
	@Autowired
	RedPacketRuleService redPacketRuleService;
	@Autowired
	private MobileService mobileService;

	@Autowired
	UserMoreService userMoreService;

	public UserInfo findByMobile(String mobile) {
		return mapper.findByMobile(mobile);
	}

	public List<UserInfo> fingByInviteesId(Integer inviteesId) {
		UserInfoBean bean = new UserInfoBean();
		bean.setInviteesId(inviteesId);
		return mapper.query(bean);
	}

	public boolean hasEmail(String email) {
		if (email == null)
			return true;
		return findByMobile(email) != null;
	}

	@Transactional
	public Boolean jifenhongbao(UserInfo formUser, String mobile, Integer jifen) {

		if(StringUtils.equals(formUser.getMobile(), mobile))  return  false;
		UserInfo mobileUser = mapper.findByMobile(mobile);
		if (jifen.intValue() < 0) {
			return false;
		}
		if (formUser != null && mobileUser != null && formUser.getIntegration().intValue() >= jifen.intValue()) {
			addIntegration(formUser, null, "发积分红包", -jifen, Boolean.TRUE);
			mapper.updateByPrimaryKeySelective(formUser);
			
			
			addIntegration(mobileUser, null, formUser.showName() + "发你积分红包", jifen, Boolean.FALSE);

			// Message m = new Message();
			// m.setContent(formUser.getUserName()+"赠送你"+jifen+"积分");
			// m.setCreateTime(new Date());
			// m.setCreateUser(formUser.getUserName());
			// m.setUpdateTime(new Date());
			// m.setUpdateUser(formUser.getUserName());
			// m.setTitle("积分赠送");
			// // m.setUserId(toUserId);
			// m.setUserName(formUser.getUserName());
			// m.setMobile(formUser.getMobile());
			// m.setAuditStatus("user");
			// messageMapper.insertSelective(m);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * @Title: generateWorkbook
	 * @Description: 根据条件生成excel文本对象
	 * @param condition
	 * @return HSSFWorkbook 返回类型
	 * @throws
	 */
	@SuppressWarnings("resource")
	public HSSFWorkbook generateWorkbook2(Map<String, Object> condition11, UserInfoBean bean) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		List<UserInfo> list = mapper.query(bean);
		if (list != null) {
			// excel文件名
			String sheetName = "蜗牛巴巴";
			try {
				String[] EnrollArray = new String[] { "id", "userName", "mobile", "registTime", "InviteFriendAmount", "downloadMobileAmount",
						"experience" };
				workbook = this.resultSetToExcel(JsonMapper.beanToList(list), sheetName, EnrollArray);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return workbook;
	}

	/**
	 * @Title: resultSetToExcel
	 * @Description: 根据结果集生成excel
	 * @param rs
	 *            数据集
	 * @param sheetName
	 *            工作表名称
	 * @return HSSFWorkbook 返回类型
	 * @throws
	 */
	public HSSFWorkbook resultSetToExcel(List<Map<String, Object>> list, String sheetName, String[] names) throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// workbook.setSheetName(0,sheetName,HSSFWorkbook..ENCODING_UTF_16);
		HSSFRow row = sheet.createRow((short) 0);
		HSSFCell cell = null;
		for (int i = 0; i < names.length; i++) {
			cell = row.createCell(i);
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue(names[i]);
		}
		int iRow = 1;
		for (Map<String, Object> map : list) {
			row = sheet.createRow((short) iRow);
			for (int i = 0; i < names.length; i++) {
				cell = row.createCell(i);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				Object oj = map.get(names[i]);
				if (oj == null) {
					oj = "";
				}
				cell.setCellValue(oj.toString());
			}
			iRow++;
		}
		return workbook;
	}

	public Integer loadVipCount() {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setIsVip(true);
		return super.queryAll(userInfoBean).size();
	}

	public Integer loadVipCount(Date startDate, Date endDate) {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setIsVip(true);
		userInfoBean.setStartDate(startDate);
		userInfoBean.setEndDate(endDate);
		return mapper.loadVipCount(userInfoBean);
	}

	public Integer loadRegisterCount(Date startDate, Date endDate) {
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setIsVip(true);
		userInfoBean.setStartDate(startDate);
		userInfoBean.setEndDate(endDate);
		return mapper.loadRegisterCount(userInfoBean);
	}

	public Integer loadDayRegisterAmount(Date date) {
		return mapper.loadDayRegisterAmount(date);
	}

	public Integer loadDay_vipAmount(Date date) {
		return mapper.loadDay_vipAmount(date);
	}

	public void toBeVip(UserInfo userInfo) throws SimpleException {
		// 成为会员, 被推荐人 获取积分
//		if (userInfo.getIsVip() != null && userInfo.getIsVip()) {
//			throw new SimpleException("您已经是金蜗牛!");
//		}
		if (userInfo.getInviteesId() != null) {
			UserInfo inviteeUser = super.findOne(userInfo.getInviteesId());
			if (inviteeUser != null && inviteeUser.getIsVip() != null && inviteeUser.getIsVip()) {
				SysConfig otherConfig = sysConfigService.findOne(SysConfigEnum.be_vip_other.getValue());
				addIntegration(inviteeUser, null, "好友成为会员", Integer.parseInt(otherConfig.getConfigValue()), Boolean.FALSE);
				// 对建人, vip人数
				Integer count = inviteeUser.getInviteFriendVipAmount();
				count = count == null ? 0 : count;
				inviteeUser.setInviteFriendVipAmount(count + 1);
				// 推荐人 发送红包
				// 待写, 1: 当前用户第几次拿红包, 红包规则
				int  groupMoney = redPacketRuleService.sendRedPack(inviteeUser, userInfo);
				save(inviteeUser);

				UserInfo secondnviteeUser = super.findOne(inviteeUser.getInviteesId());
				if (secondnviteeUser != null && secondnviteeUser.getIsVip() != null && secondnviteeUser.getIsVip()) {
					otherConfig = sysConfigService.findOne(SysConfigEnum.be_vip_other.getValue());
					addIntegration(secondnviteeUser, null, "团队成员晋升为金蜗牛", Integer.parseInt(otherConfig.getConfigValue()), Boolean.FALSE);
					// 对建人, vip人数
					// Integer count = inviteeUser.getInviteFriendVipAmount();
					// count = count == null ? 0 : count;
					// inviteeUser.setInviteFriendVipAmount(count + 1);
					// 推荐人 发送红包
					// 待写, 1: 当前用户第几次拿红包, 红包规则
//					redPacketRuleService.sendRedPack(secondnviteeUser, userInfo);
					redPacketRuleService.sendGroupRedPack(secondnviteeUser, userInfo, groupMoney);
					save(secondnviteeUser);

				}

			}

		}
		SysConfig sysConfig = sysConfigService.findOne(SysConfigEnum.be_vip_own.getValue());
		addIntegration(userInfo, null, "成为会员", Integer.parseInt(sysConfig.getConfigValue()), Boolean.FALSE);

	}

	/**
	 * 
	 * @param userInfo
	 * @param action
	 *            可以为null
	 * @param msg
	 * @param integration
	 * @param isSee
	 */
	public void addIntegration(UserInfo userInfo, String action, String msg, Integer integration, boolean isSee) {
		if(integration == null || integration.intValue() == 0	) return ;
		Integer oldScore = userInfo.getIntegration();
		if (oldScore == null)
			oldScore = 0;

		UserScoreLog userScoreLog = new UserScoreLog();
		userScoreLog.setAction(action);
		userScoreLog.setMsg(msg);
		userScoreLog.setIsSee(isSee);
		userScoreLog.setUserId(userInfo.getId());
		userScoreLog.setInt1(oldScore);
		userScoreLog.setValue(integration);
		// userScoreLog.setInt1(oldScore);
		userScoreLogService.save(userScoreLog);
		userInfo.setIntegration(oldScore + integration);
		super.save(userInfo);
	}

	public void addMoney(UserInfo userInfo, String action, String msg, Double money, boolean isSee, Integer managerId) throws SimpleException {
		UserMore userMore = userMoreService.findOne(userInfo.getId());
		if (StringUtils.equals(CashUserLogActionEnum.USER.getValue(), action) && (userMore == null || StringUtils.isBlank(userMore.getAlipayAccount()))) {
			throw new SimpleException("支付宝未绑定");
		}

		CashUserLog cashUserLog = new CashUserLog();
		Double oldbalance = userInfo.getBalance();
		Double oldreflectRed = userInfo.getReflectRed();
		oldbalance = oldbalance == null ? 0 : oldbalance;
		oldreflectRed = oldreflectRed == null ? 0 : oldreflectRed;
		// 支付宝数据
		if (userMore != null) {
			cashUserLog.setAplipay(userMore.getAlipayAccount());
			cashUserLog.setMore2(userMore.getAlipayRealName());
			cashUserLog.setMobile(userInfo.getMobile());
		}
		cashUserLog.setUserId(userInfo.getId());
		cashUserLog.setAction(action);
		cashUserLog.setManagerId(managerId);
		if (managerId != null) {
			cashUserLog.setDoStatus("1");
		}
		cashUserLog.setMore1(msg);
		cashUserLog.setCash(money);
		cashUserLog.setIsSee(isSee);
		cashUserLog.setInt1(oldbalance.intValue());

		cashUserLog.setDoStatus(CashUserLog.doStatusEnum.reflect.getValue());
		cashUserLogService.save(cashUserLog);

		// 金额数据变换
		userInfo.setBalance(oldbalance + money);
		if (money < 0) {
			userInfo.setReflectRed(oldreflectRed + money);
		}
		super.save(userInfo);

	}

	public void reflectMoney(UserInfo userInfo, double money) throws SimpleException {
		
		if(money %10 != 0){
			throw new SimpleException("余额必须为10的整数倍!");
		}
		List<CashUserLog> list = cashUserLogService.loadLastLog(userInfo.getId());
		if(list!= null && list.size() > 0){
			throw new SimpleException("每天只能提现一次");
		}
		
		if (userInfo.getBalance().doubleValue() < money) {
			throw new SimpleException("余额不足");
		}
		addMoney(userInfo, "use", "提取红包", -money, true, null);
	}

	// 添加伪ID
	public String addFakeId(UserInfo u) {
		logger.debug("==========================>添加伪ID ");
		String fakeID = "";
		MobileInfo mobileInfo = mobileService.findByMobile(u.getMobile());
		if (mobileInfo != null) {
			fakeID = mobileInfo.getFakeId();
		} else {
			Integer str = 800000000 + u.getId();
			fakeID = "w" + str;
		}
		u.setFakeId(fakeID);
		mapper.updateByPrimaryKeySelective(u);
		return  u.getFakeId();
	}

	public Integer loadUserSecondCount(Integer userId) {
		return mapper.loadUserSecondCount(userId);
	}
	
	//团队人数查询
//	public Integer groupNum(Integer id){
//		UserInfoBean userInfoBean = new UserInfoBean();
//		userInfoBean.setInviteesId(id);
//		int  count = 0;
//		List<UserInfo> firstList = mapper.query(userInfoBean);
//		if(CollectionUtils.isNotEmpty(firstList)){
//			for (UserInfo userInfo : firstList) {
//				count ++;
//				UserInfoBean userInfoBean2 = new UserInfoBean();
//				userInfoBean2.setInviteesId(userInfo.getId());
//				List<UserInfo> secondList = mapper.query(userInfoBean);
//				if(secondList != null) {
//					count += secondList.size();
//				}
//			}
//		}
//		return  count;
//	}

	@Override
	protected void initSave(UserInfo m) {
		if(m.getIsVip() == null){
			m.setIsVip(false);
		}
	}
}

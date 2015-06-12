package com.phone.cn.action.member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.BaseAppTokenBean;
import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.member.CashUserLogBean;
import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.bean.member.UserScoreLogBean;
import com.phone.cn.bean.product.MobileInfoBean;
import com.phone.cn.bean.sys.MessageBean;
import com.phone.cn.conf.AppConfig;
import com.phone.cn.conf.DataConfig;
import com.phone.cn.conf.enums.MobileInfoFromEnum;
import com.phone.cn.conf.enums.SysConfigEnum;
import com.phone.cn.constant.ErrorCodeConstant;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.member.UserMore;
import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.exception.SimpleException;
import com.phone.cn.service.member.CashUserLogService;
import com.phone.cn.service.member.RegistrationService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.member.UserLevelService;
import com.phone.cn.service.member.UserMoreService;
import com.phone.cn.service.product.DownloadLogService;
import com.phone.cn.service.product.MobileService;
import com.phone.cn.service.sys.MessageService;
import com.phone.cn.service.sys.SysConfigService;
import com.phone.cn.service.sys.UserScoreLogService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.utils.PasswordUtils;
import com.phone.cn.web.MySessionContext;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AppUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月18日 上午11:56:00
 * 
 */
@Controller
@RequestMapping(value = "app/userinfo")
@Transactional
//app/userinfo/findMobile
public class UserInfoAppAction extends BaseCRUDController<UserInfoBean, UserInfo, Integer> {

	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private SysConfigService sysConfigService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private MobileService mobileService;
	@Autowired
	private UserLevelService userLevelService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserScoreLogService userScoreLogService;
	@Autowired
	private CashUserLogService cashUserLogService;

	@Autowired
	private DownloadLogService downloadLogService;

	@Autowired
	private UserMoreService userMoreService;

	@RequestMapping("modifyPwd")
	@ResponseBody
	@AppUserLogin
	public Object modifyPwd(BaseAppTokenBean baseApp, @RequestParam String password, @RequestParam String newPwd) {
		UserInfo userInfo = baseApp.getAppUser();
		try {
			if (PasswordUtils.validPassword(userInfo.getPassword(), password)) {
				userInfo.setPassword(PasswordUtils.encrypt(newPwd));
				baseService.update(userInfo);
				return suc(SUCCESS);
			} else {
				return fail("原密码错误");
			}
		} catch (Exception e) {
		}
		return fail(FAIL);
	}

	/**
	 * 用户详情
	 * 
	 * @param baseApp
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "detail")
	@AppUserLogin
	public Map<String, Object> detail(BaseAppTokenBean baseApp) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("session的ID=" + baseApp.getAppUser().getId());
		UserInfo user = userInfoService.findOne(baseApp.getAppUser().getId());
		System.out.println(user.getInviteesId());
		map.put("levelName", userLevelService.findLevelName(user.getExperience()));
		map.put("userinfo", user);
		map.put("userMore", userMoreService.findOne(baseApp.getAppUser().getId()));
		if (user.getInviteesId() != null) {
			map.put("inviteesName", userInfoService.findOne(user.getInviteesId()).getUserName());
		}
		map.put("invitees", userInfoService.fingByInviteesId(user.getId()));
		return suc(map);
	}

	/**
	 * 积分发红包
	 * 
	 * @param baseApp
	 * @param mobiles
	 * @param jifen
	 * @return
	 */
	@ResponseBody
	@AppUserLogin
	@RequestMapping(value = "jifenhongbao")
	// appToken=94C7EF137EEB6757C6357378F5EC3111&toUserId=13522222222&jifen=21420264
	public Map<String, Object> jifenhongbao(BaseAppTokenBean baseApp, @Valid String[] mobiles, Integer jifen) {
		if (jifen == null || jifen.intValue() < 0) {
			return fail("积分不对");
		}
		UserInfo userInfo = baseApp.getAppUser();
		if (mobiles.length * jifen > userInfo.getIntegration()) {
			return fail("积分不足");
		}

		// String [] mobile = mobiles.split("\\,");
		boolean isSend = false;
		for (String mobile : mobiles) {
			if (StringUtils.isBlank(mobile))
				continue;
			if (userInfoService.jifenhongbao(userInfo, mobile, jifen)) {
				isSend = true;
			}
		}
		if (isSend) {
			return suc(userInfo.getIntegration());
		}
		return fail("赠送失败！");
	}

	/**
	 * 查询(会减少积分)
	 * 
	 * @param mobile
	 * @return
	 */
	@ResponseBody
	@AppUserLogin
	@RequestMapping(value = "search")
	public Map<String, Object> search(BaseAppTokenBean baseApp, @RequestParam String mobile) {
		UserInfo user = baseApp.getAppUser();
		if (user != null) {
			Integer s = Integer.parseInt(sysConfigService.findOne(SysConfigEnum.search_msg_mobile.getValue()).getConfigValue());
			if (user.getIntegration() != null && user.getIntegration().intValue() > Math.abs(s.intValue())) {
				userInfoService.addIntegration(user, null, "查询消耗", -Math.abs(s.intValue()), Boolean.TRUE);
			} else {
				return fail("积分不足!");
			}
			return suc(userInfoService.findByMobile(mobile));
		} else {
			return fail("非法操作，用户不存在！");
		}
	}

	/**
	 * 签到, 积分 + 经验
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "registration")
	@AppUserLogin
	public Map<String, Object> registration(BaseAppTokenBean baseApp) {
		UserInfo user = baseApp.getAppUser();
		if (user != null) {
			// logger.debug("registration----->"+registrationService.findOneDay(user.getId()).getUser());
			if (registrationService.findOneDay(user.getId()) != null) {
				return fail("今天已经签到！");
			}
			registrationService.sign(user);
			String jy = sysConfigService.findOne(SysConfigEnum.day_sign_jy.getValue()).getConfigValue();
			logger.debug("经验----------->" + jy);
			if (user.getExperience() != null) {
				user.setExperience(user.getExperience() + Integer.parseInt(sysConfigService.findOne(SysConfigEnum.day_sign_jy.getValue()).getConfigValue()));
			} else {
				user.setExperience(Integer.parseInt(sysConfigService.findOne(SysConfigEnum.day_sign_jy.getValue()).getConfigValue()));
			}
			userInfoService.addIntegration(user, null, "签到", Math.abs(Integer.parseInt(sysConfigService.findOne(SysConfigEnum.day_sign_score.getValue()).getConfigValue())), Boolean.TRUE);
			userInfoService.isSave(user);
			return suc(user.getIntegration());
		} else {
			return fail("非法操作，用户不存在！");
		}
	}

	@RequestMapping("validatorPwd")
	@AppUserLogin
	@ResponseBody
	public Object validatorPwd(BaseAppTokenBean baseApp, String password) {
		UserInfo userInfo = baseApp.getAppUser();
		if (!PasswordUtils.validPassword(userInfo.getPassword(), password)) {
			return fail("密码不正确");
		}
		return suc(SUCCESS);
	}

	@ResponseBody
	@RequestMapping("login")
	public Object login(String account, String password, BaseAppTokenBean baseApp) throws Exception {
		ResultBean resultBean = new ResultBean();
		UserInfo memberInfo = userInfoService.findByMobile(account);
		if (memberInfo != null) {
			boolean isSnapPwd = false;
			// 普通的账号登入方式 ||
			if (isSnapPwd || PasswordUtils.validPassword(memberInfo.getPassword(), password)) {
				resultBean.setIsSuccess(Boolean.TRUE);
				resultBean.setMessage("登入成功！");
				String backUrl = (String) request.getSession().getAttribute(DataConfig.BACK_URL);
				if (StringUtils.isNotBlank(backUrl)) {
					resultBean.setResult(backUrl);
				}

				HttpSession session = _createNewSessionByLogin();
				session.setAttribute(DataConfig.APP_USER, memberInfo);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("appToken", session.getId());
				map.put("user", memberInfo);
				UserMore userMore = userMoreService.findOne(memberInfo.getId());
				userMore = userMore == null ? new UserMore() : userMore;
				map.put("userMore", userMore);
				return suc(map);
				// resultBean.setResult(session.getId());
				// 登入日志
			} else {
				// resultBean.setIsSuccess(Boolean.FALSE);
				// resultBean.setMessage("密码错误！");
				return fail("密码错误！");
			}
		} else {
			// resultBean.setIsSuccess(Boolean.FALSE);
			// resultBean.setMessage("用户不存在！");
			return fail("用户不存在！");
		}
		// return JsonMapper.beanToMap(resultBean);
	}

	@RequestMapping("autoLogin")
	@ResponseBody
	public Object autoLogin(@RequestParam Integer memberId) {

		UserInfo memberInfo = userInfoService.findOne(memberId);
		if (memberInfo == null) {
			return fail("用户不存在!");
		}
		HttpSession session = _createNewSessionByLogin();
		session.setAttribute(DataConfig.APP_USER, memberInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appToken", session.getId());
		map.put("user", memberInfo);
		UserMore userMore = userMoreService.findOne(memberInfo.getId());
		userMore = userMore == null ? new UserMore() : userMore;
		map.put("userMore", userMore);
		return suc(map);
	}

	@ResponseBody
	@RequestMapping("exit")
	@AppUserLogin
	public Object exit(BaseAppTokenBean baseApp) {
		baseApp.getSession().removeAttribute(DataConfig.APP_USER);
		return suc(SUCCESS);
	}

	/**
	 * session MySessionContext 创建一个新的登录session
	 * 
	 * @return
	 */
	private HttpSession _createNewSessionByLogin() {
		// 每次登入生成新的session 防止sessionId注入
		HttpSession session = request.getSession(false);

		// HttpSession session = request.getSession(ture)
		// 如果服务器上没有session就创建一个新的session，如果有就取得session。
		// HttpSession session = request.getSession(false)
		// 如果服务器上没有session也不创建新的,返回null。

		if (session != null) {
			session.invalidate(); // 清空所有session
		}
		session = request.getSession(true);
		session.setMaxInactiveInterval(60 * 30); // 默认30分钟 , 单位s
		MySessionContext.getInstance().AddSession(session);
		return session;
	}

	public Object checkEmail(String email) {
		if (userInfoService.hasEmail(email)) {
			return fail(ErrorCodeConstant.ACCOUNT_EMAILL_MOBILE_EXIST_CODE, ErrorCodeConstant.ACCOUNT_EMAILL_MOBILE_EXIST_MSG);
		}
		return suc(SUCCESS);
	}

	/**
	 * 平台注册
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("register")
	@ResponseBody
	public Object register(BaseAppTokenBean baseApp, UserInfo memberInfo, String yzm, String otherMobile) throws Exception {
		/**
		 * 手机短信验证码待完善
		 */
		// if (StringUtils.isEmpty(yzm) || baseApp.getSession() == null
		// || !yzm.equalsIgnoreCase(String.valueOf(baseApp.getSession()
		// .getAttribute("yanzhengma")))) {
		// resultBean.setIsSuccess(Boolean.FALSE);
		// resultBean.setMessage("验证码错误");
		// return JsonMapper.beanToMap(resultBean);
		// return fail("验证码错误");
		// }

		if (userInfoService.hasEmail(memberInfo.getMobile())) {
			return fail(ErrorCodeConstant.ACCOUNT_EMAILL_MOBILE_EXIST_CODE, ErrorCodeConstant.ACCOUNT_EMAILL_MOBILE_EXIST_MSG);
		}
		memberInfo.setPassword(PasswordUtils.encrypt(memberInfo.getPassword()));
		memberInfo.setExperience(Integer.parseInt(sysConfigService.findOne(SysConfigEnum.register_jy.getValue()).getConfigValue()));
		userInfoService.save(memberInfo);
		// 注册送积分
		userInfoService.addIntegration(memberInfo, null, "注册送积分", Math.abs(Integer.parseInt(sysConfigService.findOne(SysConfigEnum.register_score.getValue()).getConfigValue())), Boolean.FALSE);
		memberInfo = userInfoService.findByMobile(memberInfo.getMobile());
		// 添加伪Id
		String fakeId = userInfoService.addFakeId(memberInfo, MobileInfoFromEnum.USER);
		Random r = new Random();
		memberInfo.setMore2(AppConfig.getCountryMobileNo(r.nextInt(5)));
		if(StringUtils.equals(otherMobile, memberInfo.getMobile())){
			return fail("推荐人不能填写自己");
		}
		
		if (StringUtils.isNotEmpty(otherMobile)) {
			UserInfo u = userInfoService.findByMobile(otherMobile);
			if (u != null) {
				u.setExperience(u.getExperience() + Integer.parseInt(sysConfigService.findOne( SysConfigEnum.ask_friend_suc_jy.getValue()).getConfigValue()));
				Integer count = u.getInviteFriendAmount();
				count = count == null ? 0 : count;
				u.setInviteFriendAmount(count + 1);
				if(u.getIsVip()){
					userInfoService.addIntegration(u, null, "成功邀请好友,金蜗牛额外加成", Integer.parseInt(sysConfigService.findOne(SysConfigEnum.user_tuijian.getValue()).getConfigValue()), Boolean.FALSE);
				}
				userInfoService.addIntegration(u, null, "成功邀请好友", Integer.parseInt(sysConfigService.findOne(SysConfigEnum.register_and_tuijian_other.getValue()).getConfigValue()),
						Boolean.FALSE);
				
				userInfoService.addIntegration(memberInfo, null, "推荐积分", Integer.parseInt(sysConfigService.findOne(SysConfigEnum.register_and_tuijian_own.getValue()).getConfigValue()),
						Boolean.FALSE);
				memberInfo.setInviteesId(u.getId());
				userInfoService.save(u);
			}
		}
		userInfoService.save(memberInfo);
		HttpSession session = _createNewSessionByLogin();
		session.setAttribute(DataConfig.APP_USER, memberInfo);

		MobileInfoBean mobileInfoBean = new MobileInfoBean();
		mobileInfoBean.setMobile(memberInfo.getMobile());

		MobileInfo mobileInfo = mobileService.findByMobile(memberInfo.getMobile(), MobileInfoFromEnum.USER);
		if (mobileInfo == null) {
			mobileInfo = new MobileInfo();
			mobileInfo.setMobile(memberInfo.getMobile());
			mobileInfo.setMobileFrom(MobileInfoFromEnum.USER.getValue());
			mobileInfo.setFakeId(fakeId);
			mobileService.save(mobileInfo);
		}
		return suc(SUCCESS, session.getId());
	}

	/**
	 * nickName image 修改头像和 昵称
	 * 
	 * @return
	 */
	@RequestMapping("modifyInfo")
	@ResponseBody
	@AppUserLogin
	public Object modifyInfo(BaseAppTokenBean baseApp, UserInfo memberInfo) {
		HttpSession session = baseApp.getSession();
		UserInfo member = (UserInfo) session.getAttribute(DataConfig.APP_USER);
		memberInfo.setId(member.getId());
		userInfoService.save(memberInfo);

		member = userInfoService.findOne(member.getId());
		session.setAttribute(DataConfig.APP_USER, member);
		return suc(SUCCESS);
	}

	/**
	 * findPwd
	 * 
	 * @param req
	 * @param baseApp
	 * @param mobile
	 * @param type
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "sendSmsCode")
	public Object sendSmsCode(HttpServletRequest req, BaseAppTokenBean baseApp, @RequestParam String mobile, String type) throws HttpException,
			IOException {

		HttpSession session = baseApp.getSession();
		if (session == null) {
			session = _createNewSessionByLogin();
		}
		// 3分钟限制
		if (!checkSmsTime(session)) {
			return fail("请稍等");
		}
		// 账号已存在

		if (StringUtils.equals("findPwd", type)) {
			if (userInfoService.findByMobile(mobile) == null)
				return fail("账号不存在");
		} else if (userInfoService.findByMobile(mobile) != null) {
			return fail("账号已存在");
		}

		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(AppConfig.appMap.get("smsUrl"));
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x + 100000;
		NameValuePair[] data = { new NameValuePair("Uid", AppConfig.appMap.get("uid")), new NameValuePair("Key", AppConfig.appMap.get("key")),
				new NameValuePair("smsMob", mobile), new NameValuePair("smsText", AppConfig.appMap.get("message") + x) };
		post.setRequestBody(data);

		client.executeMethod(post);
		String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
		System.out.println(result); // 打印返回消息状态
		Integer status = Integer.parseInt(result);
		if (status > 0) {
			session.setAttribute("yanzhengma", x);
			System.out.println("手机验证码" + x);
			session.setAttribute(DataConfig.SMS_TIME, System.currentTimeMillis());
			session.setMaxInactiveInterval(60 * 15);
			// return Boolean.TRUE;

			return suc(SUCCESS, session.getId());
		}
		post.releaseConnection();
		return fail("发送失败请重试");
	}

	private boolean checkSmsTime(HttpSession session) {
		// 防刷手机号码
		if (session.getAttribute(DataConfig.SMS_TIME) != null) {
			Long time = (Long) session.getAttribute(DataConfig.SMS_TIME);
			if (System.currentTimeMillis() - time < 1000 * 60 * 3) {
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}

	@RequestMapping("findPwd")
	@ResponseBody
	public Object findPwd(BaseAppTokenBean baseApp, String mobile, String yzm, String password) throws Exception {
		ResultBean resultBean = new ResultBean();
		/**
		 * 手机短信验证码待完善
		 */
		if (StringUtils.isEmpty(yzm) || baseApp.getSession() == null
				|| !yzm.equalsIgnoreCase(String.valueOf(baseApp.getSession().getAttribute("yanzhengma")))) {
			resultBean.setIsSuccess(Boolean.FALSE);
			resultBean.setMessage("验证码错误");
			return JsonMapper.beanToMap(resultBean);

		}
		UserInfo memberInfo = userInfoService.findByMobile(mobile);
		if (memberInfo == null) {
			return fail("用户不存在");
		}
		memberInfo.setPassword(PasswordUtils.encrypt(password));
		userInfoService.update(memberInfo);
		return suc(SUCCESS);
	}

	/**
	 * 绑定支付宝
	 * 
	 * @return
	 */
	@RequestMapping("bindAlipay")
	@ResponseBody
	@AppUserLogin
	public Object bindAlipay(BaseAppTokenBean baseApp, String alipayAccount, String alipayRealName, String password) {
		Integer id = baseApp.getAppUser().getId();
		try {
			UserInfo u = userInfoService.findOne(id);
			if (!PasswordUtils.validPassword(u.getPassword(), password)) {
				// throw new SimpleException("原支付宝账号不正确");
				return fail("密码不正确");
			}
			if (userMoreService.bindAlipay(id, alipayAccount, alipayRealName, password)) {
				return suc(SUCCESS);
			}
			return fail(FAIL);
		} catch (SimpleException e) {
			return fail(e.getMsg(), e.getCode());
		}
	}

	// 我的好友list
	@RequestMapping("friends")
	@ResponseBody
	public Map<String, Object> friendsList(BaseAppTokenBean baseApp) {
		Integer id = baseApp.getAppUser().getId();
		System.out.println("" + baseApp.getAppUser().getMobile());
		System.out.println("用户ID=" + id);
		UserInfoBean userInfoBean = new UserInfoBean();
		userInfoBean.setInviteesId(id);
		List<UserInfo> userInfos = userInfoService.queryAll(userInfoBean);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("friends", userInfos);
		System.out.println("list大小=" + userInfos.size());
		return JsonMapper.beanToMap(map);
	}

	// 修改用户信息
	@RequestMapping("updateUserInfo")
	@ResponseBody
	@AppUserLogin
	public Object updateUserInfo(BaseAppTokenBean baseApp, @Validated UserInfo userInfo, @Validated UserMore userMore) {
		Integer id = baseApp.getAppUser().getId();
		userInfo.setId(id);
		userInfo.setMobile(null);
		userMore.setId(id);
		if (userInfoService.isSave(userInfo) && userMoreService.saveOrUpdateForUser(userMore)) {
			return suc(SUCCESS);
		}
		return fail(FAIL);
		// 原来
		// UserInfo userInfo = userInfoService.findOne(id);
		// if(StringUtils.isNotBlank(userName))
		// userInfo.setUserName(userName);
		// if(sex != null)
		// userInfo.setSex(sex);
		// if (userInfoService.isSave(userInfo) && c.updateUser(id,
		// userMoreBean)) {
		// return suc(SUCCESS);
		// }
		// return fail(FAIL);
	}

	// 修改微信
	@RequestMapping("updatebyWeixin")
	@ResponseBody
	public Object updatebyWeixin(BaseAppTokenBean baseApp, @RequestParam String weixin) {
		Integer id = baseApp.getAppUser().getId();
		UserInfo userInfo = userInfoService.findOne(id);
		if (userInfo.getWeixinCount() == null) {
			userInfo.setWeixinCount(0);
		}
		if (userInfo.getWeixinCount() < 3) {
			userInfo.setWeixin(weixin);
			int count = userInfo.getWeixinCount() + 1;
			System.out.println("修改次数=" + count);
			userInfo.setWeixinCount(count);
			if (userInfoService.isUpdate(userInfo)) {
				return suc(SUCCESS);
			}
		}
		return fail("微信修改已达上限");
	}

	/**
	 * 手机验证码验证
	 * 
	 * @param baseApp
	 * @param yzm
	 * @return
	 */
	@RequestMapping("isYZM")
	@ResponseBody
	public Object isYZM(BaseAppTokenBean baseApp, @RequestParam String yzm) {
		// ResultBean resultBean = new ResultBean();
		// if (StringUtils.isEmpty(yzm) || baseApp.getSession() == null
		// || !yzm.equalsIgnoreCase(String.valueOf(baseApp.getSession()
		// .getAttribute("yanzhengma")))) {
		// resultBean.setIsSuccess(Boolean.FALSE);
		// resultBean.setMessage("验证码错误");
		// return JsonMapper.beanToMap(resultBean);
		//
		// }
		return suc(SUCCESS);
	}

	/**
	 * 成为 会员: 金蜗牛
	 * 
	 * @param baseApp
	 * @return
	 */
	@RequestMapping("toBeVip")
	@ResponseBody
	@AppUserLogin
	public Object toBeVip(BaseAppTokenBean baseApp) {
		try {
			UserInfo userInfo = new BaseAppTokenBean().getAppUser();
			userInfo = userInfoService.findOne(userInfo.getId());
			userInfoService.toBeVip(userInfo);
			return suc(SUCCESS);
		} catch (SimpleException e) {
			e.printStackTrace();
			return fail(e);
		}
	}

	@AppUserLogin
	@RequestMapping("unreadCount")
	@ResponseBody
	public Object unreadCount(BaseAppTokenBean baseApp) {
		UserInfo userInfo = baseApp.getAppUser();
		Map<String, Integer> map = new HashMap<String, Integer>();

		// 留言
		MessageBean messageBean = new MessageBean();
		messageBean.setUserId(userInfo.getId());
		messageBean.setIsSee(false);
		map.put("messageUnreadCount", messageService.queryAll(messageBean).size());
		logger.debug("留言--------------------->" + messageService.queryAll(messageBean).size());

		// 积分
		UserScoreLogBean userScoreLogBean = new UserScoreLogBean();
		userScoreLogBean.setUserId(userInfo.getId());
		userScoreLogBean.setIsSee(false);
		map.put("userScoreUnreadCount", userScoreLogService.queryAll(userScoreLogBean).size());
		logger.debug("积分--------------------->" + userScoreLogService.queryAll(userScoreLogBean).size());

		// 红包
		CashUserLogBean cashUserLogBean = new CashUserLogBean();
		cashUserLogBean.setUserId(userInfo.getId());
		cashUserLogBean.setIsSee(false);
		map.put("userRedUnreadCount", cashUserLogService.queryAll(cashUserLogBean).size());
		logger.debug("红包--------------------->" + cashUserLogService.queryAll(cashUserLogBean).size());
		return suc(map);
	}

	/**
	 * 团队管理 个人基本统计数据.
	 * 
	 * @param baseApp
	 * @return
	 */
	@RequestMapping("msgManager")
	@ResponseBody
	@AppUserLogin
	public Object  msgManager(BaseAppTokenBean baseApp){
		UserInfo userInfo = baseApp.getAppUser();
		
		Map<String,Object> map = new HashMap<String,Object>();
		
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
//					groupCount++;
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
			map.put("inviteesNo", userInfoService.findOne(inviteesId).getMobile());
		}else  {
			map.put("inviteesNo", "");
		}
		
		
		//我的好友  推荐人
		map.put("allInviteesCount", list1.size());
		// 自己下载数量
		Integer downCount = downloadLogService.loadDownAmount(userInfo) ;
		map.put("downCount", downCount == null? 0 :	downCount );
		// 积分shu
		map.put("allScore", userInfo.getIntegration() );
		
//		今天共邀请了
		map.put("dayInviteesCount", dayInvitees );
		//今日邀请金蜗牛
		map.put("dayVipCount", dayVipCount);
		//  二级好友 今日新增
		Integer  secondFriends = userInfoService.loadUserSecondCount(userInfo.getId());
		secondFriends = secondFriends==null? 0:secondFriends;
		map.put("dayGroupCount", dayInvitees + secondFriends);
		
		// 今日收到红包
		map.put("dayRedCount", cashUserLogService.loadUserDayRedCount(userInfo.getId()));
		
	//	map.put("vipCount", userInfo.getInviteFriendVipAmount() );
		map.put("vipCount", friendVipCount );
		
		// 团队人数
		map.put("grounpCount", groupFriends.size() );
		map.put("getRedCount",  cashUserLogService.userGetRedCount(userInfo)  );
		map.put("friends", list1);
		
		return suc(map);
	}

	// 电话号码查询
//	@RequestMapping("findMobile")
//	@ResponseBody
//	@AppUserLogin
//	public Object findMobile(BaseAppTokenBean baseApp, String mobile) {
//		UserInfo appUser = baseApp.getAppUser();
//		Integer s = Integer.parseInt(sysConfigService.findOne(SysConfigEnum.search_msg_mobile.getValue()).getConfigValue());
//		if (appUser.getIntegration() != null && appUser.getIntegration().intValue() > s.intValue()) {
//			userInfoService.addIntegration(appUser, null, "查询消耗", s, Boolean.TRUE);
//		} else {
//			return fail("积分不足!");
//		}
//		
//		UserInfo userInfo = userInfoService.findByMobile(mobile);
//		UserMore userMore = new UserMore();
//		Integer groupNum = 0;
//		if (userInfo != null) {
////			userInfoService.findMobileIntegrationModify(userInfo);
//			userMore = userMoreService.findOne(userInfo.getId());
//			groupNum = URLUtils.loadUserGroupMount(userInfo.getId());
//		}
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("userInfo", userInfo);
//		map.put("userMore", userMore);
//		map.put("groupNum", groupNum);
//		// logger.debug("groupNum------------------------->"+URLUtils.loadUserGroupMount(userInfo.getId())
//		// );
//
//		return suc(map);
//	}

}

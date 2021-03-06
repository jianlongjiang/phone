package com.phone.cn.service.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.phone.cn.bean.product.MobileInfoBean;
import com.phone.cn.conf.enums.MobileInfoFromEnum;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.entity.product.DownloadLog;
import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.entity.sys.MobileDownLog;
import com.phone.cn.entity.sys.SysConfig;
import com.phone.cn.mapper.product.MobileInfoMapper;
import com.phone.cn.service.BaseService;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.sys.MobileDownLogService;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午7:58:52
 * 
 */
@Service
@Transactional
public class MobileService extends BaseService<MobileInfo, Integer> {

	@Autowired
	private MobileInfoMapper mapper;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private DownloadLogService downloadLogService;

	@Autowired
	private CateInfoService cateInfoService;

	@Autowired
	private MobileDownLogService mobileDownLogService;

	@Cacheable(cacheName = "day_cache_key")
	public List<Integer> allOtherIds() {
		return mapper.getAllIds();
	}

	@Cacheable(cacheName = "day_cache_key")
	public List<Integer> allUserMobileIds() {
		return mapper.getUserMobileIds();
	}

	public List<MobileInfo> searchByIds(List<Integer> ids) {
		if (CollectionUtils.isEmpty(ids)) {
			return new ArrayList<MobileInfo>();
		}
		return mapper.searchByIds(ids);
	}

	public List<MobileInfo> getMobiles(UserInfo userInfo, Integer num, List<String> downMobiles, List<MobileInfo> mustDowns,
			List<MobileInfo> vipMobileInfos, List<MobileInfo> normalpMobileInfos, List<MobileInfo> otherMobiles, SysConfig s) {
		List<MobileInfo> needDownMobiles = new ArrayList<MobileInfo>();

		num = needDowns(num, needDownMobiles, downMobiles, mustDowns);
		
		num = needDowns(num, needDownMobiles, downMobiles, vipMobileInfos);
		num = needDowns(num, needDownMobiles, downMobiles, normalpMobileInfos);
		num = needDowns(num, needDownMobiles, downMobiles, otherMobiles);
		// 积分操作
		userInfoService.addIntegration(userInfo, "下载", "下载手机号码", -needDownMobiles.size() * Math.abs(Integer.parseInt(s.getConfigValue())) , true);
		// user.setIntegration(user.getIntegration() - list.size() *
		// Integer.parseInt(s.getConfigValue()));
		int mobileAcount = userInfo.getDownloadMobileAmount() == null ? 0 : userInfo.getDownloadMobileAmount();
		userInfo.setDownloadMobileAmount(mobileAcount + needDownMobiles.size());
		// String[] mobiles = null;
		// String backMobiles = StringUtils.isBlank(userInfo.getDownloadMobile()
		// )? "":userInfo.getDownloadMobile() ;
		StringBuffer stringBuffer = new StringBuffer();
		boolean isFirst = true;
		for (MobileInfo mobileInfo : needDownMobiles) {
			if (isFirst) {
				stringBuffer.append(mobileInfo.getMobile());
				isFirst = false;
			} else {
				stringBuffer.append(",").append(mobileInfo.getMobile());
			}
		}
		// String downIds = stringBuffer.toString();
		// if(downIds.startsWith(",")) downIds = downIds.substring(1);
		// userInfo.setDownloadMobile(downIds);
		userInfoService.isSave(userInfo);
		if (needDownMobiles.size() >= 0) {
			DownloadLog log = new DownloadLog();
			log.setCreateTime(new Date());
			log.setUpdateTime(new Date());
			log.setUserId(userInfo.getId());
			log.setMobilenum(needDownMobiles.size());
			downloadLogService.isSave(log);

			MobileDownLog mobileDownLog = new MobileDownLog();
			mobileDownLog.setCreateTime(new Date());
			mobileDownLog.setUpdateTime(new Date());
			mobileDownLog.setUserId(userInfo.getId());
			mobileDownLog.setAmount(needDownMobiles.size());
			mobileDownLog.setMobiles(stringBuffer.toString());
			mobileDownLogService.save(mobileDownLog);

			// 用户数据维护
			// downloadMobileAmount
			userInfo.setDownloadMobileAmount(userInfo.getDownloadMobileAmount() + needDownMobiles.size());
			userInfoService.save(userInfo);

		}
		return needDownMobiles;
	}

	private Integer needDowns(Integer num, List<MobileInfo> needDownMobiles, List<String> downMobiles, List<MobileInfo> goDowns) {
		for (int i = 0; i < goDowns.size() && num > 0; i++) {
			MobileInfo goInfo = goDowns.get(i);
			boolean needDown = true;
			for (String downMobile : downMobiles) {
				if (StringUtils.equals(downMobile.trim(), goInfo.getMobile().trim())) {
					needDown = false;
					break;
				}
			}
			if (needDown) {
				for (MobileInfo mobileInfo : needDownMobiles) {
					if (StringUtils.equals(mobileInfo.getMobile().trim(), goInfo.getMobile().trim())) {
						needDown = false;
						break;
					}
				}
			}
			if (needDown) {
				needDownMobiles.add(goInfo);
				num--;
			}
		}
		return num;
	}

	@Transactional
	public List<MobileInfo> getMobiles1(UserInfo userInfo, Integer num, String[] mobileids, List<MobileInfo> mustDowns,
			List<Integer> allOtherIdsCopy, List<Integer> allUserMobileIdsCopy, SysConfig s) {

		// 必下手机号码
		List<Integer> mustDonwIds = new ArrayList<Integer>();
		for (MobileInfo mobileInfo : mustDowns) {
			mustDonwIds.add(mobileInfo.getId());
		}

		List<MobileInfo> list = new ArrayList<MobileInfo>();
		Random random = new Random();
		List<Integer> allOtherIds = new ArrayList<Integer>(allOtherIdsCopy);
		List<Integer> allUserMobileIds = new ArrayList<Integer>(allUserMobileIdsCopy);

		// Arrays. 过滤已经下载的手机号码
		// TODO 注释
		if (mobileids != null) {
			for (String id : mobileids) {
				id = id.replace("[", "").replace("]", "").trim();
				if (StringUtils.isBlank(id))
					continue;
				Integer iid = Integer.parseInt(id);
				if (allOtherIds.contains(iid)) {
					allOtherIds.remove(iid);
				}
				if (allUserMobileIds.contains(iid)) {
					allUserMobileIds.remove(iid);
				}
				if (mustDonwIds.contains(iid)) {
					mustDonwIds.remove(iid);
				}
			}
		}

		// 在用户注册数据 和 上传数据中过滤 必下号码数据
		for (Integer mustDownId : mustDonwIds) {
			if (allOtherIds.contains(mustDownId)) {
				allOtherIds.remove(mustDownId);
			}
			if (allUserMobileIds.contains(mustDownId)) {
				allUserMobileIds.remove(mustDownId);
			}
		}
		List<Integer> ids = new ArrayList<Integer>();
		// 必备号码
		num = filterMobiles(random, mustDonwIds, num, ids);
		int usernum = num * 8 / 10;
		int othersnum = num * 2 / 10;
		// 注册用户 删选
		usernum = filterMobiles(random, allUserMobileIds, usernum, ids);
		// 导入用户 获取
		othersnum = filterMobiles(random, allOtherIds, othersnum, ids);
		// 导入用户数量不足, 注册用户补充
		filterMobiles(random, allUserMobileIds, othersnum, ids);
		// // 注册用户数量不足, 导入用户补充
		filterMobiles(random, allOtherIds, usernum, ids);

		list.addAll(searchByIds(ids));

		// 积分操作
		userInfoService.addIntegration(userInfo, "下载", "下载手机号码", -list.size() * Math.abs(Integer.parseInt(s.getConfigValue())), true);
		// user.setIntegration(user.getIntegration() - list.size() *
		// Integer.parseInt(s.getConfigValue()));
		int mobileAcount = userInfo.getDownloadMobileAmount() == null ? 0 : userInfo.getDownloadMobileAmount();
		userInfo.setDownloadMobileAmount(mobileAcount + list.size());
		// String[] mobiles = null;
		String backMobiles = null;// StringUtils.isBlank(userInfo.getDownloadMobile()
									// )? "":userInfo.getDownloadMobile() ;
		StringBuffer stringBuffer = new StringBuffer();
		for (MobileInfo mobileInfo : list) {
			stringBuffer.append(",").append(mobileInfo.getId());
		}
		stringBuffer.insert(0, backMobiles);
		String downIds = stringBuffer.toString();
		if (downIds.startsWith(","))
			downIds = downIds.substring(1);
		// userInfo.setDownloadMobile(downIds);
		userInfoService.isSave(userInfo);
		if (list.size() >= 0) {
			DownloadLog log = new DownloadLog();
			log.setCreateTime(new Date());
			log.setUpdateTime(new Date());
			log.setUserId(userInfo.getId());
			log.setMobilenum(list.size());
			downloadLogService.isSave(log);

			// 用户数据维护
			// downloadMobileAmount
			userInfo.setDownloadMobileAmount(userInfo.getDownloadMobileAmount() + list.size());
			userInfoService.save(userInfo);

		}

		// log.setMobilenum(num);

		return list;
	}

	/**
	 * 返回的值 为 剩余名额
	 * 
	 * @param r
	 * @param allOtherIds
	 * @param othersnum
	 * @param ids
	 * @return
	 */
	private int filterMobiles(Random r, List<Integer> allOtherIds, int othersnum, List<Integer> ids) {
		while (othersnum > 0 && allOtherIds.size() > 0) {
			int rs = r.nextInt(allOtherIds.size());
			ids.add(allOtherIds.get(rs));
			allOtherIds.remove(rs);
			othersnum--;
		}
		return othersnum;
	}

	public List<MobileInfo> allOthers() {
		return mapper.getAlls();
	}

	public List<MobileInfo> allUserMobiles() {
		return mapper.getUserMobiles();
	}

	/**
	 * 1 or 2 级 进行过滤
	 * 
	 * @param list
	 * @param firstCateId
	 * @param secondCateId
	 * @return
	 */
	public List<MobileInfo> doFilter(List<MobileInfo> list, Integer firstCateId, Integer secondCateId) {
		List<CateInfo> cateInfos = null;
		if (firstCateId != null && secondCateId == null) {
			cateInfos = cateInfoService.queryByParentId(firstCateId);
		}

		List<MobileInfo> ids = new ArrayList<MobileInfo>();
		for (MobileInfo mobileInfo : list) {
			if (firstCateId == null && secondCateId == null) { // 1级也没选择
				ids.add(mobileInfo);
			} else if (secondCateId != null) { // 选择了2级
				String cateIdsStr = String.valueOf(mobileInfo.getCateIds());
				if (StringUtils.isNotEmpty(cateIdsStr)) {
					String[] cateIds = cateIdsStr.split(",");
					if (Arrays.asList(cateIds).contains(String.valueOf(secondCateId))) {
						ids.add(mobileInfo);
					}
				}
			} else { //
				String cateIdsStr = String.valueOf(mobileInfo.getCateIds());
				if (StringUtils.isNotEmpty(cateIdsStr)) {
					String[] cateIds = cateIdsStr.split(",");

					for (CateInfo cateInfo : cateInfos) {
						if (Arrays.asList(cateIds).contains(String.valueOf(cateInfo.getId()))) {
							ids.add(mobileInfo);
						}
					}
				}
			}

		}
		return ids;
	}

	public Integer loadCountByCateId(Integer cateId) {
		Integer num = mapper.loadCountByCateId(String.valueOf(cateId));
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>二级分类大小=" + num);
		return num;
	}

	public MobileInfo findByMobile(String mobile, MobileInfoFromEnum mobileInfoFromEnum) {
		MobileInfoBean bean = new MobileInfoBean();
		bean.setMobile(mobile);
		bean.setMobileFrom(mobileInfoFromEnum.getValue());
		return selectOneByExample(bean);
		// return mapper.findByMobile(mobile);
	}

	@Override
	public MobileInfo save(MobileInfo m) {
		return super.save(m);
	}

	public List<MobileInfo> userMobileInfos(boolean isVip) {
		return mapper.userMobileInfos(isVip);
	}

}

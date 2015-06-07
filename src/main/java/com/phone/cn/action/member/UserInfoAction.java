package com.phone.cn.action.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.member.UserInfoBean;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.entity.member.UserLevel;
import com.phone.cn.entity.member.UserMore;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.service.member.UserInfoService;
import com.phone.cn.service.member.UserLevelService;
import com.phone.cn.service.member.UserMoreService;
import com.phone.cn.service.product.CateInfoService;
import com.phone.cn.utils.ExcelView;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:48:37
 *   
 */
@Controller
@RequestMapping(value="admin/member")
@AdminUserLogin
public class UserInfoAction extends BaseCRUDController<UserInfoBean, UserInfo, Integer>{
	
	@Autowired
	private CateInfoService cateInfoService;
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private UserMoreService userMoreService;
	@Autowired
	private UserLevelService userLevelService;
	
	
	/**
	 * cateIdArrays
	 */
	@Override
	public String list(UserInfoBean bean, Model model, @PathVariable Integer pageNo) {
		
//		session.set
		logger.debug("-------------------------------->bean.getMobile()="+bean.getMobile());
		logger.debug("-------------------------------->bean.getSort()="+bean.getSort());
		logger.debug("-------------------------------->bean.getCateIds()="+bean.getCateIds());
//		bean.createCateIds();
		bean.setPageSize(10);
		PageList<UserInfo> infos = userInfoService.queryAllWithPage(bean, bean.toPageBounds());
		if(infos!=null && !infos.isEmpty()){
			Map<Integer, CateInfo> cateMap = cateInfoService.findAllCateMap();
			model.addAttribute("page", infos.getPaginator());
			Map<String, List<CateInfo>> map = new HashMap<String, List<CateInfo>>();
			for (UserInfo userInfo : infos) {
				List<CateInfo> list = new ArrayList<CateInfo>();
				String cateids = userInfo.getCateIds();
				if(StringUtils.isNotEmpty(cateids)){
					String[] ids = cateids.split(",");
					for (String string : ids) {
						if(StringUtils.isNotEmpty(string) && !"null".equals(string)){
							list.add(cateMap.get(Integer.parseInt(string.replace("null", ""))));
						}
					}
				}
				map.put(String.valueOf(userInfo.getId()), list);
			}
			model.addAttribute("cates", map);
		}
		model.addAttribute("infos", infos);
		model.addAttribute("bean", bean);
//		model.addAttribute("firstCates", cateInfoService.searchFirstCates());
		model.addAttribute("firstCates",cateInfoService.queryByParentId(0));
		List<UserLevel> userLevels = userLevelService.findAll();
		model.addAttribute("levels",userLevels);
		List<CateInfo> cateInfos = cateInfoService.findAll();
		model.addAttribute("allCates",cateInfos);
		if(bean.getFirstCateId() != null) {
			model.addAttribute("secondCates",cateInfoService.queryByParentId(bean.getFirstCateId()) );
		}
		
//		UserInfo u = userInfoService.findOne(1);	
//		Integer fakeID = 800000000+u.getId();
//		
//		if(u!=null){
//			u.setFakeId("w"+fakeID);
//			userInfoService.save(u);
//		}
		return viewName("list");
	}
	
	
	@RequestMapping("userDetail")
	public  String   userDetail(Integer id, Model model){
		UserInfo userInfo = userInfoService.findOne(id);
		UserMore userMore = userMoreService.findOne(id);
		
		if(userInfo != null){
			if(userMore == null){
				userMore = new UserMore();
				userMore.setId(userInfo.getId());
				userMore.setMobile(userMore.getMobile());
				userMoreService.save(userMore);
			}
		}
		model.addAttribute("info", userInfo);
		model.addAttribute("infoMore", userMore);
		return  "admin/member/input";
	}
	
	@ResponseBody
	@RequestMapping("userDetailSave")
	public  Object   userDetailSave(UserInfo userInfo, UserMore userMore, Model model){
		userInfoService.save(userInfo);
		userMoreService.save(userMore);
		return suc(SUCCESS);
	}
	
	
	
	
	@Override
	public Map<String, Object> save(UserInfo m, HttpServletRequest request) {
		String[] cateids = request.getParameterValues("cateids");
		if (cateids==null) {
			m.setCateIds("");
			return super.save(m, request);
		}
		String s=",";
		for (String string : cateids) {
			System.out.println(string);
			s=s+string+",";
		}
		s=s.trim();
		System.out.println(s);
		m.setCateIds(s);
		return super.save(m, request);
	}
	//是否冻结
	@RequestMapping("isFreezeSave")
	@ResponseBody
	public Map<String, Object> isFreezeSave(UserInfo m, HttpServletRequest request) {
		return super.save(m, request);
	}
	
	
	
	/**
	 * 后台 管理员 添加积分 
	 * @param userIds
	 * @param integration
	 * @return
	 */
	@RequestMapping("addIntegration")
	@ResponseBody
	public  Object  addIntegration(Integer[] userIds, Integer  integration){
		for (Integer userId : userIds) {
			UserInfo userInfo = userInfoService.findOne(userId);
			if(userInfo != null) {
				userInfoService.addIntegration(userInfo, "byAdmin","系统管理员赠送积分", integration, Boolean.FALSE);
			}
		}
		return  suc(SUCCESS);
	}
	
	
	@RequestMapping("adLlistCate")
	@ResponseBody
	public Object   adLlistCate(  Integer[] userIds, Integer  cateId) {
		for (Integer userId : userIds) {
			UserInfo userInfo = userInfoService.findOne(userId);
			if(userInfo != null) {
				String  oldCateIds = userInfo.getCateIds();
				if(StringUtils.isBlank(oldCateIds)) {
					userInfo.setCateIds(","+cateId+",");
				}else {
					if(oldCateIds.endsWith(","))
						userInfo.setCateIds(oldCateIds+cateId+",");
					else 
						userInfo.setCateIds(","+oldCateIds+cateId+",");
				}
				userInfoService.save(userInfo);
			}
		}
		return  suc(SUCCESS);
	}
	
	
	@RequestMapping(value = "exportExcel2")
	public ModelAndView exportExcel2(ModelMap model, HttpServletRequest request,
			HttpServletResponse response, UserInfoBean bean ) {
		ExcelView viewExcel = new ExcelView();
		Map<String, Object> obj = null;
		bean.createCateIds();
		// 获取数据库表生成的workbook
		Map<String, Object> condition = new HashMap<String, Object>();
		HSSFWorkbook workbook = userInfoService.generateWorkbook2(condition, bean);
		try {
			viewExcel.buildExcelDocument(obj, workbook, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(viewExcel, model);
	}
	
	//添加类别
	@RequestMapping("addAll")
	@ResponseBody
	public Map<String, Object> addAll(UserInfo m, HttpServletRequest request) {
		ResultBean b= new ResultBean();
		Boolean flag = Boolean.TRUE;
		String [] ids =request.getParameterValues("ids");
		String secondCateId =request.getParameter("secondCateId");
		if(ids!=null && ids.length>0){
			for (String string : ids) {
				
					UserInfo userInfo = userInfoService.findOne(Integer.valueOf(string));
					String cateids =userInfo.getCateIds();
					
					if (StringUtils.isNotEmpty(secondCateId) && !"null".equals(secondCateId)) {
						userInfo.setCateIds(cateids+","+secondCateId);
					}
					
					if(userInfoService.save(userInfo) == null){
						flag = Boolean.FALSE;
					}
				
			}
		}
		b.setIsSuccess(flag);
		return JsonMapper.beanToMap(b);
	}
	
//	@RequestMapping("vipList/p{pageNo}")
//	public String listVip(StatisticsInfoBean bean, Model model, @PathVariable Integer pageNo) {
//		bean.setSort("update_time.desc");
////		super.list(bean, model, pageNo);
//		return viewName("resources/vipList");
//	}
	
}

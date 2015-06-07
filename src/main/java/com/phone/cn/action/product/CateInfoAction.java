package com.phone.cn.action.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phone.cn.bean.product.CateInfoBean;
import com.phone.cn.bean.product.CateInfoDto;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.service.product.CateInfoService;
import com.phone.cn.service.product.MobileService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:44:21
 *   
 */
@Controller
@RequestMapping(value="admin/mobilecate")
@AdminUserLogin
public class CateInfoAction extends BaseCRUDController<CateInfoBean, CateInfo, Integer>{
	@Autowired
	private CateInfoService cateInfoService;
	
	@Autowired
	private MobileService mobileService;
	
	@Override
	public String list(CateInfoBean bean, Model model, Integer pageNo) {
		model.addAttribute("firstCates", cateInfoService.searchFirstCates());
		return super.list(bean, model, pageNo);
	}
	
	@ResponseBody
	@RequestMapping(value="jsonCate")
	public List<Map<String, Object>> jsonCate(@RequestParam Integer parentCateId){
		CateInfoBean b = new CateInfoBean();
		b.setParentCateId(parentCateId);
		return JsonMapper.beanToList(cateInfoService.queryAll(b));
	}
	
	@RequestMapping("loadByParent")
	@ResponseBody
	public Object loadByParent(@RequestParam Integer parentId) {
		CateInfoBean bean = new CateInfoBean();
		bean.setParentCateId(parentId);
		return suc(baseService.queryAll(bean));
	}
	
	/**
	 * 一级分类列表查询
	 */
	@RequestMapping("oneList/p{pageNo}")
	public String oneList(CateInfoBean bean, Model model,@PathVariable Integer pageNo){	
		if(pageNo == null){
    		pageNo =1;
    	}
    	bean.setPageNo(pageNo);
    	bean.setCateLevel(1);
		List<CateInfo> beans =cateInfoService.queryAllWithPage(bean, bean.toPageBounds());
		List<CateInfoDto> cateInfoDtos = new ArrayList<CateInfoDto>();
		
		for (CateInfo info : beans) {
            CateInfoDto dto = new CateInfoDto();
			dto.setId(info.getId());
			dto.setCateName(info.getCateName());
			CateInfoBean cateInfoBean = new CateInfoBean();
			cateInfoBean.setParentCateId(info.getId());
			//二级分类查询
			List<CateInfo> secondCates = cateInfoService.queryAll(cateInfoBean);
			Integer  count = 0 ;
			if(CollectionUtils.isNotEmpty(secondCates)) {
				// 用户的导入量
				for (CateInfo secondCateInfo : secondCates) {
					  count = mobileService.loadCountByCateId(secondCateInfo.getId());
				}	
			}
			dto.setTwoCateSize(secondCates.size());
			dto.setUserSize(count);
			cateInfoDtos.add(dto);
		}
		
	    model.addAttribute("infos", cateInfoDtos);
		return viewName("list");
	}
	/**
	 * 一级分类添加
	 */
	@RequestMapping("oneSave")
	@ResponseBody
	public Map<String, Object> oneSave(CateInfo m, HttpServletRequest request) {
		return super.save(m, request);
	}
	/**
	 * 二级分类列表查询
	 */
	@RequestMapping("twoList/p{pageNo}")
	public String twoList(CateInfoBean bean, Model model,@PathVariable Integer pageNo){
		bean.setCateLevel(2);
		List<CateInfo> beans =cateInfoService.queryAllWithPage(bean, bean.toPageBounds());
		List<CateInfoDto> cateInfoDtos = new ArrayList<CateInfoDto>();
		for (CateInfo cateInfo : beans) {
			CateInfoDto dto = new CateInfoDto();
			dto.setId(cateInfo.getId());
			dto.setCateName(cateInfo.getCateName());
			Integer count = 0 ;
			if (CollectionUtils.isEmpty(beans)) {
				//用户导入数查询
				count = mobileService.loadCountByCateId(cateInfo.getId());
			}
			dto.setUserSize(count);
			cateInfoDtos.add(dto);
		}
		System.out.println("cateInfoDtos大小"+cateInfoDtos.size());
		model.addAttribute("infos", cateInfoDtos);
		return "admin/mobilecate/twolist";
	}
	
	/**
	 * 二级分类添加
	 */
	@RequestMapping("twoSave")
	@ResponseBody
	public Map<String, Object> twoSave(CateInfo m, HttpServletRequest request) {
		System.out.println("父类的等级id="+m.getParentCateId());
		List<CateInfo> list = cateInfoService.findAll();
		for (CateInfo cateInfo : list) {
			if (cateInfo.getId().equals(m.getParentCateId())) {
				m.setMore1(cateInfo.getCateName());
			}
		}
		return super.save(m, request);
	}
}

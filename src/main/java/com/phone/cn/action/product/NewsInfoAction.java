package com.phone.cn.action.product;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lianhai.spring.security.util.SpringSecurityContent;
import com.phone.cn.bean.product.NewsInfoBean;
import com.phone.cn.entity.product.NewsCate;
import com.phone.cn.entity.product.NewsInfo;
import com.phone.cn.entity.sys.Manager;
import com.phone.cn.service.product.NewsCateService;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月14日 下午4:52:30
 *   
 */
@Controller
@RequestMapping(value="admin/newsinfo")
@AdminUserLogin
public class NewsInfoAction extends BaseCRUDController<NewsInfoBean, NewsInfo, Integer>{

	@Autowired
	private NewsCateService newsCateService;
	
	@Override
	public String list(NewsInfoBean bean, Model model, @PathVariable Integer pageNo) {
		model.addAttribute("allcates", newsCateService.findAll());
		return super.list(bean, model, pageNo);
	}
	
	@Override
	public String input(@PathVariable Integer id, Model model) {
		model.addAttribute("allcates", newsCateService.findAll());
		return super.input(id, model);
	}
	
	@Override
	public Map<String, Object> save(NewsInfo n, HttpServletRequest request) {
		String[] newsSpacess = request.getParameterValues("newsPlacess");
		
		if(n.getTopStatus() == null ){
			n.setTopStatus(false);
		}
		if(n.getIsrec()==null){
			n.setIsrec(false);
		}
		if(n.getTopImage()==null){
			n.setTopImage("");
		}
		if(n.getMore1()==null){
			n.setMore1("");
		}
		if(newsSpacess!=null && newsSpacess.length > 0){
			n.setNewsPlaces(Arrays.toString(newsSpacess));
		}
	    if (n.getCateName()==null) {
	    	List<NewsCate> cates =newsCateService.findAll();
		    for (NewsCate newsCate : cates) {
				if(newsCate.getId().equals(n.getCateId())){
					logger.debug("CateName添加成功");
					n.setCateName(newsCate.getCateName());
				}
			} 
		}
	    Manager m= SpringSecurityContent.getUser();
	   
	    logger.debug("用户的IDDDDDDDddddddddddd"+m.getId());
	    n.setManagerId(m.getId());
		return super.save(n, request);
	}
	/**
	 * 新闻添加跳转
	 * @param m
	 * @param request
	 * @return
	 */
	@RequestMapping("add")
	public String add(Model model) {
		List<NewsCate> cates = newsCateService.findAll();
		model.addAttribute("allcates", cates);
		return viewName("input");
	}
	
	
	
	
}

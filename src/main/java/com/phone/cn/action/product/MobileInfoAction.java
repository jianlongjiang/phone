package com.phone.cn.action.product;

import static com.phone.cn.utils.ExalUtils.getStringCellValue;
import static com.phone.cn.utils.ExalUtils.getValue;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.phone.cn.bean.ResultBean;
import com.phone.cn.bean.product.CateInfoBean;
import com.phone.cn.bean.product.MobileInfoBean;
import com.phone.cn.entity.product.CateInfo;
import com.phone.cn.entity.product.MobileInfo;
import com.phone.cn.service.product.CateInfoService;
import com.phone.cn.service.product.MobileService;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.action.BaseCRUDController;
import com.phone.cn.web.interceptor.AdminUserLogin;

/**
 * @author zgdcool
 * @version 2015年3月12日 下午8:34:00
 *   
 */
@Controller
@RequestMapping(value="admin/mobileinfo")
@AdminUserLogin
public class MobileInfoAction extends BaseCRUDController<MobileInfoBean, MobileInfo, Integer>{
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private CateInfoService cateInfoService;
	File tempPathFile;
	
	@ResponseBody
	@RequestMapping(value="deleteall")
	public Map<String, Object> deleteAll(HttpServletRequest request){
		ResultBean b = new ResultBean();
		Boolean flag = Boolean.TRUE;
		String[] ids = request.getParameterValues("ids");
		if(ids!=null && ids.length>0){
			for (String string : ids) {
				if(!mobileService.isDelete(Integer.parseInt(string))){
					flag = Boolean.FALSE;
				}
			}
		}
		b.setIsSuccess(flag);
		return JsonMapper.beanToMap(b);
	}

	@ResponseBody
	@RequestMapping("uploadMember")
	public Object uploadMember(Model model, HttpServletRequest request)
			throws InvalidFormatException, IOException, FileUploadException {
		String fielName = null;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
		factory.setRepository(tempPathFile);// 设置缓冲区目录

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		upload.setSizeMax(10485760); // 设置最大文件尺寸，这里是4MB
		List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
		Iterator<FileItem> i = items.iterator();
		while (i.hasNext()) {
			FileItem fi = (FileItem) i.next();
			if (!fi.isFormField()) {
				fielName = fi.getName();
				Workbook workBook = null;
				if (fielName.endsWith("xls")) {
					workBook = new HSSFWorkbook(fi.getInputStream());
				} else if (fielName.endsWith("xlsx")) {
					workBook = new XSSFWorkbook(fi.getInputStream());
				} else {
				//	return importExl(model);
//					workBook = WorkbookFactory.create(fi.getInputStream());
				}
				@SuppressWarnings("unused")
				FormulaEvaluator formulaEvaluator = workBook.getCreationHelper()
						.createFormulaEvaluator();
				Sheet sheet = workBook.getSheetAt(0);
				System.out.println(sheet.getLastRowNum());
				for (int j = 1; j < sheet.getLastRowNum() ; j++) {
					MobileInfo mobile = new MobileInfo();
					Row row = sheet.getRow(j);
					mobile.setMobile(getValue(getStringCellValue(row, 0)));
					logger.debug("=========================>"+getValue(getStringCellValue(row, 0)));
					if(mobileService.findByMobile(mobile.getMobile())!=null){
						logger.debug("=========================>电话号码已存在");
						continue;
					}
					String cateIds = getValue(getStringCellValue(row, 1));
					if(cateIds == null) cateIds = "1";
					mobile.setMobileFrom("others");
					mobile.setCateIds(cateIds);
//					System.out.println(mobile.getMobile()  +"---"+ cateIds);
//					baseService.save(mobile);
					//添加伪ID
					if (mobileService.isSave(mobile)) {	
						logger.debug("电话号码="+mobile);
						mobile = mobileService.findByMobile(mobile.getMobile());
						logger.debug("mobile="+mobile);
						Integer fakeID = 400000000+mobile.getId();
						mobile.setFakeId("w"+fakeID);
						mobileService.save(mobile);
						
					}
				}
				if (workBook != null) {
					workBook.close();
				}
				//model.addAttribute("msg", "会员信息上传成功");
			}
		}
		return suc(SUCCESS);
	}
	
	@Override
	public String list(MobileInfoBean bean, Model model,@PathVariable Integer pageNo) {
		CateInfoBean cateInfoBean = new CateInfoBean();		
		cateInfoBean.setCateLevel(2);
		List<CateInfo> cateInfos = cateInfoService.queryAll(cateInfoBean); 
		model.addAttribute("cateInfos", cateInfos);
		return super.list(bean, model, pageNo);
	}
	
	@RequestMapping("bibeiList/p{pageNo}")
	public String bibeiList(MobileInfoBean bean,Model model,@PathVariable Integer pageNo){
		if (pageNo == null) {
			pageNo = 1;
		}
		bean.setPageNo(pageNo);
		bean.setPageSize(10);
		//bean.setMore1("y");
		model.addAttribute("bean",bean);
		PageList<MobileInfo> infos = mobileService.queryAllWithPage(bean, bean.toPageBounds());
		model.addAttribute("page",infos.getPaginator());
		model.addAttribute("infos",infos);
		return viewName("bibeiList");
	}
	
	
	@RequestMapping("disuse")
	@ResponseBody
	public Map<String, Object> disuse(MobileInfo m, HttpServletRequest request) {
		return super.save(m, request);
	}
	
	@RequestMapping("bibeiAddMobile")
	@ResponseBody
	public Map<String, Object> bibeiAddMobile(MobileInfo m, HttpServletRequest request) {
		if (mobileService.findByMobile(m.getMobile())==null) {
			m.setMore1("n");
			m.setMobileFrom("others");
			if(mobileService.isSave(m)){
				m = mobileService.findByMobile(m.getMobile());
				logger.debug("--------------------->mobile="+m.getMobile());
				Integer fakeID = 400000000+m.getId();
				m.setFakeId("w"+fakeID);
				mobileService.save(m);
			}
			return suc(SUCCESS);
		}
		return fail(FAIL);
	}
	
}

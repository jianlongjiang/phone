package com.phone.cn.service.member;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phone.cn.bean.member.CashUserLogBean;
import com.phone.cn.entity.member.CashUserLog;
import com.phone.cn.entity.member.UserInfo;
import com.phone.cn.mapper.member.CashUserLogMapper;
import com.phone.cn.service.BaseService;
import com.phone.cn.utils.JsonMapper;

@Service
public class CashUserLogService extends BaseService<CashUserLog, java.lang.Integer> {

	@Autowired
	CashUserLogMapper mapper;
	
	public void userReadAll(Integer userId) {
		mapper.userReadAll(userId);
	}

	
	/**
	 * 用户收到红包个数
	 * @param userInfo
	 * @return
	 */
	public Integer userGetRedCount(UserInfo userInfo) {
		return mapper.userGetRedCount(userInfo.getId());
	}

	
	/**
	 * @Title: generateWorkbook
	 * @Description: 根据条件生成excel文本对象
	 * @param condition
	 * @return HSSFWorkbook 返回类型
	 * @throws
	 */
	@SuppressWarnings("resource")
	public HSSFWorkbook generateWorkbook2(Map<String, Object> condition11, CashUserLogBean bean) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		List<CashUserLog> list = mapper.query(bean);
		if (list != null) {
			// excel文件名
			String sheetName = "蜗牛巴巴打款管理";
			try {
				String[] EnrollArray = new String[] { "id","userId","nickname","mobile","cash","aplipay","action","createTime" };
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
	
	//批量失败
	public Integer batchFail(String ids){
		Pattern p = Pattern.compile("\n");
		String [] strIds = p.split(ids);
		logger.debug("---------------------------------------------------------------------->ids="+ids); 
//		String [] str = ids.split("\\,");
		Integer flag = 0;
		for (String s : strIds) {
			if (StringUtils.isEmpty(s)) {
				return flag;
			}
			logger.debug("---------------------->s="+s);
//			Integer i = Integer.parseInt(s);
//			System.out.println(i);
			CashUserLog c = mapper.selectByPrimaryKey(Integer.valueOf(s));
			if (c!=null) {
				if(StringUtils.equals(c.getDoStatus(), "2") ){
					c.setDoStatus("0");
				}
				flag=mapper.updateByPrimaryKeySelective(c);	
			}	
		}
		logger.debug("---------------------------------------------------------------------->flag="+flag); 
		return flag;
	}


	/**
	 * 今日收到红包
	 * @return
	 */
	public Integer loadUserDayRedCount(Integer userId) {
		return mapper.loadUserDayRedCount(userId);
	}

	public List<CashUserLog> loadLastLog(Integer userId) {
		return  mapper.loadCurDayLogs(userId);
	}
}

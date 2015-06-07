package com.phone.cn.utils;

//import static com.sky.kf.vip.base.util.ExalUtils.addRowCellValue;

import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


/**
 * office 工具类 1： Workbook.create(InputSt)
 * 
 * @author long.jiang
 * @version 创建时间：2014-8-19 下午2:21:55
 */
public class ExalUtils{

	// 1 Workbook woorkbook = WorkbookFactory.create(input);
	// 2 Sheet sheet = woorkbook.getSheetAt(0);
	// 3 Row row = sheet.getRow(0);
	// 4 Cell cell =row.getCell(0);
	// 5 cell.getStringCellValue();
	// 6 woorkbook.write(output);

	public static String getStringCellValue( Row row, int index ) {
		Cell cell = row.getCell(index);
		if (cell == null) {
			return null;
		}
		Object result;

		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_BLANK); // 3
		// 空白
		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_BOOLEAN); // 4
		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_ERROR); // 5
		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_FORMULA); // 2
		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_NUMERIC); // 0
		// System.out.println("CELL_TYPE_BLANK:" + cell.CELL_TYPE_STRING); // 1

		switch (cell.getCellType()) { // cell 的 格式
			case Cell.CELL_TYPE_STRING: // 字符串
				result = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC: // 时间格式/ 数字格式

				if (DateUtil.isCellDateFormatted(cell)) { // poi
					result = cell.getDateCellValue();
				} else {
					result = cell.getNumericCellValue();
				}
				result = cell.getDateCellValue(); // date
				result = cell.getNumericCellValue(); // double
				break;
			case Cell.CELL_TYPE_FORMULA: // getCellFormula
				try {
					result = cell.getNumericCellValue(); // 公式的值 double
				} catch (Exception e) {
					result = cell.getCellFormula(); // / 公式的表达式
					e.printStackTrace();
				}
				break;
			case Cell.CELL_TYPE_ERROR:
				result = cell.getErrorCellValue();
				// result = cell.getErrorCellString();
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			default:
				result = null;
				break;
		}
		
		if (result != null){
			System.out.println("inde:"+index+",value:"+result.toString());
			return result.toString();
		}
		return null;
	}

	public static Date getDateCellValue( Row row, int index ) {

		Cell cell = row.getCell(index);
		if (cell == null) {
			return null;
		}

		if (DateUtil.isCellDateFormatted(cell)) {
			return cell.getDateCellValue();
		} else {
			return null;
		}
	}

	public static Double getDoubleCellValue( Row row, int index ) {
		String result = getStringCellValue(row, index);
		Double number = null;
		if (result != null) {
			try {
				number = Double.parseDouble(result);
			} catch (NumberFormatException e) {
				number = null;
			}
		}
		return number;
	}

	public static Integer getIntegerCellValue( Row row, int index ) {
		String result = getStringCellValue(row, index);
		Integer number = null;
		if (result != null) {
			try {
				Double d = Double.parseDouble(result);
				number = d.intValue();
			} catch (NumberFormatException e) {
				number = null;
			}
		}
		return number;
	}

	/**
	 * 科学计数 3.40256010353E11===》340256010353
	 * 
	 * @param numStr
	 * @return
	 */
	public static String getValue( String numStr ) {
		/*
		 * 1 大数的方法
		 */
		// BigDecimal bd = new BigDecimal("3.40256010353E11");
		// System.out.println(bd.toPlainString());

		if (numStr == null)
			return null;

		String regex = "^((\\d+.?\\d+)[Ee]{1}(\\d+))$";
		Pattern pattern = Pattern.compile(regex);

		Matcher m = pattern.matcher(numStr);
		boolean b = m.matches();
		if (b) {
			DecimalFormat df = new DecimalFormat("#");
			numStr = df.format(Double.parseDouble(numStr));
		}
		return numStr;
	}

	public static void addRowCellValue( Row row, Object o ) {
		Cell cell = row.createCell(row.getLastCellNum() > 0 ? row.getLastCellNum() : 0);
		if (o == null) {
			cell.setCellValue("");
		} else {
			cell.setCellValue(o.toString());
		}

	}

	public static void addRowCellValue( Row row, String text ) {
		Cell cell = row.createCell(row.getLastCellNum() > 0 ? row.getLastCellNum() : 0);
		cell.setCellValue(text);
	}

	public static void addRowCellValue( Row row, Integer text ) {
		Cell cell = row.createCell(row.getLastCellNum() > 0 ? row.getLastCellNum() : 0);
		if (text == null) {
			cell.setCellValue("");
		} else {
			cell.setCellValue(text);
		}
	}

	public static void addRowCellValue( Row row, Date time ) {
		Cell cell = row.createCell(row.getLastCellNum() > 0 ? row.getLastCellNum() : 0);
		if (time == null) {
			cell.setCellValue("");
		} else {
			DateFormat dateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			cell.setCellValue(com.sky.kf.vip.base.util.DateUtil.fullPatternDateToString(time));
			cell.setCellValue(dateFormate.format(time));
		}
	}

	@SuppressWarnings({ "unused", "null" })
	public static void main( String[] args ) {

		BigDecimal bd = new BigDecimal("3.40256010353E11");
		System.out.println(bd.toPlainString());
		System.out.println(getValue("3.40256010353E11"));

		List<Object> themeExals = null;
		InputStream in = null;
		OutputStream out = null;
		try {
			Workbook workbook = WorkbookFactory.create(in);

			Sheet sheet = workbook.getSheetAt(0);
			int rowNum = 1;
			Row title = sheet.createRow(0);
			addRowCellValue(title, "主题id");
			addRowCellValue(title, "skyId");
			addRowCellValue(title, "对外Id");
			addRowCellValue(title, "冒泡账号");
			addRowCellValue(title, "板块");
			addRowCellValue(title, "发布时间");
			addRowCellValue(title, "主题");
			addRowCellValue(title, "类容");
			for (Object themeExal : themeExals) {
				Row row = sheet.createRow(rowNum++);
				// int cellNum = 0;
				// 主题id
				// addRowCellValue(row, themeExal.getThemeId());
				// skyId
				// addRowCellValue(row, themeExal.getSkyId());
				// 对外Id
				// addRowCellValue(row, themeExal.getVipNo());
				// 冒泡账号
				// addRowCellValue(row, themeExal.getAccount());
				// 板块
				// addRowCellValue(row, themeExal.getBlockName());
				// 发布时间
				// addRowCellValue(row,
				// DateUtil.fullPatternDateToString(themeExal.getCreateTime()));
				// 主题
				// addRowCellValue(row, themeExal.getTitle());
				// 类容
				// addRowCellValue(row, themeExal.getContent());
			}

			workbook.write(out);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// return null;

	}

}

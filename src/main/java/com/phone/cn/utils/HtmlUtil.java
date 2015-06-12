package com.phone.cn.utils;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.springframework.web.util.HtmlUtils;

public class HtmlUtil {

	/**
	 * 一般用于 list 页面
	 * 去除html 的标签元素 默认长度为150
	 * @param htmlStr
	 * @return
	 */
	public static String htmlUnescape(String htmlStr) {
		return htmlUnescape(htmlStr, 150);
	}

	/**
	 * 去除html 的标签元素
	 * @param htmlStr
	 * @param textLength 1:null 全部返还,  2: 指定长度
	 * @return
	 */
	public static String htmlUnescape(String htmlStr, Integer textLength) {
		try {
			String str = HtmlUtils.htmlUnescape(htmlStr);
			Parser parser = new Parser();
			parser.setInputHTML(str);
			TextExtractingVisitor visitor = new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			str = visitor.getExtractedText();
			if(textLength == null){
				// 无字数限制
				return str;
			}
			if (str.length() > textLength) {
				return str.substring(0, textLength) + "...";
			} else {
				return str;
			}
		} catch (ParserException e) {
			e.printStackTrace();
			return null;
		}
	}
}

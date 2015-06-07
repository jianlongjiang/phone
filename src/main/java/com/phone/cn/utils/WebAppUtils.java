package com.phone.cn.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.CookieGenerator;
import org.springframework.web.util.WebUtils;

public class WebAppUtils extends WebUtils {

    public static String escapeParamString(String value) {

        if (value == null)
            return "";

        // value = StringUtils.replace(value, "|", "");
        value = StringUtils.replace(value, "&amp;", "");
        // value = StringUtils.replace(value, ";", "");
        value = StringUtils.replace(value, "$", "");
        // value = StringUtils.replace(value, "%", "");
        // value = StringUtils.replace(value, "@", "");
        value = StringUtils.replace(value, "'", "");
        value = StringUtils.replace(value, "\"", "");
        value = StringUtils.replace(value, "\\'", "");
        value = StringUtils.replace(value, "&lt;", "");
        value = StringUtils.replace(value, "&gt;", "");
        value = StringUtils.replace(value, "<", "");
        value = StringUtils.replace(value, ">", "");
        // value = StringUtils.replace(value, "(", "");
        // value = StringUtils.replace(value, ")", "");
        // value = StringUtils.replace(value, "+", "");
        // value = StringUtils.replace(value, "\n", "");
        value = StringUtils.replace(value, "\r", "");
        // value = StringUtils.replace(value, ",", "");
        value = StringUtils.replace(value, "\\", "");
        value = value.trim();
        return value;
    }

    /**
     * 将文本显示转换为html显示，类似php中的nl2br
     * 
     * @param value
     *            源字符串
     * @return 转换后的字符�?
     */
    public static String toHTML(String value) {
        if (value == null)
            return "";
        value = StringUtils.replace(value, "&", "&amp;");
        value = StringUtils.replace(value, "<", "&lt;");
        value = StringUtils.replace(value, ">", "&gt;");
        value = StringUtils.replace(value, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        value = StringUtils.replace(value, "\r\n", "\n");
        value = StringUtils.replace(value, "\"", "&quot;");
        value = StringUtils.replace(value, "\n", "<br>");
        value = StringUtils.replace(value, "  ", "&nbsp;&nbsp;");
        value = StringUtils.replace(value, "'", "&#39;");
        value = StringUtils.replace(value, "\\", "&#92;");
        return value;
    }

    /**
     * 获取当前时间
     * 
     * @return java.util.Date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * 获取当前IP
     * 
     * @param request
     * @return
     */
    public static String getCurrentIP(HttpServletRequest request) {
        if (request == null)
            return null;
        String result = request.getHeader("X-Real-IP");
        if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
            result = request.getHeader("x-forwarded-for");
        }
        if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
            result = request.getHeader("Proxy-Client-IP");
        }
        if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
            result = request.getHeader("WL-Proxy-Client-IP");
        }
        if (result == null || result.length() == 0 || "unknown".equalsIgnoreCase(result)) {
            result = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (!StringUtils.isEmpty(result)) {
            if (result.indexOf(".") != -1) { // 没有"."肯定是非IPv4格式
                Pattern pat = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}");
                Matcher mat = pat.matcher(result);
                result = null;
                while (mat.find()) {
                    result = mat.group(0);
                    break;
                }
            } else
                result = null;
        }
        if (StringUtils.isEmpty(result))
            result = request.getRemoteAddr();
        return result;
    }

    /**
     * 将汉字转换为全拼
     */
//    public static String getPingYin(String src) {
//        char[] t1 = null;
//        t1 = src.toCharArray();
//        String[] t2 = new String[t1.length];
//        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
//        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
//        String t4 = "";
//        int t0 = t1.length;
//        try {
//            for (int i = 0; i < t0; i++) {
//                // 判断是否为汉字字�?
//                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
//                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
//                    t4 += t2[0];
//                } else
//                    t4 += java.lang.Character.toString(t1[i]);
//            }
//            return t4;
//        } catch (BadHanyuPinyinOutputFormatCombination e1) {
//            e1.printStackTrace();
//        }
//        return t4;
//    }

    /**
     * 
     * checkPingyin:(是否含有中文).
     * 
     * @author zengxiangzhu
     * @param src
     * @return
     * @since JDK 1.6
     */
    public static boolean checkPingyin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        for (int i = 0; i < t1.length; i++) {
            // 判断是否为汉字字�?
            if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    /**
     * 半角的函�?DBC case) 任意字符�?半角字符�?全角空格�?2288，半角空格为32
     * 其他字符半角(33-126)与全�?65281-65374)的对应关系是：均相差65248
     */
    public static String toDBC(String input) {
        char[] c = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == 12288) {
                c[i] = (char) 32;
                continue;
            }
            if (c[i] > 65280 && c[i] < 65375)
                c[i] = (char) (c[i] - 65248);
        }
        return new String(c);
    }

    // 当前时间转成特定的时间格�?
    public static Date getFormatDate(String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        String temp = format.format(WebAppUtils.getCurrentDate());
        try {
            return format.parse(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getDateFormatString(Date date, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(date);
    }

    public static String getDigitFromString(String property) {
        if (property == null)
            return "";
        String value = "";
        for (int i = 0; i < property.length(); i++) {
            if (!Character.isDigit(property.charAt(i)) || (i > 2))
                break;
            value = value + property.charAt(i);
        }

        return value;
    }

    public static void setCookie(HttpServletResponse response, String name, String value, String cookieDomain) {
        CookieGenerator c = new CookieGenerator();
        c.setCookieName(name);
        if (StringUtils.isNotEmpty(cookieDomain)) {
            c.setCookieDomain(cookieDomain);
        }
        // 浏览器�?出就无效
        c.setCookieMaxAge(24*60*60);
        c.addCookie(response, value);
    }

    // 属�?名称和属性�?中的冒号":"被转换为�?#cln#"; 分号";"被转换为�?#scln#"
    public static String getProp(String prop) {
        if (StringUtils.isEmpty(prop)) {
            return "";
        }
        return prop.replace("#cln#", ":").replace("#scln#", ";");
    }

    public static String convertProp(String prop) {
        if (StringUtils.isEmpty(prop)) {
            return "";
        }
        return prop.replace(":", "#cln#").replace(";", "#scln#");
    }

}
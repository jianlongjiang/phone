package com.phone.cn.utils;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;


public class ParamUtils {

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not found
	 *         or if the parameter is a zero-length string.
	 */
	public static String getParameter(HttpServletRequest request, String name) {
		return getParameter(request, name, false);
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param emptyStringsOK
	 *            Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not
	 *         found.
	 */
	public static String getParameter(HttpServletRequest request, String name, boolean emptyStringsOK) {
		String temp = request.getParameter(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	public static String getStringParameter(HttpServletRequest request, String name, String defaultString) {
		return getParameter(request, name, defaultString);
	}

	/**
	 * 获取request中的parameter名称为name的�?，如果为null�?"则返回defaultString
	 * 
	 * @param request
	 *            httpservletrequest对象
	 * @param name
	 *            参数名称
	 * @param defaultString
	 *            返回默认�?
	 * @return 参数�?
	 */
	public static String getParameter(HttpServletRequest request, String name, String defaultString) {
		String temp = request.getParameter(name);
		if ((temp != null) && (!temp.equals(""))) {
			return temp;
		} else {
			return defaultString;
		}
	}

	/**
	 * 过滤的表单参数取�?
	 * 
	 * @param request
	 * @param name
	 *            表单参数名称
	 * @param maxLength
	 *            长度�? 0 时有�?
	 * @param defaultString
	 *            为空时取的默认�?
	 * @return 参数�?
	 */
	public static String getFilteredParameter(HttpServletRequest request, String name, int maxLength, String defaultString) {
		String temp = request.getParameter(name);
		if ((temp != null) && (!temp.equals(""))) {
			temp = WebAppUtils.escapeParamString(temp);
			if (maxLength > 0)
				temp = StringUtils.substring(temp, 0, maxLength);
			return temp;
		} else {
			return defaultString;
		}
	}

	public static String[] getFilteredParameters(HttpServletRequest request, String name, int maxLength, String defaultString) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new String[0];
		}
		String[] values = new String[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				String temp = paramValues[i];
				if ((temp != null) && (!temp.equals(""))) {
					temp = WebAppUtils.escapeParamString(temp);
					if (maxLength > 0)
						temp = StringUtils.substring(temp, 0, maxLength);
					values[i] = temp;
				} else {
					values[i] = defaultString;
				}
			} catch (Exception e) {
				values[i] = defaultString;
			}
		}
		return values;
	}

	/**
	 * Gets a parameter as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request, String name) {
		return getBooleanParameter(request, name, false);
	}

	/**
	 * Gets a parameter as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return True if the value of the parameter was "true", false otherwise.
	 */
	public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultVal) {
		String temp = request.getParameter(name);
		if ("true".equals(temp) || "on".equals(temp)) {
			return true;
		} else if ("false".equals(temp) || "off".equals(temp)) {
			return false;
		} else {
			return defaultVal;
		}
	}

	/**
	 * Gets a parameter as an int.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The int value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static Integer getIntParameter(HttpServletRequest request, String name, Integer defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Integer num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
		return getIntParameter(request, name, new Integer(defaultNum));
	}

	/**
	 * Gets a list of int parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into an int.
	 */
	public static int[] getIntParameters(HttpServletRequest request, String name, int defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new int[0];
		}
		int[] values = new int[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * Gets a list of int parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into an int.
	 */
	public static Integer[] getIntegerParameters(HttpServletRequest request, String name, Integer defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new Integer[0];
		}
		Integer[] values = new Integer[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Integer.parseInt(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	/**
	 * Gets a parameter as a double.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The double value of the parameter specified or the default value
	 *         if the parameter is not found.
	 */
	public static Double getDoubleParameter(HttpServletRequest request, String name, Double defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Double num = defaultNum;
			try {
				num = Double.parseDouble(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
		return getDoubleParameter(request, name, new Double(defaultNum));
	}

	/**
	 * Gets a parameter as a float.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The double value of the parameter specified or the default value
	 *         if the parameter is not found.
	 */
	public static Float getFloatParameter(HttpServletRequest request, String name, Float defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Float num = defaultNum;
			try {
				num = Float.parseFloat(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static float getFloatParameter(HttpServletRequest request, String name, float defaultNum) {
		return getFloatParameter(request, name, new Float(defaultNum));
	}

	/**
	 * Gets a parameter as a long.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The long value of the parameter specified or the default value if
	 *         the parameter is not found.
	 */
	public static Long getLongParameter(HttpServletRequest request, String name, Long defaultNum) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			Long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
		return getLongParameter(request, name, new Long(defaultNum));
	}

	/**
	 * Gets a list of long parameters.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param defaultNum
	 *            The default value of a parameter, if the parameter can't be
	 *            converted into a long.
	 */
	public static long[] getLongParameters(HttpServletRequest request, String name, long defaultNum) {
		String[] paramValues = request.getParameterValues(name);
		if (paramValues == null) {
			return null;
		}
		if (paramValues.length < 1) {
			return new long[0];
		}
		long[] values = new long[paramValues.length];
		for (int i = 0; i < paramValues.length; i++) {
			try {
				values[i] = Long.parseLong(paramValues[i]);
			} catch (Exception e) {
				values[i] = defaultNum;
			}
		}
		return values;
	}

	public static java.util.Date getDateParameter(HttpServletRequest request, String name, String format, java.util.Date defaultDate) {
		String temp = request.getParameter(name);
		if (temp != null && !temp.equals("")) {
			try {
				java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(format);
				defaultDate = formatter.parse(temp);

			} catch (Exception ignored) {
				//
			}
		}
		return defaultDate;
	}

	public static java.util.Date getDateParameter(HttpServletRequest request, String name, java.util.Date defaultDate) {
		return getDateParameter(request, name, "yyyy-MM-dd", defaultDate);
	}

	public static java.util.Date getDateParameter(HttpServletRequest request, String name, String format) {
		return getDateParameter(request, name, format, null);
	}

	public static java.util.Date getDateParameter(HttpServletRequest request, String name) {
		return getDateParameter(request, name, "yyyy-MM-dd", null);
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @return The value of the parameter or null if the parameter was not found
	 *         or if the parameter is a zero-length string.
	 */
	public static String getAttribute(HttpServletRequest request, String name) {
		return getAttribute(request, name, false);
	}

	/**
	 * Gets a parameter as a string.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the parameter you want to get
	 * @param emptyStringsOK
	 *            Return the parameter values even if it is an empty string.
	 * @return The value of the parameter or null if the parameter was not
	 *         found.
	 */
	public static String getAttribute(HttpServletRequest request, String name, boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(name);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return temp;
			}
		} else {
			return null;
		}
	}

	/**
	 * Gets an attribute as a boolean.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return True if the value of the attribute is "true", false otherwise.
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request, String name) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gets an attribute as a int.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return The int value of the attribute or the default value if the
	 *         attribute is not found or is a zero length string.
	 */
	public static int getIntAttribute(HttpServletRequest request, String name, int defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * Gets an attribute as a long.
	 * 
	 * @param request
	 *            The HttpServletRequest object, known as "request" in a JSP
	 *            page.
	 * @param name
	 *            The name of the attribute you want to get
	 * @return The long value of the attribute or the default value if the
	 *         attribute is not found or is a zero length string.
	 */
	public static long getLongAttribute(HttpServletRequest request, String name, long defaultNum) {
		String temp = (String) request.getAttribute(name);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
			}
			return num;
		} else {
			return defaultNum;
		}
	}
	/**
	 * 取得带相同前�?��Request Parameters.会对字符串进行过�?
	 * 注意事项：如果参数�?是多个的，那么将会被转成字符�?xx;xx;xx;xx"的存�?
	 * 写此方法的目的是为了复�?的属性�?可以存储
	 * 
	 * 返回的结果Parameter名已去除前缀
	 */
	public static Map<String,String> getParametersStartingWith(Map<String, String[]> parameters, String prefix) {
		Map<String,String> params = new LinkedHashMap<String,String>();
		if (prefix == null) {
			prefix = "";
		}
		for (String key : parameters.keySet()) {
			if ("".equals(prefix) || key.startsWith(prefix)) {
				String unprefixed = key.substring(prefix.length());
				String[] values = parameters.get(key);
				if (values == null || values.length == 0) {//NOSONAR
					// Do nothing, no values found at all.
				} 
				else if (values.length > 1) {
					if(key.startsWith("attr_") || key.startsWith("unit_")){
						for(int i=0; i<values.length; i++){
							String val = WebAppUtils.convertProp(values[i]);
							values[i] = val;
						}
					}
					String temp;
					if(key.startsWith("fact_")){
					    temp=StringUtils.join(values, ',');
					}else{
					    temp=StringUtils.join(values, ';');
					}
					params.put(unprefixed, WebAppUtils.escapeParamString(temp));
				}
				else {
					if(!StringUtils.isEmpty(values[0])) {
						//属�?名称和属性�?中的冒号":"被转换为�?#cln#"; 分号";"被转换为�?#scln#"
						if(key.startsWith("attr_") || key.startsWith("unit_")){
							values[0] = WebAppUtils.convertProp(values[0]);
						}
						params.put(unprefixed, WebAppUtils.escapeParamString(values[0]));
					} else {
						params.put(unprefixed, "");
					}
				}
			}
		}
		return params;
	}
}

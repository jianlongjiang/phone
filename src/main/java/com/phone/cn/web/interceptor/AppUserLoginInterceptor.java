package com.phone.cn.web.interceptor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.phone.cn.bean.ResultBean;
import com.phone.cn.conf.DataConfig;
import com.phone.cn.constant.ErrorCodeConstant;
import com.phone.cn.utils.JsonMapper;
import com.phone.cn.web.MySessionContext;

/**
 * @author zgdcool
 * @version 2014年10月22日 下午3:59:23
 * 
 *          _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\|
 *          |// `. / \\||| : |||// \ / _||||| -:- |||||- \ | | \\\ - /// | | |
 *          \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ .""
 *          '< `.___\_<|>_/___.' >'"". | | : `- \`.;`\ _ /`;.`/ - ` : | | \ \
 *          `-. \_ __\ /__ _/ .-` / /
 *          ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰:
 *          写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠； 酒醉酒醒日复日，网上网下年复年。
 *          但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 */
public class AppUserLoginInterceptor extends HandlerInterceptorAdapter{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
//		response.setContentType("application/json;charset=UTF-8");
		super.afterCompletion(request, response, handler, ex);
	}
	
	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler )
			throws Exception {
		String appToken = request.getParameter(DataConfig.APP_TOKEN);
//		logger.warn(appToken);
		
		// 跨域  test
		response.setHeader("Access-Control-Allow-Origin", "*");
		Map<String, String[]> map = request.getParameterMap();
		String url = request.getRequestURL().toString();
		if(url.contains("resources")|| url.contains(".css") || url.contains(".js")){
			
		}else {
			logger.warn("\n"+ "=============================================>url:"+request.getRequestURL() + "----");
			
		}
		for (Map.Entry<String, String[]> entry : map.entrySet()) {
			logger.warn("\n------------------>"+entry.getKey() + ":"+JsonMapper.toJsonStr(entry.getValue())+"-----");
		}
		
//		Map<String, Object> map = request.getParameterMap();
//		for (Map.Entry<String, Object> entry : map.entrySet()) {
//			console.log(entry.getKey() + ":"+JsonMapper.toJsonStr(entry.getValue())+"-----");
//		}
		HttpSession session = null;
		try {
			if(isNeedLogin(handler, getCurrentUrl(request)) ){
				// 通过 sessionID  来获取 session, app 形式不确定session唯一
				 session = MySessionContext.getInstance().getSession(appToken);
				// 跨域
				response.setHeader("Access-Control-Allow-Origin", "*");
				if(session ==null || session.getAttribute(DataConfig.APP_USER) == null){
					ResultBean resultBean = ResultBean.fail(ErrorCodeConstant.OUT_TIME_CODE, ErrorCodeConstant.OUT_TIME_MSG);
					String jsonStr = JsonMapper.toJsonStr(resultBean);
					response.getWriter().print(jsonStr);
					logger.warn("登入超时啊------"+jsonStr);
					return false;
				}
			}
		} catch (Exception e) {
			ResultBean resultBean = ResultBean.fail(ErrorCodeConstant.OUT_TIME_CODE, ErrorCodeConstant.OUT_TIME_MSG);
			String jsonStr = JsonMapper.toJsonStr(resultBean);
			response.getWriter().print(jsonStr);
			if(session != null)
				MySessionContext.getInstance().DelSession(session);
			return false;
		}
		return true;
	}

	public Boolean isNeedLogin( Object obj, String s ) {
		if (ClassUtils.isAssignableValue(HandlerMethod.class, obj)) {
			HandlerMethod hm = (HandlerMethod) obj;
			if (AnnotationUtils.findAnnotation(hm.getBeanType(), AppUserLogin.class) != null
					|| hm.getMethodAnnotation(AppUserLogin.class) != null) {
				
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
//		return Boolean.TRUE;
	}

	protected static String getCurrentUrl( HttpServletRequest request ) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURL());
		if (request.getQueryString() != null)
			sb.append("?").append(escapeParamString(request.getQueryString()));
		if (sb.toString().endsWith("?"))
			sb.substring(0, sb.length() - 1);
		return URLEncoder.encode(sb.toString(), "UTF-8");
	}

	public static String escapeParamString( String value ) {

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
		value = StringUtils.replace(value, "(", "");
		value = StringUtils.replace(value, ")", "");
		value = StringUtils.replace(value, "+", "");
		// value = StringUtils.replace(value, "\n", "");
		value = StringUtils.replace(value, "\r", "");
		// value = StringUtils.replace(value, ",", "");
		value = StringUtils.replace(value, "\\", "");

		return value;
	}
}

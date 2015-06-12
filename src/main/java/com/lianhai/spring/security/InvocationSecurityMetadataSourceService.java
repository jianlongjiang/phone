package com.lianhai.spring.security;

/**
 * 
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.phone.cn.entity.sys.Resources;
import com.phone.cn.entity.sys.Role;
import com.phone.cn.service.sys.ResourcesService;
import com.phone.cn.service.sys.RoleService;

/*
 * 
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 * 注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 * 事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * 
 * 此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 * 说明：对于方法的spring注入，只能在方法和成员变量里注入，
 * 如果一个类要进行实例化的时候，不能注入对象和操作对象，
 * 所以在构造函数里不能进行操作注入的数据。
 */
@Service("mySecurityMetadataSource")
public class InvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(InvocationSecurityMetadataSourceService.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourcesService resourcesService;;

	// private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	// private RequestMatcher requestMatcher = new AntPathRequestMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	@SuppressWarnings("static-access")
	
	@PostConstruct
	public void loadResourceDefine() throws Exception {
		this.resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		logger.info("=====================>  数据加载..     resources-----> roles");
		// 通过数据库中的信息设置，resouce和role

		// for (Role item:this.roleService.getAllRoles()){
		// Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		// ConfigAttribute ca = new SecurityConfig(item.getName());
		// atts.add(ca);
		// List<Resources> resourcesList =
		// resourcesService.findByRoleId(item.getId());
		// //把资源放入到spring security的resouceMap中
		// for(Resources resources:resourcesList){
		// logger.debug("获得角色：["+item.getName()+"]拥有的acton有："+resources.getUri());
		// this.resourceMap.put(resources.getUri(), atts);
		// }
		// }

		List<Resources> resourcees = resourcesService.loadAll();
		if(resourcees != null){
			for (Resources resources : resourcees) {
				Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
				for (Role role : roleService.loadByResources(resources.getId())) {
					ConfigAttribute ca = new SecurityConfig(role.getName());
					atts.add(ca);
				}
				this.resourceMap.put(resources.getUri(), atts);
			}
		}

		/*
		 * //通过硬编码设置，resouce和role resourceMap = new HashMap<String,
		 * Collection<ConfigAttribute>>(); Collection<ConfigAttribute> atts =
		 * new ArrayList<ConfigAttribute>(); ConfigAttribute ca = new
		 * SecurityConfig("admin"); atts.add(ca);
		 * resourceMap.put("/jsp/admin.jsp", atts);
		 * resourceMap.put("/swf/zara.html", atts);
		 */

	}

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - start"); //$NON-NLS-1$
		}
		
		
		// guess object is a URL.
		// String url = ((FilterInvocation) object).getRequestUrl();
		FilterInvocation fi = (FilterInvocation) object;
		logger.info("==============> According to a URL, Find out permission configuration of this URL."+fi);
		if(resourceMap == null) return  null;
		Iterator<String> ite = resourceMap.keySet().iterator();

		List<ConfigAttribute> allows = new ArrayList<ConfigAttribute>(); 
		while (ite.hasNext()) {
			String resURL = ite.next();
			String   doResURL = resURL;
			if(StringUtils.isNotBlank(resURL)){
				if(resURL.endsWith("**")) {
			//		resURL+="**";
				}else if(resURL.endsWith("*")) {
					doResURL+="*";
				}else {
					doResURL+="**";
				}
			}
			
			if(StringUtils.equals("/admin/mobilecate/oneList/p**",doResURL)){
				System.out.println("");
			}
			RequestMatcher requestMatcher = new AntPathRequestMatcher(doResURL,null, true);
//			new AntPathRequestMatcher(pattern, httpMethod, caseSensitive);
//			RegexUrlPathMatch
			logger.debug("==============> spring security: "+ resURL); //$NON-NLS-1$
//			resURL.to
			if (requestMatcher.matches(fi.getHttpRequest())) {
				logger.info("===================   访问的url  和收保护的index  >"+ resURL);
				Collection<ConfigAttribute> returnCollection = resourceMap.get(resURL);
				if (logger.isDebugEnabled()) {
					logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$
				}
//				return returnCollection;
				if(CollectionUtils.isNotEmpty(returnCollection))
					allows.addAll(returnCollection);
				
			}
//			return  allows;
			// if (urlMatcher.pathMatchesUrl(url, resURL)) {
			// Collection<ConfigAttribute> returnCollection =
			// resourceMap.get(resURL);
			// if (logger.isDebugEnabled()) {
			//					logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$
			// }
			// return returnCollection;
			// }
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getAttributes(Object) - end"); //$NON-NLS-1$
		}
		
		return allows;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}
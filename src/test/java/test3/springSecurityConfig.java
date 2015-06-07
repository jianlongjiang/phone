package test3;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;


public class springSecurityConfig {
	
	public static void main(String[] args) {
		
		System.out.println("xxxx");
		Map<String, Collection<ConfigAttribute>> resourceMap = new HashMap<String, Collection<ConfigAttribute>>();;
		
		Collection<ConfigAttribute> list1 = new ArrayList<ConfigAttribute>();
		ConfigAttribute a = new SecurityConfig("abc");
		list1.add(a);
		resourceMap.put("key", list1);
		System.out.println(resourceMap);
		Collection<ConfigAttribute> list2 = new ArrayList<ConfigAttribute>();
		ConfigAttribute b = new SecurityConfig("bbb");
		list2.add(b);
		System.out.println("=== old:"+resourceMap.put("key", list2));
		
		System.out.println(resourceMap);
		for (ConfigAttribute configAttribute : resourceMap.get("key")) {
			System.out.println(configAttribute.getAttribute());
		}
		
	}
}

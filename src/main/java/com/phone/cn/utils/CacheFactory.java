package com.phone.cn.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * 缓存工厂，使用方法<br>
 * <code>CacheFactory instance = CacheFactory.init()</code> <br>
 * 项目名称：comet 类名称：CacheFactory 类描述： 创建人：吴灵 创建时间：2014-5-14 下午2:48:04 修改备注：
 * 
 * @version
 */
public class CacheFactory {

	private static Logger log = LoggerFactory.getLogger(CacheFactory.class);
	/**
	 * 防刷 缓存
	 */
	private static Cache preventSaveCache;
	/**
	 * SESSION总缓存
	 */
	private static Cache vipUserCache;

	/**
	 * 数据库数据总缓存
	 */
	private static Cache vipDataCache;

//	private static Cache cache1;

	/**
	 * 缓存管理器
	 */
	private static CacheManager cacheManager;

	/**
	 * 缓存工厂实例
	 */
	private static CacheFactory instance;

	public static Cache getVipUserCache() {
		return vipUserCache;
	}

	public static Cache getVipDataCache() {
		return vipDataCache;
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	static {
		log.info("\r\n初始化ehcache\r\n");
		init();
	}

	/**
	 * 根据默认的配置文件初始化CacheFactory
	 * 
	 * @return
	 */
	public static CacheFactory init() {
		log.error("CacheFactory init......");
		System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
		if (instance == null) {
			instance = new CacheFactory();
			cacheManager = CacheManager.create(CacheFactory.class
					.getClassLoader().getResourceAsStream("ehcache.xml"));
			_createCachees();

		}
		return instance;
	}

	/**
	 * 根据路径来初始化CacheFactory
	 *
	 * @param path
	 * @return
	 */
	public static CacheFactory init(String path) {
		log.error("CacheFactory init......");
		System.setProperty("net.sf.ehcache.enableShutdownHook", "true");
		if (instance == null) {
			instance = new CacheFactory();
			cacheManager = CacheManager.create(CacheFactory.class
					.getClassLoader().getResourceAsStream(path));

			_createCachees();
		}
		return instance;
	}

	private static void _createCachees() {
		// vipDataCache = cacheManager.getCache("vipDataCache");
		// vipUserCache = cacheManager.getCache("vipUserCache");
		// preventSaveCache = cacheManager.getCache("preventSaveCache");
		vipDataCache = cacheManager.getCache("day_cache_key");
	}

	public static void main(String[] args) {
//		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
//		ManagementService.registerMBeans(CacheFactory.getCacheManager(),
//				mBeanServer, true, true, true, true);
		 Cache cache = CacheFactory.getVipDataCache();
		 List<String> list = new ArrayList<String>();
		 list.add("1");
		 list.add("2");
		 list.add("3");
		 CacheFactory.put(cache, "william", list, 0, 0);
		 CacheFactory.put(cache, "jack", list, 3, 3);
		
		 String key = "test";
		 CacheFactory.put(cache, key, 123, 0, 0);
		 System.out.println(CacheFactory.get(cache, key));
		
		 CacheFactory.put(cache, key, 123456789, 0, 0);
		 System.out.println(CacheFactory.get(cache, key));
		 
		 System.out.println("jack"+CacheFactory.get(cache, "jack"));
		 try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("jack"+CacheFactory.get(cache, "jack"));
		 System.out.println("william"+CacheFactory.get(cache, "william"));
		 

//		Cache dataCache = CacheFactory.getVipDataCache();
//		System.out.println("getVipDataCache" + dataCache);
//		Cache cache = CacheFactory.getPreventSaveCache();
//		System.out.println("getPreventSaveCache()" + cache);

		cacheManager.shutdown();
	}

	public static Cache getPreventSaveCache() {
		return preventSaveCache;
	}

	/**
	 * 检查Element是否为空
	 * 
	 * @param e
	 * @return
	 */
	private static boolean isNull(Element e) {
		return e == null || e.getObjectValue() == null || e.getValue() == null;
	}

	/**
	 * 存入
	 * 
	 * @param <T>
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static <T extends Serializable> void put(Cache cache, String key,
			T value) {
		Element e = new Element(key, value);
		cache.put(e);
	}

	/**
	 * 存入 并设置元素是否永恒保存
	 * 
	 * @param <T>
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public static <T extends Serializable> void put(Cache cache, String key,
			T value, boolean eternal) {
		Element element = new Element(key, value);
		element.setEternal(eternal);
		cache.put(element);
	}

	/**
	 * 存入
	 * 
	 * @param <T>
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @param value
	 *            值
	 * @param timeToLiveSeconds
	 *            最大存活时间 ----- 时间单位为 秒
	 * @param timeToIdleSeconds
	 *            最大访问间隔时间 ------- 时间单位为 秒
	 */
	public static void put(Cache cache, String key, Object value,
			int timeToLiveSeconds, int timeToIdleSeconds) {
		Element element = new Element(key, value);
		element.setTimeToLive(timeToLiveSeconds);
		element.setTimeToIdle(timeToIdleSeconds);
		cache.put(element);
	}

	/**
	 * 根据缓存对象以及元素的键来取元素的值
	 *
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @return 值
	 */
	@Deprecated
	public static Element getCacheElement(Cache cache, String key) {
		return cache.get(key);
	}

	/**
	 * 根据缓存对象以及元素的键来取元素的值
	 *
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @return 元素的值，如果不存在，返回NULL
	 */
	public static Object get(Cache cache, String key) {
		Element e = cache.get(key);
		if (!isNull(e))
			return e.getObjectValue();
		return null;
	}

	/**
	 * 根据键删除指定元素
	 *
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 */
	public static Object remove(Cache cache, String key) {
		Object o = get(cache, key);
		cache.remove(key);
		return o;
	}

	/**
	 *
	 * @param cache
	 *            缓存库
	 * @param keys
	 *            键集合
	 */
	public static void removeAll(Cache cache) {
		Assert.notNull(cache, "指定缓存为NULL。请确认。。");
		cache.removeAll();
		// cache.removeAll(cache.getKeys());
	}

	/**
	 * 根据键删除指定元素
	 *
	 * @param cache
	 *            缓存库
	 * @param keys
	 *            键集合
	 */
	public static void removeAll(Cache cache, Collection<String> keys) {
		Assert.notNull(cache, "指定缓存为NULL。请确认。。");
		Assert.notNull(keys, "指定的键集合为NULL。请确认。。");
		for (String key : keys) {
			cache.remove(key);
		}
		// cache.removeAll(keys);
	}

	@SuppressWarnings("unchecked")
	public static void addToList(Cache cache, String key, Serializable value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			List<Serializable> list = Collections
					.synchronizedList(new LinkedList<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addAllToList(Cache cache, String key,
			Collection<? extends Serializable> value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			List<Serializable> list = Collections
					.synchronizedList(new LinkedList<Serializable>());
			list.addAll(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.addAll(value);
			log.debug(key + "--" + list);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addToHashSet(Cache cache, String key, Serializable value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			Set<Serializable> list = Collections
					.synchronizedSet(new HashSet<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addAllToHashSet(Cache cache, String key,
			Collection<? extends Serializable> value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			Set<Serializable> list = Collections
					.synchronizedSet(new HashSet<Serializable>());
			list.addAll(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();
			list.addAll(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addToArrayList(Cache cache, String key,
			Serializable value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			List<Serializable> list = Collections
					.synchronizedList(new ArrayList<Serializable>());
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.add(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void addAllToArrayList(Cache cache, String key,
			Collection<? extends Serializable> value) {
		Element e = cache.get(key);
		if (isNull(e)) {
			List<Serializable> list = Collections
					.synchronizedList(new ArrayList<Serializable>());
			list.addAll(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		} else {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			list.addAll(value);
			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
		}
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T popFromList(Cache cache,
			String key, Class<T> T) {
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();
			Iterator<Serializable> it = list.iterator();
			if (list.size() > 0) {
				Serializable obj = it.next();
				it.remove();
				e = new Element(key, list);
				e.setEternal(true);
				cache.put(e);
				return (T) obj;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> List<T> popFromList(Cache cache,
			String key, int count, Class<T> T) {
		Element e = cache.get(key);
		if (e != null) {
			List<Serializable> list = (List<Serializable>) e.getObjectValue();

			if (count < 1) {
				List<T> result = (List<T>) new ArrayList<Serializable>(list);
				list.clear();
				e = new Element(key, list);
				e.setEternal(true);
				cache.put(e);
				return result;
			}

			List<T> result = new ArrayList<T>(count);
			Iterator<Serializable> it = list.iterator();
			for (int i = 0; i < count && it.hasNext(); i++) {
				Serializable obj = it.next();
				it.remove();
				result.add((T) obj);
			}

			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
			return result;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> T popFromHashSet(Cache cache,
			String key, Class<T> T) {
		Element e = cache.get(key);
		if (e != null) {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();
			Iterator<Serializable> it = list.iterator();
			if (list.size() > 0) {
				Serializable obj = it.next();
				it.remove();
				e = new Element(key, list);
				e.setEternal(true);
				cache.put(e);
				return (T) obj;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Serializable> List<T> popFromHashSet(Cache cache,
			String key, int count, Class<T> T) {
		Element e = cache.get(key);
		if (e != null) {
			Set<Serializable> list = (Set<Serializable>) e.getObjectValue();

			if (count < 1) {
				List<T> result = (List<T>) new ArrayList<Serializable>(list);
				list.clear();
				e = new Element(key, list);
				e.setEternal(true);
				cache.put(e);
				return result;
			}

			List<T> result = new ArrayList<T>(count);
			Iterator<Serializable> it = list.iterator();
			for (int i = 0; i < count && it.hasNext(); i++) {
				Serializable obj = it.next();
				it.remove();
				result.add((T) obj);
			}

			e = new Element(key, list);
			e.setEternal(true);
			cache.put(e);
			return result;
		}
		return null;
	}

	/**
	 * 根据缓存对象以及元素的键来取元素的值，如果该元素不存在，则返回0 <br>
	 * 该元素为<strong>Collection或Collection</strong>的子类，如果不是<strong>
	 * Collection或Collection</strong>的子类，会抛异常
	 *
	 * @param cache
	 *            缓存库
	 * @param key
	 *            键
	 * @return 返回此 <strong>Collection或Collection</strong>的子类 中的元素数
	 */
	@SuppressWarnings("unchecked")
	public static int getCollectionSize(Cache cache, String key) {
		Element e = cache.get(key);
		if (e != null) {
			Collection<Serializable> list = (Collection<Serializable>) e
					.getObjectValue();
			return list.size();
		}
		return 0;
	}

	/**
	 * 获取当前缓存库的所有键值集合
	 *
	 * @param cache
	 *            缓存库
	 * @return 键值集合
	 */
	@SuppressWarnings("rawtypes")
	public static List getKeys(Cache cache) {
		return cache.getKeys();
	}

	/**
	 * 获取当前缓存库的所有已<code> 参数start </code>为前缀的键值集合
	 *
	 * @param cache
	 *            缓存库
	 * @param start
	 *            前缀
	 * @return 键值集合
	 */
	public static List<String> getKeys(Cache cache, String start) {
		List<?> list = cache.getKeys();
		List<String> result = new ArrayList<String>(list.size());
		for (Object obj : list) {
			if (obj != null && obj instanceof String) {
				String s = (String) obj;
				if (s.startsWith(start))
					result.add(s);
			}
		}
		return result;
	}

	/**
	 * 返回具体的方法全路径名称 参数
	 *
	 * @param targetName
	 *            全路径
	 * @param methodName
	 *            方法名称
	 * @param arguments
	 *            参数
	 * @return 完整方法名称
	 */
	public static String getCacheKey(String targetName, String methodName,
			Object[] arguments) {
		StringBuffer sb = new StringBuffer();
		sb.append(targetName).append(".").append(methodName);
		if ((arguments != null) && (arguments.length != 0)) {
			for (int i = 0; i < arguments.length; i++) {
				sb.append(".").append(arguments[i]);
			}
		}
		return sb.toString();
	}
}

/*******************************************************
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         	佛祖保佑       永无BUG  永不修改
         	佛祖镇楼                            BUG辟易  
                              佛曰:  
                                  写字楼里写字间，写字间里程序员；  
                                  程序人员写程序，又拿程序换酒钱。  
                                  酒醒只在网上坐，酒醉还来网下眠；  
                                  酒醉酒醒日复日，网上网下年复年。  
                                  但愿老死电脑间，不愿鞠躬老板前；  
                                  奔驰宝马贵者趣，公交自行程序员。  
                                  别人笑我忒疯癫，我笑自己命太贱；  
                                  不见满街漂亮妹，哪个归得程序员？  
************************************************************/
package com.phone.cn.utils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * @author long.jiang
 * @version  创建时间：2014-10-23 下午7:28:58   
 */
public class CreateBeanFactory{
	public static <T>T createBean(Class<T> clazz){ 
		
		Field[] fields = clazz.getDeclaredFields();
//		System.out.println(clazz.getFields().length);
		T o = null;
		try {
			o = clazz.newInstance();
			for (Field field : fields) {
//				System.out.println(field.getName());
//				System.out.println(field.getType().getSimpleName());
				String name = field.getName();
				Object value = _loadValueByType(field.getType(), field.getName());
				org.apache.commons.beanutils.BeanUtils.setProperty(o, name, value);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * @param type
	 * @return
	 */
	private static Object _loadValueByType( Class<?> type ,String name) {
		Random random = new Random();
		System.out.println(type.getSimpleName());
		String typeName = type.getSimpleName();
		if(StringUtils.equals(typeName, "Integer")){
			return  random.nextInt(10000);
		}else if(StringUtils.equals(typeName, "Boolean")){
			return random.nextInt(2) ==1;
		}else if(StringUtils.equals(typeName, "Date")){
			return new Date();
		}else{
			String str =name+"_"+random.nextInt(100);
			if(str.length() > 10){
				return str.subSequence(0, 10);
			}
			return  str;
		}
	}
	
	public static void main( String[] args ) {
//		System.out.println(createBean(CompInfo.class));
	}
}	

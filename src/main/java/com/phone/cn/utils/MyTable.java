package com.phone.cn.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 标记那张表
 * @author long.jiang
 * @version  创建时间：2015-1-19 下午5:42:47
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTable {

	/**
	 * 默认为数据库字段名与对象属性名一致
	 * 不一致时可以使用该方法来关联
	 * 
	 * @return
	 */
	String name() default "";

}

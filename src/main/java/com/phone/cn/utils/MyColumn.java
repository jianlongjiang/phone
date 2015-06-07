package com.phone.cn.utils;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字段注解，用于POJO字段上<br><br>
 * <b>注意：</b>在如下情况下使用该注解<br>
 * <b>对象属性名与数据库字段名不一致时</b><br>
 * <b>指定ID</b><br>
 * <b>对象属性关联的数据库字段有默认值</b>
 * @author 吴灵
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyColumn {

	/**
	 * 默认为数据库字段名与对象属性名一致
	 * 不一致时可以使用该方法来关联
	 * 
	 * @return
	 */
	String value() default "";
	
	/**
	 * 标识一个字段为ID
	 * 
	 * @return
	 */
	boolean isID() default false;

	/**
	 * 标识与该属性关联的数据库字段是否有默认值
	 * 
	 * @return
	 */
	boolean isAutoKey() default false;

}

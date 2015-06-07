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
package com.phone.cn.web.action;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * applicationContext-service.xml  中 配置<aop:aspectj-autoproxy/>  
* @ClassName: ControllerParamsLogAop
* @Description: ( spring  aop  Aspect  ,   该类是sprngMvc controller  查看是什么类和方法,  还有其参数)
* @author jiangjianlong-蒋建龙
* @date 2015年5月17日 下午3:52:32
*
 */
//@Component
//@Aspect
public class ControllerParamsLogAop{
	public ControllerParamsLogAop() {
		System.out.println("==========>  com.phone.cn.web.action.ControllerParamsLogAop");
	}
	
	
	private   Logger	log	= LoggerFactory.getLogger(this.getClass());
//	@Around("execution(* com.sky.kf.vip..*.*Controller.*(..))") ======> old
//	com.phone.cn.action
//	@Before("execution(* com..*.action.*.*(..))")
//	@Before("execution(* com.phone.cn.action..*.*(..))")
	@Before("execution(* com.phone.cn.action..*.*(..))")
//									  com.phone.cn.action.product.CateInfoAction
//	@Before("execution(* *.*(..))")
	public void doBefore( JoinPoint jp ) {
		StringBuffer method = new StringBuffer();
		method.append(jp.getTarget().getClass().getName()).append(".").append(jp.getSignature().getName());
		Object[] args = jp.getArgs();

		log.info(" \n======================> method: {}" + method);
		for (Object obj : args) {
//			if(obj instanceof String) {
//				
//			} else if (obj instanceof Number) {
//				
//			} else if ()
//			ReflectionUtils.obtainFieldsName(object.getClass());
//			if(object.getClass().getDeclaredFields())
//			try {
//				JSON josnObject = JSONObject.fromObject(obj);
//				log.info("====================== params:{}", josnObject);
//			} catch (Exception e) {
//				log.info("====================== params:{}", obj);
//			}
		}

	}
//	@Around("execution(* com.sky.kf.vip..*.*Controller.*(..))")
	@Around("execution(* com..*.action.*.*(..))")
	public Object doAfter( ProceedingJoinPoint jp ) throws Throwable {
		return jp.proceed();
//		JSON josnObject = JSONObject.fromObject(obj);
//		log.info("=================>"+josnObject);
//		String str = MmochatFilterService.filterString(josnObject.toString());
//		
//		return str;
	}
	

	/*
	 * @After("execution(* com.sky.kf.vip..*.*Controller.*(..))") public void
	 * doAfter(JoinPoint jp) { Object[] args = jp.getArgs(); StringBuffer method
	 * = new StringBuffer();
	 * method.append(jp.getTarget().getClass().getName()).append
	 * (".").append(jp.getSignature().getName());
	 * 
	 * jp.getArgs(); jp.getKind(); jp.getSignature(); jp.getSourceLocation();
	 * jp.getStaticPart(); jp.getTarget();
	 * 
	 * }
	 */
}

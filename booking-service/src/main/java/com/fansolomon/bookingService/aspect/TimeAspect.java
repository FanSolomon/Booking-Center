/**
 * 
 */
package com.fansolomon.bookingService.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Colin
 *
 */
@Aspect
@Component
public class TimeAspect {

	//pjp对象包含当前拦截方法的信息
	@Around("execution(* com.fansolomon.bookingService.controller.HelloController.*(..))")
	private Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		long start = new Date().getTime();
		System.out.println("time aspect start!");
		//获取方法参数的数组
		Object[] args = pjp.getArgs();
		for(Object arg : args) {
			System.out.println("arg is:"+arg);
		}
		//获取返回值
		Object object = pjp.proceed();
		System.out.println("pjp.proceed() is:"+ object);
		System.out.println("time aspect耗时:" + (new Date().getTime() - start));
		System.out.println("time aspect end!");
		
		return null;
	}
}

package com.fansolomon.oauthServer.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Colin
 * @since 2020-07-11
 */
@Slf4j
@Aspect
@Component
public class RequestAspect {

	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void requestUrl() {
	}

	/**
	 * 切面表达式：
	 * execution 代表所要执行的表达式主体
	 * 第一处 * 代表方法返回类型 *代表所有类型
	 * 第二处 包名代表AOP监控的类所在的包
	 * 第三处 ..代表该包及其子包下的所有方法
	 * 第四处 * 代表类名
	 * 第五处 * 代表类中的方法名 （）指每个方法的参数 ..代表方法中的任何参数
	 */
	//pjp对象包含当前拦截方法的信息
	@Around("requestUrl()")
	private Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		long start = new Date().getTime();
		log.info("-----controller aspect start!-----");
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		log.info("request method:{}, uri is:{}", request.getMethod(), request.getRequestURI());
		log.info("request remote addr is:{}", request.getRemoteAddr());
		log.info("request class.method is:{}", pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());
		log.info("request args is:{}", Arrays.toString(pjp.getArgs()));

		//获取返回值
		Object object = pjp.proceed();
		log.info("response is:{}", object);
		log.info("-----controller aspect 耗时:{}ms-----", (new Date().getTime() - start));
		return object;
	}
}

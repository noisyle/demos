package com.noisyle.demo.annotation;

import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAnnotationAOP {
	private static final Logger logger = LoggerFactory.getLogger(MyAnnotationAOP.class);
	
	@Pointcut("@annotation(com.noisyle.demo.annotation.MyAnnotation)")
	public void myPointcut() {
	}

	@Before("myPointcut()")
	public void before(JoinPoint joinPoint) {
		logger.info("before");
	}

	@Around("myPointcut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("around - before proceed");
		MethodSignature ms = (MethodSignature) joinPoint.getSignature();
		Method method = ms.getMethod();
		MyAnnotation audit = method.getAnnotation(MyAnnotation.class);
		
		Object[] args = joinPoint.getArgs();
		for(Object o : args){
			logger.info("args:{}[{}:{}]", o.getClass().getName(), audit.pk(), PropertyUtils.getProperty(o, audit.pk()));
		}
		Object ret = joinPoint.proceed();
		logger.info("around - after proceed");
		return ret;
	}

	@AfterReturning("myPointcut()")
	public void afterReturning(JoinPoint joinPoint) {
		logger.info("afterReturning");
	}

	@AfterThrowing("myPointcut()")
	public void afterThrowing(JoinPoint joinPoint) {
		logger.info("afterThrowing");
	}

	@After("myPointcut()")
	public void after(JoinPoint joinPoint) {
		logger.info("after");
	}
}

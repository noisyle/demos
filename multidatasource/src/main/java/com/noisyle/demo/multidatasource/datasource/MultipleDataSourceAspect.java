package com.noisyle.demo.multidatasource.datasource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.noisyle.demo.multidatasource.annotation.DataSource;

@Aspect
@Component
public class MultipleDataSourceAspect {
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class);

//    @Pointcut("@within(com.noisyle.demo.multidatasource.annotation.DataSource)")
    @Pointcut("execution(* com.noisyle.demo.multidatasource.repository..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        logger.debug("Before pointcut");
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        logger.debug("className: {}, methodName: {}", className.toString(), methodName);
        Class<?>[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSourceType = null;
        try {
            Method method = className.getMethod(methodName, argClass);
            Class<?>[] interfaces = className.getInterfaces();
            if (method.isAnnotationPresent(DataSource.class)) {
                // 方法上加注解
                DataSource annotation = method.getAnnotation(DataSource.class);
                dataSourceType = annotation.value();
            } else if (className.isAnnotationPresent(DataSource.class)) {
                // 类上加注解
                DataSource annotation = className.getAnnotation(DataSource.class);
                dataSourceType = annotation.value();
            } else if (className.isAnnotationPresent(DataSource.class)) {
                // 父类方法上加注解
            }
        } catch (Exception e) {
            logger.error("Error occured", e);
        }
        // 切换数据源
        MultipleDataSourceHolder.setDataSourceType(dataSourceType);
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        logger.debug("After pointcut");
        MultipleDataSourceHolder.clearDataSourceType();
    }
}

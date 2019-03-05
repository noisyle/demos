package com.noisyle.demo.multidatasource.datasource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.demo.multidatasource.annotation.DataSource;

@Aspect
public class MultipleDataSourceAspect {
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class);

    @Pointcut("@annotation(com.noisyle.demo.multidatasource.annotation.DataSource)")
//    @Pointcut("execution(* com.noisyle.demo.multidatasource.repository..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        logger.info("Before pointcut");
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class<?>[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSourceType = null;
        try {
            java.lang.reflect.Method method = className.getMethod(methodName, argClass);
            if (method.isAnnotationPresent(DataSource.class)) {
                DataSource annotation = method.getAnnotation(DataSource.class);
                dataSourceType = annotation.value();
            }
        } catch (Exception e) {
            logger.error("Error occured", e);
        }
        // 切换数据源
        MultipleDataSourceHolder.setDataSourceType(dataSourceType);
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        logger.info("After pointcut");
        MultipleDataSourceHolder.clearDataSourceType();
    }
}

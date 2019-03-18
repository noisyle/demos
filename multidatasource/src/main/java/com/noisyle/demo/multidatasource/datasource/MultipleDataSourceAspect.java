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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.noisyle.demo.multidatasource.annotation.DataSource;

@Aspect
@Component
public class MultipleDataSourceAspect {
    final static private Logger logger = LoggerFactory.getLogger(MultipleDataSourceAspect.class);
    
    @Pointcut("execution(* com.noisyle.demo.multidatasource.repository..*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(JoinPoint point) {
        logger.debug("Before pointcut");
        Class<?> className = point.getTarget().getClass();
        String methodName = point.getSignature().getName();
        Class<?>[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSourceType = null;
        DataSource annotation = null;
        try {
            Method method = className.getMethod(methodName, argClass);
            annotation = AnnotationUtils.findAnnotation(method, DataSource.class);
            logger.debug("Annotation of method: {}", annotation);
            if(annotation!=null) {
                dataSourceType = annotation.value();
                MultipleDataSourceHolder.setDataSourceType(dataSourceType);
                return;
            }
            annotation = AnnotationUtils.findAnnotation(className, DataSource.class);
            logger.debug("Annotation of class: {}", annotation);
            if(annotation!=null) {
                dataSourceType = annotation.value();
                MultipleDataSourceHolder.setDataSourceType(dataSourceType);
                return;
            }
        } catch (Exception e) {
            logger.error("Error occured", e);
        }
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        logger.debug("After pointcut");
        MultipleDataSourceHolder.clearDataSourceType();
    }
}

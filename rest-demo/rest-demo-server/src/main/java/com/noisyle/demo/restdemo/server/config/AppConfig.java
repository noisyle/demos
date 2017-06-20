package com.noisyle.demo.restdemo.server.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:spring-context.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.noisyle.demo.restdemo.server.repository", "com.noisyle.demo.restdemo.server.service" })
public class AppConfig {

}

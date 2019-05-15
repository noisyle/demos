package com.noisyle.demo.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.noisyle.demo.multidatasource.repository.DB1UserRepository;
import com.noisyle.demo.multidatasource.repository.DB2UserRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        ctx.getBean(DB1UserRepository.class).findAll();
        ctx.getBean(DB2UserRepository.class).findAll();
    }
}

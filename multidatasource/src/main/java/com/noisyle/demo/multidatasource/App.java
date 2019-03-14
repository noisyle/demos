package com.noisyle.demo.multidatasource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.noisyle.demo.multidatasource.repository.UserRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(App.class, args);
        ctx.getBean(UserRepository.class).getDb1List();
        ctx.getBean(UserRepository.class).getDb2List();
    }
}

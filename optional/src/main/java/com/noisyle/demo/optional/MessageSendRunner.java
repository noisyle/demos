package com.noisyle.demo.optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noisyle.demo.optional.bean.Role;
import com.noisyle.demo.optional.bean.User;
import com.noisyle.demo.optional.service.UserService;

@Component
public class MessageSendRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        String role1 = userService.findById("1")
                .flatMap(User::getRole)
                .map(Role::getRolename)
                .orElse("GUEST");
        logger.info("role of user1 is {}", role1);
        
        String role2 = userService.findById("2")
                .flatMap(User::getRole)
                .map(Role::getRolename)
                .orElse("GUEST");
        logger.info("role of user1 is {}", role2);
    }

}
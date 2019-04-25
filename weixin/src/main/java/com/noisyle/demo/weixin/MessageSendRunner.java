package com.noisyle.demo.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MessageSendRunner implements CommandLineRunner {

    @Autowired
    private WxTemplateService wxTemplateService;

    @Override
    public void run(String... args) throws Exception {
        wxTemplateService.sendTemplateMessage();
    }

}
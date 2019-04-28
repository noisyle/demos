package com.noisyle.demo.weixin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MessageSendRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWxTemplateService wxTemplateService;

    @Override
    public void run(String... args) throws Exception {
        WxTemplateMessage param = WxTemplateMessage.builder()
            .openId("oMd100jVZh9BiuKeh3QpfOga2Axo")
            .templateId("RQp6kn1yvxbMIg1kyzQn4KziB71tCdzuQAy8ygiZReU")
            .forward(WxTemplateMessage.Forward.URL)
            .url("http://www.baidu.com")
            .data("first", "今天有客户过生日哦，祝福一下他（她）吧", "#000000")
            .data("keyword1", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "#000000")
            .data("keyword2", "客户 黑卫士 于今天过生日", "#000000")
            .data("remark","客户关系维护，一句简单的'生日快乐'开始吧。点击查看详情", "#000000")
            .build();
        
        logger.debug("发送参数：{}", param);
        
        wxTemplateService.sendTemplateMessage(param);
    }

}
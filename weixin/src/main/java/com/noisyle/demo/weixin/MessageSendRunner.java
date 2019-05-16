package com.noisyle.demo.weixin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.noisyle.demo.weixin.api.IWxTemplateService;
import com.noisyle.demo.weixin.bean.WxTemplateMessage;

@Component
public class MessageSendRunner implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IWxTemplateService wxTemplateService;

    @Override
    public void run(String... args) throws Exception {
        WxTemplateMessage param = WxTemplateMessage.builder()
            .openId("oMd100v3SSwRIS0wPt7HAsijXp9w")
            .templateId("RQp6kn1yvxbMIg1kyzQn4KziB71tCdzuQAy8ygiZReU")
            .miniProgramId("wx1dbb807009381bc6")
            .miniProgramPage("team/pages/msgList/msgList")
            .url("http://www.baidu.com")
            .data("first", "提醒正文", "#000000")
            .data("keyword1", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "#000000")
            .data("keyword2", "提醒内容正文", "#000000")
            .data("remark","提醒备注", "#000000")
            .build();
        
        logger.debug("发送参数：{}", param);
        
        wxTemplateService.sendTemplateMessage(param);
    }

}
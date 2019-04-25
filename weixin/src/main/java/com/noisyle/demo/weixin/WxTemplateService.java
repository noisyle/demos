package com.noisyle.demo.weixin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

@Service
public class WxTemplateService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxMpService wxMpService;

    public void sendTemplateMessage() {
        try {
            logger.debug("Callback IPs: {}", (Object[]) wxMpService.getCallbackIP());
            logger.debug("Access Token: {}", wxMpService.getAccessToken());
            
            String openid = "oeWIJ44WO3p1Q3UYy2TQpdAyUwKA";
            String templateId = "RQp6kn1yvxbMIg1kyzQn4KziB71tCdzuQAy8ygiZReU";
            String url = "http://wx14.chinanx.com/crmWeb/birthday/birthday.html?empId=4cb5ff4d3c4748a5b391c6d1e1050d3e";
            
            //发送微信消息
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setToUser(openid);
            templateMessage.setTemplateId(templateId);
            templateMessage.setUrl(url);
            
            //模板内容
            templateMessage.addData(new WxMpTemplateData("first", "今天有客户过生日哦，祝福一下他（她）吧", "#000000"));
            templateMessage.addData(new WxMpTemplateData("keyword1", new SimpleDateFormat("yyyy-MM-dd").format(new Date()), "#000000"));
            templateMessage.addData(new WxMpTemplateData("keyword2", "客户 李强 于今天过生日", "#000000"));
            templateMessage.addData(new WxMpTemplateData("remark","客户关系维护，一句简单的'生日快乐'开始吧。点击查看详情", "#000000"));
            
            //调用班纳瑞封装的方法
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            
        } catch (WxErrorException e) {
            logger.error("Send Template Message Error.", e);
        }
    }
}

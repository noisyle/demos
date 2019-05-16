package com.noisyle.demo.weixin.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noisyle.demo.weixin.api.IWxTemplateService;
import com.noisyle.demo.weixin.bean.WxTemplateMessage;
import com.noisyle.demo.weixin.bean.WxTemplateMessage.WxTemplateMessageData;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage.MiniProgram;

@Service
public class WxTemplateServiceImpl implements IWxTemplateService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private WxMpService wxMpService;

    /**
     * 发送模板消息
     * 
     * @param param
     *            请求参数 {@link WxTemplateMessage}
     */
    @Override
    public void sendTemplateMessage(WxTemplateMessage param) {
        try {
            logger.debug("Access Token: {}", wxMpService.getAccessToken());

            // 发送微信消息
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setToUser(param.getOpenId());
            templateMessage.setTemplateId(param.getTemplateId());

            // 跳转小程序
            MiniProgram miniProgram = new MiniProgram();
            miniProgram.setAppid(param.getMiniProgramId());
            miniProgram.setPagePath(param.getMiniProgramPage());
            templateMessage.setMiniProgram(miniProgram);
            
            // 跳转页面，如果与跳转小程序同时设置，则优先跳转小程序，不支持时才跳转页面
            templateMessage.setUrl(param.getUrl());

            // 模板内容
            for (WxTemplateMessageData data : param.getData()) {
                templateMessage.addData(new WxMpTemplateData(data.getName(), data.getValue(), data.getColor()));
            }

            // 调用sdk发送方法
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);

        } catch (WxErrorException e) {
            logger.error("Send Template Message Error.", e);
        }
    }
}

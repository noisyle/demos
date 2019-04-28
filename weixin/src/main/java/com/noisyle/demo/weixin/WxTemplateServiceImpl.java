package com.noisyle.demo.weixin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.noisyle.demo.weixin.WxTemplateMessage.Forward;
import com.noisyle.demo.weixin.WxTemplateMessage.WxTemplateMessageData;

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
     *            请求参数
     */
    @Override
    public void sendTemplateMessage(WxTemplateMessage param) {
        try {
            logger.debug("Access Token: {}", wxMpService.getAccessToken());

            // 发送微信消息
            WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
            templateMessage.setToUser(param.getOpenId());
            templateMessage.setTemplateId(param.getTemplateId());

            Forward forward = param.getForward();
            switch (forward) {
            case URL:
                // 跳转页面方式
                templateMessage.setUrl(param.getUrl());
                break;
            case MINI_PROGRAM:
                // 跳转小程序方式
                MiniProgram miniProgram = new MiniProgram();
                miniProgram.setAppid(param.getMiniProgramId());
                miniProgram.setPagePath(param.getMiniProgramPage());
                templateMessage.setMiniProgram(miniProgram);
                break;
            default:
                break;
            }

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

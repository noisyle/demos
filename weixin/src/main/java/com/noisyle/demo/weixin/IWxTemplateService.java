package com.noisyle.demo.weixin;

public interface IWxTemplateService {

    /**
     * 发送模板消息
     * 
     * @param param
     *            请求参数
     */
    public void sendTemplateMessage(WxTemplateMessage param);
}

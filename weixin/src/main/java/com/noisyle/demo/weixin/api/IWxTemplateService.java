package com.noisyle.demo.weixin.api;

import com.noisyle.demo.weixin.bean.WxTemplateMessage;

public interface IWxTemplateService {

    /**
     * 发送模板消息
     * 
     * @param param
     *            请求参数
     */
    public void sendTemplateMessage(WxTemplateMessage param);
}

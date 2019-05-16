package com.noisyle.demo.weixin.bean;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * 模板消息对象
 */
public class WxTemplateMessage implements Serializable {
    private static final long serialVersionUID = -9067478715802736665L;

    /**
     * 公众号关注用户openId
     */
    private String openId;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 跳转url地址
     */
    private String url;
    /**
     * 跳转小程序id
     */
    private String miniProgramId;
    /**
     * 跳转小程序页面
     */
    private String miniProgramPage;
    /**
     * 模板数据
     */
    private List<WxTemplateMessageData> data = new LinkedList<WxTemplateMessageData>();

    public String getOpenId() {
        return openId;
    }

    /**
     * 设置公众号关注用户openId
     * @param openId 公众号关注用户openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }
    public String getTemplateId() {
        return templateId;
    }


    /**
     * 设置模板id
     * @param templateId 模板id
     */
    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getUrl() {
        return url;
    }

    /**
     * 设置跳转url
     * @param url 跳转url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniProgramId() {
        return miniProgramId;
    }

    /**
     * 设置跳转小程序id
     * @param miniProgramId 跳转小程序id
     */
    public void setMiniProgramId(String miniProgramId) {
        this.miniProgramId = miniProgramId;
    }

    public String getMiniProgramPage() {
        return miniProgramPage;
    }

    /**
     * 设置跳转小程序页面
     * @param miniProgramPage 跳转小程序页面
     */
    public void setMiniProgramPage(String miniProgramPage) {
        this.miniProgramPage = miniProgramPage;
    }

    public List<WxTemplateMessageData> getData() {
        return data;
    }

    /**
     * 设置模板数据
     * @param data 模板数据列表
     */
    public void setData(List<WxTemplateMessageData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemplateMessageParameter [openId=" + openId + ", templateId=" + templateId
                + ", url=" + url + ", miniProgramId=" + miniProgramId + ", miniProgramPage=" + miniProgramPage
                + ", data=" + data + "]";
    }

    public static class WxTemplateMessageData implements Serializable {
        private static final long serialVersionUID = -4991038401301809995L;
        private String name;
        private String value;
        private String color;

        public WxTemplateMessageData() {
        }

        public WxTemplateMessageData(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public WxTemplateMessageData(String name, String value, String color) {
            this.name = name;
            this.value = value;
            this.color = color;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "WxTemplateMessageData [name=" + name + ", value=" + value + ", color=" + color + "]";
        }

    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WxTemplateMessage param = new WxTemplateMessage();
        
        /**
         * 设置公众号关注用户openId
         * @param openId 公众号关注用户openId
         */
        public Builder openId(String openId) {
            param.openId = openId;
            return this;
        }

        /**
         * 设置模板id
         * @param templateId 模板id
         */
        public Builder templateId(String templateId) {
            param.templateId = templateId;
            return this;
        }

        /**
         * 设置跳转url，如果与跳转小程序同时设置，则优先跳转小程序，不支持时才跳转url
         * @param url 跳转url
         */
        public Builder url(String url) {
            param.url = url;
            return this;
        }

        /**
         * 设置跳转小程序id
         * @param miniProgramId 跳转小程序id
         */
        public Builder miniProgramId(String miniProgramId) {
            param.miniProgramId = miniProgramId;
            return this;
        }

        /**
         * 设置跳转小程序页面
         * @param miniProgramPage 跳转小程序页面
         */
        public Builder miniProgramPage(String miniProgramPage) {
            param.miniProgramPage = miniProgramPage;
            return this;
        }

        /**
         * 添加模板数据
         * @param name 占位符名称
         * @param value 字符串
         * @return
         */
        public Builder data(String name, String value) {
            param.data.add(new WxTemplateMessageData(name, value));
            return this;
        }

        /**
         * 添加模板数据
         * @param name 占位符名称
         * @param value 字符串
         * @param color 文字颜色，如#000000
         * @return
         */
        public Builder data(String name, String value, String color) {
            param.data.add(new WxTemplateMessageData(name, value, color));
            return this;
        }

        /**
         * 构造{@link WxTemplateMessage}模板消息对象
         * @return 模板消息对象
         */
        public WxTemplateMessage build() {
            return param;
        }
    }
}

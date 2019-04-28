package com.noisyle.demo.weixin;

import java.util.LinkedList;
import java.util.List;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;

public class WxTemplateMessage {
    public static enum Forward {
        URL, MINI_PROGRAM;
    }

    private String openId;
    private String templateId;
    private Forward forward;
    private String url;
    private String miniProgramId;
    private String miniProgramPage;
    private List<WxMpTemplateData> data = new LinkedList<WxMpTemplateData>();

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public Forward getForward() {
        return forward;
    }

    public void setForward(Forward forward) {
        this.forward = forward;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMiniProgramId() {
        return miniProgramId;
    }

    public void setMiniProgramId(String miniProgramId) {
        this.miniProgramId = miniProgramId;
    }

    public String getMiniProgramPage() {
        return miniProgramPage;
    }

    public void setMiniProgramPage(String miniProgramPage) {
        this.miniProgramPage = miniProgramPage;
    }

    public List<WxMpTemplateData> getData() {
        return data;
    }

    public void setData(List<WxMpTemplateData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemplateMessageParameter [openId=" + openId + ", templateId=" + templateId + ", forward=" + forward
                + ", url=" + url + ", miniProgramId=" + miniProgramId + ", miniProgramPage=" + miniProgramPage
                + ", data=" + data + "]";
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private WxTemplateMessage param = new WxTemplateMessage();

        public Builder openId(String openId) {
            param.openId = openId;
            return this;
        }

        public Builder templateId(String templateId) {
            param.templateId = templateId;
            return this;
        }

        public Builder forward(Forward forward) {
            param.forward = forward;
            return this;
        }

        public Builder url(String url) {
            param.url = url;
            return this;
        }

        public Builder miniProgramId(String miniProgramId) {
            param.miniProgramId = miniProgramId;
            return this;
        }

        public Builder miniProgramPage(String miniProgramPage) {
            param.miniProgramPage = miniProgramPage;
            return this;
        }

        public Builder data(String name, String value) {
            param.data.add(new WxMpTemplateData(name, value));
            return this;
        }

        public Builder data(String name, String value, String color) {
            param.data.add(new WxMpTemplateData(name, value, color));
            return this;
        }

        public WxTemplateMessage build() {
            return param;
        }
    }
}

package com.noisyle.demo.weixin;

import java.util.LinkedList;
import java.util.List;

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
    private List<WxTemplateMessageData> data = new LinkedList<WxTemplateMessageData>();

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

    public List<WxTemplateMessageData> getData() {
        return data;
    }

    public void setData(List<WxTemplateMessageData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "TemplateMessageParameter [openId=" + openId + ", templateId=" + templateId + ", forward=" + forward
                + ", url=" + url + ", miniProgramId=" + miniProgramId + ", miniProgramPage=" + miniProgramPage
                + ", data=" + data + "]";
    }

    public static class WxTemplateMessageData {
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
            param.data.add(new WxTemplateMessageData(name, value));
            return this;
        }

        public Builder data(String name, String value, String color) {
            param.data.add(new WxTemplateMessageData(name, value, color));
            return this;
        }

        public WxTemplateMessage build() {
            return param;
        }
    }
}

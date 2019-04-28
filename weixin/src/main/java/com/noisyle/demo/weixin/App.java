package com.noisyle.demo.weixin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.StringUtils;

import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

@SpringBootApplication
public class App {
    @Value("${weixin.appId}")
    private String appId;
    @Value("${weixin.appSecret}")
    private String appSecret;
    @Value("${weixin.token}")
    private String token;
    @Value("${weixin.encodingAESKey}")
    private String encodingAESKey;
    @Value("${weixin.accessToken:null}")
    private String accessToken;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public WxMpInMemoryConfigStorage wxMpInMemoryConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(appId);
        wxMpConfigStorage.setSecret(appSecret);
        wxMpConfigStorage.setToken(token);
        wxMpConfigStorage.setAesKey(encodingAESKey);
        if (!StringUtils.isEmpty(accessToken)) {
            wxMpConfigStorage.updateAccessToken(accessToken, 60 * 60);
        }
        return wxMpConfigStorage;
    }

    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpInMemoryConfigStorage());
        return wxMpService;
    }
}
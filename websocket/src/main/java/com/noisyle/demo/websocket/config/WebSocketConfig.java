package com.noisyle.demo.websocket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 仅当客户端不使用SockJS时(如微信小程序)，添加setTaskScheduler和setHeartbeatValue设置，由stomp协商heartbeat。
        // 否则可以不设置，由SockJS自行协商heartbeat。
        // https://stackoverflow.com/a/42308169
        ThreadPoolTaskScheduler ts = new ThreadPoolTaskScheduler();
        ts.setThreadNamePrefix("wss-heartbeat-thread-");
        ts.initialize();

        config.enableSimpleBroker("/topic").setTaskScheduler(ts).setHeartbeatValue(new long[] { 10000, 10000 });
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-endpoint").setAllowedOrigins("*").withSockJS();
    }

}

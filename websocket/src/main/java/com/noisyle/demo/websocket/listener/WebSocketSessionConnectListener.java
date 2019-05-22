package com.noisyle.demo.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;

@Component
public class WebSocketSessionConnectListener implements ApplicationListener<SessionConnectEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        logger.debug("创建WebSocket会话: {}", event);
    }

}

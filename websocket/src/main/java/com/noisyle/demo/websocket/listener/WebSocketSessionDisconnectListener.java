package com.noisyle.demo.websocket.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketSessionDisconnectListener implements ApplicationListener<SessionDisconnectEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        logger.debug("断开WebSocket会话: {}", event);
    }

}

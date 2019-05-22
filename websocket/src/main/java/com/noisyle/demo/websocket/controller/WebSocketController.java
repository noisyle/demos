package com.noisyle.demo.websocket.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.noisyle.demo.websocket.vo.Greeting;
import com.noisyle.demo.websocket.vo.HelloMessage;

@Controller
public class WebSocketController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        return new Greeting(message.getName(), new Date().getTime());
    }

    @Scheduled(fixedDelay = 1000)
    public void updateLineChart() {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("data", new Random().nextInt(500) + 800);
        logger.debug("推送消息： {}", res);
        template.convertAndSend("/topic/linechart", res);
    }
}

package com.noisyle.demo.websocket;

import java.util.Date;

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

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) {
        return new Greeting(message.getName(), new Date().getTime());
    }

    @Scheduled(fixedDelay = 1000)
    public void publishUpdates() {
        template.convertAndSend("/topic/greetings", new Greeting("Server", new Date().getTime()));
    }
}

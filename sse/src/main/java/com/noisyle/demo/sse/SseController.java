package com.noisyle.demo.sse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SseController {
    private static final Logger logger = LoggerFactory.getLogger(SseController.class);
    private Map<String, SseEmitter> sseMap = new HashMap<String, SseEmitter>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @GetMapping("")
    public SseEmitter sse(HttpSession session) {
        SseEmitter emitter = new SseEmitter(0L);
        sseMap.put(session.getId(), emitter);
        return emitter;
    }

    @Scheduled(fixedRate = 1000)
    private void demo() {
        String ts = sdf.format(new Date());
        for(SseEmitter emitter : sseMap.values()) {
            try {
                emitter.send(ts);
            } catch (IOException e) {
                logger.error("sent message error", e);
            }
        }
    }
}

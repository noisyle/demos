package com.noisyle.demo.websocket.vo;


public class Greeting {

    private String content;
    private long time;

    public Greeting() {
    }

    public Greeting(String content, long time) {
        this.content = content;
        this.time = time;
    }
    
    public String getContent() {
    	return content;
    }
    
    public long getTime() {
    	return time;
    }

}

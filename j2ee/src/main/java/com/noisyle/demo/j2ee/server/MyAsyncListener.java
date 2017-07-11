package com.noisyle.demo.j2ee.server;

import java.io.IOException;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

public class MyAsyncListener implements AsyncListener {

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        event.getSuppliedResponse().getWriter().write("[Listener] Complete!<br />");
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        event.getSuppliedResponse().getWriter().write("[Listener] Timeout!<br />");
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        event.getSuppliedResponse().getWriter().write("[Listener] Error!<br />");
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        event.getSuppliedResponse().getWriter().write("[Listener] Start!<br />");
    }
	
}

package com.noisyle.demo.jws.server;

import javax.xml.ws.Endpoint;

import org.junit.Test;

public class DemoServiceTest {
	@Test
	public void testDemoServiceImpl() {
		Endpoint.publish("http://localhost:8080/jws/demo", new DemoServiceImpl());
		for(;;){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}
}

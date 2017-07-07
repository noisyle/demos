package com.noisyle.demo.jws.server;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebService(endpointInterface = "com.noisyle.demo.jws.server.DemoService")
@HandlerChain(file="handler-chain.xml")
public class DemoServiceImpl implements DemoService {
	private static final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Override
	public String sayHello(String name) {
		logger.debug("name: {}", name);
		return "Hello " + name + "!";
	}
}

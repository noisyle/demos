package com.noisyle.demo.jws.server;

import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.demo.jws.vo.HelloRequest;
import com.noisyle.demo.jws.vo.HelloResponse;

@WebService(endpointInterface = "com.noisyle.demo.jws.server.Hello")
@HandlerChain(file="handler-chain.xml")
public class HelloImpl implements Hello {
	private static final Logger logger = LoggerFactory.getLogger(HelloImpl.class);

	@Override
	public HelloResponse sayHello(HelloRequest request) {
		String name = request.getUser().getName();
		logger.debug("name: {}", name);
		HelloResponse response = new HelloResponse();
		HelloResponse.Result result = new HelloResponse.Result();
		result.setMessage("Hello " + name + "!");
		response.setResult(result);
		return response;
	}
	
	public static void main(String[] args) {
		logger.debug("服务启动");
		Endpoint.publish("http://localhost:8080/jws/hello", new HelloImpl());
	}
}

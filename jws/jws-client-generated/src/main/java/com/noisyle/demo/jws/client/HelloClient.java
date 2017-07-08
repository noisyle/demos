package com.noisyle.demo.jws.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.demo.jws.server.Hello;
import com.noisyle.demo.jws.server.HelloImplService;
import com.noisyle.demo.jws.server.HelloRequest;
import com.noisyle.demo.jws.server.HelloResponse;
import com.noisyle.demo.jws.server.User;

/**
 *  调用自动生成客户端的例子。
 *  客户端代码用以下命令自动生成：
 *  wsimport -keep -encoding UTF-8 http://localhost:8080/jws/hello?wsdl
 */
public class HelloClient {
	private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);
	
	public String sayHello() {
		String msg = null;
		try {
			HelloRequest request = new HelloRequest();
			User user = new User();
			user.setName("world");
			request.setUser(user);
			HelloImplService service = new HelloImplService();
			Hello hello = service.getHelloImplPort();
			HelloResponse response = hello.sayHello(request);
			logger.debug("message: {}", response.getResult().getMessage());
			msg = response.getResult().getMessage();
		} catch (Exception e) {
			logger.error("error", e);
		}
		return msg;
	}
	
	public static void main(String[] args) {
		System.out.println(new HelloClient().sayHello());
	}
}

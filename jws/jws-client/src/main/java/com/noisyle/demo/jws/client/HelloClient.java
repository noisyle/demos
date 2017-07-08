package com.noisyle.demo.jws.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.demo.jws.server.Hello;
import com.noisyle.demo.jws.vo.HelloRequest;
import com.noisyle.demo.jws.vo.HelloResponse;

/**
 *	手工编写的客户端例子。
 *	也可以用wsimport -keep http://localhost:8080/jws/hello?wsdl自动生成客户端代码。
 */
public class HelloClient {
	private static final Logger logger = LoggerFactory.getLogger(HelloClient.class);
	
	public String sayHello() {
		String msg = null;
		try {
			URL url = new URL("http://localhost:8080/jws/hello?wsdl");
			
			// 第一个参数对应wsdl中的targetNamespace
			// 第二个参数对应wsdl中的name
			QName qname = new QName("http://server.jws.demo.noisyle.com/", "HelloImplService");
			Service service = Service.create(url, qname);
			HelloRequest request = new HelloRequest();
			HelloRequest.User user = new HelloRequest.User();
			user.setName("world");
			request.setUser(user);
			Hello hello = service.getPort(Hello.class);
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

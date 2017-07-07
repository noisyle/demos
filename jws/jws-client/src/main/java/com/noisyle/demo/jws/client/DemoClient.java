package com.noisyle.demo.jws.client;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.noisyle.demo.jws.server.DemoService;

/**
 *	手工编写的客户端例子。
 *	也可以用wsimport -keep http://localhost:8080/jws/demo?wsdl自动生成客户端代码。
 */
public class DemoClient {
	private static final Logger logger = LoggerFactory.getLogger(DemoClient.class);
	
	public String sayHello() {
		String msg = null;
		try {
			URL url = new URL("http://localhost:8080/jws/demo?wsdl");
			
			// 第一个参数对应wsdl中的targetNamespace
			// 第二个参数对应wsdl中的name
			QName qname = new QName("http://server.jws.demo.noisyle.com/", "DemoServiceImplService");
			Service service = Service.create(url, qname);
			DemoService demo = service.getPort(DemoService.class);
			msg = demo.sayHello("world");
		} catch (Exception e) {
			logger.error("error", e);
		}
		return msg;
	}
}

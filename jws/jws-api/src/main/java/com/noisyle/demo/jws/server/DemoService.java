package com.noisyle.demo.jws.server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DemoService {
	@WebMethod
	String sayHello(String name);
}

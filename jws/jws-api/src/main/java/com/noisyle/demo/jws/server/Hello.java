package com.noisyle.demo.jws.server;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.noisyle.demo.jws.vo.HelloRequest;
import com.noisyle.demo.jws.vo.HelloResponse;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Hello {
	@WebMethod
	@WebResult
	HelloResponse sayHello(@WebParam HelloRequest request);
}

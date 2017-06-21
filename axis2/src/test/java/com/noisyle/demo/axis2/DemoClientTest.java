package com.noisyle.demo.axis2;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class DemoClientTest {
	@Test
	public void testGetSupportCity() throws RemoteException {
		System.out.println(StringUtils.join(new DemoClient().getSupportCity(), ","));
	}
	
//	@Test
//	public void testGetSupportCityByRPC() throws RemoteException {
//		System.out.println(new DemoClient().getSupportCityByRPC());
//	}

//	@Test
//	public void testGetSupportCityByDocument() throws RemoteException {
//		System.out.println(new DemoClient().getSupportCityByDocument());
//	}
}

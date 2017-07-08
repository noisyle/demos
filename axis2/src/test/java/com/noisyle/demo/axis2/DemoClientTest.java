package com.noisyle.demo.axis2;

import java.rmi.RemoteException;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class DemoClientTest {
	@Test
	public void testGetSupportCity() throws RemoteException {
		System.out.println(StringUtils.join(new DemoClient().getSupportCity(), ","));
	}
	
}

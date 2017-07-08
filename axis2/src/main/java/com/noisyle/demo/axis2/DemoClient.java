package com.noisyle.demo.axis2;

import java.rmi.RemoteException;

import com.noisyle.demo.axis2.WeatherWebServiceStub.GetSupportCity;
import com.noisyle.demo.axis2.WeatherWebServiceStub.GetSupportCityResponse;

public class DemoClient {
	/**
	 * 方法一：通过 wsdl2java反向生成的类 调用
	 * 
	 * @return
	 * @throws RemoteException
	 */
	public String[] getSupportCity() throws RemoteException {
		WeatherWebServiceStub web = new WeatherWebServiceStub();
		GetSupportCity getSupportCity = new GetSupportCity();
		getSupportCity.setByProvinceName("ALL");
		GetSupportCityResponse res = web.getSupportCity(getSupportCity);
		return res.getGetSupportCityResult().getString();
	}
}

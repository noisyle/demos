package com.noisyle.demo.axis2;

import java.rmi.RemoteException;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.rpc.client.RPCServiceClient;

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

	/**
	 * 方法二： 应用rpc的方式调用 这种方式就等于远程调用， 即通过url定位告诉远程服务器，告知方法名称，参数等， 调用远程服务，得到结果。 使用
	 * org.apache.axis2.rpc.client.RPCServiceClient类调用WebService
	 * 
	 * 【注】：
	 * 
	 * 如果被调用的WebService方法有返回值 应使用 invokeBlocking 方法 该方法有三个参数
	 * 第一个参数的类型是QName对象，表示要调用的方法名； 第二个参数表示要调用的WebService方法的参数值，参数类型为Object[]；
	 * 当方法没有参数时，invokeBlocking方法的第二个参数值不能是null，而要使用new Object[]{}。
	 * 第三个参数表示WebService方法的 返回值类型的Class对象，参数类型为Class[]。
	 * 
	 * 
	 * 如果被调用的WebService方法没有返回值 应使用 invokeRobust 方法
	 * 该方法只有两个参数，它们的含义与invokeBlocking方法的前两个参数的含义相同。
	 * 
	 * 在创建QName对象时，QName类的构造方法的第一个参数表示WSDL文件的命名空间名， 也就是
	 * <wsdl:definitions>元素的targetNamespace属性值。
	 * 
	 */
	public String getSupportCityByRPC() throws AxisFault {
		String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

		// 使用RPC方式调用WebService
		RPCServiceClient serviceClient = new RPCServiceClient();
		// 指定调用WebService的URL
		EndpointReference targetEPR = new EndpointReference(url);
		Options options = serviceClient.getOptions();
		// 确定目标服务地址
		options.setTo(targetEPR);
		// 确定调用方法
		options.setAction("http://WebXml.com.cn/getSupportCity");

		/**
		 * 指定要调用的getPrice方法及WSDL文件的命名空间 如果 webservice 服务端由axis2编写 命名空间 不一致导致的问题
		 * org.apache.axis2.AxisFault: java.lang.RuntimeException: Unexpected
		 * subelement arg0
		 */
		QName qname = new QName("http://WebXml.com.cn", "getSupportCity");
		// 指定getPrice方法的参数值
		Object[] parameters = new Object[] { "ALL" };

		// 指定getPrice方法返回值的数据类型的Class对象
		Class[] returnTypes = new Class[] { String.class };

		// 调用方法一 传递参数，调用服务，获取服务返回结果集
		OMElement element = serviceClient.invokeBlocking(qname, parameters);
		// 值得注意的是，返回结果就是一段由OMElement对象封装的xml字符串。
		// 我们可以对之灵活应用,下面我取第一个元素值，并打印之。因为调用的方法返回一个结果
		String result = element.getFirstElement().getText();
		System.out.println(result);
		return result;
	}

	/**
	 * 方法三： 应用document方式调用 用ducument方式应用相对繁琐而灵活。现在用的比较多。因为真正摆脱了我们不想要的耦合
	 */
	public String getSupportCityByDocument() {

		OMElement result = null;
		try {
			String url = "http://www.webxml.com.cn/WebServices/WeatherWebService.asmx";

			Options options = new Options();
			// 指定调用WebService的URL
			EndpointReference targetEPR = new EndpointReference(url);
			options.setTo(targetEPR);
			options.setAction("http://WebXml.com.cn/getSupportCity");

			ServiceClient sender = new ServiceClient();
			sender.setOptions(options);

			OMFactory fac = OMAbstractFactory.getOMFactory();
			String tns = "http://WebXml.com.cn";
			// 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的
			OMNamespace omNs = fac.createOMNamespace(tns, "tns");

			OMElement method = fac.createOMElement("getSupportCity", omNs);
			OMElement symbol = fac.createOMElement("byProvinceName", omNs);
			symbol.setText("ALL");
			method.addChild(symbol);
			method.build();

			result = sender.sendReceive(method);

			System.out.println("*************** " + result);

		} catch (AxisFault axisFault) {
			axisFault.printStackTrace();
		}
		return result + "";
	}
}

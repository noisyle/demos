package com.noisyle.demo.restdemo.client;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class ClientTest {
	@Test
	public void testGetWithCustomHeader() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8081/rest-demo-server/hello");
		httpGet.addHeader("CustomKey", "CustomValue");
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
		    System.out.println(response1.getStatusLine());
		    HttpEntity entity1 = response1.getEntity();
		    EntityUtils.consume(entity1);
		} finally {
		    response1.close();
		}
	}
	
	@Test
	public void testGet() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8081/rest-demo-server/hello");
		CloseableHttpResponse response1 = httpclient.execute(httpGet);
		try {
			System.out.println(response1.getStatusLine());
			HttpEntity entity1 = response1.getEntity();
			EntityUtils.consume(entity1);
		} finally {
			response1.close();
		}
	}
}
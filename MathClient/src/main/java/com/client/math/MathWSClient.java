package com.client.math;

import java.io.IOException;
import java.util.Properties;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class MathWSClient {
	public String operation;
	public int value1;
	public int value2;
	private String port;
	private String host;

	public MathWSClient() {
		
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mathapp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		port = props.getProperty("port");
		host = props.getProperty("host");
		//System.out.println("MathProxy Port Used: " + port);

	}

	public String getMathDotNetResult(String operation , int value1, int value2) throws ClientProtocolException, IOException {
		String url = "http://" + host + ":" + port +
									"/MathProxy/rest/hello/mathdotnet?" +
									"operation=" + operation +
									"&value1=" + value1 + 
									"&value2=" + value2;

		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);

		HttpEntity entity = httpResponse.getEntity();

		String responseString = EntityUtils.toString(entity);

		return responseString;

	}
	
	public String getMathComplexResult(String operation , String values) throws ClientProtocolException, IOException {
		String url = "http://" + host + ":" + port +
									"/MathProxy/rest/hello/mathcomplex?" +
									"operation=" + operation +
									"&values=" + values;

		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);

		HttpEntity entity = httpResponse.getEntity();

		String responseString = EntityUtils.toString(entity);

		return responseString;

	}

	public String getMathResult(String operation, int value1, int value2) throws ClientProtocolException, IOException {

		String url = "http://" + host + ":" + port +
									"/MathProxy/rest/hello/math?" +
									"operation=" + operation +
									"&value1=" + value1 + 
									"&value2=" + value2;

		HttpClient httpClient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(url);
		HttpResponse httpResponse = httpClient.execute(httpGet);

		HttpEntity entity = httpResponse.getEntity();

		String responseString = EntityUtils.toString(entity);

		return responseString;
	}

}
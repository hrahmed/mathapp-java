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
	private final String PROXY_HOST = "proxyHost";
	private final String PROXY_PORT = "proxyPort";

	public MathWSClient() {
		
		// get properties from 'System', passed in using -D
		Properties systemProps = System.getProperties();
		
		// get properties from config file
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mathapp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// get properties
		if (systemProps.getProperty(PROXY_HOST) != null) {
			host = systemProps.getProperty(PROXY_HOST);
		} else {
			host = props.getProperty("host");
		}

		
		if (systemProps.getProperty(PROXY_PORT) != null) {
			port = systemProps.getProperty(PROXY_PORT);
		} else {
			port = props.getProperty("port");
		}
		
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
	
	public String getMathNodeSimple(String operation, int value1, int value2) throws ClientProtocolException, IOException {

		String url = "http://" + host + ":" + port +
									"/MathProxy/rest/hello/mathnodesimple?" +
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
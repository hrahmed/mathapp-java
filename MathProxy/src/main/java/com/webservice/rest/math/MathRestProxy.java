package com.webservice.rest.math;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis2.AxisFault;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.context.ConfigurationContextFactory;
import org.apache.axis2.context.ServiceContext;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.mathws.MathWebServiceSoapStub;

import com.math.Local;
import com.math.Remote;
import com.math.complex.MathComplexBackendStub;
import com.math.complex.MathComplexBackendStub.CalculateMean;
import com.math.complex.MathComplexBackendStub.CalculateMeanResponse;
import com.math.complex.MathComplexBackendStub.CalculateMedian;
import com.math.complex.MathComplexBackendStub.CalculateMedianResponse;
import com.math.complex.MathComplexBackendStub.CalculateMode;
import com.math.complex.MathComplexBackendStub.CalculateModeResponse;
import com.math.simple.MathSimpleBackendStub;
import com.math.simple.MathSimpleBackendStub.Process;
import com.math.simple.MathSimpleBackendStub.ProcessResponse;
import com.mathapp.utilities.HttpRequestHelper;
import com.mathapp.utilities.JsonHelperForMathApp;
import com.mathapp.utilities.MathResponseData;


@Path("/hello")
public class MathRestProxy {	

	private String simplePort = null;
	private String simpleHost = null;
	private String complexPort = null;
	private String complexHost = null;
	private String dotNetPort = null;
	private String dotNetHost = null;
	MathSimpleBackendStub simpleStub;
	MathComplexBackendStub complexStub;
	private Call callMultiply;
	private Call callAdd;
	private MathWebServiceSoapStub dotNetStub;



	public MathRestProxy() {
		// TODO Auto-generated constructor stub
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mathapp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		simplePort = props.getProperty("simpleport");
		simpleHost = props.getProperty("simplehost");
		complexPort = props.getProperty("complexport");
		complexHost = props.getProperty("complexhost");
		dotNetPort = props.getProperty("dotnetport");
		dotNetHost = props.getProperty("dotnethost");

		try {
			simpleStub = new MathSimpleBackendStub( "http://" + simpleHost + ":" +
					simplePort	+
					"/MathSimpleBackend/services/MathSimpleBackend.MathSimpleBackendHttpSoap12Endpoint/");

			complexStub = new MathComplexBackendStub( "http://" + complexHost + ":" +
					complexPort	+ 
					"/MathComplexBackend/services/MathComplexBackend.MathComplexBackendHttpSoap12Endpoint/");

			// For dotNet call
			Service service = new Service();
			String endpoint = "http://" + dotNetHost + ":" +
					dotNetPort	+ 
					"/MathWebService/MathWebService.asmx";
			try {
				dotNetStub = new MathWebServiceSoapStub(new java.net.URL(endpoint),service);
			} catch (org.apache.axis.AxisFault e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// required for MathComplexBackend, increases client connections from 2 (default) 
			ConfigurationContext configurationContext = 
					ConfigurationContextFactory.createEmptyConfigurationContext();

			MultiThreadedHttpConnectionManager multiThreadedHttpConnectionManager = new MultiThreadedHttpConnectionManager(); 

			HttpConnectionManagerParams params = new HttpConnectionManagerParams(); 
			params.setDefaultMaxConnectionsPerHost(200); 
			multiThreadedHttpConnectionManager.setParams(params); 
			HttpClient httpClient = new HttpClient(multiThreadedHttpConnectionManager); 
			configurationContext.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, httpClient);

			ServiceContext context = complexStub ._getServiceClient().getServiceContext();
			complexStub._getServiceClient().getOptions().setTimeOutInMilliSeconds(5000000);


			context.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
			context.setProperty(HTTPConstants.CACHED_HTTP_CLIENT, httpClient);
			context.setProperty(HTTPConstants.AUTO_RELEASE_CONNECTION, true);




			/*
			MultiThreadedHttpConnectionManager connManager = (MultiThreadedHttpConnectionManager)context.getProperty(HTTPConstants.MULTITHREAD_HTTP_CONNECTION_MANAGER);

			if(connManager == null) {
			connManager = new MultiThreadedHttpConnectionManager();
			context.setProperty(HTTPConstants.MULTITHREAD_HTTP_CONNECTION_MANAGER, connManager);
			connManager.getParams().setMaxTotalConnections(100);
			connManager.getParams().setMaxConnectionsPerHost(HostConfiguration.ANY_HOST_CONFIGURATION, 100);
			}*/

		} catch (AxisFault e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return "Hello Jersey";
	}

	@GET
	@Path("/bad")
	@Produces(MediaType.TEXT_PLAIN)
	public String badMath() {
		try {
			Thread.sleep(20000);
			throw new Exception ("Bad Math Error");
		} catch (Exception e) {

		} 
		return "Bad Math";
	}


	// Process he Node backend
	@GET
	@Path("/mathnode")
	@Produces(MediaType.APPLICATION_JSON)
	public String mathNodeBackend(
			@DefaultValue("blank") @QueryParam("operation") String operation,
			@DefaultValue("0") @QueryParam("value1") int value1,
			@DefaultValue("0") @QueryParam("value2") int value2) {

		HttpRequestHelper httpReq = new HttpRequestHelper();
		String response;
		
		// set post parameters
		String urlParameters = 
				"operation=" + operation + 
				"&value1=" + value1 +
				"&value2=" + value2;


		try {
			response = httpReq.sendPost("http://localhost:8999/api/math", urlParameters);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = e.getMessage();
		}

		System.out.println("\nOutput: \n" + response);

		return "Hello with: " + operation;
	}



	// MathComplex Code
	@GET
	@Path("/math")
	@Produces(MediaType.APPLICATION_JSON)
	public String mathProcess(
			@DefaultValue("blank") @QueryParam("operation") String operation,
			@DefaultValue("0") @QueryParam("value1") int value1,
			@DefaultValue("0") @QueryParam("value2") int value2) {

		Long result = (long) 0;
		JsonHelperForMathApp jsonHelperForMathApp = new JsonHelperForMathApp();
		MathResponseData response = new MathResponseData();

		if (operation.compareToIgnoreCase("slow") == 0){
			MathUtility.doSleep(20);
			response.setResult(-1);
			response.setStatus("bronze");

		} else if (operation.compareToIgnoreCase("average") == 0){

			Average avg = new Average();
			try {
				avg.calculateAverage(operation, value1, value2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

			response.setResult(-1);
			response.setStatus("bronze");


		} else {

			try {				
				Process proc = new Process();
				proc.setOperation(operation);
				proc.setValue1(value1);
				proc.setValue2(value2);
				ProcessResponse pr = simpleStub.process(proc);

				result =  pr.get_return();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}

			// custom backend
			CustomBackend backend = new CustomBackend(0);

			for (int i = 0; i <  10000 ; i++) {
				backend.executeBackend();
			}


			if (result != null){
				response.setResult(result);
				// calculate status
				if (result > 500) response.setStatus("platinum");
				else if (result > 100) response.setStatus("gold");
				else if (result >= 0) response.setStatus("silver");
				else response.setStatus("bronze");
			} else {
				response.setResult(result);
				response.setStatus("bronze");
			}
		}
		return jsonHelperForMathApp.createJsonAsString(response);
	}

	@GET
	@Path("/mathcomplex")
	@Produces(MediaType.TEXT_PLAIN)
	public String mathComplex(
			@DefaultValue("blank") @QueryParam("operation") String operation,
			@DefaultValue("0") @QueryParam("value1") int value1,
			@DefaultValue("0") @QueryParam("value2") int value2,
			@DefaultValue("0") @QueryParam("values") String values) {

		Long result = (long) 0;
		if (operation.compareToIgnoreCase("MEAN") == 0){

			CalculateMean mean = new CalculateMean();
			String[] stringValues = values.split(",");
			long[] longValues = new long[stringValues.length];
			for (int i = 0; i < stringValues.length; i++) {
				longValues[i]=Long.valueOf(stringValues[i]);
			}
			try {
				mean.setValues(longValues);
				CalculateMeanResponse response = complexStub.calculateMean(mean);
				result = response.get_return();

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else if (operation.compareToIgnoreCase("MEDIAN") == 0){
			CalculateMedian median = new CalculateMedian();
			String[] stringValues = values.split(",");
			long[] longValues = new long[stringValues.length];
			for (int i = 0; i < stringValues.length; i++) {
				longValues[i]=Long.valueOf(stringValues[i]);
			}
			try {
				median.setValues(longValues);
				CalculateMedianResponse response = complexStub.calculateMedian(median);
				result = response.get_return();

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (operation.compareToIgnoreCase("MODE") == 0){
			CalculateMode mode = new CalculateMode();
			String[] stringValues = values.split(",");
			long[] longValues = new long[stringValues.length];
			for (int i = 0; i < stringValues.length; i++) {
				longValues[i]=Long.valueOf(stringValues[i]);
			}
			try {
				mode.setValues(longValues);

				if (longValues.length == 7){
					DataSort.sort(longValues);
				}

				CalculateModeResponse response = complexStub.calculateMode(mode);
				result = response.get_return();

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			if (operation.compareToIgnoreCase("FLUSH")==0){
				com.math.complex.MathComplexBackendStub.Process proc = new com.math.complex.MathComplexBackendStub.Process();
				proc.setValue1(0);
				proc.setValue2(0);
				proc.setOperation(operation);
				try {
					com.math.complex.MathComplexBackendStub.ProcessResponse response = complexStub.process(proc);
					result=response.get_return();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if(operation.compareToIgnoreCase("BARCODE")==0) {
				String[] stringValues = values.split(",");
				long[] longValues = new long[stringValues.length];
				for (int i = 0; i < stringValues.length; i++) {
					longValues[i]=Long.valueOf(stringValues[i]);
				}

				Remote remote = new Remote(longValues[0]);
				Local local = new Local(longValues[2]);

				for (int i = 0; i < longValues[1]; i++) {
					remote.execute();
				}

				for (int i = 0; i < longValues[3]; i++) {
					local.execute();
				}


			} else if(operation.compareToIgnoreCase("PYTHON")==0) {

				String[] stringValues = values.split(",");
				long[] longValues = new long[stringValues.length];
				for (int i = 0; i < stringValues.length; i++) {
					longValues[i]=Long.valueOf(stringValues[i]);
				}

				Remote remote = new Remote(longValues[0]);
				remote.execute();

				Local local = new Local(longValues[1]);
				local.execute();

			}




			return result.toString();
		}

		return result.toString();

	}

	@GET
	@Path("/mathdotnet")
	@Produces(MediaType.APPLICATION_JSON)
	public String mathProcessDotNet(
			@DefaultValue("blank") @QueryParam("operation") String operation,
			@DefaultValue("0") @QueryParam("value1") long value1,
			@DefaultValue("0") @QueryParam("value2") long value2) {

		Long result = (long) 0;
		JsonHelperForMathApp jsonHelperForMathApp = new JsonHelperForMathApp();
		MathResponseData response = new MathResponseData();

		if (operation.compareToIgnoreCase("add") == 0) {

			try {
				result = (Long) dotNetStub.add(value1, value2);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else if (operation.compareToIgnoreCase("multiply") == 0){

			try {
				result = (Long) dotNetStub.multiply(value1, value2);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else {
			result = null;
		}

		if (result != null){
			response.setResult(result);
			// calculate status
			if (result > 500) response.setStatus("platinum");
			else if (result > 100) response.setStatus("gold");
			else if (result >= 0) response.setStatus("silver");
			else response.setStatus("bronze");
		} else {
			response.setResult(result);
			response.setStatus("bronze");
		}
		return jsonHelperForMathApp.createJsonAsString(response);
	}

}        
package com.mathapp.utilities;
import java.awt.image.ConvolveOp;
import java.beans.XMLDecoder;
import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class JsonHelperForMathApp {

	public String createJsonAsString(MathResponseData response) {

		long result = response.getResult();
		String status = response.getStatus();

		JSONObject obj = new JSONObject();
		obj.put("result", result);
		obj.put("status", status);

		return obj.toString();
	}

	public MathResponseData getMathResponse(String jsonString){
		MathResponseData response = new MathResponseData();
		
		
		try {
					 
			JSONObject json = (JSONObject)new JSONParser().parse(jsonString);
		    response.setResult((Long)json.get("result"));
		    response.setStatus((String)json.get("status"));
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


		return response;
	}


	public static void main (String args[]) {

		// For testing

		JsonHelperForMathApp helper = new JsonHelperForMathApp();
		MathResponseData sent = new MathResponseData();
		sent.setResult(100);
		sent.setStatus("gold");


		String jsonOut = helper.createJsonAsString(sent);
		
		System.out.print(jsonOut);
		
		sent = helper.getMathResponse(jsonOut);

		
		System.out.println("Result is: " + sent.getResult());
		System.out.println("Status is: " + sent.getStatus());


	}
}
package com.webservice.rest.math;


// Used to fake a backend resource
public class CustomBackend {
	
	long sleep;
	
	public CustomBackend(long s) 
	{
		sleep = s;
	}

	public void executeBackend() {

		MathUtility.doSleep(sleep);

	}

}

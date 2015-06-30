package com.webservice.rest.math;

public class MathUtility {
	
	public static void doSleep(long seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}
	
	public static void doSleepMillisecond(long ms){
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}

}

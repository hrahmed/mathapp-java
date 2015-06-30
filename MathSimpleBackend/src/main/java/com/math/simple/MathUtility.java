package com.math.simple;

public class MathUtility {
	
	public static void doSleep(long seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}

}

package com.math.simple;

import java.util.Random;

public class MathUtility {

	public static void doSleep(long seconds){
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}

	public static void doSleepRange(int aStart, int aStop) {
		// TODO Auto-generated method stub
		int randomInt = showRandomInteger(aStart, aStop, new Random());
		try {
		    //log("***Generated : " + randomInt);
			Thread.sleep(randomInt);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}

	private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
		if (aStart > aEnd) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		//get the range, casting to long to avoid overflow problems
		long range = (long)aEnd - (long)aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		return (int)(fraction + aStart);    
	}

	private static void log(String aMessage){
		System.out.println(aMessage);
	}

}

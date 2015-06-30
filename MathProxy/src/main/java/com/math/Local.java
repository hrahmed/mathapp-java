package com.math;


// Used to fake a backend resource
public class Local {
	
	long sleepMilliseconds;
	
	public Local(long s) 
	{
		sleepMilliseconds = s;
	}

	public void execute() {

		try {
			Thread.sleep(sleepMilliseconds);
		} catch (InterruptedException e) {
			//swallow
			//e.printStackTrace();
		}
	}

}

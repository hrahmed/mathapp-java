package com.math;


// Used to fake a backend resource
public class Remote {
	
	long sleepMilliseconds;
	
	public Remote(long s) 
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

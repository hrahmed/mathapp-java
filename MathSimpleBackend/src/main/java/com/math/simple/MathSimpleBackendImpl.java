package com.math.simple;


public class MathSimpleBackendImpl {

	private int result;

	public long execute(OperationData opdata, int hashcode) throws Throwable{
			
		if (opdata.getOperation().equalsIgnoreCase("ADD")) {
			Add add = new Add(opdata.getValue1(), opdata.getValue2());
			//MathUtility.doSleep(2);
			MathUtility.doSleepRange(500, 2500);
			setResult(add.doAdd());
			
		} else if (opdata.getOperation().equalsIgnoreCase("SUBTRACT")) {
			Subtract sub = new Subtract(opdata.getValue1(), opdata.getValue2());
			//MathUtility.doSleep(2);
			MathUtility.doSleepRange(500, 2500);
			setResult(sub.doSubtract());

		} else if (opdata.getOperation().equalsIgnoreCase("MULTIPLY")){
			Multiply mult = new Multiply(opdata.getValue1(), opdata.getValue2());
			//MathUtility.doSleep(4);
			MathUtility.doSleepRange(2500, 4000);
			setResult(mult.doMultiply());

		} else if (opdata.getOperation().equalsIgnoreCase("DIVIDE")){
			Divide div = new Divide(opdata.getValue1(), opdata.getValue2());
			//MathUtility.doSleep(2);
			MathUtility.doSleepRange(500, 2500);
			setResult(div.doDivide());

		} else if (opdata.getOperation().equalsIgnoreCase("STALL")){
			//MathUtility.doSleep(50);
			MathUtility.doSleepRange(31000, 50000);
			setResult(0);
			
		} else {
			MathUtility.doSleep(4);
			throw new Exception("Invalid Math Operation");
			//throw new NullPointerException();
		}
		return getResult();
	}
	
	public long getResult() {
		return result;
	}
	
	public void setResult(int result) {
		this.result = result;
	}
}

package com.math.simple;

import java.util.Arrays;
import java.util.List;

public class MathSimpleBackend {

	private List<String> kMathOperations = Arrays.asList("add","subtract","multiply","divide","stall","error");

	public MathSimpleBackend() {
	}

	public long process(String operation, int value1, int value2) throws Throwable{

		MathSimpleBackendImpl impl = new MathSimpleBackendImpl();
		OperationData opdata = new OperationData();
		if (kMathOperations.contains(operation)) {
			opdata.setOperation(operation);
			opdata.setValue1(value1);
			opdata.setValue2(value2);
		} else {
			MathUtility.doSleep(4);
			throw new Exception("Invalid Math Opertion");
		}
		return impl.execute(opdata, operation.hashCode());
	}
}

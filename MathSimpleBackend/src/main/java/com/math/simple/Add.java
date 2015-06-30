package com.math.simple;


//hello
public class Add {
	private int value1;
	private int value2;
	private int result;
	
	public Add(int value1, int value2) {
		setValue1(value1);
		setValue2(value2);
	}

	public int doAdd() throws Throwable{
		
		try {
			result = value1 + value2;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
	
	public int getResult() {
		return result;
	}
	
	public void setValue1(int value1) {
		this.value1 = value1;
	}
	
	public void setValue2(int value2) {
		this.value2 = value2;
	}

}

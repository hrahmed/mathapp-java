package com.mathapp.utilities;


public class MathResponseData{

	long result = 0;
	String status = "unknown";

	public MathResponseData() {
		// TODO Auto-generated constructor stub
	}
	public void setResult(long result) {
		this.result = result;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getResult() {
		return result;
	}

	public String getStatus() {
		return status;
	}

}
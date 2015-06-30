package com.math.complex;

import java.io.IOException;
import java.util.Properties;

public class MathProperties {
	private static long stalldelay = 2000;
	private static long leakhighcount;
	private static long leaklowcount;
	private static boolean propertiesSet = false;

	public static void setProperties() {
		Properties props = new Properties();
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("mathapp.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		stalldelay = Long.parseLong(props.getProperty("stalldelay"));
		leakhighcount = Long.parseLong(props.getProperty("leakhighcount"));
		leaklowcount = Long.parseLong(props.getProperty("leaklowcount"));
		propertiesSet=true;
	}

	public static boolean arePropertiesSet() {
		return MathProperties.propertiesSet;
	}

	public static long getStalldelay() {
		return stalldelay;
	}

	public static long getLeakhighcount() {
		return leakhighcount;
	}

	public static long getLeaklowcount() {
		return leaklowcount;
	}
}

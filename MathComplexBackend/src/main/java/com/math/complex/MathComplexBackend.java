package com.math.complex;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class MathComplexBackend {

	private SortCache toSort;

	public long process(long value1, long value2, String operation){
		if (operation.compareToIgnoreCase("FLUSH")==0){
			SortCache.flushCache();
			return 1;
		}

		return 0;
	}

	// used to show errors to axis 2 connection limit that is not used
	public long calculateMean (long[] values){

		if (!MathProperties.arePropertiesSet()){
			MathProperties.setProperties();
		}

		if (values.length == 7){
			try {
				Thread.sleep(MathProperties.getStalldelay());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		long sum = 0;

		for (int i = 0; i < values.length; i++) {
			sum += values[i];
		}

		return sum/values.length;
	}

	// used to show memory leak
	public long calculateMedian (long[] values){
		
		if (!MathProperties.arePropertiesSet()){
			MathProperties.setProperties();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//Arrays.sort(values);

		MathSort sort = new MathSort();

		values = sort.quickSort(values);
		for (int i = 0; i < values.length; i++) {
		}
		int middle = values.length/2;
		if (values.length%2 == 1){
			return values[middle];
		} else {
			return (values[(middle-1)] + values[middle]) /2;
		}
	}

	public long calculateMode(long[] values){
		
		if (!MathProperties.arePropertiesSet()){
			MathProperties.setProperties();
		}

		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		long maxValue = 0 ,maxCount = 0;

		for (int i = 0; i < values.length; i++) {
			int count = 0;
			for (int j = 0; j < values.length; j++) {
				if (values[j] == values[i]) ++count;
			}
			if (count > maxCount){
				maxCount = count;
				maxValue = values[i];
			}
		}
		return maxValue;
	}
}

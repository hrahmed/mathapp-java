package com.math.complex;

import java.util.ArrayList;

public class SortCache {
	

	private Object fObj;
	private static ArrayList<Object> cache = new ArrayList<Object>();

	public SortCache() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(Object obj) {
		//this.mathValues = mathValues;
		cache.add(obj);
	}
	
	public void remove(Object obj){
		cache.remove(obj);
	}
	
	public static void flushCache(){
		cache.clear();
		System.out.println("SortCache Flushed");
	}
	
	public static long getSize(){
		return cache.size();
	}
	
}

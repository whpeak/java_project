package com.pyjava.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MyThreadLocal
 * @author wangheng8
 * @date: 2018年10月8日 下午4:35:56

 */

public class MyThreadLocal {
	
	private Map<MyThreadLocal,Integer> myThreadLocalMap =  new HashMap<>();

	
	public void get()
	{
		System.out.println(myThreadLocalMap.get(this));
	}
	
	public void set(int value)
	{
		myThreadLocalMap.put(this, value);
	}
	
	public static void main(String[] args) {
		MyThreadLocal myThreadLocal = new MyThreadLocal();
		
		myThreadLocal.set(5);
		myThreadLocal.get();
		
	}
}

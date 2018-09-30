package com.pyjava.obj.constructor;

/**
 * @ClassName: Constructor
 * @author wangheng8
 * @date: 2018年8月28日 上午10:55:45

 */

public class Constructor {
	static
	{
		System.out.println("step0:");
	}
	private int a;
	private double b;
	private String c;
	{
		System.out.println("step1:"+this.toString());
	}
	Constructor()
	{
		System.out.println("step2:"+this.toString());
	}
	@Override
	public String toString() {
		return "Constructor [a=" + a + ", b=" + b + ", c=" + c + "]";
	}
	public static void main(String[] args) {
		Constructor constructor1 = new Constructor();
		System.out.println(constructor1);
		Constructor constructor2 = new Constructor();
		System.out.println(constructor2);
	}

}

package com.pyjava.obj.inter_abs;

/**
 * @ClassName: InterClass
 * @author wangheng8
 * @date: 2018年8月17日 下午2:45:12

 */

public interface InterClass {
	public static final String test = "test";
	int a = 5; //会被默认的添加上public static final 修饰符。
	public abstract void test(); //会被默认的添加上public abstract修饰符
}

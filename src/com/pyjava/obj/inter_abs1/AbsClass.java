package com.pyjava.obj.inter_abs1;

/**
 * @ClassName: AbsClass
 * @author wangheng8
 * @date: 2018年8月17日 下午3:18:07
 * 
 * 抽象类是对一种事物的抽象，即对类抽象

 */

public abstract class AbsClass implements  InterClass1 {

	@Override
	public void test() {
     System.out.println("抽象类中实现接口的一个方法。。。。test");
     System.out.println(AbsClass.a);
	}
	abstract void test1();
}

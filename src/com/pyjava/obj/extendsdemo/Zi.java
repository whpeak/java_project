package com.pyjava.obj.extendsdemo;

/**
 * @ClassName: Zi
 * @author wangheng8
 * @date: 2018年8月17日 下午2:24:48
 *
 * 主要目的就是看看private修饰的变量和方法，在子类中的表现
 * 既然是private不能被继承类访问，就不要在开发一个public方法，然后再去访问private
 */

public class Zi extends Fu {

	public void myTest()

	{
		this.test1();
		System.out.println("test public : "+this.test1);
	}

	public static void main(String[] args) {
		new Zi().myTest();
	}
}

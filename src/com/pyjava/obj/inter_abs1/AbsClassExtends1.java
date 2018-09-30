package com.pyjava.obj.inter_abs1;


/**
 * @ClassName: AbsClassExtends1
 * @author wangheng8
 * @date: 2018年8月17日 下午3:19:49

 */

public class AbsClassExtends1 extends AbsClass {

	@Override
	public void test1() {
		System.out.println("继承抽象类的方法，实现了抽象方法");
		this.test();
	}
public static void main(String[] args) {
	new AbsClassExtends1().test1();
}
}

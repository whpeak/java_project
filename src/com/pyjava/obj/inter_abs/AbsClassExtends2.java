package com.pyjava.obj.inter_abs;

/**
 * @ClassName: AbsClassExtends2
 * @author wangheng8
 * @date: 2018年8月17日 下午2:54:50

 */

public class AbsClassExtends2 extends AbsClassExtends1 {

	@Override
	public void test() {
		System.out.println("call this test1.....");
		this.test1();
	}
public static void main(String[] args) {
	new AbsClassExtends2().test();
}
}

package com.pyjava.obj.inter_abs;

/**
 * @ClassName: InterClassImpl
 * @author wangheng8
 * @date: 2018年8月17日 下午2:46:59

 */

public class InterClassImpl implements InterClass {

	@Override
	public void test() {
		System.out.println(InterClassImpl.a);
	}
public static void main(String[] args) {
	new InterClassImpl().test();
}
}

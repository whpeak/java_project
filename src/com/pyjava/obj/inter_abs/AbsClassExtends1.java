package com.pyjava.obj.inter_abs;

/**
 * @ClassName: AbsClassExtends1
 * @author wangheng8
 * @date: 2018年8月17日 下午2:51:26

 */

public abstract class AbsClassExtends1 extends AbsClass {

	public abstract void test();

	@Override
	public void test1() {
		System.out.println("in AbsClassExtends1 only implements test1");
		this.test2();
	}
	
	private void test2()
	{
		System.out.println("私有方法不能抽象");
	}

}

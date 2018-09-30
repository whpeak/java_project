package com.pyjava.obj.extendsdemo;

/**
 * @ClassName: Fu
 * @author wangheng8
 * @date: 2018年8月17日 下午2:23:28

 */

public class Fu {
	private String test= "private";
	public String test1= "public";
	
	private void test()
	{
		System.out.println("this is a private test:"+this.test);
	}
	
	public void test1()
	{
		System.out.println("this is a public test1:"+this.test1);
		this.test();
	}

}

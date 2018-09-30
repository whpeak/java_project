package com.pyjava.obj.inter_abs2;

/**
 * @ClassName: AlramDoorInstance
 * @author wangheng8
 * @date: 2018年8月17日 下午3:55:44
 * 
 */

public class AlramDoorInstance extends AlramDoor {

	@Override
	public void alram() {
		System.out.println("alram");

	}

	@Override
	void open() {
		System.out.println("open");

	}

	@Override
	void close() {
		System.out.println("close");

	}

}

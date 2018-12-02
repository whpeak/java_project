package com.pyjava.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Base
 * @author wangheng8
 * @date: 2018年9月30日 下午3:37:16

 */

public class Base2 {
	private ArrayBlockingQueue<String>arrayBlockingQueue = new ArrayBlockingQueue<String>(1);
	
	private volatile Integer obj = 0;
	private int a = 4;
	public void test() throws InterruptedException
	{
		thread1Task task1 = new thread1Task();
		thread1Task task2 = new thread1Task();
		thread1Task task3 = new thread1Task();
		
		
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		
		System.out.println(obj);
		thread1.start();
		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("主线程开始获取锁。。。。。");
		System.out.println("主线程开始获取锁。。。。。");
		System.out.println("主线程开始获取锁。。。。。");
		System.out.println("主线程开始获取锁。。。。。");
		System.out.println("主线程开始获取锁。。。。。");
		System.out.println("主线程开始获取锁。。。。。");
		
		System.out.println("释放子线程的锁。。。。。");
//		obj=1;
		System.out.println(thread1.getState());
		TimeUnit.SECONDS.sleep(5);
		System.out.println(thread1.getState());
		synchronized (obj) {
			System.out.println("主线程进入同步代码块"+obj);
		}
		
		System.out.println("主线程已经获取锁了。。。。。");
		System.out.println(thread1.getState());
		
		thread1.join();
		
		System.out.println("main end....");
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Base2().test();
	}
	
	
	class thread1Task implements Runnable
	{

		@Override
		public void run() {
			
			this.doTask();
		}
		
		public  void doTask() 
		{
			synchronized (obj) {
				Thread.yield(); // 告诉jvm 我可以从执行状态变为可执行状态，而不是立马停止
				while(obj==0)
				{
//					System.out.println("111");
					a++;
				}
				System.out.println("退出循环.............................");
			}
			
			
		}
		
	}
	

}

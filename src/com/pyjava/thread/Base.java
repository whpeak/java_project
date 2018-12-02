package com.pyjava.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Base
 * @author wangheng8
 * @date: 2018年9月30日 下午3:37:16

 */

public class Base {
	private ArrayBlockingQueue<String>arrayBlockingQueue = new ArrayBlockingQueue<String>(1);
	private ReentrantLock reentrantLock = new ReentrantLock();
	
	public void test() throws InterruptedException
	{
		thread1Task task1 = new thread1Task();
		thread1Task task2 = new thread1Task();
		thread1Task task3 = new thread1Task();
		
		
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(task2);
		Thread thread3 = new Thread(task3);
		
		thread1.start();
		Thread.sleep(1000);
		thread2.start();
		Thread.sleep(1000);
		thread3.start();
		Thread.sleep(1000);
		
//		System.out.println("thread0:"+thread1.getState());
//		arrayBlockingQueue.put("a");
		System.out.println("thread0:"+thread1.getState());
		
		System.out.println("thread1:"+thread2.getState());
		System.out.println("thread2:"+thread3.getState());
		
		System.out.println(reentrantLock);
		
		
		
	}
	
	public static void main(String[] args) throws InterruptedException {
		new Base().test();
	}
	
	
	class thread1Task implements Runnable
	{

		@Override
		public void run() {
			
			this.doTask();
		}
		
		public  void doTask()
		{
			synchronized(reentrantLock)
			{
				System.out.println(Thread.currentThread().getName()+reentrantLock);
				while(true)
				{
					//当前线程在等待一个monitor lock 比如等待执行synchronized代码块或者使用synchronized标记的方法
				}
				
			}
			
			
		}
		
	}
	

}

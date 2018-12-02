package com.pyjava.thread.level5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: CountDownLatchDemo
 * @author wangheng8
 * @date: 2018年10月6日 下午3:08:29
 * 
 */

public class CountDownLatchDemo {

	public void start(int num) throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(num);

		WaitDemo waitDemo = new WaitDemo(countDownLatch);
		Thread thread = new Thread(waitDemo);

		TaskDemo TaskDemo1 = new TaskDemo(countDownLatch);
		Thread thread1 = new Thread(TaskDemo1);

		TaskDemo TaskDemo2 = new TaskDemo(countDownLatch);
		Thread thread2 = new Thread(TaskDemo2);

		TaskDemo TaskDemo3 = new TaskDemo(countDownLatch);
		Thread thread3 = new Thread(TaskDemo3);

		TaskDemo TaskDemo4 = new TaskDemo(countDownLatch);
		Thread thread4 = new Thread(TaskDemo4);

		thread.start();

		TimeUnit.SECONDS.sleep(1);
		
		System.out.println("do step 1");
		
		countDownLatch.countDown();
		
		System.out.println("do step 2");
		
		countDownLatch.countDown();
		
		System.out.println("do step 3");
		
		countDownLatch.countDown();
		
		System.out.println("thread 0 should do ...");
		
		
//		thread1.start();
//		thread2.start();
//		thread3.start();
//		thread4.start();

	}

	public static void main(String[] args) throws InterruptedException {

		new CountDownLatchDemo().start(2);
	}

	class WaitDemo implements Runnable {

		private CountDownLatch countDownLatch;

		WaitDemo(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " begin do .....");
				this.countDownLatch.await();
				System.out.println(Thread.currentThread().getName() + " end do .....");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	class TaskDemo implements Runnable {
		private CountDownLatch countDownLatch;

		TaskDemo(CountDownLatch countDownLatch) {
			this.countDownLatch = countDownLatch;
		}

		@Override
		public void run() {
			try {
				System.out.println(Thread.currentThread().getName() + " begin countDown .....");
				this.countDownLatch.countDown();
				System.out.println(Thread.currentThread().getName() + " end countDown .....");
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}

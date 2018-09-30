package com.pyjava.thread.relationship.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者demo，两种条件的唤醒，更高级的请看GSdemo （get、set的第一个字母）
 * @ClassName: SyncDemo5
 * @author wangheng8
 * @date: 2018年9月29日 下午5:51:19
 * 
 */

public class SyncDemo6 {

	static boolean flag = true;

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition condition16 = lock.newCondition();
		Condition condition26 = lock.newCondition();
		Test16 target = new Test16(lock, condition16,condition26);
		Thread thread = new Thread(target);

		Test26 target2 = new Test26(lock, condition16,condition26);
		Thread thread2 = new Thread(target2);
		thread.start();
		Thread.sleep(1000*9);
		thread2.start();
		
		System.out.println(thread.getPriority());
		System.out.println(thread2.getPriority());
		System.out.println(Thread.currentThread().getPriority());

	}

}

class Test16 implements Runnable {
	private Lock lock;
	private Condition condition16;
	private Condition condition26;

	Test16(Lock lock, Condition condition16, Condition condition26) {
		this.condition16 = condition16;
		this.condition26 = condition26;
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			Thread currentThread = Thread.currentThread();
			String name = currentThread.getName();
			while (true) {
				System.out.println(name+"准备加锁："+lock);
				lock.lock();
				System.out.println(name+"加锁完成："+lock);
				while (!SyncDemo6.flag) 
				{
					System.out.println(name+"开始等待："+lock);
					condition16.await();
					System.out.println(name+"被唤醒，等待结束："+lock);
				} 
				
				System.out.println("do some things...");
				SyncDemo6.flag = false;
				Thread.sleep(1000*10);
				System.out.println(name+"准备唤醒对方线程："+lock);
				condition26.signal();
				lock.unlock(); //在发出唤醒其他线程之后，必须在手动释放锁。因为唤醒不会有释放锁的动作
				System.out.println(name+"唤醒对方线程完成："+lock);
				
					
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}

	class Test26 implements Runnable {
		private Lock lock;
		private Condition condition16;
		private Condition condition26;

		Test26(Lock lock ,Condition condition16, Condition condition26) {
			this.condition16 = condition16;
			this.condition26 = condition26;
			this.lock = lock;
		}

		@Override
		public void run() {
			try {
				Thread currentThread = Thread.currentThread();
				String name = currentThread.getName();
				
				while(true)
				{
					System.out.println(name+"准备加锁："+lock);
					lock.lock();
					System.out.println(name+"加锁完成："+lock);
					while(SyncDemo6.flag)
					{
						System.out.println(name+"开始等待："+lock);
						condition26.await();
						System.out.println(name+"被唤醒，等待结束："+lock);
					}
				
					System.out.println("22222222222");
					SyncDemo6.flag = true;
					Thread.sleep(1000*5);
					System.out.println(name+"准备唤醒对方线程："+lock);
					condition16.signal();
					lock.unlock();
					System.out.println(name+"唤醒对方线程完成："+lock);
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally {
				lock.unlock();
			}

		}
}

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

public class SyncDemo5 {

	static boolean flag = true;

	public static void main(String[] args) throws InterruptedException {
		Lock lock = new ReentrantLock();
		Condition condition = lock.newCondition();
		Test1 target = new Test1(lock, condition);
		Thread thread = new Thread(target);

		Test2 target2 = new Test2(lock, condition);
		Thread thread2 = new Thread(target2);
		thread.start();
		Thread.sleep(1000*9);
		thread2.start();
		
		System.out.println(thread.getPriority());
		System.out.println(thread2.getPriority());
		System.out.println(Thread.currentThread().getPriority());

	}

}

class Test1 implements Runnable {
	private Lock lock;
	private Condition condition;

	Test1(Lock lock, Condition condition) {
		this.condition = condition;
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
				while (!SyncDemo5.flag) 
				{
					System.out.println(name+"开始等待："+lock);
					condition.await();
					System.out.println(name+"被唤醒，等待结束："+lock);
				} 
				
				System.out.println("do some things...");
				SyncDemo5.flag = false;
				Thread.sleep(1000*10);
				System.out.println(name+"准备唤醒对方线程："+lock);
				condition.signal();
//				lock.unlock(); //在发出唤醒其他线程之后，必须在手动释放锁。因为唤醒不会有释放锁的动作
				System.out.println(name+"唤醒对方线程完成："+lock);
				
					
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}

	class Test2 implements Runnable {
		private Lock lock;
		private Condition condition;

		Test2(Lock lock ,Condition condition) {
			this.condition = condition;
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
					while(SyncDemo5.flag)
					{
						System.out.println(name+"开始等待："+lock);
						condition.await();
						System.out.println(name+"被唤醒，等待结束："+lock);
					}
				
					System.out.println("22222222222");
					SyncDemo5.flag = true;
					Thread.sleep(1000*5);
					System.out.println(name+"准备唤醒对方线程："+lock);
					condition.signal();
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

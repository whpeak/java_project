package com.pyjava.thread.relationship.sync;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者消费者demo，两种条件的唤醒
 * @ClassName: GSDemo
 * @author wangheng8
 * @date: 2018年9月30日 上午9:46:33
 * 
 */

public class GSDemo {

	private ReentrantLock lock = new ReentrantLock();
	private Condition addCondition = lock.newCondition();
	private Condition subCondition = lock.newCondition();

	public void runThread() throws InterruptedException {
		AddTask addTask = new AddTask();
		SubTask subTask = new SubTask();

		Thread addThread = new Thread(addTask, "addThread");
		Thread subThread = new Thread(subTask, "subThread");
		System.out.println("----------------------------------");
		addThread.start();
		subThread.start();
		
		
		StatusTask statusTask = new StatusTask(addThread, subThread);
		Thread statusThread = new Thread(statusTask, "statusThread");
		
		
		statusThread.start();

	}
	
	
	public static void main(String[] args) throws InterruptedException {

		new GSDemo().runThread();
	}
	
	
	
	class StatusTask implements Runnable
	{
		private Thread addThread;
		private Thread subThread;
		StatusTask(Thread addThread,Thread subThread)
		{
			this.addThread = addThread;
			this.subThread = subThread;
		}
		@Override
		public void run() {
			while(true)
			{
				try {
					Thread.sleep(1000*1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(addThread.getName()+"------"+addThread.getState());
				System.out.println(subThread.getName()+"------"+subThread.getState());
			}
			
		}
	}
	

	private static class LongNum {
		private static long longNum = 10;

		public static long getLongNum() {
			return longNum;
		}

		public static void setLongNum(long longNum1) {
			System.out.println(Thread.currentThread().getName() + "准备变更数字");
			longNum = longNum1;
		}

		@Override
		public String toString() {
			return "LongNum [longNum=" + longNum + "]";
		}
	}

	class AddTask implements Runnable {
		private long addValue = 0;
		private Random random = new Random();
		private boolean flag = true;

		@Override
		public void run() {
			try {
				Thread thread = Thread.currentThread();
				while (flag) { //持续工作的线程，需要在while中
					System.out.println(thread.getName() + "准备加锁" + lock);
					lock.lock();//每一次的工作，都要获取lock
					System.out.println(thread.getName() + "加锁完成" + lock);
					while (LongNum.getLongNum() > 0) {//循环判断是否需要等待，如果等待，会释放锁lock
						System.out.println(thread.getName() + "开始等待" + lock);
						addCondition.await(); 
					}
					//进行业务逻辑。。。。。
					System.out.println(thread.getName() + "被唤醒" + lock);
					int temp = random.nextInt(10);
					addValue += temp;
					LongNum.setLongNum(LongNum.getLongNum() + temp);
					if (LongNum.getLongNum() > 5) {
						//停止线程的工作，跳出循环状态更改。。。。。
						System.out.println("!!!!!!!!!!!!!!终止加动作，累加数量：【" + addValue + "】临时数字为：【" + temp + "】临界 数字为：【"
								+ LongNum.getLongNum());
						flag = false; 
					} else {
						System.out.println(
								"完成加动作，累加数量：【" + addValue + "】临时数字为：【" + temp + "】临界 数字为：【" + LongNum.getLongNum());
					}
					Thread.sleep(1000 * 5);
					System.out.println(thread.getName() + "准备唤醒减线程" + lock);
					subCondition.signalAll(); //唤醒其他等待的线程，此时此刻，当前线程开不会释放锁，除非显示调用lock.unlock();
					lock.unlock();
					System.out.println(thread.getName() + "唤醒减线程完成" + lock);
				}
			} catch (Exception e) {
			} finally {
				//放置出现异常的时候，调用unlock 。前提是锁是被当前对象所持有。
				boolean isLock = lock.isLocked();
				boolean lockBySelf = lock.isHeldByCurrentThread();
				System.out.println(Thread.currentThread().getName() + " isLock:" + isLock);
				System.out.println(Thread.currentThread().getName() + " lockBySelf:" + lockBySelf);
				if (lockBySelf) {
					lock.unlock();
				}
			}

		}

	}

	class SubTask implements Runnable {
		private long subValue = 0;
		private Random random = new Random();
		boolean flag = true;

		@Override
		public void run() {
			try {
				Thread thread = Thread.currentThread();
				while (flag) {
					System.out.println(thread.getName() + "准备加锁" + lock);
					lock.lock();
					System.out.println(thread.getName() + "加锁完成" + lock);
					while (LongNum.getLongNum() <= 0) {
						System.out.println(thread.getName() + "开始等待" + lock);
						subCondition.await();
					}
					System.out.println(thread.getName() + "被唤醒" + lock);
					int temp = random.nextInt(10);
					subValue += temp;
					LongNum.setLongNum(LongNum.getLongNum() - temp);
					if (LongNum.getLongNum() < -5) {
						System.out.println("!!!!!!!!!!!!!!终止减动作，累减数量：【" + subValue + "】临时数字为：【" + temp + "】临界数字为：【"
								+ LongNum.getLongNum());
						flag = false;
					} else {
						System.out.println(
								"完成减动作，累减数量：【" + subValue + "】临时数字为：【" + temp + "】临界数字为：【" + LongNum.getLongNum());
					}
					Thread.sleep(1000 * 5);
					System.out.println(thread.getName() + "准备唤醒加线程" + lock);
					addCondition.signalAll();
					lock.unlock();
					System.out.println(thread.getName() + "唤醒加线程完成" + lock);
				}
			} catch (Exception e) {

			} finally {
				boolean isLock = lock.isLocked();
				boolean lockBySelf = lock.isHeldByCurrentThread();
				System.out.println(Thread.currentThread().getName() + " isLock:" + isLock);
				System.out.println(Thread.currentThread().getName() + " lockBySelf:" + lockBySelf);
				if (lockBySelf) {
					lock.unlock();
				}
			}

		}
	}
}

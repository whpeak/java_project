package com.pyjava.thread.level3;

/**
 * @ClassName: ThreadDemo3
 * @author wangheng8
 * @date: 2018年9月29日 上午10:23:07

 */

public class ThreadDemo3 {
	
	
	
public static void main(String[] args) throws InterruptedException {
	Thread thread1 = new Thread(new MyTask());
	Thread thread2 = new Thread(new MyTask());
	System.out.println(thread1.getState());
	thread1.start();
	System.out.println(thread1.getState());
	thread2.start();
	thread1.join();//将该线程加入当前线程，当前线程等待加入线程执行完成才继续执行
	
}
}



class MyTask implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		long fabic = fabic(40);
		System.out.println(Thread.currentThread().getState());
		System.out.println(Thread.currentThread().getName()+" result is :"+fabic);
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	long fabic(int n) {
	    if(n < 0) {
	        throw new NumberFormatException("不能小于0");
	    }
	    if(n == 1 || n == 2) {
	        return 1;
	    }
	    return fabic(n - 1) + fabic(n - 2);
	}


	}

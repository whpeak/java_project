package com.pyjava.thread.level4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName: CallThread
 * @author wangheng8
 * @date: 2018年9月29日 下午2:52:27

 */

public class CallThreadDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> futureTask = new FutureTask<String>(new CallThread());
		Thread thread = new Thread(futureTask);
		thread.start();
		
		System.out.println("开始执行main。。。。。。");
		System.out.println(futureTask.get());
	}

}

class CallThread implements Callable<String>
{

	@Override
	public String call() throws Exception {
		
		String result = "result";
		System.out.println("result");
		Thread.sleep(1000*10);
		return result;
	}
	}

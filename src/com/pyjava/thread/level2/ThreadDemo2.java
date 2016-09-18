package com.pyjava.thread.level2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangheng on 16/9/17.
 */
public class ThreadDemo2 {

    public static void main(String[] args) {
        CreateThreadTask createThreadTask=new CreateThreadTask();
        ExecutorService executorService=createThreadTask.createThread(10);
        executorService.execute(new MyThreadTask());
        executorService.shutdownNow();
    }

}


class MyThreadTask implements Runnable
{

    @Override
    public void run() {
        Thread currnetThread =Thread.currentThread();
        String threadName=currnetThread.getName();
        while (1==1)
        {
            System.out.println(threadName+ " say: hi man");
//            currnetThread.yield();

        }

    }
}

class CreateThreadTask
{
    public ExecutorService createThread(int threadNum)

    {
        ExecutorService executorService= Executors.newFixedThreadPool(threadNum);
        return executorService;
    }
}

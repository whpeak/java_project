package com.pyjava.thread.level1;

import java.util.ArrayList;
import java.util.Date;

/**
 * level1
 * 该部分将的是如何创建一个线程任务类
 * 然后在创建一个thread,用于调度该线程任务类
 *
 * 创建线程的方式 为new
 *
 * 熟悉线程类的方法,join(主线程等待子线程完成),yield(退出cpu,重新竞争)
 *
 * leve2 中使用线程池创建线程,然后在调度线程任务类
 * Created by wangheng on 16/9/16.
 */
public class ThreadDemo
{
    public static void main(String[] args) {

        ThreadDemo createThread =new ThreadDemo();
        CreateThreadTask createThreadTask =new CreateThreadTask();
        int threadNum=5;
        ArrayList<Thread> threadLists =createThreadTask.createThread(threadNum);
        createThread.startAllThread(threadLists);
        System.out.println(new Date());
    }
    public void startAllThread(ArrayList<Thread> threadLists)
    {
        for(Thread thread :threadLists)
        {
            thread.start();
        }

//          python 中的启动自线程,如果子没有执行完,主线程也会退
//          java 中不用强制调用子线程的join方法
//          这点挺好
//        for(Thread thread :threadLists)
//        {
//            try {
//                thread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }

}

class MyThreadTask implements Runnable
{

    @Override
    public void run() {
        Thread currnetThread =Thread.currentThread();
        String threadName=currnetThread.getName();
        while (true)
        {
            System.out.println(threadName+ " say: hi man");
//            currnetThread.yield();

        }

    }
}


class CreateThreadTask
{
    public ArrayList<Thread> createThread(int threadNum)
    {
        ArrayList<Thread> threadLists=new ArrayList<Thread>();

        for (int i=0;i<threadNum;i++)
        {
            threadLists.add(new Thread(new MyThreadTask()));
        }
        return threadLists;

    }
}

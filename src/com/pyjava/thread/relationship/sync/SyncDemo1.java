package com.pyjava.thread.relationship.sync;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 研究线程同步的关系
 *
 * 资源竞争关系(互斥,当前资源中只能被一个线程处理,完成后,其它可以竞争,不涉及线程顺序)
 * 线程之间协作(生产者消费者问题,多个生产者活着消费者之间是互斥,但是生产者和消费者是协作)
 *
 * 不涉及异步(也就是说仅仅触发一个动作,不关心什么时候执行完,比如request请求,服务器就是告诉客户端
 * 我接收到你的信息了,返回ok,至于如何处理请求的逻辑,那就起一个子线程,去处理,异步.ajax.触发动作)
 *
 * Created by wangheng on 16/9/17.
 *
 *
 * 资源竞争关系
 */
public class SyncDemo1 {

    public static void main(String[] args) throws InterruptedException {

        CreateThreadTask createThreadTask=new CreateThreadTask();
        ArrayList<Thread> threadLists =createThreadTask.createThread(1000);
        createThreadTask.startAllThread(threadLists);
//        Thread.sleep(5000);
//        System.out.println(NumberWithSynchronizedMethod.num);
    }

}


class Add1Task implements Runnable
{

    //调用三种类型的task方法
    //目的都是为了保证在临界资源上,只有一个线程在操作
    // (类型1)synchronized同步函数 将无序状态的线程,编程有序的
    // 保证在执行 add1 函数的时候,只有一个线程获取执行的权限.
    //将无序的,不受控制的的线程,在获取资源的时候,是有序的
    // (类型2)可以使用 synchronized 代码块
    //(类型3)上述两种可以随便替换.
    //也可以显式的调用锁. ReentrantLock

    // 上述三种方式,可以保证线程在临界区上是互斥的.但是,并不能指定顺序.线程之间,还有协作
    @Override
    public  void run() {
//        NumberWithSynchronizedMethod.add1();
//        NumberWithSynchronized.add1();
        NumberWithLock.add1();
    }
}


class NumberWithSynchronizedMethod
{
    public static int num=0;


    public static synchronized void add1()
    {
        Thread currentThread= Thread.currentThread();
        String name=currentThread.getName();
        System.out.println(name+" begin to run: "+NumberWithSynchronizedMethod.num);
        NumberWithSynchronizedMethod.num+=1;
        System.out.println(name+" end run: "+NumberWithSynchronizedMethod.num);
    }
}


class NumberWithSynchronized
{
    public static int num=0;
    public static void add1()
    {
        synchronized (NumberWithSynchronized.class)
        {
            Thread currentThread=Thread.currentThread();
            String name=currentThread.getName();
            System.out.println(name+" begin to run "+NumberWithSynchronized.num);
            NumberWithSynchronized.num+=1;
            System.out.println(name+" ended run: "+NumberWithSynchronized.num);
        }

    }
}


class NumberWithLock
{
    public static  int num=0;
    private static Lock lock=new ReentrantLock();
    public static void add1()
    {
        lock.lock();
        try
        {
            Thread currentThread=Thread.currentThread();
            String name=currentThread.getName();
            System.out.println(name+" begin to run "+NumberWithLock.num);
            NumberWithLock.num+=1;
            System.out.println(name+" end to run: " + NumberWithLock.num);
        }
        catch (Exception ex)
        {

        }
        finally {
            lock.unlock();
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
            threadLists.add(new Thread(new Add1Task()));
        }
        return threadLists;

    }

    public void startAllThread(ArrayList<Thread> threadLists)
    {
        for(Thread thread :threadLists)
        {
            thread.start();
        }
    }
}

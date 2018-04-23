package com.pyjava.thread.relationship.sync;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 *
 * Created by wangheng on 16/10/10.
 */
public class SyncDemo3
{
    public static void main(String[] args) {
        Thread wThread3=new Thread(new WriterThread3(),"writerThread");
        Thread rThread3=new Thread(new ReaderThread3(),"readerThread");
        wThread3.start();
        rThread3.start();
    }
}

class Bottle3
{
    private static Lock lock =new ReentrantLock();
    private static Condition emptyCon =lock.newCondition();
    private static Condition fillCon =lock.newCondition();
    private static Integer bottle=0;

    public static Lock getLock()
    {
        return lock;
    }
    public static Condition getEmptyCon()
    {
        return emptyCon;
    }

    public static Condition getFillCon()
    {
        return fillCon;
    }
    public static Integer getBottle()
    {
        return bottle;
    }
    public static void setBottle(int num)
    {
         bottle=num;
    }
    public  static void emptyBottle()
    {
        bottle=0;
    }
}

class WriterTask3
{
    public static void writer(int count)
    {

        System.out.println(".....writerThread enter 1...");
        String threadName=Thread.currentThread().getName();
        Lock lock =Bottle3.getLock();
        Condition empty =Bottle3.getEmptyCon();
        Condition fill =Bottle3.getFillCon();
        lock.lock();
        System.out.println(count+" witer get lock: "+ Bottle3.getBottle());
        System.out.println(".....writerThread enter 2...");
        while (0!=Bottle3.getBottle())
        {
            System.out.println(".....writerThread enter 3...");
            System.out.println(threadName+" "+count +" can not write");
            try
            {
                System.out.println(threadName+" "+count +" wait for empty signal");
                empty.await();
                System.out.println(threadName+" "+count +" rerun from here.............last stop at call await() method");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(".....writerThread enter 4...");
        System.out.println(threadName+" "+count +" can write");
        Bottle3.setBottle(3);
        fill.signal();
        System.out.println(count+" witer relase lock: "+Bottle3.getBottle());
        System.out.println("---------------------");
        lock.unlock();
//        Thread.yield();
    }
}


class ReaderTask3
{
    public static void reader(int count)
    {

        System.out.println(".....readerThread enter 1...");
        String threadName=Thread.currentThread().getName();
        Lock lock =Bottle3.getLock();
        Condition empty =Bottle3.getEmptyCon();
        Condition fill =Bottle3.getFillCon();
        lock.lock();
        System.out.println(count+" reader get lock : "+Bottle3.getBottle());
        System.out.println(".....readerThread enter 2...");
        while (0==Bottle3.getBottle())
        {
            System.out.println(".....readerThread enter 3...");
            System.out.println(threadName+" "+count +" can not reader");
            try
            {
                System.out.println(threadName+" "+count +" wait for fill signal");
                fill.await();
                System.out.println(threadName+" "+count +" rerun from here.............last stop at call await() method");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(".....readerThread enter 4...");
        System.out.println(threadName+" "+count +" can reader");
        Bottle3.emptyBottle();
        empty.signal();
        System.out.println(count+" reader relase lock: "+Bottle3.getBottle());
        System.out.println("---------------------");
        lock.unlock();
//        Thread.yield();
    }
}

class WriterThread3 implements Runnable
{
    @Override
    public void run() {
        int count=0;
        while (count<=100)
        {

            WriterTask3.writer(count);
            count++;
        }
    }
}

class ReaderThread3 implements Runnable
{

    @Override
    public void run()
    {
        int count=0;
        while (count<=100)
        {

            ReaderTask3.reader(count);
            count++;
        }
    }
}


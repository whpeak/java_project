package com.pyjava.thread.relationship.sync;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 *
 * Created by wangheng on 16/10/10.
 */
public class SyncDemo4
{
    public static void main(String[] args)
    {
        WriterThread4 wt= new WriterThread4();
        List<Thread> threadList =new ArrayList<Thread>();
        int threadNum=5;
        for (int i=0;i<threadNum;i++)
        {
            threadList.add(new Thread(wt,"writerThread"+i));
        }

        for (Thread thread:threadList)
        {
            thread.start();
        }

        try {
            Thread.sleep(10000);
            System.out.println("after 20 sec!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(1==1)
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("_____________________________main thread begin to send empty signal........ ");
            Bottle4.getLock().lock();
            Bottle4.emptyBottle();
            Bottle4.getEmptyCon().signalAll();
            Bottle4.getLock().unlock();
            System.out.println("_____________________________main thread end to send empty signal........ ");
        }


    }
}

class Bottle4
{
    private static Semaphore wSemaphore =new Semaphore(3);
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

    public static Semaphore getWSemaphore()
    {
        return  wSemaphore;
    }
}

class WriterTask4
{
    public static void writer()
    {
        String threadName=Thread.currentThread().getName();
        System.out.println(threadName+" begin to run,now try to get wSemaphore........" );
        Semaphore wSemaphore=Bottle4.getWSemaphore();
        try
        {
            wSemaphore.acquire();
            System.out.println(threadName+" now has get wSemaphore........" );
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(threadName+" try to get lock........");
        Lock lock =Bottle4.getLock();
        Condition empty =Bottle4.getEmptyCon();
        Condition fill =Bottle4.getFillCon();
        lock.lock();
        System.out.println(threadName +" get lock: "+ Bottle4.getBottle());
        while (0!=Bottle4.getBottle())
        {
            System.out.println(threadName+" " +" can not write");
            try
            {
                System.out.println(threadName+" " +" wait for empty signal");
                empty.await();
                System.out.println(threadName+" " +" rerun from here.............last stop at call await() method");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(threadName+" " +" can write");
        Bottle4.setBottle(3);
        fill.signal();
        System.out.println(threadName+ " relase lock: "+Bottle4.getBottle());
        lock.unlock();
        System.out.println(threadName+ " relase wSemaphore: "+Bottle4.getBottle()+" @@@@@@@@@@@@@@@@@@@@@@@@@@@");
        wSemaphore.release();

    }
}




class WriterThread4 implements Runnable
{
    @Override
    public void run()
    {
            WriterTask4.writer();
        }
    }



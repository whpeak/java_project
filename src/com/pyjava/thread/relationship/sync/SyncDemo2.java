package com.pyjava.thread.relationship.sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 * 生产者单角色的demo
 * 测试await函数的功能以及被唤醒后下次执行的位置.
 *
 * Created by wangheng on 16/10/10.
 */
public class SyncDemo2
{
    public static void main(String[] args) {
        Thread wThread=new Thread(new WriterThread(),"writerThread");
        wThread.start();
        while(1==1)
        {
            try
            {

                Thread.sleep(5000);
                System.out.println(".......main thread begin to send empty signal after 5sec......");
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            Lock lock =Bottle.getLock();
            lock.lock();
            Bottle.emptyBottle();
            Condition empytCon=Bottle.getEmptyCon();
            empytCon.signal();
            lock.unlock();
        }

    }

}

class Bottle
{
    private static Lock lock =new ReentrantLock();
    private static Condition emptyCon =lock.newCondition();
    private static Condition fillCon =lock.newCondition();
    private static Integer bottle=0;

    public static Lock getLock()
    {
        return Bottle.lock;
    }
    public static Condition getEmptyCon()
    {
        return Bottle.emptyCon;
    }

    public static Condition getFillCon()
    {
        return Bottle.fillCon;
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

class WriterTask
{
    public static void writer(int count)
    {
        System.out.println(".....enter step1...");
        String threadName=Thread.currentThread().getName();
        Lock lock =Bottle.getLock();
        Condition empty =Bottle.getEmptyCon();
        Condition fill =Bottle.getFillCon();
        System.out.println(".....enter step2...");
        lock.lock();
        System.out.println(".....enter step3...");
        /*

        特别特别注意:
        因为await是在while中调用的.
        当竞争条件满足的时候,会从上次挂起的位置继续执行,而不是从函数的第一句执行.
        因此如果使用
        int num =Bottle.getBottle()
        while(0==num)
        这两句话,是有问题的,因为对Bottle类中的数据修改,num不会改变.
        代码在while循环中,使用num作为判断条件,是不对的.
        因此,必须保证条件判断的使用竞争资源本身,而不是对其赋值后的其它的变量,保证临界资源的一手性
        这也充分说明,await会保存现场,然后释放锁,然后挂起.
        满足竞争条件被刺激后,会重新获取锁,然后等待执行,并从上次现场的位置继续执行.

         */
        while (0!=Bottle.getBottle())
        {
            System.out.println(".....enter step4...");
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
        System.out.println(".....enter step5...");
        System.out.println(threadName+" "+count +" can write");
        Bottle.setBottle(3);
        fill.signal();
        lock.unlock();
    }
}

class WriterThread implements Runnable
{

    @Override
    public void run() {
        int count2=0;
        while (count2<=100)
        {
            System.out.println(count2+" witer begin: "+ Bottle.getBottle());
            WriterTask.writer(count2);
            System.out.println(count2+" witer end: "+Bottle.getBottle());
            System.out.println("---------------------");
            count2++;
        }

    }
}




package com.pyjava.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 *
 * Created by wangheng on 16/10/12.
 */
public class ThreadDemoCall2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ExecutorService exec = Executors.newCachedThreadPool();
        ExecutorService exec = Executors.newFixedThreadPool(10);
        List<Future<String>> futureList=new ArrayList<Future<String>>();
        int threadNum=10;
        for(int i=0;i<threadNum;i++)
        {
            futureList.add(exec.submit(new Task(i+"")));
        }
        for (Future<String> fs :futureList )
        {
            System.out.println(fs.get());
        }
        exec.shutdown();
    }
}
class Task implements Callable<String>
{
    private String id=null;
    public Task(String id)
    {
        this.id=id;
    }
    @Override
    public String call() throws Exception {
        String info= this.id+" "+Thread.currentThread().getName();
//        System.out.println(info);
//        Thread.sleep(10000);
        return  info;
    }
}

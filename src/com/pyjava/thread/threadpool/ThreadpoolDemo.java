package com.pyjava.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangheng on 16/10/12.
 */
public class ThreadpoolDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(); //根据提交的任务数执行
        exec=Executors.newFixedThreadPool(10); //固定大小
        exec=Executors.newSingleThreadExecutor(); //按照提交的任务执行
    }
}

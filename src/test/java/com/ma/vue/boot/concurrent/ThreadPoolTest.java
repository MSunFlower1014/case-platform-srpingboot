package com.ma.vue.boot.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 创建一个线程池
 */
public class ThreadPoolTest {
    private static final Integer THREAD_MAX_NUM = 50;
    private static final Integer QUEUE_MAX_NUM = 1000;

    private static LinkedBlockingQueue queue = null;

    private static ThreadPoolExecutor threadPoolExecutor = null;

    static{
        init();
    }

    private static void init(){
        queue = new LinkedBlockingQueue();
        //参数 ： 核心线程数，最大线程数，超时时间，超时时间单位，等待队列，拒绝策略
        threadPoolExecutor = new ThreadPoolExecutor(THREAD_MAX_NUM,THREAD_MAX_NUM,60
                , TimeUnit.SECONDS,queue,new ThreadPoolExecutor.CallerRunsPolicy());
    }

    /**
     * 队列是否大小超过设置值，其本身是一个无界的队列，通过主动校验来防止插入过多任务
     * @return
     */
    public static boolean isQueueWaiting(){
        return QUEUE_MAX_NUM <= queue.size();
    }

    public static void addTask(Runnable runnable){
        //execute无返回值
        //threadPoolExecutor.execute(runnable);
        //submit会将runnable封装成一个FutureTask ，可返回执行结果
        threadPoolExecutor.submit(runnable);
    }
}

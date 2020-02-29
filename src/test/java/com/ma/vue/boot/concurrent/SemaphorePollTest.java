package com.ma.vue.boot.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 线程计数器，可以用来实现pool
 * 指定最大并发执行线程数量
 * 此类参考JDK源码注释
 */
public class SemaphorePollTest {
    private static final Integer MAX_NUM = 100;

    //指定计数器大小和锁是否公平，默认非公平
    private final Semaphore available = new Semaphore(MAX_NUM, true);

    private Object [] items = new Object[MAX_NUM];

    //表示资源是否被使用
    private Boolean [] used = new Boolean[MAX_NUM];

    //获取池中资源，acquire() 计数+1
    public Object getItem() throws InterruptedException {
        //Semaphore中使用令牌被使用数大于MAX_NUM时会阻塞该线程
        available.acquire();
        return getNextAvailableItem();
    }

    //放回资源，成功后 计数-1
    public void putItem(Object x){
        if(markAsUnused(x)){
            available.release();
        }
    }

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_NUM; ++i) {
            if(!used[i]){
                used[i] = true;
                return items[i];
            }
        }
        return null;
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_NUM; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                }else
                    return false;
            }
        }
        return false;
    }
}

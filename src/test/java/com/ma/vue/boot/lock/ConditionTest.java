package com.ma.vue.boot.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过condition 实现一个有界缓冲区
 */
public class ConditionTest {
    private final ReentrantLock lock = new ReentrantLock();

    private final Condition notEmpty = lock.newCondition();

    private final Condition notFull = lock.newCondition();

    final Object [] item = new Object[100];
    //放置元素索引，消耗元素索引，计数器
    int putInx =0 ,takeInx = 0 ,count= 0;

    /**
     * 新增元素，如果缓冲区满了，则进入等待状态
     * @param object
     * @throws InterruptedException
     */
    public void put(Object object) throws InterruptedException {
        lock.lock();
        try{
            while (count == item.length)
                notFull.await();
            item[putInx] = object;
            if(++putInx == item.length)
                putInx = 0;
            ++count;
            notFull.signal();
        }finally {
            lock.unlock();
        }
    }

    /**
     * 消耗元素，如果缓冲区空了，则进入等待状态
     * @return
     * @throws InterruptedException
     */
    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0)
                notEmpty.await();
            Object x = item[takeInx];
            if(++takeInx == item.length)
                takeInx = 0;
            --count;
            notFull.signal();
            return x;
        }finally {
            lock.unlock();
        }
    }
}

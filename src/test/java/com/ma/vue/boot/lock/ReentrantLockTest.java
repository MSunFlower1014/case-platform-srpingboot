package com.ma.vue.boot.lock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    //可重入锁,必须手动释放锁
    private final ReentrantLock reentrantLock = new ReentrantLock();

    private final ExecutorService service  = Executors.newCachedThreadPool();

    private static int sum = 0;

    //线程计数器
    private static CountDownLatch latch = new CountDownLatch(10);
    @Test
    public void reentrantLockTest(){
        for(int i=0;i<10;i++){
            service.submit(() -> {
                for(int j=0;j<10;j++){
                    reentrantLock.lock();
                    try{
                        sum ++;
                        reentrantLock.lock();
                        try{
                            sum ++;
                        }finally {
                            reentrantLock.unlock();
                        }
                    }finally {
                        reentrantLock.unlock();
                    }
                }
                //每执行完一个线程就将latch减一
                latch.countDown();
            });
        }
        try {
            latch.await();//会等待至计数器为0被唤醒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }
}

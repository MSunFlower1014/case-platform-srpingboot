package com.ma.vue.boot.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWorker implements Runnable {
    /**
     * 类似于CountDownLatch 可提供线程计数功能，但可多次使用，每当线程计数达到时会唤醒await线程
     * 底层通过ReentrantLock加锁实现线程安全
     */
    private CyclicBarrier cyclicBarrier;

    public CyclicBarrierWorker(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            cyclicBarrier.await();
            System.out.println("子线程已经就绪");
            System.out.println(Thread.currentThread());
            cyclicBarrier.await();
            System.out.println("再次就绪");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int THREAD_NUM =  10;
        List<Thread> threadList = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM);
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new CyclicBarrierWorker(cyclicBarrier)).start();
        }
        for(Thread t : threadList){
            t.join();
        }
    }
}

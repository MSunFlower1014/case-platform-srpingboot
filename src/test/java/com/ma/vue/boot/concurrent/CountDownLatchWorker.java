package com.ma.vue.boot.concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchWorker implements Runnable{

    private final CountDownLatch doneSignal;
    private final CountDownLatch startSignal;

    public CountDownLatchWorker(CountDownLatch startSignal, CountDownLatch doneSignal){
        this.doneSignal = doneSignal;
        this.startSignal = startSignal;
    }

    @Override
    public void run() {
        try{
            //等待开始
            startSignal.await();
            System.out.println("收到开始执行指令，开始执行");
            doneSignal.countDown();
            System.out.println("执行结束，计数器减一");
        }catch (InterruptedException ex){
            System.out.println(ex);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        //开始控制线程计数器
        CountDownLatch startSignal = new CountDownLatch(1);
        //结束线程计数器
        CountDownLatch doneSignal = new CountDownLatch(10);

        for (int i = 0; i < 10; i++) {
            new Thread(new CountDownLatchWorker(startSignal, doneSignal)).start();
        }

        System.out.println("准备工作");
        //doSomeThing 之后唤醒线程
        startSignal.countDown();
        //等待子线程执行结束后被唤醒
        doneSignal.await();
        System.out.println("线程都执行结束了");
    }
}
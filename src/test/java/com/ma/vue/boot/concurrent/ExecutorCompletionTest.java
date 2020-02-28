package com.ma.vue.boot.concurrent;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class ExecutorCompletionTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int sum = 0;
        int THREAD_NUM = 10;
        ExecutorCompletionService<Integer> ecs = new ExecutorCompletionService<>(Executors.newFixedThreadPool(5));

        for (int i = 0; i < THREAD_NUM; i++) {
            ecs.submit((Callable<Integer>) () -> {
                int randomInt = new Random().nextInt(10);
                System.out.println("随机数为"+randomInt);
                Thread.sleep(randomInt*100);
                return randomInt;
            });
        }
        for (int i = 0; i < THREAD_NUM; i++) {
            //take方法会阻塞的获取ExecutorCompletionService中FIFO队列的值
            Integer integer = ecs.take().get();
            sum = sum + integer;
            System.out.println("执行结束"+(i+1)+"个线程，当前sum = "+ sum);
        }
        System.out.println("最终结果sum = " + sum);
    }
}

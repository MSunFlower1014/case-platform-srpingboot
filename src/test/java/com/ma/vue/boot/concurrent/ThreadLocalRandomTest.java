package com.ma.vue.boot.concurrent;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 在多线程下，使用 java.util.Random 产生的实例来产生随机数是线程安全的，但深挖 Random 的实现过程，会发现多个线程会竞争同一 seed 而造成性能降低。
 * ThreadLocalRandom会在每个线程中维护一个自己的 Random 对象
 */
public class ThreadLocalRandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        System.out.println(Thread.currentThread().getName()+"  random = "+ThreadLocalRandom.current().nextInt(100));
                    }
                }
            }).start();
        }
    }
}

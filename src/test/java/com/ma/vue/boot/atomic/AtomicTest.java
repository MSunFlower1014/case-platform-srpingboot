package com.ma.vue.boot.atomic;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

public class AtomicTest {
    private static AtomicInteger atomicInteger= new AtomicInteger();//对于int的原子操作类

    private static LongAdder longAdder = new LongAdder();//从0开始的对于long的原子累加器

    private static LongAccumulator longAccumulator = new LongAccumulator((x,y) -> x+y,0);//原始值为0 相加

    private static LongAccumulator longAccumulator4multiplication = new LongAccumulator((x,y) -> x*y,1);//原始值为0 相乘

    @Test
    public void testAtomic(){
        ExecutorService service  = Executors.newCachedThreadPool();
        for(int i = 0 ;i < 20 ; i++){
            service.submit(() -> {
                int randomInt = new Random().nextInt(100);
                int andSet = atomicInteger.getAndSet(randomInt);
                System.out.println("atomicInteger.getAndSet old value "+andSet+" new value " +randomInt);
                //结果为10个20相加为200
                longAdder.add(10);
                System.out.println("longAdder value : "+longAdder.sum());
                //结果为 20个2相加 为40
                longAccumulator.accumulate(2);
                System.out.println("longAccumulator accumulate value : " + longAccumulator.get());
                //结果为 2的10次方1048576
                longAccumulator4multiplication.accumulate(2);
                System.out.println("longAccumulator4multiplication accumulate value : " + longAccumulator4multiplication.get());
            });
        }
        service.shutdown();
    }
}

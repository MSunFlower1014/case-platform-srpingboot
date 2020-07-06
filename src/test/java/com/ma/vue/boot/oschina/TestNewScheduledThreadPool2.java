package com.ma.vue.boot.oschina;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 开源中国问题-锁的使用
 * @author zy_mayantao
 * @date 2020/6/24 9:53
 */
public class TestNewScheduledThreadPool2 {
    public volatile static int change=0;


    public  static volatile int [] array=new int [2000];

    public static void main(String[] args) {
        //TestNewScheduledThreadPool2.change=0;
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2000);
        for(int i=0;i<2000;i++){
            service.execute(new ChangeTread2());
        }
        service.shutdown();
        try {
            service.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for(int i=0;i<array.length;i++) {
            for(int j=i+1;j<array.length;j++) {
                if(array[i]<array[j]) {
                    int temp=array[i];
                    array[i]=array[j];
                    array[j]=temp;
                }
            }
        }

        for(int i=0;i<array.length;i++) {
            System.out.println(array[i]);
        }


    }
}

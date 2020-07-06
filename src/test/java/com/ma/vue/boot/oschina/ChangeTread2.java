package com.ma.vue.boot.oschina;

/**
 * @author zy_mayantao
 * @date 2020/6/24 9:53
 */
public class ChangeTread2 implements Runnable{
    @Override
    public void run() {
        synchronized (TestNewScheduledThreadPool2.class) {
            TestNewScheduledThreadPool2.change++;
            //TestNewScheduledThreadPool2.recodeList.add(TestNewScheduledThreadPool2.change);
            TestNewScheduledThreadPool2.array[TestNewScheduledThreadPool2.change-1]=TestNewScheduledThreadPool2.change;
            //System.out.println(TestNewScheduledThreadPool2.change-1+"***********************"+TestNewScheduledThreadPool2.change);
        }
    }
}

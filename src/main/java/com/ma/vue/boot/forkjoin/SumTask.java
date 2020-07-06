package com.ma.vue.boot.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * fork join 计算数字和，继承于 RecursiveTask，与RecursiveAction相比可以获取返回值
 */
public class SumTask extends RecursiveTask<Integer> {
    private static final int MIN_NUM = 10;

    private final List list;

    public SumTask(List list) {
        this.list = list;
    }

    /**
     *执行方法
     * @return
     */
    @Override
    protected Integer compute() {
        //是否大于指定值，大于则分治
        if (list.size() <= MIN_NUM)
            return getSum(list);
        SumTask rightSumTask = new SumTask(list.subList(0, list.size() / 2));
        rightSumTask.fork();
        SumTask leftSumTask = new SumTask(list.subList(list.size() / 2, list.size()));
        return leftSumTask.compute() + rightSumTask.join();
    }

    private Integer getSum(List<Integer> list) {
        Integer sum = 0;
        for (Integer i : list) {
            sum = sum + i;
        }
        return sum;
    }


}

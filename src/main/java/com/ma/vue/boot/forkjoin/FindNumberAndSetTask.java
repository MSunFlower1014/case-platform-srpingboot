package com.ma.vue.boot.forkjoin;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.RecursiveTask;

/**
 * @author zy_mayantao
 * @date 2020/9/7 17:37
 */
public class FindNumberAndSetTask extends RecursiveTask<Map> {
    private static final ConcurrentSkipListSet<Integer> concurrentSkipListSet = new ConcurrentSkipListSet();

    public FindNumberAndSetTask(List<Integer> list){

    }

    @Override
    protected Map compute() {
        return null;
    }

    public void execute(){

    }
}

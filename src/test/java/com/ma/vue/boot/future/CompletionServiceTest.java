package com.ma.vue.boot.future;

import org.junit.Test;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ExecutorCompletionService 更好得获取异步执行结果
 * 维护 BlockingQueue<Future<V>> ，当有线程执行结束时，将响应得future添加到队列中，通过take获取
 * 内部自定义future，重写了done方法，默认FutureTask中done方法为空实现
 *     private class QueueingFuture extends FutureTask<Void> {
 *         QueueingFuture(RunnableFuture<V> task) {
 *             super(task, null);
 *             this.task = task;
 *         }
 *         protected void done() { completionQueue.add(task); }
 *         private final Future<V> task;
 *     }
 *
 * @author zy_mayantao
 * @date 2020/8/13 11:03
 */
public class CompletionServiceTest {
    private static volatile AtomicInteger sleepTime = new AtomicInteger(8);

    /**
     * 使用第一个非空结果
     * 忽略所有遇到异常的任务，
     * 当第一个任务就绪时取消所有其他任务
     * 此例中，当Thread.sleep(5000) 线程执行结束后会停止其他Thread.sleep(10000)线程
     */
    @Test
    public void getFirstResultCancelOther() {
        ExecutorService executor = Executors.newFixedThreadPool(6);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        List<Future<String>> list = new ArrayList<>();
        String result = "";
        try {
            for (int i = 0; i < 5; i++) {
                Future<String> submit = completionService.submit(() -> {

                    Thread.sleep(10000);
                    System.out.println(Thread.currentThread().getName() + "execute end ");
                    return Thread.currentThread().getId() + " thread time = " + 10000;
                });
                list.add(submit);
            }
            Future<String> submit = completionService.submit(() -> {

                Thread.sleep(5000);
                System.out.println(Thread.currentThread().getName() + " execute end ");
                return Thread.currentThread().getId() + " thread time = " + 5000;
            });
            list.add(submit);
            for (int i = 0; i < 5; i++) {
                String s = completionService.take().get();
                //获取第一个执行成功得线程结果，不为空则停止其他所有线程
                if (!StringUtils.isEmpty(s)) {
                    result = s;
                    break;
                }
            }
        } catch (Exception e) {

        } finally {
            for (Future<String> future : list) {
                future.cancel(true);
            }
        }
        System.out.println(result);
    }

    /**
     * ExecutorCompletionService 会优先获取已完成线程执行结果，而直接使用Future会因之前得线程未完成堵塞。
     * 输出结果为：
     * 16 thread time = 3000
     * 14 thread time = 4000
     * 15 thread time = 5000
     * 13 thread time = 6000
     * 12 thread time = 7000
     * completionService.take() 会返回先执行结束得线程执行结果
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void completionServiceTest() throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        for (int i = 0; i < 5; i++) {
            completionService.submit(() -> {
                final int localTime = sleepTime.decrementAndGet();
                Thread.sleep(localTime * 1000);
                return Thread.currentThread().getId() + " thread time = " + localTime * 1000;
            });
        }

        for (int i = 0; i < 5; i++) {
            String s = completionService.take().get();
            System.out.println(s);
        }
    }

    /**
     * 此测试结果未
     * 12 thread time = 8000
     * 13 thread time = 6000
     * 14 thread time = 6000
     * 15 thread time = 4000
     * 15 thread time = 2000
     * 后续线程虽然执行只需要2秒，但由于第一个线程阻塞8秒钟，导致future获取结果阻塞。
     * 结果输出顺序为future添加到list中得顺序
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void executorTest() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> submit = executor.submit(() -> {
                final int localTime = sleepTime.decrementAndGet();
                Thread.sleep(localTime * 1000);
                return Thread.currentThread().getId() + " thread time = " + localTime * 1000;
            });
            list.add(submit);
        }

        for (Future<String> future : list) {
            String s = future.get();
            System.out.println(s);
        }
    }
}

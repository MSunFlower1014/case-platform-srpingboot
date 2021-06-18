package com.ma.vue.boot.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * executors的各种线程池 笔记
 * 线程池ThreadPoolExecutor参数
 * ThreadPoolExecutor(int corePoolSize,
 *                               int maximumPoolSize,
 *                               long keepAliveTime,
 *                               TimeUnit unit,
 *                               BlockingQueue<Runnable> workQueue)
 * corePoolSize : 表示线程池核心线程数，当初始化线程池时，会创建核心线程进入等待状态，即使它是空闲的，
 * 核心线程也不会被摧毁，从而降低了任务一来时要创建新线程的时间和性能开销。
 * maximumPoolSize : 表示最大线程数，意味着核心线程数都被用完了，且等待队列已经满了，那只能重新创建新的线程来执行任务，如果达到最大线程数，会执行拒绝策略
 * keepAliveTime : 表示线程存活时间，除了核心线程外，那些被新创建出来的线程可以存活多久。意味着，这些新的线程一但完成任务，
 * 而后面都是空闲状态时，就会在一定时间后被摧毁。
 * unit : 存活时间单位，没什么好解释的，一看就懂。
 * workQueue : 表示任务的阻塞队列，由于任务可能会有很多，而线程就那么几个，所以那么还未被执行的任务就进入队列中排队，
 * 队列我们知道是 FIFO 的，等到线程空闲了，就以这种方式取出任务。这个一般不需要我们去实现。
 * 拒绝策略： 抛出 RejectedExecutionException 异常，直接丢弃任务，丢弃队列中最老的任务并添加新任务（pop），直接在当前线程中执行该任务
 */
public class ExecutorsNote {

    /**
     * 内部通过创建ThreadPoolExecutor创建
     * new ThreadPoolExecutor(nThreads, nThreads,
     *      0L, TimeUnit.MILLISECONDS,
     *      new LinkedBlockingQueue<Runnable>());
     * 线程池的核心线程数和最大线程数都由参数 nThreads 指定
     * 超出线程池最大限制的线程将被添加到一个线程安全的FIFO的无边界队列
     * 由于队列无边界，过长的队列可能会造成内存溢出
     */
    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 内部通过创建ThreadPoolExecutor创建
     * new ThreadPoolExecutor(1, 1,
     *      0L, TimeUnit.MILLISECONDS,
     *      new LinkedBlockingQueue<Runnable>()));
     * 与newFixedThreadPool类似，不过核心线程数和最大线程数都为1
     * 由于队列无边界，过长的队列可能会造成内存溢出
     */
    final ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

    /**
     * 内部通过创建ThreadPoolExecutor创建
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *      60L, TimeUnit.SECONDS,
     *      new SynchronousQueue<Runnable>());
     * 核心线程数为0，最大线程数为Integer.MAX_VALUE（会不断的创建新的线程，从而导致内存溢出）
     * 超出队列为SynchronousQueue，一个内部只能包含一个元素的队列。插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素
     */
    final ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

    /**
     * 内部通过创建ScheduledThreadPoolExecutor创建
     * 核心线程数和最大线程数为1
     * 通过DelayedWorkQueue实现定时执行，核心线程不断从DelayedWorkQueue获取达到指定时间的线程执行
     * 超出队列无界v,过大会导致内存溢出
     */
    final ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    /**
     * 内部通过创建ScheduledThreadPoolExecutor创建
     * 可以指定核心线程和最大线程的定时执行线程池
     * 实现原理与singleThreadScheduledExecutor相同
     * 超出队列无界v,过大会导致内存溢出
     */
    final ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);

    /**
     * 内部通过ForkJoinPool创建，默认线程数为CPU个数
     * 是一个工作窃取线程池
     * 适合使用在很耗时的操作，当部分线程执行结束时会窃取未完成线程任务进行执行
     */
    final ExecutorService workStealingPool = Executors.newWorkStealingPool();
}

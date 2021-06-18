package com.ma.vue.boot.concurrent;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * 对线程安全工具类的一些笔记，主要时一些队列或者Map
 */
public class QueueOrMapClassNote {
    /**
     * 一个线程安全的有界队列 由数组实现
     * 通过put和offer插入新元素，通过poll和take消费元素
     * 当队列满了，offer 添加元素时返回false，put 新元素时线程会调用锁notFull的await阻塞进程
     * 当队列为空时，poll会返回null，take会调用锁notEmpty的await阻塞进程
     */
    private final ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(20);

    /**
     * 线程安全的HashMap
     * 通过synchronized node保证单个node的线程安全
     * 默认容量16，当主动设置容量时会转换为2的次方
     * 使用hash来提升效率，当hash膨胀时使用链表法，当链长度大于等于8，会将链表转为红黑树，用于提升效率。
     * 红黑树优点：在最坏情况运行时间也是非常良好的，并且在实践中是高效的： 它可以在O(log n)时间内做查找，插入和删除，这里的n 是树中元素的数目
     *  AVL（高度平衡树）是严格平衡树，时间效能针对读取而言更高；维护稍慢，空间开销较大，在增加或者删除节点的时候，根据不同情况，旋转的次数比红黑树要多。
     *  RB-Tree（红黑数）读取略逊于AVL，维护强于AVL，空间开销与AVL类似，内容极多时略优于AVL，维护优于AVL。
     *  若搜索的次数远远大于插入和删除，那么选择AVL，如果搜索，插入删除次数几乎差不多，应该选择RB。
     *  当ConcurrentHashMap在进行扩容时，竞争线程会一同帮助进行扩容操作
     *  HashMap中的元素是无序的，与插入时的顺序无关
     *
     *  size 方法通过计算baseCount和CounterCell数组的总和得出，在添加元素时默认通过CAS操作baseCount，失败时依次CAS操作CounterCell数组以增加计数
     *  将map的大小通过多个元素相加得出，减小了多线程下对于size的竞争，提高了吞吐量（JDK1.7之前 会遍历两次map，当得出size不同时，锁住整个MAP再次计算）
     */
    private final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

    /**
     * 一个线程安全的非阻塞无界队列 由链表实现，通过CAS保证线程安全
     * 批量处理方法不保证原子性 如addAll
     * 添加元素在链表头部： addFirst() offerFirst()  两个方法实现一样
     * 添加元素在链表尾部： addLast() offerLast() 两个方法实现一样
     * getFirst() 获取链头元素，无操作
     * getLast() 获取链尾元素，无操作
     * pollFirst() 获取链头元素，删除该元素
     * pollLast() 获取链尾元素，删除该元素
     * offer(E e) add(E e) 在链尾添加元素，调用offerLast()
     * remove() pop() 获取链头元素并删除，调用pollFirst()
     * 判断容量是否为空时使用 isEmpty() {
     *         return peekFirst() == null;
     *     }
     * 因为size方法会遍历整个链表，效率较低
     */
    private final ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();

    /**
     * 由跳表实现的线程安全的map
     *     * Head nodes          Index nodes
     *      * +-+    right        +-+                      +-+
     *      * |2|---------------->| |--------------------->| |->null
     *      * +-+                 +-+                      +-+
     *      *  | down              |                        |
     *      *  v                   v                        v
     *      * +-+            +-+  +-+       +-+            +-+       +-+
     *      * |1|----------->| |->| |------>| |----------->| |------>| |->null
     *      * +-+            +-+  +-+       +-+            +-+       +-+
     *      *  v              |    |         |              |         |
     *      * Nodes  next     v    v         v              v         v
     *      * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+
     *      * | |->|A|->|B|->|C|->|D|->|E|->|F|->|G|->|H|->|I|->|J|->|K|->null
     *      * +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-+  +-
     *  JDK注释图如上，通过建立多级索引来增加查询速度，空间换时间
     *  优点：
     *  key值是有序的
     *  存取时间是log（N），和线程数几乎无关，即并发线程越高，性能相对更有优势
     */
    private final ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();

    /**
     * 内部通过 ConcurrentSkipListMap 实现，与set类似，map的value值唯一
     */
    private final ConcurrentSkipListSet concurrentSkipListSet = new ConcurrentSkipListSet();

    /**
     * 一个线程安全的ArrayList
     * 通过ReentrantLock来实现线程安全
     * 插入或者更新元素时会 创建副本操作之后替换旧版，不影响读取操作（即读操作无锁，写操作加锁）
     */
    private final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

    /**
     * 内部维护一个CopyOnWriteArrayList实现
     * 不能存储重复的元素，所有add方法调用copyOnWriteArrayList的addIfAbsent方法
     */
    private final CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();

    /**
     * 一个线程安全的从小到大的无界延迟队列，通过ReentrantLock实现线程安全
     * 放入元素必须实现 implements Delayed 接口，Delayed继承于Comparable接口，即实现compareTo和getDelay方法
     * poll方法会验证队列头元素的延迟是否到期，如果未到期返回null，到期则消耗该元素（如果头元素设置延迟时间过长，无法获取头元素后到期元素）
     */
    private final DelayQueue delayQueue = new DelayQueue();

    /**
     * 可指定公平性，FIFO或者LIFO
     * 是一个内部只能包含一个元素的队列。插入元素到队列的线程被阻塞，直到另一个线程从队列中获取了队列中存储的元素。
     * 同样，如果线程尝试获取元素并且当前不存在任何元素，则该线程将被阻塞，直到线程将元素插入队列。
     * 队列内部不会存储元素，所以尽量避免使用add,offer此类立即返回的方法
     * 参考博文 ： https://www.jianshu.com/p/d5e2e3513ba3
     */
    private final SynchronousQueue synchronousQueue = new SynchronousQueue();

    /**
     * 线程安全的优先队列，通过堆排序实现
     */
    private final PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue<>();
}

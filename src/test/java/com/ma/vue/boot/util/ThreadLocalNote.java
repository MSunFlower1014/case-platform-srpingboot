package com.ma.vue.boot.util;

public class ThreadLocalNote {
    /**
     * 内部通过Thread维护的map保存当前线程所有threadLocal，节点使用弱引用，所以key可能被回收，到value无法回收，导致内存泄漏，使用后需要及时remove清空
     */
    private static final ThreadLocal<String> local = new ThreadLocal<>();
}

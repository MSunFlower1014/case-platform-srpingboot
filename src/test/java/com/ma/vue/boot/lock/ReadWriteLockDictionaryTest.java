package com.ma.vue.boot.lock;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 用来实现并发的集合
 * 当集合预期很大，读线程比写线程多 推荐使用
 */
public class ReadWriteLockDictionaryTest {
    private final Map<String, Object> map = new TreeMap<String, Object>();
    //读写锁
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public Object getObject(String key) {
        Object o = null;
        r.lock();
        try {
            o = map.get(key);
        } finally {
            r.unlock();
        }
        return o;
    }

    public Object[] getAllKeys() {
        r.lock();
        try {
            return map.keySet().toArray();
        } finally {
            r.unlock();
        }
    }

    public Object put(String key, Object o) {
        w.lock();
        try {
            return map.put(key, o);
        } finally {
            w.unlock();
        }
    }

    public void clear() {
        w.lock();
        try {
            map.clear();
        } finally {
            w.unlock();
        }
    }
}

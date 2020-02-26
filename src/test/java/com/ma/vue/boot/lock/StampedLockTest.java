package com.ma.vue.boot.lock;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {
    private  long sum;
    //读写锁，读锁可由乐观锁升级为悲观锁，进一步提升性能，但不可重入
    private final StampedLock sl = new StampedLock();

    public void move(long sum){
        long stamp = sl.writeLock();//返回一个版本号
        try {
            this.sum = sum;
        }finally {
            sl.unlockWrite(stamp);
        }
    }

    /**
     * 默认获取乐观锁增加效率，当版本变化时获取悲观锁保证数据的正确
     * @return
     */
    public long read(){
        //获取一个乐观读锁的版本号
        long stamp = sl.tryOptimisticRead();
        //获取数据
        long sum = this.sum;
        //对比版本是否变化
        if(!sl.validate(stamp)){
            //版本变化时则获取悲观读锁
            stamp = sl.readLock();
            try{
                //获取数据
                sum = this.sum;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return sum;
    }

    /**
     * 默认使用悲观读锁，当无法升级为写锁时再显示获取写锁
     * @param newSum
     */
    public void updateIfAtOrigin(long newSum){
        //获取读锁
        long stamp = sl.readLock();
        try {
            while (sum == 0) {
                //尝试根据版本号将读锁升级为写锁，成功返回版本号，失败返回0
                long ws = sl.tryConvertToWriteLock(stamp);
                if(ws != 0L){
                    //成功则写入值
                    stamp = ws;
                    this.sum = newSum;
                    break;
                }else{
                    //失败则释放读锁，获取写锁
                    sl.unlockRead(stamp);
                    stamp = sl.readLock();
                }
            }
        }finally {
            sl.unlock(stamp);
        }
    }
}

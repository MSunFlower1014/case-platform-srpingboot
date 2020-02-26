package com.ma.vue.boot.lock;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 一个简单的QAS的实现，不可重入互斥锁
 */
public class AQSTest implements Lock, Serializable {

    /**
     * 借助AQS框架实现，重写其中部分方法即可
     */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 返回是被处于被锁状态
         *
         * @return
         */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 如果未加锁则尝试加锁
         *
         * @param acquires
         * @return
         */
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 尝试释放锁
         * @param releases
         * @return
         */
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        Condition newCondition() { return new ConditionObject(); }

        /**
         * 为反序列化提供支持
         * @param s
         * @throws IOException
         * @throws ClassNotFoundException
         */
        private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0);//重置锁状态
        }
    }

    private final AQSTest.Sync sync = new Sync();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return new Sync().newCondition();
    }

    public boolean isLocked()         { return sync.isHeldExclusively(); }

}

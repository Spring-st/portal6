package com.aof.utils;

public class RWLock {
    public static boolean TRACE = false;

    private static ThreadLocal flag = new ThreadLocal();

    private Object mutex = new Object();

    private int givenLocks = 0;

    private int waitingWriters = 0;

    public void getReadLock() {
        synchronized (this.mutex) {
            try {
                while (this.givenLocks == -1 || this.waitingWriters != 0) {
                    if (TRACE)
                        System.out.println(String.valueOf(Thread.currentThread().toString()) + "waiting for readlock");
                    this.mutex.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            this.givenLocks++;
            flag.set("readlock");
            if (TRACE)
                System.out.println(String.valueOf(Thread.currentThread().toString()) + " got readlock, GivenLocks = " + this.givenLocks);
        }
    }

    public void getWriteLock() {
        synchronized (this.mutex) {
            this.waitingWriters++;
            try {
                while (this.givenLocks != 0 && (this.givenLocks != 1 || flag.get() == null)) {
                    if (TRACE)
                        System.out.println(String.valueOf(Thread.currentThread().toString()) + "waiting for writelock");
                    this.mutex.wait();
                }
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            this.waitingWriters--;
            this.givenLocks = -1;
            flag.set(null);
            if (TRACE)
                System.out.println(String.valueOf(Thread.currentThread().toString()) + " got writelock, GivenLocks = " + this.givenLocks);
        }
    }

    public void releaseLock() {
        synchronized (this.mutex) {
            this.mutex.notifyAll();
            if (this.givenLocks == 0)
                return;
            if (this.givenLocks == -1) {
                this.givenLocks = 0;
            } else {
                this.givenLocks--;
                flag.set(null);
            }
            if (TRACE)
                System.out.println(String.valueOf(Thread.currentThread().toString()) + " released lock, GivenLocks = " + this.givenLocks);
        }
    }
}

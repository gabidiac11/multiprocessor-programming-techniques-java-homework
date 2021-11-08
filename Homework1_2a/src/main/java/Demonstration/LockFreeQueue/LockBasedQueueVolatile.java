package Demonstration.LockFreeQueue;

import Business.Verbose;
import Core.ILockFreeQueue;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueueVolatile implements ILockFreeQueue {
    volatile int head = 0, tail = 0;
    private final int QSIZE;
    private final int[] items;
    private final ReentrantLock lock = new ReentrantLock();

    public LockBasedQueueVolatile(int size) {
        items =  new int[size];
        QSIZE = size;
    }

    public void enq(int x, Verbose v) {
        v.Producer_Waiting_Before_While(x, tail, head);

        while ( tail - head == QSIZE ) {};

        v.Producer_about_to_lock(x, tail, head, lock.getHoldCount());

        lock.lock();

        v.Producer_has_locked(x, tail, head, lock.getHoldCount());

        try {
            items [ tail % QSIZE ] = x;
            tail ++;

            v.Producer_has_enqueued(x, tail, head, lock.getHoldCount());
        } finally {
            v.Producer_about_to_Unlock(x, tail, head, lock.getHoldCount());

            lock.unlock();

            v.Producer_has_unlocked(x, tail, head, lock.getHoldCount());
        }
    }

    public int deq (Verbose v) {
        v.Consumer_Waiting_Before_While(tail, head, lock.getHoldCount());
        long t = System.currentTimeMillis() + 30000;

        while ( tail == head ) {};

        v.Consumer_beforeLock(tail, head, lock.getHoldCount());

        lock.lock();

        v.Consumer_had_locked_the_section(tail, head, lock.getHoldCount());

        try {
            int item = items [ head % QSIZE ];
            head ++;

            v.Consumer_Extracted_Item(item, tail, head, lock.getHoldCount());

            return item;
        } finally {
            v.Consumer_about_to_unlock(tail, head, lock.getHoldCount());

            lock.unlock();

            v.Consumer_HAD_unlocked(tail, head, lock.getHoldCount());
        }
    }

    @Override
    public String toString() {
        Vector<Integer> copy = new Vector<>();
        int[] source = items.clone();
        for(int i = head; i < tail; i++) if(i < source.length) copy.add(source[i]);

        return Arrays.toString(copy.toArray());
    }
}

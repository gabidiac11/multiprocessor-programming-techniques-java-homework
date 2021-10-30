package Demonstration.LockFreeQueue;

import Core.ILockFreeQueue;

import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.locks.ReentrantLock;

public class LockBasedQueue implements ILockFreeQueue {
    int head = 0, tail = 0;
    private int QSIZE;
    private final int items[];

    public LockBasedQueue(int size) {
        items =  new int[size];
        QSIZE = size;
    }
    ReentrantLock lock = new ReentrantLock();

    public void enq(int x) {
        while ( tail - head == QSIZE ) {};
        lock.lock();
        try {
            items [ tail % QSIZE ] = x;
            tail ++;
        } finally {
            lock.unlock();
        }
    }

    public int deq () {
        while ( tail == head ) {};
        lock.lock();
        try {
            int item = items [ head % QSIZE ];
            head ++;
            return item;
        } finally {
            lock.unlock();
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

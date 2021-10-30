package Demonstration.LockFreeQueue;

import Core.ILockFreeQueue;

import java.util.Arrays;
import java.util.Vector;

public class LockFreeQueueMeantForOneConsumer implements ILockFreeQueue {
    int head = 0, tail = 0;
    private int qSize = 0;
    private final int items[];

    public LockFreeQueueMeantForOneConsumer(int size) {
        items =  new int[size];
        qSize = size;
    }

    public void enq(int x) {
        while (tail - head == qSize) {};
        items [tail % qSize] = x;
        tail++;
    }

    public int deq () {
        while(tail == head) {};
        int item = items [head % qSize];
        head ++;
        return item;
    }

    @Override
    public String toString() {
        Vector<Integer> copy = new Vector<>();
        int[] source = items.clone();
        for(int i = head; i < tail; i++) if(i < source.length) copy.add(source[i]);

        return Arrays.toString(copy.toArray());
    }
}

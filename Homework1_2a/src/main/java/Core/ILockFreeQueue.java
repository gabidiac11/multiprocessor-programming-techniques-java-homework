package Core;

import Business.Verbose;

public interface ILockFreeQueue {
    void enq(int x, Verbose v);
    int deq(Verbose v);
}

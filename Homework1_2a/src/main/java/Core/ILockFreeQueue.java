package Core;

public interface ILockFreeQueue {
    void enq(int x);
    int deq();
}

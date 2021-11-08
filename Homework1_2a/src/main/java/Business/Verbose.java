package Business;

import java.sql.Timestamp;

public class Verbose {
    private final ThreadEntity th;

    public Verbose(ThreadEntity th) {
        this.th = th;
    }


    public void Producer_Waiting_Before_While(int x, int tail, int head) {
        System.out.printf("[%d] Producer %s waiting before while, with tail %d, head %d, try to push x=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x);
    }

    public void Producer_about_to_lock(int x, int tail, int head, int holdCount) {
        System.out.printf("[%d] Producer %s ABOUT to locks -> tail %d, head %d, try to push x=%d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x, holdCount);
    }

    public void Producer_about_to_Unlock(int x, int tail, int head, int holdCount) {
        System.out.printf("[%d] Producer %s is ABOUT to unlock -> tail %d, head %d, try to push x=%d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x, holdCount);
    }

    public void Producer_has_locked(int x, int tail, int head, int holdCount) {
        System.out.printf("[%d] Producer %s is has LOCKED -> tail %d, head %d, try to push x=%d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x, holdCount);
    }

    public void Producer_has_unlocked(int x, int tail, int head, int holdCount) {
        System.out.printf("[%d] Producer %s has UNLOCKED -> tail %d, head %d, try to push x=%d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x, holdCount);
    }

    public void Producer_has_enqueued(int x, int tail, int head, int holdCount) {
        System.out.printf("[%d] Producer %s has ENQUEUED VALUE -> tail %d, head %d, try to push x=%d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, x, holdCount);
    }

    /** consumer */
    public void Consumer_Waiting_Before_While(int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s waiting before while, with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, holdCount);
    }

    public void Consumer_beforeLock(int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s BEFORE lock! with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, holdCount);
    }

    public void Consumer_had_locked_the_section(int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s has LOCKED! with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, holdCount);
    }

    public void Consumer_Extracted_Item(int item, int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s EXTRACTED item %d with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), item, tail, head, holdCount);
    }

    public void Consumer_about_to_unlock(int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s ABOUT to unlock! with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, holdCount);
    }

    public void Consumer_HAD_unlocked(int tail, int head, int holdCount) {
        System.out.printf("[%d] Consumer %s has UNLOCKED! with tail %d, head %d ------ holdCount=%d \n", System.currentTimeMillis(), th.getMyName(), tail, head, holdCount);
    }
}

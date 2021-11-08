package Business.Command;

import Business.Verbose;
import Core.ICommand;
import Core.ILockFreeQueue;

import java.util.Vector;

public class DequeCommand extends CommandWithSleeping implements ICommand {
    public DequeCommand() {}
    public DequeCommand(int sleepTime) {
        this.sleepTime = sleepTime;
    }

    @Override
    public Integer execute(String thName, ILockFreeQueue queue, Verbose v) throws InterruptedException {
        //System.out.printf("Thread %s started: Deque() of queue  : %s\n", thName, queue);

        int value = queue.deq(v);

        //System.out.printf("Thread %s ended  : Deque()=%d of queue: %s\n", thName, value, queue);

        MaybeSleep(thName);

        System.out.println();

        return value;
    }
}

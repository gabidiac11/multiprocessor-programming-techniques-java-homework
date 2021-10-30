package Business.Command;

import Core.ICommand;
import Core.ILockFreeQueue;

public class EnqueueCommand extends CommandWithSleeping implements ICommand {
    final int value;

    public EnqueueCommand(int value) {
        this.value = value;
    }

    public EnqueueCommand(int value, int sleepTime) {
        this.value = value;
        //command with sleeping
        this.sleepTime = sleepTime;
    }

    @Override
    public Integer execute(String thName, ILockFreeQueue queue) throws InterruptedException {
        System.out.printf("Thread %s started: Enqueue(%d) of queue: %s\n", thName, value, queue);

        queue.enq(value);

        System.out.printf("Thread %s ended  : Enqueue(%d) of queue: %s\n", thName, value, queue);

        MaybeSleep(thName);

        System.out.println();

        return null;
    }
}

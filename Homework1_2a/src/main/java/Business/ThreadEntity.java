package Business;

import Business.Command.EnqueueCommand;
import Core.ICommand;
import Core.ILockFreeQueue;

import java.util.Vector;

/**
 * - a thread that executes a set of commands
 *
 * - Vector<ICommand> commands determines if ThreadEntity is Consumer or Producer or both
 *  (ICommand can deque or enqueue so a ThreadEntity can be either Consumer, or Producer, or both)
 */
public class ThreadEntity extends Thread {
    private final String name;
    private final ILockFreeQueue queue;
    private final Vector<ICommand> commands;
    private final Verbose v;

    public ThreadEntity(String name, ILockFreeQueue queue, Vector<ICommand> commands) {
        this.name = name;
        this.queue = queue;
        this.commands = commands;
        this.v = new Verbose(this);
    }

    @Override
    public void run() {
        //System.out.printf("Thread %s started... \n", getMyName());

        while(commands.size() > 0) {
            try {
                commands.remove(0)
                        .execute(getMyName(), queue, v);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Thread %s finished... \n", getMyName());
    }

    public String getMyName() {
        return String.format("%10s", name);
    }
}

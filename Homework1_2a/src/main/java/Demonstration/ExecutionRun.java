package Demonstration;

import Business.Command.DequeCommand;
import Business.Command.EnqueueCommand;
import Business.ThreadEntity;
import Core.ICommand;
import Demonstration.LockFreeQueue.LockBasedQueue;
import Core.ILockFreeQueue;
import Demonstration.LockFreeQueue.LockBasedQueueVolatile;

import java.util.Vector;

public class ExecutionRun {

    public ExecutionRun() {
//        consumersGetSuckBecauseOfCaching();
        otherTest();
    }

    private Vector<ICommand> generateEnqueue(int count) {
        Vector<ICommand> enqCommands = new Vector<>();
        for(int i = 0; i <count; i++) {
           enqCommands.add(new EnqueueCommand(i+1));
        }
        return enqCommands;
    }

    private Vector<ICommand> generateDeque(int count) {
        Vector<ICommand> commands = new Vector<>();
        for(int i = 0; i <count; i++) {
            commands.add(new DequeCommand());
        }
        return commands;
    }

    /**
     * Exists a scenario where this algorithm won't work
     * [1636394594158] Consumer  Consumer1 waiting before while, with tail 0, head 0
     * [1636394594205] Consumer  Consumer2 waiting before while, with tail 0, head 0
     *
     * [1636394594248] Producer  Producer2 ABOUT to locks -> tail 0, head 0, try to push x=1
     * [1636394594253] Producer  Producer2 is has LOCKED -> tail 0, head 0, try to push x=1
     * [1636394594258] Producer  Producer2 has ENQUEUED VALUE -> tail 1, head 0, try to push x=1
     * [1636394594259] Producer  Producer2 is ABOUT to unlock -> tail 1, head 0, try to push x=1
     * [1636394594260] Producer  Producer2 has UNLOCKED -> tail 1, head 0, try to push x=1
     *
     * .........
     * NOW CONSUMER ARE STILL STUCK IN WHILE LOOP BECAUSE tail and head are cached as 0 and 0!
     * ........
     *
     * [... Producers finish adding stuff in the queue ...]
     *
     * .........
     * CONSUMER ARE STILL STUCK IN WHILE LOOP BECAUSE tail and head are cached as 0 and 0!
     * ........
     * ........
     *
     * This gets solved by using "volatile"
     */
    public void consumersGetSuckBecauseOfCaching() {
        int queueSize = 20;
        LockBasedQueue queue = new LockBasedQueue(queueSize);

        //a producer is receiving only enq commands
        ThreadEntity producer1 = new ThreadEntity("Producer1", queue, generateEnqueue(queueSize / 2));
        ThreadEntity producer2 = new ThreadEntity("Producer2", queue, generateEnqueue(queueSize / 2));

        //a consumer is receiving only deq commands
        ThreadEntity consumer1 = new ThreadEntity("Consumer1", queue, generateDeque(queueSize / 2));
        ThreadEntity consumer2 = new ThreadEntity("Consumer2", queue, generateDeque(queueSize / 2));


        consumer1.start();
        consumer2.start();

        //make all consumer get stuck at while(...queue is empty)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        producer1.start();
        producer2.start();

        try {
            consumer1.join();
            consumer2.join();
            producer1.join();
            producer2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void otherTest() {
        int queueSize = 100;
        ILockFreeQueue queue = new LockBasedQueueVolatile(queueSize);

        int count = 10;

        Vector<ThreadEntity> producers = new Vector<>();
        for(int i = 0; i < count; i++) producers.add(new ThreadEntity(String.format("Producer-%d",i+1) , queue, generateEnqueue(3)));

        Vector<ThreadEntity> consumers = new Vector<>();
        for(int i = 0; i < count; i++) consumers.add(new ThreadEntity(String.format("Consumer-%d",i+1) , queue, generateDeque(3)));

        for(int i = 0; i < count; i++) {
            producers.get(i).start();
            consumers.get(i).start();
        }

        producers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        consumers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}

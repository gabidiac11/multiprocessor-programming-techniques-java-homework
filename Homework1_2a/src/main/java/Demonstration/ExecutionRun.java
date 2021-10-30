package Demonstration;

import Business.Command.DequeCommand;
import Business.Command.EnqueueCommand;
import Business.ThreadEntity;
import Core.ICommand;
import Demonstration.LockFreeQueue.LockFreeQueueMeantForOneConsumer;
import Core.ILockFreeQueue;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Vector;

public class ExecutionRun {
    private final ILockFreeQueue queue;
    private final int SIZE = 20;

    public ExecutionRun() {
        queue = new LockFreeQueueMeantForOneConsumer(SIZE);
    }

    Vector<ICommand> generateEnqueue(int count) {
        Vector<ICommand> enqCommands = new Vector<>();
        for(int i = 0; i <count; i++) {
            if(new Random().nextInt(2) == 1) {
                enqCommands.add(new EnqueueCommand(i+1));
            } else {
                //generate random sleep intervals
                enqCommands.add(new EnqueueCommand(i+1, new Random().nextInt(2000 - 100 + 1) + 100));
            }

        }
        return enqCommands;
    }

    Vector<ICommand> generateDeque(int count) {
        Vector<ICommand> commands = new Vector<>();
        for(int i = 0; i <count; i++) {
            commands.add(new DequeCommand());
        }
        return commands;
    }

    @Test
    void o2neProducerOneConsumer() {
//        ThreadEntity producer = new ThreadEntity("Producer", queue, generateEnqueue(SIZE));
//        ThreadEntity consumer = new ThreadEntity("Consumer", queue, generateDeque(SIZE));
//
//        consumer.start();
//        producer.start();
//
//        try {
//            producer.join();
//            consumer.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        assertTrue(true);
    }

    @Test
    void multipleProducerMultipleConsumers() {
        ThreadEntity producer1 = new ThreadEntity("Producer1", queue, generateEnqueue(SIZE));
        ThreadEntity producer2 = new ThreadEntity("Producer2", queue, generateEnqueue(SIZE));
        ThreadEntity consumer1 = new ThreadEntity("Consumer1", queue, generateDeque(SIZE));
        ThreadEntity consumer2 = new ThreadEntity("Consumer2", queue, generateDeque(SIZE));

        consumer1.start();
        consumer2.start();
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

        assertTrue(true);
    }
}

package B;

import Common.Cook;

import B.Business.Pot;
import B.Business.Savage;

import Core.ICook;
import Core.IExerciseFactory;
import Core.IPot;
import Core.ISavage;

import java.util.Vector;

public class ExerciseProviderB implements IExerciseFactory {
    private final int N;
    private final int numOfThreads;

    public ExerciseProviderB(int N, int numOfThreads) {
        this.N = N;
        this.numOfThreads = numOfThreads;
    }

    @Override
    public IPot CreatePot() {
        return new Pot(N, numOfThreads);
    }

    @Override
    public Vector<ISavage> CreateSavages(IPot pot) {
        return new Vector<>(){{
            for(int i =0; i < numOfThreads; i++) {
                add(new Savage((Pot) pot));
            }
        }};
    }

    @Override
    public ICook CreateCook(IPot pot) {
        return new Cook(pot);
    }

    /**
     * sage threads are always hungry, and the console eventually gets overwhelmed,
     * so it cuts verbose from the beginning to make more space in the buffer for the upcoming verbose,
     * which does not permit to observe the process from the beginning
     * @param savages -
     */
    @Override
    public void AddTimeLimitIfAny(Vector<ISavage> savages) {
        final int timeLimit = 30000;
        try {
            Thread.sleep(timeLimit);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n---------------------!!! PROCESS REACHED ITS TIME LIMIT !!!---------------------------------------------------------------------------------------------------\n");
        System.out.printf("Exercise is stopping after limit of %d seconds to prevent console buffer being trimmed from start (destroying first part of the verbose)...\n", timeLimit / 1000);
        System.out.print("If you want to make the exercise persist more you can do so by going to ExerciseProviderB class and find my caller method and change ...\n");
        System.out.print("----------------------!!! PROCESS REACHED ITS TIME LIMIT !!!---------------------------------------------------------------------------------------------------\n\n");

        savages.forEach(s -> ((Thread) s).stop());
    }
}

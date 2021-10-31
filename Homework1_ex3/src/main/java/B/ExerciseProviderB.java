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

    @Override
    public int GetN() {
        return N;
    }
}

package A;

import Common.Cook;
import A.Business.Pot;
import A.Business.Savage;
import Core.ICook;
import Core.IExerciseFactory;
import Core.IPot;
import Core.ISavage;

import java.util.Vector;

public class ExerciseProviderA implements IExerciseFactory {
    private final int N;

    public ExerciseProviderA(int N) {
        this.N = N;
    }

    @Override
    public IPot CreatePot() {
        return new Pot(N);
    }

    @Override
    public Vector<ISavage> CreateSavages(IPot pot) {
        return new Vector<ISavage>(){{
            for(int i =0; i < N*10; i++) {
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

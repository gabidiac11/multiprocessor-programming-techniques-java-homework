import Core.ICook;
import Core.IExerciseFactory;
import Core.IPot;
import Core.ISavage;

import java.util.Vector;

public class Launcher {
    private final ICook cook;
    private final Vector<ISavage> savages;

    public Launcher(IExerciseFactory factory) {
        IPot pot = factory.CreatePot();
        this.cook = factory.CreateCook(pot);
        this.savages = factory.CreateSavages(pot);
    }

    public void Execute() {
        cook.start();
        savages.forEach(ISavage::start);
    }
}

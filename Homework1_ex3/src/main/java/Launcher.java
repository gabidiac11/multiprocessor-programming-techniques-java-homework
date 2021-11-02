import Core.ICook;
import Core.IExerciseFactory;
import Core.IPot;
import Core.ISavage;

import java.util.Vector;

public class Launcher {
    private final ICook cook;
    private final Vector<ISavage> savages;
    private final IExerciseFactory factory;

    public Launcher(IExerciseFactory factory) {
        IPot pot = factory.CreatePot();
        this.cook = factory.CreateCook(pot);
        this.savages = factory.CreateSavages(pot);

        this.factory = factory;
    }

    public void Execute() {
        cook.start();
        savages.forEach(ISavage::start);

        this.factory.AddTimeLimitIfAny(savages);

        try {
            for (ISavage savage : savages) {
                ((Thread) savage).join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //stop cook
        System.out.print("\nCook is stopping because no savage is hungry anymore...\n");
        ((Thread) cook).stop();
    }
}

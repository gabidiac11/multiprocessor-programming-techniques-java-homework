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

        do {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (savages.stream().allMatch(s -> ((Thread)s).isAlive()));

        System.out.print("\nCook is stopping because no savage is hungry anymore...\n");
        ((Thread) cook).stop();
    }
}

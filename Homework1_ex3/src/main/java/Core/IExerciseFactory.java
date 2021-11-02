package Core;

import java.util.Vector;

public interface IExerciseFactory {
    IPot CreatePot();
    Vector<ISavage> CreateSavages(IPot pot);
    ICook CreateCook(IPot pot);
    void AddTimeLimitIfAny(Vector<ISavage> savages);
}

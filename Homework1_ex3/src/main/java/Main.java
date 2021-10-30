import A.ExerciseProviderA;
import Core.IExerciseFactory;

public class Main {

    public static void main(String[] argv) {
        new Launcher(ChooseExercise())
                .Execute();
    }

    private static IExerciseFactory ChooseExercise() {
        //TODO: a prompted with that let you choose if you want 3_a or 3_b
        return new ExerciseProviderA(7);
    }
}

import A.ExerciseProviderA;
import B.ExerciseProviderB;
import Core.IExerciseFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] argv) throws IOException {
        new Launcher(ChooseExercise())
                .Execute();
    }

    private static IExerciseFactory ChooseExercise() throws IOException {

        String text = Files.readString(Paths.get("src/main/java/config.txt"));

        //3_b
        if(text.equals("b")) {
            return new ExerciseProviderB(7, 5);
        }

        //3_a
        return new ExerciseProviderA(7);
    }
}

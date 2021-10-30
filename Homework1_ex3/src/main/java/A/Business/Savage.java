package A.Business;

import Common.VerboseThread;
import Core.ISavage;
import Core.IThreadEntity;

public class Savage extends Thread implements ISavage, IThreadEntity {
    private static int incrementor = 0;

    private boolean hasEaten = false;
    private final Pot pot;
    private final String name;

    private final VerboseThread v;

    public Savage(Pot pot) {
        this.pot = pot;
        this.name = createName();
        v = new VerboseThread(this);
    }

    @Override
    public boolean isHungry() {
        return !hasEaten;
    }

    @Override
    public void eatOnePortion() {
        hasEaten = true;
    }

    @Override
    public void run() {
        v.PrintStart();

        while(isHungry()) {
            pot.GiveOneRation(v);
            eatOnePortion();
        }

        v.PrintEnd();
    }

    public String getTheName() {
        return String.format("%10s", name);
    }

    private static String createName() {
        incrementor++;
        return String.format("Savage-%d", incrementor - 1);
    }
}

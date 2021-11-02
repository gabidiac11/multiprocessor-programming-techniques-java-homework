package B.Business;

import Common.VerboseThread;
import Core.ISavage;
import Core.IThreadEntity;

public class Savage extends Thread implements ISavage, IThreadEntity {
    private static int incrementor = 0;

    private final Pot pot;
    private final String name;
    private final VerboseThread v;
    private final int i;

    public Savage(Pot pot) {
        this.pot = pot;
        v = new VerboseThread(this);

        i = incrementor;
        this.name = createName();
    }

    @Override
    public void run() {
        v.PrintStart();

        while(isHungry()) {
            pot.GiveOneRation(v, i);
        }

        v.PrintEnd();
    }

    @Override
    public boolean isHungry() {
        return true;
    }

    @Override
    public void eatOnePortion() {}

    public String getTheName() {
        return String.format("%10s", name);
    }

    private static String createName() {
        incrementor++;
        return String.format("Savage-%d", incrementor - 1);
    }

    @Override
    public int getIndex() {
        return i;
    }
}

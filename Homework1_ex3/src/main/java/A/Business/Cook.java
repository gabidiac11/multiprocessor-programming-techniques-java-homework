package A.Business;

import Common.VerboseThread;
import Core.ICook;
import Core.IThreadEntity;

public class Cook extends Thread implements ICook, IThreadEntity {
    private final Pot pot;
    private final VerboseThread v;

    public Cook(Pot pot) {
        this.pot = pot;
        v = new VerboseThread(this);
    }

    @Override
    public void run() {
        v.PrintStart();

        while(true) {
            pot.theCookKeepingAnOpenMind(v);
        }

//        verb.PrintEnd();
    }

    @Override
    public String getTheName() {
        return "The_Cook";
    }
}

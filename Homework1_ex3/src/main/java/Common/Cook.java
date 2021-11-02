package Common;

import Common.VerboseThread;
import Core.ICook;
import Core.IPot;
import Core.IThreadEntity;

public class Cook extends Thread implements ICook, IThreadEntity {
    private final IPot pot;
    private final VerboseThread v;

    public Cook(IPot pot) {
        this.pot = pot;
        v = new VerboseThread(this);
    }

    @Override
    public void run() {
        v.PrintStart();

        while(true) {
            pot.theCookKeepingAnOpenMind(v);
        }
    }

    @Override
    public String getTheName() {
        return "The_Cook";
    }
}

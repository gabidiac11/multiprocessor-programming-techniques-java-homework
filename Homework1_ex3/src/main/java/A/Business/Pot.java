package A.Business;

import Common.VerboseThread;
import Core.IPot;

public class Pot implements IPot {
    private volatile int remainingPortions = 0;
    private volatile boolean refillRequested = false;
    private final int N;

    public Pot(int N) {
        this.N = N;
    }

    public synchronized void GiveOneRation(VerboseThread v) {
        if(refillRequested) {  v.Detected_Request_In_Progress_For_The_Cook_To_Refill(); }

        /* suspend any other thread while the cook is refilling or about to refill */
        while (refillRequested) {
            waitAux();
        }

        /* if no rations are left, the current thread will notify the cook and wait for refill  */
        if(remainingPortions == 0) {
                if(!refillRequested) {
                    refillRequested = true;
                    //intended to reach the cook
                    notifyAll();
                    v.Savage_Requested_Refill();
                }
        }

        if(remainingPortions == 0) { v.Savage_Waits_Refilling_After_A_Sent_Request_To_Refill(); }

        //this is where the thread that requested refill waits. Obs. will get served first after refill
        while(remainingPortions == 0) {
            waitAux();
        }

        //thread consumes a unit
        remainingPortions --;
        v.Pot_A_Ration_Taken_By(remainingPortions);
    }

    private void waitAux() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized void theCookKeepingAnOpenMind(VerboseThread v) {
        //wait for any thread to tell the cook to refill
        while (!refillRequested) {
            try {
                v.CookWaits();
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        v.CookStopWaiting();

        if(refillRequested) {
            Refill(v);
            refillRequested = false;
            notifyAll();
        }
    }

    @Override
    public synchronized void Refill(VerboseThread v) {
        remainingPortions = N;
        v.CookRefilled();
    }
}

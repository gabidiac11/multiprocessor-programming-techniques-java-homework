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

    @Override
    public void GiveOneRation(VerboseThread v) {
        synchronized (this) {
        if(refillRequested) {  v.Detected_Request_In_Progress_For_The_Cook_To_Refill(); }

        /*
        * suspend any other thread while the cook is refilling or about to refill
        * this ensures one single thread will send request to the cook in case of an empty pot
        * */
        while (refillRequested) {}

        /*
        * if no rations are left, the current thread will notify the cook and wait for refill
        * */
        if(remainingPortions == 0) {
            synchronized (this) {
                if(!refillRequested) {
                    refillRequested = true;
                    notify();
                    v.Savage_Requested_Refill();
                }
            }
        }


            if(remainingPortions == 0) {
                v.Savage_Waits_Refilling_After_A_Sent_Request_To_Refill();
                notifyAll();
            }

            while(remainingPortions == 0) {}

            remainingPortions --;

            v.Pot_A_Ration_Taken_By(remainingPortions);
        }
    }

    @Override
    public synchronized void theCookKeepingAnOpenMind(VerboseThread v) {
        //wait for any thread to tell the cook to refill
        try {
            v.CookWaits();
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        v.CookStopWaiting();

        if(refillRequested) {
            Refill(v);
            refillRequested = false;
        }
    }

    @Override
    public synchronized void Refill(VerboseThread v) {
        remainingPortions = N;
        v.CookRefilled();
    }
}

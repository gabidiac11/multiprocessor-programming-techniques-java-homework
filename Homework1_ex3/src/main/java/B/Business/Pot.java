package B.Business;

import Common.VerboseThread;
import Core.IPot;

import java.util.Arrays;

public class Pot implements IPot {
    private volatile int remainingPortions = 0;
    private volatile boolean refillRequested = false;
    private final int N;

    //fairness parameters
    private volatile boolean[] flag;
    private volatile int[] label;

    public Pot(int N, int numOfThreads) {
        this.N = N;

        flag = new boolean[numOfThreads];
        label = new int[numOfThreads];
    }

    public void GiveOneRation(VerboseThread v, int i) {
        lock(v, i);

        synchronized (this) {
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
    }

    private void lock(VerboseThread v, int i) {
        int max = label[0];
        for(int j = 1; j < label.length; j++) {
            if(max < label[j]) { max = label[j]; }
        }

        flag[i] = true;
        label[i] = max + 1;

        if(!isThreadTurn(i)) { v.Savage_Is_Stuck_While_Is_Not_His_Turn(); }
        while(!isThreadTurn(i)) {}

        v.Savage_Realizes_Is_His_Turn();
    }

    private boolean isThreadTurn(int i) {
        boolean isMyTurnAssumption = true;
        for(int j = 0; j < flag.length; j++) {
            if(i == j || !flag[i]) {
                continue;
            }

            if(label[j] > label[i]) {
                isMyTurnAssumption = false;
                break;
            }

            if(label[j] == label[i] && j > i) {
                isMyTurnAssumption = false;
                break;
            }
        }

        return isMyTurnAssumption;
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

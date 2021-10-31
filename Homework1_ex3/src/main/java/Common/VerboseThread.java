package Common;

import Core.IThreadEntity;

public class VerboseThread {
    private final IThreadEntity thread;

    public VerboseThread(IThreadEntity thread) {
        this.thread = thread;
    }

    public void PrintEnd() {
        System.out.printf("%s finished...\n", getName());
    }

    public void PrintStart() {
        System.out.println();
        System.out.printf("%s started...\n", getName());
    }

    public String getName() {
        return String.format("%10s", thread.getTheName());
    }

    public void CookWaits() {
        System.out.printf("%s waits for further notification...\n", getName());
    }

    public void CookStopWaiting() {
        System.out.printf("%s stopped waiting...\n", getName());
    }

    public void CookRefilled() {
        System.out.printf("%s refilled...\n", getName());
    }

    public void Detected_Request_In_Progress_For_The_Cook_To_Refill() {
        System.out.printf("%s started waiting because is detecting request in progress for the cook to refill...\n", getName());
    }

    public void Savage_Requested_Refill() {
        System.out.printf("%s has notified the Cook for a new refill \n", getName());
    }

    public void Savage_Waits_Refilling_After_A_Sent_Request_To_Refill() {
        System.out.printf("%s waits for the Cook to respond to the request \n", getName());
    }

    public void Pot_A_Ration_Taken_By(int remainingPortions) {
        System.out.printf("%s has taken a ration from the pot, which is now left with only %d \n", getName(), remainingPortions);
    }

    public void Savage_Is_Stuck_While_Is_Not_His_Turn() {
        System.out.printf("%s is stuck while is NOT his turn\n", getName());
    }

    public void Savage_Realizes_Is_His_Turn() {
        System.out.printf("%s realizes is his turn so try to enter critical section\n", getName());
    }
}

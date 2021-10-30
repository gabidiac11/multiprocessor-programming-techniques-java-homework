package Business.Command;

public class CommandWithSleeping {
    Integer sleepTime = null;

    protected void MaybeSleep(String thName) throws InterruptedException {
        if(sleepTime != null) {
            System.out.printf("Thread %s is sleeping now, time: %d\n", thName, sleepTime);
                Thread.sleep(sleepTime.longValue());
        }
    }
}

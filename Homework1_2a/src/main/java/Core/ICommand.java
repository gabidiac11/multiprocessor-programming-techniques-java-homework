package Core;

import Business.Verbose;

public interface ICommand {
    Integer execute(String thName, ILockFreeQueue queue, Verbose v) throws InterruptedException;
}

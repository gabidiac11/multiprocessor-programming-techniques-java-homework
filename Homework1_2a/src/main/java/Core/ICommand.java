package Core;

public interface ICommand {
    Integer execute(String thName, ILockFreeQueue queue) throws InterruptedException;
}

package Core;

public interface IThreadEntity {
    String getTheName();
    default int getIndex() { return 0; }
}

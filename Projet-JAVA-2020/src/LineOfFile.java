public interface LineOfFile {
    int getId();
    int getScore();
    void addToScore(int value);
    void decreaseScoreByOne();
    void printMsg();
    boolean isActive();
}

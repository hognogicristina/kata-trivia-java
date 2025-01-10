package trivia;

interface IPlayer {
    String getName();

    int getPlace();

    void setPlace(int roll);

    int getPurses();

    void incrementPurses();

    void incrementBonusPurses();

    boolean inPenaltyBox();

    void setInPenaltyBox(boolean inPenaltyBox);

    int getStreak();

    void incrementStreak();
}

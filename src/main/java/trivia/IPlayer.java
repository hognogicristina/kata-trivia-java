package trivia;

interface IPlayer {
    String getName();

    void setPlace(int roll);

    int getPlace();

    int getPurses();

    void incrementPurses();

    boolean inPenaltyBox();

    void setInPenaltyBox(boolean inPenaltyBox);

}

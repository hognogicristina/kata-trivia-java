package trivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class Question implements IQuestion {
    private ArrayList<LinkedList<String>> questions = new ArrayList<>();
    private static final int NO_OF_QUESTIONS = 50;

    public Question() {
        for (int j = 0; j < 4; j++) {
            questions.add(new LinkedList<>());
        }
        for (int i = 0; i < NO_OF_QUESTIONS; i++) {
            questions.get(0).addLast(createQuestion("Pop", i));
            questions.get(1).addLast(createQuestion("Science", i));
            questions.get(2).addLast(createQuestion("Sports", i));
            questions.get(3).addLast(createQuestion("Rock", i));
        }
    }

    private String createQuestion(String category, int index) {
        return category + " Question " + index;
    }

    @Override
    public String getCategory(int position) {
        return switch (position) {
            case 1, 5, 9 -> "Pop";
            case 2, 6, 10 -> "Science";
            case 3, 7, 11 -> "Sports";
            default -> "Rock";
        };
    }

    @Override
    public String getQuestion(int position) {
        return switch (position) {
            case 1, 5, 9 -> questions.get(0).removeFirst();
            case 2, 6, 10 -> questions.get(1).removeFirst();
            case 3, 7, 11 -> questions.get(2).removeFirst();
            default -> questions.get(3).removeFirst();
        };
    }
}

package trivia;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Properties;

public class Question implements IQuestion {
    private ArrayList<LinkedList<String>> questions = new ArrayList<>();
    private static final int NO_OF_QUESTIONS = 50;


    public Question() {
        loadQuestions("pop.properties", 0);
        loadQuestions("science.properties", 1);
        loadQuestions("sports.properties", 2);
        loadQuestions("rock.properties", 3);
        loadQuestions("geography.properties", 4);
    }

    private void loadQuestions(String fileName, int index) {
        LinkedList<String> questionList = new LinkedList<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                throw new IOException("Properties file not found: " + fileName);
            }
            Properties properties = new Properties();
            properties.load(input);

            for (int i = 1; i <= NO_OF_QUESTIONS; i++) {
                String question = properties.getProperty(String.valueOf(i));
                if (question != null) {
                    questionList.add(question);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error loading questions from " + fileName, e);
        }
        questions.add(index, questionList);
    }

    @Override
    public String getCategory(int position) {
        return switch (position % 15) {
            case 1, 6, 11 -> "Pop";
            case 2, 7, 12 -> "Science";
            case 3, 8, 13 -> "Sports";
            case 4, 9, 14 -> "Rock";
            default -> "Geography";
        };
    }

    @Override
    public String getQuestion(int position) {
        return switch (position % 15) {
            case 1, 6, 11 -> questions.get(0).removeFirst();
            case 2, 7, 12 -> questions.get(1).removeFirst();
            case 3, 8, 13 -> questions.get(2).removeFirst();
            case 4, 9, 14 -> questions.get(3).removeFirst();
            default -> questions.get(4).removeFirst();
        };
    }
}

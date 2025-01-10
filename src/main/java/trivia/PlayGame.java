package trivia;

import java.util.Random;
import java.util.Scanner;

// DON'T TOUCH THIS CLASS. DON'T REFACTOR THIS CLASS.
// ONLY RUN IT TO MANUALLY PLAY THE GAME YOURSELF TO UNDERSTAND THE PROBLEM
public class PlayGame {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("*** Welcome to Trivia Game ***\n");
        System.out.println("Enter number of players: 2-6");
        int playerCount = readPlayerCount();
        System.out.println("Reading names for " + playerCount + " players:");
        IGame aGame = new Game();

        for (int i = 1; i <= playerCount; i++) {
            while (true) {
                System.out.print("Player " + i + " name: ");
                String playerName = scanner.nextLine().trim();
                try {
                    aGame.add(playerName);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid name: " + e.getMessage());
                    System.out.println("Please enter a valid name (at least 2 characters):");
                }
            }
        }

        System.out.println("\n\n--Starting game--");

        boolean notAWinner;
        do {
            int roll = readRoll();
            aGame.roll(roll);

            System.out.print(">> Was the answer correct? [y/n] ");
            boolean correct = readYesNo();
            if (correct) {
                notAWinner = aGame.wasCorrectlyAnswered();
            } else {
                notAWinner = aGame.wrongAnswer();
            }

        } while (notAWinner);
        System.out.println(">> Game won!");
    }

    private static int readPlayerCount() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.matches("\\d+")) {
                System.out.println("Invalid input. Please enter an integer between 1 and 6:");
                continue;
            }
            int playerCount = Integer.parseInt(input);
            if (playerCount < 2 || playerCount > 6) {
                System.out.println("Invalid number of players. Please enter a number between 2 and 6:");
                continue;
            }
            return playerCount;
        }
    }

    private static boolean readYesNo() {
        String yn = scanner.nextLine().trim().toUpperCase();
        if (!yn.matches("[YN]")) {
            System.out.println("y or n please");
            return readYesNo();
        }
        return yn.equalsIgnoreCase("Y");
    }

    private static int readRoll() {
        System.out.print(">> Throw a die and input roll, or [ENTER] to generate a random roll: ");
        String rollStr = scanner.nextLine().trim();
        if (rollStr.isEmpty()) {
            int roll = new Random().nextInt(6) + 1;
            System.out.println(">> Random roll: " + roll);
            return roll;
        }
        if (!rollStr.matches("\\d+")) {
            System.out.println("Not a number: '" + rollStr + "'");
            return readRoll();
        }
        int roll = Integer.parseInt(rollStr);
        if (roll < 1 || roll > 6) {
            System.out.println("Invalid roll");
            return readRoll();
        }
        return roll;
    }
}

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    static String playRound(String playerMove, String computerMove) {

        if (playerMove.equals(computerMove)) {
            return "Draw";
        }

        if ((playerMove.equals("Rock") && computerMove.equals("Scissors")) ||
            (playerMove.equals("Paper") && computerMove.equals("Rock")) ||
            (playerMove.equals("Scissors") && computerMove.equals("Paper"))) {
            return "Player Wins";
        }

        return "Computer Wins";
    }

    static String getComputerMove(Random random) {
        String[] moves = {"Rock", "Paper", "Scissors"};
        return moves[random.nextInt(moves.length)];
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int rounds = 5;
        int wins = 0;
        int losses = 0;
        int draws = 0;

        String[] playerMoves = new String[rounds];
        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        for (int i = 0; i < rounds; i++) {

            System.out.print("Round " + (i + 1) +
                    " - Enter your move (Rock/Paper/Scissors): ");

            String playerMove = scanner.nextLine().trim();

            if (playerMove.equalsIgnoreCase("rock")) {
                playerMove = "Rock";
            } else if (playerMove.equalsIgnoreCase("paper")) {
                playerMove = "Paper";
            } else if (playerMove.equalsIgnoreCase("scissors")) {
                playerMove = "Scissors";
            } else {
                System.out.println("Invalid move. Please enter Rock, Paper, or Scissors.");
                i--;
                continue;
            }

            String computerMove = getComputerMove(random);
            String result = playRound(playerMove, computerMove);

            playerMoves[i] = playerMove;
            computerMoves[i] = computerMove;
            results[i] = result;

            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }

            System.out.println("Computer Move: " + computerMove);
            System.out.println("Result: " + result);
            System.out.println();
        }

        double winPercentage = ((double) wins / rounds) * 100;

        System.out.println("========== FINAL SUMMARY ==========");
        System.out.printf("%-8s %-15s %-15s %-15s%n",
                "Round", "Player Move", "Computer Move", "Result");

        for (int i = 0; i < rounds; i++) {
            System.out.printf("%-8d %-15s %-15s %-15s%n",
                    i + 1,
                    playerMoves[i],
                    computerMoves[i],
                    results[i]);
        }

        System.out.println("-----------------------------------");
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Draws: " + draws);
        System.out.printf("Player Win Percentage: %.2f%%%n", winPercentage);
    }
}
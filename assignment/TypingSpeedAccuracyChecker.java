import java.util.Scanner;

public class TypingSpeedAccuracyChecker {

    static void checkTypingAccuracy(String original, String typed) {

        int matched = 0;
        int firstMismatch = -1;

        int length = Math.min(original.length(), typed.length());

        for (int i = 0; i < length; i++) {

            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }

        int totalCharacters = original.length();
        double accuracy = ((double) matched / totalCharacters) * 100;

        System.out.printf("Matched: %d/%d | Accuracy: %.2f%%",
                matched, totalCharacters, accuracy);

        if (firstMismatch == -1 && original.length() == typed.length()) {
            System.out.println(" | No Mismatches");
        } else {
            if (firstMismatch == -1) {
                firstMismatch = length;
            }

            char originalChar = firstMismatch < original.length()
                    ? original.charAt(firstMismatch) : '-';

            char typedChar = firstMismatch < typed.length()
                    ? typed.charAt(firstMismatch) : '-';

            System.out.println(" | First Mismatch at position "
                    + (firstMismatch + 1)
                    + " ('" + originalChar + "' vs '" + typedChar + "')");
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter original passage: ");
        String original = scanner.nextLine();

        System.out.print("Enter typed text: ");
        String typed = scanner.nextLine();

        checkTypingAccuracy(original, typed);

        scanner.close();
    }
}
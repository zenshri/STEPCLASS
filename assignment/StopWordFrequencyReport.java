import java.util.*;

public class StopWordFrequencyReport {

    static void printFilteredWordFrequency(String feedback) {

        String cleaned = feedback.toLowerCase()
                                .replace(".", "")
                                .replace(",", "");

        String[] words = cleaned.split("\\s+");

        String[] stopWords = {
            "the", "was", "and", "a", "is", "of", "in"
        };

        HashMap<String, Integer> frequency = new HashMap<>();

        for (String word : words) {

            boolean stopWord = false;

            for (String stop : stopWords) {

                if (word.equals(stop)) {
                    stopWord = true;
                    break;
                }
            }

            if (!stopWord) {
                frequency.put(
                    word,
                    frequency.getOrDefault(word, 0) + 1
                );
            }
        }

        ArrayList<Map.Entry<String, Integer>> list =
                new ArrayList<>(frequency.entrySet());

        list.sort((a, b) ->
                b.getValue() - a.getValue()
        );

        for (Map.Entry<String, Integer> entry : list) {

            System.out.println(
                entry.getKey() + ": " + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter feedback: ");
        String feedback = sc.nextLine();

        printFilteredWordFrequency(feedback);

        sc.close();
    }
}
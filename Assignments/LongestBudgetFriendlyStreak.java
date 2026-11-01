public class LongestBudgetFriendlyStreak {
    // Costs are non-negative.
    // Time: O(n), Extra space: O(1)
    public static int[] longestStreak(int[] costs, long budget) {
        int left = 0;
        long currentSum = 0;
        int bestLength = 0;
        int bestStart = -1;

        for (int right = 0; right < costs.length; right++) {
            currentSum += costs[right];

            while (left <= right && currentSum > budget) {
                currentSum -= costs[left];
                left++;
            }

            int length = right - left + 1;
            if (length > bestLength) {
                bestLength = length;
                bestStart = left;
            }
        }

        return bestLength == 0
                ? new int[]{0, -1}
                : new int[]{bestLength, bestStart};
    }

    public static void main(String[] args) {
        int[] first = longestStreak(
                new int[]{4, 2, 1, 7, 3, 1, 2, 1, 5}, 8);
        int[] second = longestStreak(new int[]{9, 10}, 8);

        System.out.println("(" + first[0] + ", " + first[1] + ")");
        System.out.println("(" + second[0] + ", " + second[1] + ")");
    }
}

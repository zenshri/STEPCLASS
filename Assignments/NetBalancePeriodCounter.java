import java.util.HashMap;
import java.util.Map;

public class NetBalancePeriodCounter {
    // Prefix sum + frequency map.
    // Time: O(n) average, Extra space: O(n).
    public static long countPeriods(int[] transactions, long k) {
        long prefixSum = 0;
        long count = 0;
        Map<Long, Integer> frequencies = new HashMap<>();
        frequencies.put(0L, 1);

        for (int value : transactions) {
            prefixSum += value;
            count += frequencies.getOrDefault(prefixSum - k, 0);
            frequencies.put(prefixSum, frequencies.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countPeriods(
                new int[]{3, 4, -7, 1, 3, 3, 1, -4}, 7));
        System.out.println(countPeriods(new int[]{1, 2, 3}, 10));
    }
}

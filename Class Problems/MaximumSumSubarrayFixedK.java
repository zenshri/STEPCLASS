public class MaximumSumSubarrayFixedK {
    // Time: O(n), Extra space: O(1)
    public static int maxSumSubarray(int[] sales, int k) {
        if (k < 1 || k > sales.length) {
            throw new IllegalArgumentException("k must be between 1 and array length");
        }

        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += sales[i];
        }

        int maxSum = windowSum;

        for (int right = k; right < sales.length; right++) {
            windowSum += sales[right] - sales[right - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSumSubarray(new int[]{2, 1, 5, 1, 3, 2}, 3));
    }
}

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class CanteenPriceStreak {
    // Monotonic stack of indices. O(n) time and O(n) extra space.
    public static int[] priceStreak(int[] price) {
        int n = price.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && price[stack.peek()] <= price[i]) {
                stack.pop();
            }

            result[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(priceStreak(
                new int[]{100, 80, 60, 70, 60, 75, 85})));
        System.out.println(Arrays.toString(priceStreak(new int[]{10, 20, 30})));
    }
}

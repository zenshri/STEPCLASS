public class ProductPriceFinder {
    static int lowerBound(int[] a, int target) {
        int left = 0, right = a.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] < target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    static int upperBound(int[] a, int target) {
        int left = 0, right = a.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (a[mid] <= target) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    public static void main(String[] args) {
        int[] prices = {100, 150, 150, 200, 300, 450};
        int low = 150, high = 300;
        int first = lowerBound(prices, low);
        int afterLast = upperBound(prices, high);
        System.out.println("Lower bound index " + first);
        System.out.println("Upper bound index " + afterLast);
        System.out.println("Count " + (afterLast - first));
    }
}

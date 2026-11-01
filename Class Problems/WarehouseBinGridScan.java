public class WarehouseBinGridScan {
    // Time: O(m*n), Extra space: O(1)
    public static void warehouseSummary(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            System.out.println("Total = 0, maxCoordinate = (-1, -1)");
            return;
        }

        long total = 0;
        int maxValue = grid[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int value = grid[row][col];
                total += value;

                // Strictly greater keeps the first maximum in row-major order.
                if (value > maxValue) {
                    maxValue = value;
                    maxRow = row;
                    maxCol = col;
                }
            }
        }

        System.out.println("Total = " + total
                + ", maxCoordinate = (" + maxRow + ", " + maxCol + ")");
    }

    public static void main(String[] args) {
        int[][] grid = {
            {4, 9, 2},
            {7, 1, 6},
            {3, 12, 5}
        };
        warehouseSummary(grid);
    }
}

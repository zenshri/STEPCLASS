import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class FireDrillExits {
    // Multi-source BFS from every exit. O(rows*cols) time and space.
    public static int[][] exitDistance(char[][] plan) {
        int rows = plan.length;
        if (rows == 0) return new int[0][0];
        int cols = plan[0].length;

        int[][] distance = new int[rows][cols];
        for (int[] row : distance) Arrays.fill(row, -1);

        Queue<int[]> queue = new ArrayDeque<>();

        // Add all exits before starting BFS.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (plan[r][c] == 'E') {
                    distance[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];

                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols
                        && plan[nr][nc] != '#' && distance[nr][nc] == -1) {
                    distance[nr][nc] = distance[r][c] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        char[][] plan = {
            {'.', '#', '.', 'E'},
            {'.', '.', '.', '#'},
            {'E', '#', '.', '.'}
        };

        int[][] result = exitDistance(plan);
        for (int[] row : result) {
            for (int value : row) System.out.print(value + " ");
            System.out.println();
        }
    }
}

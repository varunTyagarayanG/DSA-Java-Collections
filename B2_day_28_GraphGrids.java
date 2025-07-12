/*
========================= B2 Day 28: Islands & Grids =============================

**Problems Covered:**
1. [Number of Islands (LeetCode) - Medium](https://leetcode.com/problems/number-of-islands/)
2. [Rotting Oranges (LeetCode) - Medium](https://leetcode.com/problems/rotting-oranges/)
3. [Chef and Wells (GeeksforGeeks) - Medium](https://www.geeksforgeeks.org/problems/geeks-village-and-wells--170647/1)
*/

import java.util.*;

public class B2_day_28_GraphGrids {

    // ================= Question 1: Number of Islands ============================
    // 🔗 https://leetcode.com/problems/number-of-islands/submissions/1584811435/
    // START: Number of Islands
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        boolean[][] isVisited = new boolean[row][col];
        int ans = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!isVisited[i][j] && grid[i][j] == '1') {
                    dfsIsland(i, j, isVisited, row, col, grid);
                    ans++;
                }
            }
        }
        return ans;
    }

    static void dfsIsland(int i, int j, boolean[][] isVisited, int row, int col, char[][] grid) {
        if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] == '0' || isVisited[i][j]) {
            return;
        }
        isVisited[i][j] = true;
        dfsIsland(i - 1, j, isVisited, row, col, grid);
        dfsIsland(i, j - 1, isVisited, row, col, grid);
        dfsIsland(i, j + 1, isVisited, row, col, grid);
        dfsIsland(i + 1, j, isVisited, row, col, grid);
    }
    // END: Number of Islands

    // ================= Question 2: Rotting Oranges ============================
    // 🔗 https://leetcode.com/problems/rotting-oranges/submissions/1439594167/
    // START: Rotting Oranges
    public int orangesRotting(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        int time = 0;

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) fresh++;
                if (grid[r][c] == 2) q.offer(new int[]{r, c});
            }
        }

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (fresh > 0 && !q.isEmpty()) {
            int length = q.size();
            for (int i = 0; i < length; i++) {
                int[] curr = q.poll();
                int r = curr[0], c = curr[1];

                for (int[] dir : directions) {
                    int row = r + dir[0], col = c + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length
                            && grid[row][col] == 1) {
                        grid[row][col] = 2;
                        q.offer(new int[]{row, col});
                        fresh--;
                    }
                }
            }
            time++;
        }
        return fresh == 0 ? time : -1;
    }
    // END: Rotting Oranges

    // ================= Question 3: Chef and Wells ============================
    // 🔗 https://www.geeksforgeeks.org/problems/geeks-village-and-wells--170647/1
    // START: Chef and Wells (Geeks Village and Wells)
    public int[][] chefAndWells(int n, int m, char[][] c) {
        int[][] dist = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] == 'W') {
                    queue.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                }
                dist[i][j] = -1;
            }
        }

        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1], d = curr[2];

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && c[nx][ny] != 'N') {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, d + 2});

                    if (c[nx][ny] == 'H') {
                        dist[nx][ny] = d + 2;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (c[i][j] != 'H') {
                    dist[i][j] = 0;
                }
            }
        }

        return dist;
    }
    // END: Chef and Wells

    // =================== MAIN =======================
    public static void main(String[] args) {
        B2_day_28_GraphGrids gp = new B2_day_28_GraphGrids();

        System.out.println("1. Number of Islands (LeetCode) - Medium");
        System.out.println("🔗 https://leetcode.com/problems/number-of-islands/");
        char[][] islandGrid = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Islands: " + gp.numIslands(islandGrid));

        System.out.println("\n2. Rotting Oranges (LeetCode) - Medium");
        System.out.println("🔗 https://leetcode.com/problems/rotting-oranges/");
        int[][] orangeGrid = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println("Minutes until all rot: " + gp.orangesRotting(orangeGrid));

        System.out.println("\n3. Chef and Wells (GeeksforGeeks) - Medium");
        System.out.println("🔗 https://www.geeksforgeeks.org/problems/geeks-village-and-wells--170647/1");
        char[][] wellsGrid = {
            {'H', '.', '.', 'W'},
            {'.', 'N', '.', '.'},
            {'H', '.', '.', 'W'}
        };
        int[][] result = gp.chefAndWells(3, 4, wellsGrid);
        System.out.println("Distance matrix:");
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}

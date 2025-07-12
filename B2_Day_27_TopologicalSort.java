
/*
========================= B2 Day 27: Topological Sort =============================

**Problems Covered:**
1. [Course Schedule II (LeetCode) - Medium](https://leetcode.com/problems/course-schedule-ii/)
2. [Largest Color Value in a Directed Graph (LeetCode) - Hard](https://leetcode.com/problems/largest-color-value-in-a-directed-graph/)
3. [Flood Fill (LeetCode) - Easy](https://leetcode.com/problems/flood-fill/)
*/

import java.util.*;

public class B2_Day_27_TopologicalSort {

    // ----------- Question 1: Course Schedule II (LeetCode) - Medium -----------
    // 🔗 https://leetcode.com/problems/course-schedule-ii/
    static class Solution1 {
        public int[] topoSort(int n, List<List<Integer>> adj) {
            int[] inDegree = new int[n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < adj.get(i).size(); j++) {
                    inDegree[adj.get(i).get(j)]++;
                }
            }

            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }

            List<Integer> ans = new ArrayList<>();
            while (!q.isEmpty()) {
                int u = q.poll();
                ans.add(u);
                for (int i = 0; i < adj.get(u).size(); i++) {
                    int v = adj.get(u).get(i);
                    inDegree[v]--;
                    if (inDegree[v] == 0) {
                        q.add(v);
                    }
                }
            }

            if (ans.size() != n) {
                return new int[0];
            }

            int[] topo = new int[n];
            for (int i = 0; i < ans.size(); i++) {
                topo[i] = ans.get(i);
            }

            return topo;
        }

        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] prereq : prerequisites) {
                adj.get(prereq[1]).add(prereq[0]);
            }
            return topoSort(numCourses, adj);
        }
    }
    // ----------- End of Question 1 -----------

    // ----------- Question 2: Largest Color Value in a Directed Graph (LeetCode) - Hard -----------
    // 🔗 https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
    static class Solution2 {
        public int largestPathValue(String colors, int[][] edges) {
            int V = colors.length();
            int ans = 0;

            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
            }

            int[] inDegree = new int[V];
            for (int i = 0; i < V; i++) {
                for (int j : adj.get(i)) {
                    inDegree[j]++;
                }
            }

            int visited = 0;
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < V; i++) {
                if (inDegree[i] == 0) {
                    q.add(i);
                }
            }

            int[][] dp = new int[V][26];
            while (!q.isEmpty()) {
                int node = q.poll();
                visited++;
                dp[node][colors.charAt(node) - 'a']++;
                ans = Math.max(ans, dp[node][colors.charAt(node) - 'a']);

                for (int i : adj.get(node)) {
                    for (int j = 0; j < 26; j++) {
                        dp[i][j] = Math.max(dp[i][j], dp[node][j]);
                    }
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        q.add(i);
                    }
                }
            }

            return visited == V ? ans : -1;
        }
    }
    // ----------- End of Question 2 -----------

    // ----------- Question 3: Flood Fill (LeetCode) - Easy -----------
    // 🔗 https://leetcode.com/problems/flood-fill/
    static class Solution3 {
        public int[][] floodFill(int[][] image, int sr, int sc, int color) {
            int m = image.length;
            int n = image[0].length;
            int originalColor = image[sr][sc];

            if (originalColor == color) return image;

            Queue<int[]> queue = new LinkedList<>();
            queue.add(new int[]{sr, sc});
            image[sr][sc] = color;

            int[][] directions = {{0,1}, {1,0}, {0,-1}, {-1,0}};

            while (!queue.isEmpty()) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && image[nr][nc] == originalColor) {
                        image[nr][nc] = color;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }

            return image;
        }
    }
    // ----------- End of Question 3 -----------

    public static void main(String[] args) {
        System.out.println("===== Question 1: Course Schedule II (LeetCode) - Medium =====");
        System.out.println("🔗 https://leetcode.com/problems/course-schedule-ii/");
        Solution1 sol1 = new Solution1();
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] order = sol1.findOrder(4, prerequisites);
        System.out.println("Course Order: " + Arrays.toString(order));

        System.out.println("\n===== Question 2: Largest Color Value in a Directed Graph (LeetCode) - Hard =====");
        System.out.println("🔗 https://leetcode.com/problems/largest-color-value-in-a-directed-graph/");
        Solution2 sol2 = new Solution2();
        String colors = "abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};
        int maxColor = sol2.largestPathValue(colors, edges);
        System.out.println("Largest Color Value: " + maxColor);

        System.out.println("\n===== Question 3: Flood Fill (LeetCode) - Easy =====");
        System.out.println("🔗 https://leetcode.com/problems/flood-fill/");
        Solution3 sol3 = new Solution3();
        int[][] image = {{1,1,1},{1,1,0},{1,0,1}};
        int[][] filled = sol3.floodFill(image, 1, 1, 2);
        System.out.println("Flood Filled Image: " + Arrays.deepToString(filled));
    }
}

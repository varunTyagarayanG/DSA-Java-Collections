/*
Graphs: BFS, DFS, and Related Problems Links (in order):
1. DFS Traversal: https://www.geeksforgeeks.org/graph-dfs/
2. BFS Traversal: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
3. Convert Adjacency Matrix to Adjacency List: https://www.geeksforgeeks.org/graph-representations-using-adjacency-list-and-matrix/
4. Minimum Steps by Knight: https://www.geeksforgeeks.org/problems/steps-by-knight5927/1
5. Path Exists in Graph (DFS): https://leetcode.com/problems/find-if-path-exists-in-graph/
6. Reorder Routes to Make All Paths Lead to the City Zero: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/
*/

import java.util.*;

public class B2_Day_23_24_Graphs_BFS_DFS {

    // ========================= Question 1: DFS Traversal =============================
    // Link: https://www.geeksforgeeks.org/graph-dfs/
    static void dfs(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        System.out.print(node + " ");
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited, adj);
            }
        }
    }

    // ========================= Question 2: BFS Traversal =============================
    // Link: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    static void bfs(int start, List<List<Integer>> adj) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // ========================= Question 3: Convert Matrix to Adjacency List =============================
    // Link: https://www.geeksforgeeks.org/graph-representations-using-adjacency-list-and-matrix/
    static List<List<Integer>> convertToAdjList(int[][] matrix) {
        int n = matrix.length;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    adj.get(i).add(j);
                }
            }
        }
        return adj;
    }

    // ========================= Question 4: Minimum Steps by Knight =============================
    // Link: https://www.geeksforgeeks.org/problems/steps-by-knight5927/1
    static int minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {
        Queue<int[]> q = new LinkedList<>();
        int[][] directions = {
            {-1, -2}, {-1, 2}, {1, -2}, {1, 2},
            {-2, -1}, {2, -1}, {-2, 1}, {2, 1}
        };
        boolean[][] visited = new boolean[n + 1][n + 1];
        q.add(new int[]{knightPos[0], knightPos[1]});
        visited[knightPos[0]][knightPos[1]] = true;

        int steps = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pos = q.poll();
                if (pos[0] == targetPos[0] && pos[1] == targetPos[1]) return steps;
                for (int[] d : directions) {
                    int x = pos[0] + d[0];
                    int y = pos[1] + d[1];
                    if (x >= 1 && x <= n && y >= 1 && y <= n && !visited[x][y]) {
                        visited[x][y] = true;
                        q.add(new int[]{x, y});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    // ========================= Question 5: Path Exists in Graph (DFS) =============================
    // Link: https://leetcode.com/problems/find-if-path-exists-in-graph/
    static boolean dfsPath(int node, int dest, List<List<Integer>> adj, boolean[] visited) {
        if (node == dest) return true;
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor] && dfsPath(neighbor, dest, adj, visited)) {
                return true;
            }
        }
        return false;
    }

    static boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        return dfsPath(source, destination, adj, new boolean[n]);
    }

    // ========================= Question 6: Reorder Routes to Make All Paths Lead to City Zero =============================
    // Link: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/
    static int reorderRoutes(int n, int[][] connections) {
        // TODO: Implement this method
        return -1;
    }

    // ========================= main() =============================
    public static void main(String[] args) {

        int[][] mat = {
            {0, 1, 1, 0},
            {1, 0, 1, 1},
            {1, 1, 0, 0},
            {0, 1, 0, 0}
        };
        List<List<Integer>> adjList = convertToAdjList(mat);

        System.out.println("=============== QUESTION 1: DFS Traversal ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/graph-dfs/");
        boolean[] visited1 = new boolean[adjList.size()];
        dfs(0, visited1, adjList);
        System.out.println();

        System.out.println("\n=============== QUESTION 2: BFS Traversal ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/");
        bfs(0, adjList);
        System.out.println();

        System.out.println("\n=============== QUESTION 3: Convert Matrix to Adjacency List ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/graph-representations-using-adjacency-list-and-matrix/");
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print(i + " -> ");
            for (int node : adjList.get(i)) {
                System.out.print(node + " ");
            }
            System.out.println();
        }

        System.out.println("\n=============== QUESTION 4: Minimum Steps by Knight ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/steps-by-knight5927/1");
        int[] knight = {4, 5}, target = {1, 1};
        int boardSize = 6;
        System.out.println("Result: " + minStepToReachTarget(knight, target, boardSize));

        System.out.println("\n=============== QUESTION 5: Path Exists in Graph (DFS) ===============");
        System.out.println("Link: https://leetcode.com/problems/find-if-path-exists-in-graph/");
        int[][] edges = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("Result: " + validPath(3, edges, 0, 2));

        System.out.println("\n=============== QUESTION 6: Reorder Routes to Make All Paths Lead to City Zero ===============");
        System.out.println("Link: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/description/");
        int[][] conns = {{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}};
        System.out.println("Result: " + reorderRoutes(6, conns) + " (placeholder)");
    }
}

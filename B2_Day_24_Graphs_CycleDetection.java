// Question 1: Detect Cycle in an Undirected Graph using BFS
// Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

// Question 2: Detect Cycle in a Directed Graph using DFS
// Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

import java.util.*;

public class B2_Day_24_Graphs_CycleDetection {

    // ====================== Methods for Question 1 ===========================
    // BFS method for cycle detection in an undirected graph
    public static boolean bfsUndirected(int start, List<List<Integer>> adj, boolean[] isVisited, int[] parent) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        isVisited[start] = true;
        parent[start] = -1;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int neighbor : adj.get(node)) {
                if (!isVisited[neighbor]) {
                    isVisited[neighbor] = true;
                    q.add(neighbor);
                    parent[neighbor] = node;
                } else if (neighbor != parent[node]) {
                    return true;  // Cycle found
                }
            }
        }

        return false;
    }

    // Main method for detecting cycle in an undirected graph
    public static boolean isCycleUndirected(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]); // Undirected
        }

        boolean[] isVisited = new boolean[V];
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        for (int i = 0; i < V; i++) {
            if (!isVisited[i]) {
                if (bfsUndirected(i, adj, isVisited, parent)) {
                    return true;
                }
            }
        }

        return false;
    }
    // =================== End of Methods for Question 1 =======================


    // ====================== Methods for Question 2 ===========================
    // DFS method for cycle detection in a directed graph
    public static boolean dfsDirected(int node, List<List<Integer>> adj, boolean[] isVisited, boolean[] currPath) {
        isVisited[node] = true;
        currPath[node] = true;

        for (int i : adj.get(node)) {
            if (!isVisited[i]) {
                if (dfsDirected(i, adj, isVisited, currPath)) return true;
            } else if (currPath[i]) {
                return true;
            }
        }

        currPath[node] = false;
        return false;
    }

    // Main method for detecting cycle in a directed graph
    public static boolean isCycleDirected(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]); // Directed
        }

        boolean[] isVisited = new boolean[V];
        boolean[] currPath = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!isVisited[i]) {
                if (dfsDirected(i, adj, isVisited, currPath)) {
                    return true;
                }
            }
        }

        return false;
    }
    // =================== End of Methods for Question 2 =======================


    public static void main(String[] args) {

        // ==================== Test for Question 1 ====================
        System.out.println("===== Question 1: Cycle Detection in Undirected Graph =====");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1");

        int V1 = 5;
        int[][] edgesWithCycle1 = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 0}
        };

        int[][] edgesWithoutCycle1 = {
            {0, 1},
            {1, 2},
            {2, 3}
        };

        System.out.println("Graph 1 (with cycle): " + isCycleUndirected(V1, edgesWithCycle1));  // true
        System.out.println("Graph 2 (without cycle): " + isCycleUndirected(V1, edgesWithoutCycle1));  // false

        // ==================== Test for Question 2 ====================
        System.out.println("\n===== Question 2: Cycle Detection in Directed Graph =====");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1");

        int V2 = 4;
        int[][] edgesWithCycle2 = {
            {0, 1},
            {1, 2},
            {2, 3},
            {3, 1}
        };

        int[][] edgesWithoutCycle2 = {
            {0, 1},
            {1, 2},
            {2, 3}
        };

        System.out.println("Graph 3 (with cycle): " + isCycleDirected(V2, edgesWithCycle2));  // true
        System.out.println("Graph 4 (without cycle): " + isCycleDirected(V2, edgesWithoutCycle2));  // false
    }
}

/*
========================= B2 Day 26: Graph Cycles & Topological Sort =============================
Problems Covered:
1. Eventual Safe States (GFG) - https://www.geeksforgeeks.org/problems/eventual-safe-states/1
2. Longest Cycle in a Graph (LeetCode) - https://leetcode.com/problems/longest-cycle-in-a-graph/
3. Shortest Cycle in a Graph (LeetCode) - https://leetcode.com/problems/shortest-cycle-in-a-graph/
4. Topological Sort (Kahn's Algorithm) (GFG) - https://www.geeksforgeeks.org/problems/topological-sort/1
5. Topological Sort (DFS-based) (GFG) - https://www.geeksforgeeks.org/problems/topological-sort/1
===============================================================================================
*/

// ========================= B2 Day 26: Graphs =============================

import java.util.*;

public class B2_Day_26_GraphCycles_TopologicalSort {

    // ===== Question 1: Eventual Safe States =====
    // 🔗 https://www.geeksforgeeks.org/problems/eventual-safe-states/1
    // ========= START =========
    public static List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
        boolean[] isVisited = new boolean[V];
        boolean[] currPath = new boolean[V];
        int[] check = new int[V];

        for (int i = 0; i < V; i++) {
            if (!isVisited[i]) {
                dfsSafeNodes(i, adj, isVisited, currPath, check);
            }
        }

        ArrayList<Integer> safeNode = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (check[i] == 1) {
                safeNode.add(i);
            }
        }

        return safeNode;
    }

    private static boolean dfsSafeNodes(int node, List<List<Integer>> adj, boolean[] isVisited, boolean[] currPath, int[] check) {
        isVisited[node] = true;
        currPath[node] = true;

        for (int i : adj.get(node)) {
            if (!isVisited[i]) {
                if (dfsSafeNodes(i, adj, isVisited, currPath, check)) {
                    check[node] = 0;
                    return true;
                }
            } else if (currPath[i]) {
                return true;
            }
        }

        check[node] = 1;
        currPath[node] = false;
        return false;
    }
    // ========= END =========


    // ===== Question 2: Longest Cycle in a Graph =====
    // 🔗 https://leetcode.com/problems/longest-cycle-in-a-graph/
    // ========= START =========
    public static int longestCycle(int[] edges) {
        int n = edges.length;
        boolean[] isVisited = new boolean[n];
        boolean[] isInPath = new boolean[n];
        int[] count = new int[n];
        Arrays.fill(count, 1);
        int ans = -1;

        for (int i = 0; i < n; i++) {
            if (!isVisited[i]) {
                ans = Math.max(ans, dfsLongestCycle(i, edges, isVisited, isInPath, count));
            }
        }

        return ans;
    }

    private static int dfsLongestCycle(int node, int[] edges, boolean[] isVisited, boolean[] isInPath, int[] count) {
        isVisited[node] = true;
        isInPath[node] = true;
        int ans = -1;

        int k = edges[node];
        if (k != -1 && !isVisited[k]) {
            count[k] = count[node] + 1;
            ans = dfsLongestCycle(k, edges, isVisited, isInPath, count);
        } else if (k != -1 && isInPath[k]) {
            ans = Math.max(ans, count[node] - count[k] + 1);
        }

        isInPath[node] = false;
        return ans;
    }
    // ========= END =========


    // ===== Question 3: Shortest Cycle in a Graph =====
    // 🔗 https://leetcode.com/problems/shortest-cycle-in-a-graph/
    // ========= START =========
    public static int findShortestCycle(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        int ans = Integer.MAX_VALUE;

        for (int start = 0; start < n; start++) {
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            int[] parent = new int[n];
            Arrays.fill(parent, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            dist[start] = 0;

            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : adj.get(u)) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        parent[v] = u;
                        q.add(v);
                    } else if (parent[u] != v) {
                        ans = Math.min(ans, dist[u] + dist[v] + 1);
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    // ========= END =========


    // ===== Question 4: Topological Sort (Kahn's Algorithm) =====
    // 🔗 https://www.geeksforgeeks.org/problems/topological-sort/1
    // ========= START =========
    public static ArrayList<Integer> topoSort_Kahn(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) adj.get(edge[0]).add(edge[1]);

        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j : adj.get(i)) inDegree[j]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        ArrayList<Integer> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            int f = q.poll();
            ans.add(f);
            for (int i : adj.get(f)) {
                inDegree[i]--;
                if (inDegree[i] == 0) q.add(i);
            }
        }

        return ans;
    }
    // ========= END =========


    // ===== Question 5: Topological Sort (DFS-based) =====
    // 🔗 https://www.geeksforgeeks.org/problems/topological-sort/1
    // ========= START =========
    public static ArrayList<Integer> topoSort_DFS(int V, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] edge : edges) adj.get(edge[0]).add(edge[1]);

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfsTopo(i, adj, visited, stack);
        }

        ArrayList<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private static void dfsTopo(int node, List<List<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int i : adj.get(node)) {
            if (!visited[i]) dfsTopo(i, adj, visited, stack);
        }
        stack.push(node);
    }
    // ========= END =========


    public static void main(String[] args) {
        System.out.println("========= B2 Day 26 - Graph Cycles & Topological Sort =========");

        // ============== Test for Question 1 ==============
        System.out.println("\n===== Question 1: Eventual Safe States =====");
        System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/eventual-safe-states/1");
        List<List<Integer>> adj1 = Arrays.asList(
            Arrays.asList(1, 2),
            Arrays.asList(2, 3),
            Arrays.asList(5),
            Arrays.asList(0),
            Arrays.asList(5),
            Arrays.asList(),
            Arrays.asList()
        );
        System.out.println("Safe Nodes: " + eventualSafeNodes(7, adj1));

        // ============== Test for Question 2 ==============
        System.out.println("\n===== Question 2: Longest Cycle in a Graph =====");
        System.out.println("🔗 Link: https://leetcode.com/problems/longest-cycle-in-a-graph/");
        int[] edges = {3, 3, 4, 2, 3};
        System.out.println("Longest Cycle Length: " + longestCycle(edges));

        // ============== Test for Question 3 ==============
        System.out.println("\n===== Question 3: Shortest Cycle in a Graph =====");
        System.out.println("🔗 Link: https://leetcode.com/problems/shortest-cycle-in-a-graph/");
        int[][] edgeList = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
        System.out.println("Shortest Cycle Length: " + findShortestCycle(4, edgeList));

        // ============== Test for Question 4 ==============
        System.out.println("\n===== Question 4: Topological Sort (Kahn's Algorithm) =====");
        System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/topological-sort/1");
        int[][] topoEdges = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        System.out.println("Topo Sort (Kahn): " + topoSort_Kahn(6, topoEdges));

        // ============== Test for Question 5 ==============
        System.out.println("\n===== Question 5: Topological Sort (DFS-based) =====");
        System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/topological-sort/1");
        System.out.println("Topo Sort (DFS): " + topoSort_DFS(6, topoEdges));
    }
}

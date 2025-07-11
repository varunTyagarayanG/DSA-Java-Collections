// ========================= B2 Day 26: Graphs =============================

import java.util.*;

public class B2_Day_26_GraphCycles_TopologicalSort {

    // Question 1: Eventual Safe States
    // 🔗 https://www.geeksforgeeks.org/problems/eventual-safe-states/1
    static class Solution1 {
        public boolean dfs(int node , List<List<Integer>> adj ,boolean[] isVisited ,boolean[] currPath ,int[]check){
            isVisited[node] = true ;
            currPath[node] = true ;

            for(int i : adj.get(node)){
                if(!isVisited[i]){
                    if(dfs(i,adj ,isVisited,currPath , check)) {
                        check[node] = 0 ;  
                        return true ;
                    }
                }
                else if(currPath[i]){
                    return true ;
                }
            }
            check[node] = 1 ; 
            currPath[node] = false ;
            return false ;
        }

        List<Integer> eventualSafeNodes(int V, List<List<Integer>> adj) {
            boolean[] isVisited = new boolean[V] ;
            boolean[] currPath = new boolean[V] ;
            int[] check = new int[V] ;

            for(int i = 0 ; i < V; i++){
                if(!isVisited[i]){
                    dfs(i ,adj , isVisited ,currPath ,check);
                }
            }

            ArrayList<Integer> safeNode = new ArrayList<>() ;
            for(int i = 0 ; i < V ; i++){
                if(check[i] == 1){
                    safeNode.add(i) ;
                }
            }

            return safeNode ;
        }
    }

    // Question 2: Longest Cycle in a Graph
    // 🔗 https://leetcode.com/problems/longest-cycle-in-a-graph/
    static class Solution2 {
        int ans = -1 ;

        public void dfsFindLongestCycle(int node ,int[]edges , boolean[] isVisited ,boolean[] isInPath , int[]count){
            isVisited[node] = true ;
            isInPath[node] = true ;  
            int  k = edges[node] ;

            if(k != -1 && !isVisited[k]){
                isInPath[k] = true ;
                isVisited[k] = true ;
                count[k] = count[node] + 1 ;
                dfsFindLongestCycle(k , edges ,isVisited,isInPath,count);
            }
            else if(k != -1 && isInPath[k]){
                ans = Math.max(ans , count[node] - count[k] + 1 );
            }

            isInPath[node] = false ;
        }

        public int longestCycle(int[] edges) {
            int n = edges.length ;  
            boolean[] isVisited  = new boolean[n] ;
            boolean[] isInPath = new boolean[n] ;
            int[] count = new int[n] ;
            Arrays.fill(count , 1) ;

            for(int i = 0 ; i<n ; i++){
                if(!isVisited[i]){
                    dfsFindLongestCycle(i,edges,isVisited,isInPath,count) ;
                }
            }

            return ans ;     
        }
    }

    // Question 3: Shortest Cycle in a Graph
    // 🔗 https://leetcode.com/problems/shortest-cycle-in-a-graph/
    static class Solution3 {
        public int findShortestCycle(int n, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for(int i = 0 ; i < n ; i++){
                adj.add(new ArrayList<>());
            }

            for(int[] edge : edges){
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }

            int ans = Integer.MAX_VALUE;

            for(int start = 0 ; start < n ; start++) {
                int[] dist = new int[n];
                Arrays.fill(dist, -1);
                Queue<Integer> q = new LinkedList<>();
                q.add(start);
                dist[start] = 0;
                int[] parent = new int[n];
                Arrays.fill(parent, -1);

                while(!q.isEmpty()) {
                    int u = q.poll();

                    for(int v : adj.get(u)) {
                        if(dist[v] == -1) {
                            dist[v] = dist[u] + 1;
                            parent[v] = u;
                            q.add(v);
                        } else if(parent[u] != v) {
                            ans = Math.min(ans, dist[u] + dist[v] + 1);
                        }
                    }
                }
            }

            return ans == Integer.MAX_VALUE ? -1 : ans;
        }
    }

    // Question 4: Topological Sort (Kahn's Algorithm)
    // 🔗 https://www.geeksforgeeks.org/problems/topological-sort/1
    static class Solution4_Kahn {
        public static ArrayList<Integer> topoSort(int V, int[][] edges) {
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

            Queue<Integer> topo = new LinkedList<>();
            ArrayList<Integer> ans = new ArrayList<>();

            for (int i = 0; i < V; i++) {
                if (inDegree[i] == 0) {
                    topo.add(i);
                }
            }

            while (!topo.isEmpty()) {
                int f = topo.poll();
                ans.add(f);

                for (int i : adj.get(f)) {
                    inDegree[i]--;
                    if (inDegree[i] == 0) {
                        topo.add(i);
                    }
                }
            }

            return ans;
        }
    }

    // Question 5: Topological Sort (DFS-based)
    // 🔗 https://www.geeksforgeeks.org/problems/topological-sort/1
    static class Solution5_DFS {
        static Stack<Integer> ans = new Stack<>();

        public static void dfs(int node, List<List<Integer>> adj, boolean[] isVisited) {
            isVisited[node] = true;

            for (int i : adj.get(node)) {
                if (!isVisited[i]) {
                    dfs(i, adj, isVisited);
                }
            }

            ans.add(node);
        }

        public static ArrayList<Integer> topoSort(int V, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
            }

            boolean[] isVisited = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!isVisited[i]) {
                    dfs(i, adj, isVisited);
                }
            }

            ArrayList<Integer> lst = new ArrayList<>();
            while (!ans.isEmpty()) {
                lst.add(ans.pop());
            }

            return lst;
        }
    }

    public static void main(String[] args) {

    System.out.println("Day 26 - Graphs");

    // ============== Test for Question 1 ==============
    System.out.println("\n===== Question 1: Eventual Safe States =====");
    System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/eventual-safe-states/1");
    Solution1 s1 = new Solution1();
    List<List<Integer>> adj1 = Arrays.asList(
        Arrays.asList(1, 2),
        Arrays.asList(2, 3),
        Arrays.asList(5),
        Arrays.asList(0),
        Arrays.asList(5),
        Arrays.asList(),
        Arrays.asList()
    );
    System.out.println("Safe Nodes: " + s1.eventualSafeNodes(7, adj1));

    // ============== Test for Question 2 ==============
    System.out.println("===== Question 2: Longest Cycle in a Graph =====");
    System.out.println("🔗 Link: https://leetcode.com/problems/longest-cycle-in-a-graph/");
    Solution2 s2 = new Solution2();
    int[] edges2 = {3, 3, 4, 2, 3};
    System.out.println("Longest Cycle Length: " + s2.longestCycle(edges2));

    // ============== Test for Question 3 ==============
    System.out.println("\n===== Question 3: Shortest Cycle in a Graph =====");
    System.out.println("🔗 Link: https://leetcode.com/problems/shortest-cycle-in-a-graph/");
    Solution3 s3 = new Solution3();
    int[][] edges3 = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
    System.out.println("Shortest Cycle Length: " + s3.findShortestCycle(4, edges3));

    // ============== Test for Question 4 ==============
    System.out.println("\n===== Question 4: Topological Sort (Kahn's Algorithm) =====");
    System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/topological-sort/1");
    int[][] edges4 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
    System.out.println("Topo Sort (Kahn): " + Solution4_Kahn.topoSort(4, edges4));

    // ============== Test for Question 5 ==============
    System.out.println("\n===== Question 5: Topological Sort (DFS) =====");
    System.out.println("🔗 Link: https://www.geeksforgeeks.org/problems/topological-sort/1");
    int[][] edges5 = {{0, 1}, {0, 2}, {1, 3}, {2, 3}};
    System.out.println("Topo Sort (DFS): " + Solution5_DFS.topoSort(4, edges5));
}

}

/*
 * Binary Trees 2 Links (in order):
 * 1. K Sum Paths: https://www.geeksforgeeks.org/problems/k-sum-paths/1
 * 2. Sum of the Longest Bloodline of a Tree: https://www.geeksforgeeks.org/problems/sum-of-the-longest-bloodline-of-a-tree/1
 * 3. Lowest Common Ancestor in a Binary Tree: https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1
 * 4. Kth Ancestor in a Binary Tree: https://www.geeksforgeeks.org/problems/kth-ancestor-in-a-tree/1
 */

import java.util.*;

class B2_Day_14_BinaryTrees {

    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static int idx = 0;
    static int[] treeInput = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1}; // sample input

    public static Node buildTree() {
        if (idx >= treeInput.length || treeInput[idx] == -1) {
            idx++;
            return null;
        }

        Node root = new Node(treeInput[idx++]);
        root.left = buildTree();
        root.right = buildTree();
        return root;
    }

    // ========================= Question 1 =============================
    // Print all K Sum Paths
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/k-sum-paths/1
    public static void kSumHelper(Node root, int k, int[] count, ArrayList<Integer> lst) {
        if (root == null) return;

        lst.add(root.data);

        kSumHelper(root.left, k, count, lst);
        kSumHelper(root.right, k, count, lst);

        int sum = 0;
        for (int i = lst.size() - 1; i >= 0; i--) {
            sum += lst.get(i);
            if (sum == k) count[0]++;
        }

        lst.remove(lst.size() - 1);
    }

    public static int sumK(Node root, int k) {
        int[] count = new int[1];
        kSumHelper(root, k, count, new ArrayList<>());
        return count[0];
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 2 =============================
    // Sum of the Longest Bloodline of a Tree
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/sum-of-the-longest-bloodline-of-a-tree/1
    public static void findLongestPath(Node root, int currSum, int currLength, int[] maxLength, int[] maxSum) {
        if (root == null) return;

        currSum += root.data;
        currLength++;

        if (root.left == null && root.right == null) {
            if (currLength > maxLength[0]) {
                maxLength[0] = currLength;
                maxSum[0] = currSum;
            } else if (currLength == maxLength[0]) {
                maxSum[0] = Math.max(maxSum[0], currSum);
            }
            return;
        }

        findLongestPath(root.left, currSum, currLength, maxLength, maxSum);
        findLongestPath(root.right, currSum, currLength, maxLength, maxSum);
    }

    public static int sumOfLongRootToLeafPath(Node root) {
        int[] maxLength = new int[1];
        int[] maxSum = new int[1];
        findLongestPath(root, 0, 0, maxLength, maxSum);
        return maxSum[0];
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 3 =============================
    // Lowest Common Ancestor in a Binary Tree
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1
    public static Node lca(Node root, int n1, int n2) {
        if (root == null) return null;

        if (root.data == n1 || root.data == n2) return root;

        Node left = lca(root.left, n1, n2);
        Node right = lca(root.right, n1, n2);

        if (left != null && right != null) return root;

        return left != null ? left : right;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 4 =============================
    // Kth Ancestor in a Binary Tree
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/kth-ancestor-in-a-tree/1
    static Node ans = null;

    public static int kthAncestor(Node root, int k, int node) {
        ans = null; // Reset global before each call
        int[] kRef = new int[]{k};
        solveKthAncestor(root, kRef, node);
        return (ans != null) ? ans.data : -1;
    }

    private static Node solveKthAncestor(Node root, int[] k, int node) {
        if (root == null) return null;

        if (root.data == node) return root;

        Node left = solveKthAncestor(root.left, k, node);
        Node right = solveKthAncestor(root.right, k, node);

        if (left != null || right != null) {
            k[0]--;
            if (k[0] == 0 && ans == null) {
                ans = root;
                return null;
            }
            return root;
        }

        return null;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=============== DAY 13-14: BINARY TREES 2 ===============");

        idx = 0;
        Node root = buildTree();

        // Question 1
        System.out.println("\n=============== QUESTION 1: K SUM PATHS ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/k-sum-paths/1");
        int k1 = 7;
        int countPaths = sumK(root, k1);
        System.out.println("Number of K-Sum Paths: " + countPaths);

        // Question 2
        System.out.println("\n=============== QUESTION 2: SUM OF LONGEST BLOODLINE ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/sum-of-the-longest-bloodline-of-a-tree/1");
        int sum = sumOfLongRootToLeafPath(root);
        System.out.println("Sum of Longest Root-to-Leaf Path: " + sum);

        // Question 3
        System.out.println("\n=============== QUESTION 3: LOWEST COMMON ANCESTOR ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-binary-tree/1");
        int n1 = 4, n2 = 5;
        Node ancestor = lca(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + ": " + (ancestor != null ? ancestor.data : "null"));

        // Question 4
        System.out.println("\n=============== QUESTION 4: KTH ANCESTOR IN A TREE ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/kth-ancestor-in-a-tree/1");
        int target = 5, k2 = 2;
        int result = kthAncestor(root, k2, target);
        System.out.println("Kth Ancestor of " + target + " with k=" + k2 + " is: " + result);
    }
}

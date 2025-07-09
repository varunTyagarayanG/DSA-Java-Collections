import java.util.*;

public class B2_Day_16_BST_PQ {

    // ===== TreeNode Definition =====
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    static int idx = 0;
    static int[] treeInput = {30, 20, 10, -1, -1, -1, 40, -1, 50, -1, 60, -1, -1}; // Sample unbalanced BST

    public static TreeNode buildBST() {
        if (idx >= treeInput.length || treeInput[idx] == -1) {
            idx++;
            return null;
        }
        TreeNode root = new TreeNode(treeInput[idx++]);
        root.left = buildBST();
        root.right = buildBST();
        return root;
    }

    // ========================= Question 1 =============================
    // Lowest Common Ancestor in a BST
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1
    public static TreeNode LCA(TreeNode root, TreeNode n1, TreeNode n2) {
        if (root == null) return null;

        if (root.val > n1.val && root.val > n2.val) {
            return LCA(root.left, n1, n2);
        } else if (root.val < n1.val && root.val < n2.val) {
            return LCA(root.right, n1, n2);
        }

        return root;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 2 =============================
    // Balance a Binary Search Tree
    // 🔗 LeetCode: https://leetcode.com/problems/balance-a-binary-search-tree/
    public static TreeNode balanceBST(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        inorder(root, lst);
        return buildBalancedBST(lst, 0, lst.size() - 1);
    }

    private static void inorder(TreeNode root, List<Integer> lst) {
        if (root == null) return;
        inorder(root.left, lst);
        lst.add(root.val);
        inorder(root.right, lst);
    }

    private static TreeNode buildBalancedBST(List<Integer> lst, int start, int end) {
        if (start > end) return null;
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(lst.get(mid));
        node.left = buildBalancedBST(lst, start, mid - 1);
        node.right = buildBalancedBST(lst, mid + 1, end);
        return node;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 3 =============================
    // Kth Smallest Element using PriorityQueue
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1
    public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int val : arr) {
            maxHeap.add(val);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.poll();
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 4 =============================
    // Kth Largest Element in an Array
    // 🔗 LeetCode: https://leetcode.com/problems/kth-largest-element-in-an-array/
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int val : nums) {
            minHeap.add(val);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        return minHeap.poll();
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 5 =============================
    // Nearly Sorted (K-Sorted) Array
    // 🔗 GFG: https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1
    public static void nearlySorted(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int index = 0;

        for (int val : arr) {
            minHeap.add(val);
            if (minHeap.size() > k) {
                arr[index++] = minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------

    public static void printLevelOrder(TreeNode root) {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");
                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=============== DAY 16: BST QUESTIONS + PRIORITY QUEUE ===============");

        idx = 0;
        TreeNode root = buildBST();

        // Question 1
        System.out.println("\n=============== QUESTION 1: LOWEST COMMON ANCESTOR IN BST ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/lowest-common-ancestor-in-a-bst/1");
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(60);
        TreeNode lca = LCA(root, n1, n2);
        System.out.println("LCA of " + n1.val + " and " + n2.val + " is: " + (lca != null ? lca.val : "null"));

        // Question 2
        System.out.println("\n=============== QUESTION 2: BALANCE A BINARY SEARCH TREE ===============");
        System.out.println("Link: https://leetcode.com/problems/balance-a-binary-search-tree/");
        System.out.println("Level Order of Original (Unbalanced) BST:");
        printLevelOrder(root);
        TreeNode balancedRoot = balanceBST(root);
        System.out.println("Level Order of Balanced BST:");
        printLevelOrder(balancedRoot);

        // PriorityQueue Section Begins
        System.out.println("\n=============== PRIORITY QUEUE QUESTIONS BEGIN NOW ===============");

        // Question 3
        System.out.println("\n=============== QUESTION 3: KTH SMALLEST ELEMENT USING PRIORITY QUEUE ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/kth-smallest-element5635/1");
        int[] arr1 = {7, 10, 4, 3, 20, 15};
        int k1 = 3;
        System.out.println("Kth smallest element (k=" + k1 + ") is: " + kthSmallest(arr1, k1));

        // Question 4
        System.out.println("\n=============== QUESTION 4: KTH LARGEST ELEMENT IN ARRAY ===============");
        System.out.println("Link: https://leetcode.com/problems/kth-largest-element-in-an-array/");
        int[] arr2 = {3, 2, 1, 5, 6, 4};
        int k2 = 2;
        System.out.println("Kth largest element (k=" + k2 + ") is: " + findKthLargest(arr2, k2));

        // Question 5
        System.out.println("\n=============== QUESTION 5: NEARLY SORTED ARRAY ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/nearly-sorted-1587115620/1");
        int[] arr3 = {6, 5, 3, 2, 8, 10, 9};
        int k3 = 3;
        System.out.print("Original array: ");
        System.out.println(Arrays.toString(arr3));
        nearlySorted(arr3, k3);
        System.out.print("Nearly sorted: ");
        System.out.println(Arrays.toString(arr3));
    }
}

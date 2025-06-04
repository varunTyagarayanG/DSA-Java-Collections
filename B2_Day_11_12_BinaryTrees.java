import java.util.*;

class TreeNode {
    int data;
    TreeNode left, right;

    TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class B2_Day_11_12_BinaryTrees {

    static int idx = 0;
    static int[] treeInput = {10, 5, 3, -1, -1, 7, -1, 8, -1, -1, 20, -1, 25, -1, -1};

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    // Build Tree + Traversals
    // LeetCode: https://leetcode.com/problems/binary-tree-inorder-traversal/
    public static TreeNode buildTree() {
        if (idx >= treeInput.length || treeInput[idx] == -1) {
            idx++;
            return null;
        }

        TreeNode root = new TreeNode(treeInput[idx++]);
        root.left = buildTree();
        root.right = buildTree();
        return root;
    }

    public static void printGraph(TreeNode root) {
        printGraphUtil(root, "", true);
    }

    private static void printGraphUtil(TreeNode root, String prefix, boolean isLeft) {
        if (root != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.data);
            printGraphUtil(root.left, prefix + (isLeft ? "│   " : "    "), true);
            printGraphUtil(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void preorder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static void postorder(TreeNode root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void levelOrderTraversal(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int n = q.size();
            for (int i = 0; i < n; i++) {
                TreeNode ele = q.poll();
                System.out.print(ele.data + " ");
                if (ele.left != null) q.add(ele.left);
                if (ele.right != null) q.add(ele.right);
            }
        }
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    public static int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left, right) + 1;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    static int diameter;

    public static int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        diameterHelper(root);
        return diameter;
    }

    private static int diameterHelper(TreeNode root) {
        if (root == null) return 0;
        int left = diameterHelper(root.left);
        int right = diameterHelper(root.right);
        diameter = Math.max(diameter, left + right);
        return Math.max(left, right) + 1;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    public static boolean isBalanced(TreeNode root) {
        return checkBalancedHeight(root) != -1;
    }

    private static int checkBalancedHeight(TreeNode root) {
        if (root == null) return 0;

        int lh = checkBalancedHeight(root.left);
        if (lh == -1) return -1;

        int rh = checkBalancedHeight(root.right);
        if (rh == -1) return -1;

        if (Math.abs(lh - rh) > 1) return -1;

        return Math.max(lh, rh) + 1;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    // Question 5 : ---------------------------------------------Start-------------------------------------------------
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.data != q.data) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------

    // Question 6 : ---------------------------------------------Start-------------------------------------------------
    public static boolean isSumTree(TreeNode root) {
        return sumTreeHelper(root) != -1;
    }

    private static int sumTreeHelper(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.data;

        int leftSum = sumTreeHelper(root.left);
        int rightSum = sumTreeHelper(root.right);

        if (leftSum == -1 || rightSum == -1 || root.data != leftSum + rightSum)
            return -1;

        return root.data + leftSum + rightSum;
    }
    // Question 6 : ---------------------------------------------End---------------------------------------------------

    // Question 7 : ---------------------------------------------Start-------------------------------------------------
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        boolean leftToRight = false ;

        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                level.add(node.data);

                if (node.left != null) q.add(node.left);
                if (node.right != null) q.add(node.right);
            }

            if (!leftToRight) Collections.reverse(level);
            ans.add(level);
            leftToRight = !leftToRight;
        }

        return ans;
    }
    // Question 7 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: BUILD BINARY TREE ===============");

        idx = 0;  // reset index before building
        TreeNode root = buildTree();

        // Traversals
        System.out.println("\nInorder Traversal:");
        inorder(root);

        System.out.println("\nPreorder Traversal:");
        preorder(root);

        System.out.println("\nPostorder Traversal:");
        postorder(root);

        System.out.println("\n\nLevel Order Traversal:");
        levelOrderTraversal(root);

        System.out.println("\n\nGraphical View of the Tree:");
        printGraph(root);

        // Question 2: Depth/Height
        System.out.println("\n=============== QUESTION 2: DEPTH OF BINARY TREE ===============");
        System.out.println("Depth: " + depth(root));

        // Question 3: Diameter
        System.out.println("\n=============== QUESTION 3: DIAMETER OF BINARY TREE ===============");
        System.out.println("Diameter: " + diameterOfBinaryTree(root));

        // Question 4: Balanced Tree
        System.out.println("\n=============== QUESTION 4: IS TREE BALANCED? ===============");
        System.out.println("Balanced? " + isBalanced(root));

        // Question 5: Same Tree
        System.out.println("\n=============== QUESTION 5: ARE TWO TREES SAME? ===============");
        System.out.println("Same Tree as Itself? " + isSameTree(root, root));

        // Question 6: Sum Tree
        System.out.println("\n=============== QUESTION 6: IS TREE A SUM TREE? ===============");
        System.out.println("Sum Tree? " + isSumTree(root));

        // Question 7: Zigzag Level Order
        System.out.println("\n=============== QUESTION 7: ZIGZAG LEVEL ORDER TRAVERSAL ===============");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        for (List<Integer> level : zigzag) {
            System.out.println(level);
        }
    }
}

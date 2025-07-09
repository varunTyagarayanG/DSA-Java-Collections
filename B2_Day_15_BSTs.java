/*
 * Binary Search Trees (BSTs) Links (in order):
 * 1. Insert a Node in BST: https://leetcode.com/problems/insert-into-a-binary-search-tree/
 * 2. Delete a Node in BST: https://leetcode.com/problems/delete-node-in-a-bst/
 * 3. Inorder, Preorder, Postorder Traversals of BST: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
 * 4. Validate if it is a BST: https://leetcode.com/problems/validate-binary-search-tree/
 * 5. Kth Smallest Element in BST: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * 6. Kth Largest Element in BST: https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
 */

import java.util.*;

public class B2_Day_15_BSTs {

    // ===== Node Definition =====
    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            this.data = val;
        }
    }

    // Root of the BST
    Node root;

    // ========================= Question 1 =============================
    // Insert a Node in BST
    // 🔗 LeetCode: https://leetcode.com/problems/insert-into-a-binary-search-tree/
    public Node insert(Node root, int val) {
        if (root == null) return new Node(val);

        if (val < root.data)
            root.left = insert(root.left, val);
        else
            root.right = insert(root.right, val);

        return root;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 2 =============================
    // Delete a Node in BST
    // 🔗 LeetCode: https://leetcode.com/problems/delete-node-in-a-bst/
    public Node delete(Node root, int key) {
        if (root == null) return null;

        if (key < root.data)
            root.left = delete(root.left, key);
        else if (key > root.data)
            root.right = delete(root.right, key);
        else {
            // One or zero children
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Two children: find inorder successor
            Node succ = getMin(root.right);
            root.data = succ.data;
            root.right = delete(root.right, succ.data);
        }

        return root;
    }

    // Helper to find min value node (used in delete)
    private Node getMin(Node node) {
        while (node.left != null)
            node = node.left;
        return node;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 3 =============================
    // Inorder, Preorder, Postorder Traversals of BST
    // 🔗 GFG: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/
    public void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 4 =============================
    // Validate if it is a BST
    // 🔗 LeetCode: https://leetcode.com/problems/validate-binary-search-tree/
    public boolean isValidBST(Node root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValid(Node root, long min, long max) {
        if (root == null) return true;
        if (root.data <= min || root.data >= max) return false;
        return isValid(root.left, min, root.data) && isValid(root.right, root.data, max);
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 5 =============================
    // Kth Smallest Element in BST
    // 🔗 LeetCode: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(Node root, int k) {
        Stack<Node> stack = new Stack<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }

            root = stack.pop();
            k--;
            if (k == 0) return root.data;
            root = root.right;
        }
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------

    // ========================= Question 6 =============================
    // Kth Largest Element in BST
    // 🔗 GFG: https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
    public int kthLargest(Node root, int k) {
        Stack<Node> stack = new Stack<>();

        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.right;
            }

            root = stack.pop();
            k--;
            if (k == 0) return root.data;
            root = root.left;
        }
    }
    // Question 6 : ---------------------------------------------End---------------------------------------------------

    public static void main(String[] args) {
        System.out.println("=============== DAY 15: BINARY SEARCH TREES ===============");

        B2_Day_15_BSTs tree = new B2_Day_15_BSTs();
        int[] values = {20, 10, 30, 5, 15, 25, 35};

        // Question 1: Insert
        System.out.println("\n=============== QUESTION 1: INSERT INTO BST ===============");
        System.out.println("Link: https://leetcode.com/problems/insert-into-a-binary-search-tree/");
        for (int val : values) {
            tree.root = tree.insert(tree.root, val);
        }

        // Question 3: Traversals
        System.out.println("\n=============== QUESTION 3: TRAVERSALS ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/tree-traversals-inorder-preorder-and-postorder/");
        System.out.print("Inorder: ");
        tree.inorder(tree.root); // 5 10 15 20 25 30 35
        System.out.print("\nPreorder: ");
        tree.preorder(tree.root);
        System.out.print("\nPostorder: ");
        tree.postorder(tree.root);

        // Question 4: Validate BST
        System.out.println("\n\n=============== QUESTION 4: VALIDATE BST ===============");
        System.out.println("Link: https://leetcode.com/problems/validate-binary-search-tree/");
        System.out.println("Is Valid BST? " + tree.isValidBST(tree.root));

        // Question 5: Kth Smallest
        System.out.println("\n=============== QUESTION 5: KTH SMALLEST ELEMENT ===============");
        System.out.println("Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/");
        int kSmall = 3;
        System.out.println("Kth Smallest (k=" + kSmall + "): " + tree.kthSmallest(tree.root, kSmall));

        // Question 6: Kth Largest
        System.out.println("\n=============== QUESTION 6: KTH LARGEST ELEMENT ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/");
        int kLarge = 2;
        System.out.println("Kth Largest (k=" + kLarge + "): " + tree.kthLargest(tree.root, kLarge));

        // Question 2: Delete
        System.out.println("\n=============== QUESTION 2: DELETE NODE FROM BST ===============");
        System.out.println("Link: https://leetcode.com/problems/delete-node-in-a-bst/");
        tree.root = tree.delete(tree.root, 10);
        System.out.print("Inorder after deleting 10: ");
        tree.inorder(tree.root); // Updated BST after deletion
    }
}

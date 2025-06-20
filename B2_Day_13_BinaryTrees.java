import java.util.*;

/*
Binary Tree Traversal and Views Links (in order):
1. Boundary Traversal: https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
2. Vertical Order Traversal: https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
3. Top View: https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
4. Bottom View: https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
5. Left View: https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1
*/

class Node {
    int data;
    Node left, right;
    Node(int data) {
        this.data = data;
        this.left = this.right = null;
    }
}

public class B2_Day_13_BinaryTrees {

    // Question 1: ---------------------------------------------Start-------------------------------------------------
    // Boundary Traversal
    // 🔗 https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1
    public static void leftBoundary(Node root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) return;
        ans.add(root.data);
        if (root.left != null) leftBoundary(root.left, ans);
        else leftBoundary(root.right, ans);
    }

    public static void rightBoundary(Node root, ArrayList<Integer> ans) {
        if (root == null || (root.left == null && root.right == null)) return;
        if (root.right != null) rightBoundary(root.right, ans);
        else rightBoundary(root.left, ans);
        ans.add(root.data);
    }

    public static void leafNodes(Node root, ArrayList<Integer> ans) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            ans.add(root.data);
            return;
        }
        leafNodes(root.left, ans);
        leafNodes(root.right, ans);
    }

    public static ArrayList<Integer> boundaryTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null) return ans;
        if (!(root.left == null && root.right == null)) ans.add(root.data);
        leftBoundary(root.left, ans);
        leafNodes(root, ans);
        rightBoundary(root.right, ans);
        return ans;
    }
    // Question 1: ---------------------------------------------End---------------------------------------------------


    // Question 2: ---------------------------------------------Start-------------------------------------------------
    // Vertical Order Traversal
    // 🔗 https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    static class VPair {
        Node key;
        int value;
        VPair(Node key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static ArrayList<ArrayList<Integer>> verticalOrder(Node root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        Queue<VPair> queue = new LinkedList<>();
        queue.offer(new VPair(root, 0));

        while (!queue.isEmpty()) {
            VPair current = queue.poll();
            Node node = current.key;
            int level = current.value;

            map.putIfAbsent(level, new ArrayList<>());
            map.get(level).add(node.data);

            if (node.left != null) queue.offer(new VPair(node.left, level - 1));
            if (node.right != null) queue.offer(new VPair(node.right, level + 1));
        }

        result.addAll(map.values());
        return result;
    }
    // Question 2: ---------------------------------------------End---------------------------------------------------


    // Question 3: ---------------------------------------------Start-------------------------------------------------
    // Top View
    // 🔗 https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    public static ArrayList<Integer> topView(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<VPair> q = new LinkedList<>();
        q.add(new VPair(root, 0));

        while (!q.isEmpty()) {
            VPair ele = q.poll();
            Node node = ele.key;
            int hd = ele.value;

            if (!map.containsKey(hd)) {
                map.put(hd, node.data);
            }

            if (node.left != null) q.add(new VPair(node.left, hd - 1));
            if (node.right != null) q.add(new VPair(node.right, hd + 1));
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int key : map.keySet()) ans.add(map.get(key));
        return ans;
    }
    // Question 3: ---------------------------------------------End---------------------------------------------------


    // Question 4: ---------------------------------------------Start-------------------------------------------------
    // Bottom View
    // 🔗 https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
    static class BPair<U, V> {
        private U key;
        private V value;

        public BPair(U key, V value) {
            this.key = key;
            this.value = value;
        }

        public U getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    public static ArrayList<Integer> bottomView(Node root) {
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<BPair<Node, Integer>> q = new LinkedList<>();
        q.add(new BPair<>(root, 0));

        while (!q.isEmpty()) {
            BPair<Node, Integer> ele = q.poll();
            Node node = ele.getKey();
            int hd = ele.getValue();

            map.put(hd, node.data);

            if (node.left != null) q.add(new BPair<>(node.left, hd - 1));
            if (node.right != null) q.add(new BPair<>(node.right, hd + 1));
        }

        ArrayList<Integer> ans = new ArrayList<>();
        for (int key : map.keySet()) ans.add(map.get(key));
        return ans;
    }
    // Question 4: ---------------------------------------------End---------------------------------------------------

    // Question 5: ---------------------------------------------Start-------------------------------------------------
    // Left View of Binary Tree
    // 🔗 https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1
    public static ArrayList<Integer> leftView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node curr = q.poll();

                if (i == 0) {
                    result.add(curr.data);
                }

                if (curr.left != null) q.add(curr.left);
                if (curr.right != null) q.add(curr.right);
            }
        }

        return result;
    }
    // Question 5: ---------------------------------------------End--------------------------------------------------
    // Tree Printer Utility
    public static void printTree(Node root) {
        printTreeHelper(root, "", true);
    }

    private static void printTreeHelper(Node node, String indent, boolean isLeft) {
        if (node == null) return;
        System.out.println(indent + (isLeft ? "├── " : "└── ") + node.data);
        if (node.left != null || node.right != null) {
            printTreeHelper(node.left, indent + (isLeft ? "│   " : "    "), true);
            printTreeHelper(node.right, indent + (isLeft ? "│   " : "    "), false);
        }
    }


    public static void main(String[] args) {
        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(20);
        root.left.left = new Node(3);
        root.left.right = new Node(7);
        root.left.right.right = new Node(8);
        root.right.right = new Node(25);
        System.out.println("\n=============== TREE STRUCTURE (2D VIEW) ===============");
        printTree(root);
        // Q1
        System.out.println("=============== QUESTION 1: BOUNDARY TRAVERSAL ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1");
        System.out.println("Boundary Traversal: " + boundaryTraversal(root));

        // Q2
        System.out.println("\n=============== QUESTION 2: VERTICAL ORDER TRAVERSAL ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1");
        ArrayList<ArrayList<Integer>> vertical = verticalOrder(root);
        for (ArrayList<Integer> col : vertical) {
            System.out.println(col);
        }

        // Q3
        System.out.println("\n=============== QUESTION 3: TOP VIEW ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/top-view-of-binary-tree/1");
        System.out.println("Top View: " + topView(root));

        // Q4
        System.out.println("\n=============== QUESTION 4: BOTTOM VIEW ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1");
        System.out.println("Bottom View: " + bottomView(root));
        
        // Q5
        System.out.println("\n=============== QUESTION 5: LEFT VIEW ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/left-view-of-binary-tree/1");
        ArrayList<Integer> view = leftView(root);
        System.out.println("Left View: " + view);
        

    }
}

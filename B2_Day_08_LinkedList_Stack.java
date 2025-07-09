/*
 * Linked List & Stack Problems:
 * 1. Sort Linked List using Merge Sort
 *    LeetCode: https://leetcode.com/problems/sort-list/
 * 2. Remove Duplicates from Sorted List II
 *    LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
 * 3. Flattening a Linked List
 *    GFG: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
 * 4. Next Greater Element (Topic: Stack)
 *    GFG: https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1
 * 5. Smallest Number on the Left (Topic: Stack)
 *    GFG: https://www.geeksforgeeks.org/problems/smallest-number-on-left3403/1
 */

import java.util.*;

public class B2_Day_08_LinkedList_Stack {

    // ========================= Linked List Node Class =============================
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    // ========================= Question 1 =============================
    public static ListNode getMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode merge(ListNode left, ListNode right) {
        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (left != null && right != null) {
            if (left.val > right.val) {
                tail.next = right;
                right = right.next;
            } else {
                tail.next = left;
                left = left.next;
            }
            tail = tail.next;
        }

        while (left != null) {
            tail.next = left;
            left = left.next;
            tail = tail.next;
        }

        while (right != null) {
            tail.next = right;
            right = right.next;
            tail = tail.next;
        }

        return dummy.next;
    }

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode left = head;
        ListNode right = getMid(head);
        ListNode temp = right.next;
        right.next = null;
        right = temp;

        left = sortList(left);
        right = sortList(right);

        return merge(left, right);
    }

    // ========================= Question 2 =============================
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode pt1 = head;

        while (pt1 != null && pt1.next != null) {
            if (pt1.val == pt1.next.val) {
                int repVal = pt1.val;
                while (pt1 != null && pt1.val == repVal) {
                    pt1 = pt1.next;
                }
                prev.next = pt1;
            } else {
                prev = pt1;
                pt1 = pt1.next;
            }
        }

        return dummy.next;
    }

    // ========================= Question 3 =============================
    static class Node {
        int data;
        Node next;
        Node bottom;

        Node(int d) {
            data = d;
            next = null;
            bottom = null;
        }
    }

    static Node merge(Node a, Node b) {
        if (a == null) return b;
        if (b == null) return a;

        Node result;

        if (a.data < b.data) {
            result = a;
            result.bottom = merge(a.bottom, b);
        } else {
            result = b;
            result.bottom = merge(a, b.bottom);
        }

        result.next = null;
        return result;
    }

    static Node flatten(Node root) {
        if (root == null || root.next == null)
            return root;

        root.next = flatten(root.next);
        root = merge(root, root.next);

        return root;
    }

    // ========================= Question 4 =============================
    public static ArrayList<Integer> nextLargerElement(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        ArrayList<Integer> ans = new ArrayList<>();

        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i]) {
                st.pop();
            }

            if (st.isEmpty()) {
                ans.add(0, -1);
            } else {
                ans.add(0, st.peek());
            }

            st.push(arr[i]);
        }
        return ans;
    }

    // ========================= Question 5 =============================
    public static int[] leftSmaller(int[] arr) {
        int n = arr.length;
        Stack<Integer> st = new Stack<>();
        int[] ans = new int[n];

        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && st.peek() >= arr[i]) {
                st.pop();
            }

            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(arr[i]);
        }

        return ans;
    }

    // ========================= Main Method =============================
    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1: Sort Linked List using Merge Sort ===============");
        System.out.println("Link: https://leetcode.com/problems/sort-list/");
        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode sorted = sortList(head);
        while (sorted != null) {
            System.out.print(sorted.val + " -> ");
            sorted = sorted.next;
        }
        System.out.println("null");

        System.out.println("\n=============== QUESTION 2: Remove Duplicates from Sorted List II ===============");
        System.out.println("Link: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/");
        ListNode head2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3, new ListNode(4, new ListNode(4, new ListNode(5)))))));
        ListNode result = deleteDuplicates(head2);
        while (result != null) {
            System.out.print(result.val + " -> ");
            result = result.next;
        }
        System.out.println("null");

        System.out.println("\n=============== QUESTION 3: Flattening a Linked List ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1");
        Node root = new Node(5);
        root.bottom = new Node(7);
        root.bottom.bottom = new Node(8);
        root.bottom.bottom.bottom = new Node(30);

        root.next = new Node(10);
        root.next.bottom = new Node(20);

        root.next.next = new Node(19);
        root.next.next.bottom = new Node(22);
        root.next.next.bottom.bottom = new Node(50);

        root.next.next.next = new Node(28);
        root.next.next.next.bottom = new Node(35);
        root.next.next.next.bottom.bottom = new Node(40);
        root.next.next.next.bottom.bottom.bottom = new Node(45);

        Node flatHead = flatten(root);
        while (flatHead != null) {
            System.out.print(flatHead.data + " -> ");
            flatHead = flatHead.bottom;
        }
        System.out.println("null");

        System.out.println("\n=============== TOPIC: STACK ==================");

        System.out.println("=============== QUESTION 4: Next Greater Element ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/next-larger-element-1587115620/1");
        int[] arr = {4, 5, 2, 25};
        ArrayList<Integer> res = nextLargerElement(arr);
        System.out.println(res);

        System.out.println("=============== QUESTION 5: Smallest Number on the Left ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/smallest-number-on-left3403/1");
        int[] arr2 = {1, 6, 4, 10, 2, 5};
        int[] res2 = leftSmaller(arr2);
        System.out.println(Arrays.toString(res2));
    }
}
/**
 * Definition for singly-linked list.
 */
import java.util.*;

class ListNode {
    int val;
    ListNode next;

    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class B2_Day_07_LinkedList {

    // Question 1 : ---------------------------------------------Start-------------------------------------------------
    // LeetCode: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;

        while (temp != null && temp.next != null) {
            if (temp.val != temp.next.val) {
                temp = temp.next;
            } else {
                temp.next = temp.next.next;
            }
        }

        return head;
    }
    // Question 1 : ---------------------------------------------End---------------------------------------------------

    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    // Code360: https://www.naukri.com/code360/problems/remove-duplicates-from-unsorted-linked-list_1069331
    public static ListNode removeDuplicatesUnsorted(ListNode head) {
        if (head == null) return null;

        HashSet<Integer> seen = new HashSet<>();
        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            if (seen.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                seen.add(curr.val);
                prev = curr;
            }
            curr = curr.next;
        }

        return head;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    // Sort linked list of 0s, 1s, and 2s using dummy nodes
    public static ListNode sortZeroOneTwoList(ListNode head) {
        if (head == null) return null;

        ListNode zeroDummy = new ListNode(-1);
        ListNode oneDummy = new ListNode(-1);
        ListNode twoDummy = new ListNode(-1);

        ListNode zero = zeroDummy, one = oneDummy, two = twoDummy;

        ListNode curr = head;
        while (curr != null) {
            if (curr.val == 0) {
                zero.next = curr;
                zero = zero.next;
            } else if (curr.val == 1) {
                one.next = curr;
                one = one.next;
            } else {
                two.next = curr;
                two = two.next;
            }
            curr = curr.next;
        }

        zero.next = (oneDummy.next != null) ? oneDummy.next : twoDummy.next;
        one.next = twoDummy.next;
        two.next = null;

        return zeroDummy.next;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    // LeetCode: https://leetcode.com/problems/merge-two-sorted-lists/
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {        
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        }

        if (list2 != null) {
            curr.next = list2;
        }

        return dummy.next;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    // Question 5 : ---------------------------------------------Start-------------------------------------------------
    // GFG: https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null)
            return true;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node secondHalf = reverseList(slow);
        Node firstHalf = head;

        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data)
                return false;
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }

        return true;
    }

    private static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }
    // Question 5 : ---------------------------------------------End---------------------------------------------------
    // Question 6 : Add two numbers represented by linked lists
    // GFG: https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1
    public static Node addTwoLists(Node l1, Node l2) {
        l1 = reverseNodeList(l1);
        l2 = reverseNodeList(l2);

        Node dummy = new Node(-1);
        Node temp = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            carry = sum / 10;
            temp.next = new Node(sum % 10);
            temp = temp.next;
        }

        return reverseNodeList(dummy.next);
    }

    private static Node reverseNodeList(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static void printList(ListNode head) {
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        /*
        Linked List Interview Patterns Links (in order):
        1. Merge Two Sorted Lists: https://leetcode.com/problems/merge-two-sorted-lists/
        2. Add Two Numbers as Linked Lists: https://leetcode.com/problems/add-two-numbers/
        3. Intersection Point in Y Shaped Linked Lists: https://www.geeksforgeeks.org/problems/intersection-point-in-y-shapped-linked-lists/1
        4. Remove Nth Node From End: https://leetcode.com/problems/remove-nth-node-from-end-of-list/
        5. Detect and Remove Cycle: https://leetcode.com/problems/linked-list-cycle/
        */

        // Q1
        System.out.println("=============== QUESTION 1: MERGE TWO SORTED LISTS ===============");
        System.out.println("Link: https://leetcode.com/problems/merge-two-sorted-lists/");
        ListNode head1 = new ListNode(1, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(3)))));
        printList(head1);
        head1 = deleteDuplicates(head1);
        printList(head1);

        // Question 2 Test
        System.out.println("\n=============== QUESTION 2: REMOVE DUPLICATES FROM UNSORTED LIST ===============");
        System.out.println("Link: https://www.naukri.com/code360/problems/remove-duplicates-from-unsorted-linked-list_1069331");
        ListNode head2 = new ListNode(1, new ListNode(3, new ListNode(2, new ListNode(3, new ListNode(2, new ListNode(4))))));
        printList(head2);
        head2 = removeDuplicatesUnsorted(head2);
        printList(head2);

        // Question 3 Test
        System.out.println("\n=============== QUESTION 3: SORT 0s, 1s, 2s LINKED LIST ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/sort-a-linked-list-of-0s-1s-and-2s/");
        ListNode head3 = new ListNode(2, new ListNode(1, new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(0))))));
        printList(head3);
        head3 = sortZeroOneTwoList(head3);
        printList(head3);

        // Question 4 Test
        System.out.println("\n=============== QUESTION 4: MERGE TWO SORTED LISTS ===============");
        System.out.println("Link: https://leetcode.com/problems/merge-two-sorted-lists/");
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(6)));
        printList(l1);
        printList(l2);
        ListNode merged = mergeTwoLists(l1, l2);
        printList(merged);

        // Question 5 Test
        System.out.println("\n=============== QUESTION 5: CHECK PALINDROME LINKED LIST ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/check-if-a-linked-list-is-palindrome/");
        Node head5 = new Node(1);
        head5.next = new Node(2);
        head5.next.next = new Node(3);
        head5.next.next.next = new Node(2);
        head5.next.next.next.next = new Node(1);

        boolean isPalin = isPalindrome(head5);
        System.out.println("Is Palindrome: " + isPalin);
        // Q6
        System.out.println("\n=============== QUESTION 6: ADD TWO NUMBERS REPRESENTED BY LINKED LISTS ===============");
        System.out.println("Link: https://www.geeksforgeeks.org/problems/add-two-numbers-represented-by-linked-lists/1");
        Node num1 = new Node(2);
        num1.next = new Node(4);
        num1.next.next = new Node(3);  // Represents 342

        Node num2 = new Node(5);
        num2.next = new Node(6);
        num2.next.next = new Node(4);  // Represents 465

        Node sumList = addTwoLists(num1, num2);  // Expected: 807 → 7 → 0 → 8

        System.out.print("Sum List: ");
        while (sumList != null) {
            System.out.print(sumList.data + " -> ");
            sumList = sumList.next;
        }
        System.out.println("null");

    }
}

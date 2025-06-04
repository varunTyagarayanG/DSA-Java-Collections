class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class B2_Day_06_LinkedList {

    // Question 1a : ---------------------------------------------Start-----------------------------------------------
    // LeetCode: https://leetcode.com/problems/reverse-linked-list/
    // Iterative approach to reverse a linked list
    public static Node reverseListIterative(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }

        return prev;
    }
    // Question 1a : ---------------------------------------------End-------------------------------------------------

    // Question 1b : ---------------------------------------------Start-----------------------------------------------
    // LeetCode: https://leetcode.com/problems/reverse-linked-list/
    // Recursive approach to reverse a linked list
    public static Node reverseListRecursive(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
    // Question 1b : ---------------------------------------------End-------------------------------------------------

    // Question 2 : ---------------------------------------------Start-------------------------------------------------
    // LeetCode: https://leetcode.com/problems/reverse-nodes-in-k-group/
    public static Node reverseKGroup(Node head, int k) {
        Node temp = head;
        for (int i = 0; i < k; i++) {
            if (temp == null) return head;
            temp = temp.next;
        }

        Node prev = null, curr = head, next = null;
        int count = 0;

        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            count++;
        }

        if (next != null) {
            head.next = reverseKGroup(next, k);
        }

        return prev;
    }
    // Question 2 : ---------------------------------------------End---------------------------------------------------

    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    // LeetCode: https://leetcode.com/problems/middle-of-the-linked-list/
    public static Node findMiddle(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    // Question 3 : ---------------------------------------------End---------------------------------------------------

    // Question 4 : ---------------------------------------------Start-------------------------------------------------
    // Code360: https://www.naukri.com/code360/problems/interview-shuriken-42-detect-and-remove-loop_241049
    public static void detectAndRemoveLoop(Node head) {
        Node slow = head;
        Node fast = head;

        boolean loopExists = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                loopExists = true;
                break;
            }
        }

        if (!loopExists) return;

        slow = head;
        if (slow == fast) {
            while (fast.next != slow) {
                fast = fast.next;
            }
        } else {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        fast.next = null;
    }
    // Question 4 : ---------------------------------------------End---------------------------------------------------

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {

        // QUESTION 1a: ITERATIVE
        System.out.println("=============== QUESTION 1a: REVERSE LINKED LIST (ITERATIVE) ===============");
        Node head1a = new Node(1);
        head1a.next = new Node(2);
        head1a.next.next = new Node(3);
        head1a.next.next.next = new Node(4);
        head1a.next.next.next.next = new Node(5);
        head1a.next.next.next.next.next = new Node(6);

        System.out.println("Original List:");
        printList(head1a);

        head1a = reverseListIterative(head1a);

        System.out.println("Reversed List (Iterative):");
        printList(head1a);

        // QUESTION 1b: RECURSIVE
        System.out.println("\n=============== QUESTION 1b: REVERSE LINKED LIST (RECURSIVE) ===============");
        Node head1b = new Node(1);
        head1b.next = new Node(2);
        head1b.next.next = new Node(3);
        head1b.next.next.next = new Node(4);
        head1b.next.next.next.next = new Node(5);
        head1b.next.next.next.next.next = new Node(6);

        System.out.println("Original List:");
        printList(head1b);

        head1b = reverseListRecursive(head1b);

        System.out.println("Reversed List (Recursive):");
        printList(head1b);

        // QUESTION 2: REVERSE K-GROUP
        System.out.println("\n=============== QUESTION 2: REVERSE NODES IN K-GROUP ===============");
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);

        System.out.println("Original List:");
        printList(head2);

        int k = 2;
        head2 = reverseKGroup(head2, k);

        System.out.println("Reversed in K-Group (k = " + k + "):");
        printList(head2);

        // QUESTION 3: FIND MIDDLE
        System.out.println("\n=============== QUESTION 3: FIND MIDDLE OF LINKED LIST ===============");
        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        head3.next.next.next = new Node(4);
        head3.next.next.next.next = new Node(5);
        head3.next.next.next.next.next = new Node(6);

        System.out.println("Linked List:");
        printList(head3);

        Node middle = findMiddle(head3);
        System.out.println("Middle Node Value: " + middle.data);

        // QUESTION 4: DETECT AND REMOVE LOOP
        System.out.println("\n=============== QUESTION 4: DETECT AND REMOVE LOOP ===============");
        Node head4 = new Node(1);
        head4.next = new Node(2);
        head4.next.next = new Node(3);
        head4.next.next.next = new Node(4);
        head4.next.next.next.next = new Node(5);

        // Creating loop: 5 -> 3
        head4.next.next.next.next.next = head4.next.next;

        System.out.println("Loop created manually. Running detectAndRemoveLoop...");
        detectAndRemoveLoop(head4);

        System.out.println("After removing loop:");
        printList(head4);
    }
}

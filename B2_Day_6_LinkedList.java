class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class B2_Day_6_LinkedList {

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
        // Base case
        if (head == null || head.next == null) {
            return head;
        }

        // Reverse the rest list
        Node newHead = reverseListRecursive(head.next);

        // Fix current node's next pointer
        head.next.next = head;
        head.next = null;

        return newHead;
    }
    // Question 1b : ---------------------------------------------End-------------------------------------------------
    // Question 3 : ---------------------------------------------Start-------------------------------------------------
    // ✅ LeetCode: https://leetcode.com/problems/middle-of-the-linked-list/
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
    // ✅ Code360: https://www.naukri.com/code360/problems/interview-shuriken-42-detect-and-remove-loop_241049
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

        // Step 2: Find the start of the loop
        slow = head;
        if (slow == fast) {
            // Loop starts from head itself
            while (fast.next != slow) {
                fast = fast.next;
            }
        } else {
            while (slow.next != fast.next) {
                slow = slow.next;
                fast = fast.next;
            }
        }

        // Step 3: Remove the loop
        fast.next = null;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        System.out.println("=============== QUESTION 1a: REVERSE LINKED LIST (ITERATIVE) ===============");

        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);

        System.out.println("Original List:");
        printList(head1);

        head1 = reverseListIterative(head1);

        System.out.println("Reversed List (Iterative):");
        printList(head1);

        System.out.println("\n=============== QUESTION 1b: REVERSE LINKED LIST (RECURSIVE) ===============");

        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = new Node(6);

        System.out.println("Original List:");
        printList(head2);

        head2 = reverseListRecursive(head2);

        System.out.println("Reversed List (Recursive):");
        printList(head2);
        System.out.println("=============== QUESTION 3: FIND MIDDLE OF LINKED LIST ===============");

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
        System.out.println("=============== QUESTION 4: DETECT AND REMOVE LOOP ===============");

        // Create a looped list: 1 -> 2 -> 3 -> 4 -> 5 -> 3 (loop starts again here)
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

/*
 * Linked List Basic Operations (No external links for these basic operations)
 */

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class B2_Day_05_LinkedList {

    // Q1: Insert at Head
    public static Node insertAtHead(Node head, int data) {
        Node temp = new Node(data);
        temp.next = head;
        return temp;
    }

    // Q2: Insert at End
    public static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);
        if (head == null) return newNode;

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = newNode;
        return head;
    }

    // Q3: Insert at Position (1-based)
    public static Node insertAtPosition(Node head, int data, int pos) {
        if (pos == 1) return insertAtHead(head, data);

        Node newNode = new Node(data);
        Node temp = head;
        int count = 1;

        while (temp != null && count < pos - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        newNode.next = temp.next;
        temp.next = newNode;
        return head;
    }

    // Q4: Delete at Head
    public static Node deleteAtHead(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return null;
        }
        return head.next;
    }

    // Q5: Delete at End
    public static Node deleteAtEnd(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }

        temp.next = null;
        return head;
    }

    // Q6: Delete at Position (1-based)
    public static Node deleteAtPosition(Node head, int pos) {
        if (head == null) return null;
        if (pos == 1) return head.next;

        Node temp = head;
        int count = 1;

        while (temp.next != null && count < pos - 1) {
            temp = temp.next;
            count++;
        }

        if (temp.next == null) {
            System.out.println("Position out of bounds");
            return head;
        }

        temp.next = temp.next.next;
        return head;
    }

    // Q7: Detect Cycle (Floyd’s Cycle Detection Algorithm)
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    // Utility: Print Linked List
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Linked List Basic Operations (No external links for these basic operations)");

        Node head = null;

        // Q1: Insert at Head
        System.out.println("=============== Q1: INSERT AT HEAD ===============");
        System.out.println("Link: (No external link)");
        head = insertAtHead(head, 10);
        head = insertAtHead(head, 20);
        head = insertAtHead(head, 30);
        printList(head); // Expected: 30 20 10

        // Q2: Insert at End
        System.out.println("\n=============== Q2: INSERT AT END ===============");
        System.out.println("Link: (No external link)");
        head = insertAtEnd(head, 40);
        head = insertAtEnd(head, 50);
        printList(head); // Expected: 30 20 10 40 50

        // Q3: Insert at Position
        System.out.println("\n=============== Q3: INSERT AT POSITION ===============");
        System.out.println("Link: (No external link)");
        head = insertAtPosition(head, 25, 3);  // Insert 25 at position 3
        head = insertAtPosition(head, 5, 1);   // Insert 5 at head
        head = insertAtPosition(head, 60, 100); // Invalid position
        printList(head); // Expected: 5 30 20 25 10 40 50

        // Q4: Delete Head
        System.out.println("\n=============== Q4: DELETE AT HEAD ===============");
        System.out.println("Link: (No external link)");
        head = deleteAtHead(head);
        printList(head); // Expected: 30 20 25 10 40 50

        // Q5: Delete End
        System.out.println("\n=============== Q5: DELETE AT END ===============");
        System.out.println("Link: (No external link)");
        head = deleteAtEnd(head);
        printList(head); // Expected: 30 20 25 10 40

        // Q6: Delete at Position
        System.out.println("\n=============== Q6: DELETE AT POSITION ===============");
        System.out.println("Link: (No external link)");
        head = deleteAtPosition(head, 3); // Remove 25
        head = deleteAtPosition(head, 1); // Remove 30
        head = deleteAtPosition(head, 100); // Invalid position
        printList(head); // Expected: 20 10 40

        // Q7: Detect Cycle
        System.out.println("\n=============== Q7: DETECT CYCLE ===============");
        System.out.println("Link: (No external link)");
        System.out.println("Cycle detected? " + hasCycle(head)); // false

        // Create a cycle manually for testing
        Node tail = head;
        while (tail.next != null) tail = tail.next;
        tail.next = head.next; // Creates cycle at node with value 10

        System.out.println("Cycle detected after creating one? " + hasCycle(head)); // true
    }
}

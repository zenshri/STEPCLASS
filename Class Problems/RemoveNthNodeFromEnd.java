public class RemoveNthNodeFromEnd {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // O(n) time, O(1) extra space. Dummy node handles removal of the head.
    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }

    static Node list(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int value : values) {
            tail.next = new Node(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    static void print(Node head) {
        if (head == null) { System.out.println("(empty list)"); return; }
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(removeNthFromEnd(list(1, 2, 3, 4, 5), 2));
        print(removeNthFromEnd(list(1), 1));
    }
}

public class ReorderLinkedListAlternately {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // O(n) time, O(1) extra space: find middle, reverse second half, merge alternately.
    public static void reorderList(Node head) {
        if (head == null || head.next == null) return;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node second = reverse(slow.next);
        slow.next = null;
        Node first = head;

        while (second != null) {
            Node firstNext = first.next;
            Node secondNext = second.next;

            first.next = second;
            second.next = firstNext;

            first = firstNext;
            second = secondNext;
        }
    }

    private static Node reverse(Node head) {
        Node previous = null, current = head;
        while (current != null) {
            Node next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        return previous;
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
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node even = list(1, 2, 3, 4);
        reorderList(even);
        print(even);

        Node odd = list(1, 2, 3, 4, 5);
        reorderList(odd);
        print(odd);
    }
}

public class AddTwoNumbersLinkedLists {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // Digits are stored in reverse order. O(max(m,n)) time and output space.
    public static Node addNumbers(Node a, Node b) {
        Node dummy = new Node(0), tail = dummy;
        int carry = 0;

        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) { sum += a.data; a = a.next; }
            if (b != null) { sum += b.data; b = b.next; }

            tail.next = new Node(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
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
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        print(addNumbers(list(2, 4, 3), list(5, 6, 4)));
        print(addNumbers(list(9, 9), list(1)));
    }
}

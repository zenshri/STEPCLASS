public class FindIntersectionNode {
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    // Compares node references (identity), not values.
    // O(m+n) time and O(1) extra space.
    public static Node findIntersection(Node a, Node b) {
        int lengthA = length(a);
        int lengthB = length(b);

        while (lengthA > lengthB) { a = a.next; lengthA--; }
        while (lengthB > lengthA) { b = b.next; lengthB--; }

        while (a != b) {
            a = a.next;
            b = b.next;
        }
        return a;
    }

    private static int length(Node head) {
        int count = 0;
        while (head != null) { count++; head = head.next; }
        return count;
    }

    static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node shared = new Node(8);
        shared.next = new Node(4);
        shared.next.next = new Node(5);

        Node a = new Node(4);
        a.next = new Node(1);
        a.next.next = shared;

        Node b = new Node(5);
        b.next = new Node(6);
        b.next.next = new Node(1);
        b.next.next.next = shared;

        Node intersection = findIntersection(a, b);
        System.out.println(intersection == null ? "null" : "Node with value " + intersection.data);
        System.out.println(findIntersection(new Node(2), new Node(1)));
    }
}

public class MergingTwoTicketCounters {
    static class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }
    // Relinks existing nodes; O(m+n) time and O(1) auxiliary space.
    public static Node mergeSorted(Node a, Node b) {
        Node dummy = new Node(0), tail = dummy;
        while (a != null && b != null) {
            if (a.data <= b.data) { tail.next = a; a = a.next; }
            else { tail.next = b; b = b.next; }
            tail = tail.next;
        }
        tail.next = (a != null) ? a : b;
        return dummy.next;
    }
    static Node list(int... values) {
        Node dummy = new Node(0), tail = dummy;
        for (int v : values) { tail.next = new Node(v); tail = tail.next; }
        return dummy.next;
    }
    static void print(Node h) {
        while (h != null) { System.out.print(h.data); if (h.next != null) System.out.print(" -> "); h = h.next; }
        System.out.println();
    }
    public static void main(String[] args) {
        print(mergeSorted(list(3,8,15), list(5,8,12,20)));
        print(mergeSorted(null, list(4)));
    }
}

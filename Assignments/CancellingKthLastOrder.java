public class CancellingKthLastOrder {
    static class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }
    // One pass: O(n) time, O(1) extra space.
    public static Node removeKthFromEnd(Node head, int k) {
        Node dummy = new Node(0);
        dummy.next = head;
        Node lead = dummy, trail = dummy;
        for (int i = 0; i <= k; i++) {
            if (lead == null) throw new IllegalArgumentException("k exceeds list length");
            lead = lead.next;
        }
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }
        trail.next = trail.next.next;
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
        print(removeKthFromEnd(list(10,20,30,40,50), 2));
        print(removeKthFromEnd(list(10,20,30,40,50), 5));
    }
}

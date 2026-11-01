public class PalindromeTrainCode {
    static class Node {
        int data; Node next;
        Node(int data) { this.data = data; }
    }
    // O(n) time, O(1) extra space; restores the list before returning.
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next; fast = fast.next.next;
        }
        Node second = reverse(slow.next);
        Node secondHead = second, first = head;
        boolean result = true;
        while (second != null) {
            if (first.data != second.data) { result = false; break; }
            first = first.next; second = second.next;
        }
        slow.next = reverse(secondHead);
        return result;
    }
    private static Node reverse(Node head) {
        Node previous = null, current = head;
        while (current != null) {
            Node next = current.next; current.next = previous;
            previous = current; current = next;
        }
        return previous;
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
        Node a = list(1,2,3,2,1);
        System.out.println(isPalindrome(a)); print(a);
        Node b = list(1,2,3,2);
        System.out.println(isPalindrome(b)); print(b);
        Node c = list(1,2,2,1);
        System.out.println(isPalindrome(c)); print(c);
    }
}

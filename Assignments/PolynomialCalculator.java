public class PolynomialCalculator {
    static class Node {
        int coefficient, exponent; Node next;
        Node(int coefficient, int exponent) { this.coefficient = coefficient; this.exponent = exponent; }
    }
    // O(m+n) time. New result list uses O(m+n) space.
    public static Node addPolynomials(Node p, Node q) {
        Node dummy = new Node(0, 0), tail = dummy;
        while (p != null && q != null) {
            if (p.exponent > q.exponent) {
                tail.next = new Node(p.coefficient, p.exponent); p = p.next;
            } else if (p.exponent < q.exponent) {
                tail.next = new Node(q.coefficient, q.exponent); q = q.next;
            } else {
                int sum = p.coefficient + q.coefficient;
                if (sum != 0) {
                    tail.next = new Node(sum, p.exponent);
                    tail = tail.next;
                }
                p = p.next; q = q.next;
                continue;
            }
            tail = tail.next;
        }
        while (p != null) {
            tail.next = new Node(p.coefficient, p.exponent); tail = tail.next; p = p.next;
        }
        while (q != null) {
            tail.next = new Node(q.coefficient, q.exponent); tail = tail.next; q = q.next;
        }
        return dummy.next;
    }
    static Node polynomial(int[][] terms) {
        Node dummy = new Node(0, 0), tail = dummy;
        for (int[] term : terms) {
            if (term[0] != 0) { tail.next = new Node(term[0], term[1]); tail = tail.next; }
        }
        return dummy.next;
    }
    static void print(Node h) {
        if (h == null) { System.out.println("0"); return; }
        boolean first = true;
        while (h != null) {
            int c = h.coefficient, e = h.exponent;
            if (!first) System.out.print(c >= 0 ? " + " : " - ");
            else if (c < 0) System.out.print("-");
            int abs = Math.abs(c);
            if (e == 0 || abs != 1) System.out.print(abs);
            if (e > 0) { System.out.print("x"); if (e > 1) System.out.print("^" + e); }
            first = false; h = h.next;
        }
        System.out.println();
    }
    public static void main(String[] args) {
        Node p = polynomial(new int[][]{{5,3},{4,2},{2,0}});
        Node q = polynomial(new int[][]{{5,2},{-3,1},{1,0}});
        print(addPolynomials(p, q));
        Node p2 = polynomial(new int[][]{{3,2},{1,0}});
        Node q2 = polynomial(new int[][]{{-3,2},{4,1}});
        print(addPolynomials(p2, q2));
    }
}

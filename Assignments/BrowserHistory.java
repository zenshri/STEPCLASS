public class BrowserHistory {
    static class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
        }
    }

    private Node current;

    public BrowserHistory(String homepage) {
        current = new Node(homepage);
    }

    // O(1): discard forward history and append the new page.
    public void visit(String url) {
        Node newPage = new Node(url);
        current.next = null;
        newPage.prev = current;
        current.next = newPage;
        current = newPage;
    }

    // O(steps), stopping at the homepage.
    public String back(int steps) {
        while (steps > 0 && current.prev != null) {
            current = current.prev;
            steps--;
        }
        return current.url;
    }

    // O(steps), stopping at the latest visited page.
    public String forward(int steps) {
        while (steps > 0 && current.next != null) {
            current = current.next;
            steps--;
        }
        return current.url;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("srm.edu");

        browser.visit("a.com");
        browser.visit("b.com");
        browser.visit("c.com");

        System.out.println(browser.back(1));     // b.com
        System.out.println(browser.back(1));     // a.com
        System.out.println(browser.forward(1));  // b.com

        browser.visit("d.com");
        System.out.println(browser.forward(2));  // d.com
        System.out.println(browser.back(2));     // a.com
        System.out.println(browser.back(7));     // srm.edu
    }
}

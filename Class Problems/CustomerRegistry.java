import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Customer {
    private int id;
    private String name;
    Customer(int id, String name) { this.id = id; this.name = name; }
    @Override public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Customer)) return false;
        Customer other = (Customer)obj;
        return id == other.id && Objects.equals(name, other.name);
    }
    @Override public int hashCode() { return Objects.hash(id, name); }
    @Override public String toString() { return "(" + id + ", " + name + ")"; }
}
public class CustomerRegistry {
    public static void main(String[] args) {
        Set<Customer> registry = new HashSet<>();
        System.out.println(registry.add(new Customer(101, "Asha")));
        System.out.println(registry.add(new Customer(101, "Asha")) + " (duplicate rejected)");
        System.out.println(registry.add(new Customer(102, "Ravi")));
        System.out.println("unique count " + registry.size());
        System.out.println("contains: " + registry.contains(new Customer(101, "Asha")));
    }
}

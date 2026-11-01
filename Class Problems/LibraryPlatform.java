abstract class Resource {
    protected String id;
    Resource(String id) { this.id = id; }
    public String getId() { return id; }
    public abstract boolean canReserve();
    public abstract boolean canDownload();
}
class BookResource extends Resource {
    BookResource(String id) { super(id); }
    public boolean canReserve() { return true; }
    public boolean canDownload() { return false; }
}
class EBookResource extends Resource {
    EBookResource(String id) { super(id); }
    public boolean canReserve() { return false; }
    public boolean canDownload() { return true; }
}
public class LibraryPlatform {
    private Resource[] resources = new Resource[100];
    private int size = 0;

    public void add(Resource resource) {
        if (findIndex(resource.getId()) != -1) {
            System.out.println("duplicate rejected"); return;
        }
        int pos = size;
        while (pos > 0 && resources[pos - 1].getId().compareTo(resource.getId()) > 0) {
            resources[pos] = resources[pos - 1]; pos--;
        }
        resources[pos] = resource; size++;
        System.out.println(resource.getId() + " added");
    }
    private int findIndex(String id) {
        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = resources[mid].getId().compareTo(id);
            if (cmp == 0) return mid;
            if (cmp < 0) left = mid + 1; else right = mid - 1;
        }
        return -1;
    }
    public void reserve(String id, String member) {
        int i = findIndex(id);
        if (i < 0) System.out.println(id + " not found");
        else if (resources[i].canReserve()) System.out.println(id + " reserved for " + member);
        else System.out.println(id + " rejected: reserve unsupported");
    }
    public void download(String id) {
        int i = findIndex(id);
        if (i < 0) System.out.println(id + " not found");
        else if (resources[i].canDownload()) System.out.println(id + " downloaded");
        else System.out.println(id + " rejected: download unsupported");
    }
    public void find(String id) {
        int i = findIndex(id);
        if (i < 0) System.out.println(id + " not found");
        else System.out.println(id + " found at index " + i);
    }
    public static void main(String[] args) {
        LibraryPlatform library = new LibraryPlatform();
        library.add(new BookResource("B1"));
        library.add(new BookResource("B1"));
        library.add(new EBookResource("E1"));
        library.reserve("B1", "M1");
        library.download("B1");
        library.download("E1");
        library.find("E1");
    }
}

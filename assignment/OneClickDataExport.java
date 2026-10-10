
interface Exportable {
    String exportData();
}

class ExportCounter {
    static int totalExports = 0;

    static int getTotalExports() {
        return totalExports;
    }
}

class ReportGenerator implements Exportable {
    private String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    public String exportData() {
        ExportCounter.totalExports++;
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    private String username;

    public UserProfile(String username) {
        this.username = username;
    }

    public String exportData() {
        ExportCounter.totalExports++;
        return "Exported profile: " + username;
    }
}

public class OneClickDataExport {
    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }

    public static void main(String[] args) {
        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        Exportable ref = r;
        exportAll(new Exportable[]{ref, u});

        System.out.println(getTotalExports());
    }
}

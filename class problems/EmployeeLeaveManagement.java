abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract int getMaxLeaveDays();
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 30;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 15;
    }
}

class ContractEmployee extends Employee {
    ContractEmployee(String name) {
        super(name);
    }

    int getMaxLeaveDays() {
        return 10;
    }
}

class LeaveRequest {
    Employee employee;
    String startDate, endDate;
    String status = "Pending";

    LeaveRequest(Employee employee, String start, String end) {
        this.employee = employee;
        this.startDate = start;
        this.endDate = end;
    }

    void review(String decision) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change status: "
                    + status + " request cannot revert to Pending.");
            return;
        }

        if (decision.equals("Approved") || decision.equals("Rejected")) {
            status = decision;

            System.out.println("Leave request for "
                    + employee.name + " " + status.toLowerCase() + ".");
            System.out.println("Status: " + status);
        }
    }
}

class LeaveManager {

    LeaveRequest submit(Employee employee, String start, String end) {

        LeaveRequest request = new LeaveRequest(employee, start, end);

        System.out.println("Leave request submitted by "
                + employee.name + " for " + start + " to " + end);

        System.out.println("Status: " + request.status);

        return request;
    }

    void review(LeaveRequest request, String decision) {
        request.review(decision);
    }
}

public class EmployeeLeaveManagement {
    public static void main(String[] args) {

        LeaveManager manager = new LeaveManager();

        Employee john = new FullTimeEmployee("John Doe");
        Employee jane = new PartTimeEmployee("Jane Smith");

        LeaveRequest r1 = manager.submit(
                john, "2024-10-10", "2024-10-12");

        manager.review(r1, "Approved");

        LeaveRequest r2 = manager.submit(
                jane, "2024-11-01", "2024-11-05");

        manager.review(r1, "Pending");
    }
}
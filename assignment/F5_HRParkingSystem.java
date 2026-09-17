class Employee {
    String empId;
    String empName;
    double salary;

    Employee(String empId, String empName, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.salary = salary;
    }

    double getSalary() {
        return salary;
    }
}

class ManagerEmployee extends Employee {
    double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return salary + teamBonus;
    }
}

class InternEmployee extends Employee {
    double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(salary, stipendCap);
    }
}

class ParkingSlot {
    String slotNo;
    int capacity;
    int occupiedCount;

    ParkingSlot(String slotNo, int capacity) {
        this.slotNo = slotNo;
        this.capacity = capacity;
        this.occupiedCount = 0;
    }

    boolean allot(String vehicleNo) {
        if (occupiedCount < capacity) {
            occupiedCount++;
            return true;
        }

        return false;
    }
}

class CompanyEmployeeRecord {
    String name;
    String empId;
    Employee employee;
    ParkingSlot slot;

    static int totalRecords = 0;

    CompanyEmployeeRecord(String name, String empId,
                          Employee employee) {
        this.name = name;
        this.empId = empId;
        this.employee = employee;
        this.slot = null;

        totalRecords++;
    }

    String fullProfile() {

        double pay;

        if (employee instanceof ManagerEmployee) {
            pay = ((ManagerEmployee) employee).effectiveSalary();
        } else if (employee instanceof InternEmployee) {
            pay = ((InternEmployee) employee).effectiveSalary();
        } else {
            pay = employee.getSalary();
        }

        String slotNumber;

        if (slot != null) {
            slotNumber = slot.slotNo;
        } else {
            slotNumber = "no parking assigned";
        }

        return name + " | Pay: Rs " + pay +
                " | Slot: " + slotNumber;
    }
}

public class F5_HRParkingSystem {
    public static void main(String[] args) {

        ParkingSlot slot1 = new ParkingSlot("A1", 1);
        ParkingSlot slot2 = new ParkingSlot("A2", 1);

        ManagerEmployee manager =
                new ManagerEmployee("E001", "Divya", 70000, 8000);

        Employee employee =
                new Employee("E002", "Karan", 40000);

        InternEmployee intern =
                new InternEmployee("E003", "Meera", 12000, 10000);

        CompanyEmployeeRecord record1 =
                new CompanyEmployeeRecord("Divya", "E001", manager);

        CompanyEmployeeRecord record2 =
                new CompanyEmployeeRecord("Karan", "E002", employee);

        CompanyEmployeeRecord record3 =
                new CompanyEmployeeRecord("Meera", "E003", intern);

        if (slot1.allot("DIV123")) {
            record1.slot = slot1;
        }

        if (slot2.allot("KAR456")) {
            record2.slot = slot2;
        }

        System.out.println(record1.fullProfile());
        System.out.println(record2.fullProfile());
        System.out.println(record3.fullProfile());

        System.out.println(
            "Total records: " + CompanyEmployeeRecord.totalRecords
        );
    }
}
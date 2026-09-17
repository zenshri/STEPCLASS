class Employee {
    private String empId;
    private String empName;
    private double salary;

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
    private double teamBonus;

    ManagerEmployee(String empId, String empName,
                    double salary, double teamBonus) {
        super(empId, empName, salary);
        this.teamBonus = teamBonus;
    }

    double effectiveSalary() {
        return getSalary() + teamBonus;
    }
}

class InternEmployee extends Employee {
    private double stipendCap;

    InternEmployee(String empId, String empName,
                   double salary, double stipendCap) {
        super(empId, empName, salary);
        this.stipendCap = stipendCap;
    }

    double effectiveSalary() {
        return Math.min(getSalary(), stipendCap);
    }
}

public class F2_Employee {
    public static void main(String[] args) {

        Employee employee =
                new Employee("E001", "Karan", 40000);

        ManagerEmployee manager =
                new ManagerEmployee("E002", "Divya", 70000, 8000);

        InternEmployee intern =
                new InternEmployee("E003", "Meera", 12000, 10000);

        if (employee instanceof Employee) {
            System.out.println(
                "Plain employee pay: Rs " + employee.getSalary()
            );
        }

        if (manager instanceof ManagerEmployee) {
            System.out.println(
                "Manager effective pay: Rs " +
                manager.effectiveSalary()
            );
        }

        if (intern instanceof InternEmployee) {
            System.out.println(
                "Intern effective pay: Rs " +
                intern.effectiveSalary()
            );
        }
    }
}
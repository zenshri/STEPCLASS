class FeeAccount {
    private double totalFee;
    private double amountPaid;

    FeeAccount(double totalFee) {
        this.totalFee = totalFee;
        this.amountPaid = 0;
    }

    void pay(double amount) {
        if (amount > 0) {
            amountPaid += amount;
        } else {
            System.out.println("Payment rejected");
        }
    }

    double getDue() {
        return totalFee - amountPaid;
    }
}

class HostelFeeAccount extends FeeAccount {

    HostelFeeAccount(double totalFee) {
        super(totalFee);
    }

    void payInTwoInstallments(double amount) {
        pay(amount);
        pay(amount);
    }
}

class HostelRoom {
    String roomNo;
    int beds;
    int occupied;

    HostelRoom(String roomNo, int beds) {
        this.roomNo = roomNo;
        this.beds = beds;
        this.occupied = 0;
    }

    boolean allot(String name) {
        if (occupied < beds) {
            occupied++;
            return true;
        }

        return false;
    }
}

class SrmStudent {
    String name;
    String regNo;
    HostelFeeAccount feeAccount;
    HostelRoom room;

    static int totalStudents = 0;

    SrmStudent(String name, String regNo, HostelFeeAccount feeAccount) {
        this.name = name;
        this.regNo = regNo;
        this.feeAccount = feeAccount;
        this.room = null;

        totalStudents++;
    }

    String fullStatus() {

        String roomNumber;

        if (room != null) {
            roomNumber = room.roomNo;
        } else {
            roomNumber = "unallotted";
        }

        return name + " | Due: Rs " + feeAccount.getDue()
                + " | Room: " + roomNumber;
    }
}

public class F5_ManagementSystem {
    public static void main(String[] args) {

        HostelRoom room1 = new HostelRoom("C-214", 1);
        HostelRoom room2 = new HostelRoom("C-507", 1);

        SrmStudent ravi = new SrmStudent(
                "Ravi", "RA001",
                new HostelFeeAccount(200000)
        );

        SrmStudent anitha = new SrmStudent(
                "Anitha", "RA002",
                new HostelFeeAccount(200000)
        );

        SrmStudent karthik = new SrmStudent(
                "Karthik", "RA003",
                new HostelFeeAccount(200000)
        );

        room1.allot(ravi.name);
        ravi.room = room1;

        room2.allot(anitha.name);
        anitha.room = room2;

        ravi.feeAccount.pay(60000);
        anitha.feeAccount.pay(20000);

        // Invalid payment
        karthik.feeAccount.pay(-5000);

        System.out.println(ravi.fullStatus());
        System.out.println(anitha.fullStatus());
        System.out.println(karthik.fullStatus());

        System.out.println(
            "Total students: " + SrmStudent.totalStudents
        );
    }
}
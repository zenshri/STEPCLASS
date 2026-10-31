import java.util.*;

abstract class Student {
    String name;
    int currentCredits;

    Student(String name, int credits) {
        this.name = name;
        this.currentCredits = credits;
    }

    abstract int getCreditLimit();
}

class RegularStudent extends Student {
    RegularStudent(String name, int credits) {
        super(name, credits);
    }

    int getCreditLimit() {
        return 24;
    }
}

class HonorsStudent extends Student {
    HonorsStudent(String name, int credits) {
        super(name, credits);
    }

    int getCreditLimit() {
        return 28;
    }
}

class ExchangeStudent extends Student {
    ExchangeStudent(String name, int credits) {
        super(name, credits);
    }

    int getCreditLimit() {
        return 20;
    }
}

class Elective {
    String name;
    int credits;
    int capacity;

    List<Student> enrolled = new ArrayList<>();
    Queue<Student> waitlist = new LinkedList<>();

    Elective(String name, int credits, int capacity) {
        this.name = name;
        this.credits = credits;
        this.capacity = capacity;
    }

    boolean alreadyExists(Student student) {
        return enrolled.contains(student) || waitlist.contains(student);
    }

    boolean hasCreditCapacity(Student student) {
        return student.currentCredits + credits
                <= student.getCreditLimit();
    }

    void enroll(Student student) {

        if (alreadyExists(student)) {
            System.out.println(student.name
                    + " is already enrolled or waitlisted.");
            return;
        }

        if (!hasCreditCapacity(student)) {
            System.out.println("Enrollment failed: " + student.name
                    + " would exceed the " + student.getClass().getSimpleName()
                    .replace("Student", "") + " credit limit ("
                    + (student.currentCredits + credits) + "/"
                    + student.getCreditLimit() + ").");
            return;
        }

        if (enrolled.size() >= capacity) {
            waitlist.add(student);
            System.out.println(name + " is full.");
            System.out.println(student.name + " added to waitlist (position "
                    + waitlist.size() + ").");
            return;
        }

        enrolled.add(student);
        student.currentCredits += credits;

        System.out.println(student.name + " enrolled in " + name
                + " (credits: " + student.currentCredits
                + "/" + student.getCreditLimit() + ").");
    }

    void drop(Student student) {

        if (!enrolled.remove(student)) {
            System.out.println(student.name + " is not enrolled.");
            return;
        }

        student.currentCredits -= credits;

        System.out.println(student.name + " dropped " + name
                + " (credits: " + student.currentCredits
                + "/" + student.getCreditLimit() + ").");

        promoteNext();
    }

    void promoteNext() {
        Iterator<Student> iterator = waitlist.iterator();

        while (iterator.hasNext()) {
            Student student = iterator.next();

            if (hasCreditCapacity(student)) {
                iterator.remove();

                enrolled.add(student);
                student.currentCredits += credits;

                System.out.println(student.name
                        + " promoted from waitlist and enrolled in "
                        + name + " (credits: " + student.currentCredits
                        + "/" + student.getCreditLimit() + ").");
                return;
            }
        }
    }
}

class EnrollmentService {
    void enrollStudent(Elective elective, Student student) {
        elective.enroll(student);
    }

    void dropStudent(Elective elective, Student student) {
        elective.drop(student);
    }
}

public class ElectiveSeatRush {
    public static void main(String[] args) {

        Elective elective = new Elective(
                "Cloud Computing", 4, 2);

        EnrollmentService service = new EnrollmentService();

        Student asha = new RegularStudent("Asha", 20);
        Student ravi = new HonorsStudent("Ravi", 22);
        Student neha = new ExchangeStudent("Neha", 12);
        Student kiran = new RegularStudent("Kiran", 22);

        service.enrollStudent(elective, asha);
        service.enrollStudent(elective, ravi);
        service.enrollStudent(elective, neha);
        service.enrollStudent(elective, kiran);

        service.dropStudent(elective, asha);
    }
}
public class StudentMarksGrid {
    public static void main(String[] args) {
        String[] students = {"Anu", "Ravi", "Meena"};
        String[] subjects = {"Math", "Sci", "Eng"};
        Integer[][] marks = {
            {80, 90, 70},
            {60, 85, null},
            {95, 75, 88}
        }; // null means the student has no mark for that subject.

        for (int r = 0; r < students.length; r++) {
            int total = 0;
            for (int c = 0; c < subjects.length; c++)
                if (marks[r][c] != null) total += marks[r][c];
            System.out.println("Total " + students[r] + " " + total);
        }

        for (int c = 0; c < subjects.length; c++) {
            int top = -1, topper = -1;
            for (int r = 0; r < students.length; r++) {
                if (marks[r][c] != null && marks[r][c] > top) {
                    top = marks[r][c]; topper = r;
                }
            }
            if (topper >= 0)
                System.out.println("Topper " + subjects[c] + " " + students[topper] + " " + top);
        }
    }
}

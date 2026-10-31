import java.util.*;

abstract class Question {
    protected String questionText;
    protected String correctAnswer;

    Question(String questionText, String correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    abstract boolean evaluate(String answer);
}

class MCQQuestion extends Question {
    MCQQuestion(String text, String answer) {
        super(text, answer);
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    TrueFalseQuestion(String text, String answer) {
        super(text, answer);
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String title;
    List<Question> questions = new ArrayList<>();

    Examination(String title) {
        this.title = title;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    Examination exam;
    Map<Integer, String> answers = new HashMap<>();
    boolean submitted = false;

    Attempt(Examination exam) {
        this.exam = exam;
    }

    void answerQuestion(int number, String answer) {
        if (submitted) {
            System.out.println("Cannot modify submitted attempt.");
            return;
        }

        answers.put(number, answer);
        System.out.println("Question " + number + " answered with '" + answer + "'.");
    }

    void submit() {
        if (submitted) {
            System.out.println("Already submitted.");
            return;
        }

        submitted = true;
        System.out.println("Examination '" + exam.title + "' submitted successfully.");
        evaluate();
    }

    void evaluate() {
        int score = 0;

        for (int i = 0; i < exam.questions.size(); i++) {
            String answer = answers.get(i);

            if (answer != null && exam.questions.get(i).evaluate(answer)) {
                score++;
            }
        }

        System.out.println("Result for '" + exam.title + "' attempt: "
                + score + "/" + exam.questions.size() + " correct.");
    }
}

class Student {
    String name;

    Student(String name) {
        this.name = name;
    }

    Attempt startExam(Examination exam) {
        System.out.println("Examination '" + exam.title + "' started by " + name + ".");
        return new Attempt(exam);
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Examination exam = new Examination("Math Quiz");

        exam.addQuestion(new MCQQuestion("2 + 2 = ?", "A"));
        exam.addQuestion(new MCQQuestion("5 + 5 = ?", "B"));

        Student student = new Student("John");

        Attempt attempt = student.startExam(exam);

        attempt.answerQuestion(1, "A");
        attempt.answerQuestion(2, "C");

        attempt.submit();
    }
}
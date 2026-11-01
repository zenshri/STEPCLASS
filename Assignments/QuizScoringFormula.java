import java.util.ArrayDeque;
import java.util.Deque;

public class QuizScoringFormula {
    private static int precedence(char op) {
        return (op == '+' || op == '-') ? 1 : 2;
    }

    private static void applyTop(Deque<Integer> values, Deque<Character> operators) {
        char op = operators.pop();
        int right = values.pop();
        int left = values.pop();

        switch (op) {
            case '+': values.push(left + right); break;
            case '-': values.push(left - right); break;
            case '*': values.push(left * right); break;
            case '/':
                if (right == 0) throw new ArithmeticException("Division by zero");
                values.push(left / right);
                break;
            default: throw new IllegalArgumentException("Unknown operator");
        }
    }

    // Two-stack, left-to-right evaluation. Integer division follows Java rules.
    public static int evaluate(String expr) {
        Deque<Integer> values = new ArrayDeque<>();
        Deque<Character> operators = new ArrayDeque<>();

        for (int i = 0; i < expr.length();) {
            char ch = expr.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
            } else if (Character.isDigit(ch)) {
                int number = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    number = number * 10 + (expr.charAt(i) - '0');
                    i++;
                }
                values.push(number);
            } else if (ch == '(') {
                operators.push(ch);
                i++;
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyTop(values, operators);
                }
                if (operators.isEmpty()) throw new IllegalArgumentException("Mismatched brackets");
                operators.pop();
                i++;
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && operators.peek() != '('
                        && precedence(operators.peek()) >= precedence(ch)) {
                    applyTop(values, operators);
                }
                operators.push(ch);
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') throw new IllegalArgumentException("Mismatched brackets");
            applyTop(values, operators);
        }

        if (values.size() != 1) throw new IllegalArgumentException("Invalid expression");
        return values.pop();
    }

    public static void main(String[] args) {
        System.out.println(evaluate("2 * (3 + 4) - 10 / 5"));
        System.out.println(evaluate("(8 - 2) * (1 + 2) / 4"));
    }
}

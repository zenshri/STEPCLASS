import java.util.Random;
import java.util.Scanner;

public class BMICalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {

        System.out.printf("%-10s %-15s %-15s %-15s %-15s%n",
                "Person", "Height (m)", "Weight (kg)", "BMI", "Status");

        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < heights.length; i++) {

            double bmi = weights[i] / (heights[i] * heights[i]);
            String status = getBmiStatus(bmi);

            System.out.printf("%-10d %-15.2f %-15.2f %-15.2f %-15s%n",
                    i + 1,
                    heights[i],
                    weights[i],
                    bmi,
                    status);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int numberOfPeople = 10;

        double[] heights = new double[numberOfPeople];
        double[] weights = new double[numberOfPeople];

        System.out.println("BMI Calculator for Team");
        System.out.println();

        for (int i = 0; i < numberOfPeople; i++) {

            System.out.print("Enter height (m) for Person " + (i + 1) + ": ");
            heights[i] = scanner.nextDouble();

            System.out.print("Enter weight (kg) for Person " + (i + 1) + ": ");
            weights[i] = scanner.nextDouble();
        }

        System.out.println();
        System.out.println("========== WELLNESS REPORT ==========");

        printWellnessReport(heights, weights);
    }
}
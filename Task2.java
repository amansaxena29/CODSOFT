import java.util.*;

public class Task2 {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the Number of subjects: ");
            int numSubj = sc.nextInt();

            int totalMarks = 0;

            for (int i = 1; i <= numSubj; i++) {
                System.out.print("Enter marks for subject " + i + ": ");
                int marks = sc.nextInt();
                totalMarks = totalMarks +  marks;
            }

            double averagePercentage = (double) totalMarks / numSubj;

            System.out.println("Total Marks : " + totalMarks);
            System.out.println("Average Percentage : " + String.format("%.2f", averagePercentage) + "%");

            String grade = calculateGrade(averagePercentage);
            System.out.println("Grade : " + grade);
        }

        public static String calculateGrade(double averagePercentage) {
            if (averagePercentage >= 90 && averagePercentage <=100) {
                return "A";
            } else if (averagePercentage >= 80) {
                return "B";
            } else if (averagePercentage >= 70) {
                return "C";
            } else if (averagePercentage >= 60) {
                return "D";
            } else if (averagePercentage >= 50) {
                return "E";
            }
            else {
                return "F";
            }
        }
    }

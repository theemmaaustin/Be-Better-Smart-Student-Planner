// GpaCalculator.java
// This class handles the GPA Calculator feature
// The user inputs courses, credit hours, and letter grades
// The class calculates and displays the cumulative GPA

import java.util.Scanner;

public class GpaCalculator {

    
    // A = 4.0, B = 3.0, C = 2.0, D = 1.0, F = 0.0
    private double getGradePoints(String letterGrade) {
        switch (letterGrade.toUpperCase()) {
            case "A": return 4.0;
            case "B": return 3.0;
            case "C": return 2.0;
            case "D": return 1.0;
            case "F": return 0.0;
            default:
                // If the user types something unrecognized, treat it as F
                System.out.println("Unknown grade. Counting as F (0.0 points).");
                return 0.0;
        }
    }

    // This is the main GPA calculator flow
    public void calculateGpa(Scanner scanner) {
        System.out.println("\n-_-_- GPA Calculator -_-_-");

        System.out.print("How many courses do you want to input? ");
        String input = scanner.nextLine().trim();

        // try-catch in case user types a letter instead of a number
        try {
            int numCourses = Integer.parseInt(input);

            if (numCourses <= 0) {
                System.out.println("Please enter at least one course.\n");
                return;
            }

            double totalCredits = 0.0;
            double totalPoints = 0.0;

            // Loop through each course and collect information
            for (int i = 0; i < numCourses; i++) {
                System.out.println("\nCourse " + (i + 1) + ":");

                System.out.print("Enter course name: ");
                String courseName = scanner.nextLine().trim();

                // Get credit hours with validation
                System.out.print("Enter number of credits (e.g. 3): ");
                String creditsInput = scanner.nextLine().trim();
                double credits;

                try {
                    credits = Double.parseDouble(creditsInput);
                    if (credits <= 0) {
                        System.out.println("Credits must be greater than 0. Setting to 3.\n");
                        credits = 3.0;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid credits. Setting to 3.\n");
                    credits = 3.0;
                }

                // Get letter grade
                System.out.print("Enter letter grade (A, B, C, D, F): ");
                String letterGrade = scanner.nextLine().trim();

                // Convert letter grade to grade points
                double gradePoints = getGradePoints(letterGrade);

                // Add to running totals
                // totalPoints adds (grade value × credits) for this course
                totalCredits += credits;
                totalPoints += (gradePoints * credits);

                System.out.println(courseName + " recorded: " + letterGrade.toUpperCase() + 
                                   " | " + credits + " credits | " + 
                                   gradePoints + " grade points");
            }

            // Calculate and display final GPA
            if (totalCredits == 0) {
                System.out.println("\nYou entered 0 total credits. GPA cannot be calculated.\n");
            } else {
                double gpa = totalPoints / totalCredits;

                // Round to 2 decimal places for clean display
                // String.format("%.2f", gpa) formats the number to 2 decimal places
                System.out.println("\n-_-_- GPA Result -_-_-");
                System.out.println("Total Credits: " + totalCredits);
                System.out.println("Total Grade Points: " + totalPoints);
                System.out.println("Your GPA is: " + String.format("%.2f", gpa) + "\n");
            }

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number for courses.\n");
        }
    }
}
// HomeworkTracker.java
// This class handles the Homework Tracker feature
// Users can create assignments, mark them complete, and view all assignments
// Each assignment is stored as a HashMap inside an ArrayList

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class HomeworkTracker {

    // ArrayList of HashMaps — each HashMap is one assignment
    // Keys: "title", "course", "due", "completed"
    private ArrayList<HashMap<String, String>> homeworkList;

    // Random is used for picking a motivational message when completing a task
    private Random random;

    public HomeworkTracker() {
        homeworkList = new ArrayList<>();
        random = new Random();
    }

    // This method creates a new assignment and adds it to the list
    public void createAssignment(Scanner scanner) {
        System.out.println("\n-_-_- Create Assignment -_-_-");

        System.out.print("Enter assignment name: ");
        String title = scanner.nextLine().trim();

        System.out.print("Enter course name (e.g., CS100): ");
        String course = scanner.nextLine().trim();

        System.out.print("Enter due date (e.g., 2026-12-01 or 'Monday'): ");
        String due = scanner.nextLine().trim();

        // Validate — none of the fields can be empty
        if (title.isEmpty() || course.isEmpty() || due.isEmpty()) {
            System.out.println("Assignment name, course, and due date cannot be empty.\n");
            return;
        }

        // Create a new HashMap and put all the assignment details in it
        HashMap<String, String> assignment = new HashMap<>();
        assignment.put("title", title);
        assignment.put("course", course);
        assignment.put("due", due);
        assignment.put("completed", "false"); // starts as not completed

        // Add the HashMap to our ArrayList
        homeworkList.add(assignment);
        System.out.println("Assignment created successfully!\n");
    }

    // This method marks a selected assignment as completed
    public void markCompleted(Scanner scanner) {
        if (homeworkList.isEmpty()) {
            System.out.println("You don't have any assignments yet.\n");
            return;
        }

        System.out.println("\n-_-_- Mark Assignment Completed -_-_-");

        // Display all assignments with numbers so user can pick one
        for (int i = 0; i < homeworkList.size(); i++) {
            HashMap<String, String> hw = homeworkList.get(i);

            // Get the completed value and turn it into a readable status
            String status = hw.get("completed").equals("true") ? "Done" : "Pending";

            System.out.println((i + 1) + ". " + hw.get("title") +
                    " | Course: " + hw.get("course") +
                    " | Due: " + hw.get("due") +
                    " | Status: " + status);
        }

        System.out.print("\nWhich assignment is completed? (number): ");
        String input = scanner.nextLine().trim();

        // try-catch in case user types a letter instead of a number
        try {
            int selection = Integer.parseInt(input) - 1;

            if (selection < 0 || selection >= homeworkList.size()) {
                System.out.println("Invalid choice.\n");
                return;
            }

            // Update the completed field in the HashMap to "true"
            homeworkList.get(selection).put("completed", "true");

            // Pick a random motivational message
            String[] messages = {
                "You did it! Task conquered!",
                "Great job, keep going!",
                "Another assignment off the list!",
                "Nice work, you're staying on top of it!"
            };

            // random.nextInt(messages.length) picks a number from 0 to 3
            String message = messages[random.nextInt(messages.length)];
            System.out.println("Assignment marked as completed!");
            System.out.println(message + "\n");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.\n");
        }
    }

    // This method displays all assignments in the list
    public void seeAllAssignments() {
        if (homeworkList.isEmpty()) {
            System.out.println("No assignments yet. Add one to get started.\n");
            return;
        }

        System.out.println("\n-_-_- All Assignments -_-_-");

        for (HashMap<String, String> hw : homeworkList) {
            // This is a for-each loop — it goes through every HashMap in the list
            String status = hw.get("completed").equals("true") ? "Done" : "Pending";

            System.out.println("- " + hw.get("title") +
                    " | Course: " + hw.get("course") +
                    " | Due: " + hw.get("due") +
                    " | Status: " + status);
        }

        System.out.println("\nYou currently have " + homeworkList.size() + " assignment(s).\n");
    }

    // This is the Homework Tracker menu
    public void homeworkMenu(Scanner scanner) {
        System.out.println("\n-_-_- Homework Tracker -_-_-");
        String choice = "";

        while (!choice.equals("4")) {
            System.out.println("1. Create Assignment");
            System.out.println("2. Mark Assignment Completed");
            System.out.println("3. See All Assignments");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose (1-4): ");
            choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    createAssignment(scanner);
                    break;
                case "2":
                    markCompleted(scanner);
                    break;
                case "3":
                    seeAllAssignments();
                    break;
                case "4":
                    System.out.println("Returning to Main Menu..\n");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-4.\n");
            }
        }
    }
}
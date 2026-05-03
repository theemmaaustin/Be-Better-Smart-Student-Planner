// GoalTracker.java
// This class handles everything related to the Goal Tracker feature
// Users can add goals, update progress, and view all their goals
// Each goal is stored as a String array: [goalName, progressPercentage]

import java.util.ArrayList;
import java.util.Scanner;

public class GoalTracker {

    private ArrayList<String[]> goals;

    // Constructor
    public GoalTracker() {
        goals = new ArrayList<>();
    }

    // This method adds a new goal to the list
    // It takes a Scanner so it can ask the user for input
    public void addGoal(Scanner scanner) {
        System.out.println("\n-_-_- Add New Goal -_-_-");
        System.out.print("Enter the name of your goal: ");
        String goalName = scanner.nextLine().trim();

        // Validate — don't allow empty goal names
        if (goalName.isEmpty()) {
            System.out.println("You haven't typed a goal name.\n");
            return; // exit the method early
        }

        // Create a String array with the goal name and starting progress of "0"
        String[] newGoal = {goalName, "0"};
        goals.add(newGoal);
        System.out.println("Your goal '" + goalName + "' has been added! Starting Progress: 0%\n");
    }


    public void updateGoal(Scanner scanner) {
        System.out.println("\n-_-_- Update Goal Progress -_-_-");

        // Can't update if there are no goals yet
        if (goals.isEmpty()) {
            System.out.println("No goals have been added yet.\n");
            return;
        }

        // Display all current goals with their progress
        printGoalList();

        System.out.print("Enter the number of the goal to update: ");
        String input = scanner.nextLine().trim();

        // try-catch here because parseInt will crash if user types a letter
        try {
            int goalNum = Integer.parseInt(input);
            int goalIndex = goalNum - 1; // subtract 1 because lists start at index 0

            // Check that the number is actually in range
            if (goalIndex < 0 || goalIndex >= goals.size()) {
                System.out.println("That goal number is not valid.\n");
                return;
            }

            System.out.print("Enter the new progress (0-100): ");
            String progressInput = scanner.nextLine().trim();
            int newProgress = Integer.parseInt(progressInput);

            // Validate the progress range
            if (newProgress < 0 || newProgress > 100) {
                System.out.println("Progress must be between 0 and 100.\n");
                return;
            }

            // Update the progress in the goal's String array
            // Index 1 is the progress slot — we convert the int back to a String
            goals.get(goalIndex)[1] = String.valueOf(newProgress);
            System.out.println("Progress updated for '" + goals.get(goalIndex)[0] + "' -> " + newProgress + "%\n");

            if (newProgress == 100) {
                System.out.println("You completed your goal! Amazing work!\n");
            }

        } catch (NumberFormatException e) {
            // This runs if the user typed something that isn't a number
            System.out.println("Invalid input. Please enter a number.\n");
        }
    }

    // This method displays all goals with their progress and status
    public void viewGoals() {
        System.out.println("\n-_-_- All Goals -_-_-");

        if (goals.isEmpty()) {
            System.out.println("No goals set yet.\n");
            return;
        }

        // Loop through the ArrayList using a standard for loop
        // i starts at 0 but we display i+1 so it looks like 1, 2, 3 to the user
        for (int i = 0; i < goals.size(); i++) {
            String name = goals.get(i)[0];       // index 0 is the goal name
            String progress = goals.get(i)[1];   // index 1 is the progress

            // Determine status based on progress
            String status;
            if (progress.equals("100")) {
                status = "COMPLETED";
            } else {
                status = "In Progress";
            }

            System.out.println("[" + (i + 1) + "] " + name + " - " + progress + "% - " + status);
        }
        System.out.println();
    }

    // This is a helper method — it just prints the numbered list of goals
    // We use it in both updateGoal() and viewGoals() to avoid repeating code
    private void printGoalList() {
        for (int i = 0; i < goals.size(); i++) {
            System.out.println((i + 1) + ". " + goals.get(i)[0] + " (" + goals.get(i)[1] + "%)");
        }
        System.out.println();
    }

    // This is the Goal Tracker menu loop
    // It keeps running until the user chooses to go back to the main menu
    public void goalMenu(Scanner scanner) {
        System.out.println("\n-_-_- Goal Tracker -_-_-");
        String choice = "";

        while (!choice.equals("4")) {
            System.out.println("1. Add Goal");
            System.out.println("2. Update Progress");
            System.out.println("3. View All Goals");
            System.out.println("4. Back to Main Menu");
            System.out.print("Choose (1-4): ");
            choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    addGoal(scanner);
                    break;
                case "2":
                    updateGoal(scanner);
                    break;
                case "3":
                    viewGoals();
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
// Main.java
// This is the entry point of the Be Better Smart Student Planner
// It displays the main menu and routes the user to each feature

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserProfile profile = new UserProfile();
        profile.startMenu(scanner);

        while (true) {

            // Show the menu options
            printMainMenu();

            String choice = scanner.nextLine().trim();

            // Route the user to the correct feature based on their input
            switch (choice) {
                case "1":
                    System.out.println("\n[Goal Tracker coming soon]\n");
                    break;
                case "2":
                    System.out.println("\n[Homework Tracker coming soon]\n");
                    break;
                case "3":
                    System.out.println("\n[Budget Tracker coming soon]\n");
                    break;
                case "4":
                    System.out.println("\n[GPA Calculator coming soon]\n");
                    break;
                case "5":
                    System.out.println("Goodbye! Keep being better.");
                    scanner.close(); 
                    return;         
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 5.\n");
            }
        }
    }

 
    static void printWelcomeBanner() {
        System.out.println("_________________________________");
        System.out.println("      Welcome to Be Better!      ");
        System.out.println("   A Smart Planner for Students  ");
        System.out.println("_________________________________\n");
    }

    
    static void printMainMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1. Goal Tracker");
        System.out.println("2. Homework Tracker");
        System.out.println("3. Budget Tracker");
        System.out.println("4. GPA Calculator");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }
}
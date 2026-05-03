// UserProfile.java
// This class handles everything related to the user's profile
// It stores their name, major, and graduation year
// It also saves and loads that data from a text file

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserProfile {


    String name;
    String major;
    String graduationYear;

    static final String PROFILE_FILE = "userProfile.txt";

  
    public UserProfile() {
        this.name = "";
        this.major = "";
        this.graduationYear = "";
    }

    public void saveProfile() {
        try {
            FileWriter writer = new FileWriter(PROFILE_FILE);
            writer.write(name + "\n");
            writer.write(major + "\n");
            writer.write(graduationYear + "\n");
            writer.close(); // Always close the writer when done
            System.out.println("Profile saved successfully.\n");
        } catch (IOException e) {
            // This runs if something goes wrong with writing the file
            System.out.println("Error saving profile: " + e.getMessage());
        }
    }

  
    public void loadProfile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(PROFILE_FILE));
            name = reader.readLine();           // reads first line
            major = reader.readLine();          // reads second line
            graduationYear = reader.readLine(); // reads third line
            reader.close(); // Always close the reader when done

            // Basic check — if any line was empty or missing, reset to empty string
            if (name == null) name = "";
            if (major == null) major = "";
            if (graduationYear == null) graduationYear = "";

        } catch (IOException e) {
            // This runs if the file doesn't exist yet — that's okay for new users
            System.out.println("No saved profile found. Starting fresh.\n");
        }
    }

    // This method sets up a brand new user by asking for their information
    public void setupNewUser(Scanner scanner) {
        System.out.println("\nWelcome! Let's set up your profile.");

        System.out.print("Enter your name: ");
        name = scanner.nextLine().trim();

        System.out.print("Enter your major: ");
        major = scanner.nextLine().trim();

        System.out.print("Enter your graduation year: ");
        graduationYear = scanner.nextLine().trim();

        // Save immediately after setup
        saveProfile();
        System.out.println("Welcome, " + name + "!\n");
    }

    // This method displays the loaded profile to the user
    public void displayProfile() {
        System.out.println("Name: " + name);
        System.out.println("Major: " + major);
        System.out.println("Graduation Year: " + graduationYear + "\n");
    }

    // This method runs the full start menu flow
    // It asks if the user is new or returning and acts accordingly
    public void startMenu(Scanner scanner) {
        System.out.println("_________________________________");
        System.out.println("      Welcome to Be Better!      ");
        System.out.println("   A Smart Planner for Students  ");
        System.out.println("_________________________________\n");

        System.out.println("Have you used Be Better on this computer before?");
        System.out.print("Enter yes or no: ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("yes")) {
            loadProfile();
            if (name.isEmpty()) {
                // File existed but was empty — treat as new user
                setupNewUser(scanner);
            } else {
                System.out.println("Welcome back, " + name + "!\n");
                displayProfile();
            }
        } else {
            setupNewUser(scanner);
        }
    }
}
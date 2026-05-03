// BudgetTracker.java
// This class handles the Budget Tracker feature
// Supports new and returning users with income and expense tracking
// Data is saved and loaded per user using FileReader and FileWriter

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BudgetTracker {

    // Fields to hold the current user's budget data
    private double income;
    private ArrayList<HashMap<String, String>> expensesList;
    private String currentFile;

    // Constructor — initializes empty state
    public BudgetTracker() {
        income = 0.0;
        expensesList = new ArrayList<>();
        currentFile = "";
    }

    // Saves income and expenses to the user's personal budget file
    // Format: first line is income, remaining lines are name|amount
    public void saveBudgetData() {
        try {
            FileWriter writer = new FileWriter(currentFile);
            writer.write(income + "\n");

            for (HashMap<String, String> expense : expensesList) {
                writer.write(expense.get("name") + "|" + expense.get("amount") + "\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving budget data: " + e.getMessage());
        }
    }

    // Loads income and expenses from the user's personal budget file
    public void loadBudgetData() {
        // Reset before loading so we don't stack old data on top of new
        income = 0.0;
        expensesList = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(currentFile));

            // First line is always income
            String incomeLine = reader.readLine();
            if (incomeLine != null && !incomeLine.isEmpty()) {
                income = Double.parseDouble(incomeLine);
            }

            // Every line after that is an expense in name|amount format
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // Split on | to separate name and amount
                    String[] parts = line.split("\\|");
                    if (parts.length == 2) {
                        HashMap<String, String> expense = new HashMap<>();
                        expense.put("name", parts[0]);
                        expense.put("amount", parts[1]);
                        expensesList.add(expense);
                    }
                }
            }

            reader.close();

        } catch (IOException e) {
            System.out.println("No saved budget found for this user. Starting fresh.\n");
        }
    }

    // Displays a full summary of income, expenses and remaining balance
    public void showSummary() {
        System.out.println("\n-_-_- ACCOUNT SUMMARY -_-_-");
        System.out.println("Income: $" + income);

        double totalExpenses = 0.0;

        if (expensesList.isEmpty()) {
            System.out.println("No expenses yet.");
        } else {
            System.out.println("\nExpenses:");
            for (HashMap<String, String> expense : expensesList) {
                System.out.println("- " + expense.get("name") + ": $" + expense.get("amount"));
                totalExpenses += Double.parseDouble(expense.get("amount"));
            }
        }

        double remaining = income - totalExpenses;
        System.out.println("\nTotal Expenses: $" + totalExpenses);
        System.out.println("Remaining Money: $" + remaining + "\n");
    }

    // Asks the user for one expense and saves it
    public void addExpense(Scanner scanner) {
        System.out.println("\n-_-_- Add a New Expense -_-_-");
        System.out.print("Enter expense name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter expense amount: ");
        String amountInput = scanner.nextLine().trim();

        // try-catch in case user types something that isn't a number
        try {
            double amount = Double.parseDouble(amountInput);

            if (amount < 0) {
                System.out.println("Expense amount cannot be negative.\n");
                return;
            }

            HashMap<String, String> expense = new HashMap<>();
            expense.put("name", name);
            expense.put("amount", String.valueOf(amount));
            expensesList.add(expense);

            saveBudgetData();
            System.out.println("Expense added successfully.\n");

        } catch (NumberFormatException e) {
            System.out.println("Invalid amount. Please enter a number.\n");
        }
    }

    // This is the main Budget Tracker flow
    // Handles both new and returning users
    public void budgetMenu(Scanner scanner) {
        System.out.println("\n-_-_- BUDGET TRACKER -_-_-");

        System.out.print("Enter your name (no spaces): ");
        String username = scanner.nextLine().trim();
        currentFile = username + "_budget.txt";

        System.out.println("Are you:");
        System.out.println("1. A new user");
        System.out.println("2. A returning user");
        System.out.print("Choose (1/2): ");
        String userType = scanner.nextLine().trim();

        if (userType.equals("1")) {
            // New user flow
            System.out.println("\nDo you currently have:");
            System.out.println("1. Income only");
            System.out.println("2. Savings only");
            System.out.println("3. Both income and savings");
            System.out.println("4. None (start at 0)");
            System.out.print("Choose (1-4): ");
            String moneyChoice = scanner.nextLine().trim();

            if (moneyChoice.equals("1") || moneyChoice.equals("3")) {
                System.out.print("Enter your income: ");
                String incomeInput = scanner.nextLine().trim();
                try {
                    income = Double.parseDouble(incomeInput);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid amount. Setting income to 0.\n");
                    income = 0.0;
                }
            } else {
                income = 0.0;
            }

            saveBudgetData();

            // Ask user to add expenses one by one
            System.out.println("\nNow let's add your expenses.");
            while (true) {
                addExpense(scanner);
                System.out.print("Do you want to add another expense? (yes/no): ");
                String more = scanner.nextLine().trim().toLowerCase();
                if (!more.equals("yes")) {
                    break;
                }
            }

            showSummary();

        } else if (userType.equals("2")) {
            // Returning user flow — load their file
            loadBudgetData();
            System.out.println("\nWelcome back, " + username + "!");

            String choice = "";
            while (!choice.equals("c")) {
                System.out.println("a. View summary");
                System.out.println("b. Add a new expense");
                System.out.println("c. Back to main menu");
                System.out.print("Choose (a/b/c): ");
                choice = scanner.nextLine().trim().toLowerCase();
                System.out.println();

                switch (choice) {
                    case "a":
                        showSummary();
                        break;
                    case "b":
                        addExpense(scanner);
                        System.out.print("Do you want to view summary? (yes/no): ");
                        String see = scanner.nextLine().trim().toLowerCase();
                        if (see.equals("yes")) {
                            showSummary();
                        }
                        break;
                    case "c":
                        System.out.println("Returning to main menu.\n");
                        break;
                    default:
                        System.out.println("Invalid option.\n");
                }
            }

        } else {
            System.out.println("Invalid choice.\n");
        }
    }
}
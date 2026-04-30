# 📚 Be Better — Smart Student Planner

A console-based productivity app built for students to manage goals, assignments, budgets, and GPA — all in one place.

Built in Java by **Emma Austin** as part of a CS project at NJIT.

---

## 🚀 How to Run

Make sure you have Java installed, then compile and run from the project folder:

```bash
javac *.java
java Main
```

No external libraries required.

---

## ✨ Features

### 🎯 Goal Tracker
Set personal goals and track your progress from 0–100%. It flags goals as completed once you hit 100%.

### 📝 Homework Tracker
Add assignments with course name and due date, mark them as completed, and view everything on your list. Includes randomized motivational messages when you finish a task.

### 💰 Budget Tracker
Supports new and returning users. Set up your income, log expenses, and view a running summary of what you've spent and what's left. Budget data is saved per user to a local file.

### 🎓 GPA Calculator
Input your courses, credit hours, and letter grades to calculate your GPA with NJIT grading scale. Handles multiple courses in one session.

---

## 📁 File Storage

The app saves user data locally:
- `userProfile.txt` — stores your name, major, and graduation year
- `[username]_budget.txt` — stores income and expenses per user

> These files are excluded from version control via `.gitignore`

---

## 🏗️ Project Structure

```
Main.java            - Entry point and main menu loop
UserProfile.java     - Profile setup and file I/O
GoalTracker.java     - Goal management
HomeworkTracker.java - Assignment tracking
BudgetTracker.java   - Income and expense tracking
GpaCalculator.java   - GPA computation
```

---

## 🛠️ Built With

- Java
- Core OOP principles — classes, encapsulation, ArrayList, HashMap
- File I/O via BufferedReader / BufferedWriter
- Scanner for user input
- Try-catch exception handling throughout

---

## 👩🏾‍💻 Contributors

- [Emma Austin](https://github.com/theemmaaustin)

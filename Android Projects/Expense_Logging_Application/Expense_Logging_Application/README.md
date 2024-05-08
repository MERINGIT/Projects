# Expense/Income Logging Application

## Overview
This mobile application is designed to log and manage expenses and income. It consists of three activities focusing on login/sign-up, expense overview, and expense/input details. The application utilizes shared preferences, Room/SQLite for data storage, RecyclerView, and UI fragments.

## Functionality

### Activity 1: Login and Sign Up
- Login Fragment: Includes input fields for username and password, a login button, and a sign-up button.
- Sign-Up Fragment: Contains input fields for profile name, username, and password, along with a create button.
- Data storage and retrieval using shared preferences.

### Activity 2: Expense Overview
- Top elements: Logout button, add expenses/income button, and profile name display.
- RecyclerView: Displays elements representing expenses/income, each with name, amount (with '+' or '-' indicating income or expense), and edit button.
- Data related to expenses stored in Room/SQLite.

### Activity 3: Expense/Input Details
- Two modes: Add mode (empty fields) and Edit mode (prefilled fields).
- Elements: Text input fields for name, amount, and description; input for expense or income; save and delete buttons.

## Implementation
- LoginActivity and SignUpFragment for user authentication and registration.
- ExpenseOverviewActivity with RecyclerView for displaying expenses/income.
- ExpenseInputDetailsActivity for adding/editing expenses/income.
- Use of shared preferences for user data storage and Room/SQLite for expense data.
- Fragment navigation and RecyclerView implementation for UI.

## Requirements
- Android Studio
- Kotlin programming knowledge
- Familiarity with Android development concepts (RecyclerView, Fragments, Room/SQLite, Shared Preferences)

## Usage
1. Clone the repository to your local machine.
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or physical device.
4. Login or sign up to access the expense management features.
5. Add, edit, or delete expenses/income as needed.

## Credits
This application was developed as part of the CSCI 4176/5708 Mobile Computing course. Credits to the course instructors for the assignment specifications and guidance.

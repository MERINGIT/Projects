package com.example.midterm// com.example.midterm.ThirdActivity.kt

import ExpenseViewModel
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.midterm.Data.Expense

class ThirdActivity : AppCompatActivity() {

    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button
    private lateinit var editTextName: EditText
    private lateinit var editTextAmount: EditText
    private lateinit var editTextDescription: EditText
    private lateinit var radioGroupType: RadioGroup
    private lateinit var radioExpense: RadioButton
    private lateinit var radioIncome: RadioButton

    private var isEditMode = false
    private var existingId: Long = 0
    private lateinit var expenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        // Initialize UI elements
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)
        editTextName = findViewById(R.id.editTextName)
        editTextAmount = findViewById(R.id.editTextAmount)
        editTextDescription = findViewById(R.id.editTextDescription)
        radioGroupType = findViewById(R.id.radioGroupType)
        radioExpense = findViewById(R.id.radioExpense)
        radioIncome = findViewById(R.id.radioIncome)

        // Check if the activity is in edit mode
        isEditMode = intent.getBooleanExtra("isEditMode", false)

        // If in edit mode, fetch the existing expense by ID and populate the UI
        if (isEditMode) {
            existingId = intent.getLongExtra("id", 0)
            expenseViewModel.selectExpenseById(existingId).observe(this) { expense ->
                expense?.let {
                    editTextName.setText(expense.name)
                    editTextAmount.setText(expense.amount)
                    editTextDescription.setText(expense.description)

                    // Check the appropriate radio button based on expense type (income or expense)
                    if (expense.isIncome) {
                        radioIncome.isChecked = true
                    } else {
                        radioExpense.isChecked = true
                    }
                }
            }

            // Setup functionality for deleting an existing expense
            setupDeleteFunctionality()
        } else {
            // Setup functionality for adding a new expense
            setupAddFunctionality()
            // Setup functionality for deleting (disabled for adding new expenses)
            setupDeleteFunctionality()
        }

        // Setup common functionality for saving changes
        setupSaveFunctionality()
    }

    // Functionality for adding a new expense
    private fun setupAddFunctionality() {
        btnSave.setOnClickListener {
            // Implement logic to save the entry
            saveEntry()

            // Return to Activity 2
            finish()
        }
    }

    // Functionality for deleting an existing expense
    private fun setupDeleteFunctionality() {
        btnDelete.setOnClickListener {
            // Check if the expense to delete exists
            if (isEditMode) {
                // Fetch the expense by ID and observe the changes
                expenseViewModel.selectExpenseById(existingId).observe(this) { expense ->
                    expense?.let {
                        Log.d("DeleteExpense", "Expense found: $it")

                        // Call the deleteExpense method in the ViewModel with the existing expense
                        expenseViewModel.deleteExpense(it)

                        Log.d("DeleteExpense", "Expense deleted")
                        Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show()

                        // Return to Activity 2
                        finish()
                    }
                }
            } else {
                // Inform the user that there's nothing to delete (this case is disabled for adding new expenses)
                Toast.makeText(this, "Deleted successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    // Common functionality for saving changes
    private fun setupSaveFunctionality() {
        btnSave.setOnClickListener {
            // Implement logic to save the entry
            saveEntry()

            // Return to Activity 2
            finish()
        }
    }

    // Save the entry (either add new or update existing)
    private fun saveEntry() {
        // Implement the logic to save the entry to the Room database
        // Use the values from the EditText fields (editTextName, editTextAmount, editTextDescription)

        val name = editTextName.text.toString()
        val amount = editTextAmount.text.toString()
        val description = editTextDescription.text.toString()

        // Determine whether it's an expense or income based on the selected radio button
        val isIncome = radioIncome.isChecked

        // Create an Expense object
        val expense = Expense(name = name, amount = amount, description = description, isIncome = isIncome)

        // Use the ViewModel to insert or update the expense in the Room database
        if (isEditMode) {
            // Update existing entry
            expense.id = existingId // Set the ID for updating
            expenseViewModel.updateExpense(expense)
            Toast.makeText(this, "Updated successfully", Toast.LENGTH_SHORT).show()
        } else {
            // Insert new entry
            expenseViewModel.insertExpense(expense)
            Toast.makeText(this, "Inserted successfully", Toast.LENGTH_SHORT).show()
        }
    }
}

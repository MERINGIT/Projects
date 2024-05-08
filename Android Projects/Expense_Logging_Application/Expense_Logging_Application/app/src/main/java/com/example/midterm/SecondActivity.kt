// SecondActivity.kt

package com.example.midterm

import ExpenseViewModel
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.Data.Expense
import com.example.midterm.recyclerview.ExpenseAdapter
import kotlinx.coroutines.launch

/**
 * SecondActivity representing the main screen after successful login.
 * Displays a list of expenses and provides options for logout and adding new expenses.
 */
class SecondActivity : AppCompatActivity(), ExpenseAdapter.ExpenseItemClickListener {

    private lateinit var expenseViewModel: ExpenseViewModel
    private lateinit var expenseAdapter: ExpenseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense)

        // Initialize ViewModel and RecyclerView
        expenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewExpenses)
        expenseAdapter = ExpenseAdapter(this)
        recyclerView.adapter = expenseAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Set up button click listeners
        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            // Navigate back to the login screen
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        findViewById<Button>(R.id.btnAddExpense).setOnClickListener {
            // Open com.example.midterm.ThirdActivity in add mode
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        // Display the user's profile name
        val profileNameTextView: TextView = findViewById(R.id.tvProfileName)
        val profileName = intent.getStringExtra("profile_name")
        if (profileName != null) {
            profileNameTextView.text = "Profile Name: $profileName"
        }

        // Use lifecycleScope to launch a coroutine for observing LiveData
        lifecycleScope.launch {
            expenseViewModel.getAllExpenses().observe(this@SecondActivity) { expenses ->
                expenses?.let { expenseAdapter.setExpenses(it) }
            }
        }
    }

    // Implement the onItemClick method from ExpenseAdapter.ExpenseItemClickListener interface
    override fun onItemClick(expense: Expense) {
        // Handle item click as needed
        // For example, open com.example.midterm.ThirdActivity in edit mode with the selected expense details
        val intent = Intent(this, ThirdActivity::class.java).apply {
            putExtra("isEditMode", true)
            putExtra("id", expense.id)
            putExtra("existingName", expense.name)
            putExtra("existingAmount", expense.amount.toString())
            putExtra("existingDescription", expense.description)
        }
        startActivity(intent)
    }
}

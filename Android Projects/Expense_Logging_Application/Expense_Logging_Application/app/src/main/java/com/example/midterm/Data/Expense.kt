package com.example.midterm.Data

// Expense.kt

import androidx.room.Entity
import androidx.room.PrimaryKey

// Define the Expense data class, which represents an entry in the expenses database table.
@Entity(tableName = "expenses_new")
data class Expense(
    // Primary key for the database table, auto-generating unique IDs.
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    // Name of the expense or income source.
    val name: String,

    // Amount associated with the expense or income.
    val amount: String,

    // Description or additional information about the expense.
    val description: String,

    // Boolean flag indicating whether the entry represents income (true) or expense (false).
    val isIncome: Boolean
)

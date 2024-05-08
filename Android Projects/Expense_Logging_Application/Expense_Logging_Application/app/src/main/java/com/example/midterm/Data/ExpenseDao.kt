package com.example.midterm.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

// ExpenseDao: Data Access Object interface for handling database operations related to Expense entities.
@Dao
interface ExpenseDao {

    // Insert a new expense into the expenses_new table.
    @Insert
    fun insertExpense(expense: Expense)

    // Update an existing expense in the expenses_new table.
    @Update
    fun updateExpense(expense: Expense)

    // Delete an expense from the expenses_new table.
    @Delete
    fun deleteExpense(expense: Expense)

    // Delete an expense by its ID from the expenses_new table.
    @Query("DELETE FROM expenses_new WHERE id = :expenseId")
    fun deleteExpenseById(expenseId: Long)

    // Retrieve a specific expense by its ID from the expenses_new table.
    @Query("SELECT * FROM expenses_new WHERE id = :expenseId")
    fun selectExpenseById(expenseId: Long): LiveData<Expense>

    // Retrieve all expenses from the expenses_new table.
    @Query("SELECT * FROM expenses_new")
    fun getAllExpenses(): LiveData<List<Expense>>
}

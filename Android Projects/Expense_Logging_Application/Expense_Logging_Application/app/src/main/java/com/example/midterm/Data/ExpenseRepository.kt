// ExpenseRepository.kt
package com.example.midterm.Data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// ExpenseRepository: Repository class responsible for handling data operations between the ViewModel and the DAO.
class ExpenseRepository(private val expenseDao: ExpenseDao) {

    // Coroutine function to insert an expense into the database asynchronously.
    suspend fun insertExpense(expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseDao.insertExpense(expense)
        }
    }

    // Coroutine function to update an expense in the database asynchronously.
    suspend fun updateExpense(expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseDao.updateExpense(expense)
        }
    }

    // Coroutine function to delete an expense from the database asynchronously.
    suspend fun deleteExpense(expense: Expense) {
        withContext(Dispatchers.IO) {
            expenseDao.deleteExpense(expense)
        }
    }

    // Function to retrieve a specific expense by its ID from the database.
    // Returns a LiveData object to observe changes.
    fun getExpenseById(expenseId: Long): LiveData<Expense> {
        return expenseDao.selectExpenseById(expenseId)
    }

    // Function to retrieve all expenses from the database.
    // Returns a LiveData object to observe changes.
    fun getAllExpenses(): LiveData<List<Expense>> {
        return expenseDao.getAllExpenses()
    }
}

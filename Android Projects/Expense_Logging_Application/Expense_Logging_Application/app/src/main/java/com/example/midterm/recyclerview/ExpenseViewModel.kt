// ExpenseViewModel.kt
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.midterm.Data.Expense
import com.example.midterm.Data.ExpenseDatabase
import com.example.midterm.Data.ExpenseRepository
import kotlinx.coroutines.launch

// ExpenseViewModel: ViewModel class responsible for interacting with the repository and providing data to the UI.
class ExpenseViewModel(application: Application) : AndroidViewModel(application) {

    // Repository instance to handle data operations.
    private val repository: ExpenseRepository

    // Initialization block to create an instance of the repository.
    init {
        // Get the ExpenseDao from the database and pass it to the repository.
        val expenseDao = ExpenseDatabase.getDatabase(application).expenseDao()
        repository = ExpenseRepository(expenseDao)
    }

    // Coroutine function to insert an expense into the database asynchronously.
    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    // Coroutine function to update an expense in the database asynchronously.
    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repository.updateExpense(expense)
    }

    // Coroutine function to delete an expense from the database asynchronously.
    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense)
    }

    // Function to retrieve a specific expense by its ID from the database.
    // Returns a LiveData object to observe changes.
    fun selectExpenseById(expenseId: Long): LiveData<Expense> {
        return repository.getExpenseById(expenseId)
    }

    // Function to retrieve all expenses from the database.
    // Returns a LiveData object to observe changes.
    fun getAllExpenses(): LiveData<List<Expense>> {
        return repository.getAllExpenses()
    }
}

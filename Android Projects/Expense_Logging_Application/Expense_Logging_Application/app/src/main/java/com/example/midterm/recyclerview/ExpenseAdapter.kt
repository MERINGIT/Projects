// ExpenseAdapter.kt

package com.example.midterm.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midterm.Data.Expense
import com.example.midterm.R

/**
 * Adapter for populating a RecyclerView with Expense items.
 *
 * @param listener Interface for handling item click events.
 */
class ExpenseAdapter(
    private val listener: ExpenseItemClickListener
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    // List to hold the Expense items
    private var expensesList: List<Expense> = emptyList()

    /**
     * Set the list of expenses for the adapter.
     *
     * @param expenses List of Expense items to be displayed.
     */
    fun setExpenses(expenses: List<Expense>) {
        expensesList = expenses
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        // Inflate the layout for each item view and create a ViewHolder
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.expense_item, parent, false)
        return ExpenseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        // Bind data to the ViewHolder
        val currentExpense = expensesList[position]
        holder.bind(currentExpense)
    }

    override fun getItemCount(): Int {
        // Return the number of items in the list
        return expensesList.size
    }

    /**
     * ViewHolder class representing an item view in the RecyclerView.
     *
     * @param itemView View for a single item in the RecyclerView.
     */
    inner class ExpenseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Views within the item view
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewAmount: TextView = itemView.findViewById(R.id.textViewAmount)
        private val btnEdit: Button = itemView.findViewById(R.id.btnEdit)

        /**
         * Bind data to the item view.
         *
         * @param expense Expense object representing the data to be displayed.
         */
        fun bind(expense: Expense) {
            // Set text values for TextViews
            textViewName.text = "Name:${expense.name}"

            // Display income amounts with a '+' sign and expense amounts with a '-' sign
            val amountText = when {
                expense.isIncome -> "Amount :+${expense.amount}"
                else -> "Amount: -${expense.amount}"
            }
            textViewAmount.text = amountText

            // Set click listener for the Edit button and invoke the onItemClick method
            btnEdit.setOnClickListener {
                listener.onItemClick(expense)
            }
        }
    }

    /**
     * Interface for handling item click events in the RecyclerView.
     */
    interface ExpenseItemClickListener {
        /**
         * Called when an item in the RecyclerView is clicked.
         *
         * @param expense Expense object representing the clicked item.
         */
        fun onItemClick(expense: Expense)
    }
}

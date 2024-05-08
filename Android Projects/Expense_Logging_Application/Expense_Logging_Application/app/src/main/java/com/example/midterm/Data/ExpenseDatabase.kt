package com.example.midterm.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// ExpenseDatabase: Room database class that defines the database configuration.
@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class ExpenseDatabase : RoomDatabase() {

    // Abstract method to retrieve the ExpenseDao, which contains database operations.
    abstract fun expenseDao(): ExpenseDao

    // Companion object to provide methods for creating and accessing the database.
    companion object {
        // Volatile ensures that the INSTANCE variable is always up-to-date and visible to all threads.
        @Volatile
        private var INSTANCE: ExpenseDatabase? = null

        // Method to get an instance of the ExpenseDatabase.
        fun getDatabase(context: Context): ExpenseDatabase {
            // If an instance already exists, return it to avoid creating multiple instances.
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // Synchronized block to ensure that only one thread can create an instance at a time.
            synchronized(this) {
                // Create a new database instance using Room's databaseBuilder.
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "expense_database"
                ).build()

                // Set the INSTANCE variable to the newly created instance.
                INSTANCE = instance

                // Return the created instance.
                return instance
            }
        }
    }
}

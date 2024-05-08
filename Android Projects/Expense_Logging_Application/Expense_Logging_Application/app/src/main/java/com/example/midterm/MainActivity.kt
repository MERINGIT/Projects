// MainActivity.kt
package com.example.midterm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import com.example.midterm.fragments.LoginFragment
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.midterm.fragments.SignUpFragment

// MainActivity: The main activity of the application responsible for managing fragments and navigation.
class MainActivity : AppCompatActivity() {

    // Shared Preferences for storing data persistently across app launches.
    private lateinit var sharedPreferences: SharedPreferences

    // Called when the activity is first created.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the layout for this activity
        setContentView(R.layout.main_activity_layout)

        // Check if savedInstanceState is null to avoid overlapping fragments on configuration changes
        if (savedInstanceState == null) {
            // Replace the initial fragment with the LoginFragment
            replaceFragment(LoginFragment())
        }
    }

    // Method for replacing fragments in the fragment container
    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    // Method for navigating to Activity Two (replace this with your actual logic)
    fun navigateToActivityTwo(value: String) {
        // Extract the profileName value
        val profileName = value

        // Check if profileName is not empty before navigating
        if (profileName != null) {
            if (profileName.isNotEmpty()) {
                // Show a welcome message using a Toast
                Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()

                // Create an Intent to navigate to the SecondActivity and pass the profileName
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("profile_name", profileName)
                startActivity(intent)
            }
        }
    }

    // Private method to show a Toast message
    private fun showToast(message: String) {
        // For simplicity, showing a Toast for messages
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}

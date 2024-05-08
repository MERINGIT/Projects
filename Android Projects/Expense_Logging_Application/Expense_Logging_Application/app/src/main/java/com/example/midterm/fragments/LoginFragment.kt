// LoginFragment.kt
package com.example.midterm.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.midterm.MainActivity
import com.example.midterm.R

// LoginFragment: Fragment for handling user login.
class LoginFragment : Fragment() {

    // Shared Preferences for storing login information.
    private lateinit var sharedPreferences: SharedPreferences

    // Called to create the view hierarchy associated with the fragment.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    // Called immediately after onCreateView() has returned.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set up the click listener for the login button
        view.findViewById<Button>(R.id.buttonLogin).setOnClickListener {
            // Retrieve entered username and password
            val username = view.findViewById<EditText>(R.id.editTextUsername).text.toString().trim()
            val password = view.findViewById<EditText>(R.id.editTextPassword).text.toString().trim()

            // Validate login details
            if (validateLoginDetails(username, password)) {
                // Save login status in SharedPreferences and navigate to Activity Two
                saveLoginStatus(true)
                val value = sharedPreferences.getString(SignUpFragment.KEY_PROFILE_NAME, "") ?: ""
                (activity as? MainActivity)?.navigateToActivityTwo(value)
            } else {
                // Show an error message (e.g., using a Toast)
                showToast("Invalid username or password")
            }
        }

        // Set up the click listener for the sign-up button
        view.findViewById<Button>(R.id.buttonSignUp).setOnClickListener {
            // Navigate to Sign-Up Fragment
            (activity as? MainActivity)?.replaceFragment(SignUpFragment())
        }
    }

    // Method to validate entered login details against saved credentials.
    private fun validateLoginDetails(username: String, password: String): Boolean {
        val savedUsername = getSavedUsername()
        val savedPassword = getSavedPassword()

        return username == savedUsername && password == savedPassword
    }

    // Method to retrieve the saved username from SharedPreferences.
    private fun getSavedUsername(): String {
        return sharedPreferences.getString(SignUpFragment.KEY_USERNAME, "") ?: ""
    }

    // Method to retrieve the saved password from SharedPreferences.
    private fun getSavedPassword(): String {
        return sharedPreferences.getString(SignUpFragment.KEY_PASSWORD, "") ?: ""
    }

    // Method to save the login status in SharedPreferences.
    private fun saveLoginStatus(isLoggedIn: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean("isLoggedIn", isLoggedIn)
            apply()
        }
    }

    // Method to show a Toast message.
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

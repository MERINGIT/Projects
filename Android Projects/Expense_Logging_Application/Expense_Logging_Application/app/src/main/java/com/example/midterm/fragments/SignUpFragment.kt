// SignUpFragment.kt
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

// SignUpFragment: Fragment for user registration.
class SignUpFragment : Fragment() {

    // Shared Preferences for storing user registration information.
    private lateinit var sharedPreferences: SharedPreferences

    // Called to create the view hierarchy associated with the fragment.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup, container, false)
    }

    // Called immediately after onCreateView() has returned.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        // Set up click listener for the create account button
        view.findViewById<Button>(R.id.buttonCreateAccount).setOnClickListener {
            // Validate and save user details using SharedPreferences
            saveUserDetails()
            // Show a Toast indicating successful registration
            Toast.makeText(requireContext(), "Registered Successfully", Toast.LENGTH_SHORT).show()

            // Navigate back to the LoginFragment
            (activity as? MainActivity)?.replaceFragment(LoginFragment())
        }
    }

    // Method to save user details to SharedPreferences
    private fun saveUserDetails() {
        // Retrieve user details from the EditText fields
        val profileName = view?.findViewById<EditText>(R.id.editTextProfileName)?.text.toString().trim()
        val username = view?.findViewById<EditText>(R.id.editTextSignUpUsername)?.text.toString().trim()
        val password = view?.findViewById<EditText>(R.id.editTextSignUpPassword)?.text.toString().trim()

        // Save user details to SharedPreferences
        with(sharedPreferences.edit()) {
            putString(KEY_PROFILE_NAME, profileName)
            putString(KEY_USERNAME, username)
            putString(KEY_PASSWORD, password)
            apply()
        }
    }

    // Companion object to hold constant keys for SharedPreferences
    companion object {
        const val KEY_PROFILE_NAME = "profile_name"
        const val KEY_USERNAME = "username"
        const val KEY_PASSWORD = "password"
    }
}

package com.example.assignment3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CityInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city_info)

        // Retrieve continent and city information from the Intent, defaulting to "Unknown" if not present
        val continent = intent.getStringExtra("continent") ?: "Unknown Continent"
        val city = intent.getStringExtra("city") ?: "Unknown City"

        // Set the activity title dynamically based on the selected continent and city
        title = "About the $city, $continent"

        // Create an instance of the CityInfoFragment with city and continent data
        val fragment = CityInfoFragment.newInstance(city, continent)

        // Replace the fragment container with the CityInfoFragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}

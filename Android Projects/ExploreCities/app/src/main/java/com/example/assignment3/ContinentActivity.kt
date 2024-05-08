package com.example.assignment3;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ContinentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the activity_continent layout resource
        setContentView(R.layout.activity_continent)

        // Retrieve the selected continent from the Intent, defaulting to "Unknown Continent" if not present
        val continent = intent.getStringExtra("continent") ?: "Unknown Continent"

        // Set the activity title dynamically based on the selected continent
        title = "Discovering $continent: Select a city"

        // Retrieve the list of cities for the selected continent using the CityRepository
        val cities = CityRepository.getCitiesByContinent(continent)

        // Find the RecyclerView in the layout using its ID
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Set the LinearLayoutManager for the RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Create an adapter for the RecyclerView using the CityCardAdapter class
        val adapter = CityCardAdapter(cities) { selectedCity ->

            // Create an Intent to navigate to the CityInfoActivity upon selecting a city
            val intent = Intent(this, CityInfoActivity::class.java)

            // Pass selected city and continent data as extras to the Intent
            intent.putExtra("city", selectedCity.name)
            intent.putExtra("continent", selectedCity.continent)

            // Start the CityInfoActivity
            startActivity(intent)
        }

        // Set the adapter for the RecyclerView
        recyclerView.adapter = adapter
    }
}

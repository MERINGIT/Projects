// Package declaration indicating the location of the MainActivity class
package com.example.assignment3;

// Import statements for necessary Android and app-specific classes
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.assignment3.R // Importing the R class for resource references

// MainActivity class, extending AppCompatActivity
class MainActivity : AppCompatActivity() {

    // Overriding the onCreate method to initialize the activity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Setting the content view to the activity_main layout resource
        setContentView(R.layout.activity_main)

        // Setting the title of the activity
        title = "Select a Continent"

        // Array of continents to be displayed in the ListView
        val continents = arrayOf("Asia", "Europe", "North America", "South America", "Africa", "Australia")

        // Creating an ArrayAdapter to bind data to the ListView
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, continents)

        // Finding the ListView in the layout using its ID
        val listView: ListView = findViewById(R.id.listView)

        // Setting the adapter for the ListView
        listView.adapter = adapter

        // Setting a click listener for the items in the ListView
        listView.setOnItemClickListener { _, _, position, _ ->
            // Getting the selected continent based on the clicked position
            val selectedContinent = continents[position]

            // Creating an Intent to start the ContinentActivity
            val intent = Intent(this, ContinentActivity::class.java)

            // Passing the selected continent as an extra to the Intent
            intent.putExtra("continent", selectedContinent)

            // Starting the ContinentActivity
            startActivity(intent)
        }
    }
}

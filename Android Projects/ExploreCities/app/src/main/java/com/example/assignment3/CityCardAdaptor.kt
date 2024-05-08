package com.example.assignment3

// CityCardAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the RecyclerView to display CityCard items
class CityCardAdapter(private val cities: List<City>, private val onCityClickListener: (City) -> Unit) :
    RecyclerView.Adapter<CityCardAdapter.CityCardViewHolder>() {

    // ViewHolder class to represent each item in the RecyclerView
    class CityCardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cityImageView: ImageView = itemView.findViewById(R.id.cityImageView)
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityNameTextView)
    }

    // Method called to create a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityCardViewHolder {
        // Inflate the layout for each item and return a ViewHolder
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_card_item, parent, false)
        return CityCardViewHolder(itemView)
    }

    // Method called to bind data to views in the ViewHolder
    override fun onBindViewHolder(holder: CityCardViewHolder, position: Int) {
        // Get the data for the current city
        val currentCity = cities[position]

        // Set the image resource and text for the city in the ViewHolder
        holder.cityImageView.setImageResource(currentCity.imageResId)
        holder.cityNameTextView.text = currentCity.name

        // Set a click listener for the entire CardView to handle city selection
        holder.itemView.setOnClickListener { onCityClickListener(currentCity) }
    }

    // Method to determine the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return cities.size
    }
}

package com.example.explorecityprovince

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.explorecityprovince.Adapters.CitiyInfoAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//Need to implement TabView
class CitiesActivity : AppCompatActivity() {

    val tabsArray = arrayOf("Mumbai", "Kolkata", "Goa")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cities_info)

        val viewPager = findViewById<ViewPager2>(R.id.viewpager)
        val tablayout = findViewById<TabLayout>(R.id.tablayout)

        val adapter = CitiyInfoAdapter(supportFragmentManager,lifecycle)
        viewPager.adapter = adapter

        TabLayoutMediator(tablayout,viewPager){
                tab, position -> tab.text = tabsArray[position]
        }.attach()
    }
}
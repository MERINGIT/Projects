package com.example.explorecityprovince

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.explorecityprovince.Adapters.ProvinceInfoAdapter
import com.example.explorecityprovince.Models.Province

// Implemented ViewPager to display different Views of province Info
class ProvinceInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_province_info)

        val viewpage : ViewPager2 = findViewById(R.id.viewpager)
        val provincesList = getProvinces()
        val adapter = ProvinceInfoAdapter(provincesList)
        viewpage.adapter = adapter
        viewpage.clipToPadding = false
        viewpage.clipChildren = false
        viewpage.offscreenPageLimit =2
        viewpage.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);

    }

    private fun getProvinces() : MutableList<Province> {

        val provinces = mutableListOf<Province>()
        provinces.add(Province("Kerala",R.drawable.kerala,R.string.kerala))
        //https://unsplash.com/photos/brown-boat-on-body-of-water-near-green-trees-during-daytime-29ezCWtMtnM, last accessed 20/03/2024
        provinces.add(Province("Rajasthan",R.drawable.rajasthan,R.string.rajasthan))
        //https://www.sotc.in/blog/uncategorized/colour-coordinated-cities-rajasthan,  last accessed 20/03/2024
        provinces.add(Province("Kashmir",R.drawable.kashmir,R.string.kashmir))
        //https://commons.wikimedia.org/wiki/File:Majestic_snow_capped_mountains_at_Gulmarg,_Kashmir,_India.jpg , last accessed 20/03/2024
        return provinces
    }
}
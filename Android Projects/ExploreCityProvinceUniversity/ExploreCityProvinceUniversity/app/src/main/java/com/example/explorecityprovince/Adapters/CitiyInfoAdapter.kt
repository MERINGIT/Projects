package com.example.explorecityprovince.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.explorecityprovince.Fragments.GoaFragment
import com.example.explorecityprovince.Fragments.KolkataFragment
import com.example.explorecityprovince.Fragments.MumbaiFragment

//For CityInfo
class CitiyInfoAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager,lifecycle){

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> return MumbaiFragment()
            1 -> return KolkataFragment()
        }
        return GoaFragment()
    }
}
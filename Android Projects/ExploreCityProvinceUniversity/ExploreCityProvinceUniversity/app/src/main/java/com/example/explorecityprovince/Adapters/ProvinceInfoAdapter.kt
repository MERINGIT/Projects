package com.example.explorecityprovince.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.explorecityprovince.Models.Province
import com.example.explorecityprovince.R

//For provinceInfo
class ProvinceInfoAdapter(var provincelist : MutableList<Province>)
    : RecyclerView.Adapter<ProvinceInfoAdapter.ProvinceInfoHolder>(){

    inner class ProvinceInfoHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var provinceImg : ImageView = itemView.findViewById(R.id.provinceimage)
        var provinceHead : TextView = itemView.findViewById(R.id.provinceheading)
        var provinceDesc : TextView = itemView.findViewById(R.id.provincedesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProvinceInfoHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.province_info_item,parent, false)
        return ProvinceInfoHolder(v)
    }

    override fun getItemCount(): Int {
        return provincelist.size
    }

    override fun onBindViewHolder(holder: ProvinceInfoHolder, position: Int) {
        val viewPagerItem: Province = provincelist[position]
        holder.provinceImg.setImageResource(viewPagerItem.image)
        holder.provinceHead.setText(viewPagerItem.name)
        holder.provinceDesc.setText(viewPagerItem.description)
    }
}
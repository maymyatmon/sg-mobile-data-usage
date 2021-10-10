package com.may.mobiledatausage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.may.mobiledatausage.R
import com.may.mobiledatausage.model.YearlyRecord

class ItemAdapter(
    private var dataSet: List<YearlyRecord>
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val tvYear: TextView = view.findViewById(R.id.item_year)
        val tvVolume: TextView = view.findViewById(R.id.item_volume)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataSet[position]
        holder.tvYear.text = item.year
        holder.tvVolume.text = "Total Volume: ${item.volume}"
    }


    override fun getItemCount() = dataSet.size
}
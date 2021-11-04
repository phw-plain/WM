package com.example.wm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class DetailedAdapter (val dataList: List<Detailed>)
    : RecyclerView.Adapter<DetailedAdapter.DetailedItemViewHolder>()
{
    class DetailedItemViewHolder(val view: View)
        : RecyclerView.ViewHolder(view)
    {
        lateinit var detailed : Detailed
        val detailedTitle = view.findViewById<EditText>(R.id.detailed_title)
        val handText = view.findViewById<EditText>(R.id.handwash_text)
        val machineText = view.findViewById<EditText>(R.id.machine_text)

        fun bind(d: Detailed) {
            this.detailed = d
            detailedTitle.setText(detailed.title)
            handText.setText(detailed.text1)
            machineText.setText(detailed.text2)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)

        return DetailedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailedItemViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int = dataList.size

//    override fun getItemViewType(position: Int): Int = R.layout.quote_edit_list_item
}
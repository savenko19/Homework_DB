package com.example.homework.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.homework.R
import com.example.homework.model.pojo.PersonNumber

class NumberAdapter(private val numbers: ArrayList<PersonNumber>) :
    RecyclerView.Adapter<NumberAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val number = itemView.findViewById<TextView>(R.id.number)

        fun bind(personNumber: PersonNumber) {
            name.text = personNumber.name
            number.text = personNumber.number
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.number_item_layout, parent, false))


    override fun onBindViewHolder(holder: NumberAdapter.ViewHolder, position: Int) {
        holder.bind(numbers[position])
    }

    override fun getItemCount() = numbers.size
}
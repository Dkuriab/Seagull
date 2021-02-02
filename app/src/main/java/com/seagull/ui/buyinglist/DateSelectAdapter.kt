package com.seagull.ui.buyinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.DateIdentificator

class DateSelectAdapter(
    var list: MutableList<DateIdentificator>,
    private val onClick: (Int) -> Unit,
    private val liveDataListener: (CardView, Int) -> Unit
) : RecyclerView.Adapter<DateSelectAdapter.DateViewHolder>() {

    inner class DateViewHolder(var root: View) : RecyclerView.ViewHolder(root) {
        private val date = root.findViewById<TextView>(R.id.date)

        fun bind(item: DateIdentificator) {
            val dateValue = "${item.day} ${item.month}"
            date.text = dateValue

            val position = list.lastIndexOf(item)
            liveDataListener(root as CardView, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val holder = DateViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.selectable_date, parent, false)
        )

        holder.root.setOnClickListener {
            onClick(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(
        holder: DateViewHolder,
        position: Int
    ) = holder.bind(list[position])

    override fun getItemCount() = list.size

}
package com.seagull.ui.buyinglist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.buyinglist.BuyingListItem

class BuyingListAdapter(
    var list: MutableList<BuyingListItem>,
    private val onClick: (Int) -> Unit,
) : RecyclerView.Adapter<BuyingListAdapter.ListViewHolder>() {

    class ListViewHolder(var root: View) : RecyclerView.ViewHolder(root) {
        private val name = root.findViewById<TextView>(R.id.name)
        private val weight = root.findViewById<TextView>(R.id.weight)
        private val measure = root.findViewById<TextView>(R.id.measure)

        fun bind(item: BuyingListItem) {
            name.text = item.name
            weight.text = item.weight.toString()
            measure.text = item.measureType.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val holder = ListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.buying_list_item, parent, false)
        )

        holder.root.setOnClickListener {
            onClick(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(
        holder: ListViewHolder,
        position: Int
    ) = holder.bind(list[position])

    override fun getItemCount() = list.size

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addNew(item: BuyingListItem) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun moveToEnd(position: Int) {
        if (position < 0) {
            return
        }
        list.removeAt(position).also {
            list.add(it)
        }
        if (position == 0) {
            notifyItemRemoved(position)
            notifyItemInserted(list.size - 1)
        } else {
            notifyItemMoved(position, list.size - 1)
        }
    }
}
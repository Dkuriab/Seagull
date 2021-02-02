package com.seagull.ui.buyinglist

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.seagull.R
import com.seagull.data.model.buyinglist.BuyingListItem

class BuyingListAdapter(
    var list: MutableList<BuyingListItem>,
    private val onClick: (Int, String) -> Unit,
    private val liveDataListener: (CardView, String) -> Unit
) : RecyclerView.Adapter<BuyingListAdapter.ListViewHolder>() {

    inner class ListViewHolder(var root: View) : RecyclerView.ViewHolder(root) {
        private val name = root.findViewById<TextView>(R.id.name)
        private val weight = root.findViewById<TextView>(R.id.weight)
        private val measure = root.findViewById<TextView>(R.id.measure)


        fun bind(item: BuyingListItem) {
            Log.i("ViewHolder bind", "binding ${item.name}")
            name.text = item.name
            weight.text = item.weight.toString()
            measure.text = item.measureType.toString()

            val card = root as CardView
            if (item.bought) {
                card.setCardBackgroundColor(Color.DKGRAY)
            } else {
                card.setCardBackgroundColor(Color.WHITE)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val holder = ListViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.buying_list_item, parent, false)
        )

        holder.root.setOnClickListener {
            onClick(holder.adapterPosition, it.findViewById<TextView>(R.id.name).text.toString())
        }

        return holder
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun addNew(item: BuyingListItem) {
        list.add(item)
        notifyItemInserted(list.size - 1)
    }

    fun changeStatus(position: Int) {
        if (position < 0) {
            return
        }
        list.removeAt(position).also {
            it.bought = !it.bought
            if (it.bought) {
                list.add(it)
                notifyItemMoved(position, list.size - 1)
                notifyItemChanged(list.size - 1)
            } else {
                list.add(0, it)
                notifyItemMoved(position, 0)
                notifyItemChanged(0)
            }
        }
    }
}
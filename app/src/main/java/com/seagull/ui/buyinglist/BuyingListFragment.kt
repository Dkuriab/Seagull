package com.seagull.ui.buyinglist

import android.animation.Animator
import android.animation.AnimatorInflater
import android.graphics.Color.BLACK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seagull.MyApplication.Companion.appContext
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.data.model.buyinglist.BuyingListItem
import com.seagull.data.model.buyinglist.MeasureType
import java.util.*

class BuyingListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.buying_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val first = view.findViewById<TextView>(R.id.first_text)
//        val second = view.findViewById<TextView>(R.id.second_text)
//        val third = view.findViewById<TextView>(R.id.third_text)
//        val fourth = view.findViewById<TextView>(R.id.fourth_text)
//        val fifth = view.findViewById<TextView>(R.id.fifth_text)
//        val sixth = view.findViewById<TextView>(R.id.sixth_text)
//        val seventh = view.findViewById<TextView>(R.id.seventh_text)

//        first.text = getDate(0).toString()
//        second.text = getDate(1).toString()
//        third.text = getDate(2).toString()
//        fourth.text = getDate(3).toString()
//        fifth.text = getDate(4).toString()
//        sixth.text = getDate(5).toString()
//        seventh.text = getDate(6).toString()

        setRecyclerView(view)
    }

    private fun setRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val viewManager = LinearLayoutManager(appContext)

        recyclerView.apply {
            layoutManager = viewManager
            adapter = BuyingListAdapter(
                //TODO ViewModel .buyingList.observe(...)

                list = mutableListOf(
                    BuyingListItem("Картошка", 20, MeasureType.ITEMS),
                    BuyingListItem("Перикись водорода", 100, MeasureType.GRAMS),
                    BuyingListItem("Лук", 1, MeasureType.ITEMS),
                    BuyingListItem("Растворитель", 1, MeasureType.KILOGRAMS),
                    BuyingListItem("Картошка", 30, MeasureType.ITEMS),
                    BuyingListItem("Potatoes", 40, MeasureType.ITEMS),
                    BuyingListItem("Картошка", 1000, MeasureType.ITEMS),
                    BuyingListItem("Картошка", 12, MeasureType.ITEMS),
                ),
                onClick = { position: Int ->
                    val adapter = adapter as BuyingListAdapter
                    adapter.moveToEnd(position)
                }
            )
        }

    }

    private fun getDate(after: Int): DateIdentificator {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return DateIdentificator(data[1], data[2].toInt())
    }
}

package com.seagull.ui.buyinglist

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seagull.MyApplication.Companion.appContext
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.data.model.buyinglist.BuyingListItem
import com.seagull.data.model.buyinglist.MeasureType
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext
import java.util.*

class BuyingListFragment : Fragment() {

    private val model: BuyingListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buying_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setRecyclers(view)
    }

    private fun setRecyclers(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val viewManager = LinearLayoutManager(appContext)
        recyclerView.apply {
            layoutManager = viewManager
            adapter = BuyingListAdapter(
                //TODO ViewModel .buyingList.observe(...)

                list = mutableListOf(
                    BuyingListItem("Картошка", 20, MeasureType.ITEMS, false),
                    BuyingListItem("Перикись водорода", 100, MeasureType.GRAMS, false),
                    BuyingListItem("Лук", 1, MeasureType.ITEMS, false),
                    BuyingListItem("Растворитель", 1, MeasureType.KILOGRAMS, false),
                    BuyingListItem("Potatoes", 40, MeasureType.ITEMS, false),
                ),
                onClick = { position: Int, name: String ->
                    model.changeIngredientStatus(name)
                    val adapter = adapter as BuyingListAdapter
                    adapter.changeStatus(position)
                },

                liveDataListener = { view: CardView, name: String ->
                    model.boughtIngredients.observe(viewLifecycleOwner, {
                        Log.i("LiveData", "$name is listened")
                        if (it.contains(name)) {
                            view.setCardBackgroundColor(Color.GREEN)
                        } else {
                            view.setCardBackgroundColor(Color.WHITE)
                        }
                    })
                }
            )
        }

        val datesViewManager = LinearLayoutManager(appContext, RecyclerView.HORIZONTAL, false)
        val datesRecyclerView = view.findViewById<RecyclerView>(R.id.dates_recycler_view)
        datesRecyclerView.apply {
            layoutManager = datesViewManager
            adapter = DateSelectAdapter(
                list = (0..6).map {
                    getDate(it)
                }.toMutableList(),
                onClick = { position: Int ->
                    model.changeDateStatus(position)
                },
                liveDataListener = { view: CardView, position: Int ->
                    model.selectedDates[position].observe(viewLifecycleOwner, {
                        if (it) {
                            view.setCardBackgroundColor(Color.GREEN)
                        } else {
                            view.setCardBackgroundColor(Color.BLACK)
                        }
                    })
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

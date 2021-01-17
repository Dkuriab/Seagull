package com.seagull.ui.buyinglist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.seagull.MyApplication.Companion.appContext
import com.seagull.R
import com.seagull.data.model.DateIdentificator
import com.seagull.ui.login.LoginActivity
import java.util.*

class BuyingListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buying_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val first = view.findViewById<TextView>(R.id.first_text)
        val second = view.findViewById<TextView>(R.id.second_text)
        val third = view.findViewById<TextView>(R.id.third_text)
        val fourth = view.findViewById<TextView>(R.id.fourth_text)
        val fifth = view.findViewById<TextView>(R.id.fifth_text)
        val sixth = view.findViewById<TextView>(R.id.sixth_text)
        val seventh = view.findViewById<TextView>(R.id.seventh_text)

        first.text = getDate(0).toString()
        second.text = getDate(1).toString()
        third.text = getDate(2).toString()
        fourth.text = getDate(3).toString()
        fifth.text = getDate(4).toString()
        sixth.text = getDate(5).toString()
        seventh.text = getDate(6).toString()
    }

    //TODO if there is empty day (nothing was selected) then make untouchable/make another onClick listener

    private fun getDate(after: Int) : DateIdentificator {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, after)
        val data = calendar.time.toString().split(" ")
        return DateIdentificator(data[1], data[2].toInt())
    }
}

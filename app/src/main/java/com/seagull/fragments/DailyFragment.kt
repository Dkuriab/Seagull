package com.seagull.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.seagull.R
import com.seagull.navigate
import kotlinx.android.synthetic.main.daily_fragment.*

class DailyFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.daily_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breakfast_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToBreakfastListFragment())
        }
    }
}
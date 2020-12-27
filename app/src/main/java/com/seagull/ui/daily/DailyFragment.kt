package com.seagull.ui.daily

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.seagull.R
import com.seagull.misc.navigate
import com.seagull.ui.MainActivity
import com.seagull.misc.slideUp
import com.seagull.misc.unHideBottomBar
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
        postponeEnterTransition()
        view.doOnPreDraw { startPostponedEnterTransition() }

        unHideBottomBar()

        breakfast_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToBreakfastListFragment())
        }

        lunch_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToLunchListFragment())
        }

        dinner_card.setOnClickListener {
            navigate(DailyFragmentDirections.actionDailyFragmentToDinnerListFragment())
        }

        setTransition()
    }

    private fun setTransition() {
        exitTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ true
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
        reenterTransition = MaterialSharedAxis(
            MaterialSharedAxis.Z,
            /* forward= */ false
        ).apply {
            duration = resources.getInteger(R.integer.reply_motion_duration_large).toLong()
        }
    }
}
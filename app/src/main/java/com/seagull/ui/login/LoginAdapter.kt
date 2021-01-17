package com.seagull.ui.login

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class LoginAdapter(fm: FragmentManager, private val context: Context, private val tabCount: Int) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount(): Int {
        return tabCount
    }

    override fun getItem(position: Int): Fragment {
        Log.d("login","Get Item $position")
        return when (position) {
            0 -> LoginFragment()
            1 -> RegisterFragment()
            else -> RegisterFragment()
        }
    }
}
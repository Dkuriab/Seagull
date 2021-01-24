package com.seagull.misc

import android.view.View
import androidx.core.view.forEach
import com.seagull.ui.MainActivity

fun slideUp(view: View) {
    view.animate().translationY(0F)
}

fun slideDown(view: View) {
    view.animate().translationY(view.height.toFloat())
}

fun unHideBottomBar() {
    slideUp(MainActivity.bottomNavigationView)
    MainActivity.bottomNavigationView.menu.forEach {
        it.isEnabled = true
    }
}

fun hideBottomBar() {
    slideDown(MainActivity.bottomNavigationView)
    MainActivity.bottomNavigationView.menu.forEach {
        it.isEnabled = false
    }
}
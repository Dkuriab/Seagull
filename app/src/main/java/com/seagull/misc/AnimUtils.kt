package com.seagull.misc

import android.view.View
import android.view.animation.TranslateAnimation
import androidx.core.view.forEach
import com.seagull.ui.MainActivity

fun slideUp(view: View) {
    val animate =
        TranslateAnimation(
            0F,
            0F,
            view.height.toFloat(),
            0F
        )
    animate.duration = 300
    animate.fillAfter = true
    view.startAnimation(animate)
    view.isEnabled = true
}

fun slideDown(view: View) {
    val animate = TranslateAnimation(
        0F,
        0F,
        0F,
        view.height.toFloat()
    )
    animate.duration = 300
    animate.fillAfter = true
    view.startAnimation(animate)
    view.isEnabled = false
}

fun unHideBottomBar() {
    if (!MainActivity.bottomNavigationView.isEnabled) {
        slideUp(MainActivity.bottomNavigationView)
        MainActivity.bottomNavigationView.menu.forEach { it.isEnabled = true }
    }
}
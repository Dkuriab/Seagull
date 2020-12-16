package com.seagull.misc

import android.view.View
import android.view.animation.TranslateAnimation

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
    view.isActivated = true
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
    view.isActivated = false
}
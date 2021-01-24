package com.seagull.misc

import android.animation.Animator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import androidx.core.view.forEach
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
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

fun getSlideAnimatorWithStartDelay(startPosition: Float): TranslateAnimation {
    val animator = TranslateAnimation(
        Animation.RELATIVE_TO_PARENT, startPosition,
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f,
        Animation.RELATIVE_TO_PARENT, 0.0f
    )
    animator.duration = 400
    animator.interpolator = FastOutSlowInInterpolator()
    return animator
}
package pro.jsandoval.kantotest.util.ext

import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.EditText

fun View.startAlphaAnimation(duration: Long = 200L, visibility: Int) {
    val alphaAnimation = if (visibility == View.VISIBLE) AlphaAnimation(0f, 1f) else AlphaAnimation(1f, 0f)
    alphaAnimation.duration = duration
    alphaAnimation.fillAfter = true
    startAnimation(alphaAnimation)
}

fun EditText.getFieldText(): String = text.toString().trim()
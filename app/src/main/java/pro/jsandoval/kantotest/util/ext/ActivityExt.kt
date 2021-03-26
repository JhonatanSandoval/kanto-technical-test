package pro.jsandoval.kantotest.util.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.PowerManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

@SuppressLint("InvalidWakeLockTag")
fun Activity.lockScreen() {
    window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    val powerManager = getSystemService(Context.POWER_SERVICE) as PowerManager
    val wakeLock = powerManager.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP or PowerManager.FULL_WAKE_LOCK, "Delitel_Repartidor")
    wakeLock.acquire()
    wakeLock.release()
}
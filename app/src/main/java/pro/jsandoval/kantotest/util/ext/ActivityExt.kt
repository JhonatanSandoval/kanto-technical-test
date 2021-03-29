package pro.jsandoval.kantotest.util.ext

import android.Manifest
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.core.app.ActivityCompat

fun Activity.hideKeyboard() {
    if (currentFocus != null) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

fun Activity.requestCameraPermission(requestCode: Int) =
    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), requestCode)
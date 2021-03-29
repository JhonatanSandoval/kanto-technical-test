package pro.jsandoval.kantotest.util.ext

import android.content.pm.PackageManager
import android.os.Build

fun isNougatOrHigher(): Boolean = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)

fun IntArray.areAllPermissionsGranted(): Boolean {
    var granted = false
    if (isNotEmpty()) {
        forEach { item ->
            granted = (item == PackageManager.PERMISSION_GRANTED)
        }
    }
    return granted
}

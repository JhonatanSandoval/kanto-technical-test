package pro.jsandoval.kantotest.util.ext

import android.os.Build

fun isOreoOrHigher(): Boolean = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)

fun isNougatOrHigher(): Boolean = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
package pro.jsandoval.kantotest.util.ext

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import java.io.File

fun ViewGroup.inflater(): LayoutInflater = LayoutInflater.from(context)

fun Context.hasCameraPermission(): Boolean = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
fun Context.hasWriteExternalStoragePermission(): Boolean = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED

fun Context.getTempImageFile(): File {
    return File(externalCacheDir, "avatar.png")
}

package pro.jsandoval.kantotest.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import androidx.core.content.FileProvider
import pro.jsandoval.kantotest.BuildConfig
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.util.ext.getTempImageFile
import pro.jsandoval.kantotest.util.ext.isNougatOrHigher

object ImagePicker {

    @JvmStatic
    fun createPickImageChooser(context: Context): Intent {
        val intentList = mutableListOf<Intent>()
        addIntentsToList(context, intentList, Intent(Intent.ACTION_PICK).apply {
            setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        })
        addIntentsToList(context, intentList, Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            if (isNougatOrHigher()) {
                putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(context, "${BuildConfig.APPLICATION_ID}.fileprovider", context.getTempImageFile()))
            } else {
                putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(context.getTempImageFile()))
            }
        })
        return Intent.createChooser(intentList.removeAt(intentList.size - 1), context.getString(R.string.select_an_action)).apply {
            putExtra(Intent.EXTRA_INITIAL_INTENTS, (intentList.toTypedArray() as Array<Parcelable>))
        }
    }

    @JvmStatic
    private fun addIntentsToList(context: Context, intents: MutableList<Intent>, intent: Intent) {
        val resolveInfo = context.packageManager.queryIntentActivities(intent, 0)
        resolveInfo.forEach { item ->
            val packageName = item.activityInfo.packageName
            val targetedIntent = Intent(intent).apply { setPackage(packageName) }
            intents.add(targetedIntent)
        }
    }

}
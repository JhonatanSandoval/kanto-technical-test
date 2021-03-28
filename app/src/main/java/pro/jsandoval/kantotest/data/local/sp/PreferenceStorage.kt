package pro.jsandoval.kantotest.data.local.sp

import android.content.Context
import android.content.SharedPreferences
import pro.jsandoval.kantotest.util.delegates.BooleanPreference
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Since there are not webservices for update remote data, we only call them once
 */

interface PreferenceStorage {
    var isDataAlreadySync: Boolean
}

@Singleton
class SharedPreferenceStorage @Inject constructor(private val context: Context) : PreferenceStorage {

    private val prefs: Lazy<SharedPreferences> = lazy { context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override var isDataAlreadySync by BooleanPreference(prefs, KEY_IS_DATA_SYNC, false)

    companion object {
        private const val PREFS_NAME = "KantoTest-AppSp"

        private const val KEY_IS_DATA_SYNC = "recordListSync"
    }
}

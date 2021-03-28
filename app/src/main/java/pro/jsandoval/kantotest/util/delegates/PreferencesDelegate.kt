package pro.jsandoval.kantotest.util.delegates

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class BooleanPreference(
    private val prefs: Lazy<SharedPreferences>,
    private val key: String, private val defaultValue: Boolean
) : ReadWriteProperty<Any, Boolean> {
    override fun getValue(thisRef: Any, property: KProperty<*>): Boolean {
        return prefs.value.getBoolean(key, defaultValue)
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: Boolean) {
        prefs.value.edit { putBoolean(key, value) }
    }
}
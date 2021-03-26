package pro.jsandoval.kantotest.util.core

import pro.jsandoval.kantotest.BuildConfig.DEBUG
import timber.log.Timber

object TimberFactory {
    fun setupOnDebug() {
        Timber.uprootAll()
        if (DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
package pro.jsandoval.kantotest

import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.multidex.MultiDexApplication
import androidx.work.Configuration
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import pro.jsandoval.kantotest.util.core.TimberFactory
import javax.inject.Inject

@HiltAndroidApp
class App : MultiDexApplication(), Configuration.Provider {

    @Inject lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()

        initDebug()
        initCoil()
    }

    private fun initDebug() {
        TimberFactory.setupOnDebug()
    }

    private fun initCoil() {
        Coil.setDefaultImageLoader {
            ImageLoader(this) {
                crossfade(true)
                okHttpClient {
                    OkHttpClient.Builder()
                        .cache(CoilUtils.createDefaultCache(this@App))
                        .build()
                }
            }
        }
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .setMinimumLoggingLevel(Log.INFO)
            .build()
    }


}
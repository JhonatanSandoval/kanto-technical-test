package pro.jsandoval.kantotest

import androidx.multidex.MultiDexApplication
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import pro.jsandoval.kantotest.util.core.TimberFactory

@HiltAndroidApp
class App : MultiDexApplication() {

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

}
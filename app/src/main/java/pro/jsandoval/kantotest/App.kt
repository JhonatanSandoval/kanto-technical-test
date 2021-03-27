package pro.jsandoval.kantotest

import androidx.multidex.MultiDexApplication
import coil.Coil
import coil.ImageLoader
import coil.util.CoilUtils
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import dagger.hilt.android.HiltAndroidApp
import okhttp3.OkHttpClient
import pro.jsandoval.kantotest.util.core.TimberFactory

@HiltAndroidApp
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        initDebug()
        initCoil()
        createSimpleVideoCache()
    }

    private fun createSimpleVideoCache() {
        val leastRecentlyUsedCacheEvictor = LeastRecentlyUsedCacheEvictor(90 * 1024 * 1024)
        val databaseProvider: DatabaseProvider = ExoDatabaseProvider(this)
        if (simpleVideoCache == null) {
            simpleVideoCache = SimpleCache(cacheDir, leastRecentlyUsedCacheEvictor, databaseProvider)
        }
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

    companion object {
        var simpleVideoCache: SimpleCache? = null
    }

}
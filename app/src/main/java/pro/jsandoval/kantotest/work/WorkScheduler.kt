package pro.jsandoval.kantotest.work

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkScheduler(private val context: Context) {

    private val workManager by lazy { WorkManager.getInstance(context) }
    private val networkConstraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

    fun scheduleSyncWorkerRepeating() {
        val workRequest = createStandardPeriodicWorkRequest<SyncWorker>(PERIODIC_TIME, PERIODIC_TIME_UNIT)
        workManager.apply {
            enqueueUniquePeriodicWork(SyncWorker.REPEATING_TAG, ExistingPeriodicWorkPolicy.KEEP, workRequest)
        }
    }

    private inline fun <reified T : CoroutineWorker> createStandardPeriodicWorkRequest(repeatInterval: Long, timeUnit: TimeUnit, inputDataBuilder: Data.Builder = Data.Builder()): PeriodicWorkRequest {
        inputDataBuilder.putBoolean(KEY_PERIODIC_CHECK, true)

        val workRequestBuilder = PeriodicWorkRequestBuilder<T>(repeatInterval, timeUnit, PERIODIC_TIME, TimeUnit.MINUTES) // the repeating could execute in the next 0-15min
        workRequestBuilder.setConstraints(networkConstraint)
        workRequestBuilder.setInputData(inputDataBuilder.build())
        return workRequestBuilder.build()
    }

    companion object {
        private const val KEY_PERIODIC_CHECK = "KEY_PERIODIC_CHECK"

        private const val PERIODIC_TIME = 15.toLong()
        private val PERIODIC_TIME_UNIT = TimeUnit.MINUTES
    }

}
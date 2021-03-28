package pro.jsandoval.kantotest.work

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.flow.collect
import pro.jsandoval.kantotest.data.util.MessageType
import pro.jsandoval.kantotest.domain.repository.UserRepository
import timber.log.Timber

class SyncWorker @WorkerInject constructor(
    @Assisted appContext: Context, @Assisted params: WorkerParameters,
    private val userRepository: UserRepository
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        // this piece of code could be called if we could have "update" remote webservices

        /*
        userRepository.fetchUserDetails().collect { dataState ->
            when (dataState.type) {
                MessageType.Success -> Timber.d("user sync data complete <-------------- ")
                MessageType.Error -> Timber.e("can not fetch user data: ${dataState.message}")
            }
        }
         */

        return Result.success()
    }

    companion object {
        const val REPEATING_TAG = "SyncWorker_Repeating"
    }
}
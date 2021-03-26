package pro.jsandoval.kantotest.util.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.NonCancellable.cancel
import pro.jsandoval.kantotest.util.coroutines.ioScope
import pro.jsandoval.kantotest.util.event.EmptySingleLiveEvent
import pro.jsandoval.kantotest.util.event.SingleLiveEvent
import timber.log.Timber

abstract class BaseViewModel : ViewModel(), CoroutineScope by ioScope() {

    val errorNetworkNotConnectedEvent = EmptySingleLiveEvent()
    val loaderDataEvent = SingleLiveEvent<Boolean>()
    val errorMessageDataEvent = SingleLiveEvent<String>()

    @InternalCoroutinesApi
    override fun onCleared() {
        try {
            cancel()
        } catch (e: Exception) {
            Timber.e(e, "The BaseViewModel CoroutineScope does not have an associated job to cancel...")
        }
        super.onCleared()
    }

}
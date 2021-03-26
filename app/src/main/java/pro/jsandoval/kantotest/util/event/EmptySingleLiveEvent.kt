package pro.jsandoval.kantotest.util.event

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import pro.jsandoval.kantotest.util.event.SingleLiveEvent

class EmptySingleLiveEvent : SingleLiveEvent<Unit>() {

    @MainThread
    fun call() {
        super.setValue(Unit)
    }

    @WorkerThread
    fun postCall() {
        super.postValue(Unit)
    }
}

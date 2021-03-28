package pro.jsandoval.kantotest.presentation.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import pro.jsandoval.kantotest.data.local.sp.PreferenceStorage
import pro.jsandoval.kantotest.data.util.MessageType
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.domain.repository.RecordRepository
import pro.jsandoval.kantotest.domain.repository.UserRepository
import pro.jsandoval.kantotest.util.base.BaseViewModel
import pro.jsandoval.kantotest.work.WorkScheduler
import timber.log.Timber

class MainViewModel @ViewModelInject constructor(
    private val workScheduler: WorkScheduler,
    private val preferenceStorage: PreferenceStorage,
    private val userRepository: UserRepository,
    private val recordRepository: RecordRepository
) : BaseViewModel() {

    private val _userDataEvent = MutableLiveData<User>()
    val userDataEvent: LiveData<User> = _userDataEvent

    init {
        checkFetchData()
    }

    private fun checkFetchData() = launch {
        if (!preferenceStorage.isDataAlreadySync) {
            runBlocking(IO) {
                val fetchUserDetails = userRepository.fetchUserDetails()
                val fetchRecords = recordRepository.fetchRecords()

                fetchUserDetails.combine(fetchRecords) { userDataState, recordsDataState ->
                    if (userDataState.type == MessageType.Success && recordsDataState.type == MessageType.Success) {
                        Timber.i("finished -----")
                        workScheduler.scheduleSyncWorkerRepeating()
                        preferenceStorage.isDataAlreadySync = true
                    }
                }.collect {
                    // just for combine call
                }
            }

        }
    }

    fun loadCurrentUser() = launch {
        userRepository.get().collect { user -> _userDataEvent.postValue(user) }
    }

}
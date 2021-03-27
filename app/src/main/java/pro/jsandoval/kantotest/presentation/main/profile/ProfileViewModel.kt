package pro.jsandoval.kantotest.presentation.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pro.jsandoval.kantotest.data.MessageType
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.domain.model.User
import pro.jsandoval.kantotest.domain.repository.RecordRepository
import pro.jsandoval.kantotest.domain.repository.UserRepository
import pro.jsandoval.kantotest.util.base.BaseViewModel
import pro.jsandoval.kantotest.util.event.SingleLiveEvent
import timber.log.Timber

class ProfileViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val recordRepository: RecordRepository
) : BaseViewModel() {

    val userDataEvent = SingleLiveEvent<User>()
    val recordsDataEvent = SingleLiveEvent<List<Record>>()

    init {
        loadCurrentUser()
        loadRecords()
    }

    private fun loadCurrentUser() = launch {
        userRepository.get().collect { user -> userDataEvent.postValue(user) }
    }

    private fun loadRecords() = launch {
        recordRepository.getRecords().collect { dataState ->
            when (dataState.type) {
                MessageType.Success -> recordsDataEvent.postValue(dataState.data)
                MessageType.Error -> Timber.e("Error loading records from network: $dataState.message")
            }
        }
    }

}
package pro.jsandoval.kantotest.presentation.main.profile

import androidx.hilt.lifecycle.ViewModelInject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import pro.jsandoval.kantotest.data.util.MessageType
import pro.jsandoval.kantotest.domain.model.Record
import pro.jsandoval.kantotest.domain.repository.RecordRepository
import pro.jsandoval.kantotest.util.base.BaseViewModel
import pro.jsandoval.kantotest.util.event.SingleLiveEvent
import timber.log.Timber

class ProfileViewModel @ViewModelInject constructor(
    private val recordRepository: RecordRepository
) : BaseViewModel() {

    val recordsDataEvent = SingleLiveEvent<List<Record>>()

    init {
        loadRecords()
    }

    private fun loadRecords() = launch {
        recordRepository.getRecords().collect { records -> recordsDataEvent.postValue(records) }
    }

}
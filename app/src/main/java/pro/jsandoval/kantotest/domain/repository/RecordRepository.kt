package pro.jsandoval.kantotest.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.DataState
import pro.jsandoval.kantotest.domain.model.Record

interface RecordRepository {

    fun getRecords(): Flow<DataState<List<Record>>>

}
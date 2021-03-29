package pro.jsandoval.kantotest.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.util.DataState
import pro.jsandoval.kantotest.domain.model.Record

interface RecordRepository {

    suspend fun fetchRecords(): Flow<DataState<Boolean>>

    suspend fun getRecords(): Flow<List<Record>>

    suspend fun likeRecord(record: Record, newStatus: Boolean)

}
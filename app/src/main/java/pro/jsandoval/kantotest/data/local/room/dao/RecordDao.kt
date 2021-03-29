package pro.jsandoval.kantotest.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.local.room.entity.RecordEntity

@Dao
interface RecordDao {

    @Query("SELECT  * FROM ${RecordEntity.TABLE_NAME}")
    fun getRecords(): Flow<List<RecordEntity>>

    @Insert
    suspend fun insert(recordList: List<RecordEntity>)

    @Update
    suspend fun update(recordEntity: RecordEntity)

    @Query("DELETE FROM ${RecordEntity.TABLE_NAME}")
    suspend fun delete()

    @Query("SELECT * FROM ${RecordEntity.TABLE_NAME} WHERE id = :recordId")
    suspend fun getSimple(recordId: Long): RecordEntity?

}
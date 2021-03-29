package pro.jsandoval.kantotest.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.local.room.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    fun get(): Flow<UserEntity?>

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    suspend fun getSimple(): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Update
    suspend fun update(userEntity: UserEntity)

}
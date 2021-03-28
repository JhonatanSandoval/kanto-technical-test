package pro.jsandoval.kantotest.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import pro.jsandoval.kantotest.data.local.room.dao.RecordDao
import pro.jsandoval.kantotest.data.local.room.dao.UserDao
import pro.jsandoval.kantotest.data.local.room.entity.RecordEntity
import pro.jsandoval.kantotest.data.local.room.entity.UserEntity

@Database(
    entities = [UserEntity::class, RecordEntity::class],
    version = KantoTestDatabase.DB_VERSION,
    exportSchema = false
)
@TypeConverters(KantoTestDatabaseConverters::class)
abstract class KantoTestDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun recordDao(): RecordDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "KantoTest-AppDb"
    }

}
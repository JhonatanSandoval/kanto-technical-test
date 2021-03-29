package pro.jsandoval.kantotest.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = RecordEntity.TABLE_NAME)
data class RecordEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo var user: UserEntity? = null,
    @ColumnInfo var song: String = "",
    @ColumnInfo var video: String = "",
    @ColumnInfo var preview: String = "",
    @ColumnInfo var likes: Int = 0,
    @ColumnInfo var likedByMe: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "records"
        fun recordEntity(block: RecordEntity.() -> Unit) = RecordEntity().apply(block)
    }
}
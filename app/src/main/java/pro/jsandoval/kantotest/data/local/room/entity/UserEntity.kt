package pro.jsandoval.kantotest.data.local.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = false) var userId: String = "",
    @ColumnInfo var name: String = "",
    @ColumnInfo var username: String = "",
    @ColumnInfo var img: String = "",
    @ColumnInfo var biography: String = "",
    @ColumnInfo var followers: Int = 0,
    @ColumnInfo var followed: Int = 0,
    @ColumnInfo var views: Int = 0
) {
    companion object {
        const val TABLE_NAME = "user"
        fun userEntity(block: UserEntity.() -> Unit) = UserEntity().apply(block)
    }
}
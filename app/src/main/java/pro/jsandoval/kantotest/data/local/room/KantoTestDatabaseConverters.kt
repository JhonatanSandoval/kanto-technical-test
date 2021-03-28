package pro.jsandoval.kantotest.data.local.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import pro.jsandoval.kantotest.data.local.room.entity.UserEntity

class KantoTestDatabaseConverters {

    @TypeConverter
    fun saveUserEntity(user: UserEntity?): String = Gson().toJson(user)

    @TypeConverter
    fun restoreUser(value: String?): UserEntity? {
        val typeToken = object : TypeToken<UserEntity?>() {}.type
        return Gson().fromJson(value, typeToken)
    }

}
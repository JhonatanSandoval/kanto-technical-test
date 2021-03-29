package pro.jsandoval.kantotest.domain.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.util.DataState
import pro.jsandoval.kantotest.domain.model.User

interface UserRepository {

    suspend fun fetchUserDetails(): Flow<DataState<Boolean>>

    suspend fun get(): Flow<User>

    suspend fun updateCurrentUser(userDetails: User)

    // pass a valid userId if you want
    suspend fun uploadUserAvatar(userId: String = "default", filePath: Uri)

}
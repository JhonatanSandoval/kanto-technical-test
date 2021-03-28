package pro.jsandoval.kantotest.domain.repository

import kotlinx.coroutines.flow.Flow
import pro.jsandoval.kantotest.data.util.DataState
import pro.jsandoval.kantotest.domain.model.User

interface UserRepository {

    suspend fun fetchUserDetails(): Flow<DataState<Boolean>>

    suspend fun get(): Flow<User>

    suspend fun updateCurrentUser(userDetails: User)

}